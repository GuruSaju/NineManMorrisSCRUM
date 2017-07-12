package morris_java;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class GuiPoint {
	
	public enum Player {ONE, TWO, COMPUTER, NONE};
	private final BasicStroke GuiPoint_STROKE = new BasicStroke(4, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_ROUND);
	private final static Color black = Color.black;
	private final static Color white = Color.white;
	protected static int size = 30; // default size of the outer circle of  mill point
	
	private Ellipse2D GuiPoint_outer;
	private Ellipse2D GuiPoint_inner;
	
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

	
	private int x;
	private int y;
	private int size_outer;
	private int size_inner;
	private Color color = white;
	
	public GuiPoint(int x, int y, int position){
		this.x = x;
		this.y = y;
		this.position = position;
		this.size_outer = size;
		this.size_inner = (int) (size/2);
		createGuiPoint();
	}
	
	private void createGuiPoint(){
		int outerX = this.x;
		int outerY = this.y;
		this.GuiPoint_outer = new Ellipse2D.Float(outerX, outerY, this.size_outer, this.size_outer);
		
		int offset = (int) (this.GuiPoint_outer.getWidth()/4);
		int innerX = (int) this.GuiPoint_outer.getCenterX() - offset;
		int innerY = (int) this.GuiPoint_outer.getCenterY() - offset;		
		this.GuiPoint_inner = new Ellipse2D.Float(innerX, innerY, this.size_inner, this.size_inner);
		
	}
	public int getPosition(){
		return this.position;
	}
	
	public Ellipse2D getPointShape(){
		return this.GuiPoint_outer;
	}
	
	public int getCenterX(){
		return (int) this.GuiPoint_outer.getCenterX();
	}
	
	public int getCenterY(){
		return (int) this.GuiPoint_outer.getCenterY();
	}
	
	public void setColor(Color c ){
		this.color = c;
	}
	
	public void setOccupied(boolean o){
		this.occupied = o;
	}
	
	public boolean getOccupied(){
		return this.occupied;
	}
	
	public String toString(){
		return "GuiPoint position: " + this.position +"\n" +
				"Occupied: " + this.occupied;
	}
	
	public void draw(Graphics2D g){
		g.setStroke(GuiPoint_STROKE);
		g.setColor(black);
		g.fill(this.GuiPoint_outer);
		g.setColor(this.color);
		g.fill(this.GuiPoint_inner);
	}
	
}
