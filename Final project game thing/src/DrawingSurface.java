
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * 
 * @author Alex, Sumukhi, and Aaron
 *
 *         Class that draws things and allows for images to be on the screen.
 */
public class DrawingSurface extends PApplet {
	private Home home ;
	private Player p;
	private PImage backBeta;
	private PImage pBullet, eBullet;
	private PImage obstacle;
	private PImage gun;
	private PImage eUp, eDown ,eRight ,eLeft;
	private PImage hUp, hDown, hRight, hLeft;
	private PImage portal;
	private Room currentRoom;
	private PImage leftMeleeGoblin;
	private PImage stationEnemy;
	private PImage barrel;
	private int levelNumber;
	private int roomNumber;
	private boolean levelComplete ;
	private boolean gameOver ;
	private boolean drawHitBox;


	/**
	 * Creates a DrawingSurface that has enemies, a player, and other game elements
	 */
	public DrawingSurface() {
		home = new Home();
		p = new Player(50, 460);
		currentRoom = null;
		RoomSchema.create();
		levelComplete = false ;
		gameOver = false ;
		drawHitBox = false;
		levelNumber = 0;
		roomNumber = 0;
		currentRoom = RoomSchema.getRoom(levelNumber,getRoomNumber());
		currentRoom.setPlayer(p);
	}

	/**
	 * The statements in the setup() function execute once when the program begins
	 */
	public void setup() {
		pBullet = loadImage("Resorces/bullettt.png");
		eBullet = loadImage("Resorces/bulletE.png");
		gun = loadImage("Resorces/test/gun.png");
		// backBeta = loadImage("Resorces/temp,beta/FLOOR.png");
		backBeta = loadImage("Resorces/tiles/stones.jpg");
		obstacle = loadImage("Resorces/brick.jpg");
		eUp = loadImage("Resorces/enemy_sprites/upGoblin.png");
		eDown = loadImage("Resorces/enemy_sprites/frontGoblin.png");
		eRight = loadImage("Resorces/enemy_sprites/rightGoblin.png");
		leftMeleeGoblin = loadImage("Resorces/test/axeGoblinLeft.png");
		eLeft = loadImage("Resorces/enemy_sprites/leftGoblin.png");
		/*hUp = loadImage("Resorces/hero_sprites/up.png");
		hDown = loadImage("Resorces/hero_sprites/standingDown.png");
		hRight = loadImage("Resorces/hero_sprites/right.png");
		hLeft = loadImage("Resorces/hero_sprites/left.png");*/
		hUp = loadImage("Resorces/test/up.png");
		hDown = loadImage("Resorces/test/down.png");
		hRight = loadImage("Resorces/test/right.png");
		hLeft = loadImage("Resorces/test/left.png");
		stationEnemy = loadImage("Resorces/enemy_sprites/frontGoblin.png");
		barrel = loadImage("Resorces/room_structures/barrel.png");

		portal = loadImage("Resorces/test/Portal.png");
	}

	/**
	 * Changes the reference of this current room to a new level is the
	 * player has completed the level and to a a new room if the player has 
	 * completed the current room
	 */
	public void transportToNextRoom() {
		if(getRoomNumber() < RoomSchema.ROOMS - 1) {
			setRoomNumber(getRoomNumber() + 1) ;
		} else if(levelNumber < RoomSchema.LEVELS-1) {
			levelComplete = true ;
			levelNumber++ ;
			setRoomNumber(0) ;
		} else {  //Game over
			levelComplete = true ;
			gameOver = true ;
		}
		Player player = currentRoom.getPlayer();
		currentRoom = RoomSchema.getRoom(levelNumber,getRoomNumber());
		currentRoom.setPlayer(player);
	}

	/**
	 * Starts the game when mouse is
	 * clikced in the home screen
	 */
	public void mouseClicked() {
		home.mouseClicked(this);
	}
	
