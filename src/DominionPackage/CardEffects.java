package DominionPackage;

import java.util.Scanner;

public class CardEffects {

	public void Cellar(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// +1 Action
			player1.addNumActions(1);
			
			// Effect
			System.out.println("Discard card(s) - choose a number between 0 (discard none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			
			for (int i = 0; i < player1.getCardsInHand().size(); i++) {
				// Print out the cards with the value
				for (int j = 0; j < player1.getCardsInHand().size(); j++) {
					System.out.println((i + 1) + " = " + player1.getCardsInHand().get(j).getName());
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
			
			scan.close();
		}
	}
	
	public void Chapel(Kingdom kingdoms, int playerTurnCounter, Player player1, Player player2, Player player3, Player player4) {
		if (playerTurnCounter == 1) {
			
			// Effect
			System.out.println("Trash card(s) - choose a number between 0 (trash none) and " + player1.getCardsInHand().size());
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			
			for (int i = 0; i < player1.getCardsInHand().size(); i++) {
				// Print out the cards with the value
				for (int j = 0; j < player1.getCardsInHand().size(); j++) {
					System.out.println((i + 1) + " = " + player1.getCardsInHand().get(j).getName());
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
					
				} else {
					System.out.println("That is not a valid choice");
				}
			}
			
			scan.close();
		}
	}
	
}
