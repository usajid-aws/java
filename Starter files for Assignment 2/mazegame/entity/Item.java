package mazegame.entity;

public class Item {
	
	private String name;
	private int weight; 
	private int cost;
	
	public Item(String v, int w, int c)
	{
		this.setName(v);
		this.setWeight(w);
		this.setCost(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
