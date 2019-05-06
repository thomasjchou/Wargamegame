/*Sara Piette
*CS 110: Final Project
*War Game: GUI Interface
*/
/**
 * WarGameGUI provides a GUI interface to play the card game War between user and computer
 * User clicks the Flip Card button to progress game. Disabled when the game is over.
 * Each card flip displays a new card from the deck of cards that was split among 2 players (user and computer).
 * Cards compared and higher card wins all cards.
 * If cards are tied, players remove two cards from their pile and flip 1 of them. Winner gets ALL cards
 * @author Sara Piette
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarGameGUI extends JFrame{
	//Declare Variables
	private JPanel gameContainer, leftTop, rightTop, leftBottom, 
   rightBottom, buttonBar, cardBar, winnerBar;
   
	/*
	 * JButtons
	 * buttons to play,restart, or exit game
	 */
	private JButton flipButton, restartButton, exitButton; 
   
	/*
	 * Image Icons
	 * userBack and compBack hold static image of back side of cards
	 * userFront and compFront change with each card flip based on card
	 */
	private ImageIcon userFront, userBack, compFront, compBack;	
   
	/*
	 * JLabels to hold Image Icons assigned to them
	 * warBack1 and 2 used to display Image Icon of back side of 2nd card flipped during war
	 */
	private JLabel userPicBack, userPicFront, compPicBack, compPicFront, warBack1, warBack2;
   
	/*
	 * JLabels to Display:
	 * whose deck is whose (userCards & numCards)
	 * number of cards each player has left (userNum, compNum)
	 * winner of each round and winner of game (winnerLabel)
	 */
	private JLabel userCards, compCards, userNum, compNum, winnerLabel;
   
	/*
	 * WarGame object
	 * instantiates new card game of war
	 */
	private WarGame war;
	
	public WarGameGUI()
	{
		//title JFrame
		super("Game of War");
		//Create a new War Game--the game guts
		war = new WarGame();
		
		//set layout of default container
		setLayout(new BorderLayout());
      
	  createPanels();
      createPanelLabels();
      createButtons();
      createImageIcons();
      addPanelsToFrames();
      addToMainFrame();	
		
	}
	
	
   /**
   *createPanels creates panels and panel containers
   */
   private void createPanels()
   {
      //create grid container for card panels
		gameContainer = new JPanel(new GridLayout(2,2));
		gameContainer.setMaximumSize(new Dimension(450,550));
		
		//create 4 top and bottom panels
		leftTop = new JPanel(new FlowLayout());
		leftTop.setMaximumSize(new Dimension(200, 225));
		
		rightTop = new JPanel(new FlowLayout());
		rightTop.setMaximumSize(new Dimension(250, 225));
		
		leftBottom = new JPanel(new FlowLayout());
		leftBottom.setMaximumSize(new Dimension(200, 225));
		
		rightBottom = new JPanel(new FlowLayout());
		rightBottom.setMaximumSize(new Dimension(250, 225));

		//create winner bar panel 
		winnerBar = new JPanel(new FlowLayout());
		winnerBar.setMinimumSize(new Dimension(300,200));
      
      //create card bar panel
		cardBar = new JPanel(new GridLayout(4,1));
		cardBar.setBackground(new Color(250,250,250));
		cardBar.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		cardBar.setSize(200,300);
      
      //create button bar panel
		buttonBar = new JPanel(new FlowLayout());
		buttonBar.setMinimumSize(new Dimension(900,200));
	
	//Set Borders
		winnerBar.setBorder(BorderFactory.createLoweredBevelBorder());
		cardBar.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 2, Color.black));
		leftTop.setBorder(BorderFactory.createLoweredBevelBorder());
		rightTop.setBorder(BorderFactory.createLoweredBevelBorder());
		leftBottom.setBorder(BorderFactory.createLoweredBevelBorder());
		rightBottom.setBorder(BorderFactory.createLoweredBevelBorder());
		
	//Align Text
		winnerBar.setAlignmentX(50);
   }
   
   /**
   *createPanelLabels creates labels for winnerBar and cardBar panel
   */
   private void createPanelLabels()
   {
      //create label to display who wins each round
      winnerLabel = new JLabel();
      winnerLabel.setFont(new Font("Times",Font.BOLD,36));
      winnerLabel.setText("Welcome to War");
      winnerBar.add(winnerLabel);

		//create card pile labels
		userCards = new JLabel();
		userCards.setText("User Cards ");
		userCards.setFont(new Font("Times", Font.BOLD, 18));
		userCards.setHorizontalAlignment(SwingConstants.CENTER);
		cardBar.add(userCards);
		
		userNum = new JLabel();
		userNum.setText(""+war.getUserNumCards());
		userNum.setFont(new Font("Times", Font.BOLD, 40));
		userNum.setForeground(new Color(0,186,161));
		userNum.setHorizontalAlignment(SwingConstants.CENTER);
		userNum.setVerticalAlignment(SwingConstants.TOP);
		cardBar.add(userNum);
      
		
		compCards = new JLabel();
		compCards.setText("Computer Cards ");
		compCards.setFont(new Font("Times", Font.BOLD, 18));
		compCards.setHorizontalAlignment(SwingConstants.CENTER);
		cardBar.add(compCards);
		
		compNum = new JLabel();
		compNum.setText(""+war.getCompNumCards());
		compNum.setFont(new Font("Times", Font.BOLD, 40));
		compNum.setForeground(new Color(216,88,118));
		compNum.setHorizontalAlignment(SwingConstants.CENTER);
		compNum.setVerticalAlignment(SwingConstants.TOP);
		cardBar.add(compNum);
		

   }
   
   /**
   *createButtons makes and activates JButtons for buttonBar panel 
   */
	private void createButtons()
   {
      //create flip button for user
		flipButton = new JButton("Flip Card");
		flipButton.setForeground(new Color(42,125,220));
		flipButton.setFont(new Font("Times",Font.BOLD,18));
		flipButton.addActionListener(new ButtonListener());
		buttonBar.add(flipButton);
		
		//create restartButton
		restartButton = new JButton("Restart");
		restartButton.setFont(new Font("Times",Font.BOLD,18));
		restartButton.setForeground(new Color(17,176,59));
		restartButton.addActionListener(new ResetListener());
		buttonBar.add(restartButton);
		
		//create Exit button
		exitButton = new JButton("Exit");
		exitButton.setForeground(new Color(220,60,42));
		exitButton.setFont(new Font("Times",Font.BOLD,18));
		exitButton.addActionListener(new ExitListener());
		buttonBar.add(exitButton);

   }
   
   /**
   *createImageIcons creates all images and labels for images 
   *adds JLabels to frames
   */
   private void createImageIcons()
   {
      //create image icons
		userFront = null;
		userBack = new ImageIcon("images/back.jpg");
		compFront = null;
		compBack = new ImageIcon ("images/back.jpg");
      
      //create labels for image icons
		userPicFront = new JLabel();
		compPicFront = new JLabel();
		
		userPicBack = new JLabel(userBack);
		compPicBack = new JLabel(compBack);
		
		warBack1 = new JLabel();
		warBack2 = new JLabel();
      
      //add pictures to panels
		leftTop.add(userPicBack);
		leftBottom.add(compPicBack);
		
		rightTop.add(warBack1);
		rightBottom.add(warBack2);
		
		rightTop.add(userPicFront);
		rightBottom.add(compPicFront);

   }
   
   /**
   *adds subpanels to container panel
   */
   private void addPanelsToFrames()
   {
      //add all panels to proper frames
		gameContainer.add(leftTop);
		gameContainer.add(rightTop);
		gameContainer.add(leftBottom);
		gameContainer.add(rightBottom);
   }
   /**
   * adds all panels to main frame
   */
   private void addToMainFrame()
   {
      //add main container panels to large jFrame
		add(gameContainer, BorderLayout.CENTER);
		add(cardBar, BorderLayout.WEST);
		add(buttonBar, BorderLayout.SOUTH);
      add(winnerBar, BorderLayout.NORTH);
   }  
   
   
	/**
   *ButtonListener class implements methods for each card flip 
   *card flip activated by flipButton 
   */
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
         if (war.gameOver()==false)
         {
        	 //set the text of each  card pile number based on winner
             	userNum.setText(""+war.getUserNumCards());
             	compNum.setText(""+war.getCompNumCards());
         			
                  //determine whether or not war cards must be laid out
                  if (war.getRoundWinner() == -1)
                  {  
                     warBack1.setIcon(userBack);
                     warBack2.setIcon(compBack);
                  }
                  else
                  {
                     warBack1.setIcon(null);
                     warBack2.setIcon(null);
                  }
                  
         			//flip the next card in each pile 
         			war.flipCard();
           			
                  //change the card icons
                  userFront = war.getUserCard().getIcon();
         			compFront = war.getCompCard().getIcon();
         
                  userPicFront.setIcon(userFront);
         			compPicFront.setIcon(compFront);
                  
                  //compare cards to determine winner
                  war.compareCards();
                           
                  if (war.getRoundWinner() == 1)
                  {
                     winnerLabel.setForeground(new Color(0,186,161));
                     winnerLabel.setText("User wins "+war.getWagerSize()+" cards.");
                     
                  }
                  else if(war.getRoundWinner() == 0)
                  {
                     winnerLabel.setForeground(new Color(216,88,118));
                     winnerLabel.setText("Computer wins "+war.getWagerSize()+" cards.");
                     
                  }
                  else if (war.getRoundWinner() == -1)
                  {
                     winnerLabel.setForeground(Color.black);
                     winnerLabel.setText("It's a War!");
                     
                  }
          }
          else if(war.gameOver() == true)
          {
                if (war.getRoundWinner() == 1)
                  {  
                     winnerLabel.setForeground(new Color(0,186,161));
                     winnerLabel.setText("Game over. User Wins!");
                     winnerLabel.setFont(new Font("Times",Font.BOLD, 50));
                     disableFlip();
                  }
                else if(war.getRoundWinner() == 0)
                  {
                     winnerLabel.setForeground(new Color(216,88,118));
                     winnerLabel.setText("Game over. Computer Wins.");
                     winnerLabel.setFont(new Font("Times",Font.BOLD, 50));
                     disableFlip();
                  }
          }
		}
      /**
      * disable flip method inactivates flipButton 
      * when one player runs out of cards--initiates game's end
      */
      public void disableFlip()
      {
         flipButton.setEnabled(false);
      }
	}
   
   
   /**
   * ExitListener class implements methods associated when exitButton activated
   * closes entire program
   */
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
   /**
   * ResetListener class implements methods for restartButton
   * starts a new game of war when clicked.
   */
	private class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//create new war game
			war = new WarGame();
			
			//reset all labels and pictures
		      winnerLabel.setForeground(Color.black);
		      winnerLabel.setText("New Game");
		    
		      userCards.setText("User Cards ");

		      userNum.setText(""+war.getUserNumCards());
			
		      compCards.setText("Computer Cards ");
		
		      compNum.setText(""+war.getCompNumCards());
		      
		    //reset Image Icons
			userFront = null;
			compFront = null;

			userPicFront.setIcon(userFront);
		    compPicFront.setIcon(compFront);
						
		}
	}
}
