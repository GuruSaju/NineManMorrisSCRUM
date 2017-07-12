package morris_java;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class GuiBoard extends JPanel implements MouseListener{

private final BasicStroke LINE_STROKE = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.CAP_BUTT);	
	
	private Point positionOfFirstGuiPoint = new Point(330, 70); 
	private int BoardLength = 500; // default length of the outer most square, must be even
	
	private int outerSquareLength;
	private int midSquareLength;
	private int inSquareLength;
	
	private int Offset1;
	private int Offset2;
	
	private Point outerSquareGuiPoints;
	private Point midSquareGuiPoints;
	private Point inSquareGuiPoints;
	
	final private Coord[] coordinate = {null, new Coord(0,0), new Coord(0,3), new Coord(0,6),
            new Coord(3,6), new Coord(6,6), new Coord(6,3),
            new Coord(6,0), new Coord(3,0), new Coord(1,1),
            new Coord(1,3), new Coord(1,5), new Coord(3,5),
            new Coord(5,5), new Coord(5,3), new Coord(5,1),
            new Coord(3,1), new Coord(2,2), new Coord(2,3),
            new Coord(2,4), new Coord(3,4), new Coord(4,4),
            new Coord(4,3), new Coord(4,2), new Coord(3,2)};
	
	private int clickPoint = 25;
	
	protected GuiPoint[] positions = new GuiPoint[24];
	
	public GuiBoard(){
		initialize_variables();
		GuiPoint.size = 50; // diameter of the circle (mill points)
		createGuiPoints();
	
		this.addMouseListener(this);
		this.clickPoint = 25;
	}
	
	public Coord getCoordByGuipointIndx(int i){
		return coordinate[i];
	}
	
	private void initialize_variables(){
		
		outerSquareLength = BoardLength;
		midSquareLength = (int) (0.65 * outerSquareLength);
		inSquareLength = (int) (0.49 * midSquareLength); 
		
		Offset1 = (int) ((outerSquareLength-midSquareLength)/2);
		Offset2 = (int) ((midSquareLength-inSquareLength)/2);
		
		outerSquareGuiPoints = positionOfFirstGuiPoint;
		midSquareGuiPoints = new Point(outerSquareGuiPoints.x+Offset1, outerSquareGuiPoints.y+Offset1);
		inSquareGuiPoints = new Point(midSquareGuiPoints.x+Offset2, midSquareGuiPoints.y+Offset2);
	}
	
	private void createBoardLines(Graphics2D gameBoard){
		/* Creating the Board based on the GuiPoints
		 * and the length of the squares
		 */		
		gameBoard.setStroke(LINE_STROKE);

		// squares for the board
		gameBoard.setColor(Color.WHITE);
		 BufferedImage imageA,imageC;
			try {
				imageA = ImageIO.read(new File("nine_men_s_morris.jpg"));
			
				imageC = ImageIO.read(new File("egypt"));
				
				gameBoard.drawImage(imageC, 0, 0, 1200, 700,null);
				
			    gameBoard.drawImage(imageA, 290, 20, 630, 630,null);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void createGuiPoints(){
		positions[0] = new GuiPoint(outerSquareGuiPoints.x, outerSquareGuiPoints.y-10, 1);
		positions[1] = new GuiPoint(outerSquareGuiPoints.x+outerSquareLength/2, outerSquareGuiPoints.y-10, 2);
		positions[2] = new GuiPoint(outerSquareGuiPoints.x+outerSquareLength, outerSquareGuiPoints.y-10, 3);
		positions[3] = new GuiPoint(outerSquareGuiPoints.x+outerSquareLength, outerSquareGuiPoints.y+outerSquareLength/2-10, 4);
		positions[4] = new GuiPoint(outerSquareGuiPoints.x+outerSquareLength, outerSquareGuiPoints.y+outerSquareLength-10, 5);
		positions[5] = new GuiPoint(outerSquareGuiPoints.x+outerSquareLength/2, outerSquareGuiPoints.y+outerSquareLength-10, 6);
		positions[6] = new GuiPoint(outerSquareGuiPoints.x, outerSquareGuiPoints.y+outerSquareLength-10, 7);
		positions[7] = new GuiPoint(outerSquareGuiPoints.x, outerSquareGuiPoints.y+outerSquareLength/2-10, 8);
		positions[8] = new GuiPoint(midSquareGuiPoints.x, midSquareGuiPoints.y-10, 9);
		positions[9] = new GuiPoint(midSquareGuiPoints.x+midSquareLength/2, midSquareGuiPoints.y-10, 10);
		positions[10] = new GuiPoint(midSquareGuiPoints.x+midSquareLength+10, midSquareGuiPoints.y-10, 11);
		positions[11] = new GuiPoint(midSquareGuiPoints.x+midSquareLength+10, midSquareGuiPoints.y+midSquareLength/2-10, 12);
		positions[12] = new GuiPoint(midSquareGuiPoints.x+midSquareLength+5, midSquareGuiPoints.y+midSquareLength, 13);
		positions[13] = new GuiPoint(midSquareGuiPoints.x+midSquareLength/2, midSquareGuiPoints.y+midSquareLength, 14);
		positions[14] = new GuiPoint(midSquareGuiPoints.x-5, midSquareGuiPoints.y+midSquareLength-6, 15);
		positions[15] = new GuiPoint(midSquareGuiPoints.x-5, midSquareGuiPoints.y+midSquareLength/2-10, 16);
		positions[16] = new GuiPoint(inSquareGuiPoints.x, inSquareGuiPoints.y-10, 17);
		positions[17] = new GuiPoint(inSquareGuiPoints.x+inSquareLength/2, inSquareGuiPoints.y-10, 18);
		positions[18] = new GuiPoint(inSquareGuiPoints.x+inSquareLength, inSquareGuiPoints.y-10, 19);
		positions[19] = new GuiPoint(inSquareGuiPoints.x+inSquareLength, inSquareGuiPoints.y+inSquareLength/2-10, 20);
		positions[20] = new GuiPoint(inSquareGuiPoints.x+inSquareLength, inSquareGuiPoints.y+inSquareLength-10, 21);
		positions[21] = new GuiPoint(inSquareGuiPoints.x+inSquareLength/2, inSquareGuiPoints.y+inSquareLength-10, 22);
		positions[22] = new GuiPoint(inSquareGuiPoints.x, inSquareGuiPoints.y+inSquareLength-10, 23);
		positions[23] = new GuiPoint(inSquareGuiPoints.x, inSquareGuiPoints.y+inSquareLength/2-10, 24);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		createBoardLines(g2d);
		if(positions != null){
			for(GuiPoint mp: positions){
				mp.draw(g2d);
			}
		}
	}
	
	private GuiPoint checkPositionForGuiPoint(int x, int y){
		for(GuiPoint mp : positions){
			if (mp.getPointShape().contains(x, y) ) {
				return mp;
			}
		}
		return null;
	}

	
	public void setUnOccupied(int index) {
		positions[index].setOccupied(false);
	}
	
	public void setOccupied(int index) {
		positions[index].setOccupied(true);
	}
	
	public boolean getOccupiedStatus(int i){
		return positions[i].getOccupied();
	}
	
	public int getClickPoint() {
		return clickPoint;
	}
	
	public void setCLickPoint() {
		clickPoint = 25;
	}
	
	public void setGuiPoint(int index, Color c){

		positions[index].setColor(c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GuiPoint p = checkPositionForGuiPoint(e.getX(), e.getY());
		if(p != null){
			System.out.println(p.toString());
			clickPoint = p.getPosition();
		}
		else
			clickPoint = 25;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateGui(HashMap<Coord, String> boardStatus) {
		// TODO Auto-generated method stub
		for(int i = 1; i < 25; i++) {
			String status = boardStatus.get(getCoordByGuipointIndx(i));
			if (status.equals("X")) {
				this.setOccupied((i - 1));
				this.setGuiPoint((i - 1), Color.BLUE);
			}
			else if (status.equals("O")){
				this.setOccupied((i - 1));
				this.setGuiPoint((i - 1), new Color(255, 0, 255));
			}		
			else {
				this.setUnOccupied((i - 1));
				this.setGuiPoint((i - 1), Color.WHITE);
			}
		}
		this.repaint();
	}


	public void selectTokenOnGui(Coord coord, HashMap<Coord, String> boardStatus) {
		for(int i = 1; i < 25; i++) {
			String status = boardStatus.get(getCoordByGuipointIndx(i));
			if(getCoordByGuipointIndx(i).equals(coord)) {
				this.setGuiPoint((i - 1), Color.RED);
				continue;
			}
			else if (status.equals("X")) {
				this.setOccupied((i - 1));
				this.setGuiPoint((i - 1), Color.BLUE);
			}
			else if (status.equals("O") ){
				this.setOccupied((i - 1));
				this.setGuiPoint((i - 1), Color.MAGENTA);
			}		
			else {
				this.setUnOccupied((i - 1));
				this.setGuiPoint((i - 1), Color.WHITE);
			}
		}
		this.repaint();
	}

	public void highlightPotential(List<Coord> potentialpos) {
		// TODO Auto-generated method stub
		for(Coord coord : potentialpos) {
			for(int i = 1; i < 25; i++) {
				if(coordinate[i].equals(coord)) {
					this.setGuiPoint((i - 1), Color.ORANGE);
				}
			}
		}
		this.repaint();
	}

}