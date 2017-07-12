package morris_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//import javafx.scene.image.Image;

public class MainMenu extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	//Frame contains these panels
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	//Options "New Game" or "Load game"
	private String option;
	private boolean SELECTED;

	private JButton[] select;

	private JLabel title;
	private JLabel authors;
	private JLabel center;

	public MainMenu() throws InterruptedException{

		frame = new JFrame("Nine Men's Morris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.black);

		initNorthPanel();
		initSouthPanel();
		initCenterPanel();
		initWestPanel();
		initEastPanel();

		//east and west panels can be populated with future features.

		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		//We must wait for user to select option
		while(!SELECTED) 
			Thread.sleep(1000);				
	}


	/**
	 * Initializes North panel along with components
	 */
	public void initNorthPanel(){
		northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.setBackground(Color.white);
		Font font1 = new Font("SansSerif", Font.BOLD, 48);
		title = new JLabel("Nine Men's Morris", SwingConstants.CENTER);
		title.setOpaque(true);
		title.setBackground(Color.white);
		title.setFont(font1);

		Font font2 = new Font("SansSerif", Font.BOLD, 16);
		authors = new JLabel("Guru S Miguel Q Shaui P Yuan L", SwingConstants.CENTER);
		authors.setFont(font2);
		authors.setOpaque(true);
		authors.setBackground(Color.white);

		northPanel.add(title, BorderLayout.NORTH);
		northPanel.add(authors, BorderLayout.SOUTH);


	}

	/**
	 * Initializes center panel along with components
	 */
	public void initCenterPanel(){
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(640, 500));
		centerPanel.setBackground(Color.white);

		center = new JLabel();
		center.setIcon(new ImageIcon("nmm.resized.jpg"));
		centerPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		centerPanel.setBackground(Color.white);

		centerPanel.add(center);

	}

	/**
	 * Initializes east panel can add components in future
	 */
	public void initEastPanel(){
		new JPanel();
	}

	/**
	 * Initializes west panel can add components in future
	 */
	public void initWestPanel(){
		new JPanel();

	}

	/**
	 * Initializes south panel along with components
	 */
	public void initSouthPanel(){
		select = new JButton[2];

		southPanel = new JPanel();
		southPanel.setBackground(Color.white);

		select[0] = new JButton("New Game");
		select[0].setPreferredSize(new Dimension(150, 40));
		select[0].addActionListener(this);

		select[1] = new JButton("Load Game");
		select[1].setPreferredSize(new Dimension(150, 40));
		select[1].addActionListener(this);


		for(int i = 0; i < select.length; i++)
		{
			southPanel.add(select[i]);
		}

	}


	/**
	 * ActionEvent will determine if user selected "New Game" or "Load Game"
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		String selected = actionEvent.getActionCommand();

		if(selected.compareToIgnoreCase("NEW GAME") == 0)
		{
			option = "NEW GAME";
		}
		else
		{
			option = "LOAD GAME";
		}
		SELECTED = true;			
	}

	/**
	 * Change visibility of gui if user has selected desired options
	 */
	public void closeGUI()
	{
		frame.setVisible(false);
	}


	public boolean hasSelectedLoadGame(){

		return option.equalsIgnoreCase("LOAD GAME");
	}

}
