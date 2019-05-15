import java.awt.Window;
import java.awt.event.KeyEvent;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author Alex, Sumukhi, and Aaron
 *
 * Class that draws things and allows for images to be on the screen.
 */
public class DrawingSurface extends PApplet {

	private Player p;
	private RangedEnemy rangedEnemy;
	private Map m;
	private PImage back;
	private PImage backBeta;
	private PImage pBullet, eBullet;
	private PImage obstacle;
	private double interval, timeCheck;
	private PImage eUp ;
	private PImage eDown;
	private PImage eRight;
	private PImage eLeft;
	
	/**
	 *  Creates a DrawingSurface that has
	 *  enemies, a player, and other game elements
	*/
	public DrawingSurface() 
	{
		m = new Map();
		p = new Player(250 , 200);
		rangedEnemy = new RangedEnemy(Math.random()*300, Math.random()*300, 3,3); 
		interval = 1000;
		timeCheck = millis();
	}
	
	/**
	 *  The statements in the setup() function 
	 *  execute once when the program begins
	*/
	public void setup() 
	{		
		pBullet = loadImage("Resorces/bullettt.png");
		eBullet = loadImage("Resorces/bulletE.png");
		back = loadImage("Resorces/tiles/tileBlock.png");	
		//backBeta = loadImage("Resorces/temp,beta/FLOOR.png");
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

		/*for(int i = 0; i < 900; i+=60) {         //INEFFICIENT, but temporary
			for(int j = 0; j < 900; j+=30) {
				fill(80,39,8);
				strokeWeight(0.8f);
				rect(i, j, 60, 30, 3, 3, 3, 3);			
			}
		}*/

		m.getRoom(0, 0).draw(this, backBeta, obstacle);
		
		
		for(int x = 0; x < p.getExistingBullets().size(); x++)  //INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b = p.getExistingBullets().get(x);
			if(b.move())
			{
				p.getExistingBullets().remove(x);
				x--;
			}
			b.draw(this, pBullet);
		}
		
		for(int x = 0; x < rangedEnemy.getGun().getExistingBullets().size(); x++)  //INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			Bullet b =  rangedEnemy.getGun().getExistingBullets().get(x);
			if(b.move())
			{
				rangedEnemy.getGun().getExistingBullets().remove(x);
				x--;
			}
				
			b.draw(this, eBullet);
		}
		
		if(millis() > interval + timeCheck) {
			timeCheck = millis();
			double tempXVel = rangedEnemy.getxVel();
			double tempYVel = rangedEnemy.getyVel();

			if(Math.random()*6 >= 3) {
				tempXVel*=-1;
			}
			else{
				tempYVel*=-1;
			}
			rangedEnemy.setVel(tempXVel, tempYVel);
			rangedEnemy.fireToPlayer(p);
		}
		
		rangedEnemy.draw(this, eUp, eDown, eRight, eLeft);
		p.draw(this); //draws this player

	}
	
	/**
	 * Makes this player shoot a bullet from his/her
	 * weapon on a mouse click
	*/
	public void mousePressed() 
	{
		if (mouseButton == LEFT) 
		{
			p.fireWeapon(mouseX, mouseY);
		} 
	}
	
	 //1=left, 2=left & up, 3=up, 4= right & up 
	//5 = right 6 = right & down 7 = down 8 = left & down
	/** 
	 * Uses Processing PApplet to check when a keyboard key is pressed 
	 * @post The velocity of this player will be changed
	*/
	private void kPressed()
	{
		if(key == CODED)
		{
			if(keyCode == UP)
				p.setup(true);
			if(keyCode == DOWN)
				p.setdown(true);
			if(keyCode == LEFT)
				p.setleft(true);
			if(keyCode == RIGHT)
				p.setright(true);
		}
		if(key == 'w') //UP
		{
			p.setup(true);
		}
		if(key == 'a') //LEFT
		{
			p.setleft(true);
		}
		if(key == 'd') //RIGHT
		{
			p.setright(true);
		}
		if(key == 's') //DOWN
		{
			p.setdown(true);
		}
	}/*
	private void kPressed()
	{
		if(key == CODED)
		{
			if(keyCode == UP)
				p.setVelocity(0, -5);
			if(keyCode == DOWN)
				p.setVelocity(0,5);
			if(keyCode == LEFT)
				p.setVelocity(-5, 0);
			if(keyCode == RIGHT)
				p.setVelocity(5, 0);
		}
		if(key == 'w') //UP
		{
			p.setVelocity(0, -5);
		}
		if(key == 'a') //LEFT
		{
			p.setVelocity(-5, 0);
		}
		if(key == 'd') //RIGHT
		{
			p.setVelocity(5, 0);
		}
		if(key == 's') //DOWN
		{
			p.setVelocity(0,5);
		}
	}*/
	public void keyReleased()
	{
		if(key == CODED)
		{
			if(keyCode == UP)
				p.setup(false);
			if(keyCode == DOWN)
				p.setdown(false);
			if(keyCode == LEFT)
				p.setleft(false);
			if(keyCode == RIGHT)
				p.setright(false);
		}
		if(key == 'w') //UP
		{
			p.setup(false);
		}
		if(key == 'a') //LEFT
		{
			p.setleft(false);
		}
		if(key == 'd') //RIGHT
		{
			p.setright(false);
		}
		if(key == 's') //DOWN
		{
			p.setdown(false);
		}
	}
	
}