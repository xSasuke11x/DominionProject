package DominionPackage;

public class Card {

	private String name, type1, type2, effect1, effect2, effect3, effect4, effect5, cost, potion, numLeft;
	//private String[] cards;
	//private List<String[]> deckList = new ArrayList<String[]>();
	
	public Card(String name, String type1, String type2, String effect1, String effect2, String effect3, String effect4, String effect5, String cost, String potion, String numLeft) {
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.effect1 = effect1;
		this.effect2 = effect2;
		this.effect3 = effect3;
		this.effect4 = effect4;
		this.effect5 = effect5;
		this.cost = cost;
		this.potion = potion;
		this.numLeft = numLeft;
	}

	/*
	public Card(String[] cardArray) {
		this.cards = cards;
	}

	public List<String[]> getDeckList() {
		return this.deckList;
	}
	
	public void addCardToDeck(String[] tokens) {
		deckList.add(tokens);
	}
	*/
	
	@Override
	public String toString() {
		return (this.getName() + ", " + this.getType1() + ", " + this.getType2() + ", " + this.getEffect1() + ", " + this.getEffect2() 
			+ ", " + this.getEffect3() + ", " + this.getEffect4() + ", " + this.getEffect5() + ", " + this.getCost() + ", " + this.getPotion() 
			+ ", " + this.getNumLeft());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType1(String type1) {
		this.type1 = type1;
	}
	
	public String getType1() {
		return type1;
	}
	
	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	public String getType2() {
		return type2;
	}
	
	public void setEffect1(String effect1) {
		this.effect1 = effect1;
	}
	
	public String getEffect1() {
		return effect1;
	}
	
	public void setEffect2(String effect2) {
		this.effect2 = effect2;
	}
	
	public String getEffect2() {
		return effect2;
	}
	
	public void setEffect3(String effect3) {
		this.effect3 = effect3;
	}
	
	public String getEffect3() {
		return effect3;
	}
	
	public void setEffect4(String effect4) {
		this.effect4 = effect4;
	}
	
	public String getEffect4() {
		return effect4;
	}
	
	public void setEffect5(String effect5) {
		this.effect5 = effect5;
	}
	
	public String getEffect5() {
		return effect5;
	}
	
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getCost() {
		return cost;
	}
	
	public void setPotion(String potion) {
		this.potion = potion;
	}
	
	public String getPotion() {
		return potion;
	}
	
	public void setNumLeft(String numLeft) {
		this.numLeft = numLeft;
	}
	
	public String getNumLeft() {
		return numLeft;
	}

}
