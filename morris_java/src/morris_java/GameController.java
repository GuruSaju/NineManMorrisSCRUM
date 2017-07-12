package morris_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
@SuppressWarnings("serial")
public class GameController implements Serializable{

	Player player_1;
	Player player_2;
	Player current_player;
	Position last_pos;
	String state;
	GameBoard board;
	private List<Coord> potentialpos;
	private int numMoves;
	private PlayerType playerType;
	private int AIlevel;
	private static String gameSaveDir = "log";
	private static String gameSaveFilePath = gameSaveDir + File.separator + "game.ser";
	/**
	 * @return the playerType
	 */
	PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * @return the aIlevel
	 */
	int getAIlevel() {
		return AIlevel;
	}

	/**
	 * @param playerType the playerType to set
	 */
	void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	/**
	 * @param aIlevel the aIlevel to set
	 */
	void setAIlevel(int aIlevel) {
		AIlevel = aIlevel;
	}

	/**
	 * 
	 */
	public GameController()
	{
		this.player_1 = new Player("player_1", "X");
		this.player_2 = new Player("player_2", "O");
		this.last_pos = null;
		this.current_player = this.player_1;
		this.setState(GameState.drop);
		this.board = new GameBoard();
		this.potentialpos = new ArrayList<Coord>();
	}

	/**
	 * @param state the state to set
	 */
	private void setState(String state) {
		this.state = state;
		this.current_player.setState(state);
	}

	/**
	 * @return the board
	 */
	GameBoard getBoard() {
		return board;
	}

	public String getState() {
		return this.state;
	}
	
	public Player getCurrentPlayer() {
		return this.current_player;
	}

	public Player getOpponentPlayer() {
		if(this.player_1.equals(this.current_player)) {
			return this.player_2;
		} else {
			return this.player_1;
		}
	}
	public boolean move(Position pos){

		if(!this.board.isLegal(this.state, this.current_player, this.last_pos, pos))
		{
			return false;
		}
		this.setNumMoves(this.getNumMoves() + 1);
		//apply change to board
		this.board.change(this.state, this.last_pos, pos);

		//state transition
		if (this.state.equals(GameState.move_1))
		{
			this.last_pos = pos;
			potentialpos = this.board.generateMove2PotentialPos(this.last_pos.getCoord());
			this.setState(GameState.move_2);
			return true;
		}
		else if (this.state.equals(GameState.fly_1))
		{
			this.last_pos = pos;
			potentialpos = this.board.generateFly2PotentialPos();
			this.setState(GameState.fly_2);
			return true;
		}
		else if (this.state.equals(GameState.drop) || (this.state.equals(GameState.move_2) && !this.board.isUnselect()) 
				|| (this.state.equals(GameState.fly_2)  && !this.board.isUnselect())) 
		{
			if(this.board.isMill(pos))
			{
				this.setState(GameState.new_mill);
				this.current_player.formedNewMill();
				return true;
			}
			// the case did not form a mill
			this.change_player();
			this.decide_new_player_state();
			return true;
		}
		else if(this.state.equals(GameState.move_2) && this.board.isUnselect()) {
			this.setState(GameState.move_2);
			this.last_pos = pos;
			potentialpos = this.board.generateMove2PotentialPos(this.last_pos.getCoord());
			this.board.setUnselect();
			return true;
		}
		else if(this.state.equals(GameState.fly_2) && this.board.isUnselect()) {
			this.setState(GameState.fly_2);
			this.last_pos = pos;
			potentialpos = this.board.generateFly2PotentialPos();
			this.board.setUnselect();
			return true;
		}
		else
		{
			//the case state == new_mill
			this.change_player();
			this.decide_new_player_state();
			return true;
		}
	}

	public void change_player() {
		if (this.current_player.equals(this.player_1))
		{
			this.current_player = this.player_2;
		}
		else
		{
			this.current_player = this.player_1;        	
		}

	}
	public void decide_new_player_state() {
		int winningCond = EvalFunctions.winningConfig(board, this.player_1, this.player_2);
		if(winningCond == 1) {
			this.setState(GameState.player_1_win);
			return;
		} else if (winningCond == -1) {
			this.setState(GameState.player_2_win);
			return;
		}
		
		if (this.current_player.getTokensPut() < 9)
		{
			this.setState(GameState.drop);
		}
//		else if (this.current_player.getTokensLeft() < 3)
//		{
//			this.set_opponent_win();
//		}
		else if (this.current_player.getTokensLeft() == 3)
		{
			this.setState(GameState.fly_1);
		}
		else
		{
			this.setState(GameState.move_1);
		}
		this.current_player.setState(this.state);
	}

// no longer useful since we have EvalFunctions.winningConfig()
//	private void set_opponent_win() 
//	{
//		if (this.current_player.equals(this.player_1))
//		{
//			this.setState(GameState.player_2_win);
//		}
//		else{
//			this.setState(GameState.player_1_win);
//		}
//	}

	public HashMap<Coord, String> getBoardStatus() {
		return board.getBoardStatus();
	}

	public String getBoardLayout() {
		return board.toString();
	}
	
	public List<Coord> getPotentialPos() {
		return potentialpos;
	}

	public GameController deepClone() {
		GameController clonedGame = null;
		try {
			clonedGame = (GameController) ObjectCloner.deepCopy(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("deep copy failed");
			System.exit(1);
		}
		return clonedGame;
	}

	public int getNumMoves() {
		return numMoves;
	}

	private void setNumMoves(int numMoves) {
		this.numMoves = numMoves;
	}

	public static void saveGame(GameController game) {
		new File(GameController.gameSaveDir).mkdirs();
		try {
			FileOutputStream fileOut = new FileOutputStream(GameController.gameSaveFilePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.printf("game data is saved in %s\n", GameController.gameSaveFilePath);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static GameController loadGame() {
		GameController game = null;
		try {
			FileInputStream fileIn = new FileInputStream(GameController.gameSaveFilePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			game = (GameController) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("GameController class not found\n");
			c.printStackTrace();
			return null;
		}
		return game;
	}

}
