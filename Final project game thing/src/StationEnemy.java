
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class StationEnemy extends Enemy
{
	private Gun eGun;
	
	
	/**
	 * Creates a default StationEnemy object with a Gun
	 */
	public StationEnemy() {
		super();
		this.eGun = new Gun(1,100,1,1,150,150);
		setTimeSinceFire((int)(1000 * Math.random()));
	}
	
	/**
	 * Creates a custom StationEnemy object with a Gun, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param xVel the x velocity component of this enemy
	 * @param yVel the y velocity component of this enemy
	 */
	public StationEnemy(double x, double y, int fireRate, int maxHealth) {
		super(x, y, maxHealth);
		eGun = new Gun(1,400,2,fireRate,60,150);
		setTimeSinceFire((int)(1000 * Math.random()));
	}
	
	/**
	 * Fires a bullet to the location of player
	 * @param player the player object this StationEnemy has to shoot/target
	 */
	public void fireAllDir(int millis, ArrayList<Structure> structures) 
	{
		
		if((millis - getTimeSinceFire())/1000.0 > eGun.getAttackSpeed())
		{
			setTimeSinceFire(millis);
	
			double angle = 0;
			
			angle =  (Math.random()*360)*(180/Math.PI);		

			getGun().fireBullet(getX(), getY(), angle, false);
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
	 *  Draws a graphical representation of this StationEnemy at its respective location 
	 *  by checking the StationEnemy's current direction
	 * @param structures 
	 *  @post the x and y coordinate values of this StationEnemy will be changed
	*/
	public void draw(PApplet drawer, PImage eDown) {
		if(isAlive())
		{
			drawer.image(eDown,  (int)getX(), (int)getY());
			
			getRect().move(getX(), getY());		
		}
		super.draw(drawer);
	}
	
}