package mazegame.entity;

import java.util.ArrayList;

public class NPC extends Character{
	
	public Location location;
	private ArrayList<Item> items;
	
	public NPC(String name, Location l, ArrayList<Item> items)
	{
		super(name);
		this.location = l;
		this.setItems(items);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
