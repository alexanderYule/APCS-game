import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Room 
{
	private ArrayList<Structure> Structures; 
	private ArrayList<RangedEnemy> rangedEnemies;
	private ArrayList<MeleeEnemy> meleeEnemies;
	private int roomID;
	//private 
	/**
	 * Creates a default Room object that contains structures in the room
	 */
	public Room(int roomID)
	{
		this.roomID = roomID;
		Structures  = new ArrayList<Structure>();
		rangedEnemies = new ArrayList<RangedEnemy>();
		setRoom();
		setEnemies();
	}
	
	public ArrayList<Structure> getStructures()
	{
		return Structures;
	}
	public int getRoomID()
	{
		return roomID;
	}
	private void setEnemies()
	{
//		if(roomID == 0)
//		{
//			
//		}
//		else
//		{
			rangedEnemies.add(new RangedEnemy(Math.random()*300, Math.random()*300, 3,3));
//		}
	}
	public ArrayList<RangedEnemy> getRangedEnemies()
	{
		return rangedEnemies;
	}
	private void setRoom()
	{
		if(roomID == 1)
		{
			for(int x = 0; x < 23; x++)
			{
				for(int y= 0; y < 23; y++)
				{
					if(x == 22 || y == 22 || x == 0 || y == 0) // x == 22 && y == 11
					{
						if(x != 22 || (y != 11 && y != 12 && y != 10))
						Structures.add(new Structure(x,y));
					}
				}
			}
		}
		else
		{
			for(int x = 0; x < 23; x++)
			{
				for(int y= 0; y < 23; y++)
				{
					if(x == 22 || y == 22 || x == 0 || y == 0)
					{
						Structures.add(new Structure(x,y));
					}
				}
			}
		}
	}
	public void draw(PApplet drawer, PImage floor, PImage obstacle, PImage rEUp, PImage rEDown, PImage rERight, PImage rELeft, PImage eBullet)
	{
		for(int x = 0; x < 920; x+=40) 
		{         
			for(int y = 0; y < 920; y+=40) 
			{
				drawer.image(floor, x, y);
				for( Structure s : Structures)
					s.draw(drawer, obstacle ,x , y);	
			}
		}
		for(RangedEnemy e : rangedEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, rELeft, eBullet);
	}
}
