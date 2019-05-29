import java.util.ArrayList;

import adsouza.shapes.Line;
import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

public class Room {
	private Rectangle entryDoor;
	private Rectangle exitDoor;
	private ArrayList<Structure> structures;
	private ArrayList<RangedEnemy> rangedEnemies;
	private ArrayList<MeleeEnemy> meleeEnemies;
	private ArrayList<StationEnemy> stationEnemies;
	private ArrayList<HealthBooster> healthBoosters;
	private ArrayList<Enemy> allEnemies;
	private Player player;
	private int roomID; // Fight Room ID = 1 AND Rest Room ID = 2 AND Room ID = 3 is BOSS
	private boolean exiting;
	private PImage wall;
	private PImage door;
	private PImage lock;

	private Rectangle[] walls;

	/**
	 * Creates a custom Room object with a roomID, walls, rangedEnemies, meleeEnemies, 
	 * stationEnemies, and boosters
	 * @param roomID the type of room
	 * @param walls the walls in this room
	 * @param rangedEnemies the RangedEnemies in this room
	 * @param meleeEnemies the RangedEnemies in this room
	 * @param stationEnemies the RangedEnemies in this room
	 * @param boosters the health boosters within this room
	 */
	public Room(int roomID, Rectangle[] walls, RangedEnemy[] rangedEnemies, MeleeEnemy[] meleeEnemies,
			StationEnemy[] stationEnemies,HealthBooster[] boosters) {
		this.roomID = roomID;
		structures = new ArrayList<Structure>();
		this.rangedEnemies = new ArrayList<RangedEnemy>();
		this.meleeEnemies = new ArrayList<MeleeEnemy>();
		this.stationEnemies = new ArrayList<StationEnemy>();
		this.healthBoosters = new ArrayList<HealthBooster>();
		allEnemies = new ArrayList<Enemy>();
		player = null;
		entryDoor = null;
		exitDoor = null;
		for (Rectangle r : walls) {
			structures.add(new Structure(r, false));
		}
		this.walls = walls;
		setRoom();
		setEnemies(rangedEnemies, meleeEnemies, stationEnemies);
		setBoosters(boosters);
		exiting = false;
		wall = null;
		door = null;
	}

	/**
	 * 
	 * @return all the structures in the room
	 */
	public ArrayList<Structure> getStructures() {
		return structures;
	}

	/**
	 * 
	 * @return a number representing the room
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * Sets the reference to the player to p
	 * @param p the new Player object
	 */
	public void setPlayer(Player p) {
		if(p != null) {
			player = p;
			player.setX(50);
			player.setY(350);
			player.setRoomStat(false);
		}
	}

	/**
	 * @return the Player object in this Room
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets all RangedEnemies, MeleeEnemies, and StationEnemies
	 * in the room to the specified, respective parameters
	 */
	private void setEnemies(RangedEnemy[] rangedEnemies, MeleeEnemy[] meleeEnemies, StationEnemy[] stationEnemies) {
		for (RangedEnemy r : rangedEnemies) {
			addRangedEnemy(r);
		}
		for (MeleeEnemy m : meleeEnemies) {
			addMeleeEnemy(m);
		}
		for (StationEnemy m : stationEnemies) {
			addStationEnemy(m);
			m.getGun().setBulletSpeed(150);
		}
	}
	/**
	 * Sets all the HealthBoosters to the references in
	 * the specified values
	 */
	private void setBoosters(HealthBooster[] boosters) {
		for(HealthBooster h : boosters) {
			healthBoosters.add(h);
		}
	}

	/**
	 * Remove the particular HealthBooster b from the
	 * list of HealthBoosters in this Room
	 * @param b the HealthBooster object that will be removed 
	 * from the list of healthboosters in this Room
	 */
	public void removeHealthBooster(HealthBooster b) {
		healthBoosters.remove(b);
	}
	
	/**
	 * 
	 * @return all alive ranged enemies in the room as an arrrayList
	 */
	public ArrayList<RangedEnemy> getRangedEnemies() {
		return rangedEnemies;
	}

	/**
	 * 
	 * @return all alive stationary enemies in the room as an arrrayList
	 */
	public ArrayList<StationEnemy> getStationEnemies() {
		return stationEnemies;
	}

