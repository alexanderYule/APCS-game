
import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

public class RangedEnemy extends Enemy
{
	private Gun eGun;
	
	
	/**
	 * Creates a default RangedEnemy object with a Gun
	 */
	public RangedEnemy() {
		super();
		this.eGun = new Gun(1,100,1,1,150,150);
		setVelocity(Math.random()*5, Math.random()*5);
	}
	
	/**
	 * Creates a custom RangedEnemy object with a Gun, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param xVel the x velocity component of this enemy
	 * @param yVel the y velocity component of this enemy
	 */
	public RangedEnemy(double x, double y, double xVel, double yVel) {
		super(x, y);
		eGun = new Gun(1,400,1,1,50,150);
		setVelocity(xVel, yVel);
		setTimeSinceFire((int)(1000 * Math.random()));
	}
	
	/**
	 * Fires a bullet to the location of player
	 * @param player the player object this RangedEnemy has to shoot/target
	 */
	public void fireToPlayer(Player player, int millis) 
	{
		if((millis - getTimeSinceFire())/1000.0 > eGun.getAttackSpeed())
		{
			setTimeSinceFire(millis);
			double vx = player.getX() - getX();
			double vy = player.getY() - getY();
	
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
	

	/**
	 *  Draws a graphical representation of this RangedEnemy at its respective location 
	 *  by checking the RangedEnemy's current direction
	 * @param structures 
	 *  @post the x and y coordinate values of this RangedEnemy will be changed
	*/
	public void draw(PApplet drawer, PImage eUp, PImage eDown,PImage eRight,PImage eLeft) {
		if(isAlive())
		{			
			if(getDir() == 1) { //LEFT
				drawer.image(eLeft, (int)getX(), (int)getY());
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
			drawer.rect((float)getX() + 20, (float)getY()+10, 10f, 50f);
		}
	}
}
