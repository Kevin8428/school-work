
public class MazeNode {

	public static final int OPEN = 0;
	public static final int WALL = 1;
	public static final int EXIT = 2;
	public static final int ENTRANCE = 3;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	private int north;
	private int south;
	private int east;
	private int west;
	
	public int getNorth() {
		return north;
	}
	public void setNorth(int north) {
		this.north = north;
	}
	public int getSouth() {
		return south;
	}
	public void setSouth(int south) {
		this.south = south;
	}
	public int getEast() {
		return east;
	}
	public void setEast(int east) {
		this.east = east;
	}
	public int getWest() {
		return west;
	}
	public void setWest(int west) {
		this.west = west;
	}
	
}
