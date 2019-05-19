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

	private Room[][] rooms;
	private int level;
	
	public Map()
	{
		rooms = new Room[4][4];		
		
		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[row].length-1; col++) {
				rooms[row][col] = new Room(1);   //Fight Room
			}
			
			rooms[row][rooms[row].length-1] = new Room(2); //Non-Fight Room
		}
		
	}
	
	public Room getRoom(int x, int y)
	{
		return rooms[x][y];
	}
	
	public int getLevel() {
		return this.level;
	}
	
}
