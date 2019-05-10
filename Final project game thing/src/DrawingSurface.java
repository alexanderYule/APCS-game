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
	private PImage pBullet, eBullet;
	private double interval, timeCheck;
	
	/**
	 *  Creates a DrawingSurface that has
	 *  enemies, a player, and other game elements
	*/
	public DrawingSurface() 
	{
		p = new Player(100 , 100);
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
	}
	
	/**
	 *  Draws the the particular Shape instances on DrawingSurface using 
	 *  Processing PApplet.
	 *  @post background will change the color of the of the Processing
	 *  window to white
	*/
	public void draw() { 
		background(255);   // Clear the screen with a white background

		for(int i = 0; i < 900; i+=20) {         //INEFFICIENT, but temporary
			for(int j = 0; j < 900; j+=20) {
				image(back, i, j);
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
	public void keyPressed()
	{
		if (keyCode == KeyEvent.VK_SPACE)
		{
			
		} 
		if(keyCode == KeyEvent.VK_W) //UP
		{
			p.setVelocity(0, -5);
		}
		if(keyCode == KeyEvent.VK_A) //LEFT
		{
			p.setVelocity(-5, 0);
		}
		if(keyCode == KeyEvent.VK_D) //RIGHT
		{
			p.setVelocity(5, 0);
		}
		if(keyCode == KeyEvent.VK_S) //DOWN
		{
			p.setVelocity(0,5);
		}
	}
	
}