	/**
	 * 
	 * @return all alive melee enemies in the room as an arrrayList
	 */
	public ArrayList<MeleeEnemy> getMeleeEnemies() {
		return meleeEnemies;
	}

	/**
	 * 
	 * @return returns all alive enemies in the room as an arrrayList
	 */
	public ArrayList<Enemy> getAllEnemies() {
		return allEnemies;
	}

	/**
	 * @return the number of enemies in this
	 * Room
	 */
	public int enemyCount() {
		return getAllEnemies().size();
	}

	/**
	 * Removes the Enemy e from the rangedEnemy, meleeEnemy, and/or staionEnemy
	 * lists
	 * 
	 * @param e The enemy to check for existence in current enemy lists
	 */
	public void removeEnemy(Enemy e) {
		if (rangedEnemies.contains(e)) {
			rangedEnemies.remove(e);
			allEnemies.remove(e);
		}

		if (meleeEnemies.contains(e)) {
			meleeEnemies.remove(e);
			allEnemies.remove(e);
		}

		if (stationEnemies.contains(e)) {
			stationEnemies.remove(e);
			allEnemies.remove(e);
		}
	}

	/**
	 * Sets the elements in this Room: 
	 * structures, entry, and exit doors
	 * 
	 */
	private void setRoom() {
		for (int x = 0, y = 0; x < 960; x += 40) { // BORDERS
			structures.add(new Structure(x, y));
		}

		for (int x = 0, y = 0; y < 960; y += 40) { // BORDERS
			if ((y < 320 || y > 400))
				structures.add(new Structure(x, y));
		}

		for (int x = 885, y = 0; y < 960; y += 40) {
			if (y < 320 || y > 400)
				structures.add(new Structure(x, y));
		}

		for (int x = 0, y = 690; x < 960; x += 40) { // BORDERS
			structures.add(new Structure(x, y));
		}

		entryDoor = new Rectangle(0, 320, 40, 120, 255, 255, 255);
		exitDoor = new Rectangle(885, 320, 40, 120, 165, 42, 42);
		structures.add(new Structure(exitDoor, true));

		/*
		 * if(roomID == 1) { for(int x = 0; x < 23; x++) { for(int y= 0; y < 23; y++) {
		 * if(x == 22 || y == 22 || x == 0 || y == 0) // x == 22 && y == 11 { if(x != 22
		 * || (y != 11 && y != 12 && y != 10)) structures.add(new Structure(x,y)); } } }
		 * } else { for(int x = 0; x < 23; x++) { for(int y= 0; y < 23; y++) { if(x ==
		 * 22 || y == 22 || x == 0 || y == 0) { structures.add(new Structure(x,y)); } }
		 * } }
		 */
	}

