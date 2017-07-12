package morris_java;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class GuiPoint {

	private final BasicStroke GuiPoint_STROKE = new BasicStroke(6, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_ROUND);
	private final static Color black = Color.black;
	private final static Color white = Color.white;
	protected static int size ;
	private Ellipse2D GuiPoint_outer;
	private Ellipse2D GuiPoint_inner;
	private int position; 
	private boolean occupied = false;
	private int x;
	private int y;
	private int outer;
	private int inner;
	private Color color = white;
	
	public GuiPoint(int x, int y, int position){
		this.x = x;
		this.y = y;
		this.position = position;
		this.outer = size;
		this.inner = (int) (size/2);
		createGuiPoint();
	}
	
	private void createGuiPoint(){
		this.GuiPoint_outer = new Ellipse2D.Float(this.x, this.y, this.outer, this.outer);
		int offset = (int) (this.GuiPoint_outer.getWidth()/4);
		int x = (int) this.GuiPoint_outer.getCenterX() - offset;
		int y = (int) this.GuiPoint_outer.getCenterY() - offset;		
		this.GuiPoint_inner = new Ellipse2D.Float(x, y, this.inner, this.inner);
		
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
	
	public Color getColor(){
		return this.color;
	}
	
	public void setOccupied(boolean o){
		this.occupied = o;
	}
	
	public boolean getOccupied(){
		return this.occupied;
	}

	public void draw(Graphics2D g){
		g.setStroke(GuiPoint_STROKE);
		g.setColor(black);
		g.fill(this.GuiPoint_outer);
		g.setColor(this.color);
		g.fill(this.GuiPoint_inner);
	}
	
}
