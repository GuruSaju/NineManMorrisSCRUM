


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class MillPoint {
	
	public enum Player {ONE, TWO, COMPUTER, NONE};
	private final BasicStroke MILLPOINT_STROKE = new BasicStroke(4, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_ROUND);
	private final static Color black = Color.black;
	private final static Color white = Color.white;
	protected static int size = 30; // default size of the outer circle of  mill point
	
	private Ellipse2D millPoint_outer;
	private Ellipse2D millPoint_inner;
	
	/* 
	 * Each point on the board for a mill is given a 
	 * position number. Therefore, there are 24 positions 
	 * on the board.
	 */
	private int position; 
	
	/*
	 * This indicates whether the mill point is occupied
	 * with another piece or not.
	 */
	private boolean occupied = false;
	
	/*
	 * Indicates the player whose piece is 
	 * occupying the mill point. By default it 
	 * is set to NONE when this.occupied = false.
	 */
	private Player player = Player.NONE;
	
	private int x;
	private int y;
	private int size_outer;
	private int size_inner;
	private Color millColor = white;
	
	public MillPoint(int x, int y, int position){
		this.x = x;
		this.y = y;
		this.position = position;
		this.size_outer = size;
		this.size_inner = (int) (size/2);
		createMillPoint();
	}
	
	private void createMillPoint(){
		int outerX = this.x;
		int outerY = this.y;
		this.millPoint_outer = new Ellipse2D.Float(outerX, outerY, this.size_outer, this.size_outer);
		
		int offset = (int) (this.millPoint_outer.getWidth()/4);
		int innerX = (int) this.millPoint_outer.getCenterX() - offset;
		int innerY = (int) this.millPoint_outer.getCenterY() - offset;		
		this.millPoint_inner = new Ellipse2D.Float(innerX, innerY, this.size_inner, this.size_inner);
		
	}
	public int getPosition(){
		return this.position;
	}
	
	public Player getOccupyingPlayer(){
		return this.player;
	}
	
	public void setOccupyingPlayer(Player p){
		this.player = p ;
	}
	
	public Ellipse2D getPointShape(){
		return this.millPoint_outer;
	}
	
	public int getCenterX(){
		return (int) this.millPoint_outer.getCenterX();
	}
	
	public int getCenterY(){
		return (int) this.millPoint_outer.getCenterY();
	}
	
	public void setColor(Color c ){
		this.millColor = c;
	}
	
	public void setOccupied(boolean o){
		this.occupied = o;
		if(! this.occupied){
			this.player = Player.NONE;
		}
	}
	
	public boolean getOccupied(){
		return this.occupied;
	}
	
	public String toString(){
		return "Millpoint position: " + this.position +"\n" +
				"Occupied: " + this.occupied;
	}
	
	public void draw(Graphics2D g){
		g.setStroke(MILLPOINT_STROKE);
		g.setColor(black);
		g.fill(this.millPoint_outer);
		g.setColor(this.millColor);
		g.fill(this.millPoint_inner);
	}
	
}
