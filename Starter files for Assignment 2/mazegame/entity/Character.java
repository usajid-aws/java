package mazegame.entity;

import java.util.ArrayList;

public class Character {
	private int agility;
    private int lifePoints;
    private String name;
    private int strength;
    private ArrayList<Item> items;
    
//    public Mazegame.Entity.Dice m_Dice;
//    public Mazegame.Entity.Party m_Party;
//    public Mazegame.Entity.Item m_Item;
//    public Mazegame.Entity.Shield m_Shield;
//    public Mazegame.Entity.Weapon m_Weapon;
//    public Mazegame.Entity.Armor m_Armor;
    
    public Character()
    {
    }

    public Character(String name)
    {
        this.setName(name);
        setItems(new ArrayList<Item>());
    }

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private int getStrength() {
		return strength;
	}

	private void setStrength(int strength) {
		this.strength = strength;
	}
	
	public void listItems()
	{
		if(this.getItems().size() == 0 )
		{
			System.out.println("You Dont have Any items!");
			return;
		}
		 for(Item i:this.getItems())
		 {
			 System.out.println("Item: " + i.getName() + " Value: " + i.getCost());
		 }
		 
	}
	
	public Item getSpecificItem(String item)
	{
		 for(Item i:this.getItems())
		 {
			 if(i.getName().equals(item))
			 {
				 return i;
			 }
		 }
		 return null;
		
	}
	
	public boolean hasSpecificItem(String item)
	{
		 for(Item i:this.getItems())
		 {
			 if(i.getName().equals(item))
			 {
				 return true;
			 }
		 }
		 return false;
		
	}
	
	
	
	public void addToItems(Item item)
	{
		getItems().add(item);
	}
	
	
	

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
