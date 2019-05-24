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
	private PImage gun;
	private PImage eUp;
	private PImage eDown;
	private PImage eRight;
	private PImage eLeft;
	private PImage hUp;
	private PImage hDown;
	private PImage hRight;
	private PImage hLeft;
	private PImage attackHurt;
	private Room currentRoom;
	private PImage leftMeleeGoblin;
	private double interval, timeCheck;
	private int levelNumber = 0 ;
	private int roomNumber = 0 ;
	private boolean levelComplete ;
	private boolean gameOver ;
	private boolean drawHitBox;


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
		currentRoom = RoomSchema.getRoom(levelNumber,getRoomNumber());
		currentRoom.setPlayer(p);
		levelComplete = false ;
		gameOver = false ;
		drawHitBox = true;
	}

	/**
	 * The statements in the setup() function execute once when the program begins
	 */
	public void setup() {
		pBullet = loadImage("Resorces/bullettt.png");
		eBullet = loadImage("Resorces/bulletE.png");
		back = loadImage("Resorces/tiles/tileBlock.png");
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
		attackHurt = loadImage("Resorces/attackBlood.png");
	}


	public void transportToNextRoom() {
		if(getRoomNumber() < RoomSchema.ROOMS - 1) {
			setRoomNumber(getRoomNumber() + 1) ;
		} else if(levelNumber < RoomSchema.LEVELS-1) {
			levelComplete = true ;
			levelNumber++ ;
			setRoomNumber(0) ;
		} else {
			levelComplete = true ;
			gameOver = true ;
		}
		Player player = currentRoom.getPlayer();
		currentRoom = RoomSchema.getRoom(levelNumber,getRoomNumber());
		currentRoom.setPlayer(player);
	}

	/**
	 * Draws the the particular Shape instances on DrawingSurface using Processing
	 * PApplet.
	 * 
	 * @post will move the player if "w","a","s","d" or any of the arrow keys are
	 *       pressed.
	 */
	public void draw() {
		
		if(levelComplete) {
			this.fill(255,255,255);
			
			for(int x = 40; x < 1000; x += 300) 
			{         
				for(int y = 40; y < 1100; y += 300) 
				{
					image(backBeta, x , y);
				}
			}
			
			this.strokeWeight(1);
			this.stroke(0,0,0);
			this.fill(0,0,0);
			this.textSize(40);
			String str = "Level " + (levelNumber+1) + " Complete, \nPress Enter to continue.." ;
			
			if(gameOver) {
				str = "Game Over" ;
				if(levelComplete)
					str += "\n You Win!" ;
			}
			
			this.text(str, 400, 400);
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
			String str = "Game Over\n You Lose!" ;
			
			this.text(str, 400, 400);
			return ;			
		}

		if (keyPressed) {
			kPressed();
		} else {
			p.notMoving();
		}

		currentRoom.draw(this, backBeta, obstacle, eUp, eDown, eRight, eLeft, eBullet, attackHurt, leftMeleeGoblin);
		
		ArrayList<Structure> structures = currentRoom.getStructures();


		
		
		for (int x = 0; x < p.getExistingBullets().size(); x++) // INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b = p.getExistingBullets().get(x);
		if (b.move(p, currentRoom.getAllEnemies(), structures)) {
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
				if (b.move(p, currentRoom.getAllEnemies(),structures)) {
					r.getGun().getExistingBullets().remove(x);
					x--;
				}

				b.draw(this, eBullet);
			}
			r.fireToPlayer(p, structures, millis());
			r.move(structures);
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
				if (b.move(p, currentRoom.getAllEnemies(),structures)) {
					r.getGun().getExistingBullets().remove(x);
					x--;
				}

				b.draw(this, eBullet);
			}
			r.fireAllDir(millis(), structures);
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
			r.move(p, structures);
		}
		
		
		if(currentRoom.getAllEnemies().size() == 0) //ROOM CHANGE
			p.setRoomStat(true);
		p.draw(this,hUp,hDown,hRight,hLeft); // draws this player
		if(p.move(structures))
		{
			currentRoom.canExit(true);		
		}
		if(currentRoom.canSendPlayer())		
		{
			transportToNextRoom();
			p.setRoomStat(false);
			currentRoom.canExit(false);
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
			drawHitBox = !drawHitBox;
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
			levelComplete = false ;
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

	public void setGameOver() {
		gameOver = true ;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

}