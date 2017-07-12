/**
 * 
 */
package morris_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author speng created on Oct 6, 2015
 */
public class GameConsoleGui {
	private BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));

	public void run() {
		GameController game = new GameController();
		boolean AI = false;
		Player AI_player = game.player_1;
		while (game.state != GameState.player_1_win
				&& game.state != GameState.player_2_win) {
			System.out.println(game.board);
			System.out.println(game.current_player);
			System.out.println(game.state);
			if (AI && game.current_player == AI_player) {
				// TODO randomly generate a coordinate
			} else {
				Coord coord = getInputCoord();
				Position pos = new Position(coord, game.current_player);
				if (!game.move(pos)) {
					System.out.println("illegal move");
				}
			}
		}
		System.out.println(game.board);
		System.out.println(game.state);
	}

	private Coord getInputCoord() {
		String userInput = null;

		try {
			userInput = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// in.close();
		return parseInput(userInput);
	}

	private Coord parseInput(String userInput) {
		Pattern p = Pattern
				.compile("\\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*");
		Matcher m = p.matcher(userInput);
		if (m.matches()) {
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			System.out.printf("x = %d, y = %d\n", x, y);// for debugging
			return new Coord(x, y);
		}
		return null;
	}

}
