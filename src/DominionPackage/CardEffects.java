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
					System.out.println("That is not a valid choice");
				}
			}
			
			for (int i = 0; i < numDiscard; i++) {
				
				// Draw the top card from the deck and remove it from the deck - drawCard() adds it to the hand AND removes it from the top of the deck
				player1.drawCard(player1.getDeck().get(0));
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
			
			List<List<Card>> fourCostKingdoms = new ArrayList<List<Card>>();
			
			// Add all 4-cost kingdoms to the fourCostKingdoms list
			for (List<Card> kingdom : kingdoms.supplyList) {
				try {
					if (Integer.parseInt(kingdom.get(0).getCost()) <= 4) {
						fourCostKingdoms.add(kingdom);
					}
				} catch (NumberFormatException ex) {
					// Catch is for formality to catch out integer parsing errors. The Try block still parses all 4-cost kingdoms correctly.
				}
			}
			
			// Print out the 4-cost kingdoms
			int i = 1;
			System.out.println("The following are the 4-cost kingdoms:");
			for (List<Card> kingdom : fourCostKingdoms) {
				System.out.println(i + " = " + kingdom);
				i++;
			}
			
			// Let the player choose a kingdom
			System.out.println("Press a number between 1 and " + fourCostKingdoms.size() + " to gain the card");
			Scanner scan = new Scanner(System.in);
			String choice;
			while (scan.hasNext()) {
				choice = scan.nextLine();
				
				// Choices must be a number between 1 and the size of the 4-cost kingdoms list
				if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= fourCostKingdoms.size()) {
					List<Card> kingdom = new ArrayList<Card>();
					kingdom = fourCostKingdoms.get(Integer.parseInt(choice) - 1);
					
					// Add the card from the kingdom to the discard pile
					player1.addCardToDiscardPile(kingdom.get(0));
					
					// Remove the card from the kingdom
					kingdoms.removeCardFromSupplyList(kingdom.get(0));
				} else {
					System.out.println("Invalid number. Please press a number between 1 and " + fourCostKingdoms.size() + " to gain the card");
				}
			}
		}
	}
	
	public void Bureaucrat(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Gain a Silver onto the deck
			player1.addCardToDeck(kingdoms.silver.get(0));
			
			// Remove the Silver from the supply list
			kingdoms.removeCardFromSupplyList(kingdoms.silver.get(0));
			
			// For two players...
			if (player1.getNumPlayers() == 2) {
				System.out.println("All other players should now look away until they say it is ok to look.");
				
				// Delay next output for 3 seconds
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// Do nothing
				}
				
				// Print out the cards in the hand
				int i = 1;
				List<Card> vicCardsInHand = new ArrayList<Card>();
				Card returnedCard = new Card(null, null, null, null, null, null, null, null);
				
				System.out.println("Here are the victory cards in your hand:");
				
				for (Card card : player2.getCardsInHand()) {
					if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || 
						"Victory".equals(card.getType3()) || "Victory".equals(card.getType4())) {
						vicCardsInHand.add(card);
						System.out.println(i + " = " + card.getName());
					}
					i++;
				}
				
				System.out.println("Press a number between 1 and " + vicCardsInHand.size() + " to place the card back onto the deck.");
				
				Scanner scan = new Scanner(System.in);
				String choice;
				while (scan.hasNext()) {
					choice = scan.nextLine();
					
					if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= vicCardsInHand.size()) {
						// Put a victory card from the hand onto the deck
						returnedCard = vicCardsInHand.get(Integer.parseInt(choice) - 1);
						player2.addCardToDeck(returnedCard);
						
						// Remove the first card from the hand that has the same name as the target card
						for (Card card : player2.getCardsInHand()) {
							if (returnedCard.getName().equals(card.getName())) {
								player2.getCardsInHand().remove(card);
								break;
							}
						}
					} else {
						System.out.println("Invalid choice. Press a number between 1 and " + vicCardsInHand.size() + " to place the card back onto the deck.");
					}
				}
			}
		}
	}
	
	public void Gardens(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player1.getCardsInHand().size() + player1.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player1.addExtraVictoryPoints(totalNumCards);
		} else if (playerTurnCounter == 2) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player2.getCardsInHand().size() + player2.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player2.addExtraVictoryPoints(totalNumCards);
		} else if (playerTurnCounter == 3) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player3.getCardsInHand().size() + player3.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player3.addExtraVictoryPoints(totalNumCards);
		} else if (playerTurnCounter == 4) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player4.getCardsInHand().size() + player4.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player4.addExtraVictoryPoints(totalNumCards);
		}
	}
	
	public void Militia(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +2 Coins
			player1.addExtraCoins(2);
			
			// For two players...
			if (player1.getNumPlayers() == 2) {
				System.out.println("All other players should now look away until they say it is ok to look. First player to resolve the effect is to the left"
						+ " of the preson who played Militia");
				
				// Delay next output for 3 seconds
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// Do nothing
				}
				
				// Print out the cards in the player's hand
				int i;
				Scanner scan = new Scanner(System.in);
				String choice;
				System.out.println("Here are the cards in Player " + (playerTurnCounter + 1) + "'s hand:");
				
				// Repeat while the player has more than 3 cards in their hand
				while (player2.getCardsInHand().size() > 3) {
					i = 1;
					for (Card card : player2.getCardsInHand()) {
						System.out.println(i + " = " + card.getName());
						i++;
					}
					
					System.out.println("Press a number between 1 and " + player2.getCardsInHand().size() + " to place the card back onto the deck. This will"
							+ " repeat until you have 3 cards left in your hand");
					
					choice = scan.nextLine();
					
					if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= player2.getCardsInHand().size()) {
						Card card = player2.getCardsInHand().get(Integer.parseInt(choice));
						
						// Put the selected card onto the deck
						player2.addCardToDeck(card);
						
						// Remove the selected card from the player's hand
						player2.removeCardFromHand(card);
					} else {
						System.out.println("Invalid choice. Press a number between 1 and " + player2.getCardsInHand().size() + " to place the card back onto the deck.");
					}
				}
			}
		}
	}
	
	public void Moneylender(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			Scanner scan = new Scanner(System.in);
			String choice;
			int handLimit = 1;
			
			System.out.println("Do you want to trash a copper for +3 Coins? Press [y] for yes or [n] for no.");
			while (scan.hasNext()) {
				choice = scan.nextLine();
				
				if (choice.toLowerCase().equals("y")) {
					// Search through the hand for a copper
					for (Card card : player1.getCardsInHand()) {
						if ("Copper".equals(card.getName())) {
							// Remove the card from the player's hand
							player1.removeCardFromHand(card);
							
							// Add the card to the player's discard pile
							player1.addCardToDiscardPile(card);
							
							// Add 3 extra coins to that player
							player1.addExtraCoins(3);
							break;
						}
						
						if (handLimit == player1.getCardsInHand().size()) {
							System.out.println("You have no coppers to trash!");
							break;
						}
						
						handLimit++;
					}
				} else {
					// The player chose not to discard a Copper
					break;
				}
			}
		}
	}
}
