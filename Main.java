

import javax.swing.JFrame;

public class Main{

	public static void main(String[] args){
		Main gm = new Main();
		gm.start();
	}
	
	public void start(){
		JFrame f = new JFrame("Nine Men's Morris");
		f.setSize(800, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board b = new Board();
		f.add(b);
		
		f.setResizable(false);
		f.setVisible(true);		
		
	}

}

