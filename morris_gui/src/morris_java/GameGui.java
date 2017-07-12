package morris_java;

import javax.swing.JFrame;

public class GameGui {
	public void start() throws InterruptedException{
		JFrame f = new JFrame("Nine Men's Morris");
		f.setSize(800, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameController game = new GameController();
		boolean AI = false;
		Player AI_player = game.player_1;
		
		f.add(game.guiboard);
		
		f.setResizable(false);
		f.setVisible(true);
		
		while (game.state != GameState.player_1_win
				&& game.state != GameState.player_2_win) {
			if(game.guiboard.getClickPoint() > 24){
				Thread.currentThread();
				Thread.sleep(1);
				continue;
			}
			System.out.println(game.board);
			System.out.println(game.current_player);
			System.out.println(game.state);
			if (AI && game.current_player == AI_player) {
				// TODO randomly generate a coordinate
			} else {
				Coord coord = getInputCoord(game.guiboard);
				
				Position pos = new Position(coord, game.current_player);
				if (!game.move(pos)) {
					System.out.println("illegal move");
				}
				else {
					if(game.state == "move_2" || game.state == "fly_2")
						game.selectTokenOnGui(coord);
					else
						game.updateGui();
				}
			}
		}
		System.out.println(game.board);
		System.out.println(game.state);
	}
	
	private Coord[] coordinate = {null, new Coord(0,0), new Coord(0,3), new Coord(0,6),
			                            new Coord(3,6), new Coord(6,6), new Coord(6,3),
			                            new Coord(6,0), new Coord(3,0), new Coord(1,1),
			                            new Coord(1,3), new Coord(1,5), new Coord(3,5),
			                            new Coord(5,5), new Coord(5,3), new Coord(5,1),
			                            new Coord(3,1), new Coord(2,2), new Coord(2,3),
			                            new Coord(2,4), new Coord(3,4), new Coord(4,4),
			                            new Coord(4,3), new Coord(4,2), new Coord(3,2)};

	private Coord getInputCoord(GuiBoard b) {
		Coord userInput = null;
		
		userInput = coordinate[b.getClickPoint()];
		// in.close();
		
		b.setCLickPoint();
		return userInput;
	}

}
