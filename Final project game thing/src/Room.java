import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Room 
{
	private ArrayList<Structure> structures; 
	private ArrayList<RangedEnemy> rangedEnemies;
	private ArrayList<MeleeEnemy> meleeEnemies;
	private ArrayList<Enemy> allEnemies;
	private int roomID; //Fight Room ID = 1 AND Rest Room ID = 2 AND Room ID = 3 is BOSS

	/**
	 * Creates a default Room object that contains structures in the room
	 */
	public Room(int roomID)
	{
		this.roomID = roomID;
		structures  = new ArrayList<Structure>();
		rangedEnemies = new ArrayList<RangedEnemy>();
		allEnemies = new ArrayList<Enemy>();
		setRoom();
		setEnemies();
	}
	
	public ArrayList<Structure> getStructures()
	{
		return structures;
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
			addRangedEnemy(new RangedEnemy(Math.random()*300, Math.random()*300, 3,3));
			addRangedEnemy(new RangedEnemy(Math.random()*300, Math.random()*300, 3,3));

//		}
	}
	public ArrayList<RangedEnemy> getRangedEnemies()
	{
		return rangedEnemies;
	}
	public ArrayList<Enemy> getAllEnemies()
	{
		return allEnemies;
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
						structures.add(new Structure(x,y));
					}
					if(x == 15 && y == 15)
						structures.add(new Structure(x,y));
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
						structures.add(new Structure(x,y));
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
				for( Structure s : structures)
					s.draw(drawer, obstacle ,x , y);	
			}
		}

		for(RangedEnemy e : rangedEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, rELeft);
	}
	
	public void addRangedEnemy(RangedEnemy enemy) {
		rangedEnemies.add(enemy);
		allEnemies.add(enemy);
	}
	
	public void addMeleeEnemy(MeleeEnemy enemy) {
		this.meleeEnemies.add(enemy);
		allEnemies.add(enemy);
	}
	
	public void addStructure(Structure stuct) {
		structures.add(stuct);
	}
}
