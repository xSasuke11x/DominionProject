package DominionPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardEffects {

	public void Cellar(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Action
			player1.addNumActions(1);
			
			// Effect
			System.out.println("Discard card(s) and redraw the same amount - choose a number between 0 (discard none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			
			for (int i = 0; i < player1.getCardsInHand().size(); i++) {
				int choice = scan.nextInt();
				
				// Print out the cards with the value
				System.out.println("Cards in hand:");
				for (int j = 0; j < player1.getCardsInHand().size(); j++) {
					System.out.println((j + 1) + " = " + player1.getCardsInHand().get(j).getName());
				}
				
				// Remove the chosen card from the hand and add it to the discard pile
				if (choice == 0) {
					break;
				} else if (choice >= 1 || choice <= player1.getCardsInHand().size()) {
					
					// Get the selected card
					Card card = player1.getCardsInHand().get(choice - 1);
					
					// Remove the card from the hand
					player1.removeCardFromHand(card);
					
					// Add the card that was removed from the hand to the discard pile
					player1.addCardToDiscardPile(card);
					
					// Draw the top card from the deck and remove it from the deck - drawCard() adds it to the hand AND removes it from the top of the deck
					player1.drawCard(player1.getDeck().get(0));
				} else {
					System.out.println("That is not a valid choice");
				}
			}
		}
	}
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Effect
			System.out.println("Trash card(s) up to 4 - choose a number between 0 (trash none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			int choice;
			
			for (int i = 0; i < 4; i++) {
				choice = scan.nextInt();
				
				// Print out the cards with the value
				System.out.println("Cards in hand:");
				for (int j = 0; j < player1.getCardsInHand().size(); j++) {
					
					System.out.println((j + 1) + " = " + player1.getCardsInHand().get(j).getName());
				}
				
				// Remove the chosen card from the hand and add it to the discard pile
				if (choice == 0) {
					break;
				} else if (choice >= 1 || choice <= player1.getCardsInHand().size()) {
					
					// Get the selected card
					Card card = player1.getCardsInHand().get(choice - 1);
					
					// Remove the card from the hand
					player1.removeCardFromHand(card);
					
					// Add the card that was removed from the hand to the trash pile
					kingdoms.trash.add(card);
				} else {
					System.out.println("That is not a valid choice");
				}
			}
		}
	}
	
	public void Moat(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +2 Cards
			player1.drawCard(player1.getDeck().get(0));
			player1.drawCard(player1.getDeck().get(0));
		}
	}
	
	public void Harbinger(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			player1.drawCard(player1.getDeck().get(0));
			
			// +1 Action
			player1.addNumActions(1);
			
			// Effect
			System.out.println("Look through the discard pile and place it onto your deck - choose a number between 0 (cancel effect) and " 
					+ player1.getDiscardPile().size());
			Scanner scan = new Scanner(System.in);
			boolean continueLooping = true;
			
			while (continueLooping) {
				int choice = scan.nextInt();
				
				// Print out the cards with the value
				System.out.println("Cards in discard pile:");
				for (int i = 0; i < player1.getDiscardPile().size(); i++) {
					System.out.println((i + 1) + " = " + player1.getDiscardPile().get(i).getName());
				}
				
				// Remove the chosen card from the hand and add it to the discard pile
				if (choice == 0) {
					break;
				} else if (choice >= 1 || choice <= player1.getDiscardPile().size()) {
					
					// Get the selected card
					Card card = player1.getDiscardPile().get(choice - 1);
					
					// Remove the card from the discard pile
					player1.removeCardFromDiscardPile(card);
					
					// Add the card that was removed from the hand to the deck
					player1.addCardToDeck(card);
					
					continueLooping = false;
				} else {
					System.out.println("That is not a valid choice");
				}
			}
		}
	}
	
	public void Merchant(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			player1.drawCard(player1.getDeck().get(0));
			
			// +1 Action
			player1.addNumActions(1);
			
			int silverCounter = 0;
			
			
			// Get the cards in play and see if any of them are Silver
			for (Card card : player1.getCardsInPlay()) {
				if (card.getName().equals("Silver"))
					silverCounter++;
			}
			
			// If a silver was played, get an extra coin for this turn
			if (silverCounter >= 1)
				player1.addExtraCoins(1);
		}
	}
	
	public void Vassal(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +2 Coins
			player1.addExtraCoins(2);
			
			// This is the card on top of the deck
			Card card = player1.getDeck().get(0);
			
			// Discard the top card of the deck and add it to the discard pile
			player1.removeCardFromDeck(card);
			player1.addCardToDiscardPile(card);
			
			// This is the card on top of the discard pile
			Card card2 = player1.getDiscardPile().get(0);
			
			// If the top card of the discard pile is an action card...
			if (card2.getType1().equals("Action")) {
				System.out.println("The card you just discard: ");
				System.out.println(player1.getDiscardPile().get(0));
				System.out.println(" is an Action card. Would you like to play it? Press [y] for yes or [n] for no.");
				
				Scanner scan = new Scanner(System.in);
				String choice;
				while (scan.hasNext()) {
					choice = scan.nextLine();
					
					// If the player chooses to play the Action card, remove the card from the discard pile and add it to the player's cards in play
					if (choice.toLowerCase().equals("y")) {
						player1.removeCardFromDiscardPile(card2);
						player1.addCardToCardsInPlay(card2);
					} else 
						break;
				}
			}
		}
	}
	
	public void Village(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			player1.drawCard(player1.getDeck().get(0));
			
			// +1 Action
			player1.addNumActions(2);
		}
	}
	
	public void Workshop(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			List<Card> fourCostCards = new ArrayList<Card>();
			
			// Get the list of all cards in the supply
		}
	}
}
