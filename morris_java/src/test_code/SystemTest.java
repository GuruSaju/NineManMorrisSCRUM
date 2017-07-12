/**
 * 
 */
package test_code;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import morris_java.GameConsoleGui;
import morris_java.PlayerType;

/**
 * @author speng
 *
 */
public class SystemTest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		SystemTest sysTest = new SystemTest();
		sysTest.regressionTest();
	}
	
	public void regressionTest() throws FileNotFoundException {
		String testsPath = "tests";
		String inFileName = "4.in";
		String outFileName = "4.out";
		this.runTest(testsPath, inFileName, outFileName);
	}
	
	public void runTest(String testsPath, String inFileName, String outFileName) throws FileNotFoundException {
	    PrintStream console = System.out;
	    BufferedInputStream in = new BufferedInputStream(
	      new FileInputStream(testsPath + File.separator + inFileName));
	    PrintStream out = new PrintStream(
	      new BufferedOutputStream(
	        new FileOutputStream(testsPath + File.separator + outFileName)));
	    System.setIn(in);
	    System.setOut(out);
	    System.setErr(out);

		GameConsoleGui gameConsoleGui = new GameConsoleGui();
		gameConsoleGui.run(false, PlayerType.HUMAN_VS_AI, false, 0);
		
	    out.close(); // Remember this!
	    System.setOut(console);
	}

}
