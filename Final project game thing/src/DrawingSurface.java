import java.awt.Color;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import adsouza.shapes.Line;
import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Alex, Sumukhi, and Aaron
 *
 *         Class that draws things and allows for images to be on the screen.
 */
public class DrawingSurface extends PApplet {
	private Player p;
	private Map m;
	private PImage back;
	private PImage backBeta;
	private PImage pBullet, eBullet;
	private PImage obstacle;
	private double interval, timeCheck;
	private PImage eUp;
	private PImage eDown;
	private PImage eRight;
	private PImage eLeft;
	private PImage hUp;
	private PImage hDown;
	private PImage hRight;
	private PImage hLeft;
	private Room currentRoom;
	private CutsceneDrawer cutscene;

	/**
	 * Creates a DrawingSurface that has enemies, a player, and other game elements
	 */
	public DrawingSurface() {
		m = new Map(0);
		p = new Player(50, 460);
		interval = 1000;
		timeCheck = millis();
		currentRoom = null;
		RoomSchema.create();
		cutscene = new CutsceneDrawer();
	}

	/**
	 * The statements in the setup() function execute once when the program begins
	 */
	public void setup() {
		pBullet = loadImage("Resorces/bullettt.png");
		eBullet = loadImage("Resorces/bulletE.png");
		back = loadImage("Resorces/tiles/tileBlock.png");
		// backBeta = loadImage("Resorces/temp,beta/FLOOR.png");
		backBeta = loadImage("Resorces/floor.png");
		obstacle = loadImage("Resorces/brick.jpg");
		eUp = loadImage("Resorces/enemy_sprites/upGoblin.png");
		eDown = loadImage("Resorces/enemy_sprites/frontGoblin.png");
		eRight = loadImage("Resorces/enemy_sprites/rightGoblin.png");
		eLeft = loadImage("Resorces/enemy_sprites/leftGoblin.png");
		hUp = loadImage("Resorces/hero_sprites/up.png");
		hDown = loadImage("Resorces/hero_sprites/standingDown.png");
		hRight = loadImage("Resorces/hero_sprites/right.png");
		hLeft = loadImage("Resorces/hero_sprites/left.png");
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Draws the the particular Shape instances on DrawingSurface using Processing
	 * PApplet.
	 * 
	 * @post will move the player if "w","a","s","d" or any of the arrow keys are
	 *       pressed.
	 */
	public void draw() {

		if (keyPressed) {
			kPressed();
		} else {
			p.notMoving();
		}

		currentRoom = m.getRoom(0, 0);
		currentRoom.setPlayer(p);
		currentRoom.draw(this, backBeta, obstacle, eUp, eDown, eRight, eLeft, eBullet);
		ArrayList<Structure> structures = currentRoom.getStructures();

//		if (currentRoom.getAllEnemies().size() <= 0) {
//
//			currentRoom = m.getRoom(1, 0);
//		}

		/*
		 * 
		 * when the player exits a room
		 * 
		 * 
		 */

		for (int x = 0; x < p.getExistingBullets().size(); x++) // INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b = p.getExistingBullets().get(x);
			if (b.move(p, structures, currentRoom.getAllEnemies())) {
				p.getExistingBullets().remove(x);
				x--;
			}
			b.draw(this, pBullet);
		}

		for (int y = 0; y < currentRoom.getRangedEnemies().size(); y++) {
			RangedEnemy r = currentRoom.getRangedEnemies().get(y);
			if (!r.isAlive()) {
				currentRoom.getRangedEnemies().remove(y);
				y--;
			}
			for (int x = 0; x < r.getGun().getExistingBullets().size(); x++) {
				Bullet b = r.getGun().getExistingBullets().get(x);
				if (b.move(p, structures, currentRoom.getAllEnemies())) {
					r.getGun().getExistingBullets().remove(x);
					x--;
				}

				b.draw(this, eBullet);
			}
			r.fireToPlayer(p, millis(), structures);

		}

		// if(millis() > interval + timeCheck ) {
		//
		// timeCheck = millis();

		for (RangedEnemy r : currentRoom.getRangedEnemies()) // Changes direction of enemies
		{
			// if(millis() > interval + timeCheck ) {

			r.move(structures);
			// }
		}

		for (MeleeEnemy r : currentRoom.getMeleeEnemies()) // Changes direction of enemies
		{
			// if(millis() > interval + timeCheck ) {

			r.move(p, structures);
			// }
		}
		if(currentRoom.getAllEnemies().size() == 0)
			p.setRoomStat(true);
		p.draw(this); // draws this player
		if (p.move(structures)) 
		{
			System.out.println("xd");
			startCutscene();
		}
	}

	private void startCutscene() {
		int timeSince = millis();
		for (int x = 0; x < 23; x++) {
			for (int y = 0; y < 23; y++) {

			}
		}
	}

	/**
	 * Makes this player shoot a bullet from his/her weapon on a mouse click
	 */
	public void mousePressed() {
		if (mouseButton == LEFT) {
			p.fireWeapon(mouseX, mouseY, millis());
		}
	}

	// 1=left, 2=left & up, 3=up, 4= right & up
	// 5 = right 6 = right & down 7 = down 8 = left & down
	/**
	 * Uses Processing PApplet to check when a keyboard key is pressed
	 * 
	 * @post The velocity of this player will be changed
	 */
	private void kPressed() {
		if (key == CODED) {
			if (keyCode == UP)
				p.setup(true);
			if (keyCode == DOWN)
				p.setdown(true);
			if (keyCode == LEFT)
				p.setleft(true);
			if (keyCode == RIGHT)
				p.setright(true);
		}
		if (key == 'w') // UP
		{
			p.setup(true);
		}
		if (key == 'a') // LEFT
		{
			p.setleft(true);
		}
		if (key == 'd') // RIGHT
		{
			p.setright(true);
		}
		if (key == 's') // DOWN
		{
			p.setdown(true);
		}
		if (key == 'p') {
			p.takeDamage(1.0);
		}
	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == UP)
				p.setup(false);
			if (keyCode == DOWN)
				p.setdown(false);
			if (keyCode == LEFT)
				p.setleft(false);
			if (keyCode == RIGHT)
				p.setright(false);
		}
		if (key == 'w') // UP
		{
			p.setup(false);
		}
		if (key == 'a') // LEFT
		{
			p.setleft(false);
		}
		if (key == 'd') // RIGHT
		{
			p.setright(false);
		}
		if (key == 's') // DOWN
		{
			p.setdown(false);
		}
	}

}