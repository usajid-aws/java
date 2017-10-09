public class Person{
	
    private String name;
    private int startLevel, endLevel, removed;
    boolean off;
    // person has name, start level, end level,
    // and boolean off, meaning he has gotten off the list and
    // on the elevator
	public Person(String name, int startLevel, int endLevel) {
		this.setName(name);
		this.setStartLevel(startLevel);
		this.setEndLevel(endLevel);
		setRemoved(getRemoved() + 1);
		off=false;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}

	public int getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(int endLevel) {
		this.endLevel = endLevel;
	}

	public int getRemoved() {
		return removed;
	}

	public void setRemoved(int removed) {
		this.removed = removed;
	}
}