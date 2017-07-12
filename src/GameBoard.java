import java.util.HashMap;

class GameBoard {

	private HashMap<String, Position> positions = new HashMap<String, Position>();

	public boolean isLegal(String state, Player current_player, Position last_pos, Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	public void change(String state, Position last_pos, Position pos) {
		// TODO Auto-generated method stub

	}

	public boolean isMill(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	public void init() {
		Position temp;
		temp = this.positions.get("(0, 0)");
		temp.setDown(this.positions.get("(3, 0)"));
		temp.setRight(this.positions.get("(0, 3)"));

		temp = this.positions.get("(0, 3)");
		temp.setDown(this.positions.get("(1, 3)"));
		temp.setLeft(this.positions.get("(0, 0)"));
		temp.setRight(this.positions.get("(0, 6)"));

		temp = this.positions.get("(0, 6)");
		temp.setDown(this.positions.get("(3, 6)"));
		temp.setLeft(this.positions.get("(0, 3)"));

		temp = this.positions.get("(1, 1)");
		temp.setDown(this.positions.get("(3, 1)"));
		temp.setRight(this.positions.get("(1, 3)"));

		temp = this.positions.get("(1, 3)");
		temp.setUp(this.positions.get("(0, 3)"));
		temp.setDown(this.positions.get("(2, 3)"));
		temp.setLeft(this.positions.get("(1, 1)"));
		temp.setRight(this.positions.get("(1, 5)"));

		temp = this.positions.get("(1, 5)");
		temp.setDown(this.positions.get("(3, 5)"));
		temp.setLeft(this.positions.get("(1, 3)"));

		temp = this.positions.get("(2, 2)");
		temp.setDown(this.positions.get("(3, 2)"));
		temp.setRight(this.positions.get("(2, 3)"));

		temp = this.positions.get("(2, 3)");
		temp.setUp(this.positions.get("(1, 3)"));
		temp.setLeft(this.positions.get("(2, 2)"));
		temp.setRight(this.positions.get("(2, 4)"));
		
		temp = this.positions.get("(2, 4)");
		temp.setDown(this.positions.get("(3, 4)"));
		temp.setLeft(this.positions.get("(2, 3)"));

		temp = this.positions.get("(3, 0)");
		temp.setUp(this.positions.get("(0, 0)"));
		temp.setDown(this.positions.get("(6, 0)"));
		temp.setRight(this.positions.get("(3, 1)"));

		temp = this.positions.get("(3, 1)");
		temp.setUp(this.positions.get("(1, 1)"));
		temp.setDown(this.positions.get("(5, 1)"));
		temp.setLeft(this.positions.get("(3, 0)"));
		temp.setRight(this.positions.get("(3, 2)"));

		temp = this.positions.get("(3, 2)");
		temp.setUp(this.positions.get("(2, 2)"));
		temp.setDown(this.positions.get("(4, 2)"));
		temp.setLeft(this.positions.get("(3, 1)"));

		temp = this.positions.get("(3, 4)");
		temp.setUp(this.positions.get("(2, 4)"));
		temp.setDown(this.positions.get("(4, 4)"));
		temp.setRight(this.positions.get("(3, 5)"));

		temp = this.positions.get("(3, 5)");
		temp.setUp(this.positions.get("(1, 5)"));
		temp.setDown(this.positions.get("(5, 5)"));
		temp.setLeft(this.positions.get("(3, 4)"));
		temp.setRight(this.positions.get("(3, 6)"));

		temp = this.positions.get("(3, 6)");
		temp.setUp(this.positions.get("(0, 6)"));
		temp.setDown(this.positions.get("(6, 6)"));
		temp.setLeft(this.positions.get("(3, 5)"));

		temp = this.positions.get("(4, 2)");
		temp.setUp(this.positions.get("(3, 2)"));
		temp.setRight(this.positions.get("(4, 3)"));

		temp = this.positions.get("(4, 3)");
		temp.setDown(this.positions.get("(5, 3)"));
		temp.setLeft(this.positions.get("(4, 2)"));
		temp.setRight(this.positions.get("(4, 4)"));

		temp = this.positions.get("(4, 4)");
		temp.setUp(this.positions.get("(3, 4)"));
		temp.setLeft(this.positions.get("(4, 3)"));
		
		temp = this.positions.get("(5, 1)");
		temp.setUp(this.positions.get("(3, 1)"));
		temp.setRight(this.positions.get("(5, 3)"));

		temp = this.positions.get("(5, 3)");
		temp.setUp(this.positions.get("(4, 3)"));
		temp.setDown(this.positions.get("(6, 3)"));
		temp.setLeft(this.positions.get("(5, 1)"));
		temp.setRight(this.positions.get("(5, 5)"));

		temp = this.positions.get("(5, 5)");
		temp.setUp(this.positions.get("(3, 5)"));
		temp.setLeft(this.positions.get("(5, 3)"));
		
		temp = this.positions.get("(6, 0)");
		temp.setUp(this.positions.get("(3, 0)"));
		temp.setRight(this.positions.get("(6, 3)"));

		temp = this.positions.get("(6, 3)");
		temp.setUp(this.positions.get("(5, 3)"));
		temp.setLeft(this.positions.get("(0, 0)"));
		temp.setRight(this.positions.get("(6, 6)"));

		temp = this.positions.get("(6, 6)");
		temp.setUp(this.positions.get("(3, 6)"));
		temp.setLeft(this.positions.get("(6, 3)"));
	}

}
