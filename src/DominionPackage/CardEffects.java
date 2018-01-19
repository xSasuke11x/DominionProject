package DominionPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardEffects {
	
	public Runnable getCardEffect(int IDOfCard, Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		Map<Integer, Runnable> effects = new HashMap<>();
		
		effects.put(1, () -> Copper(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(2, () -> Silver(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(3, () -> Gold(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(4, () -> Platinum(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(10, () -> Cellar(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(11, () -> Chapel(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(12, () -> Moat(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(13, () -> Harbinger(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(14, () -> Merchant(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(15, () -> Vassal(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(16, () -> Village(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(17, () -> Workshop(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(18, () -> Bureaucrat(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(19, () -> Gardens(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(20, () -> Militia(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(21, () -> Moneylender(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(22, () -> Poacher(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(23, () -> Remodel(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		effects.put(24, () -> Smithy(kingdoms, playerTurnCounter, player1, player2, player3, player4));
		/*effects.put(25, () -> ThroneRoom(kingdoms, playerTurnCounter, player1, player2, player3, player4));
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

	public void Copper(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Copper being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(1);
		}
	}
	
	public void Silver(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Silver being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(2);
		}
	}
	
	public void Gold(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Gold being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(3);
		}
	}
	
	public void Platinum(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Platinum being played");
		if (playerTurnCounter == 1) {
			player1.addExtraCoins(5);
		}
	}
	
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
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
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
					System.out.println("That is not a valid choice. Choose a number between 0 (cancel effect) and " + player1.getDiscardPile().size());
					choice = scan.nextInt();
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
			if ("Action".equals(card2.getType1())) {
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
					choice = scan.nextLine();
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
						choice = scan.nextLine();
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
			player1.addExtraVictoryPoints((int)Math.floor(totalNumCards / 10));
		} else if (playerTurnCounter == 2) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player2.getCardsInHand().size() + player2.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player2.addExtraVictoryPoints((int)Math.floor(totalNumCards / 10));
		} else if (playerTurnCounter == 3) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player3.getCardsInHand().size() + player3.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player3.addExtraVictoryPoints((int)Math.floor(totalNumCards / 10));
		} else if (playerTurnCounter == 4) {
			
			// Set totalNumCards to the sum of the cards in the deck and the hand 
			int totalNumCards = player4.getCardsInHand().size() + player4.getDeck().size();
			
			// Add the total to the extra victory points for that player
			player4.addExtraVictoryPoints((int)Math.floor(totalNumCards / 10));
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
						System.out.println("Invalid choice. Press a number between 1 and " + player2.getCardsInHand().size() 
								+ " to place the card back onto the deck.");
						choice = scan.nextLine();
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
	
	public void Poacher(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
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
	
	public void Remodel(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Effect
			System.out.println("Trash a card in your hand. Gain a card costing up to +2 Coins more than it.");
			
			if (player1.getCardsInHand().size() > 0) {
				System.out.println("Choose a number between 1 and " + player1.getCardsInHand().size() + " to trash.");
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
	
	public void Smithy(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				player1.drawCard(player1.getDeck().get(0));
			}
		} else if (playerTurnCounter == 2) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				player2.drawCard(player2.getDeck().get(0));
			}
		} else if (playerTurnCounter == 3) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				player3.drawCard(player3.getDeck().get(0));
			}
		} else if (playerTurnCounter == 4) {
			
			// +3 Cards
			for (int i = 0; i < 3; i++) {
				player4.drawCard(player4.getDeck().get(0));
			}
		}
	}
	
	/*public void ThroneRoom(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			player1.addNumActions(1);
			
		}
	}*/
}
