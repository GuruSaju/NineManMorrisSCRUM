package Test_GUI;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import morris_java.GuiBoard;

public class GuiBoardTest {

	@Test
	public void testGuiBoard() {
		GuiBoard game = new GuiBoard();
		double desired = 25;
		assertEquals(game.getClickPoint(), desired, 0.0001);
	}

	@Test
	public void testGetCoordByGuipointIndx() {
		GuiBoard game = new GuiBoard();
		double desiredX = 0;
		double desiredY = 0;
		int cp = 1;
		
		//System.out.println("Clickpoint(1) X: " + game.getCoordByGuipointIndx(cp).getX() ); used for testing
		//System.out.println("Clickpoint(1) Y: " + game.getCoordByGuipointIndx(cp).getX() ); used for testing
		
		
		assertEquals(game.getCoordByGuipointIndx(cp).getX(), desiredX, 0.0001);
		assertEquals(game.getCoordByGuipointIndx(cp).getY(), desiredY, 0.0001);
	}

	@Test
	public void testPaintComponentGraphics() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetUnOccupied() {
		GuiBoard game = new GuiBoard();
		boolean status = false;
		int cp = 1;
		
		game.setUnOccupied(cp);
		
		assertEquals(game.getOccupiedStatus(cp), status);
	}

	@Test
	public void testSetOccupied() {
		GuiBoard game = new GuiBoard();
		boolean status = true;
		int cp = 1;
		
		game.setOccupied(cp);
		
		assertEquals(game.getOccupiedStatus(cp), status);
	}

	@Test
	public void testGetClickPoint() {
		GuiBoard game = new GuiBoard();
		double desired = 25;
		assertEquals(game.getClickPoint(), desired, 0.0001);
	}

	@Test
	public void testSetCLickPoint() {
		GuiBoard game = new GuiBoard();
		double desired = 25;
		assertEquals(game.getClickPoint(), desired, 0.0001);
	}


	@Test
	public void testMouseClicked() {
		
	}


	@Test
	public void testUpdateGui() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTokenOnGui() {
		fail("Not yet implemented");
	}

}
