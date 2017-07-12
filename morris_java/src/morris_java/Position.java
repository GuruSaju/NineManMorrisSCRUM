
package morris_java;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position  implements Serializable{
	private Coord coord;


	private Player player;
	private Position up;
	private Position down;
	private Position left;
	private Position right;
	public Position getUp() {
		return up;
	}

	public Position getDown() {
		return down;
	}

	public Position getLeft() {
		return left;
	}

	public Position getRight() {
		return right;
	}
	public void setUp(Position up) {
		this.up = up;
	}

	public void setDown(Position down) {
		this.down = down;
	}

	public void setLeft(Position left) {
		this.left = left;
	}

	public void setRight(Position right) {
		this.right = right;
	}


	public Position(Coord coord, Player player) {
		this.coord = coord;
		this.player = player;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


}
