import java.awt.Color;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

	/**
	 * Creates a DrawingSurface that has enemies, a player, and other game elements
	 */
	public DrawingSurface() {
		m = new Map();
		p = new Player(250, 200);
		interval = 1000;
		timeCheck = millis();
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
	}

	/**
	 *  Draws the the particular Shape instances on DrawingSurface using 
	 *  Processing PApplet.
	 *  @post will move the player if "w","a","s","d" or any of the arrow keys are pressed.
	*/
	public void draw() { 
		if(keyPressed)
		{
			kPressed();
		}
		else
		{
			p.notMoving();
		}
		
		
		Room thisRoom = m.getRoom(0, 0);
		thisRoom.draw(this, backBeta, obstacle,eUp, eDown, eRight, eLeft, eBullet);
		ArrayList<Structure> structures = thisRoom.getStructures();
		

		if(thisRoom.getAllEnemies().size() <= 0) {
			p.setRoomStat(true);  //CAN GO OUT OF THIS ROOM 
			System.out.println("fas");
			//thisRoom =  m.getRoom(1, 0);
		}
		
		/*
		 * 
		 * when the player exits a room
		 * 
		 * 
		 */
		
		for(int x = 0; x < p.getExistingBullets().size(); x++)  //INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b = p.getExistingBullets().get(x);
			if(b.move(p, structures, thisRoom.getAllEnemies()))
			{
				p.getExistingBullets().remove(x);
				x--;
			}
			b.draw(this, pBullet);
		}
		
		for(int y = 0; y < thisRoom.getRangedEnemies().size(); y++)
		{
			RangedEnemy r = thisRoom.getRangedEnemies().get(y);
			if(!r.isAlive())
			{
				thisRoom.getRangedEnemies().remove(y);
				y--;
			}
			for(int x = 0; x < r.getGun().getExistingBullets().size(); x++) 
			{
				Bullet b =  r.getGun().getExistingBullets().get(x);
				if(b.move(p, structures, thisRoom.getAllEnemies()))
				{
					r.getGun().getExistingBullets().remove(x);
					x--;
				}
					
				b.draw(this, eBullet);
			}
				
		    if(millis() > interval + timeCheck) {  //LATER MAKE IT DEPENDENT ON EACH ENEMY RATE
		    	r.fireToPlayer(p);
		    	timeCheck = millis();
			}
		}
		
//		if(millis() > interval + timeCheck ) {
//
//			timeCheck = millis();

			for(Enemy r : thisRoom.getAllEnemies()) //Changes direction of enemies
			{
				//if(millis() > interval + timeCheck ) {
	
					r.move(structures);
				//}
			}
	
			p.draw(this); //draws this player
			p.move(structures);
	
	}

	/**
	 * Makes this player shoot a bullet from his/her weapon on a mouse click
	 */
	public void mousePressed() 
	{
		if (mouseButton == LEFT) 
		{
			p.fireWeapon(mouseX, mouseY,millis());
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