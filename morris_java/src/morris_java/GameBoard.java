package morris_java;

import java.util.*;
import java.io.Serializable;
@SuppressWarnings("serial")
public class GameBoard  implements Serializable{
	static List<Coord> coordList = Arrays.asList(new Coord(0, 0), new Coord(0, 3), new Coord(0, 6), new Coord(1, 1),
			new Coord(1, 3), new Coord(1, 5), new Coord(2, 2), new Coord(2, 3), new Coord(2, 4), new Coord(3, 0),
			new Coord(3, 1), new Coord(3, 2), new Coord(3, 4), new Coord(3, 5), new Coord(3, 6), new Coord(4, 2),
			new Coord(4, 3), new Coord(4, 4), new Coord(5, 1), new Coord(5, 3), new Coord(5, 5), new Coord(6, 0),
			new Coord(6, 3), new Coord(6, 6));
	HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
	private boolean unselect = false;

	public HashMap<Coord, Position> getPositions() {
		return positions;
	}

	public void setPositions(HashMap<Coord, Position> positions) {
		this.positions = positions;
	}

	// Constructor
	public GameBoard() {
		unselect = false;
		for (Coord coord : coordList) {
			positions.put(coord, new Position(coord, null));
		}
		init();
	}

	boolean isNeutralPosition(Position pos) {
		return positions.get(pos.getCoord()).getPlayer() == null;
	}

	boolean isSamePlayer(Position pos) {
		return pos.getPlayer().equals(positions.get(pos.getCoord()).getPlayer());
	}

	boolean isOpponentPos(Position pos) {
		return !isSamePlayer(pos) && !isNeutralPosition(pos);
	}

	boolean isAdjacentToSelected(Position lastpos, Position pos) {
		// check if is adjacent to last selected token
		Position corresPos = positions.get(pos.getCoord());
		Position corre_last_pos = positions.get(lastpos.getCoord());
		return corresPos == corre_last_pos.getUp() || corresPos == corre_last_pos.getDown()
				|| corresPos == corre_last_pos.getLeft() || corresPos == corre_last_pos.getRight();
	}

	boolean hasVacantNeibourPos(Position pos) {
		Position corresPos = positions.get(pos.getCoord());
		return corresPos.getUp() != null && corresPos.getUp().getPlayer() == null
				|| corresPos.getDown() != null && corresPos.getDown().getPlayer() == null
				|| corresPos.getLeft() != null && corresPos.getLeft().getPlayer() == null
				|| corresPos.getRight() != null && corresPos.getRight().getPlayer() == null;
	}

	boolean isLegal(String state, Player player, Position lastPos, Position pos) {

		if (!positions.containsKey(pos.getCoord())) {
			return false;
		}
		if (state.equals(GameState.drop))
			return isLegalDrop(pos);
		if (state.equals(GameState.move_1))
			return isLegalMove1(pos);
		if (state.equals(GameState.fly_1))
			return isLegalFly1(pos);
		if (state.equals(GameState.move_2)) {
			return isLegalMove2AllowUnselect(pos, lastPos);
		}
		if (state.equals(GameState.fly_2)) {
			return isLegalFly2AllowUnselect(pos);
		}
		if (state.equals(GameState.new_mill))
			return isLegalNewMill(pos);
		return false;
	}

	private boolean isLegalDrop(Position pos) {
		return isNeutralPosition(pos);
	}

	private boolean isLegalMove1(Position pos) {
		return isSamePlayer(pos) && hasVacantNeibourPos(pos);
	}

	private boolean isLegalFly1(Position pos) {
		return isSamePlayer(pos);
	}

	private boolean isLegalMove2(Position pos, Position lastPos) {
		return isNeutralPosition(pos) && isAdjacentToSelected(lastPos, pos);
	}

	private boolean isLegalMove2AllowUnselect(Position pos, Position lastPos) {
		if (isSamePlayer(pos) && hasVacantNeibourPos(pos)) {
			this.unselect = true;
			return true;
		} else
			return isLegalMove2(pos, lastPos);
	}

	private boolean isLegalFly2(Position pos) {
		return isNeutralPosition(pos);
	}

	private boolean isLegalFly2AllowUnselect(Position pos) {
		if (isSamePlayer(pos)) {
			this.unselect = true;
			return true;
		} else
			return isLegalFly2(pos);
	}

