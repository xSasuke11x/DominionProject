package DominionPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TemporaryCardEffects {
	
	public void Moneylender(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			@SuppressWarnings("resource")
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
	
	public void Poacher(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Card
			player1.drawCard(player1.getDeck().get(0));
			
			// +1 Action
			player1.addNumActions(1);
			
			// +1 Coin
			player1.addExtraCoins(1);
			
			// Effect
			int numEmptyKingdoms = 0;
			
			// Keep a running count of how many empty supply piles there are
			for (List<Card> kingdom : kingdoms.getSupplyList()) {
				if (kingdom.get(0).equals(null)) {
					numEmptyKingdoms++;
				}
			}
			
			// Discard a card for each empty supply pile
			for (int i = 0; i < numEmptyKingdoms; i++) {
				// Keep in mind that there might not be cards in the hand to discard
				if (player1.getCardsInHand().size() > 0) {
					// Do actions while cards exist in the hand
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					String choice;
					
					System.out.println("Discard a card per empty supply pile.");
					while (scan.hasNext()) {
						choice = scan.nextLine();
						
						// Print out the hand and choose a card to discard
						System.out.println("The following are the cards in your hand. Press a number between 1 and " + player1.getCardsInHand().size() 
								+ " to discard that card.");
						for (Card card : player1.getCardsInHand()) {
							System.out.println(card);
						}
						
						// Valid number
						if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= player1.getCardsInHand().size()) {
							Card card = player1.getCardsInHand().get(Integer.parseInt(choice) - 1);
							
							// Add the card to the discard pile.
							player1.addCardToDiscardPile(card);
							
							// Remove a card from the player's hand
							player1.removeCardFromHand(card);
						} else {
							// Invalid choice
							System.out.println("That is not a valid number. Please choose a number between 1 and " + player1.getCardsInHand().size() 
								+ " to discard that card.");
							choice = scan.nextLine();
						}
					}
				} else {
					break;
				}
			}
		}
	}
	
	public void Remodel(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Effect
			System.out.println("Trash a card in your hand. Gain a card costing up to +2 Coins more than it.");
			
			if (player1.getCardsInHand().size() > 0) {
				System.out.println("Choose a number between 1 and " + player1.getCardsInHand().size() + " to trash.");
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				int choice;
				boolean cardDiscarded = false;
				
				while (cardDiscarded == false) {
					choice = scan.nextInt();
					
					// Print out the cards with the value
					System.out.println("Cards in hand:");
					for (int i = 0; i < player1.getCardsInHand().size(); i++) {
						
						System.out.println((i + 1) + " = " + player1.getCardsInHand().get(i).getName());
					}
					
					// Remove the chosen card from the hand and add it to the discard pile
					if (choice > 0 && choice <= player1.getCardsInHand().size()) {
						
						// Get the selected card
						Card card = player1.getCardsInHand().get(choice - 1);
						
						// Remove the card from the hand
						player1.removeCardFromHand(card);
						
						// Add the card that was removed from the hand to the trash pile
						kingdoms.trash.add(card);
						
						// Set the boolean to indicate that the player has trashed a card.
						cardDiscarded = true;
						
						List<List<Card>> twoMoreCostKingdoms = new ArrayList<List<Card>>();
						
						// Add all kingdoms to the twoMoreCostKingdoms list that cost at most 2 more than the discarded card
						for (List<Card> kingdom : kingdoms.supplyList) {
							try {
								if (Integer.parseInt(kingdom.get(0).getCost()) <= (Integer.parseInt(card.getCost() + 2))) {
									twoMoreCostKingdoms.add(kingdom);
								}
							} catch (NumberFormatException ex) {
								// Catch is for formality to catch out integer parsing errors. The Try block still parses all 4-cost kingdoms correctly.
							}
						}
						
						// Print out the twoMoreCostKingdoms list
						int j = 1;
						System.out.println("The following are the kingdoms that cost up to two more than your discarded card:");
						for (List<Card> kingdom : twoMoreCostKingdoms) {
							System.out.println(j + " = " + kingdom);
							j++;
						}
						
						// Let the player choose a kingdom
						System.out.println("Press a number between 1 and " + twoMoreCostKingdoms.size() + " to gain the card");
						@SuppressWarnings("resource")
						Scanner scan2 = new Scanner(System.in);
						String choice2;
						while (scan.hasNext()) {
							choice2 = scan2.nextLine();
							
							// Choices must be a number between 1 and the size of the twoMoreCostKingdoms list
							if (Integer.parseInt(choice2) > 0 && Integer.parseInt(choice2) <= twoMoreCostKingdoms.size()) {
								List<Card> kingdom = new ArrayList<Card>();
								kingdom = twoMoreCostKingdoms.get(Integer.parseInt(choice2) - 1);
								
								// Add the card from the kingdom to the discard pile
								player1.addCardToDiscardPile(kingdom.get(0));
								
								// Remove the card from the kingdom
								kingdoms.removeCardFromSupplyList(kingdom.get(0));
							} else {
								System.out.println("Invalid number. Please press a number between 1 and " + twoMoreCostKingdoms.size() + " to gain the card");
								choice2 = scan2.nextLine();
							}
						}
					} else {
						System.out.println("That is not a valid choice. Choose a number between 1 and " + player1.getCardsInHand().size() + " to trash.");
						choice = scan.nextInt();
					}
				}
			} else {
				System.out.println("You do not have enough cards in your hand to trash.");
			}
		}
	}
	
	public void Smithy(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				Card card = player1.getDeck().get(0);
				player1.drawCard(card);
				System.out.println("You drew " + card);
			}
			
			System.out.println();
			System.out.println("Cards in your hand:");
			for (Card card2 : player1.getCardsInHand())
				System.out.println(card2);
		} else if (playerTurnCounter == 2) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				Card card = player1.getDeck().get(0);
				player1.drawCard(card);
				System.out.println("You drew " + card);
			}
			
			System.out.println();
			System.out.println("Cards in your hand:");
			for (Card card2 : player1.getCardsInHand())
				System.out.println(card2);
		} else if (playerTurnCounter == 3) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				Card card = player1.getDeck().get(0);
				player1.drawCard(card);
				System.out.println("You drew " + card);
			}
			
			System.out.println();
			System.out.println("Cards in your hand:");
			for (Card card2 : player1.getCardsInHand())
				System.out.println(card2);
		} else if (playerTurnCounter == 4) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				Card card = player1.getDeck().get(0);
				player1.drawCard(card);
				System.out.println("You drew " + card);
			}
			
			System.out.println();
			System.out.println("Cards in your hand:");
			for (Card card2 : player1.getCardsInHand())
				System.out.println(card2);
		}
	}
	
	/*public void ThroneRoom(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			player1.addNumActions(1);
			
		}
	}*/
}
