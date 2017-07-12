package morris_java;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		boolean debug = false;
		if(debug) {
			GameConsoleGui gameConsoleGui = new GameConsoleGui();
			gameConsoleGui.run();
		}
		else {
			GameGui gameGui = new GameGui();
			gameGui.start();
		}
	}
}