	private boolean isLegalNewMill(Position pos) {
		//TODO fix Issue #5
		if(!isOpponentPos(pos)) {
			return false;
		}
		Position corres_pos = this.positions.get(pos.getCoord());
		if(isMill(corres_pos) && isThereTokenNotInMill(corres_pos.getPlayer())) {
			return false;
		}
		return true;
	}
	
	private boolean isThereTokenNotInMill(Player player) {
		for(Coord coord: GameBoard.coordList) {
			Player corres_player = this.positions.get(coord).getPlayer();
			if(player.equals(corres_player)) {
				Position pos = new Position(coord, corres_player);
				if(!isMill(pos)) {
					return true;
				}
			}
		}
		return false;
	}

	void removeToken(Position pos) {
		positions.get(pos.getCoord()).getPlayer().removeToken();
		positions.get(pos.getCoord()).setPlayer(null);
	}

	void putToken(Position pos) {
		positions.get(pos.getCoord()).setPlayer(pos.getPlayer());
		pos.getPlayer().putToken();
	}

	boolean isMill(Position pos) {
		return isHorizontalMill(pos) || isVerticalMill(pos);
	}

	boolean isVerticalMill(Position pos) {
		boolean ret = false;
		Position corresPos = positions.get(pos.getCoord());
		Player player = pos.getPlayer();
		if (corresPos.getUp() == null)
			ret = player.equals(corresPos.getDown().getPlayer()) && player.equals(corresPos.getDown().getDown().getPlayer());
		else if (corresPos.getDown() == null)
			ret = player.equals(corresPos.getUp().getPlayer()) && player.equals(corresPos.getUp().getUp().getPlayer());
		else
			ret = player.equals(corresPos.getDown().getPlayer()) && player.equals(corresPos.getUp().getPlayer());

		return ret;
	}

	boolean isHorizontalMill(Position pos) {
		boolean ret = false;
		Position corresPos = positions.get(pos.getCoord());
		Player player = pos.getPlayer();
		if (corresPos.getLeft() == null)
			ret = player.equals(corresPos.getRight().getPlayer()) && player.equals(corresPos.getRight().getRight().getPlayer());

		else if (corresPos.getRight() == null)
			ret = player.equals(corresPos.getLeft().getPlayer()) && player.equals(corresPos.getLeft().getLeft().getPlayer());
		else
			ret = player.equals(corresPos.getRight().getPlayer()) && player.equals(corresPos.getLeft().getPlayer());

		return ret;
	}

	void change(String state, Position lastPos, Position pos) {
		if (state.equals(GameState.drop))
			putToken(pos);
		else if (state.equals(GameState.move_2) || state.equals(GameState.fly_2)) {
			if (!unselect) {
				removeToken(positions.get(lastPos.getCoord()));
				putToken(pos);
			}
		} else if (state.equals(GameState.new_mill))
			removeToken(pos);
	}

	public String toString() {
		List<String> nodeList = new ArrayList<String>();
		for (Coord coord : coordList) {
			if (positions.get(coord).getPlayer() == null)
				nodeList.add("*");
			else
				nodeList.add(positions.get(coord).getPlayer().getColor());
		}
		String board_repr = String.format("  0 1 2 3 4 5 6\n" + "0 %s-----%s-----%s\n" + "1 | %s---%s---%s |\n"
				+ "2 | | %s-%s-%s | |\n" + "3 %s-%s-%s   %s-%s-%s\n" + "4 | | %s-%s-%s | |\n" + "5 | %s---%s---%s-|\n"
				+ "6 %s-----%s-----%s\n", nodeList.toArray());
		return board_repr;
	}

	public HashMap<Coord, String> getBoardStatus() {
		HashMap<Coord, String> boradStatus = new HashMap<Coord, String>();
		for (Coord coord : coordList) {
			Position p = positions.get(coord);
			if (p.getPlayer() == null)
				boradStatus.put(coord, "*");
			else {
				boradStatus.put(coord, p.getPlayer().getColor());
			}
		}
		return boradStatus;
	}

