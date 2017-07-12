package unit_test;

import org.junit.Test;

import junit.framework.Assert;
import morris_java.GameController;

public class GameControllerTest {

	@Test
	public void testGameController() {
		GameController gc = new GameController();
		String desiredval = "drop";
		Assert.assertEquals("test for GameController Constructor", gc.getState(), desiredval);
	}

	@Test
	public void testGetBoardLayout() {
		GameController gc = new GameController();
		String desiredval = 
	             String.format(
	           "  0 1 2 3 4 5 6\n" +
	           "0 *-----*-----*\n" + 
	           "1 | *---*---* |\n" +
	           "2 | | *-*-* | |\n" +
	           "3 *-*-*   *-*-*\n" +
	           "4 | | *-*-* | |\n" +
	           "5 | *---*---*-|\n" +
	           "6 *-----*-----*\n");
		Assert.assertEquals("test for getBoardLayout()", gc.getBoardLayout(), desiredval);
	}

}
