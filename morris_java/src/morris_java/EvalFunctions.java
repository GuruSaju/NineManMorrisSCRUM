/**
 * 
 */
package morris_java;

import java.util.Arrays;
import java.util.List;

public class EvalFunctions {
	// use eval function definition from 
	//https://kartikkukreja.wordpress.com/2014/03/17/heuristicevaluation-function-for-nine-mens-morris/
	private static int formedMill(GameBoard board, Player me, Player opponent) {
		//TODO 1 if a mill is formed in the last move by the player (and an opponent’s piece should be grabbed 
		//in this move), -1 if a mill was formed by the opponent in the last move, 0 otherwise 
		int ret = 0;
		if(me.getState().equals(GameState.new_mill)) {
			ret = 1;
		} else if (opponent.getState().equals(GameState.new_mill)) {
			ret = -1;
		}
		return 0;
	}

	private static int diffNumMills(Player player, Player opponent) {
		//TODO Difference between the number of yours and yours opponent’s mills
		int numMills_me = numMills(player);
		int numMills_opponent = numMills(opponent);
		return numMills_me - numMills_opponent;
	}

	private static int numMills(Player player) {
		//TODO How many mills this player has
		return player.getTotalMill();
	}

	private static int diffNumBlockedOpponentTokens(GameBoard board, Player me, Player opponent) {
		//TODO Difference between the number of yours opponent’s and yours blocked pieces (pieces which don’t have 
		//an empty adjacent point)
		int myBlockedTokens = numBlockedTokens(board, me);
		int opponentBlockedTokens = numBlockedTokens(board, opponent);
		return opponentBlockedTokens - myBlockedTokens;
	}

	private static int numBlockedTokens(GameBoard board, Player player){
		//tokens cannot be blocked when player is in flying phase
		if(!player.getState().equals(GameState.fly_1) && !player.getState().equals(GameState.fly_2) ) { 
			List<Coord> potentialpos = null;
			int blocked = 0;
			for(Coord coord : GameBoard.coordList){
				if(player.equals(board.getPositions().get(coord).getPlayer())) {
					potentialpos = board.generateMove2PotentialPos(coord);
					if(potentialpos.size() == 0)
						blocked++;
				}
			}
			return blocked;
		}
		return 0;
	}


	private static int numBlockedOpponentTokens(GameBoard board, Player player) {
		//TODO how many tokens of opponent are blocked(have no legal move)
		return 0;
	}

	private static int diffNumTokens(GameBoard board, Player me, Player opponent) {
		//TODO Difference between the number of yours and yours opponent’s pieces
		return EvalFunctions.numTokens(board, me) - EvalFunctions.numTokens(board, opponent);
	}

	private static int numTokens(GameBoard board, Player player) {
		//TODO how many tokens this player has
		return player.getTokensLeft();
	}

	private static int diffNum2TokenConf(GameBoard board, Player me, Player opponent) {
		//TODO Difference between the number of yours and yours opponent’s 2 piece configurations
		//A “2 pieces configuration” refers to having 2 pieces on one line (and the other place
		//empty, so mill possibility).
		int num2TokenConf_me = num2TokenConf(board, me);
		int num2TokenConf_opponent = num2TokenConf(board, opponent);
		return num2TokenConf_me - num2TokenConf_opponent;
	}

	private static int num2TokenConf(GameBoard board, Player player) {
		//TODO how 2-token configurations this player has
		//A “2 pieces configuration” refers to having 2 pieces on one line (and the other place
		//empty, so mill possibility).
		int num2TokenConf = 0;
		for(Coord coord: GameBoard.coordList) {
			if(board.getPositions().get(coord).getPlayer() != null) 
				continue;
			
			Position pos = new Position(coord, player);
			if(board.isMill(pos)) {
				num2TokenConf++;
			}
		}
		return num2TokenConf;
	}

	private static int diffNum3TokenConfig(GameBoard board, Player me, Player opponent) {
		//TODO Difference between the number of yours and yours opponent’s 3 piece configurations
		// A “3 pieces configuration” offers the opportunity to form a mill in two places so the
		//opponent cannot stop you. These refer to the first stage (placing pieces) and also to the final phase.
		int num3TokenConf_me = num3TokenConf(board, me);
		int num3TokenConf_opponent = num3TokenConf(board, opponent);
		return num3TokenConf_me - num3TokenConf_opponent;
	}

	private static int num3TokenConf(GameBoard board, Player player) {
		//TODO how many 3-token configurations this player has
		// A “3 pieces configuration” offers the opportunity to form a mill in two places so the
		//opponent cannot stop you. These refer to the first stage (placing pieces) and also to the final phase.
		List<Coord> threeConfCoordList = Arrays.asList(new Coord(0, 0), new Coord(0, 6), new Coord(1, 1), 
				new Coord(1, 5), new Coord(2, 2), new Coord(2, 4), new Coord(4, 2), new Coord(4, 4), 
				new Coord(5, 1), new Coord(5, 5), new Coord(6, 0), new Coord(6, 6));
		int num3TokenConf = 0;
		for(Coord coord : threeConfCoordList) {
			if(board.getPositions().get(coord).getPlayer() != player) 
				continue;
			
			Position pos = board.getPositions().get(coord);
			int x = coord.getX();
			int y = coord.getY();
			
			if(x == y) { //from (0, 0) to (6, 6)
				if(x < 3 && (pos.getDown().getPlayer() == player && pos.getRight().getPlayer() == player))   //upper left 
					num3TokenConf++;
				else if(pos.getUp().getPlayer() == player && pos.getLeft().getPlayer() == player) { //down right
					num3TokenConf++;
				}
			}
			else {  //from (0, 6) to (6, 0)
				if(x < y && (pos.getDown().getPlayer() == player && pos.getLeft().getPlayer() == player)) //upper right
					num3TokenConf++;
				else if(pos.getUp().getPlayer() == player && pos.getRight().getPlayer() == player) //down left
					num3TokenConf++;
			}
		}
		return num3TokenConf;
	}

