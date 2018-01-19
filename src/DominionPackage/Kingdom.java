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
	public List<List<Card>> supplyList = new ArrayList<List<Card>>();
	public List<List<Card>> treasureList = new ArrayList<List<Card>>();
	public List<List<Card>> victoryList = new ArrayList<List<Card>>();
	public List<List<Card>> actionList = new ArrayList<List<Card>>();

	public void Setup(CardDatabase cd, int numPlayers, Player player1, Player player2, Player player3, Player player4) {
		kingdoms = kingdomSetup(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10);
		
		// Populate Base Piles
		for (Card card : cd.getCardList()) {
			for (int i = 0; i < Integer.parseInt(card.getNumLeft()); i++) {
				if ("1".equals(card.getID())) {
					// Populate Copper Pile
					copper.add(card);
				} else if ("2".equals(card.getID())) {
					// Populate Silver Pile
					silver.add(card);
				} else if ("3".equals(card.getID())) {
					// Populate Gold Pile
					gold.add(card);
				} else if ("4".equals(card.getID())) {
					// Populate Platinum Pile
					platinum.add(card);
				} else if ("5".equals(card.getID()) && i < (numPlayers * 10) - 10) {
					// Populate Curse pile based on number of players
					curse.add(card);
				} 
			}
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
		
		// Deal initial Copper to all players
		if (numPlayers == 2) {
			for (int i = 0; i < 7; i++) {
				Card copperCard = copper.get(0);
				if (numPlayers == 2) {
					player1.addCardToDeck(copperCard);
					copper.remove(0);
					player2.addCardToDeck(copperCard);
					copper.remove(0);
				} else if (numPlayers == 3) {
					player1.addCardToDeck(copperCard);
					copper.remove(0);
					player2.addCardToDeck(copperCard);
					copper.remove(0);
					player3.addCardToDeck(copperCard);
					copper.remove(0);
				} else {
					player1.addCardToDeck(copperCard);
					copper.remove(0);
					player2.addCardToDeck(copperCard);
					copper.remove(0);
					player3.addCardToDeck(copperCard);
					copper.remove(0);
					player4.addCardToDeck(copperCard);
					copper.remove(0);
				}
			}
		}
		
		// Deal initial Estates
		for (int i = 0; i < 3; i++) {
			Card estateCard = estate.get(0);
			
			if (numPlayers == 2) {
				player1.addCardToDeck(estateCard);
				estate.remove(0);
				player2.addCardToDeck(estateCard);
				estate.remove(0);
			} else if (numPlayers == 3) {
				player1.addCardToDeck(estateCard);
				estate.remove(0);
				player2.addCardToDeck(estateCard);
				estate.remove(0);
				player3.addCardToDeck(estateCard);
				estate.remove(0);
			} else {
				player1.addCardToDeck(estateCard);
				estate.remove(0);
				player2.addCardToDeck(estateCard);
				estate.remove(0);
				player3.addCardToDeck(estateCard);
				estate.remove(0);
				player4.addCardToDeck(estateCard);
				estate.remove(0);
			}
		}
		
		// Populate the random 10 Kingdoms
		Random generator = new Random();
        generator.setSeed((int)System.currentTimeMillis());
        int i;
        List<Card> list = new ArrayList<Card>(cd.getCardList()); 
        List<Card> subList = list.subList(9, totalNumCards); // Remove base cards from the card list
        List<Card> temp = new ArrayList<Card>();
        Iterator<Card> iterator;
        Collections.shuffle(subList); // Shuffle the non-base cards to be randomly selected
        for (iterator = subList.iterator(), i = 0; iterator.hasNext() && i < 10; i++) {
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
        
        // Create list of actions
        actionList = actListSetup();
        
        // Create list of treasures
        treasureList = treasListSetup();
        
        // Create list of victories
        victoryList = vicListSetup();
        
        // Create the overall supply list
        supplyList = supplyListSetup();
        
        /*for (Card temp2 : estate) {
        	System.out.println(temp2);
        }*/
        
        //System.out.println(player1.getDeck());
	}
	
	// If a kingdom is a victory card, change the number of cards from 10 to 8
	public List<Card> setupVictories(List<Card> kingdom) {
		Iterator<Card> iterator;
		int i;
		
		for (iterator = kingdom.iterator(), i = 0; iterator.hasNext() && i < 2; i++) {
			Card card = iterator.next();
			if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || 
				"Victory".equals(card.getType3()) || "Victory".equals(card.getType4())) {
	        	iterator.remove();
			} 
		}
		
		return kingdom;
	}
	
	public List<List<Card>> actListSetup() {
		List<List<Card>> actList = new ArrayList<List<Card>>();
		
		for (int i = 0; i < kingdoms.size(); i++) {
			if ("Action".equals(kingdoms.get(i).get(0).getType1())) {
				actList.add(kingdoms.get(i));
			}
		}
		
		return actList;
	}
	
	public List<List<Card>> treasListSetup() {
		List<List<Card>> treasureList = new ArrayList<List<Card>>();
		
		treasureList.add(copper);
		treasureList.add(silver);
		treasureList.add(gold);
		treasureList.add(platinum);
		
		Iterator<List<Card>> iterator;
		int i;
		
		for (iterator = kingdoms.iterator(), i = 0; iterator.hasNext() && i < kingdoms.size(); i++) {
			List<Card> kingdom = iterator.next();
			Card card = kingdom.get(0);
			if ("Treasure".equals(card.getType1()) || "Treasure".equals(card.getType2()) || 
				"Treasure".equals(card.getType3()) || "Treasure".equals(card.getType4())) {
				treasureList.add(kingdom);
			}
		}
		
		return treasureList;
	}
	
	public List<List<Card>> vicListSetup() {
		List<List<Card>> vicList = new ArrayList<List<Card>>();
		
		vicList.add(estate);
		vicList.add(duchy);
		vicList.add(province);
		vicList.add(colony);
		
		Iterator<List<Card>> iterator;
		int i;
		
		for (iterator = kingdoms.iterator(), i = 0; iterator.hasNext() && i < kingdoms.size(); i++) {
			List<Card> kingdom = iterator.next();
			Card card = kingdom.get(0);
			if ("Victory".equals(card.getType1()) || "Victory".equals(card.getType2()) || 
				"Victory".equals(card.getType3()) || "Victory".equals(card.getType4())) {
				vicList.add(kingdom);
			}
		}
		
		return vicList;
	}
	
	public List<List<Card>> supplyListSetup() {
		List<List<Card>> supList = new ArrayList<List<Card>>();
		Iterator<List<Card>> treasItr, vicItr;
		int i, j, k;
		
		// Add all cards that have Action as its first type; second type does not matter
		for (i = 0; i < actionList.size(); i++) {
			supList.add(actionList.get(i));
		}
		
		// Double check for Action/Treasure cards and don't add them if they are
		for (treasItr = treasureList.iterator(), j = 0; treasItr.hasNext() && j < treasureList.size(); j++) {
			List<Card> kingdom = treasItr.next();
			Card card = kingdom.get(0);
			
			// Only add the card if Treasure is its first type; second type does not matter as it would have been added already
			if ("Treasure".equals(card.getType1())) {
				supList.add(kingdom);
			}
		}
		
		// Double check for Action/Victory or Treasure/Victory cards and don't add them if they are
		for (vicItr = victoryList.iterator(), k = 0; vicItr.hasNext() && k < victoryList.size(); k++) {
			List<Card> kingdom = vicItr.next();
			Card card = kingdom.get(0);
			
			// Only add the card if Victory is its first type; second type does not matter as it would have been added already
			if ("Victory".equals(card.getType1())) {
				supList.add(kingdom);
			}
		}
		
		// Add curse pile to the supply list
		supList.add(curse);
		
		return supList;
	}
	
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
	
	public List<List<Card>> getSupplyList() {
		return supplyList;
	}
	
	public void removeCardFromSupplyList(Card card) {
		// Search through each kingdom in the supply list
		for (List<Card> kingdom : supplyList) {
			//System.out.println("Test kingdom size = " + kingdom.size());
			
			// If the card of concern has the same name as the kingdom, remove a card from that kingdom
			if (card.getName().equals(kingdom.get(0).getName())) {
				kingdom.remove(0);
				//System.out.println("Test kingdom size after remove = " + kingdom.size());
			}
		}
	}
}
