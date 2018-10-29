package mazegame.control;




import java.util.ArrayList;
import java.util.Random;

import mazegame.boundary.IMazeClient;
import mazegame.boundary.IMazeData;
import mazegame.entity.Item;
import mazegame.entity.Player;

public class DungeonMaster {
	private IMazeClient gameClient;
	private IMazeData gameData;
	private Player thePlayer;
	
	private boolean runGame, gameWon;
	int total = 1000; // count to kill monster, in case player returns and tries to kill again
	 public DungeonMaster(IMazeData gameData, IMazeClient gameClient)
     {
         this.gameData = gameData;
         this.gameClient = gameClient;
         runGame=true;
         gameWon = false;
     }

     public void printWelcome()
     {
         gameClient.playerMessage(gameData.getWelcomeMessage());
     }

     public void setupPlayer()
     {
         String playerName = gameClient.getReply("What name do you choose to be known by?");
         thePlayer = new Player(playerName);
         thePlayer.setCurrentLocation(gameData.getStartingLocation());
         gameClient.playerMessage("Welcome " + playerName + "\n\n");
         gameClient.playerMessage("You find yourself looking at ");
         gameClient.playerMessage(gameData.getStartingLocation().getDescription());
                  
         
     }

     public void runGame()
     {
         printWelcome();
         setupPlayer();
         while(runGame)
         {
        	 //System.out.println(thePlayer.getCurrentLocation().getLabel());
        	 if(thePlayer.getCurrentLocation().getLabel().equals("Room 1"))
        	 {
        		 runRoomOne();
        		 continue;
        	 }
        	 if(thePlayer.getCurrentLocation().getLabel().equals("Room2"))
        	 {
        		 runRoomTwo();
        		 continue;
        	 }
        	 if(thePlayer.getCurrentLocation().getLabel().equals("room3"))
        	 {
        		 runRoomThree();
        		 continue;
        	 }
        	 if(thePlayer.getCurrentLocation().getLabel().equals("room4"))
        	 {
        		 runRoomFour();
        		 continue;
        	 }
        	 if(thePlayer.getCurrentLocation().getLabel().equals("room5"))
        	 {
        		 runRoomFive();
        		 continue;
        	 }
        	 if(thePlayer.getCurrentLocation().getLabel().equals("room6"))
        	 {
        		 runRoomSix();
        		 continue;
        	 }
        	 
         }
         
         if(gameWon)
         {
        	 gameClient.playerMessage("Congrats, You Won The Game!! \n \n" );
         }
     }

	private void runRoomTwo() {
		gameClient.playerMessage("\n\n You see a flash of light, an alram clock ringing, That was one wild dream... \n\n");
		runGame = false;
		gameWon = true;
		return;
		
	}

