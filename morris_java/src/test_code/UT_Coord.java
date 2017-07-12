/**
 * 
 */
package test_code;

import org.junit.Test;

import morris_java.Coord;

/**
 * @author shuaipeng
 *
 */
public class UT_Coord {

	/**
	 * Test method for {@link morris_java.Coord#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		int x = 1, y = 2;
		Coord coord1 = new Coord(x, y);
		org.junit.Assert.assertEquals(coord1.hashCode(), 100*x + y);
	}

	/**
	 * Test method for {@link morris_java.Coord#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Coord coord1 = new Coord(1, 1);
		Coord coord2 = new Coord(2, 2);
		org.junit.Assert.assertNotEquals(coord1, coord2);
		org.junit.Assert.assertEquals(coord1, coord1);
		org.junit.Assert.assertEquals(coord1, new Coord(1, 1));
	}

}