	private void init() {
		Position temp;
		temp = this.positions.get(new Coord(0, 0));
		temp.setDown(this.positions.get(new Coord(3, 0)));
		temp.setRight(this.positions.get(new Coord(0, 3)));

		temp = this.positions.get(new Coord(0, 3));
		temp.setDown(this.positions.get(new Coord(1, 3)));
		temp.setLeft(this.positions.get(new Coord(0, 0)));
		temp.setRight(this.positions.get(new Coord(0, 6)));

		temp = this.positions.get(new Coord(0, 6));
		temp.setDown(this.positions.get(new Coord(3, 6)));
		temp.setLeft(this.positions.get(new Coord(0, 3)));

		temp = this.positions.get(new Coord(1, 1));
		temp.setDown(this.positions.get(new Coord(3, 1)));
		temp.setRight(this.positions.get(new Coord(1, 3)));

		temp = this.positions.get(new Coord(1, 3));
		temp.setUp(this.positions.get(new Coord(0, 3)));
		temp.setDown(this.positions.get(new Coord(2, 3)));
		temp.setLeft(this.positions.get(new Coord(1, 1)));
		temp.setRight(this.positions.get(new Coord(1, 5)));

		temp = this.positions.get(new Coord(1, 5));
		temp.setDown(this.positions.get(new Coord(3, 5)));
		temp.setLeft(this.positions.get(new Coord(1, 3)));

		temp = this.positions.get(new Coord(2, 2));
		temp.setDown(this.positions.get(new Coord(3, 2)));
		temp.setRight(this.positions.get(new Coord(2, 3)));

		temp = this.positions.get(new Coord(2, 3));
		temp.setUp(this.positions.get(new Coord(1, 3)));
		temp.setLeft(this.positions.get(new Coord(2, 2)));
		temp.setRight(this.positions.get(new Coord(2, 4)));

		temp = this.positions.get(new Coord(2, 4));
		temp.setDown(this.positions.get(new Coord(3, 4)));
		temp.setLeft(this.positions.get(new Coord(2, 3)));

		temp = this.positions.get(new Coord(3, 0));
		temp.setUp(this.positions.get(new Coord(0, 0)));
		temp.setDown(this.positions.get(new Coord(6, 0)));
		temp.setRight(this.positions.get(new Coord(3, 1)));

		temp = this.positions.get(new Coord(3, 1));
		temp.setUp(this.positions.get(new Coord(1, 1)));
		temp.setDown(this.positions.get(new Coord(5, 1)));
		temp.setLeft(this.positions.get(new Coord(3, 0)));
		temp.setRight(this.positions.get(new Coord(3, 2)));

		temp = this.positions.get(new Coord(3, 2));
		temp.setUp(this.positions.get(new Coord(2, 2)));
		temp.setDown(this.positions.get(new Coord(4, 2)));
		temp.setLeft(this.positions.get(new Coord(3, 1)));

		temp = this.positions.get(new Coord(3, 4));
		temp.setUp(this.positions.get(new Coord(2, 4)));
		temp.setDown(this.positions.get(new Coord(4, 4)));
		temp.setRight(this.positions.get(new Coord(3, 5)));

		temp = this.positions.get(new Coord(3, 5));
		temp.setUp(this.positions.get(new Coord(1, 5)));
		temp.setDown(this.positions.get(new Coord(5, 5)));
		temp.setLeft(this.positions.get(new Coord(3, 4)));
		temp.setRight(this.positions.get(new Coord(3, 6)));

		temp = this.positions.get(new Coord(3, 6));
		temp.setUp(this.positions.get(new Coord(0, 6)));
		temp.setDown(this.positions.get(new Coord(6, 6)));
		temp.setLeft(this.positions.get(new Coord(3, 5)));

		temp = this.positions.get(new Coord(4, 2));
		temp.setUp(this.positions.get(new Coord(3, 2)));
		temp.setRight(this.positions.get(new Coord(4, 3)));

		temp = this.positions.get(new Coord(4, 3));
		temp.setDown(this.positions.get(new Coord(5, 3)));
		temp.setLeft(this.positions.get(new Coord(4, 2)));
		temp.setRight(this.positions.get(new Coord(4, 4)));

		temp = this.positions.get(new Coord(4, 4));
		temp.setUp(this.positions.get(new Coord(3, 4)));
		temp.setLeft(this.positions.get(new Coord(4, 3)));

		temp = this.positions.get(new Coord(5, 1));
		temp.setUp(this.positions.get(new Coord(3, 1)));
		temp.setRight(this.positions.get(new Coord(5, 3)));

		temp = this.positions.get(new Coord(5, 3));
		temp.setUp(this.positions.get(new Coord(4, 3)));
		temp.setDown(this.positions.get(new Coord(6, 3)));
		temp.setLeft(this.positions.get(new Coord(5, 1)));
		temp.setRight(this.positions.get(new Coord(5, 5)));

		temp = this.positions.get(new Coord(5, 5));
		temp.setUp(this.positions.get(new Coord(3, 5)));
		temp.setLeft(this.positions.get(new Coord(5, 3)));

		temp = this.positions.get(new Coord(6, 0));
		temp.setUp(this.positions.get(new Coord(3, 0)));
		temp.setRight(this.positions.get(new Coord(6, 3)));

		temp = this.positions.get(new Coord(6, 3));
		temp.setUp(this.positions.get(new Coord(5, 3)));
		temp.setLeft(this.positions.get(new Coord(6, 0)));
		temp.setRight(this.positions.get(new Coord(6, 6)));

		temp = this.positions.get(new Coord(6, 6));
		temp.setUp(this.positions.get(new Coord(3, 6)));
		temp.setLeft(this.positions.get(new Coord(6, 3)));
	}

