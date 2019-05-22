/**
 * This class stores the rooms and their order.
 * code TBD
 * @author sumukhipandey
 *
 */
public class Map 
{
	//private static final fightRoom f = new Room();
	//Maybe implement like this: Rest are all fight rooms
//	[][][][][Rest Room]   --> Level 1
//	[][][][][Rest Room]   --> Level 2
//	[][][][][Rest Room]   --> Level 3
//	[][][][][Rest Room]   --> Level 4
//	[][][][][Rest Room]   --> Level 5

	
	
	private int level;
	/**
	 * creates a map object
	 * @param level the level this map is played on.
	 */
	public Map(int level)
	{
		/*
		this.level = level;
		rooms = new Room[4][4];		
		
		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[row].length-1; col++) {
				rooms[row][col] = new Room(1);   //Fight Room
			}
			
			rooms[row][rooms[row].length-1] = new Room(2); //Non-Fight Room
		}
		*/
	}
	/**
	 * 
	 * @param x the x position of the room
	 * @param y the y position of the room
	 * @return the room at x,y in the array of rooms.
	 */
	public Room getRoom(int x, int y)
	{
		return RoomSchema.getRoom(x, y);
		//return rooms[x][y];
	}
	/**
	 * 
	 * @return the level the map is made at
	 */
	public int getLevel() {
		return this.level;
	}
	
}
