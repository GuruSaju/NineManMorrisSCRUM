
class Position {
	private String coord;
	private Player player;
	private Position up;
	private Position down;
	private Position left;
	private Position right;
	
	Position(String pos, Player player) {
		this.coord = pos;
		this.player = player;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
	}
	
	public void setUp(Position position)
	{
		this.up = position;
	}
	
	public void setDown(Position position)
	{
		this.down = position;
	}
	
	public void setRight(Position position)
	{
		this.right = position;
	}
	
	public void setLeft(Position position)
	{
		this.left = position;
	}
}
