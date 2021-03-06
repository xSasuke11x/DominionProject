package DominionPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameFlow {
	
	private boolean winCondition = false;
	
	public void drawPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println();
		System.out.println("Here in Draw");
		
		Player player = players.get(playerTurnCounter - 1);
		
		// Draw 5 cards
		int initialDraw = 5;
		for (Player aPlayer : players) {
			if (aPlayer.getCardsInHand().size() == 0) {
				for (int i = 0; i < initialDraw; i++) {
					Collections.reverse(aPlayer.getDeck());
					try {
						Card card = aPlayer.getDeck().get(0);
						aPlayer.drawCard(card);
					} catch (IndexOutOfBoundsException e) {
						if (aPlayer.getDiscardPile().size() > 0) {
							System.out.println("You have no more cards to draw. Reshuffling deck");
							restartDeck(aPlayer);
							Card card = aPlayer.getDeck().get(0);
							aPlayer.drawCard(card);
						} else {
							if (aPlayer.getCardsInHand().size() == 0) {		// If Player 3 or Player 4 is not in the game
								break;
							} else {
								System.out.println("You have no more cards to draw and there are no cards left in your discard pile to reshuffle");
								break;
							}
						}
					}
				}
			}
		}
		
		// Print the cards in the hand
		printCardsInHand(player);
		
		actionPhase(kingdoms, playerTurnCounter, players);
	}
	
	public void printCardsInHand(Player player) {
		System.out.println();
		System.out.println("Cards in hand:");
		for (Card card : player.getCardsInHand()) {
			System.out.println(card.getName());
		}
		System.out.println();
	}
	
	public void actionPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Here in Action");
		
		Player player = players.get(playerTurnCounter - 1);
		actionPhaseSteps(player, kingdoms, playerTurnCounter, players);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [y] to enter Buy Phase");
		
		if (scan.hasNextLine()) {
			String choice = scan.nextLine();
			switch (choice) {
				case "y":
					buyPhase(kingdoms, playerTurnCounter, players);
					break;
				default:
					buyPhase(kingdoms, playerTurnCounter, players);
					break;
			}
		}
	}
	
	public void actionPhaseSteps(Player player, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		// Print available actions
		if (player.getAction().size() != 0) {
			printCardsInHand(player);			// Print the cards in the hand
			playActionCards(player, kingdoms, playerTurnCounter, players);				// Play the card
		} else
			System.out.println("No available action cards to play");
	}
	
	public void printActionsInHand(Player player) {
		int i = 0;
		//printCardsInHand(player);
		if (player.getAction().size() != 0) {
			System.out.println("Action cards in hand:");
			for (Card card : player.getAction()) {
				System.out.println(i + " = " + card.getName());
				i++;
			}
		} else if (player.getNumActions() == 0)
			System.out.println("You have no Actions left to play Action cards");
		else if (player.getAction().size() != 0)
			System.out.println("You have no Action cards to play");
		System.out.println();
	}
	
	public void playActionCards(Player player, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		CardEffects ce = new CardEffects();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		
		printActionsInHand(player);
		
		System.out.println("Press -1 to not play an Action card");
		System.out.println("Press a number between 0 and " + (player.getAction().size() - 1) + " to play the action card");
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			// Choices must be a number between -1 and the size of the number of actions in the player's hand
			if (choice >= 0 && choice <= player.getAction().size() && player.getNumActions() > 0) {
				while (player.getAction().size() != 0) {
					if (choice == -1)
						break;
					
					Card card = player.getAction().get(choice);
					int IDOfCard = Integer.parseInt(card.getID());
					player.addNumActions(-1);				// Use up an action
					player.addCardToCardsInPlay(card);		// Add the card to play
					player.removeCardFromHand(card);		// Remove the card from the hand and remove it from the type list
					ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();		// Find the card in the map and play it
					
					System.out.println();
					if (player.getAction().size() != 0 && player.getNumActions() > 0) {
						printActionsInHand(player);
						System.out.println("Press -1 to not play any Action cards");
						System.out.println("Press a number between 0 and " + (player.getAction().size() - 1) + " to play another action card");
						System.out.println("Number of Actions left: " + player.getNumActions());
						System.out.println();
						choice = scan.nextInt();
					} else {
						System.out.println("You have no Action cards to play and/or you ran out of Actions to play Action cards");
						System.out.println();
						break;
					}
				}
				break;
			} else if (choice == -1) {
				System.out.println("You chose not to play an Action card");
				break;
			} else {
				System.out.println("Invalid number. Press a number between 0 and " + (player.getAction().size() - 1) + " to play the card");
				choice = scan.nextInt();
			}
			printActionsInHand(player);
		}
	}
	
	public void buyPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Here in Buy");
		
		Player player = players.get(playerTurnCounter - 1);
		playTreasureCards(player, kingdoms, playerTurnCounter, players);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [y] to enter Cleanup Phase");
		
		if (scan.hasNextLine()) {
			String choice = scan.nextLine();
			switch (choice) {
				case "y":
					cleanupPhase(kingdoms, playerTurnCounter, players);
					break;
				default:
					cleanupPhase(kingdoms, playerTurnCounter, players);
					break;
			}
		}
	}
	
	public void playTreasureCards(Player player, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		
		CardEffects ce = new CardEffects();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice, IDOfCard = 0;
		
		printTreasuresInHand(player);
		
		if (player.getTreasure().size() != 0) {
			System.out.println("Press -2 to not play any treasure cards");
			System.out.println("Press -1 to play all treasures in your hand");
			System.out.println("Press a number between 0 and " + (player.getTreasure().size() - 1) + " to play a treasure card");
		} else
			System.out.println("You have no Treasure cards in your hand. Press -2 to continue");
		
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			// Choices must be a number between -2 and the size of the player's hand
			if (choice >= 0 && choice <= player.getTreasure().size() - 1) {
				Card card = player.getTreasure().get(choice);		// Get the first card in the Treasure list
				IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the first card in the Treasure list
				player.addCardToCardsInPlay(card);			// Put the treasure in play
				player.removeCardFromHand(card);			// Remove the card from the hand
				ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();
				System.out.println();
				if (player.getTreasure().size() != 0) {
					System.out.println("Press -2 to not play any treasure cards");
					System.out.println("Press -1 to play all treasures in your hand");
					System.out.println("Press a number between 0 and " + (player.getTreasure().size() - 1) + " to play another treasure card");
				} else
					break;
			} else if (choice == -1) {
				while (player.getTreasure().size() != 0) {
					Card card = player.getTreasure().get(0);			// Get the first card in the Treasure list
					IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the first card in the Treasure list
					player.addCardToCardsInPlay(card);			// Put the treasure in play
					player.removeCardFromHand(card);			// Remove the card from the hand
					ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();		// Run the card base on its ID
				}
				break;
			} else if (choice == -2) {
				System.out.println("You either chose not to player any Treasure cards or have no Treasure cards to play");
				break;
			} else {
				System.out.println("Invalid number. Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to play the card");
				choice = scan.nextInt();
			}
			printTreasuresInHand(player);
		}
		
		// If Merchant was played, add an extra coin
		int silverCounter = 0;
		int merchantCounter = 0;
		boolean merchantPlayed = false;
		for (Card card : player.getCardsInPlay()) {
			if ("Merchant".equals(card.getName())) {
				merchantPlayed = true;
				merchantCounter++;
			}
			if ("Silver".equals(card.getName())) {
				silverCounter++;
			}
		}
		if (silverCounter > 0 && merchantPlayed == true)
			player.addExtraCoins(1 * merchantCounter);
		
		System.out.println("Total coins in play: " + player.getExtraCoins());
		System.out.println("Total buys left: " + player.getNumBuys());
		System.out.println();
		buyCards(player, kingdoms);
	}
	
	public void printTreasuresInHand(Player player) {
		int i = 0;
		System.out.println();
		if (player.getTreasure().size() != 0) {
			System.out.println("Treasures in hand:");
			for (Card card : player.getTreasure()) {
				System.out.println(i + " = " + card);
				i++;
			}
			System.out.println();
		} else
			System.out.println("You have no Treasure cards to play");
	}
	
	public void buyCards(Player player, Kingdom kingdoms) {
		List<List<Card>> kingdomChoices = availableCardsToBuy(player, kingdoms);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println();
		System.out.println("Press -1 to not buy any cards");
		System.out.println("Press a number between 0 and " + (kingdomChoices.size() - 1) + " to buy that kingdom");
		while (scan.hasNext()) {
			if (player.getNumBuys() == 0)
				break;
			
			choice = scan.nextInt();
			
			if (choice >= 0 && choice <= kingdomChoices.size() && Integer.parseInt(kingdomChoices.get(choice).get(0).getNumLeft()) > 0) {
				Card card = kingdomChoices.get(choice).get(0);
				System.out.println("Adding " + card.getName() + " to discard pile");
				player.addCardToDiscardPile(card);		// Add the bought card to the discard pile
				kingdoms.removeCardFromSupplyList(card);		// Remove the card from the supply
				int numLeft = Integer.parseInt(card.getNumLeft());
				card.setNumLeft(Integer.toString(numLeft - 1));		// Manually reduce the number of cards left by 1
				player.addNumBuys(-1);		// Reduce the number of buys this turn by 1
				break;
			} else if (choice == -1) {
				System.out.println("You chose not to buy anything");
				break;
			} else {
				System.out.println("Invalid choice. Press -1 to not buy any cards");
				System.out.println("Press a number between 0 and " + (kingdomChoices.size() - 1) + " to buy that kingdom");
			}
		}
	}
	
	public List<List<Card>> availableCardsToBuy(Player player, Kingdom kingdoms) {
		List<List<Card>> availableToBuy = new ArrayList<List<Card>>();
		int i = 0;
		for (List<Card> kingdom : kingdoms.getSupplyList()) {
			if (kingdom.size() != 0) {
				if (Integer.parseInt(kingdom.get(0).getCost()) <= player.getExtraCoins()) {
					System.out.println(i + " = " + kingdom.get(0));
					availableToBuy.add(kingdom);
					i++;
				}
			}
		}
		
		return availableToBuy;
	}
	
	public void cleanupPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Here in Cleanup");
		
		if (playerTurnCounter == 1) {
			resetBoard(players.get(0));
			discardHand(players.get(0));
		} else if (playerTurnCounter == 2) {
			resetBoard(players.get(1));
			discardHand(players.get(1));
		} else if (playerTurnCounter == 3) {
			resetBoard(players.get(2));
			discardHand(players.get(2));
		} else if (playerTurnCounter == 4) {
			resetBoard(players.get(3));
			discardHand(players.get(3));
		}
		
		resetNumActionsLeft(players);
		resetNumBuysLeft(players);
		resetExtraCoins(players);
		checkWinCondition(kingdoms, players);
	}
	
	public void discardHand(Player player) {
		while (player.getCardsInHand().size() != 0) {
			Card card = player.getCardsInHand().get(0);
			player.addCardToDiscardPile(card);
			player.removeCardFromHand(card);
		}
	}
	
	public void checkWinCondition(Kingdom kingdoms, List<Player> players) {
		int supplyCount = 0;
		
		if (kingdoms.province.size() == 0)
			setWinCondition(true);
		else if (kingdoms.colony.size() == 0)
			setWinCondition(true);
		else {
			for (List<Card> kingdom : kingdoms.getSupplyList()) {
				if (kingdom.size() == 0)
					supplyCount++;
			}
			if (supplyCount == 3) {
				setWinCondition(true);
			}
		}
		
		if (getWinCondition() == true) {
			System.out.println("Game over!");
			
			// Return all players' cards in their hand to the deck
			System.out.println("Returning all cards in all players' hands back to their deck");
			for (Player player : players) {
				while (player.getCardsInHand().size() != 0) {
					Card card = player.getCardsInHand().get(0);
					player.addCardToDeck(card);
					player.removeCardFromHand(card);
				}
			}
			
			// Return all players' cards in their discard pile to the deck
			System.out.println("Returning all cards in all players' discard pile back to their deck");
			for (Player player : players) {
				while (player.getDiscardPile().size() != 0) {
					Card card = player.getDiscardPile().get(0);
					player.addCardToDeck(card);
					player.removeCardFromDiscardPile(card);
				}
			}
			
			// Calculate Victory Points
			System.out.println("Sorting each players' deck by the card's ID number");
			for (Player player : players) {
				//player.getDeck().sort(Comparator.comparing(Card::getID));
				for (Card card : player.getDeck()) {
					if ("Estate".equals(card.getName()))
						player.addExtraVictoryPoints(1);
					else if ("Duchy".equals(card.getName()))
						player.addExtraVictoryPoints(3);
					else if ("Province".equals(card.getName()))
						player.addExtraVictoryPoints(6);
					else if ("Colony".equals(card.getName()))
						player.addExtraVictoryPoints(10);
					else if ("Gardens".equals(card.getName())) {
						CardEffects ce = new CardEffects();
						int IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the Action card discarded
						ce.getCardEffect(IDOfCard, kingdoms, player.getTurnCounter(), players).run();			// Play the Action card
					}
				}
			}
			announceWinner(players);
		}
	}
	
	public void announceWinner(List<Player> players) {
		int highestScore = 0, count = 0;
		List<Player> tempPlayers = new ArrayList<Player>();
		
		for (Player player : players) {
			if (player.getExtraVictoryPoints() >= highestScore) {
				highestScore = player.getExtraVictoryPoints();
				//System.out.println("TEST: score = " + highestScore);
				tempPlayers.add(player);
			}
			count++;
			if (count == 4) {
				if (tempPlayers.size() == 1)
					System.out.println("The winner is Player " + tempPlayers.get(0).getTurnCounter() + " with " + highestScore + " points");
				else {
					System.out.println("The winners are:");
					for (Player aPlayer : tempPlayers)
						System.out.println("Player " + aPlayer.getTurnCounter());
				}
			}
		}
	}
	
	public void restartDeck(Player player) {
		while (player.getDiscardPile().size() != 0) {
			Card card = player.getDiscardPile().get(0);
			player.addCardToDeck(card);
			player.removeCardFromDiscardPile(card);
		}
		Collections.shuffle(player.getDeck());
		System.out.println("Your deck has been shuffled");
	}
	
	public void resetBoard(Player player) {
		while (player.getCardsInPlay().size() != 0) {
			player.addCardToDiscardPile(player.getCardsInPlay().get(0));
			player.removeCardFromPlay(player.getCardsInPlay().get(0));
		}
	}
	
	public void resetNumActionsLeft(List<Player> players) {
		for (Player player : players) {
			player.resetNumActions();
		}
	}
	
	public void resetNumBuysLeft(List<Player> players) {
		for (Player player : players) {
			player.resetNumBuys();
		}
	}
	
	public void resetExtraCoins(List<Player> players) {
		for (Player player : players) {
			player.resetExtraCoins();
		}
	}
	
	public void setWinCondition(boolean winCondition) {
		this.winCondition = winCondition;
	}
	
	public boolean getWinCondition() {
		return winCondition;
	}
}
