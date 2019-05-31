
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author aaron d, sumukhi p, alex y
 * StationEnemy objects are stationed at one place throughout
 * Room completion and shoot bullets in random direction
 */
public class StationEnemy extends Enemy
{
	private Gun eGun;
	
	
	/**
	 * Creates a default StationEnemy object with a Gun
	 */
	public StationEnemy() {
		super();
		this.eGun = new Gun(1,100,1,150,150, false);
		setTimeSinceFire((int)(1000 * Math.random()));
	}
	
	/**
	 * Creates a custom StationEnemy object with a Gun, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param fireRate the Gun Fire rate of this Enemy
	 * @param maxHealth the health of this Enemy
	 * @param damage the damage dealt by this StationEnemy
	 */
	public StationEnemy(int damage,double x, double y, int fireRate, int maxHealth) {
		super(x, y, maxHealth);
		eGun = new Gun(damage,400,fireRate,60,150, false);
		setTimeSinceFire((int)(1000 * Math.random()));
	}
	
	/**
	 * Fires a bullet to the location of player
	 * @param p the player object this StationEnemy has to shoot/target
	 * @param millis the number of milliseconds until the StationEnemy shoots again
	 * @param structures the list of structures in the Room
	 */
	public void fireNearPlayer(Player p, int millis, ArrayList<Structure> structures) 
	{
		
		if((millis - getTimeSinceFire())/1000.0 > eGun.getAttackSpeed())
		{
			setTimeSinceFire(millis);
	
			double angle = 0;
			double vx = p.getX() + p.getRect().getWidth()/2 - getX();
			double vy = p.getY() + p.getRect().getHeight()/2 - getY();
			
			angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
			angle +=  (50 - (Math.random()*100));		

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
	 * @param eDown the image of this StationEnemy facing front 
	 * @param drawer the PApplet that draws a representation of this
	 * StationEnemy
	 * @pre drawer must not be null
	 * @post the x and y coordinate values of this StationEnemy will be changed
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