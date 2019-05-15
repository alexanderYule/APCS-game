import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

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
		setRoom();
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
		if(roomID == 0)
		{}
		else
		{
			for(int x = 0; x < 23; x++)
			{
				for(int y= 0; y < 23; y++)
				{
					if(x == 23 || y == 23 || x == 0 || y == 0)
					{
						Structures.add(new Structure(x,y));
					}
				}
			}
		}
	}
	public void draw(PApplet drawer, PImage floor, PImage obstacle)
	{
		for(int x = 0; x < 920; x+=40) 
		{         
			for(int y = 0; y < 920; y+=40) 
			{
				drawer.image(floor, x, y);
				for( Structure s : Structures)
				{
					if(s.getX()* 40 == x && s.getY() * 40 == y)
						drawer.image(obstacle, x, y);
				}
				
				
			}
		}
	}
}
