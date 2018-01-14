package DominionPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Kingdom {
	
	private int vicTwoPlayers = 8, vicThreePlusPlayers = 12, totalNumCards = 36;
	
	// Allocate space for each of the 19 kingdoms and the trash pile
	public List<Card> copper = new ArrayList<Card>();
	public List<Card> silver = new ArrayList<Card>();
	public List<Card> gold = new ArrayList<Card>();
	public List<Card> platinum = new ArrayList<Card>();
	public List<Card> estate = new ArrayList<Card>();
	public List<Card> duchy = new ArrayList<Card>();
	public List<Card> province = new ArrayList<Card>();
	public List<Card> colony = new ArrayList<Card>();
	public List<Card> curse = new ArrayList<Card>();
	public List<Card> k1 = new ArrayList<Card>();
	public List<Card> k2 = new ArrayList<Card>();
	public List<Card> k3 = new ArrayList<Card>();
	public List<Card> k4 = new ArrayList<Card>();
	public List<Card> k5 = new ArrayList<Card>();
	public List<Card> k6 = new ArrayList<Card>();
	public List<Card> k7 = new ArrayList<Card>();
	public List<Card> k8 = new ArrayList<Card>();
	public List<Card> k9 = new ArrayList<Card>();
	public List<Card> k10 = new ArrayList<Card>();
	public List<List<Card>> kingdoms = new ArrayList<List<Card>>();
	public List<Card> trash = new ArrayList<Card>();
	public List<List<Card>> supply = new ArrayList<List<Card>>();
	public List<List<Card>> treasureList = new ArrayList<List<Card>>();
	public List<List<Card>> victoryList = new ArrayList<List<Card>>();

	public void Setup(CardDatabase cd, int numPlayers, Player player1, Player player2, Player player3, Player player4) {
		
		kingdoms = kingdomSetup(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10);
		
		// Populate Copper Pile
		for (int i = 0; i < Integer.parseInt(cd.getCardList().get(0).getNumLeft()); i++) {
			copper.add(cd.getCardList().get(0));
		}
		
		// Deal initial Copper to all players
		if (numPlayers == 2) {
			for (int i = 0; i < 7; i++) {
				//deck1 = player1.getDeck();
				//deck2 = player2.getDeck();
				//deck1.add(cd.getCardList().get(0));
				player1.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				//deck2.add(cd.getCardList().get(0));
				player2.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
			}
		} else if (numPlayers == 3) {
			for (int i = 0; i < 7; i++) {
				//deck3 = player3.getDeck();
				//deck3.add(cd.getCardList().get(0));
				player1.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				player2.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				player3.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
			}
		} else {
			for (int i = 0; i < 7; i++) {
				//deck4 = player4.getDeck();
				//deck3.add(cd.getCardList().get(0));
				player1.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				player2.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				player3.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
				player4.addCardToDeck(cd.getCardList().get(0));
				copper.remove(0);
			}
		}
		
		// Populate Silver pile
		for (int i = 0; i < Integer.parseInt(cd.getCardList().get(1).getNumLeft()); i++) {
			silver.add(cd.getCardList().get(1));
		}
		
		// Populate Gold pile
		for (int i = 0; i < Integer.parseInt(cd.getCardList().get(2).getNumLeft()); i++) {
			gold.add(cd.getCardList().get(2));
		}
		
		// Populate Platinum pile
		for (int i = 0; i < Integer.parseInt(cd.getCardList().get(3).getNumLeft()); i++) {
			platinum.add(cd.getCardList().get(3));
		}
		
		// Populate Curse pile based on number of players
		for (int i = 0; i < (numPlayers * 10) - 10; i++) {
			curse.add(cd.getCardList().get(4));
		}
		
		// Populate Estate pile
		if (numPlayers == 2) {
			for (int i = 0; i < vicTwoPlayers + (3 * numPlayers); i++) {
				estate.add(cd.getCardList().get(5));
			}
		} else {
			for (int i = 0; i < vicThreePlusPlayers + (3 * numPlayers); i++) {
				estate.add(cd.getCardList().get(5));
			}
		}
		
		// Distribute initial Estates
		if (numPlayers == 2) {
			for (int i = 0; i < 3; i++) {
				//deck1 = player1.getDeck();
				//deck2 = player2.getDeck();
				//deck1.add(cd.getCardList().get(5));
				player1.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				//deck2.add(cd.getCardList().get(5));
				player2.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
			}
		} else if (numPlayers == 3) {
			for (int i = 0; i < 3; i++) {
				//deck3 = player3.getDeck();
				//deck3.add(cd.getCardList().get(5));
				player1.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				player2.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				player3.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
			}
		} else {
			for (int i = 0; i < 3; i++) {
				//deck4 = player4.getDeck();
				//deck4.add(cd.getCardList().get(5));
				player1.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				player2.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				player3.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
				player4.addCardToDeck(cd.getCardList().get(5));
				estate.remove(0);
			}
		}
		
		// Populate Duchy, Province, and Colony piles
		if (numPlayers == 2) {
			for (int i = 0; i < vicTwoPlayers; i++) {
				duchy.add(cd.getCardList().get(6));
				province.add(cd.getCardList().get(7));
				colony.add(cd.getCardList().get(8));
			}
		} else {
			for (int i = 0; i < vicThreePlusPlayers; i++) {
				duchy.add(cd.getCardList().get(6));
				province.add(cd.getCardList().get(7));
				colony.add(cd.getCardList().get(8));
			}
		}
		
		// Populate the random 10 Kingdoms
		Random generator = new Random();
        generator.setSeed((int)System.currentTimeMillis());
        int num, i;
        List<Card> list = new ArrayList<Card>(); 
        list = cd.getCardList();
        List<Card> subList = list.subList(9, totalNumCards); // Remove base cards from the card list
        List<Card> temp = new ArrayList<Card>();
        Iterator<Card> iterator;
        Collections.shuffle(subList);
        for (iterator = subList.iterator(), i = 0; iterator.hasNext() && i < 10; i++) {
        	num = Math.abs(generator.nextInt()) % totalNumCards + 9;
            Card card = iterator.next();
            
            for (int j = 0; j < 10; j++) {
            	kingdoms.get(i).add(card);
            }
            
            //System.out.println(subList.get(0).getType1().toString()); // Get the Type1 for each card
            
            temp.add(card);
            iterator.remove();
        }
        
        // Set the each kingdom to their respective lists
        k1 = kingdoms.get(0);
        k2 = kingdoms.get(1);
        k3 = kingdoms.get(2);
        k4 = kingdoms.get(3);
        k5 = kingdoms.get(4);
        k6 = kingdoms.get(5);
        k7 = kingdoms.get(6);
        k8 = kingdoms.get(7);
        k9 = kingdoms.get(8);
        k10 = kingdoms.get(9);
        
        // If a kingdom is a victory kingdom, change the number of cards to 8
        for (int j = 0; j < 10; j++) {
        	setupVictories(kingdoms.get(j));
        }
        
        //System.out.println(Arrays.toString(kingdoms.get(0).toArray()));
        
        /*
        System.out.println(k1.size());
        System.out.println(k2.size());
        System.out.println(k3.size());
        System.out.println(k4.size());
        System.out.println(k5.size());
        System.out.println(k6.size());
        System.out.println(k7.size());
        System.out.println(k8.size());
        System.out.println(k9.size());
        System.out.println(k10.size());
        */
        
        //System.out.println();
        
        // Print out the 10 kingdom cards
        for (Card card : temp) {
        	System.out.println(card.getName());
        }
		
        //System.out.println();
        //System.out.println("Player1 deck = " + player1.getDeck()); // Prints out the deck list for the player
		
        //System.out.println(cd.getCardList().size());
        
        // Create list of treasures
        treasureList = treasureListSetup();
        
        // Create list of victories
        victoryList = vicListSetup();
        
        // Create the overall supply list
        //supplyListSetup(kingdoms);
	}
	
	// If a kingdom is a victory card, change the number of cards from 10 to 8
	public List<Card> setupVictories(List<Card> kingdom) {
		Iterator<Card> iterator;
		int i;
		
		for (iterator = kingdom.iterator(), i = 0; iterator.hasNext() && i < 2; i++) {
			Card card = iterator.next();
			if (card.getType1().equals("Victory") || card.getType1().equals("Victory")) {
	        	iterator.remove();
			}
		}
		
		return kingdom;
	}
	
	public List<List<Card>> treasureListSetup() {
		List<List<Card>> treasureList = new ArrayList<List<Card>>();
		
		treasureList.add(this.copper);
		treasureList.add(this.silver);
		treasureList.add(this.gold);
		treasureList.add(this.platinum);
		
		for (int i = 0; i < this.kingdoms.size(); i++) {
			if (this.kingdoms.get(i).get(0).getType1().equals("Treasure")) {
				treasureList.add(this.kingdoms.get(i));
			}
		}
		
		return treasureList;
	}
	
	public List<List<Card>> vicListSetup() {
		List<List<Card>> vicList = new ArrayList<List<Card>>();
		
		vicList.add(this.estate);
		vicList.add(this.duchy);
		vicList.add(this.province);
		vicList.add(this.colony);
		
		for (int i = 0; i < this.kingdoms.size(); i++) {
			if (this.kingdoms.get(i).get(0).getType1().equals("Victory") || this.kingdoms.get(i).get(0).getType2().equals("Victory")) {
				vicList.add(this.kingdoms.get(i));
			}
		}
		
		return vicList;
	}
	
	/*public List<List<Card>> supplyListSetup() {
		List<List<Card>> supplyList = new ArrayList<List<Card>>();
		
		for (int i = 0; i < this.treasureList.size(); i++) {
			if () {
				supplyList.add();
			}
		}
		
		return null;
	}*/
	
	// Create a list of kingdoms and return it
	public List<List<Card>> kingdomSetup(List<Card> k1, List<Card> k2, List<Card> k3, List<Card> k4, List<Card> k5, List<Card> k6, List<Card> k7, 
			List<Card> k8, List<Card> k9, List<Card> k10) {
		
		List<List<Card>> kingdoms = new ArrayList<List<Card>>();
		
		kingdoms.add(k1);
		kingdoms.add(k2);
		kingdoms.add(k3);
		kingdoms.add(k4);
		kingdoms.add(k5);
		kingdoms.add(k6);
		kingdoms.add(k7);
		kingdoms.add(k8);
		kingdoms.add(k9);
		kingdoms.add(k10);
		
		return kingdoms;
	}
	
	/*public List<Card> getSupply() {
		List<Card> supply = new ArrayList<Card>();
		
		return supply();
	}*/
}
