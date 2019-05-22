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
		meleeEnemies = new ArrayList<MeleeEnemy>();
		allEnemies = new ArrayList<Enemy>();
		setRoom();
		setEnemies();
	}
	/**
	 * 
	 * @return all the structures in the room
	 */
	public ArrayList<Structure> getStructures()
	{
		return structures;
	}
	/**
	 * 
	 * @return a number representing the room
	 */
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
			addRangedEnemy(new RangedEnemy(500,500, 3,3));
			addRangedEnemy(new RangedEnemy(300,300, 3,3));
			addMeleeEnemy(new MeleeEnemy(Math.random()*300, Math.random()*300, 2.5));

//		}
	}
	/**
	 * 
	 * @return all alive ranged enemies in the room as an arrrayList
	 */
	public ArrayList<RangedEnemy> getRangedEnemies()
	{
		return rangedEnemies;
	}
	/**
	 * 
	 * @return all alive melee enemies in the room as an arrrayList
	 */
	public ArrayList<MeleeEnemy> getMeleeEnemies()
	{
		return meleeEnemies;
	}
	/**
	 * 
	 * @return returns all alive enemies in the room as an arrrayList
	 */
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
	/**
	 * draws the floor, the structures, the enemies, and the bullets
	 * 
	 * @param drawer the drawer object that draws everything
	 * @param floor the image of the floor to be drawn
	 * @param obstacle the image of the obstacles to be drawn
	 * @param rEUp the image of the enemy when facing up to be drawn
	 * @param rEDown the image of the enemy when facing down to be drawn
	 * @param rERight the image of the enemy when facing right to be drawn
	 * @param rELeft the image of the enemy when facing left to be drawn
	 * @param eBullet the image of the enemies bullets to be drawn
	 */
	public void draw(PApplet drawer, PImage floor, PImage obstacle, PImage rEUp, PImage rEDown, PImage rERight, PImage rELeft, PImage eBullet)
	{
		for(int x = 0; x < 920; x+=40) 
		{         
			for(int y = 0; y < 920; y+=40) 
			{
				drawer.image(floor, x, y);
				
			}
		}
		for( Structure s : structures)
			s.draw(drawer, obstacle);	

		for(RangedEnemy e : rangedEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, rELeft);
		for(MeleeEnemy e: meleeEnemies)
			e.draw(drawer,rEUp, rEDown, rERight, rELeft);
	}
	/**
	 * 
	 * @param enemy adds a ranged enemy to the room
	 */
	public void addRangedEnemy(RangedEnemy enemy) {
		rangedEnemies.add(enemy);
		allEnemies.add(enemy);
	}
	/**
	 * 
	 * @param enemy adds a melee enemy to the room.
	 */
	public void addMeleeEnemy(MeleeEnemy enemy) {
		this.meleeEnemies.add(enemy);
		allEnemies.add(enemy);
	}
	/**
	 * 
	 * @param stuct adds a structure to the room
	 */
	public void addStructure(Structure stuct) {
		structures.add(stuct);
	}
	
}
