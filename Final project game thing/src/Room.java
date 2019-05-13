import java.util.ArrayList;

public class Room 
{
	private ArrayList<Structure> Structures;  //NEEDS TO BE INIT. BASED ON LEVELS IN THE TOTAL GAME...
	
	private int roomID;
	
	/**
	 * Creates a default Room object that contains structures in the room
	 */
	public Room(int roomID)
	{
		this.roomID = roomID;
		Structures  = new ArrayList<Structure>();
	}
	public ArrayList<Structure> getStructures()
	{
		return Structures;
	}
	public int getRoomID()
	{
		return roomID;
	}
}
