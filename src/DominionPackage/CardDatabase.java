package DominionPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDatabase {
	//private List<String[]> cardCreationList = new ArrayList<String[]>();
	private List<Card> cardCreationList = new ArrayList<Card>();
	private int totalNumCards = 102;
	
	//public List<String[]> getCardList() {
	public List<Card> getCardList() {
		return cardCreationList;
	}
	
	// Read text version of cards from file and add to final list
	public void PopulateDatabase() {
		String name = null, type1 = null, type2 = null, type3 = null, type4 = null, effect = null,  cost = null, numLeft = null;
		String fileName = "CardList.txt";
		String line;
		//Card cp = new Card(name, type1, type2, effect1, effect2, effect3, effect4, effect5, cost, potion);
		
		try {
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
	        
	        String delimiter = "[/]";
	        
	        for (int i = 0; i < totalNumCards; i++) {
	        	while((line = bufferedReader.readLine()) != null) {
	        		Card cp = new Card(name, type1, type2, type3, type4, effect, cost, numLeft);
	            	String[] tokens = line.split(delimiter);
	            	
	            	cp.setName(tokens[0]);
	                cp.setType1(tokens[1]);
	                cp.setType2(tokens[2]);
	                cp.setType3(tokens[3]);
	                cp.setType4(tokens[4]);
	                cp.setEffect(tokens[5]);
	                cp.setCost(tokens[6]);
	                cp.setNumLeft(tokens[7]);
	                if (cp.getType2().contains("\"null\"")) {
	                	tokens[2] = null;
	                	cp.setType2(tokens[2]);
	                }
	                if (cp.getType3().contains("\"null\"")) {
	                	tokens[3] = null;
	                	cp.setType3(tokens[3]);
	                }
	                if (cp.getType4().contains("\"null\"")) {
	                	tokens[4] = null;
	                	cp.setType4(tokens[4]);
	                }
	            	
	                cardCreationList.add(cp);
	                
	                //System.out.println(cp.getName() + ", " + cp.getType1() + ", " + cp.getType2() + ", " + cp.getEffect1() + ", " + cp.getEffect2() 
	                //	+ ", " + cp.getEffect3() + ", " + cp.getEffect4() + ", " + cp.getEffect5() + ", " + cp.getCost() + ", " + cp.getPotion());
	            }
	        }
	        
	        //System.out.println(cardCreationList.get(0));
	        
	        /*for (Card card : cardCreationList) {
	        	System.out.println(card);
			}*/
	        
	        //cp.addCardToDeck(card);
        	//System.out.println(Arrays.toString(card));
        	/*System.out.println(card.getName() + ", " + card.getType1() + ", " + card.getType2() + ", " + card.getEffect1() + ", " + card.getEffect2()
        		+ ", " + card.getEffect3() + ", " + card.getEffect4() + ", " + card.getEffect5() + ", " + card.getCost() + ", " + card.getPotion());*/
	        
	        // Always close files.
	        bufferedReader.close();
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + fileName + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");                  
	    }
	}
}
