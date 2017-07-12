package unit_test;

import morris_java.Player;
import org.junit.Test;
import junit.framework.Assert;

public class PlayerTest {

	@Test
	public void testGetColor() {
		Player player = new Player("player1", "X");
		String desiredval = "X";
		Assert.assertEquals("test for getColor()", player.getColor(), desiredval);
	}

	@Test
	public void testSetColor() {
		Player player = new Player("player1", "X");
		player.setColor("O");
		String desiredval = "O";
		Assert.assertEquals("test for setColor()", player.getColor(), desiredval);
	}

	@Test
	public void testPlayer() {
		Player player = new Player("player1", "X");
		int desiredval = 0;
		Assert.assertEquals("test for constructor Plaer()", player.getTokensLeft(), desiredval);
	}

	@Test
	public void testGetTokensPut() {
		Player player = new Player("player1", "X");
		int desiredval = 0;
		Assert.assertEquals("test for getTokensPut()", player.getTokensPut(), desiredval);
	}

	@Test
	public void testGetTokensLeft() {
		Player player = new Player("player1", "X");
		int desiredval = 0;
		Assert.assertEquals("test for getTokensLeft()", player.getTokensLeft(), desiredval);
	}

	@Test
	public void testToString() {
		Player player = new Player("player1", "X");
		String desiredval = "player1 X";
		Assert.assertEquals("test for toString()", player.toString(), desiredval);
	}

}
