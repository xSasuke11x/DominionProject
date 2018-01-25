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
		effects.put(12, () -> Moat(kingdoms, playerTurnCounter, players));
		effects.put(13, () -> Harbinger(kingdoms, playerTurnCounter, players));
		effects.put(14, () -> Merchant(kingdoms, playerTurnCounter, players));
		effects.put(15, () -> Vassal(kingdoms, playerTurnCounter, players));
		effects.put(16, () -> Village(kingdoms, playerTurnCounter, players));
		effects.put(17, () -> Workshop(kingdoms, playerTurnCounter, players));
		effects.put(18, () -> Bureaucrat(kingdoms, playerTurnCounter, players));
		effects.put(19, () -> Gardens(kingdoms, playerTurnCounter, players));
		effects.put(20, () -> Militia(kingdoms, playerTurnCounter, players));
		/*effects.put(21, () -> Moneylender(kingdoms, playerTurnCounter, players, players.get(0), players.get(1), players.get(2), players.get(3)));
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
	
	public void attemptDrawFromDeck(Player player) {
		try {
			Card card = player.getDeck().get(0);
			player.drawCard(card);
			System.out.println("You drew " + card);
			printCardsInHand(player);
		} catch (IndexOutOfBoundsException e) {
			if (player.getDiscardPile().size() > 0) {		// Reshuffle the deck if drawing a card doesn't work
				System.out.println("You have no more cards to draw. Reshuffling deck");
				while (player.getDiscardPile().size() != 0) {
					Card card = player.getDiscardPile().get(0);
					player.addCardToDeck(card);			// Add the card from the discard pile to the deck
					player.removeCardFromDiscardPile(card);			// Remove the card from the discard pile
				}
				Collections.shuffle(player.getDeck());
				Card card = player.getDeck().get(0);
				player.drawCard(card);		// Draw a card
				System.out.println("You drew " + card);
				printCardsInHand(player);
			} else
				System.out.println("You have no more cards to draw and there are no cards left in your discard pile to reshuffle");
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
		System.out.println("Cellar being played");
		System.out.println("Effect: Discard any number of cards. +1 Card per card discarded");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		
		// +1 Action
		player.addNumActions(1);
		
		// Effect
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
			attemptDrawFromDeck(player);
		}
		
		printCardsInHand(player);
	}
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Chapel being played");
		System.out.println("Effect: Trash up to 4 cards from your hand");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// Effect
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
	
	public void Moat(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Moat being played");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		
		for (int i = 0; i < 2; i++) {
			attemptDrawFromDeck(player);
		}
	}
	
	public void Harbinger(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Harbinger being played");
		System.out.println("Effect: Look through your discard pile. You may put a card from it onto your deck");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// +1 Card
		attemptDrawFromDeck(player);
		
		// +1 Action
		player.addNumActions(1);
		
		// Effect
		if (player.getDiscardPile().size() != 0) {
			System.out.println("Look through the discard pile, choose a card and place it onto your deck");
			System.out.println("Press a number between 0 and " + (player.getDiscardPile().size() - 1) + " to place a card from"
					+ " your discard pile onto the deck");
			
			// Print out the cards with the value
			System.out.println("Cards in discard pile:");
			for (int i = 0; i < player.getDiscardPile().size(); i++) {
				System.out.println(i + " = " + player.getDiscardPile().get(i));
			}
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			
			while (scan.hasNext()) {
				int choice = scan.nextInt();
				
				// Remove the chosen card from the hand and add it to the discard pile
				if (choice >= 0 || choice <= player.getDiscardPile().size() - 1) {
					// Get the selected card
					Card card = player.getDiscardPile().get(choice);
					
					// Add the card from the discard pile onto the deck
					player.addCardToDeck(card);
					System.out.println("You selected " + card + " to place onto your deck");
					
					// Remove the card from the discard pile
					player.removeCardFromDiscardPile(card);
					
					break;
				} else {
					System.out.println("Invalid input. Press a number between 0 and " + (player.getDiscardPile().size() - 1) + " to place a card from "
							+ "your discard pile onto the deck");
					
					// Print out the cards with the value
					System.out.println("Cards in discard pile:");
					for (int i = 0; i < player.getDiscardPile().size(); i++) {
						System.out.println(i + " = " + player.getDiscardPile().get(i));
					}
				}
			}
		} else {
			System.out.println();
			System.out.println("Your discard pile is empty");
		}
	}
	
	public void Merchant(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Merchant being played");
		System.out.println("Effect: The first time you play a Silver this turn, +1 Coin");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// +1 Card
		attemptDrawFromDeck(player);	
		
		// +1 Action
		player.addNumActions(1);
	}
	
	public void Vassal(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Vassal being played");
		System.out.println("Effect: Discard the top card of your deck. If it is an Action card, you may play it");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// +2 Coins
		player.addExtraCoins(2);
			
		try {
			Card card = player.getDeck().get(0);	// Get the top card of the deck
			player.addCardToDiscardPile(card);		// Add a copy of the top of the deck to the discard pile
			player.removeCardFromDeck(card);		// Remove the top card of the deck
			System.out.println("You discarded " + card);
			
			if ("Action".equals(card.getType1())) {			// The card discarded is an Action card
				System.out.println(player.getDiscardPile().get(0).getName() + " is an Action card. Would you like to play it? Press [y] for yes or [n] for no.");
				
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				String choice;
				while (scan.hasNext()) {
					choice = scan.nextLine();
					
					// If the player chooses to play the Action card, remove the card from the discard pile and add it to the player's cards in play
					if (choice.toLowerCase().equals("y")) {
						int IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the Action card discarded
						player.addCardToCardsInPlay(card);					// Add a copy of the top of the deck to the discard pile
						player.removeCardFromDiscardPile(card);				// Remove the top card of the deck
						getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();			// Play the Action card
						break;
					} else {
						System.out.println("You chose not to play " + card.getName());
						System.out.println();
						break;
					}
				}
			} else
				System.out.println(card.getName() + " is not an Action card");
		} catch (IndexOutOfBoundsException e) {
			if (player.getDiscardPile().size() > 0) {		// Re-shuffle the deck if drawing a card doesn't work
				System.out.println("You have no more cards to draw. Reshuffling deck");
				while (player.getDiscardPile().size() != 0) {
					Card card = player.getDiscardPile().get(0);
					player.addCardToDeck(card);				// Add the card from the discard pile to the deck
					player.removeCardFromDiscardPile(card);			// Remove the card from the discard pile
				}
				Collections.shuffle(player.getDeck());		// Shuffle the deck
				Card card = player.getDeck().get(0);		// Get the top card of the deck
				player.addCardToDiscardPile(card);			// Add a copy of the top of the deck to the discard pile
				player.removeCardFromDeck(card);			// Remove the top card of the deck
				System.out.println("You discarded " + card);
				
				if ("Action".equals(card.getType1())) {
					System.out.println(player.getDiscardPile().get(0).getName() + " is an Action card. Would you like to play it? Press [y] for yes or [n] for no.");
					
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					String choice;
					while (scan.hasNext()) {
						choice = scan.nextLine();
						
						// If the player chooses to play the Action card, remove the card from the discard pile and add it to the player's cards in play
						if (choice.toLowerCase().equals("y")) {
							int IDOfCard = Integer.parseInt(card.getID());
							player.addCardToCardsInPlay(card);
							player.removeCardFromDiscardPile(card);
							getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();
							break;
						} else {
							System.out.println("You chose not to play " + card.getName());
							System.out.println();
							break;
						}
					}
				} else
					System.out.println(card.getName() + " is not an Action card");
			} else
				System.out.println("You have no more cards to draw and there are no cards left in your discard pile to reshuffle");
		}
	}
	
	public void Village(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Village being played");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
			
		// +1 Card
		attemptDrawFromDeck(player);
			
		// +2 Actions
		player.addNumActions(2);
	}
	
	public void Workshop(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Workshop being played");
		System.out.println("Effect: Gain a card costing up to 4 Coins");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		
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
		int i = 0;
		System.out.println("The following are the 4-cost kingdoms:");
		for (List<Card> kingdom : fourCostKingdoms) {
			System.out.println(i + " = " + kingdom.get(0));
			i++;
		}
		
		// Let the player choose a kingdom
		System.out.println("Press a number between 0 and " + (fourCostKingdoms.size() - 1) + " to gain the card");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			// Choices must be a number between 0 and the size of the 4-cost kingdoms list
			if (choice > 0 && choice <= fourCostKingdoms.size() - 1) {
				Card card = fourCostKingdoms.get(choice).get(0);
				
				// Add the card from the kingdom to the discard pile
				player.addCardToDiscardPile(card);
				System.out.println("You gained a " + card.getName());
				
				// Remove the card from the kingdom and set how many cards are left in the supply
				kingdoms.removeCardFromSupplyList(card);
				int numLeft = Integer.parseInt(card.getNumLeft());
				card.setNumLeft(Integer.toString(numLeft - 1));
				break;
			} else {
				System.out.println("Invalid number. Press a number between 0 and " + (fourCostKingdoms.size() - 1) + " to gain the card");
			}
		}
	}
	
	public void Bureaucrat(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Bureaucrat being played");
		System.out.println("Effect: Each other player reveals a Victory card from his hand and puts it on his deck (or reveals a hand with no Victory cards)");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		List<Player> sublistPlayers = new ArrayList<Player>();
		int turnCheck = 0;
		
		// Add each player to a new list who isn't the current player
		for (Player aPlayer : players) {
			if (aPlayer.getTurnCounter() != player.getTurnCounter()) {
				sublistPlayers.add(aPlayer);		// Add players to a new sublist that aren't the current player's turn
			}
		}
		
		// Gain a Silver card
		for (List<Card> kingdom : kingdoms.getSupplyList()) {
			if (Integer.parseInt(kingdom.get(0).getNumLeft()) > 0 && "Silver".equals(kingdom.get(0).getName())) {
				Card card = kingdom.get(0);
				
				player.addCardToDeck(card);			// Place the Silver onto the deck
				System.out.println("You gained a Silver card");
				
				kingdoms.removeCardFromSupplyList(card);		// Remove the Silver from the supply list
				break;
			}
		}
		
		// Iterate through each other player based on turn number
		for (Player thePlayer : sublistPlayers) {
			int i = 0;
			System.out.println("This is Player " + thePlayer.getTurnCounter());
			
			if (thePlayer.getVictory().size() > 0) {
				// Print out the Victory cards in hand
				System.out.println("Here are the victory cards in player " + thePlayer.getTurnCounter() + "'s hand:");
				for (Card card : thePlayer.getVictory()) {
					System.out.println(i + " = " + card);
					i++;
				}
				
				System.out.println("Press a number between 0 and " + (thePlayer.getVictory().size() - 1) + " to place the card back onto the deck");
				
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				int choice;
				while (scan.hasNext()) {
					choice = scan.nextInt();
					
					if (choice >= 0 && choice <= thePlayer.getVictory().size() - 1) {
						Card returnedCard = thePlayer.getVictory().get(choice);		// Get the selected Victory card in the hand
						thePlayer.addCardToDeck(returnedCard);				// Put a Victory card from the hand onto the deck
						System.out.println("You placed " + returnedCard.getName() + " back onto the deck");
						thePlayer.removeCardFromHand(returnedCard);			// Remove the Victory card from the hand and remove it from the type list
						turnCheck++;
						break;
					} else {
						System.out.println("Invalid choice. Press a number between 0 and " + (thePlayer.getVictory().size() - 1) 
								+ " to place the card back onto the deck.");
					}
				}
			} else {
				System.out.println("Player " + thePlayer.getTurnCounter() + " has no Victory cards");
				System.out.println("Revealing " + thePlayer.getTurnCounter() + "'s hand now");
				printCardsInHand(thePlayer);
				turnCheck++;
			}
			if ((player.getNumPlayers() == 2 && turnCheck == 1) || (player.getNumPlayers() == 3 && turnCheck == 2) 
					|| (player.getNumPlayers() == 4 && turnCheck == 3))
				break;		// Exit if all the players have returned a card or has revealed their hand
		}
	}
	
	public void Gardens(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Gardens being played");
		System.out.println("Effect: Worth 1 Victory Point for every 10 cards in your deck (rounded down)");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		
		// Set totalNumCards to the sum of the cards in the deck - all cards will have been sent back to the deck by other functions at the end of the game
		int totalNumCards = player.getDeck().size();
		
		// Add the total to the extra victory points for that player
		player.addExtraVictoryPoints((int)Math.floor(totalNumCards / 10));
		System.out.println("Player " + player.getTurnCounter() + " gained " + (int)Math.floor(totalNumCards / 10) + " Victory points");
	}
	
	public void Militia(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Militia being played");
		System.out.println("Effect: Each other player discards down to 3 cards in hand");
		System.out.println();
		
		Player player = players.get(playerTurnCounter - 1);
		List<Player> sublistPlayers = new ArrayList<Player>();
		int turnCheck = 0;
		
		// +2 Coins
		player.addExtraCoins(2);
		
		// Add each player to a new list who isn't the current player
		for (Player aPlayer : players) {
			if (aPlayer.getTurnCounter() != player.getTurnCounter()) {
				sublistPlayers.add(aPlayer);		// Add players to a new sublist that aren't the current player's turn
			}
		}
		
		// Iterate through each other player based on turn number
		for (Player thePlayer : sublistPlayers) {
			if (thePlayer.getCardsInHand().size() < 4) {
				System.out.println("Player " + thePlayer.getTurnCounter() + " has at most 3 cards in their hand");
				continue;
			}
			
			System.out.println("This is Player " + thePlayer.getTurnCounter() + "'s hand");
			while (thePlayer.getCardsInHand().size() > 3) {
				if (thePlayer.getCardsInHand().size() > 0) {
					// Print out the other player's hand
					printCardsInHand(thePlayer);
					System.out.println();
					System.out.println("Press a number between 0 and " + (thePlayer.getCardsInHand().size() - 1) + " to discard that card");
					
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					int choice;
					while (scan.hasNext()) {
						choice = scan.nextInt();
						
						if (choice >= 0 && choice <= thePlayer.getCardsInHand().size() - 1) {
							Card card = thePlayer.getCardsInHand().get(choice);		// Get the selected card in the hand
							thePlayer.addCardToDiscardPile(card);				// Put a card from the hand to the discard pile
							System.out.println("Player " + thePlayer.getTurnCounter() + " chose to discard " + card.getName());
							thePlayer.removeCardFromHand(card);			// Remove the card from the hand and remove it from the type list
							if (thePlayer.getCardsInHand().size() > 3) {
								// Print out the other player's hand
								printCardsInHand(thePlayer);
								System.out.println();
								System.out.println("Press a number between 0 and " + (thePlayer.getCardsInHand().size() - 1) + " to discard that card");
							}
							else {
								turnCheck++;		// The player is done so increase turnCheck to indicate they are finished
								break;
							}
						} else {
							System.out.println("Invalid choice. Press a number between 0 and " + (thePlayer.getCardsInHand().size() - 1) + " to discard that card");
						}
					}
				}
			}
			printCardsInHand(thePlayer);
			if (thePlayer.getCardsInHand().size() < 4) {
				System.out.println("Player " + thePlayer.getTurnCounter() + " now has at most 3 cards in their hand");
			}
			if ((player.getNumPlayers() == 2 && turnCheck == 1) || (player.getNumPlayers() == 3 && turnCheck == 2) 
					|| (player.getNumPlayers() == 4 && turnCheck == 3)) {
				System.out.println("All players have discarded down to at most 3 cards in their hand");
				break;		// Exit if all the players have discarded down to 3 cards in their hand
			}
		}
	}
}
