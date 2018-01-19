package DominionPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameFlow {
	
	public void drawPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println();
		System.out.println("Here in Draw");
		
		// Draw 5 cards
		int initialDraw = 5;
		for (int i = 0; i < initialDraw; i++) {
			if (playerTurnCounter == 1) {
				if (player1.getDeck().size() == 0)
					restartDeck(player1);
				player1.drawCard(player1.getDeck().get(0));
			} else if (playerTurnCounter == 2) {
				if (player2.getDeck().size() == 0)
					restartDeck(player2);
				player2.drawCard(player2.getDeck().get(0));
			} else if (playerTurnCounter == 3) {
				if (player3.getDeck().size() == 0)
					restartDeck(player3);
				player3.drawCard(player3.getDeck().get(0));
			} else {
				if (player4.getDeck().size() == 0)
					restartDeck(player4);
				player4.drawCard(player4.getDeck().get(0));
			}
		}
		
		actionPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
	}
	
	public void actionPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Here in Action");
		
		Player player = new Player();
		
		if (playerTurnCounter == 1) {
			player = player1;
			actionPhaseSteps(player, kingdoms, playerTurnCounter, player1, player2, player3, player4);
		} else if (playerTurnCounter == 2) {
			player = player2;
			actionPhaseSteps(player, kingdoms, playerTurnCounter, player1, player2, player3, player4);
		} else if (playerTurnCounter == 3) {
			player = player3;
			actionPhaseSteps(player, kingdoms, playerTurnCounter, player1, player2, player3, player4);
		} else if (playerTurnCounter == 4) {
			player = player4;
			actionPhaseSteps(player, kingdoms, playerTurnCounter, player1, player2, player3, player4);
		}
		
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
	
	public void actionPhaseSteps(Player player, Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		// What type of cards in the deck are available
		availableCards(player);
		
		// Print available actions
		if (player.getAction().size() != 0) {
			printCardsInHand(player);			// Print the cards in the hand
			printActionsInHand(player);			// Print action cards in hand
			playActionCard(player, kingdoms, playerTurnCounter, player1, player2, player3, player4);				// Play the card
		} else
			System.out.println("No available action cards to play");
	}
	
	public void printCardsInHand(Player player) {
		int i = 1;
		System.out.println();
		System.out.println("Cards in hand:");
		for (Card card : player.getCardsInHand()) {
			System.out.println(i + " = " + card.getName());
			i++;
		}
		System.out.println();
	}
	
	public void printActionsInHand(Player player) {
		int i = 1;
		System.out.println();
		System.out.println("Action cards in hand:");
		for (Card card : player.getAction()) {
			System.out.println(i + " = " + card.getName());
			i++;
		}
		System.out.println();
	}
	
	public void playActionCard(Player player, Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		CardEffects ce = new CardEffects();
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println("Press a number between 1 and " + player.getCardsInHand().size() + " to play the action card");
		while (scan.hasNext()) {
			choice = scan.nextInt();
			
			// Choices must be a number between 1 and the size of the player's hand
			if (choice > 0 && choice <= player.getCardsInHand().size() && player.getNumActions() != 0) {
				int IDOfCard = Integer.parseInt(player.getCardsInHand().get(choice).getID());
				player.addNumActions(-1);
				ce.getCardEffect(IDOfCard, kingdoms, playerTurnCounter, player1, player2, player3, player4).run();
			} else {
				System.out.println("Invalid number. Press a number between 1 and " + player.getCardsInHand().size() + " to play the card");
				choice = scan.nextInt();
			}
		}
	}
	
	public void buyPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Here in Buy");
		cleanupPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
	}
	
	public void cleanupPhase(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		System.out.println("Here in Cleanup");
		
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		
		if (playerTurnCounter == 1) {
			resetBoard(player1);
			playerTurnCounter++;
		} else if (playerTurnCounter == 2) {
			resetBoard(player2);
			playerTurnCounter++;
		} else if (playerTurnCounter == 3) {
			resetBoard(player3);
			playerTurnCounter++;
		} else if (playerTurnCounter == 4) {
			resetBoard(player4);
			playerTurnCounter = 1;
		}
		
		resetCardsAvailable(players);
		resetNumActionsLeft(players);
		resetNumBuysLeft(players);
		resetExtraCoins(players);
		
		//return true;
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
	
	public void resetCardsAvailable(List<Player> players) {
		for (Player player : players) {
			player.resetCardsAvailable();
		}
	}
	
	public void availableCards(Player player) {
		// Search through the hand for an action cards
		Card card = new Card(null, null, null, null, null, null, null, null);
		Iterator<Card> itr = player.getCardsInHand().iterator();
		
		while (itr.hasNext()) {
			if (player.getCardsInHand().size() != 0)
				card = itr.next();
			if ("Action".equals(card.getType1())) {
				player.setActCardsAvailable(true);
				player.addCardToAction(card);
			}
			if ("Treasure".equals(card.getType1()) || "Treasure".equals(card.getType2()) || "Treasure".equals(card.getType3()) || "Treasure".equals(card.getType4())) {
				player.setCoinCardsAvailable(true);
				player.addCardToTreasure(card);
			}
			if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || "Victory".equals(card.getType3()) || "Victory".equals(card.getType4())) {
				player.setVicCardsAvailable(true);
				player.addCardToVictory(card);
			}
			if (card.getType1().equals("Curse")) {
				player.setCurseCardsAvailable(true);
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
	
}
