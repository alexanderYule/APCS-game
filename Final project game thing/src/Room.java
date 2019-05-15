import java.util.ArrayList;

public class Room 
{
	private ArrayList<Structure> Structures; 
	private int roomID;
	//private 
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
	private void setRoom()
	{
		if(roomID == 1)
		{}
		else
		{
			
		}
	}
}
