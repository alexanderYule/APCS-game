
import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author aaron d, alex y
 * Represents a RangedEnemy object that can attack by firing
 * at the Player
 */
public class RangedEnemy extends Enemy
{
	private Gun eGun;
	private int timeSinceChanged;
	
	
	/**
	 * Creates a default RangedEnemy object with a Gun
	 */
	public RangedEnemy() {
		super();
		this.eGun = new Gun(1,100,1,150,150, false);
		setVelocity(Math.random()*5, Math.random()*5);
		timeSinceChanged = (int)(1000 * Math.random());
	}
	
	/**
	 * Creates a custom RangedEnemy object with a Gun, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param xVel the x velocity component of this enemy
	 * @param yVel the y velocity component of this enemy
	 * @param damage the damage dealt by this RangedEnemy
	 * @param maxHealth the health of this RangedEnemy
	 */
	public RangedEnemy(int damage, double x, double y, double xVel, double yVel, int maxHealth) {
		super(x, y, maxHealth);
		eGun = new Gun(damage,400,3,60,150, false);
		setVelocity(xVel, yVel);
		setTimeSinceFire((int)(1000 * Math.random()));
		timeSinceChanged = (int)(1000 * Math.random());
	}
	
	
	/**
	 * Fires a bullet to the location of player
	 * @param player the player object this RangedEnemy has to shoot/target
	 * @param s the list of structures in the Room
	 * @param millis the time interval of shooting
	 */
	public void fireToPlayer(Player player, ArrayList<Structure> s, int millis) 
	{
		if(!canSeePlayer(player, s)) {
			return ;
		}
		if((millis - getTimeSinceFire())/1000.0 > eGun.getAttackSpeed())
		{
			setTimeSinceFire(millis);
			double vx = player.getX() + player.getRect().getWidth()/2 - getX();
			double vy = player.getY() + player.getRect().getHeight()/2 - getY();
	
			double angle = 0;
			
			angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
		
			getGun().fireBullet(getX(), getY(), angle, false);
		}
	}
	
	/**
	 * Returns the Gun this enemy is holding
	 * @return the Gun object that belongs to this enemy
	 */
	public Gun getGun() {
		return eGun;
	}
	public void move(ArrayList<Structure> s)
	{
		//changeDirection();
		boolean colDetected = false;
		if(this.getX() >= 900) {
			colDetected = true;
		}
		for(Structure str : s)
		{
			if(str.getHitBox().intersects(getRect()))
				colDetected = true;
		}
	
		
 		
		if(colDetected) 
 		{
			this.setVelocity(getxVel() * -1,getyVel() * -1);
 			//setyVel(getyVel() * -1);
			setY(getY() + getyVel());
 			//setxVel(getxVel() * -1);
			setX(getX() + getxVel());
 		}
		else
		{
			setY(getY() + getyVel());
			setX(getX() + getxVel());
		}
	}
	
	/**
	 * Draws a graphical representation of this RangedEnemy at its respective location 
	 *  by checking the RangedEnemy's current direction
	 * @param drawer the PApplet that will draw a representation of this RnagedEnemy
	 * @param eUp the left image of this RangedEnemy
	 * @param eDown the left image of this RangedEnemy
	 * @param eRight the left image of this RangedEnemy
	 * @param eLeft the left image of this RangedEnemy
	 * @post the x and y coordinate values of this RangedEnemy will be changed
	 */
	public void draw(PApplet drawer, PImage eUp, PImage eDown,PImage eRight,PImage eLeft) {
		if(isAlive())
		{
			
			if(getDir() == 1) { //LEFT
				drawer.image(eLeft, (int)(getX()), (int)getY());
			}
			else if(getDir() == 2) { //UP
				drawer.image(eUp, (int)getX(), (int)getY());
			}
			else if(getDir() == 3) { //RIGHT
				drawer.image(eRight,  (int)getX(), (int)getY());
			}
			else {  //DOWN
				drawer.image(eDown,  (int)getX(), (int)getY());
			}
			getRect().move(getX(), getY());		
		}
		super.draw(drawer);
	}
	
}
