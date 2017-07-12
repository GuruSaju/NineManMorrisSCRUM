/**
 * 
 */
package morris_java;

import java.util.List;
import java.util.Random;

public class AI {
	private int depth;
	private Player me;
	private Coord bestMove;

	private void setDepth(int difficultLevel) {
		this.depth = difficultLevel;
		if(this.depth < 0) {
			this.depth = 0;
		}
	}
	
	public AI(int difficultLevel, Player me) {
		this.setDepth(difficultLevel);
		this.me = me;
	}
	
	public Coord makeMove(GameController game) {
		GameController clonedGame = game.deepClone();
		Coord coord = null;
		if(depth == 0) {// a stupid AI who make random legal moves
			coord = this.makeRandomMove(clonedGame.getBoard(), clonedGame.getState(), clonedGame.last_pos);
		} else {
			System.out.println("caculating... ...");
			alphaBeta(game, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
			coord = this.bestMove;
//			System.out.println("final value: " + value);
		}
		return coord;
	}
	
	private Coord makeRandomMove(GameBoard board, String gameState, Position lastPos) {
		Coord coord;
		Coord lastCoord = null;
		List<Coord> list;
		Random randomGenerator = new Random();
		if(gameState.equals(GameState.move_2)) {
			lastCoord = lastPos.getCoord();
		}
		list = board.generatePotentialPos(gameState, this.me, lastCoord);
		int index = randomGenerator.nextInt(list.size());
		coord = list.get(index);
//		if(gameState.equals(GameState.move_1)) {
//			this.lastPos = pos;
//		}
		return coord;
	}
	
	private int alphaBeta(GameController game, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if(depth == 0 || game.getState().equals(GameState.player_1_win) || game.getState().equals(GameState.player_2_win)) {
			if(game.getState().equals(GameState.new_mill)) {
//				System.out.println("new mill");
			}
			return EvalFunctions.evalFunction(game, this.me);//consider new mill
		}
		if(maximizingPlayer) {
			int value = Integer.MIN_VALUE;
			Coord lastCoord = null;
			if(game.getState().equals(GameState.move_2)) {
				lastCoord = game.last_pos.getCoord();
			}
			List<Coord> list = game.getBoard().generatePotentialPos(game.getState(), game.current_player, lastCoord);//what if empty?
			for(Coord coord: list) {
				GameController clonedGame = game.deepClone();
				Position pos = new Position(coord, clonedGame.current_player);
				clonedGame.move(pos);
//				System.out.println(clonedGame.getState());
//				if(clonedGame.getState().equals(GameState.player_1_win)) {
//					System.out.println("check ");//
//				}
//				printDebugInfo(clonedGame, pos);
				int new_value;
				if(clonedGame.getState().equals(GameState.fly_2) || clonedGame.getState().equals(GameState.move_2)
						|| clonedGame.getState().equals(GameState.new_mill) ) {
					new_value = this.alphaBeta(clonedGame, depth - 1, alpha, beta, true);
				} else {
					new_value = this.alphaBeta(clonedGame, depth - 1, alpha, beta, false);
				}
				if(depth == this.depth) {
					Random randomGenerator = new Random();
					if(alpha < new_value) {
						this.bestMove = pos.getCoord();
					} 
					else if (alpha == new_value && randomGenerator.nextBoolean()) {
						this.bestMove = pos.getCoord();
					}
				}
				value = Math.max(value, new_value);
				alpha = Math.max(alpha, value);
				if(beta <= alpha) {
					break;
				}
			}
			return value;
		} else {
			int value = Integer.MAX_VALUE;
			Coord lastCoord = null;
			if(game.getState().equals(GameState.move_2)) {
				lastCoord = game.last_pos.getCoord();
			}
			List<Coord> list = game.getBoard().generatePotentialPos(game.getState(), game.current_player, lastCoord);//what if empty?
			for(Coord coord: list) {
				GameController clonedGame = game.deepClone();
				Position pos = new Position(coord, clonedGame.current_player);
				clonedGame.move(pos);
//				printDebugInfo(clonedGame, pos);
				if(clonedGame.getState().equals(GameState.fly_2) || clonedGame.getState().equals(GameState.move_2)
						|| clonedGame.getState().equals(GameState.new_mill) ) {
					value = Math.max(value, this.alphaBeta(clonedGame, depth - 1, alpha, beta, false));
				} else {
					value = Math.min(value, this.alphaBeta(clonedGame, depth - 1, alpha, beta, true));
				}
				beta = Math.min(beta, value);
				if(beta <= alpha) {
					break;
				}
			}
			return value;
		}
	}
	
//	private void printDebugInfo(GameController game, Position pos) {
//		System.out.println(pos.getCoord());
//		System.out.print(game.getBoardLayout());
//	}
}
