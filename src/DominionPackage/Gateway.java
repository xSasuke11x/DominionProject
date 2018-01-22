package DominionPackage;

import java.util.ArrayList;
import java.util.List;

public class Gateway {
	
	public static void main(String[] args) {
		// Change this to test multiple players
		int playerTurnCounter = 1;
		
		//boolean winCondition = false;
		List<Player> players = new ArrayList<Player>();
		
		Player player1 = new Player();
		player1.setTurnCounter(1);
		players.add(player1);
		Player player2 = new Player();
		player2.setTurnCounter(2);
		players.add(player2);
		Player player3 = new Player();
		player3.setTurnCounter(3);
		players.add(player3);
		Player player4 = new Player();
		player4.setTurnCounter(4);
		players.add(player4);
		//int numPlayers = setupNumPlayers();
		
		CardDatabase cd = new CardDatabase();
		cd.PopulateDatabase();
		
		// Setup the kingdoms and distribute initial hand
		Kingdom kingdoms = new Kingdom();
		kingdoms.Setup(cd, player1.getNumPlayers(), players);
		
		//kingdoms.vicListSetup();
		//System.out.println(kingdoms.victoryList.size());
		
		GameFlow gf = new GameFlow();
		
		// Shuffle the cards in each player's deck
		for (Player player : players)
			player.shuffleDeck(player.getDeck());
		
		// Initiate gameplay
		for (; gf.getWinCondition() == false;) {
			if (playerTurnCounter == 1) {
				System.out.println();
				System.out.println("It is player 1's turn");
				gf.drawPhase(kingdoms, playerTurnCounter, players);
				playerTurnCounter++;
			} else if (playerTurnCounter == 2) {
				System.out.println();
				System.out.println("It is player 2's turn");
				gf.drawPhase(kingdoms, playerTurnCounter, players);
				if (player1.getNumPlayers() == 3)
					playerTurnCounter++;
				else
					playerTurnCounter = 1;
			} else if (playerTurnCounter == 3) {
				System.out.println();
				System.out.println("It is player 3's turn");
				gf.drawPhase(kingdoms, playerTurnCounter, players);
				if (player1.getNumPlayers() == 4)
					playerTurnCounter++;
				else
					playerTurnCounter = 1;
			} else {
				System.out.println();
				System.out.println("It is player 4's turn");
				gf.drawPhase(kingdoms, playerTurnCounter, players);
				playerTurnCounter = 1;
			}
		}
	}

	/*public static int setupNumPlayers() {
		return 2;
	}*/
}
