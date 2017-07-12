package unit_test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;

import junit.framework.Assert;
import morris_java.Coord;
import morris_java.GameConsoleGui;
import org.junit.Before;  

public class GameConsoleGuiTest {

	GameConsoleGui gcgui = null;

	@Before  
	public void setUp() throws Exception {  
		gcgui = new GameConsoleGui();  
	} 

	@Test
	public void testParseInputOfRegularInput() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method testNoParamMethod = gcgui.getClass().getDeclaredMethod("parseInput",String.class);
		testNoParamMethod.setAccessible(true);  

		Object result = testNoParamMethod.invoke(gcgui, "(3, 4)");
		Coord desiredval = new Coord(3, 4);
		Assert.assertEquals("test for parseInput()", result, desiredval);
	}

	@Test
	public void testParseInputOfInvalidInput() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method testNoParamMethod = gcgui.getClass().getDeclaredMethod("parseInput",String.class);
		testNoParamMethod.setAccessible(true);  

		Object result = testNoParamMethod.invoke(gcgui, "(3,s 3)");
		Coord desiredval = null;
		Assert.assertEquals("test for parseInput()", result, desiredval);
	}
	
	@Test
	public void testParseInputOfInputWithSpace() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method testNoParamMethod = gcgui.getClass().getDeclaredMethod("parseInput",String.class);
		testNoParamMethod.setAccessible(true);  

		Object result = testNoParamMethod.invoke(gcgui, "     (         3               ,                     3       )   ");
		Coord desiredval = new Coord(3, 3);
		Assert.assertEquals("test for parseInput()", result, desiredval);
	}

}
