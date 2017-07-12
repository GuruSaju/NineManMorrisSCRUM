/**
 * 
 */
package morris_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameConsoleGui {
	private BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));

	public static void main(String[] args) {
		GameConsoleGui gameConsoleGui = new GameConsoleGui();
//		gameConsoleGui.run(false, PlayerType.HUMAN_VS_AI, false, 5);//humman vs AI
//		gameConsoleGui.run(false, PlayerType.HUMAN_VS_HUMAN, false, 5);//humman vs human
		gameConsoleGui.run(false, PlayerType.AI_VS_AI, false, 1);//
	}

	public void run(boolean loadGame, PlayerType playerType, boolean humanFirst, int level) {
		GameController game = null;
		if(loadGame) {
			game = GameController.loadGame();
		} else {
			game = new GameController();
			game.setAIlevel(level);
			game.setPlayerType(playerType);
		}
		GameLog gameLog = new GameLog();
		Player AI_player = game.player_1;
		AI ai = new AI(game.getAIlevel(), AI_player);
		AI test_ai = new AI(-1, game.player_2);
		gameLog.addToLog("playerType = " + game.getPlayerType().toString());
		gameLog.addToLog("difficultyLevel = " + game.getAIlevel());
		while (!game.getState().equals(GameState.player_1_win) 
				&& !game.getState().equals(GameState.player_2_win) ) {
			gameLog.addToLog(game.getBoardLayout());
			gameLog.addToLog(game.getCurrentPlayer().toString());
			gameLog.addToLog(game.getState());
			if(game.getState().equals(GameState.move_2) || game.getState().equals(GameState.fly_2)) {
				gameLog.addToLog(game.getPotentialPos().toString());
			}
			Coord coord = null;		
			switch (game.getPlayerType()) {
			case HUMAN_VS_HUMAN:
				coord = getInputCoord();
				break;
			case HUMAN_VS_AI:
				if(game.getCurrentPlayer().equals(AI_player)) {
					coord = ai.makeMove(game);
				} else {
					coord = getInputCoord();
				}
				break;
			case AI_VS_AI:
				if(game.getCurrentPlayer().equals(AI_player)) {
					coord = ai.makeMove(game);
				} else {
					coord = test_ai.makeMove(game);
				}
				break;
			default:
				System.err.println("invalid player type");
				break;
			}
			
			gameLog.addToLog(coord.toStringDebug());
			Position pos = new Position(coord, game.getCurrentPlayer());
			if (!game.move(pos)) {
				gameLog.addToLog("illegal move");
			}
			GameController.saveGame(game);
		}
		gameLog.addToLog(game.getBoardLayout());
		gameLog.addToLog(game.getState());
		gameLog.addToLog("game finished in " + game.getNumMoves() + " moves");
		gameLog.close();
	}

	private Coord getInputCoord() {
		String userInput = null;

		try {
			userInput = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseInput(userInput);
	}

	private Coord parseInput(String userInput) {
		Pattern p = Pattern
				.compile("\\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*");
		Matcher m = p.matcher(userInput);
		if (m.matches()) {
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			Coord coord = new Coord(x, y);
			return coord;
		}
		return null;
	}

}

//
//can you and guru record 2 complete system tests and write them into the report? I forgot to mention that.
//		yeah which section is it on the doc?
//				III.II System test
//				do we have tests or we have to write new tests?
//						run the game and capture the screen shots for each step. There're 2 tests in that section, 
//						one is to let human beats AI
//						one is to let AI beats human
//						
//						okay. just screenshots. i can do for one.
//						what do you mean by each step? each click? 
//								each click or each important move. some explaination on what's happening would be great
//								and please try to give them to me as soon as possible so I can integrate our reports and submit
//								
//								I can work on it and email you by 2. I have class after this and then I can work on it. 
//								okay i will email you before lunch
//								i will take human beats AI