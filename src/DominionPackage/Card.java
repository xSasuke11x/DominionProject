package DominionPackage;

public class Card {

	private String name, type1, type2, type3, type4, effect, ID, cost, numLeft;
	
	public Card(String name, String type1, String type2, String type3, String type4, String effect, String ID, String cost, String numLeft) {
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
		this.type4 = type4;
		this.effect = effect;
		this.ID = ID;
		this.cost = cost;
		this.numLeft = numLeft;
	}
	
	// Override printing method
	@Override
	public String toString() {
		return (this.getName() + ", " + this.getType1() + ", " + this.getType2() + ", " + this.getType3() + ", " + this.getType4() + ", " 
				+ this.getEffect() + ", " + this.getID() + ", " + this.getCost() + ", " + this.getNumLeft());
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
	
	public void setType3(String type3) {
		this.type3 = type3;
	}
	
	public String getType3() {
		return type3;
	}
	
	public void setType4(String type4) {
		this.type4 = type4;
	}
	
	public String getType4() {
		return type4;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public String getEffect() {
		return effect;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getCost() {
		return cost;
	}
	
	public void setNumLeft(String numLeft) {
		this.numLeft = numLeft;
	}
	
	public String getNumLeft() {
		return numLeft;
	}

}
