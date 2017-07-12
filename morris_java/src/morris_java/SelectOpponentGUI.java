package morris_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectOpponentGUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String opp;
	private boolean SELECTED = false;
	private boolean SELECTEDIFFICULTY = false;
	private boolean SELECTEORDER = false;
	
	private JButton[] opponent;

	private JFrame frame;
	private Label title;
	private JLabel center;
	private JPanel panel;

	private JPanel opponentPanel;
	private JPanel centerPanel;
	private JPanel radioPanelDifficulty;
	private JPanel radioPanelOrder;
	private JPanel southPanel;
	
	private ButtonGroup groupDifficulty;
	private ButtonGroup groupOrder;
	
	private int difficulty = 0;
	private boolean humanFirst = true;

	public SelectOpponentGUI() throws InterruptedException{
		opponent = new JButton[3];

		frame = new JFrame("Nine Men's Morris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.black);		

		initNorthPanel();
		initSouthPanel();
		initCenterPanel();
		initWestPanel();
		initEastPanel();

		frame.add(title, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setSize(640, 550);
		frame.pack();
		frame.setVisible(true);

		while(!SELECTED){
			Thread.sleep(1);
		}
		
		if(getOpponent().equals(PlayerType.HUMAN_VS_AI)) {
			radioPanelDifficulty.setVisible(true);
			radioPanelOrder.setVisible(true);
			while(!(SELECTEDIFFICULTY && SELECTEORDER)) 
				Thread.sleep(1);			
		}
	}


	/**
	 * Initializes east panel can add components in future
	 */
	private void initEastPanel() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Initializes west panel can add components in future
	 */
	private void initWestPanel() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Initializes Center panel along with components
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(640, 500));
		
		center = new JLabel();
		center.setIcon(new ImageIcon("nmm.resized.jpg"));

		centerPanel.add(center);
		
	}


	/**
	 * Initializes South panel along with components
	 */
	private void initSouthPanel() {
		panel = new JPanel();
		panel.setBackground(Color.white);

		

		opponent[0] = new JButton("HUMAN_VS_HUMAN");
		opponent[0].setPreferredSize(new Dimension(200, 40));
		opponent[0].addActionListener(this);		
		
		opponent[1] = new JButton("HUMAN_VS_AI");
		opponent[1].setPreferredSize(new Dimension(200, 40));
		opponent[1].addActionListener(this);
		
		opponent[2] = new JButton("AI_VS_AI");
		opponent[2].setPreferredSize(new Dimension(200, 40));
		opponent[2].addActionListener(this);

		opponentPanel = new JPanel(new GridLayout(3, 1));
		opponentPanel.setBackground(Color.white);

		for(int i = 0; i < opponent.length; i++)
		{
			opponentPanel.add(opponent[i]);
		}

		panel.add(opponentPanel, BorderLayout.SOUTH);

		JRadioButton easy = new JRadioButton("Easy");
		JRadioButton medium = new JRadioButton("Medium");
		JRadioButton hard = new JRadioButton("Hard");
		
		JRadioButton humanFirst = new JRadioButton("HumanFirst");
		JRadioButton AIFirst = new JRadioButton("AIFirst");

		easy.addActionListener(new RadioButtonListenerForDifficulty());
		medium.addActionListener(new RadioButtonListenerForDifficulty());
		hard.addActionListener(new RadioButtonListenerForDifficulty());
		
		humanFirst.addActionListener(new RadioButtonListenerForOrder());
		AIFirst.addActionListener(new RadioButtonListenerForOrder());

		//Group the radio buttons.
		groupDifficulty = new ButtonGroup();
		groupDifficulty.add(easy);
		groupDifficulty.add(medium);
		groupDifficulty.add(hard);
		
		groupOrder = new ButtonGroup();
		groupOrder.add(humanFirst);
		groupOrder.add(AIFirst);
	
		radioPanelDifficulty = new JPanel(new GridLayout(3, 1));
		radioPanelDifficulty.add(easy);
		radioPanelDifficulty.add(medium);
		radioPanelDifficulty.add(hard);
		radioPanelDifficulty.setVisible(false);
		
		radioPanelOrder = new JPanel(new GridLayout(2, 1));
		radioPanelOrder.add(humanFirst);
		radioPanelOrder.add(AIFirst);
		radioPanelOrder.setVisible(false);
		
		southPanel = new JPanel();
		southPanel.add(opponentPanel);
		southPanel.add(radioPanelDifficulty);
		southPanel.add(radioPanelOrder);
	}


	/**
	 * Initializes North panel along with components
	 */
	private void initNorthPanel() {
		Font font1 = new Font("SansSerif", Font.BOLD, 24);
		title = new Label("Select Opponent");
		title.setFont(font1);
		title.setBackground(Color.white);
		title.setPreferredSize(new Dimension(100, 50));
		title.setAlignment(Label.CENTER);
	}



	/**
	 * ActionEvent will determine if user selected HUMAN_VS_HUMAN, HUMAN_VS_AI, AI_VS_AI
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		String selected = actionEvent.getActionCommand();
		
		if(selected.compareToIgnoreCase("HUMAN_VS_HUMAN") == 0)
		{
			opp = "HUMAN_VS_HUMAN";
		}
		else if(selected.compareToIgnoreCase("HUMAN_VS_AI") == 0)
		{
			opp = "HUMAN_VS_AI";		
			
		}
		else {
			opp = "AI_VS_AI";
		}
		SELECTED = true;			
	}
	
	/**
	 * Lists to Radio Buttons for difficulty the user desires
	 */
	private class RadioButtonListenerForDifficulty implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String selected = event.getActionCommand();
			
			if(selected.equalsIgnoreCase("easy"))
			{
				difficulty = 0;
			}
			else if(selected.equalsIgnoreCase("medium"))
			{
				difficulty = 1;
			}
			else if(selected.equalsIgnoreCase("hard"))
			{
				difficulty = 2;
			}
			
			SELECTEDIFFICULTY = true;		
		}
	}
	
	/**
	 * Lists to Radio Buttons for playing order as the user desires
	 */
	private class RadioButtonListenerForOrder implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String selected = event.getActionCommand();
			
			if(selected.equalsIgnoreCase("humanFirst"))
			{
				humanFirst = true;
			}
			else if(selected.equalsIgnoreCase("AIFirst"))
			{
				humanFirst = false;
			}
			
			SELECTEORDER = true;		
		}
	}

	/**
	 * Change visibility of gui if user has selected desired options
	 */
	public void closeGUI()
	{
		frame.setVisible(false);
	}


	/**
	 * getter for selected opponent "human" or "ai" 
	 * @return HUMAN_VS_HUMAN, HUMAN_VS_AI, AI_VS_AI
	 */
	public PlayerType getOpponent(){

		if(opp.equalsIgnoreCase("HUMAN_VS_HUMAN"))
			return PlayerType.HUMAN_VS_HUMAN;
		else if(opp.equalsIgnoreCase("HUMAN_VS_AI"))
			return PlayerType.HUMAN_VS_AI;
		else
			return PlayerType.AI_VS_AI;
	}
	

	/**
	 * getter for selected difficulty easy, medium, or hard
	 * @return difficulty user selected
	 */
	public int getDifficulty(){
		return difficulty;
	}

	public boolean getOrder() {
		return humanFirst;
	}
}