	/**
	 * Draws the the particular Shape instances on DrawingSurface using Processing
	 * PApplet.
	 * 
	 * @post will move the player if "w","a","s","d" or any of the arrow keys are
	 * pressed.
	 */
	public void draw() {
		if(!home.isStarted()) {
			home.draw(this);
			return ;
		} 
		if (mousePressed == true) {
		    p.fireWeapon(mouseX, mouseY, millis());
		} 
		this.strokeWeight(1);
		this.stroke(0,0,0);
		
		if(levelComplete  && levelNumber != 3) {
			background(0,0,0);
			this.strokeWeight(1);
			this.stroke(0,0,0);
			this.fill(0,0,0);
			this.textSize(40);
			portal.resize(400, 400);
			image(portal, 250,75);			
			fill(0,0,0);
			Rectangle r1 = new Rectangle(200, 500, 75,75);
			Rectangle r2 = new Rectangle(425, 500, 75,75);
			Rectangle r3 = new Rectangle(650, 500, 75,75);
			if(!gameOver) {
				r1.draw(this);
				r2.draw(this);
				r3.draw(this);
			}
			textFont(createFont("Impact", 32));
			textAlign(CENTER);
			fill(255,255,255);
			if(levelNumber <= 1 )
			{
				if(r1.isPointInside(mouseX, mouseY))				
					text("Increase attack speed slightly, slightly more damage.",463, 650);	
				if(r2.isPointInside(mouseX, mouseY))
					text("Increase attack speed drastically, but decreases damage.",463, 650);								
				if(r3.isPointInside(mouseX, mouseY))						
					text("Have a spray of bullets, but slower attack speed.",463, 650);				
				text(" Select a powerup to move onto the next level", 463, 30);
			}
			if(levelNumber == 2 && !gameOver)
			{
				if(r1.isPointInside(mouseX, mouseY))						
					text("Increased attack speed and damage.",463, 650);
				if(r2.isPointInside(mouseX, mouseY))				
					text("Double Machine gun.",463, 650);	
				if(r3.isPointInside(mouseX, mouseY))
					text("Upgraded Shotgun.",463, 650);													
				text(" Select a powerup to move onto the next level", 463, 50);
			}
			String str = "";
			if(gameOver) {
				textFont(createFont("Impact", 100));
				textAlign(CENTER);
				str = "Game Over" ;
							
				if(levelComplete)
					str += "\n You Win!" ;
			}
			
			this.text(str, 450, 300);
			textAlign(LEFT);
			return ;
		} else if(gameOver) {
			for(int x = 0; x < 1000; x += 300) 
			{         
				for(int y = 0; y < 1100; y += 300) 
				{
					image(backBeta, x , y);
				}
			}
			this.fill(255,255,255);
			//this.rect(0, 0, 1000, 1100);
			this.strokeWeight(1);
			this.stroke(0,0,0);
			this.fill(0,0,0);
			this.textSize(40);
			textFont(createFont("Impact", 100));
			textAlign(CENTER);
			fill(255,255,255);
			String str = "Game Over\n You Lose!" ;
			
			this.text(str, 450, 300);
			
			textAlign(LEFT);
			return ;			
		}

		if (keyPressed) {
			kPressed();
		} else {
			p.notMoving();
		}
			currentRoom.draw(this, backBeta, obstacle, eUp, eDown, eRight, eLeft, eBullet, leftMeleeGoblin,stationEnemy);
			
		ArrayList<Structure> structures = currentRoom.getStructures();
		ArrayList<HealthBooster> boosters = currentRoom.getHealthBoosters();
		
		
		for (int x = 0; x < p.getExistingBullets().size(); x++) // INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b = p.getExistingBullets().get(x);
		if (!home.isPaused() && b.move(p, currentRoom.getAllEnemies(), structures)) {
				p.getExistingBullets().remove(x);
				x--;
			}
			b.draw(this, pBullet);
		}

		for (int y = 0; y < currentRoom.getRangedEnemies().size(); y++) { //Adjust rANGEDeNEMIES
			RangedEnemy r = currentRoom.getRangedEnemies().get(y);
			if (!r.isAlive()) {
				currentRoom.getRangedEnemies().remove(y);
				y--;
			}
			for (int x = 0; x < r.getGun().getExistingBullets().size(); x++) {
				Bullet b = r.getGun().getExistingBullets().get(x);
				if (!home.isPaused() && b.move(p, currentRoom.getAllEnemies(),structures)) {
					r.getGun().getExistingBullets().remove(x);
					x--;
				}

				b.draw(this, eBullet);
			}
			if(!home.isPaused()) {
				r.fireToPlayer(p, structures, millis());
				r.move(structures);
			}
			if(drawHitBox)
			{
				r.drawHitBox(this);
			}
		}
		
		for (int y = 0; y < currentRoom.getStationEnemies().size(); y++) {
			StationEnemy r = currentRoom.getStationEnemies().get(y);
			if (!r.isAlive()) {
				currentRoom.getStationEnemies().remove(y);
				y--;
			}
			for (int x = 0; x < r.getGun().getExistingBullets().size(); x++) {
				Bullet b = r.getGun().getExistingBullets().get(x);
				if (!home.isPaused() && b.move(p, currentRoom.getAllEnemies(),structures)) {
					r.getGun().getExistingBullets().remove(x);
					x--;
				}

				b.draw(this, eBullet);
			}
			r.fireNearPlayer(p, millis(), structures);
		}

