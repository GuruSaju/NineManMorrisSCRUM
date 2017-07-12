package morris_java;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui {
	JFrame f = new JFrame("Nine Men's Morris");
	private boolean restart =false;
	public void start(boolean loadGame, PlayerType playerType, boolean humanFirst, int level) throws InterruptedException{

		f.setSize(1200, 980);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameController game = null;
		if(loadGame) {
			game = GameController.loadGame();
		} else {
			game = new GameController();
			game.setAIlevel(level);
			game.setPlayerType(playerType);
		}

		GameLog gameLog = new GameLog();
		GuiBoard guiboard = new GuiBoard();

		Player AI_player = humanFirst ? game.player_2 : game.player_1;
		AI ai = new AI(game.getAIlevel(), AI_player);

		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add (guiboard,BorderLayout.CENTER);
		JLabel L2 = new JLabel("<html><pre><span style='font-size:20px'> <font color='blue'>PLAYER 1</font>          State:" + game.state   +"            PLAYER 2  "
				+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
				+ "</span></pre></html>",JLabel.CENTER);
		JPanel P2 = new JPanel(new BorderLayout());                  // Make a JPanel;       
		P2.add(L2,BorderLayout.CENTER);
		P2.setBackground(new Color(199, 199, 199));
		JButton savebutton =new JButton("Save");
		JButton restartbutton =new JButton("Restart");
		savebutton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				//TODO save
				System.out.println("saved");
				//f.setVisible(false); 

			} 
		} );
		restartbutton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				//TODO save
				System.out.println("restart");
				restart=true;
				f.setVisible(false); 


			} 
		} );
		JPanel subPanel = new JPanel(new GridLayout(1,2));

		subPanel.add( savebutton);
		subPanel.add( restartbutton);

		JLabel L3 = new JLabel("<html><pre><span style='font-size:20px'> <font color='blue'>"+game.player_1+"</font>          -           <font color='#FF00FF'>" +game.player_2+"</font>  "
				+ "<br>    "+game.player_1.getTotalMill()+"                                  "+game.player_2.getTotalMill()+""
				+ "</span></pre></html>",JLabel.CENTER);
		JPanel P3 = new JPanel(new BorderLayout()); 
		// Make a JPanel;

		P3.setBackground(new Color(199,199,199));
		P3.add(subPanel,BorderLayout.CENTER);
		P3.add(L3,BorderLayout.SOUTH);
		f.getContentPane().add (P2,BorderLayout.SOUTH);
		f.getContentPane().add (P3,BorderLayout.NORTH);
		f.setVisible(true);
		guiboard.updateGui(game.getBoardStatus());
		int nmill=0;
		while (!game.state.equals(GameState.player_1_win) 
				&& !game.state.equals(GameState.player_2_win)) {
			if(restart){
				return;
			}
			
			if(!game.state.equals(GameState.new_mill))
				L3.setText("<html><pre><span style='font-size:20px'> <font color='blue'> PLAYER 1</font>          -           <font color='#FF00FF'>PLAYER 2</font>  "
						+ "<br>    "+game.player_1.getTotalMill()+"                             "+game.player_2.getTotalMill()+""
						+ "</span></pre></html>");
			if(guiboard.getClickPoint() > 24 && !(game.getPlayerType().equals(PlayerType.HUMAN_VS_AI) && game.current_player.equals(AI_player))){
				Thread.currentThread();
				Thread.sleep(1);
				continue;
			}
			gameLog.addToLog(game.getBoardLayout());
			gameLog.addToLog(game.current_player.toString());
			gameLog.addToLog(game.state);
			if(nmill==0){
				if(game.current_player.equals(game.player_1))
					L2.setText("<html><pre><span style='font-size:20px'> <font color='blue'>PLAYER 1</font>              State:" + game.state   +"            PLAYER 2  "
							+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
							+ "</span></pre></html>");

				else
					L2.setText("<html><pre><span style='font-size:20px'> PLAYER 1              State:" + game.state   +"            <font color='#FF00FF'>PLAYER 2</font>   "
							+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
							+ "</span></pre></html>");

			}

			if (game.getPlayerType().equals(PlayerType.HUMAN_VS_AI) && game.current_player.equals(AI_player)) {
				//AI goes here
				Coord coord = ai.makeMove(game);
				Position pos = new Position(coord, game.getCurrentPlayer());
				gameLog.addToLog(pos.getCoord().toString());
				if (!game.move(pos)) {
					gameLog.addToLog("illegal move");
				}
				else {
					if(game.state.equals(GameState.move_2) || game.state.equals(GameState.fly_2)) {
						guiboard.selectTokenOnGui(pos.getCoord(), game.getBoardStatus());
						guiboard.highlightPotential(game.getPotentialPos());
					}
					else
						guiboard.updateGui(game.getBoardStatus());
				}
			} 
			else {
				Coord coord = getInputCoord(guiboard);
				gameLog.addToLog(coord.toString());
				Position pos = new Position(coord, game.current_player);
				if(nmill==0){

				}
				nmill=0;
				if (!game.move(pos)) {
					gameLog.addToLog("illegal move");
					if(game.current_player.equals(game.player_1))
						L2.setText("<html><pre><span style='font-size:20px'> <font color='blue'>PLAYER 1</font>              State:" + game.state   +"            PLAYER 2  "
								+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
								+ "</span></pre></html>");

					else
						L2.setText("<html><pre><span style='font-size:20px'>PLAYER 1              State:" + game.state   +"            <font color='#FF00FF'>PLAYER 2</font>   "
								+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
								+ "</span></pre></html>");

				}
				else {
					if(game.state.equals(GameState.move_2) || game.state.equals(GameState.fly_2)) {
						guiboard.selectTokenOnGui(coord, game.getBoardStatus());
						guiboard.highlightPotential(game.getPotentialPos());
					}
					else
						guiboard.updateGui(game.getBoardStatus());
				}
				if(!game.state.equals(GameState.illegal_move) && !game.state.equals(GameState.new_mill) ){
					if(game.current_player.equals(game.player_2) ){
						Thread.sleep(700);
						L2.setText("<html><pre><span style='font-size:20px'> PLAYER 1              State:" + game.state   +"            <font color='#FF00FF'>PLAYER 2</font>   "
								+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
								+ "</span></pre></html>");

					}else{
						Thread.sleep(700);
						L2.setText("<html><pre><span style='font-size:20px'> <font color='blue'>PLAYER 1</font>              State:" + game.state   +"            PLAYER 2  "
								+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
								+ "</span></pre></html>");

					}


				}


					if(!game.state.equals(GameState.illegal_move) && game.state.equals(GameState.new_mill) ){
						nmill=1;

						if(game.current_player.equals(game.player_2) ){
							//							game.player_1.incrementRemoved();
							Thread.sleep(700);
							L2.setText("<html><pre><span style='font-size:20px'> PLAYER 1              State:" + game.state   +"            <font color='#FF00FF'> PLAYER 2</font>   "
									+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
									+ "</span></pre></html>");

						}else{
							//				        	 game.player_2.incrementRemoved();
							Thread.sleep(700);
							L2.setText("<html><pre><span style='font-size:20px'> <font color='blue'>PLAYER 1</font>              State:" + game.state   +"            PLAYER 2  "
									+ "<br>tokens on board:"+game.player_1.getTokensLeft()+"                     tokens on board:"+game.player_2.getTokensLeft()+""
									+ "</span></pre></html>");


						}

					
				}
			}
			GameController.saveGame(game);
		}
		gameLog.addToLog(game.getBoardLayout());
		gameLog.addToLog(game.state);
		gameLog.addToLog("game finished in " + game.getNumMoves() + " moves");
		gameLog.close();
		L3.setText("<html><pre><span style='font-size:20px'> <font color='blue'> PLAYER 1</font>          -           <font color='#FF00FF'>PLAYER 2</font>  "
				+ "<br>    "+game.player_1.getTotalMill()+"                                  "+game.player_2.getTotalMill()+""
				+ "</span></pre></html>");
		if(game.state.equals(GameState.player_1_win)){
			L2.setText("<html> <span style='font-size:20px'><font color='blue'>PLAYER 1 WINS</font> <br> </span></html>");
		}
		else if(game.state.equals(GameState.player_2_win)){
			L2.setText("<html> <span style='font-size:20px'><font color='#FF00FF'>PLAYER 2 WINS</font> <br> </span></html>");
		}

	}
	public boolean isRestart() {
		return restart;
	}



	public void setRestart(boolean restart) {
		this.restart = restart;
	}



	private Coord getInputCoord(GuiBoard b) {
		Coord inputCoord = null;
		inputCoord = b.getCoordByGuipointIndx(b.getClickPoint());
		b.setCLickPoint();
		return inputCoord;
	}
}