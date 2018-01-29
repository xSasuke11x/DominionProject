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
	
	public void addCardToTypeList(Card card) {
		if ("Action".equals(card.getType1()))
			action.add(card);
		if ("Treasure".equals(card.getType1()) || "Treasure".equals(card.getType2()) || "Treasure".equals(card.getType3()) || "Treasure".equals(card.getType4()))
			treasure.add(card);
		if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || "Victory".equals(card.getType3()) || "Victory".equals(card.getType4()))
			victory.add(card);
	}
	
	public void removeCardFromTypeList(Card card) {
		if ("Action".equals(card.getType1())) {
			removeCardFromAction(card);
		}
		if ("Treasure".equals(card.getType1()) || "Treasure".equals(card.getType2()) || "Treasure".equals(card.getType3()) 
				|| "Treasure".equals(card.getType4())) {	
			removeCardFromTreasure(card);
		}
		if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || "Victory".equals(card.getType3()) 
				|| "Victory".equals(card.getType4())) {	
			removeCardFromVictory(card);
		}
	}
	
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
	
	public void resetNumActions() {
		numActionsLeft = 1;
	}
	
	public void resetNumBuys() {
		numBuysLeft = 1;
	}
	
	public void resetExtraCoins() {
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
		Collections.reverse(getDiscardPile());
		discardPile.add(card);
		Collections.reverse(getDiscardPile());
	}
	
	public void addCardToCardsInPlay(Card card) {
		Collections.reverse(getCardsInPlay());
		cardsInPlay.add(card);
		Collections.reverse(getCardsInPlay());
	}
	
	public void addCardToDeck(Card card) {
		Collections.reverse(getDeck());
		deck.add(card);
		Collections.reverse(getDeck());
	}
	
	public void addCardToHand(Card card) {
		Collections.reverse(getDeck());
		hand.add(card);
		addCardToTypeList(card);
		Collections.reverse(getDeck());
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
	
	public void removeCardFromAction(Card card) {
		action.remove(card);
	}
	
	public void removeCardFromTreasure(Card card) {
		treasure.remove(card);
	}
	
	public void removeCardFromVictory(Card card) {
		victory.remove(card);
	}
	
	public void removeCardFromCurse(Card card) {
		curse.remove(card);
	}
	
	public void removeCardFromDeck(Card card) {
		deck.remove(card);
	}
	
	public void removeCardFromHand(Card card) {
		hand.remove(card);
		removeCardFromTypeList(card);
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
		addCardToTypeList(card);
	}
	
	public void shuffleDeck(List<Card> deck) {
		Collections.shuffle(deck);
	}
}
