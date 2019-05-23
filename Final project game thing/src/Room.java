import java.util.ArrayList;

import adsouza.shapes.Line;
import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

public class Room 
{
	private Rectangle entryDoor ;
	private Rectangle exitDoor ;
	private ArrayList<Structure> structures; 
	private ArrayList<RangedEnemy> rangedEnemies;
	private ArrayList<MeleeEnemy> meleeEnemies;
	private ArrayList<StationEnemy> stationEnemies;
	private ArrayList<Enemy> allEnemies;
	private Player player ;
	private int roomID; //Fight Room ID = 1 AND Rest Room ID = 2 AND Room ID = 3 is BOSS
	private boolean exiting ;
	private PImage wall ;
	private PImage door ;
	
	private Rectangle[] walls ;

	/**
	 * Creates a default Room object that contains structures in the room
	 */
	public Room(int roomID,Rectangle[] walls,RangedEnemy[] rangedEnemies,MeleeEnemy[] meleeEnemies, StationEnemy[] stationEnemies)
	{
		this.roomID = roomID;
		structures  = new ArrayList<Structure>();
		this.rangedEnemies = new ArrayList<RangedEnemy>();
		this.meleeEnemies = new ArrayList<MeleeEnemy>();
		this.stationEnemies = new ArrayList<StationEnemy>();
		allEnemies = new ArrayList<Enemy>();
		player = null ;
		entryDoor =null;
		exitDoor = null ;
		for(Rectangle r : walls)
		{
			structures.add(new Structure(r,false));
		}
		this.walls = walls;
		setRoom();
		setEnemies(rangedEnemies,meleeEnemies,stationEnemies);
		exiting = false ;
		wall = null ;
		door = null ;
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
	
	public void setPlayer(Player p) {
		player = p ;
		player.setX(50);
		player.setY(460);
	}
	
	public Player getPlayer() {
		return player ;
	}
	
	private void setEnemies(RangedEnemy[] rangedEnemies,MeleeEnemy[] meleeEnemies, StationEnemy[] stationEnemies)
	{
		for(RangedEnemy r : rangedEnemies) {
			addRangedEnemy(r);
		}
		for(MeleeEnemy m : meleeEnemies) {
			addMeleeEnemy(m);
		}
		for(StationEnemy m : stationEnemies) {
			addStationEnemy(m);
			m.getGun().setBulletSpeed(150);
		}
	
		
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
	 * @return all alive stationary enemies in the room as an arrrayList
	 */
	public ArrayList<StationEnemy> getStationEnemies()
	{
		return stationEnemies;
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
	
	public int enemyCount() {
		return getAllEnemies().size() ;
	}
	/**
	 * Removes the Enemy e from the rangedEnemy, 
	 * meleeEnemy, and/or staionEnemy lists
	 * @param e The enemy to check for existence in current
	 * enemy lists
	 */
	public void removeEnemy(Enemy e) {
		if(rangedEnemies.contains(e)) {
			rangedEnemies.remove(e);
			allEnemies.remove(e);
		}

		if(meleeEnemies.contains(e)) {
			meleeEnemies.remove(e);
			allEnemies.remove(e);
		}
		
		if(stationEnemies.contains(e)) {
			stationEnemies.remove(e);
			allEnemies.remove(e);
		}
	}
	
	private void setRoom()
	{
		for(int x = 0 , y = 0 ; x < 960 ; x += 40) {  //BORDERS
			structures.add(new Structure(x,y));
		}

		for(int x = 0 , y = 0 ; y < 960 ; y += 40) {  //BORDERS
			if((y < 410 || y > 510))
				structures.add(new Structure(x,y));
		}

		for(int x = 885 , y = 0 ; y < 960 ; y += 40) {
			if(y < 400 || y > 510)
				structures.add(new Structure(x,y));
		}

		for(int x = 0 , y = 880 ; x < 960 ; x += 40) {  //BORDERS
			structures.add(new Structure(x,y));
		}
		
		entryDoor = new Rectangle(0,400,40,120,255,255,255);
		exitDoor = new Rectangle(885,400,40,120,165,42,42);
		structures.add(new Structure(exitDoor, true));

		
		/*
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
		*/
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
	public void draw(DrawingSurface drawer, PImage floor, PImage obstacle, PImage rEUp, PImage rEDown, PImage rERight, PImage rELeft, PImage eBullet,PImage attack)
	{
		if(wall == null) {
	 		wall = drawer.loadImage("Resorces/tiles/brickwall.jpg");
	 		door = drawer.loadImage("Resorces/tiles/door.jpg");
		}
		for(int x = 40; x < 1000; x += 300) 
		{         
			for(int y = 40; y < 1100; y += 300) 
			{
				drawer.image(floor, x , y);
				
			}
		}
		
		for(Rectangle r : walls) {
			wall.resize((int)r.getWidth(), (int)r.getHeight());
			drawer.image(wall, (int)r.getX(), (int)r.getY());
			//r.draw(drawer);
		}
		
		for( Structure s : structures)
		{
			/*if(s.getClass() != TransportPad.class)
				s.draw(drawer, obstacle);	
			else*/
				s.draw(drawer, obstacle);
		}
		
		door.resize((int)entryDoor.getWidth(), (int)entryDoor.getHeight());
		drawer.image(door, (int)entryDoor.getX(), (int)entryDoor.getY());
		if(!exiting || enemyCount() > 0) {
			door.resize((int)exitDoor.getWidth(), (int)exitDoor.getHeight());
			drawer.image(door, (int)exitDoor.getX(), (int)exitDoor.getY());
		} else if(exitDoor.getHeight() > 0) {
			door.resize((int)exitDoor.getWidth(), (int)exitDoor.getHeight());
			drawer.image(door, (int)exitDoor.getX(), (int)exitDoor.getY());
			double h = exitDoor.getHeight() ;
			exitDoor.setHeight((int)(h-2)) ;
			if(exitDoor.getHeight() <= 0) {
				drawer.transportToNextRoom() ;
				return ;
			}
		}

		for(RangedEnemy e : rangedEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, rELeft);
		for(MeleeEnemy e: meleeEnemies)
			e.draw(drawer,rEUp, rEDown, rERight, rELeft, attack);
		for(StationEnemy e: stationEnemies)
			e.draw(drawer, rEDown);
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
	 * @param enemy adds a stationary enemy to the room.
	 */
	public void addStationEnemy(StationEnemy enemy) {
		this.stationEnemies.add(enemy);
		allEnemies.add(enemy);
	}
	/**
<<<<<<< Updated upstream
	 *     
=======
	 * 
>>>>>>> Stashed changes
	 * @param stuct adds a structure to the room
	 */
	public void addStructure(Structure stuct) {
		structures.add(stuct);
	}

	public boolean canExit() {
		Rectangle rect = player.getRect() ;
		if(enemyCount() == 0 && exitDoor.intersects(rect)) {
			exiting = true ;
			return true ;
		}		
		return false ;
	}
	
	public boolean findCollison(Rectangle rect) {
		boolean found = false ;

		for(Rectangle r : walls) {
			if(r.intersects(rect)) {
				found = true ;
			}
		}

		for(Structure r : structures) {
			if(r.getHitBox().intersects(rect)) {
				found = true ;
			}
		}

		if(enemyCount() > 0 && exitDoor.intersects(rect)) {
			found = true ;
		}

		return found ;
	}
	
	public boolean findCollison(double x , double y) {
		boolean found = false ;
		
		for(Rectangle r : walls) {
			if(r.isPointInside(x, y)) {
				found = true ;
			}
		}

		for(Structure r : structures) {
			if(r.getHitBox().isPointInside(x, y)) {
				found = true ;
			}
		}

		return found ;
	}
	
	public boolean playerInSight(Enemy e) {
		Rectangle rect = e.getRect() ;
		int rx = (int) (rect.getX() + rect.getWidth()/2) ;
		int ry = (int) (rect.getY() + rect.getHeight()/2) ;
		Rectangle prect = player.getRect() ;
		int px = (int) (prect.getX() + prect.getWidth()/2) ;
		int py = (int) (prect.getY() + prect.getHeight()/2) ;
		
		Line l = new Line(rx,ry,px,py);
	
		boolean found = true ;
		for(Rectangle r : walls) {
			if(r.intersects(l)) {
				found = false ;
			}
		}
		
		for(Structure r : structures) {
			if(r.getHitBox().intersects(l)) {
				found = false ;
			}
		}

		return found ;
	}
	
}