	private void runRoomSix() {
		String reply = "";
		
		if(!thePlayer.getCurrentLocation().isItemsClaimed())
    	{
    		reply = gameClient.getYorNo("You see a Lever in the middle of the room. Hit 'Y' to pull, 'N' to skip");
            if(reply.equalsIgnoreCase("n")) ;
            else
            {
            	gameClient.playerMessage("You hear the click of a door get unlocked... I wonder where that came from");
            	thePlayer.getCurrentLocation().setItemsClaimed(true);
            	thePlayer.getCurrentLocation().setDescription("A room with flowers painted everywhere...");
            	gameData.getRoom2().setLocked(false);
            }
    	}
        while(true)
        {
        
       	 reply = checkInput();
       	 if(reply!=null)
       	 {
       		 if(reply.equalsIgnoreCase("q"))
       		 {
       			runGame = false;
       			return;
       		 }
       		if(reply.equalsIgnoreCase("l"))
     		 {
      			gameClient.playerMessage("These are your Items: \n");
     			thePlayer.listItems();
     		 } 
      		if(reply.equalsIgnoreCase("d"))
    		{
      			if(thePlayer.getItems().size() == 0 )
       			{
       				System.out.println("You Dont have Any items!");
       			}
     			gameClient.playerMessage("These are your Items: \n");
    			thePlayer.listItems();
    			reply = gameClient.getReply("Which Item do you want to Drop? this Item cannot be reclaimed.");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to drop?");
					 }
				 }
				 
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
				 
    		 } 
       		 
       		 if(thePlayer.getCurrentLocation().getExitDescription(reply)!=null)
       		 {
       			 System.out.println(thePlayer.getCurrentLocation().getExitDescription(reply));
       			 String nextAction = gameClient.getYorNo("Would you like to enter? 'Y' to Enter, 'N' to Skip");
       			 if(nextAction.equalsIgnoreCase("N")) continue;
       			 else
       			 {
       				 if(reply.equalsIgnoreCase("east"))
       				 {
       					 
       						thePlayer.setCurrentLocation(gameData.getRoom5());
          					 gameClient.playerMessage(gameData.getRoom5().getDescription());
          					 return;
       					
       				 }
       				
       			 }
       		 }
       		 else //room6 north and west and south nothing
       		 {
       			if(reply.equalsIgnoreCase("west") || reply.equalsIgnoreCase("north") || reply.equalsIgnoreCase("south"))
       			{
       				gameClient.playerMessage("A White Wall painted with beautiful flowers");       				
       			}
       		       			
       		 }

       	 }
       	 else
       	 {
       		 gameClient.playerMessage("Sorry wrong input, please try again");        		 
       	 }
        }
		
		
		
		
	}

	private void runRoomFive() {
		String reply = "";
        while(true)
        {
       	 reply = checkInput();
       	 if(reply!=null)
       	 {
       		 if(reply.equalsIgnoreCase("q"))
       		 {
       			runGame = false;
       			return;
       		 }
       		if(reply.equalsIgnoreCase("l"))
     		 {
      			gameClient.playerMessage("These are your Items: \n");
     			thePlayer.listItems();
     		 } 
      		if(reply.equalsIgnoreCase("d"))
    		{
      			if(thePlayer.getItems().size() == 0 )
       			{
       				System.out.println("You Dont have Any items!");
       			}
     			gameClient.playerMessage("These are your Items: \n");
    			thePlayer.listItems();
    			reply = gameClient.getReply("Which Item do you want to Drop? this Item cannot be reclaimed.");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to drop?");
					 }
				 }
				 
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
				 
    		 } 
       		 
       		 if(thePlayer.getCurrentLocation().getExitDescription(reply)!=null)
       		 {
       			 System.out.println(thePlayer.getCurrentLocation().getExitDescription(reply));
       			 String nextAction = gameClient.getYorNo("Would you like to enter? 'Y' to Enter, 'N' to Skip");
       			 if(nextAction.equalsIgnoreCase("N")) continue;
       			 else
       			 {
       				 if(reply.equalsIgnoreCase("west"))
       				 {
       					 if(thePlayer.getCurrentLocation().isItemsClaimed())
       					 {
       						thePlayer.setCurrentLocation(gameData.getRoom6());
          					 gameClient.playerMessage(gameData.getRoom6().getDescription());
          					 return;
       					 }
       					 else
       					 {
       						 roomFiveMonster();
       						 if(!runGame) return;
       						 if(thePlayer.getCurrentLocation().isItemsClaimed())
       						 {
       							 thePlayer.getCurrentLocation().setDescription("a dead monster lies on the floor.. Oh the stench!");
       						 }
       					 }
       					 
       				 }
       				 else if(reply.equalsIgnoreCase("south"))
       				 {
       					 
       						 thePlayer.setCurrentLocation(gameData.getRoom4());
       						 gameClient.playerMessage(gameData.getRoom4().getDescription());
       						 return;      					 
       				 }
       			 }
       		 }
       		 else //room5 north and east nothing
       		 {
       			if(reply.equalsIgnoreCase("east") || reply.equalsIgnoreCase("north"))
       			{
       				gameClient.playerMessage("A brick wall with what seems to be scratched from monster claws");       				
       			}
       		       			
       		 }

       	 }
       	 else
       	 {
       		 gameClient.playerMessage("Sorry wrong input, please try again");        		 
       	 }
        }
		
		
		
	}

	
	private void roomFiveMonster() {
		String reply= "";
		while(true)
		{
			 reply = gameClient.getYorNo("A big hairy Monster blocks a door, attack it? press 'Y' to attack, 'N' to skip");
			 if(reply.equalsIgnoreCase("n"))
			 {
				 gameClient.playerMessage("I guess I'll deal with it later, but I wonder whats behind that door... \n");
				 return;
			 }
			 else
			 {
				while(true)
				{
					
					reply = gameClient.getYorNo("Attack Monster? press 'Y' to attack, 'N' to flee");
					if(reply.equals("n"))
					{
						 gameClient.playerMessage("I guess I'll deal with it later, but I wonder whats behind that door... \n");
						 return;						
					}
					else
					{
						int attack = thePlayer.getRandomHealthBoost(0, 600);
						gameClient.playerMessage("You attack the monster and he loses "+attack+" lifepoints \n");
						total -= attack;
						attack = thePlayer.getRandomHealthBoost(0, 500); //monster attack
						thePlayer.setLifePoints(thePlayer.getLifePoints() - attack);
						if(thePlayer.getLifePoints() <= 0)
       					{
       						gameClient.playerMessage("Oh dear, You are dead! \n");
       						runGame=false;
       						return;
       					}
						gameClient.playerMessage("the Monster attacks you and you lose "+attack+" lifepoints \n");
						gameClient.playerMessage("Your current life points is: " + thePlayer.getLifePoints());
						if(total < 0 ) gameClient.playerMessage("\n the monster has 0 lifepoints left");
						else gameClient.playerMessage("\n the monster has " + total +" lifepoints left");
						if(thePlayer.hasSpecificItem("Health Potion"))
   						{
   							String interact = gameClient.getYorNo("\n use Health Potion? hit 'Y' to heal, 'N' to skip \n");
   						
   							if(interact.equalsIgnoreCase("n"))
   							{
   								if(total <= 0)
   								{
   									gameClient.playerMessage("\n\n Congrats, you have killed the Monster!");
   									thePlayer.getCurrentLocation().setItemsClaimed(true);
   									thePlayer.getCurrentLocation().setDescription("A dead hairy monster lies on the ground... Oh the stench!");
   									return;
   								}
   								else continue;
   							}
   							else
   							{
   								thePlayer.useHealthPotion();
   								gameClient.playerMessage("you drink the health potion, your lifepoints are now: " + thePlayer.getLifePoints() + "\n");
   								thePlayer.setWieght(thePlayer.getWieght() - 100);
   							 thePlayer.getItems().remove(thePlayer.getSpecificItem("Health Potion"));
   				   								
   							}
   							
   						}
						if(total <= 0)
						{
							gameClient.playerMessage("\n\n Congrats, you have killed the Monster!");
							thePlayer.getCurrentLocation().setItemsClaimed(true);
							return;
						}
						
						
					}
					
				}
			 }
		}
		
		
	}

	private void runRoomFour() {
		String reply = "";
        while(true)
        {
       	 reply = checkInput();
       	 if(reply!=null)
       	 {
       		 if(reply.equalsIgnoreCase("q"))
       		 {
       			runGame = false;
       			return;
       		 }
       		if(reply.equalsIgnoreCase("l"))
     		 {
      			gameClient.playerMessage("These are your Items: \n");
     			thePlayer.listItems();
     		 } 
      		if(reply.equalsIgnoreCase("d"))
    		{
      			if(thePlayer.getItems().size() == 0 )
       			{
       				System.out.println("You Dont have Any items!");
       		
       			}
     			gameClient.playerMessage("These are your Items: \n");
    			thePlayer.listItems();
    			reply = gameClient.getReply("Which Item do you want to Drop? this Item cannot be reclaimed.");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to drop?");
					 }
				 }
				 
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
				 
    		 } 
       		 
       		 if(thePlayer.getCurrentLocation().getExitDescription(reply)!=null)
       		 {
       			 System.out.println(thePlayer.getCurrentLocation().getExitDescription(reply));
       			 String nextAction = gameClient.getYorNo("Would you like to enter? 'Y' to Enter, 'N' to Skip");
       			 if(nextAction.equalsIgnoreCase("N")) continue;
       			 else
       			 {
       				 if(reply.equalsIgnoreCase("west"))
       				 {
       					 thePlayer.setCurrentLocation(gameData.getRoom3());
       					 gameClient.playerMessage(gameData.getRoom3().getDescription());
       					 return;
       				 }
       				 else if(reply.equalsIgnoreCase("north"))
       				 {
       					 
       						 thePlayer.setCurrentLocation(gameData.getRoom5());
       						 gameClient.playerMessage(gameData.getRoom5().getDescription());
       						 return;      					 
       				 }
       			 }
       		 }
       		 else //east supply crate, south nothing 
       		 {
       			if(reply.equalsIgnoreCase("east"))
       			{
       				if(thePlayer.getCurrentLocation().isItemsClaimed() == false)
       				{
       					gameClient.playerMessage("You Find A Crate! \n");
       					String interact = gameClient.getYorNo("\n interact? hit 'Y' to open crate, 'N' to skip \n");
           				if(interact.equalsIgnoreCase("n")) continue;
           				else
           				{
           					if(thePlayer.canAddToItems(new Item("Health Potion", 100, 50)))
           				    {
           						thePlayer.addToItems(new Item("Health Potion", 100, 50));
           						gameClient.playerMessage("You Obtain a health potion! \n");
           						thePlayer.setWieght(thePlayer.getWieght()-100);
           						thePlayer.getCurrentLocation().setItemsClaimed(true);
           						if(thePlayer.getLifePoints() < 1000)
           						{
           							interact = gameClient.getYorNo("\n use Health Potion? hit 'Y' to heal, 'N' to skip \n");
               						
           							if(interact.equalsIgnoreCase("n"))
           							{
           								thePlayer.getCurrentLocation().setItemsClaimed(true);
           								continue;
           							}
           							else
           							{
           								thePlayer.useHealthPotion();
           								gameClient.playerMessage("you drink the health potion, your lifepoints are now: " + thePlayer.getLifePoints() + "\n");
           								thePlayer.setWieght(thePlayer.getWieght() - 100);
           								thePlayer.getItems().remove(thePlayer.getSpecificItem("Health Potion"));
           								thePlayer.getCurrentLocation().setItemsClaimed(true);
           							}
           						}
           				    }
           					else
           					{
           						gameClient.playerMessage("the crates empty, bummer");
           						thePlayer.getCurrentLocation().setItemsClaimed(true);
           					}
           				}
       				}
       				else
       				{
       					gameClient.playerMessage("'\n Just an Old empty crate, maybe I Already found something in there... \n");       					
       				}
       			}
       			else
       			{
       				gameClient.playerMessage("Just a broken window, peeking out into the darkeness... \n");
       			}
       			        			
       		 }

       	 }
       	 else
       	 {
       		 gameClient.playerMessage("Sorry wrong input, please try again");        		 
       	 }
        }
		
	}

	private void runRoomThree() {
		String reply = "";
		if(thePlayer.getCurrentLocation().isItemsClaimed()) thePlayer.getCurrentLocation().setDescription("A big empty room, I think I've been here before...");
        while(true)
        {
       	 reply = checkInput();
       	 if(reply!=null)
       	 {
       		 if(reply.equalsIgnoreCase("q"))
       		 {
       			runGame = false;
       			return;
       		 }
       		if(reply.equalsIgnoreCase("l"))
     		 {
      			gameClient.playerMessage("These are your Items: \n");
     			thePlayer.listItems();
     		 } 
      		if(reply.equalsIgnoreCase("d"))
    		{
      			if(thePlayer.getItems().size() == 0 )
       			{
       				System.out.println("You Dont have Any items!");
       			
       			}
     			gameClient.playerMessage("These are your Items: \n");
    			thePlayer.listItems();
    			reply = gameClient.getReply("Which Item do you want to Drop? this Item cannot be reclaimed.");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to drop?");
					 }
				 }
				 
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
				 
    		 } 
       		 
       		 if(thePlayer.getCurrentLocation().getExitDescription(reply)!=null)
       		 {
       			 System.out.println(thePlayer.getCurrentLocation().getExitDescription(reply));
       			 String nextAction = gameClient.getYorNo("Would you like to enter? 'Y' to Enter, 'N' to Skip");
       			 if(nextAction.equalsIgnoreCase("N")) continue;
       			 else
       			 {
       				 if(reply.equalsIgnoreCase("west"))
       				 {
       					 thePlayer.setCurrentLocation(gameData.getStartingLocation());
       					 gameClient.playerMessage(gameData.getStartingLocation().getDescription());
       					 return;
       				 }
       				 else if(reply.equalsIgnoreCase("east"))
       				 {
       					 
       						 thePlayer.setCurrentLocation(gameData.getRoom4());
       						 gameClient.playerMessage(gameData.getRoom4().getDescription());
       						 return;      					 
       				 }
       			 }
       		 }
       		 else //south supply crate, north nothing 
       		 {
       			if(reply.equalsIgnoreCase("south"))
       			{
       				if(thePlayer.getCurrentLocation().isItemsClaimed() == false)
       				{
       					gameClient.playerMessage("You Find A Crate! \n");
       					String interact = gameClient.getYorNo("\n interact? hit 'Y' to open crate, 'N' to skip \n");
           				if(interact.equalsIgnoreCase("n")) continue;
           				else
           				{
           					gameClient.playerMessage("A Snake comes out and bites you, you scream in pain as it sliters away... \n");           			
           					thePlayer.setLifePoints(thePlayer.getLifePoints() - 400);
           					gameClient.playerMessage("Your Lifepoints are now: " + thePlayer.getLifePoints() + "\n");
           					if(thePlayer.getLifePoints() <= 0)
           					{
           						gameClient.playerMessage("Oh dear, You are dead! \n");
           						runGame=false;
           						return;
           					}
           					else
           					{
           						if(thePlayer.hasSpecificItem("Health Potion"))
           						{
           							interact = gameClient.getYorNo("\n use Health Potion? hit 'Y' to heal, 'N' to skip \n");
           						
           							if(interact.equalsIgnoreCase("n"))
           							{
           								thePlayer.getCurrentLocation().setDescription("A big empty room, I think I've been here before...");
           								gameData.getStartingLocation().setItemsClaimed(true);
           								continue;
           							}
           							else
           							{
           								thePlayer.useHealthPotion();
           								gameClient.playerMessage("you drink the health potion, your lifepoints are now: " + thePlayer.getLifePoints() + "\n");
           								thePlayer.setWieght(thePlayer.getWieght() - 100);
           							    thePlayer.getItems().remove(thePlayer.getSpecificItem("Health Potion"));
           								gameData.getStartingLocation().setItemsClaimed(true);
           								thePlayer.getCurrentLocation().setDescription("A big empty room, I think I've been here before...");
           							}
           							
           						}
           						else
           						{
           							gameClient.playerMessage("Maybe I should look for a Health Potion...");
           						}
           					}
           				}
       
       						
       					thePlayer.getCurrentLocation().setItemsClaimed(true);
       					thePlayer.getCurrentLocation().setDescription("A big empty room, I think I've been here before...");
       				}
       				else
       				{
       					gameClient.playerMessage("'\n Just an Old empty crate, maybe I Already found something in there... \n");       					
       				}
       			}
       			else
       			{
       				gameClient.playerMessage("Just a cracked bricked wall, nothing out of place here");
       			}
       			        			
       		 }

       	 }
       	 else
       	 {
       		 gameClient.playerMessage("Sorry wrong input, please try again");        		 
       	 }
        }
		
	}

	private void runRoomOne() {
		String reply = "";
        while(true)
        {
       	 reply = checkInput();
       	 if(reply!=null)
       	 {
       		 if(reply.equalsIgnoreCase("q"))
       		 {
       			runGame = false;
       			return;
       		 }
       		if(reply.equalsIgnoreCase("l"))
      		 {
       			gameClient.playerMessage("These are your Items: \n");
      			thePlayer.listItems();
      		 } 
       		if(reply.equalsIgnoreCase("d"))
     		{
       			if(thePlayer.getItems().size() == 0 )
       			{
       				System.out.println("You Dont have Any items!");
       			
       			}
      			gameClient.playerMessage("These are your Items: \n");
     			thePlayer.listItems();
     			reply = gameClient.getReply("Which Item do you want to Drop? this Item cannot be reclaimed.");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to drop?");
					 }
				 }
				 
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
				 
     		 } 
       		 
       		 if(thePlayer.getCurrentLocation().getExitDescription(reply)!=null)
       		 {
       			 System.out.println(thePlayer.getCurrentLocation().getExitDescription(reply));
       			 String nextAction = gameClient.getYorNo("Would you like to enter? 'Y' to Enter, 'N' to Skip");
       			 if(nextAction.equalsIgnoreCase("N")) continue;
       			 else
       			 {
       				 if(reply.equalsIgnoreCase("east"))
       				 {
       					 thePlayer.setCurrentLocation(gameData.getRoom3());
       					 gameClient.playerMessage(gameData.getRoom3().getDescription());
       					 return;
       				 }
       				 else if(reply.equalsIgnoreCase("north"))
       				 {
       					 if(gameData.getRoom2().isLocked())
       					 {
       						 gameClient.playerMessage("The door seems to be locked...");
       					 }
       					 else
       					 {
       						 thePlayer.setCurrentLocation(gameData.getRoom2());
       						 gameClient.playerMessage(gameData.getRoom2().getDescription());
       						 return;
       					 }       					 
       				 }
       			 }
       		 }
       		 else //west is supply crate --- south is the store
       		 {
       			if(reply.equalsIgnoreCase("west"))
       			{
       				if(gameData.getStartingLocation().isItemsClaimed() == false)
       				{
       					gameClient.playerMessage("You Find A Crate! \n");
       					thePlayer.setCoins(1000);
       					if(thePlayer.canAddToItems(new Item("Sword Level 1", 300, 200)))
       				    {
       						thePlayer.addToItems((new Item("Sword Level 1", 300, 200)));
       						gameClient.playerMessage("You Obtain a Level 1 Sword! \n");
       						thePlayer.setWieght(thePlayer.getWieght()-300);
       				    }
       				
       					if(thePlayer.canAddToItems(new Item("Health Potion", 100, 50)))
       				    {
       						thePlayer.addToItems(new Item("Health Potion", 100, 50));
       						gameClient.playerMessage("You Obtain a health potion! \n");
       						thePlayer.setWieght(thePlayer.getWieght()-100);
       						
       						if(thePlayer.getLifePoints() < 1000)
       						{
       							String interact = gameClient.getYorNo("\n use Health Potion? hit 'Y' to heal, 'N' to skip \n");
           						
       							if(interact.equalsIgnoreCase("n"))
       							{
       								gameData.getStartingLocation().setItemsClaimed(true);
       								continue;
       							}
       							else
       							{
       								thePlayer.useHealthPotion();
       								gameClient.playerMessage("you drink the health potion, your lifepoints are now: " + thePlayer.getLifePoints() + "\n");
       								thePlayer.setWieght(thePlayer.getWieght() - 100);
       								thePlayer.getItems().remove(thePlayer.getSpecificItem("Health Potion"));
       								
       							}
       						}
       				    }
       					if(thePlayer.canAddToItems(new Item("Armour level 1", 300, 0)))
       				    {
       						thePlayer.addToItems(new Item("Armour level 1", 300, 500));
       						gameClient.playerMessage("You Obtain a Level 1 Armour!");
       						thePlayer.setWieght(thePlayer.getWieght()-300);
       				    }
       					
      					
       			
       					gameData.getStartingLocation().setItemsClaimed(true);
       				}
       				else
       				{
       					gameClient.playerMessage("'\n Just an Old empty crate, maybe I Already found something in there... \n");       					
       				}
       			}
       			else if(reply.equalsIgnoreCase("south"))
       			{
       				gameClient.playerMessage("\n You see a rusty shop with an old man... \n");
       				String interact = gameClient.getYorNo("\n interact? hit 'Y' to enter shop, 'N' to skip \n");
       				if(interact.equalsIgnoreCase("n")) continue;
       				else
       				{
       					gameClient.playerMessage("You have " + thePlayer.getCoins() + " coins \n");
       					if(thePlayer.getCoins() == 0)
       					{
       						gameClient.playerMessage("Maybe I shoud look for coins or tiems to sell first...");
       		     			continue;
       					}
       					else
       					{
       						shopInteract();
       					}
       				}
       			}
       		 }

       	 }
       	 else
       	 {
       		 gameClient.playerMessage("Sorry wrong input, please try again");        		 
       	 }
        }
		
	}

	private void shopInteract() {
		 while(true)
		 {
			 String reply = gameClient.getReply("Would you like to sell or buy? press 'E' to Exit Shop");
			 while(true)
			 {
				 if(reply.equalsIgnoreCase("sell")
					|| reply.equalsIgnoreCase("buy")
					|| reply.equalsIgnoreCase("e")) break;
				 reply = gameClient.getReply("Invalid input. Would you like to sell or buy?");
			 }
			 if(reply.equalsIgnoreCase("e")) return;
			 if(reply.equalsIgnoreCase("sell"))
			 {
				
				if(thePlayer.getItems().size() == 0 )
				{
					gameClient.playerMessage("You Dont have Items to Sell! ");	
					return;
				}
				gameClient.playerMessage("these are your Items: \n");
				
				thePlayer.listItems();
				 
				 
				 reply = gameClient.getReply("Which Item do you want to sell?");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:thePlayer.getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("You don't have this Item! which Item do you want to sell?");
					 }
				 }
				 
				 gameClient.playerMessage("You sell the Item for " + thePlayer.getSpecificItem(reply).getCost() + "\n");
				 thePlayer.setCoins(thePlayer.getCoins() + thePlayer.getSpecificItem(reply).getCost());
				 thePlayer.setWieght(thePlayer.getWieght() - thePlayer.getSpecificItem(reply).getWeight());
				 gameClient.playerMessage("You have " + thePlayer.getCoins() + " coins");
				 if(!reply.equals("Health Potion")) gameData.getStartingLocation().getNpc().addToItems(thePlayer.getSpecificItem(reply));
				 thePlayer.getItems().remove(thePlayer.getSpecificItem(reply));
				 
			 }
			 else //buy Stuff
			 {
				 gameClient.playerMessage("You have " + thePlayer.getCoins() + " coins \n");
				 gameClient.playerMessage("these are the Items you can buy: \n");
				 gameData.getStartingLocation().getNpc().listItems();
				 reply = gameClient.getReply("Which Item do you want to buy?");
				 boolean itemExists = false;
				 while(!itemExists)
				 {
					 for(Item i:gameData.getStartingLocation().getNpc().getItems())
					 {
						 if(i.getName().equals(reply))
						 {
							 itemExists=true;
							 break;
						 }					 
					 }
					 if(!itemExists)
					 {
						 reply = gameClient.getReply("Item Doesnt Exist! which Item do you want to buy? \n");
					 }
				 }
				 //can add the selected item
				 if((thePlayer.getWieght() + gameData.getStartingLocation().getNpc().getSpecificItem(reply).getWeight())<1000)
				 {
					 if(thePlayer.getCoins() >= gameData.getStartingLocation().getNpc().getSpecificItem(reply).getCost())
					 {
						 thePlayer.addToItems(gameData.getStartingLocation().getNpc().getSpecificItem(reply));
						 thePlayer.setCoins(thePlayer.getCoins() - gameData.getStartingLocation().getNpc().getSpecificItem(reply).getCost());
						 thePlayer.setWieght(thePlayer.getWieght() + gameData.getStartingLocation().getNpc().getSpecificItem(reply).getWeight());
						 gameClient.playerMessage("You Bought: " + gameData.getStartingLocation().getNpc().getSpecificItem(reply).getName() + "\n");
						 gameClient.playerMessage("You now have: " + thePlayer.getCoins() + " Coins \n");
						 gameClient.playerMessage("You now Weigh: " + thePlayer.getWieght() + "lbs \n");
						 if(!reply.equals("Health Potion")) gameData.getStartingLocation().getNpc().getItems().remove(gameData.getStartingLocation().getNpc().getSpecificItem(reply));
						 
					 }
					 else
					 {
						 gameClient.playerMessage("You Dont have enough coins!\n");
						 return;
					 }
					 
				 }
				 else
				 {
					 gameClient.playerMessage("You can only carry 1000lbs, your current weigth is: " + thePlayer.getWieght() + "\n");
					 gameClient.playerMessage("Weight of Item Chosen: " + gameData.getStartingLocation().getNpc().getSpecificItem(reply).getWeight() + "\n");
					 gameClient.playerMessage("Total Weight: " + (thePlayer.getWieght() + gameData.getStartingLocation().getNpc().getSpecificItem(reply).getWeight()));
					 gameClient.playerMessage("\n Sell Items to purchase \n");
				 }
			 }
		 }
		
	}

	private String checkInput() {
		 gameClient.playerMessage("\n\n hit 'west' to look west, 'east' to look east', 'south' to look south, and 'north' to look north. Hit 'L' to listItems, D to Drop Items.");
         String reply = gameClient.getReply("<<Hit Q to exit>>");
         if(reply.equalsIgnoreCase("north") || 
            reply.equalsIgnoreCase("east") ||
            reply.equalsIgnoreCase("south") ||
            reply.equalsIgnoreCase("west") ||
            reply.equalsIgnoreCase("q") ||
            reply.equalsIgnoreCase("l") ||
            reply.equalsIgnoreCase("d"))  return reply;
         return null;
         
		
	}
}
