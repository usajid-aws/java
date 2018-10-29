package mazegame.boundary;

import mazegame.entity.Location;

public interface IMazeData {
	Location getStartingLocation();
	String getWelcomeMessage();
	Location getRoom2();
	Location getRoom3();
	Location getRoom4();
	Location getRoom5();
	Location getRoom6();
}