	public List<Coord> generateDropPotentialPos() {
		// any position that is empty
		List<Coord> potentialPos = new ArrayList<Coord>();
		for (Position pos : this.positions.values()) {
			if (this.isLegalDrop(pos)) {
				potentialPos.add(pos.getCoord());
			}
		}
		return potentialPos;
	}

	public List<Coord> generateNewMillPotentialPos(Player player) {
		// any position that is occupied by o token
		List<Coord> potentialPos = new ArrayList<Coord>();
		for (Coord coord : GameBoard.coordList) {
			// it is necessary to construct a new Position object using player
			// because we need to compare whether is opponent player
			if (this.isLegalNewMill(new Position(coord, player))) {
				potentialPos.add(coord);
			}
		}
		return potentialPos;
	}

	public List<Coord> generateMove1PotentialPos(Player player) {
		// any position that is 1. occupied by own token 2. have adjacent empty
		// positions
		List<Coord> potentialPos = new ArrayList<Coord>();
		for (Coord coord : GameBoard.coordList) {
			if (this.isLegalMove1(new Position(coord, player))) {
				potentialPos.add(coord);
			}
		}
		return potentialPos;
	}

	public List<Coord> generateFly1PotentialPos(Player player) {
		// any position that is occupied by own token
		List<Coord> potentialPos = new ArrayList<Coord>();
		for (Coord coord : GameBoard.coordList) {
			if (this.isLegalFly1(new Position(coord, player))) {
				potentialPos.add(coord);
			}
		}
		return potentialPos;
	}

	public List<Coord> generateMove2PotentialPos(Coord coord) {
		List<Coord> potentialPos = new ArrayList<Coord>();
		Position corre_last_pos = positions.get(coord);
		if (corre_last_pos.getDown() != null && (corre_last_pos.getDown()).getPlayer() == null) {
			potentialPos.add(corre_last_pos.getDown().getCoord());
		}
		if (corre_last_pos.getUp() != null && (corre_last_pos.getUp()).getPlayer() == null) {
			potentialPos.add(corre_last_pos.getUp().getCoord());
		}
		if (corre_last_pos.getLeft() != null && (corre_last_pos.getLeft()).getPlayer() == null) {
			potentialPos.add(corre_last_pos.getLeft().getCoord());
		}
		if (corre_last_pos.getRight() != null && (corre_last_pos.getRight()).getPlayer() == null) {
			potentialPos.add(corre_last_pos.getRight().getCoord());
		}
		return potentialPos;
	}

	public List<Coord> generatePotentialPos(String gameState, Player player, Coord lastCoord) {
		List<Coord> list = null;
		if(gameState.equals(GameState.drop)) {
			list = this.generateDropPotentialPos();
		} else if (gameState.equals(GameState.move_1)) {
			list = this.generateMove1PotentialPos(player);
		} else if (gameState.equals(GameState.move_2)) {
			list = this.generateMove2PotentialPos(lastCoord);
		} else if (gameState.equals(GameState.fly_1)) {
			list = this.generateFly1PotentialPos(player);
		} else if (gameState.equals(GameState.fly_2)) {
			list = this.generateFly2PotentialPos();
		} else if (gameState.equals(GameState.new_mill)) {
			list = this.generateNewMillPotentialPos(player);
		} else {
			throw new RuntimeException("no potential pos for game state: " + gameState);
		}
		return list;
	}
	
	public List<Coord> generateFly2PotentialPos() {
		List<Coord> potentialPos = new ArrayList<Coord>();
		for (Position pos : this.positions.values()) {
			if (this.isLegalFly2(pos)) {
				potentialPos.add(pos.getCoord());
			}
		}
		return potentialPos;
	}

	public boolean isUnselect() {
		return unselect;
	}

	public void setUnselect() {
		unselect = false;
	}
}
