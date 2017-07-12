package morris_java;

import morris_java.GameGui;
import morris_java.MainMenu;
import morris_java.PlayerType;
import morris_java.SelectOpponentGUI;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		boolean restart = false, select = true;

		do{
			MainMenu menu = new MainMenu();

			menu.closeGUI();
			boolean isLoadGame = menu.hasSelectedLoadGame();
			System.out.println("load game: " + isLoadGame);
			PlayerType playerType = null;
			int difficulty = 1;
			boolean isHumanFirst = true;

			if(!isLoadGame) {
				SelectOpponentGUI selectOpponent = new SelectOpponentGUI();
				playerType = selectOpponent.getOpponent();
				System.out.println(playerType);

				//true if they selected human, false otherwise
				System.out.println("Opponent human: " + playerType);
				if(playerType.equals(PlayerType.HUMAN_VS_AI)) {
					difficulty = selectOpponent.getDifficulty();
					System.out.println("Difficulty: " + difficulty);
					isHumanFirst = selectOpponent.getOrder();
				}
				selectOpponent.closeGUI();
			}

			GameGui gameGui = new GameGui();

			gameGui.start(isLoadGame, playerType, isHumanFirst, difficulty);
			while(select){
				Thread.sleep(1000);
				restart=gameGui.isRestart();
				if(restart){
					select=false;
				}
			}
		}while(restart);
	}
}
