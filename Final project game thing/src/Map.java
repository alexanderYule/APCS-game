/**
 * This class stores the rooms and their order.
 * code TBD
 * @author sumukhipandey
 *
 */
public class Map 
{
	//private static final fightRoom f = new Room();
	private Room[][] Map;
	
	public Map()
	{
		Map = new Room[4][4];
		Map[0][0] = new Room(1);
	}
	public Room getRoom(int x, int y)
	{
		return Map[x][y];
	}
	
}
