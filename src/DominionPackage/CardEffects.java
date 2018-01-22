package DominionPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardEffects {
	
	public Runnable getCardEffect(int IDOfCard, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		Map<Integer, Runnable> effects = new HashMap<>();
		
		effects.put(1, () -> Copper(kingdoms, playerTurnCounter, players));
		effects.put(2, () -> Silver(kingdoms, playerTurnCounter, players));
		effects.put(3, () -> Gold(kingdoms, playerTurnCounter, players));
		effects.put(4, () -> Platinum(kingdoms, playerTurnCounter, players));
		effects.put(10, () -> Cellar(kingdoms, playerTurnCounter, players));
		effects.put(11, () -> Chapel(kingdoms, playerTurnCounter, players));
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
	
	public void printCardsInHand(Player player) {
		System.out.println("Cards in hand:");
		for (int i = 0; i < player.getCardsInHand().size(); i++) {
			System.out.println(i + " = " + player.getCardsInHand().get(i).getName());
		}
	}
	
	public void Copper(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Copper being played");
		if (playerTurnCounter == 1) {
			players.get(0).addExtraCoins(1);
		} else if (playerTurnCounter == 2) {
			players.get(1).addExtraCoins(1);
		} else if (playerTurnCounter == 3) {
			players.get(2).addExtraCoins(1);
		} else
			players.get(3).addExtraCoins(1);
	}
	
	public void Silver(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Silver being played");
		if (playerTurnCounter == 1) {
			players.get(0).addExtraCoins(2);
		} else if (playerTurnCounter == 2) {
			players.get(1).addExtraCoins(2);
		} else if (playerTurnCounter == 3) {
			players.get(2).addExtraCoins(2);
		} else
			players.get(3).addExtraCoins(2);
	}
	
	public void Gold(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Gold being played");
		if (playerTurnCounter == 1) {
			players.get(0).addExtraCoins(3);
		} else if (playerTurnCounter == 2) {
			players.get(1).addExtraCoins(3);
		} else if (playerTurnCounter == 3) {
			players.get(2).addExtraCoins(3);
		} else
			players.get(3).addExtraCoins(3);
	}
	
	public void Platinum(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Platinum being played");
		if (playerTurnCounter == 1) {
			players.get(0).addExtraCoins(5);
		} else if (playerTurnCounter == 2) {
			players.get(1).addExtraCoins(5);
		} else if (playerTurnCounter == 3) {
			players.get(2).addExtraCoins(5);
		} else
			players.get(3).addExtraCoins(5);
	}
	
	public void Cellar(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Playing Cellar");
		
		Player player = players.get(playerTurnCounter - 1);
		
		// +1 Action
		player.addNumActions(1);
		
		// Effect
		System.out.println("Discard card(s) and redraw the same amount.");
		// Print out the cards with the value
		printCardsInHand(player);
		System.out.println("Press -1 to not discard cards");
		System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to discard the card");
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int numDiscard = 0;
		
		// Don't do anything if there are no cards in the hand
		if (player.getCardsInHand().size() == 0) {
			System.out.println("Your hand is empty");
			System.out.println();
		}
		
		while (player.getCardsInHand().size() != 0) {
			int choice = scan.nextInt();
			
			// Remove the chosen card from the hand and add it to the discard pile
			if (choice == -1) {
				System.out.println("You chose not to discard a card");
				System.out.println();
				break;
			} else if (choice >= 0 && choice <= player.getCardsInHand().size() - 1 && player.getCardsInHand().size() != 0) {
				// Get the selected card
				Card card = player.getCardsInHand().get(choice);
				
				// Remove the card from the hand
				System.out.println("You chose to remove " + card.getName() + " from your hand");
				player.removeCardFromHand(card);
				
				// Remove the card from the type list
				//player.removeCardFromTypeList(card);
				
				// Add the card that was removed from the hand to the discard pile
				player.addCardToDiscardPile(card);
				
				numDiscard++;
				
				// Print out the cards with the value
				if (player.getCardsInHand().size() != 0) {
					printCardsInHand(player);
					System.out.println("Press -1 to not discard cards");
					System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to discard the card");
				} else {
					System.out.println("Your hand is now empty");
					System.out.println();
					break;
				}
			} else {
				System.out.println("That is not a valid choice. Press -1 to not discard cards");
				System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to discard the card");
				choice = scan.nextInt();
			}
		}
		
		for (int i = 0; i < numDiscard; i++) {
			// Draw the top card from the deck and remove it from the deck
			if (player.getDeck().size() != 0) {
				Card card = player.getDeck().get(0);
				player.drawCard(card);
				System.out.println("You drew " + card);
			} else {
				System.out.println("Your deck is empty. Reshuffling now");
				while (player.getDiscardPile().size() != 0) {
					Card card = player.getDiscardPile().get(0);
					player.addCardToDeck(card);
					player.removeCardFromDiscardPile(card);
				}
				Collections.shuffle(player.getDeck());
				Card card2 = player.getDeck().get(0);
				player.drawCard(card2);
				System.out.println("You drew " + card2);
			}
		}
		
		printCardsInHand(player);
	}
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Playing Chapel");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// Effect
		System.out.println("Trash card(s) up to 4");
		
		// Do nothing if the player has no cards in their hand
		if (player.getCardsInHand().size() == 0)
			System.out.println("You have no cards in your hand to trash");
		
		System.out.println("Press -1 to not trash any cards");
		System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to trash that card");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		
		for (int i = 0; i < 4 && player.getCardsInHand().size() != 0; i++) {
			// Print out the cards in the player's hand
			printCardsInHand(player);
			
			choice = scan.nextInt();
			
			// Remove the chosen card from the hand and add it to the discard pile
			if (choice == -1) {
				System.out.println("You chose not to trash a card");
				break;
			} else if (choice >= 0 && choice <= player.getCardsInHand().size() - 1) {
				
				// Get the selected card
				Card card = player.getCardsInHand().get(choice);
				
				// Remove the card from the hand and remove it from the type list
				player.removeCardFromHand(card);
				System.out.println("You trashed " + card.getName());
				
				// Remove the card from the type list
				//player.removeCardFromTypeList(card);
				
				// Add the card that was removed from the hand to the trash pile
				kingdoms.trash.add(card);
				
				if (player.getCardsInHand().size() != 0) {
					System.out.println("Press -1 to not trash any more cards");
					System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to trash another card");
				}	
			} else {
				System.out.println("That is not a valid choice. Press -1 to not trash any cards");
				System.out.println("Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to trash that card");
				choice = scan.nextInt();
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
				
				@SuppressWarnings("resource")
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
	
	public void Workshop(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
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
			@SuppressWarnings("resource")
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
	
	public void Bureaucrat(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
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
				Card returnedCard = new Card(null, null, null, null, null, null, null, null, null);
				
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
				
				@SuppressWarnings("resource")
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
	
	public void Gardens(Kingdom kingdoms, int playerTurnCounter, List<Player> players, Player player1, Player player2, Player player3, Player player4) {
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
}
