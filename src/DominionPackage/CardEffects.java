package DominionPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardEffects {
	
	public Runnable getCardEffect(int IDOfCard, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		Map<Integer, Runnable> effects = new HashMap<>();
		
		effects.put(1, () -> Copper(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(2, () -> Silver(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(3, () -> Gold(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(4, () -> Platinum(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(10, () -> Cellar(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(11, () -> Chapel(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(12, () -> Moat(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(13, () -> Harbinger(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(14, () -> Merchant(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(15, () -> Vassal(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(16, () -> Village(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		/*effects.put(17, () -> Workshop(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(18, () -> Bureaucrat(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(19, () -> Gardens(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(20, () -> Militia(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(21, () -> Moneylender(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(22, () -> Poacher(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(23, () -> Remodel(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(24, () -> Smithy(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
		effects.put(25, () -> ThroneRoom(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(26, () -> Bandit(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(27, () -> CouncilRoom(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(28, () -> Festival(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(29, () -> Laboratory(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(30, () -> Library(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(31, () -> Market(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(32, () -> Mine(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(33, () -> Sentry(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(34, () -> Witch(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(35, () -> Artisan(kingdoms, playerTurnCounter, player1, player2, player3, player4));*/
		
		// Invoke the command by:
        //int cmd = 1;
        //commands.get(cmd).run();   // Prints "Teleport"
		
		return effects.get(IDOfCard);
	}

	public void Copper(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Copper being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(1);
		}
	}
	
	public void Silver(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Silver being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(2);
		}
	}
	
	public void Gold(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Gold being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(3);
		}
	}
	
	public void Platinum(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Platinum being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(5);
		}
	}
	
	public void Cellar(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Action
			player1.addNumActions(1);
			
			// Effect
			System.out.println("Discard card(s) and redraw the same amount - choose a number between 0 (discard none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			int numDiscard = 0;
			
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
					
					numDiscard++;
				} else {
					System.out.println("That is not a valid choice. Choose a number between between 0 (discard none) and " + player1.getCardsInHand().size());
					choice = scan.nextInt();
				}
			}
			
			for (int i = 0; i < numDiscard; i++) {
				
				// Draw the top card from the deck and remove it from the deck - drawCard() adds it to the hand AND removes it from the top of the deck
				player1.drawCard(player1.getDeck().get(0));
			}
		}
	}
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Effect
			System.out.println("Trash card(s) up to 4 - choose a number between 0 (trash none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			int choice;
			
			for (int i = 0; i < 4; i++) {
				// Print out the cards with the value
				System.out.println("Cards in hand:");
				for (int j = 0; j < player1.getCardsInHand().size(); j++) {
					
					System.out.println((j + 1) + " = " + player1.getCardsInHand().get(j).getName());
				}
				
				choice = scan.nextInt();
				
				// Remove the chosen card from the hand and add it to the discard pile
				if (choice == 0) {
					break;
				} else if (choice > 0 || choice <= player1.getCardsInHand().size()) {
					
					// Get the selected card
					Card card = player1.getCardsInHand().get(choice - 1);
					
					// Remove the card from the hand
					player1.removeCardFromHand(card);
					
					// Add the card that was removed from the hand to the trash pile
					kingdoms.trash.add(card);
				} else {
					System.out.println("That is not a valid choice. Choose a number between 0 (trash none) and " + player1.getCardsInHand().size());
					choice = scan.nextInt();
				}
			}
		}
	}
	
	public void Moat(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			Card card = player1.getDeck().get(0);
			Card card2 = player1.getDeck().get(1);
			
			// +2 Cards
			player1.drawCard(card);
			System.out.println("You drew " + card);
			player1.drawCard(card2);
			System.out.println("You drew " + card);
			System.out.println();
			System.out.println("Your hand: ");
			for (Card card3 : player1.getCardsInHand())
				System.out.println(card3);
		}
	}
	
	public void Harbinger(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			Card card = player1.getDeck().get(0);
			player1.drawCard(card);
			System.out.println("You drew " + card);
			
			// +1 Action
			player1.addNumActions(1);
			
			// Effect
			if (player1.getDiscardPile().size() != 0) {
				System.out.println("Look through the discard pile and place it onto your deck");
				System.out.println("Press -1 to cancel the effect or press a number between 0 and " + (player1.getDiscardPile().size() - 1) + "to place a card from"
						+ " your discard pile onto the deck");
				
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				//boolean continueLooping = true;
				
				while (scan.hasNext()) {
					int choice = scan.nextInt();
					
					// Print out the cards with the value
					System.out.println("Cards in discard pile:");
					for (int i = 0; i < player1.getDiscardPile().size(); i++) {
						System.out.println((i + 1) + " = " + player1.getDiscardPile().get(i).getName());
					}
					
					// Remove the chosen card from the hand and add it to the discard pile
					if (choice == -1) {
						break;
					} else if (choice >= 0 || choice <= player1.getDiscardPile().size() - 1) {
						
						// Get the selected card
						Card card2 = player1.getDiscardPile().get(choice);
						System.out.println("You selected " + card2 + " to place onto your deck");
						
						// Remove the card from the discard pile
						player1.removeCardFromDiscardPile(card);
						
						// Add the card that was removed from the hand to the deck
						player1.addCardToDeck(card);
						
						//continueLooping = false;
						break;
					} else {
						System.out.println("Invalid input. Press -1 to cancel the effect or press a number between 0 and " + (player1.getDiscardPile().size() - 1) + 
								"to place a card from your discard pile onto the deck");
						choice = scan.nextInt();
					}
				}
			} else
				System.out.println("Your discard pile is empty");
		}
	}
	
	public void Merchant(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
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
	
	public void Vassal(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +2 Coins
			player1.addExtraCoins(2);
			
			// This is the card on top of the deck
			Card card = player1.getDeck().get(0);
			
			// Discard the top card of the deck and add it to the discard pile
			player1.addCardToDiscardPile(card);
			player1.removeCardFromDeck(card);
			
			System.out.println("You discarded " + card);
			
			// If the top card of the discard pile is an action card...
			if ("Action".equals(card.getType1())) {
				System.out.println(player1.getDiscardPile().get(0).getName() + " is an Action card. Would you like to play it? Press [y] for yes or [n] for no.");
				
				Scanner scan = new Scanner(System.in);
				String choice;
				while (scan.hasNext()) {
					choice = scan.nextLine();
					
					// If the player chooses to play the Action card, remove the card from the discard pile and add it to the player's cards in play
					if (choice.toLowerCase().equals("y")) {
						player1.addCardToCardsInPlay(card);
						player1.removeCardFromDiscardPile(card);
					} else 
						break;
				}
			}
		}
	}
	
	public void Village(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			player1.drawCard(player1.getDeck().get(0));
			
			// +1 Action
			player1.addNumActions(2);
		}
	}
	
}
