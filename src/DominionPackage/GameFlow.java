package DominionPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameFlow {
	
	//public Player player1 = new Player();
	
	public void drawPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			for (int i = 0; i < 5; i++) {
				if (player1.getDeck().size() == 0)
					restartDeck(player1);
				player1.drawCard(player1.getDeck().get(0));
			}
		} else if (playerTurnCounter == 2) {
			for (int i = 0; i < 5; i++) {
				if (player2.getDeck().size() == 0)
					restartDeck(player2);
				player2.drawCard(player2.getDeck().get(0));
			}
		} else if (playerTurnCounter == 3) {
			for (int i = 0; i < 5; i++) {
				if (player3.getDeck().size() == 0)
					restartDeck(player3);
				player3.drawCard(player3.getDeck().get(0));
			}
		} else {
			for (int i = 0; i < 5; i++) {
				if (player4.getDeck().size() == 0)
					restartDeck(player4);
				player4.drawCard(player4.getDeck().get(0));
			}
		}
		
		System.out.println("Here in Draw");
		
		actionPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
	}
	
	public void actionPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		
		boolean actCardsAvailable = false, vicCardsAvailable = false, coinCardsAvailable = false, curseCardsAvailable = false, attCardsAvailable = false, 
				durCardsAvailable = false, reactCardsAvailable = false;
		
		Card card = new Card(null, null, null, null, null, null, null, null, null, null, null);
		//player1.getDeck();
		//player2.getDeck();
		//player3.getDeck();
		//player4.getDeck();
		
		if (playerTurnCounter == 1) {
			
			// Play cards and remove them from the hand
			//player1.playCard(player1.getCardsInHand().get(0));
			
			// Search through the hand for an action cards
			Iterator<Card> itr = player1.getCardsInHand().iterator();
			
			while (itr.hasNext()) {
				if (player1.getCardsInHand().size() != 0)
					card = itr.next();
				if (card.getType1().equals("Action")) {
					actCardsAvailable = true;
					player1.addCardToAction(card);
				} else if (card.getType1().equals("Treasure")) {
					coinCardsAvailable = true;
					player1.addCardToTreasure(card);
				} else if (card.getType1().equals("Victory") || card.getType2().equals("Victory")) {
					vicCardsAvailable = true;
					player1.addCardToVictory(card);
				} else if (card.getType1().equals("Curse")) {
					curseCardsAvailable = true;
					player1.addCardToCurse(card);
				} else if (card.getType2().equals("Attack")) {
					attCardsAvailable = true;
				} else if (card.getType2().equals("Duration")) {
					durCardsAvailable = true;
				} else if (card.getType2().equals("Reaction")) {
					reactCardsAvailable = true;
				}
			}
			
			System.out.println("Here in Action");
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Press [y] to enter Buy Phase");
			
			if (scan.hasNextLine()) {
				String choice = scan.nextLine();
				switch (choice) {
					case "y":
						//scan.close();
						buyPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
						break;
					default:
						//scan.close();
						buyPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
						break;
				}
			}
		}
		
		//System.out.println(player1.getTreasure().size());
		//System.out.println(player1.getVictory().size());
		
		//System.out.println(player1.getDeck().size());
		
	}
	
	public void buyPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Here in Buy");
		cleanupPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
	}
	
	public void cleanupPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			while (player1.getCardsInPlay().size() != 0) {
				player1.addCardToDiscardPile(player1.getCardsInPlay().get(0));
				player1.removeCardFromPlay(player1.getCardsInPlay().get(0));
			}
		}
		
		System.out.println("Here in Cleanup");
		
		//return true;
	}
	
	public void restartDeck(Player player) {
		
		while (player.getDiscardPile().size() != 0) {
			player.addCardToDeck(player.getDiscardPile().get(0));
			player.removeCardFromDiscardPile(player.getDiscardPile().get(0));
		}
		
	}
	
}