	/**
	 * Draws the floor, the structures, the enemies, and the bullets
	 * 
	 * @param drawer   the drawer object that draws everything
	 * @param floor    the image of the floor to be drawn
	 * @param obstacle the image of the obstacles to be drawn
	 * @param rEUp     the image of the enemy when facing up to be drawn
	 * @param rEDown   the image of the enemy when facing down to be drawn
	 * @param rERight  the image of the enemy when facing right to be drawn
	 * @param rELeft   the image of the enemy when facing left to be drawn
	 * @param eBullet  the image of the enemies bullets to be drawn
	 * @param leftMeleeGoblin the image of the MeleeEnemy facing left
	 * @param stationDown the image of the Stationary Enemy
	 * @pre drawer must not be null
	 */
	public void draw(DrawingSurface drawer, PImage floor, PImage obstacle, PImage rEUp, PImage rEDown, 
					 PImage rERight,PImage rELeft, PImage eBullet, PImage leftMeleeGoblin, PImage stationDown) {
		if (wall == null) {
			wall = drawer.loadImage("Resorces/tiles/brickwall.jpg");  //ATTACK PIMAGE IS NEVER USED...
			door = drawer.loadImage("Resorces/tiles/door.jpg");
		}
		for (int x = 40; x < 1000; x += 300) {
			for (int y = 40; y < 1100; y += 300) {
				drawer.image(floor, x, y);
			}
		}

		for (Rectangle r : walls) {
			wall.resize((int) r.getWidth(), (int) r.getHeight());
			drawer.image(wall, (int) r.getX(), (int) r.getY());
			// r.draw(drawer);
		}

		for (Structure s : structures) {
			/*
			 * if(s.getClass() != TransportPad.class) s.draw(drawer, obstacle); else
			 */
			s.draw(drawer, obstacle);
		}

		drawer.image(door, (int) entryDoor.getX(), (int) entryDoor.getY());
		if (!exiting || enemyCount() > 0) {
			door.resize((int) exitDoor.getWidth(), (int) exitDoor.getHeight());
			drawer.image(door, (int) exitDoor.getX(), (int) exitDoor.getY());
		} else if (exitDoor.getHeight() > 0) {
			door.resize((int) exitDoor.getWidth(), (int) exitDoor.getHeight());
			drawer.image(door, (int) exitDoor.getX(), (int) exitDoor.getY());
			double h = exitDoor.getHeight();
			exitDoor.setHeight((int) (h - 2));
		}

		for (RangedEnemy e : rangedEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, rELeft);
		for (MeleeEnemy e : meleeEnemies)
			e.draw(drawer, rEUp, rEDown, rERight, leftMeleeGoblin);
		for (StationEnemy e : stationEnemies)
			e.draw(drawer, stationDown);
		for (HealthBooster h : healthBoosters)
			h.draw(drawer);
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
	 * Checks whether the door is open and returns true 
	 * accordingly
	 * @return true if the exit is open or false otherwise
	 */
	public boolean canSendPlayer()
	{
		if(exitDoor.getHeight() <= 0)
			return true;
		return false;
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
	 * 
	 * @param stuct adds a structure to the room
	 */
	public void addStructure(Structure stuct) {
		structures.add(stuct);
	}
	/**
	 * Sets the exit status of the player in the room 
	 * @param b boolean to change the exiting status
	 * @return true if the Player can exit the current Room
	 * or false otherwise
	 */
	public boolean canExit(boolean b) {
		Rectangle rect = player.getRect();
		exiting = b;
		return true;
	}
	/**
	 * Checks whether the Rectangle object rect 
	 * collides with any structures, walls, or exit 
	 * door in the room
	 * @param rect the rectangle to check for collision
	 * @return true if collision was detected or 
	 * false otherwise
	 */
	public boolean findCollison(Rectangle rect) {
		boolean found = false;

		for (Rectangle r : walls) {
			if (r.intersects(rect)) {
				found = true;
			}
		}

		for (Structure r : structures) {
			if (r.getHitBox().intersects(rect)) {
				found = true;
			}
		}

		if (enemyCount() > 0 && exitDoor.intersects(rect)) {
			found = true;
		}

		return found;
	}
	/**
	 * Checks whether the point (x,y) collides with 
	 * structures, or walls in the room
	 * @param x x-coordinate of the point
	 * @param y y-coordinate of the point
	 * @return true if collision was detected or
	 * false otherwise
	 */
	public boolean findCollison(double x, double y) {
		boolean found = false;

		for (Rectangle r : walls) {
			if (r.isPointInside(x, y)) {
				found = true;
			}
		}

		for (Structure r : structures) {
			if (r.getHitBox().isPointInside(x, y)) {
				found = true;
			}
		}

		return found;
	}
	/**
	 * Draws a line from the Enemy to the Player and 
	 * checks whether the Enemy can see the Player
	 * @param e the Enemy object
	 * @return true if a straight line can be drawn to the player or 
	 * false otherwise
	 */
	public boolean playerInSight(Enemy e) {
		Rectangle rect = e.getRect();
		int rx = (int) (rect.getX() + rect.getWidth() / 2);
		int ry = (int) (rect.getY() + rect.getHeight() / 2);
		Rectangle prect = player.getRect();
		int px = (int) (prect.getX() + prect.getWidth() / 2);
		int py = (int) (prect.getY() + prect.getHeight() / 2);

		Line l = new Line(rx, ry, px, py);

		boolean found = true;
		for (Rectangle r : walls) {
			if (r.intersects(l)) {
				found = false;
			}
		}

		for (Structure r : structures) {
			if (r.getHitBox().intersects(l)) {
				found = false;
			}
		}

		return found;
	}
	/**
	 * 
	 * @return the healthBoosters in this Room
	 */
	public ArrayList<HealthBooster> getHealthBoosters() {
		return healthBoosters ;
	}

}
