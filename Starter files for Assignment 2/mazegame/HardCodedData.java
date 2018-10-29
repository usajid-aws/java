package mazegame;

import java.util.ArrayList;

import mazegame.boundary.IMazeData;
import mazegame.entity.Exit;
import mazegame.entity.Item;
import mazegame.entity.Location;
import mazegame.entity.NPC;

public class HardCodedData implements IMazeData {
	private Location startUp;
	private Location room2;
	private Location room3;
	private Location room4;
	private Location room5;
	private Location room6;
	
	public HardCodedData()
	{
		createLocations();
	}
	
	public Location getRoom2()
	{
		return room2;
	}
	
	public Location getRoom3()
	{
		return room3;
	}
	
	public Location getRoom4()
	{
		return room4;
	}
	
	public Location getRoom5()
	{
		return room5;
	}
	
	public Location getRoom6()
	{
		return room6;
	}
	
	public Location getStartingLocation()
	{
		return startUp;
	}
	
	public String getWelcomeMessage()
	{
		return "Welcome to the Mount Helanous";
	}
	
	private void createLocations () 
	{
		startUp = new Location ("You find yourself stading in a big open room, lets see what mysteries it beholds...", "Room 1");
		room2 = new Location ("It's the final Boss! Here we go!", "Room2");
		room3 = new Location ("Another room, there seems to be the growl of a small monster...", "room3");
		room4 = new Location ("A big empty room, interesting...", "room4");
		room5 = new Location ("A big hairy monster bigger than the one you saw before drools in front of you, it may be asleep...", "room5");
		room6 = new Location ("a big empty room with a lever in middle, I wonder what it does...", "room6");
		
		room2.setLocked(true);
		// Connect Locations
		startUp.addExit("north",  new Exit ("you see a big steel door to the north", room2));
		startUp.addExit("east",  new Exit ("you hear a slight scratching sound coming from the east, I wonder what that is...", room2));
		
        room3.addExit("west", new Exit("you see a small light flickering towards the west", startUp));
        room3.addExit("east", new Exit("you hear water dripping from a room in the east", room4));
        
        room4.addExit("west", new Exit("you see a small light flickering towards the west", room3));
        room4.addExit("north",  new Exit ("you see a wooden door with holes to the north", room5));
        
        room5.addExit("west", new Exit("you see a small light flickering towards the west", room6));
        room5.addExit("south",  new Exit ("you see a small light flickering in the distance towards the south", room4));
        
        room6.addExit("east", new Exit("you see a small light flickering towards the east", room5));
        
        //Items in rooms
        //items in room 1 - startup (will be found in crate)
        NPC storeMaster = new NPC("storemaster", startUp, new ArrayList<Item>());        storeMaster.getItems().add(new Item("Sword Level 2", 600, 400));
        storeMaster.getItems().add(new Item("Health Potion", 50, 100));
        storeMaster.getItems().add(new Item("Armour level 2", 500, 300));
        storeMaster.getItems().add(new Item("Sword Level 3", 1500, 550));
        storeMaster.getItems().add(new Item("Armour level 3", 1500, 500));
        startUp.setNpc(storeMaster);
        
        

       			
	}
}
