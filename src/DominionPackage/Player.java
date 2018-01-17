package DominionPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private List<Card> deck = new ArrayList<Card>();
	private List<Card> discardPile = new ArrayList<Card>();
	private List<Card> hand = new ArrayList<Card>();
	private List<Card> cardsInPlay = new ArrayList<Card>();
	private List<Card> action = new ArrayList<Card>();
	private List<Card> victory = new ArrayList<Card>();
	private List<Card> treasure = new ArrayList<Card>();
	private List<Card> curse = new ArrayList<Card>();
	//private List<Card> attack = new ArrayList<Card>();
	//private List<Card> duration = new ArrayList<Card>();
	//private List<Card> reaction = new ArrayList<Card>();
	private int numActionsLeft = 1;
	private int numBuysLeft = 1;
	private int extraCoins = 0;
	private int extraVictoryPoints = 0;
	private int turnCounter;
	private int numPlayers = 2;
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public List<Card> getAction() {
		return action;
	}
	
	public List<Card> getVictory() {
		return victory;
	}
	
	public List<Card> getTreasure() {
		return treasure;
	}
	
	public List<Card> getCurse() {
		return curse;
	}
	
	public int getNumActions() {
		return numActionsLeft;
	}
	
	public int getNumBuys() {
		return numBuysLeft;
	}
	
	public int getExtraCoins() {
		return extraCoins;
	}
	
	public int getExtraVictoryPoints() {
		return extraVictoryPoints;
	}
	
	public void addNumActions(int num) {
		numActionsLeft = numActionsLeft + num;
	}
	
	public void addNumBuys(int num) {
		numBuysLeft = numBuysLeft + num;
	}
	
	public void addExtraCoins(int num) {
		extraCoins = extraCoins + num;
	}
	
	public void addExtraVictoryPoints(int num) {
		extraVictoryPoints = extraVictoryPoints + num;
	}
	
	public void resetNumActions(int num) {
		numActionsLeft = 1;
	}
	
	public void resetExtraCoins(int num) {
		extraCoins = 0;
	}
	
	public List<Card> getDiscardPile() {
		return discardPile;
	}
	
	public List<Card> getCardsInPlay() {
		return cardsInPlay;
	}
	
	public List<Card> getCardsInHand() {
		return hand;
	}
	
	public int getTurnCounter() {
		return turnCounter;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void setTurnCounter(int num) {
		turnCounter = num;
	}
	
	public void setNumPlayers(int num) {
		numPlayers = num;
	}
	
	public void playCard(Card card) {
		cardsInPlay.add(card);
		removeCardFromDeck(card);
	}
	
	public void addCardToDiscardPile(Card card) {
		discardPile.add(card);
	}
	
	public void addCardToCardsInPlay(Card card) {
		cardsInPlay.add(card);
	}
	
	public void addCardToDeck(Card card) {
		deck.add(card);
	}
	
	public void addCardToAction(Card card) {
		action.add(card);
	}
	
	public void addCardToVictory(Card card) {
		victory.add(card);
	}
	
	public void addCardToTreasure(Card card) {
		treasure.add(card);
	}
	
	public void addCardToCurse(Card card) {
		curse.add(card);
	}
	
	public void removeCardFromDeck(Card card) {
		deck.remove(card);
	}
	
	public void removeCardFromHand(Card card) {
		hand.remove(card);
	}
	
	public void removeCardFromPlay(Card card) {
		cardsInPlay.remove(card);
	}
	
	public void removeCardFromDiscardPile(Card card) {
		discardPile.remove(card);
	}
	
	public void drawCard(Card card) {
		hand.add(card);
		removeCardFromDeck(card);
	}
	
	public void shuffleDeck(List<Card> deck) {
		Collections.shuffle(deck);
	}
}
