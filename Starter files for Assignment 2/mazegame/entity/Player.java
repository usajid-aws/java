package mazegame.entity;

import java.util.ArrayList;
import java.util.Random;

public class Player extends Character {
	
	private Location currentLocation;
    private int wieght;
    private int coins;
   
    public Player()
    {
    	this.setWieght(1000);
    	this.setCoins(0);
    	this.setLifePoints(1000);
    	
    }

    public Player(String name)
	{
	    super (name);
	    this.setLifePoints(1000);
	    this.setWieght(1000);
	    this.setCoins(0);
	    setItems(new ArrayList<Item>());
	}

	public int getWieght() {
		return wieght;
	}

	public void setWieght(int wieght) {
		this.wieght = wieght;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public boolean canAddToItems(Item item)
	{
		if(this.wieght - item.getWeight() > 0) return true;
		return false;
	}
	
	public int getRandomHealthBoost(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public void useHealthPotion() {
		int ranOne = this.getRandomHealthBoost(0, 200);
			int ranTwo = this.getRandomHealthBoost(0, 200);
			this.setLifePoints(this.getLifePoints()+ranOne+ranTwo);
		
	}
	
	
	
	
	

}
