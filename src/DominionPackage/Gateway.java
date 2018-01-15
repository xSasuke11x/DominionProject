package DominionPackage;

public class Gateway {

	public static void main(String[] args) {
		int numPlayers = 2;
		int playerTurnCounter = 1;
		boolean winCondition = false;
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		
		CardDatabase cd = new CardDatabase();
		cd.PopulateDatabase();
		
		// Setup the kingdoms and distribute initial hand
		Kingdom kingdoms = new Kingdom();
		kingdoms.Setup(cd, numPlayers, player1, player2, player3, player4);
		
		//kingdoms.vicListSetup();
		//System.out.println(kingdoms.victoryList.size());
		
		GameFlow gf = new GameFlow();
		
		while (winCondition == false) {
			player1.shuffleDeck(player1.getDeck());
			player2.shuffleDeck(player2.getDeck());
			player3.shuffleDeck(player3.getDeck());
			player4.shuffleDeck(player4.getDeck());
			
			if (player1.getDeck().size() == 0) {
				player1.shuffleDeck(player1.getDeck());
			}
			if (player2.getDeck().size() == 0) {
				player2.shuffleDeck(player2.getDeck());
			}
			if (player3.getDeck().size() == 0) {
				player3.shuffleDeck(player3.getDeck());
			}
			if (player4.getDeck().size() == 0) {
				player4.shuffleDeck(player4.getDeck());
			}
			
			gf.drawPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
			//CardEffects ce = new CardEffects();
			//ce.Chapel(kingdoms, playerTurnCounter, player1, player2, player3, player4);
			winCondition = true;
			//gf.actionPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
			//gf.buyPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
			//winCondition = gf.cleanupPhase(kingdoms, playerTurnCounter, player1, player2, player3, player4);
		}
	}

}
