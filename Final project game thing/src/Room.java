import java.util.ArrayList;

public class Room 
{
	private ArrayList<Structure> Structures;
	private int[][] rooms;  //NEEDS TO BE INIT. BASED ON LEVELS IN THE TOTAL GAME...
	
	private int roomID;
	
	/**
	 * Creates a default Room object that contains structures in the room
	 */
	public Room()
	{
		Structures  = new ArrayList<Structure>();
	}
}