		// if(millis() > interval + timeCheck ) {
		//
		// timeCheck = millis();
		if(drawHitBox)
		{
			p.drawHitBox(this);
		}
		for (MeleeEnemy r : currentRoom.getMeleeEnemies()) // Changes direction of enemies
		{
			if(drawHitBox)
			{
				r.drawHitBox(this);
			}
			if(!home.isPaused())
				r.move(p, structures);
		}
		
		
		if(currentRoom.getAllEnemies().size() == 0) //ROOM CHANGE
			p.setRoomStat(true);
		p.draw(this,hUp,hDown,hRight,hLeft); // draws this player
		if(!home.isPaused() && p.move(structures,boosters,currentRoom))
		{
			currentRoom.canExit(true);		
		}
		if(currentRoom.canSendPlayer())		
		{
			transportToNextRoom();
			p.setRoomStat(false);
			currentRoom.canExit(false);
		}
		image(gun, 50,930);
		pushStyle();
		ellipseMode(CENTER);
		fill(0,0,0,255);
		//ellipse(50,930,50,50);
			//arc(50,930,50,50,0, (float)(2*PI -(2*PI * (p.getTimeSinceFire()*1000)/p.getGun().getAttackSpeed())), PIE);
	}

	/**
	 * Makes this player shoot a bullet from his/her weapon on a mouse click
	 */
	public void mousePressed() {
		int x = mouseX;
		int y = mouseY;
		if (mouseButton == LEFT) 
		{
			if(levelComplete)
			{
				if(levelNumber == 1)
				{
					if(x >= 200 && x <= 275 && y >= 500 && y<= 575)
					{
						p.getNewGun(1);
						levelComplete = false;
					}
					if(x >= 425 && x <= 500 && y >= 500 && y<= 575)
					{
						p.getNewGun(2);
						levelComplete = false;
					}
					if(x >= 650 && x <= 725 && y >= 500 && y<= 575)
					{
						p.getNewGun(3);
						levelComplete = false;
					}
				}
				else if(levelNumber == 2)
				{
					if(x >= 200 && x <= 275 && y >= 500 && y<= 575)
					{
						p.getNewGun(6);
						levelComplete = false;
					}
					if(x >= 425 && x <= 500 && y >= 500 && y<= 575)
					{
						p.getNewGun(4);
						levelComplete = false;
					}
					if(x >= 650 && x <= 725 && y >= 500 && y<= 575)
					{
						p.getNewGun(5);
						levelComplete = false;
					}
				}
			}
			if(!home.isPaused())
				p.fireWeapon(x, y, millis());
		}
	}

	// 1=left, 2=left & up, 3=up, 4= right & up
	// 5 = right 6 = right & down 7 = down 8 = left & down
	/**
	 * Runs when a key is typed to set 
	 * disable/enable HitBoxes and levelComplete
	 * status
	 */
	public void keyTyped()
	{
		if (key == 'p' || key == 'P') 
			drawHitBox = !drawHitBox;
		if(key == 'l' || key == 'L')
			levelComplete = !levelComplete;
	}
	/**
	 * Uses Processing PApplet to check when a keyboard key is pressed
	 * 
	 * @post The velocity of this player will be changed
	 */
	private void kPressed() {
		if (key == CODED) {
			if (keyCode == UP)
				p.setUp(true);
			if (keyCode == DOWN)
				p.setDown(true);
			if (keyCode == LEFT)
				p.setLeft(true);
			if (keyCode == RIGHT)
				p.setRight(true);
		}
		if (key == 'w' || key == 'W') // UP
		{
			p.setUp(true);
		}
		if (key == 'a' || key == 'A') // LEFT
		{
			p.setLeft(true);
		}
		if (key == 'd' || key == 'D') // RIGHT
		{
			p.setRight(true);
		}
		if (key == 's' || key == 'S') // DOWN
		{
			p.setDown(true);
		}
		
	}
	/**
	 *Sets the direction of the player based on the keys 
	 *W, A, S, D or Up, Down, Left, Right
	 */
	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == UP)
				p.setUp(false);
			if (keyCode == DOWN)
				p.setDown(false);
			if (keyCode == LEFT)
				p.setLeft(false);
			if (keyCode == RIGHT)
				p.setRight(false);
			levelComplete = false ;
		}
		if (key == 'w' || key == 'W') // UP
		{
			p.setUp(false);
		}
		if (key == 'a' || key == 'A') // LEFT
		{
			p.setLeft(false);
		}
		if (key == 'd' || key == 'D') // RIGHT
		{
			p.setRight(false);
		}
		if (key == 's' || key == 'S') // DOWN
		{
			p.setDown(false);
		}
		if (key == 'f' || key == 'F') // DOWN
		{
			home.pause();
		}
	}
	/**
	 * Sets the game status of the game to over
	 */
	public void setGameOver() {
		gameOver = true ;
	}
	/**
	 * @return The room number of the current room
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * Sets the room number of the current Room
	 * to a new roomNumber
	 * @param roomNumber The room number the current room's room
	 * number will be changed to
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	/**
	 * @return The levelNumber of the currentRoom
	 */
	public int getLevelNumber() {
		return levelNumber;
	}

}