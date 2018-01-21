package DominionPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameFlow {
	
	private boolean winCondition = false;
	
	public void drawPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println();
		System.out.println("Here in Draw");
		
		// Draw 5 cards
		int initialDraw = 5;
		for (int i = 0; i < initialDraw; i++) {
			if (playerTurnCounter == 1) {
				if (players.get(0).getDeck().size() == 0)
					restartDeck(players.get(0));
				players.get(0).drawCard(players.get(0).getDeck().get(0));
			} else if (playerTurnCounter == 2) {
				if (players.get(1).getDeck().size() == 0)
					restartDeck(players.get(1));
				players.get(1).drawCard(players.get(1).getDeck().get(0));
			} else if (playerTurnCounter == 3) {
				if (players.get(2).getDeck().size() == 0)
					restartDeck(players.get(2));
				players.get(2).drawCard(players.get(2).getDeck().get(0));
			} else {
				if (players.get(3).getDeck().size() == 0)
					restartDeck(players.get(3));
				players.get(3).drawCard(players.get(3).getDeck().get(0));
			}
		}
		
		actionPhase(kingdoms, playerTurnCounter, players);
	}
	
	public void actionPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Here in Action");
		
		Player player = new Player();
		
		if (playerTurnCounter == 1) {
			player = players.get(0);
			actionPhaseSteps(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 2) {
			player = players.get(1);
			actionPhaseSteps(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 3) {
			player = players.get(2);
			actionPhaseSteps(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 4) {
			player = players.get(3);
			actionPhaseSteps(player, kingdoms, playerTurnCounter, players);
		}
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [y] to enter Buy Phase");
		
		if (scan.hasNextLine()) {
			String choice = scan.nextLine();
			switch (choice) {
				case "y":
					//scan.close();
					buyPhase(kingdoms, playerTurnCounter, players);
					break;
				default:
					//scan.close();
					buyPhase(kingdoms, playerTurnCounter, players);
					break;
			}
		}
	}
	
	public void actionPhaseSteps(Player player, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		// Add each type of card to their respective lists for the player
		availableCards(player);
		
		// Print available actions
		if (player.getAction().size() != 0) {
			printCardsInHand(player);			// Print the cards in the hand
			//printActionsInHand(player);			// Print action cards in hand
			playActionCards(player, kingdoms, playerTurnCounter, players);				// Play the card
		} else
			System.out.println("No available action cards to play");
	}
	
	public void printCardsInHand(Player player) {
		//int i = 0;
		System.out.println();
		System.out.println("Cards in hand:");
		for (Card card : player.getCardsInHand()) {
			System.out.println(card.getName());
			//i++;
		}
		System.out.println();
	}
	
	public void printActionsInHand(Player player) {
		int i = 0;
		System.out.println();
		System.out.println("Action cards in hand:");
		for (Card card : player.getAction()) {
			System.out.println(i + " = " + card.getName());
			i++;
		}
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
			if (choice >= 0 && choice <= player.getAction().size() && player.getNumActions() != 0) {
				Card card = player.getAction().get(choice);
				int IDOfCard = Integer.parseInt(card.getID());
				player.addNumActions(-1);				// Use up an action
				player.addCardToCardsInPlay(card);		// Add the card to play
				for (Card theCard : player.getCardsInHand()) {
					if (card.getName().equals(theCard.getName())) {
						player.removeCardFromAction(card);
						player.removeCardFromHand(card);			// Remove the card from the hand
						break;
					}
				}
				ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();		// Find the card in the map and play it
				System.out.println();
				if (player.getAction().size() != 0) {
					System.out.println("Press -1 to not play any Action cards");
					System.out.println("Press a number between 0 and " + (player.getAction().size() - 1) + " to play another action card");
				} else
					break;
			} else if (choice == -1) {
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
		
		Player player = new Player();
		
		if (playerTurnCounter == 1) {
			player = players.get(0);
			playTreasureCards(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 2) {
			player = players.get(1);
			playTreasureCards(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 3) {
			player = players.get(2);
			playTreasureCards(player, kingdoms, playerTurnCounter, players);
		} else if (playerTurnCounter == 4) {
			player = players.get(3);
			playTreasureCards(player, kingdoms, playerTurnCounter, players);
		}
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [y] to enter Cleanup Phase");
		
		if (scan.hasNextLine()) {
			String choice = scan.nextLine();
			switch (choice) {
				case "y":
					//scan.close();
					cleanupPhase(kingdoms, playerTurnCounter, players);
					break;
				default:
					//scan.close();
					cleanupPhase(kingdoms, playerTurnCounter, players);
					break;
			}
		}
	}
	
	public void printTreasuresInHand(Player player) {
		int i = 0;
		System.out.println();
		System.out.println("Treasures in hand:");
		for (Card card : player.getTreasure()) {
			System.out.println(i + " = " + card);
			i++;
		}
		System.out.println();
	}
	
	public void playTreasureCards(Player player, Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		
		CardEffects ce = new CardEffects();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		
		printTreasuresInHand(player);
		
		System.out.println("Press -2 to not play any treasure cards");
		System.out.println("Press -1 to play all treasures in your hand");
		System.out.println("Press a number between 0 and " + (player.getTreasure().size() - 1) + " to play a treasure card");
		
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			// Choices must be a number between -2 and the size of the player's hand
			if (choice >= 0 && choice <= player.getCardsInHand().size()) {
				Card card = player.getTreasure().get(choice);		// Get the first card in the Treasure list
				int IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the first card in the Treasure list
				player.addCardToCardsInPlay(card);					// Put the treasure in play
				for (Card theCard : player.getCardsInHand()) {
					if (card.getName().equals(theCard.getName())) {
						player.removeCardFromTreasure(card);		// Remove the card from the Treasure list
						player.removeCardFromHand(card);			// Remove the card from the hand
						break;
					}
				}
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
					int IDOfCard = Integer.parseInt(card.getID());		// Get the ID of the first card in the Treasure list
					player.addCardToCardsInPlay(card);					// Put the treasure in play
					for (Card theCard : player.getCardsInHand()) {
						if (card.getName().equals(theCard.getName())) {
							player.removeCardFromTreasure(card);		// Remove the card from the Treasure list
							player.removeCardFromHand(card);			// Remove the card from the hand
							ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, players).run();		// Run the card base on its ID
							break;
						}
					}
				}
				break;
			} else if (choice == -2) {
				break;
			} else {
				System.out.println("Invalid number. Press a number between 0 and " + (player.getCardsInHand().size() - 1) + " to play the card");
				choice = scan.nextInt();
			}
			printTreasuresInHand(player);
		}
		
		System.out.println("Total coins in play: " + player.getExtraCoins());
		buyCards(player, kingdoms);
	}
	
	public void buyCards(Player player, Kingdom kingdoms) {
		List<List<Card>> kingdomChoices = availableCardsToBuy(player, kingdoms);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println("Press -1 to not buy any cards");
		System.out.println("Press a number between 0 and " + (kingdomChoices.size() - 1) + " to buy that kingdom");
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			if (choice >= 0 && choice <= kingdomChoices.size()) {
				Card card = kingdomChoices.get(choice).get(0);
				System.out.println("Adding " + card.getName() + " to discard pile");
				player.addCardToDiscardPile(card);
				kingdoms.removeCardFromSupplyList(card);
				break;
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("Invalid choice. Press -1 to not buy any cards");
				System.out.println("Press a number between 0 and " + (kingdomChoices.size() - 1) + " to buy that kingdom");
				choice = scan.nextInt();
			}
		}
	}
	
	public List<List<Card>> availableCardsToBuy(Player player, Kingdom kingdoms) {
		List<List<Card>> availableToBuy = new ArrayList<List<Card>>();
		int i = 0;
		for (List<Card> kingdom : kingdoms.getSupplyList()) {
			if (Integer.parseInt(kingdom.get(0).getCost()) <= player.getExtraCoins()) {
				System.out.println(i + " = " + kingdom);
				availableToBuy.add(kingdom);
				i++;
			}
		}
		
		return availableToBuy;
	}
	
	public void cleanupPhase(Kingdom kingdoms, int playerTurnCounter, List<Player> players) {
		System.out.println("Here in Cleanup");
		
		/*List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);*/
		
		if (playerTurnCounter == 1) {
			resetBoard(players.get(0));
			playerTurnCounter++;
		} else if (playerTurnCounter == 2) {
			resetBoard(players.get(1));
			playerTurnCounter++;
		} else if (playerTurnCounter == 3) {
			resetBoard(players.get(2));
			playerTurnCounter++;
		} else if (playerTurnCounter == 4) {
			resetBoard(players.get(3));
			playerTurnCounter = 1;
		}
		
		//resetCardsAvailable(players);
		discardHand(players);
		resetNumActionsLeft(players);
		resetNumBuysLeft(players);
		resetExtraCoins(players);
		checkWinCondition(kingdoms);
		
		//return winCondition;
	}
	
	public void discardHand(List<Player> players) {
		for (Player player : players) {
			while (player.getCardsInHand().size() != 0) {
				Card card = player.getCardsInHand().get(0);
				player.addCardToDiscardPile(card);
				player.removeCardFromHand(card);
			}
		}
	}
	
	public void checkWinCondition(Kingdom kingdoms) {
		//boolean winCondition = false;
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
		
		//return winCondition;
	}
	
	public void restartDeck(Player player) {
		while (player.getDiscardPile().size() != 0) {
			player.addCardToDeck(player.getDiscardPile().get(0));
			player.removeCardFromDiscardPile(player.getDiscardPile().get(0));
		}
		
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
	
	/*public void resetCardsAvailable(List<Player> players) {
		for (Player player : players) {
			player.resetCardsAvailable();
		}
	}*/
	
	public void availableCards(Player player) {
		// Search through the hand for an action cards
		Card card = new Card(null, null, null, null, null, null, null, null, null);
		Iterator<Card> itr = player.getCardsInHand().iterator();
		
		while (itr.hasNext()) {
			if (player.getCardsInHand().size() != 0)
				card = itr.next();
			if ("Action".equals(card.getType1())) {
				//player.setActCardsAvailable(true);
				player.addCardToAction(card);
			}
			if ("Treasure".equals(card.getType1()) || "Treasure".equals(card.getType2()) || "Treasure".equals(card.getType3()) || "Treasure".equals(card.getType4())) {
				//player.setCoinCardsAvailable(true);
				player.addCardToTreasure(card);
			}
			if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || "Victory".equals(card.getType3()) || "Victory".equals(card.getType4())) {
				//player.setVicCardsAvailable(true);
				player.addCardToVictory(card);
			}
			if (card.getType1().equals("Curse")) {
				//player.setCurseCardsAvailable(true);
				player.addCardToCurse(card);
			} /*else if (card.getType2().equals("Attack")) {
				attCardsAvailable = true;
			} else if (card.getType2().equals("Duration")) {
				durCardsAvailable = true;
			} else if (card.getType2().equals("Reaction")) {
				reactCardsAvailable = true;
			}*/
		}
	}
	
	public void setWinCondition(boolean winCondition) {
		this.winCondition = winCondition;
	}
	
	public boolean getWinCondition() {
		return winCondition;
	}
}
