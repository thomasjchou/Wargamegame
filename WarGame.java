/**
 * WarGame builds the structure for the card game War
 * Each card flip displays a new card from the deck of cards that was split among 2 players (user and computer).
 * Cards compared and higher card wins all cards.
 * If cards are tied, players remove two cards from their pile and flip 1 of them. Winner gets ALL cards
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Random;

public class WarGame {
   //declare static constants
   private static final int COMP_WIN = 0;	//number indicates computer has won
   private static final int USER_WIN = 1; //number indicates user has won
   private static final int WAR = -1;	//number indicates tie between user and computer --> equals a war
	//Declare variables
	private ArrayList<Card> userCards;	//user's card pile
	private ArrayList<Card> compCards;	//computer's card pile
	private ArrayList<Card> warCards;	//card pile up for grabs --> awarded to winner
	private Deck cardDeck;		// card deck of 52 cards
	private Card userCard, compCard;	//individual cards flipped from user & computer piles to be compared
   private int winNum;	//holds value of who has won game/round
   private int wagerCount; //holds number of cards won each time;
   private boolean gameOver;	//determines whether the game is over or not
	
   /**
	 * Default Constructor
	 */
	public WarGame()
	{
		userCards = new ArrayList<Card>();
		compCards = new ArrayList<Card>();
		warCards = new ArrayList<Card>();
		cardDeck = new Deck();
		gameOver = false;
		
		splitDeck();
	}
	
	/**
	 * SplitDeck method splits the card deck in half between two players
	 */
	public void splitDeck()
	{
		
		while(cardDeck.cardsLeft() != 0)
		{
			userCards.add(cardDeck.dealCard());
			compCards.add(cardDeck.dealCard());
		}
	}
	
	/**
	 * flipCard method removes a card from each player's list and assigns to card object
	 * checks to make sure user and computer's piles are not empty
	 * 
	 */
	public void flipCard()
	{
		//if user and computer card piles not empty, flip a card from each
		if (!userCards.isEmpty() && !compCards.isEmpty())
		{
			userCard = userCards.remove((userCards.size())-1);
			compCard = compCards.remove((compCards.size())-1);
		}
		//else if user has no cards left but computer does, computer automatically wins
		else if (userCards.isEmpty() && !compCards.isEmpty())
		{
			//Computer Wins
			winNum = COMP_WIN;
			gameOver = true;
		}
		//else if computer has no cards but user does, user automatically wins
		else if(!userCards.isEmpty() && compCards.isEmpty())
		{
			//User Wins
			winNum = USER_WIN;
			gameOver = true;
		}
		//else if computer and user cards has no cards left, throw an exception
		else if(userCards.isEmpty() && compCards.isEmpty())
		{
			throw new ListIndexOutOfBoundsException ("the arraylist is empty");
		}
		
	}
	/**
	 * CompareCards method sees whether user or computer's deck has higher cards
	 * if user or computer runs out of cards, opposite automatically wins
	 * if users have equal cards, goes to a war
	 */
	public void compareCards()
	{
		//add cards to temporary array that will give all cards to winner
		warCards.add(userCard);
		warCards.add(compCard);
		
		//if cards are equal, a war ensues
		if (userCard.equals(compCard))
		{
         winNum = WAR;
			war();
		}
		//else if the user's card is greater than the computers
		//add all cards to user's card pile
		else if (userCard.greaterThan(compCard)==true)
		{
			winNum = USER_WIN;
			wagerCount = warCards.size();
			for(Card c: warCards)
				
			{	
				//add the new cards to the front (bottom) of the user's deck
				userCards.add(0,c);
			}
			
			//clear the arraylist of all cards that were won
			warCards.clear();
			//shuffle user and computer's decks
			shuffle();
		}
		
		//else if the computer's card is greater than the user
		//add all cards to computer's card pile
		else 
		{
			winNum = COMP_WIN;
			wagerCount = warCards.size();
			for(Card c: warCards)
			{
				//add the new cards to the front (bottom) of the computer's deck
				compCards.add(0,c);
			}
			
			//clear the arraylist of all cards that were won
			warCards.clear();
			//shuffle user and computer's decks
			shuffle();
		}
	}
	
	
	/**
	 * War method 
	 * called when the user and computer's cards are equal
	 * adds an extra card from each players' pile to the total number of cards to be won
	 */
	public void war()
	{		
		warCards.add(userCards.remove((userCards.size())-1));
		warCards.add(compCards.remove((compCards.size())-1));	
	}

	
	/**
	 * shuffle method 
	 * shuffles user's & computer's card piles after each round
	 */
	public void shuffle()
	{
		//Declare variable
		//Declare variables
		Random gen = new Random();
		int randNum;

		//loop to shuffle cards in user deck
		for (int i = 0; i <= userCards.size()-1; i++)
		{
			randNum = gen.nextInt(userCards.size());
			Card temp = userCards.get(i);
			userCards.set(i, userCards.get(randNum));
			userCards.set(randNum, temp);
					
		}
		
		//loop to shuffle cards in comp deck
		for (int i = 0; i <= compCards.size()-1; i++)
		{
			randNum = gen.nextInt(compCards.size());
			Card temp = compCards.get(i);
			compCards.set(i, compCards.get(randNum));
			compCards.set(randNum, temp);
							
		}
	}
	
	
	
	/**
	 * getUserCard method returns card object from user's pile
	 * @return userCard object
	 */
	public Card getUserCard()
	{
		return userCard;
	}
	
	
	/**
	 * getCompCard method returns card object from computer's pile
	 * @return compCard object
	 */
	public Card getCompCard()
	{
		return compCard;
	}
	
	
	/**
	 * getUserNumCards method returns number of cards in user's pile
	 * @return Int number of cards left in user's deck
	 */
	public int getUserNumCards()
	{
		return userCards.size();
	}
	
	
	/**
	 * getCompNumCards method returns number of cards in computer's pile
	 * @return Int number of cards left in computer's deck
	 */
	public int getCompNumCards()
	{
		return compCards.size();
	}
	
	
	/**
	 * get Wager size returns the size of the number of cards to be won
	 * @return Int size of warCards array for how many cards are wagered
	 */
	public int getWagerSize()
	{
		return wagerCount;
	}
	
	
   /**
    *getWinner() determines who won each round (user = 1 , computer = 0, if tie it's a war = -1)
    *@return boolean of who won round 
   */  
	public int getRoundWinner()
   {
      return winNum;
   }
	
	
   /**
   *gameOver method determines whether or not the game is over and 
   *@return boolean true if there is a winner and false if game is still going
   */
	public boolean gameOver()
   {
      return gameOver;
   }   
	

}
