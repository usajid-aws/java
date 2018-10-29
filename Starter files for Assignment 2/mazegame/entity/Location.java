package mazegame.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	private HashMap<String, Exit> exits;
	private String description;
	private String label;
	private ArrayList<Item> items= new ArrayList<Item>();
	private boolean ItemsClaimed;
	private boolean locked;
	private NPC npc;
	
	public Location () { }
	
	public Location (String description, String label)
	{
		this.setDescription(description);
		this.setLabel(label);
		exits = new HashMap<String, Exit>();
		setLocked(false);
		setItemsClaimed(false);
	}
	
	public boolean addExit (String exitLabel, Exit theExit)
	{
		if (exits.containsKey(exitLabel))
			return false;
		exits.put(exitLabel, theExit);
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public String getExitDescription(String direction)
	{
		if(exits.get(direction)==null) return null;
		else return exits.get(direction).getDescription();
	}

	public boolean isItemsClaimed() {
		return ItemsClaimed;
	}

	public void setItemsClaimed(boolean itemsClaimed) {
		ItemsClaimed = itemsClaimed;
	}

	public NPC getNpc() {
		return npc;
	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}
	
}
