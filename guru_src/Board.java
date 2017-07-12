


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")

public class Board extends JPanel implements MouseMotionListener, MouseListener{
	
	private final BasicStroke LINE_STROKE = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.CAP_BUTT);	
	
	private Point positionOfFirstMillPoint = new Point(50, 125); 
	private int outerSquareLength = 600; // default length of the outer most square, must be even
	
	private int osl;
	private int msl;
	private int isl;
	
	private int squareOffset1;
	private int squareOffset2;
	
	private Point omp;
	private Point mmp;
	private Point imp;
	
	protected MillPoint[] pointsOnBoard = new MillPoint[24];
	
	public Board(){
		initialize_variables();
		MillPoint.size = 30; // diameter of the circle (mill points)
		createMillPoints();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		pointsOnBoard[12].setOccupied(true);
	}
	
	private void initialize_variables(){
		
		osl = outerSquareLength; // outer square length	
		msl = (int) (0.6 * osl); // middle square length
		isl = (int) (0.4 * msl); // inner square length
		
		squareOffset1 = (int) ((osl-msl)/2);
		squareOffset2 = (int) ((msl-isl)/2);
		
		omp = positionOfFirstMillPoint;
		mmp = new Point(omp.x+squareOffset1, omp.y+squareOffset1);
		imp = new Point(mmp.x+squareOffset2, mmp.y+squareOffset2);
	}
	
	private void createBoardLines(Graphics2D gameBoard){
		/* Creating the Board based on the MillPoints
		 * and the length of the squares
		 */		
		gameBoard.setStroke(LINE_STROKE);

		// squares for the board
		gameBoard.setColor(Color.LIGHT_GRAY);
		gameBoard.fillRect(pointsOnBoard[0].getCenterX(), pointsOnBoard[0].getCenterY(), osl, osl);
		gameBoard.setColor(Color.BLACK);
		gameBoard.drawRect(pointsOnBoard[0].getCenterX(), pointsOnBoard[0].getCenterY(), osl, osl);
		gameBoard.drawRect(pointsOnBoard[8].getCenterX(), pointsOnBoard[8].getCenterY(), msl, msl);
		gameBoard.drawRect(pointsOnBoard[16].getCenterX(), pointsOnBoard[16].getCenterY(), isl, isl);
		
		// lines
		gameBoard.drawLine(pointsOnBoard[1].getCenterX(), pointsOnBoard[1].getCenterY(), pointsOnBoard[9].getCenterX(),pointsOnBoard[9].getCenterY());
		gameBoard.drawLine(pointsOnBoard[9].getCenterX(), pointsOnBoard[9].getCenterY(), pointsOnBoard[17].getCenterX(), pointsOnBoard[17].getCenterY());
		gameBoard.drawLine(pointsOnBoard[3].getCenterX(), pointsOnBoard[3].getCenterY(), pointsOnBoard[11].getCenterX(), pointsOnBoard[11].getCenterY());
		gameBoard.drawLine(pointsOnBoard[11].getCenterX(), pointsOnBoard[11].getCenterY(), pointsOnBoard[19].getCenterX(), pointsOnBoard[19].getCenterY());
		gameBoard.drawLine(pointsOnBoard[5].getCenterX(), pointsOnBoard[5].getCenterY(), pointsOnBoard[13].getCenterX(), pointsOnBoard[13].getCenterY());
		gameBoard.drawLine(pointsOnBoard[13].getCenterX(), pointsOnBoard[13].getCenterY(), pointsOnBoard[21].getCenterX(), pointsOnBoard[21].getCenterY());
		gameBoard.drawLine(pointsOnBoard[7].getCenterX(), pointsOnBoard[7].getCenterY(), pointsOnBoard[15].getCenterX(), pointsOnBoard[15].getCenterY());
		gameBoard.drawLine(pointsOnBoard[15].getCenterX(), pointsOnBoard[15].getCenterY(), pointsOnBoard[23].getCenterX(), pointsOnBoard[23].getCenterY());
	}
	
	private void createMillPoints(){
		pointsOnBoard[0] = new MillPoint(omp.x, omp.y, 1);
		pointsOnBoard[1] = new MillPoint(omp.x+osl/2, omp.y, 2);
		pointsOnBoard[2] = new MillPoint(omp.x+osl, omp.y, 3);
		pointsOnBoard[3] = new MillPoint(omp.x+osl, omp.y+osl/2, 4);
		pointsOnBoard[4] = new MillPoint(omp.x+osl, omp.y+osl, 5);
		pointsOnBoard[5] = new MillPoint(omp.x+osl/2, omp.y+osl, 6);
		pointsOnBoard[6] = new MillPoint(omp.x, omp.y+osl, 7);
		pointsOnBoard[7] = new MillPoint(omp.x, omp.y+osl/2, 8);
		pointsOnBoard[8] = new MillPoint(mmp.x, mmp.y, 9);
		pointsOnBoard[9] = new MillPoint(mmp.x+msl/2, mmp.y, 10);
		pointsOnBoard[10] = new MillPoint(mmp.x+msl, mmp.y, 11);
		pointsOnBoard[11] = new MillPoint(mmp.x+msl, mmp.y+msl/2, 12);
		pointsOnBoard[12] = new MillPoint(mmp.x+msl, mmp.y+msl, 13);
		pointsOnBoard[13] = new MillPoint(mmp.x+msl/2, mmp.y+msl, 14);
		pointsOnBoard[14] = new MillPoint(mmp.x, mmp.y+msl, 15);
		pointsOnBoard[15] = new MillPoint(mmp.x, mmp.y+msl/2, 16);
		pointsOnBoard[16] = new MillPoint(imp.x, imp.y, 17);
		pointsOnBoard[17] = new MillPoint(imp.x+isl/2, imp.y, 18);
		pointsOnBoard[18] = new MillPoint(imp.x+isl, imp.y, 19);
		pointsOnBoard[19] = new MillPoint(imp.x+isl, imp.y+isl/2, 20);
		pointsOnBoard[20] = new MillPoint(imp.x+isl, imp.y+isl, 21);
		pointsOnBoard[21] = new MillPoint(imp.x+isl/2, imp.y+isl, 22);
		pointsOnBoard[22] = new MillPoint(imp.x, imp.y+isl, 23);
		pointsOnBoard[23] = new MillPoint(imp.x, imp.y+isl/2, 24);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		createBoardLines(g2d);
		if(pointsOnBoard != null){
			for(MillPoint mp: pointsOnBoard){
				mp.draw(g2d);
			}
		}
	}
	
	private MillPoint checkPositionForMillPoint(int x, int y){
		for(MillPoint mp : pointsOnBoard){
			if (mp.getPointShape().contains(x, y) ) {
				return mp;
			}
		}
		return null;
	}
	
	private void setAllMillPointColorToWhite(){
		for(MillPoint mp : pointsOnBoard){
			mp.setColor(Color.WHITE);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MillPoint p = checkPositionForMillPoint(e.getX(), e.getY());
		if(p != null){
			if(!p.getOccupied()){
				p.setColor(Color.GREEN);
			}
			else{
				p.setColor(Color.RED);
			}
		}
		else{
			setAllMillPointColorToWhite();
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		MillPoint p = checkPositionForMillPoint(e.getX(), e.getY());
		if(p != null){
			System.out.println(p.toString());
		}
		
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
	
}