	private static int diffNumDoubleMills(GameBoard board, Player me, Player opponent) {
		//TODO Difference between number of yours and yours opponent’s double mills
		// A double-mills is one in which two mills share a common piece
		return 0;
	}

	private static int numDoubleMills(GameBoard board, Player player) {
		//TODO how many double-mills configurations this player has
		// A double-mills is one in which two mills share a common piece
		return 0;
	}

	static int winningConfig(GameBoard board,Player me,Player opponent) {
		//TODO 1 if the state is winning for the player, -1 if losing, 0 otherwise
		if(lessThan3Tokens(opponent) || allTokensBlocked(board, opponent))
			return 1;
		if(lessThan3Tokens(me) || allTokensBlocked(board, me))
			return -1;
		return 0;
	}

	private static boolean lessThan3Tokens(Player player) {
		//TODO true if this player has passed drop phase and has less than 3 tokens
		if(player.getTokensPut() > 9 && player.getTokensLeft() < 3)
			return true;
		return false;
	}

	private static boolean allTokensBlocked(GameBoard board, Player player) {
		//TODO true if all tokens of this player are blocked(have no legal move)
		if(player.getTokensPut() >= 9 && player.getTokensLeft() > 3) { 
			List<Coord> potentialpos = null;
			for(Coord coord : GameBoard.coordList){
				if(board.getPositions().get(coord).getPlayer() != player) 
					continue;
				potentialpos = board.generateMove2PotentialPos(coord);
				if(potentialpos.size() != 0)
					return false;
			}
			return true;
		}
		else 
			return false;
	}

	public static int phaseDropEvalFunction(GameBoard board, Player me, Player opponent) {
		//		int value = 18*formedMill(board, player, opponent)
		//				+ 26*diffNumMills(board, player) +
		//				+ 1*diffNumBlockedOpponentTokens(board, player)
		//				+ 9*diffNumTokens(board, player, opponent)
		//				+ 10*diffNum2TokenConf(board, player, opponent)
		//				+ 7*diffNum3TokenConfig(board, player);
		//				
		//		int value = 10*diffNum2TokenConf(board, me, opponent) 
		//				+ 26*diffNumMills(board, me, opponent)
		//				+ 1*diffNumBlockedOpponentTokens(board, me, opponent);
		int value = 10*num2TokenConf(board, me) + 26*numMills(me);
		return value;
	}

	public static int phaseMoveEvalFunction(GameBoard board, Player me, Player opponent) {
		//		int value = 14*formedMill(board, player, opponent)
		//				+ 43*diffNumMills(board, player) +
		//				+ 10*diffNumBlockedOpponentTokens(board, player)
		//				+ 11*diffNumTokens(board, player, opponent)
		//				+ 8*diffNumDoubleMills(board, player)
		//				+ 1086*winningConfig(board, player, opponent);
		int value =  30*diffNumMills(me, opponent)
				+ 1*diffNumBlockedOpponentTokens(board, me, opponent)
				+ 10*diffNumTokens(board, me, opponent)
				+ 1086*winningConfig(board, me, opponent);
		return value;
	}

	public static int phaseFlyEvalFunction(GameBoard board, Player me, Player opponent) {
		//		int value = 16*formedMill(board, player, opponent)
		//				+ 10*diffNum2TokenConf(board, player, opponent)
		//				+ 1*diffNum3TokenConfig(board, player)
		//				+ 1190*winningConfig(board, player, opponent);
		int value = 10*diffNum2TokenConf(board, me, opponent)
				+ 30*diffNumMills(me, opponent)
				+ 10*diffNumTokens(board, me, opponent)
				+ 1190*winningConfig(board, me, opponent);
		return value;
	}

	public static int evalFunction(GameController game, Player me) {
		GameBoard board = game.getBoard();
		//replace me with corresponding player of this game controller
		me = me.equals(game.player_1) ? game.player_1 : game.player_2;
		Player opponent = me.equals(game.player_1) ? game.player_2 : game.player_1;
		Player player_1 = game.player_1;
		// be careful of the new mill state
		int value = 0;
		String state = me.getState();
		if(state.equals(GameState.drop)) {
			value = EvalFunctions.phaseDropEvalFunction(board, me, opponent);
//			System.out.println(game.getBoardLayout());
//			System.out.println("value: " + value);
			value = EvalFunctions.phaseDropEvalFunction(board, me, opponent);
		} else if(state.equals(GameState.move_1) || state.equals(GameState.move_2) || state.equals(GameState.new_mill)) {
			value = EvalFunctions.phaseMoveEvalFunction(board, me, opponent);
		} else if (state.equals(GameState.fly_1) || state.equals(GameState.fly_2)) {
			value = EvalFunctions.phaseFlyEvalFunction(board, me, opponent);
		} else if (state.equals(GameState.player_1_win)) {
			value = me.equals(player_1) ? 2000 : -2000;
		} else if (state.equals(GameState.player_2_win)) {
			value = me.equals(player_1) ? -2000 : 2000;
		} else {
			System.err.println("wrong game state in eval function: " + me.getState());
			System.exit(1);
		}
		//		System.out.print(game.getBoardLayout());
		//		System.out.println("value: " + value);
		return value;
	}
}
