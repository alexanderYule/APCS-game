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
	private double interval, timeCheck;
	
	/**
	 *  Creates a DrawingSurface that has
	 *  enemies, a player, and other game elements
	*/
	public DrawingSurface() 
	{
		p = new Player(250 , 200);
		rangedEnemy = new RangedEnemy(Math.random()*300, Math.random()*300, 3,3); 
		interval = 5000;
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
		backBeta = loadImage("Resorces/temp,beta/FLOOR.png");
	}
	
	
	/**
	 *  Draws the the particular Shape instances on DrawingSurface using 
	 *  Processing PApplet.
	 *  @post background will change the color of the of the Processing
	 *  window to white
	*/
	public void draw() { 
		if(keyPressed)
		{
			kPressed(key);
		}
		background(0);   // Clear the screen with a white background

		/*for(int i = 0; i < 900; i+=60) {         //INEFFICIENT, but temporary
			for(int j = 0; j < 900; j+=30) {
				fill(80,39,8);
				strokeWeight(0.8f);
				rect(i, j, 60, 30, 3, 3, 3, 3);			
			}
		}*/

		for(int i = 0; i < 900; i+=40) 
		{         
			for(int j = 0; j < 900; j+=40) 
			{
				image(backBeta, i, j);
			}
		}
		for(Bullet b : p.getExistingBullets())  //INCORPORATE IN PLAYER CLASS LATER
		{
			b.move();
			image(pBullet,(int)b.getX(),(int)b.getY());
		}
		
		for(Bullet b : rangedEnemy.getGun().getExistingBullets())  //INCORPORATE IN RANGED ENEMY CLASS LATER
		{
			b.move();
			image(eBullet,(int)b.getX(),(int)b.getY());
		}	
		
		if(millis() > interval + timeCheck) {
			timeCheck = millis();
			double tempXVel = rangedEnemy.getxVel();
			double tempYVel = rangedEnemy.getyVel();

			if(Math.random()*6 >= 3) {
				tempXVel*=-1;
				tempYVel=0;
			}
			else{
				tempXVel*=0;
				tempYVel*=-1;
			}
			rangedEnemy.setVel(tempXVel, tempYVel);
			rangedEnemy.fireToPlayer(p);
		}
		
		rangedEnemy.draw(this);
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
	
	/** 
	 * Uses Processing PApplet to check when a keyboard key is pressed 
	 * @post The velocity of this player will be changed
	*/
	private void kPressed(char key)
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
	}
	
}