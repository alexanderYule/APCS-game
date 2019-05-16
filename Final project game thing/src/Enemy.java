import adsouza.shapes.Rectangle;

/**
 * 
 * @author ayule801
 *
 */
public class Enemy extends GameEntity
{
	private double damage; //WHAT DOES DAMAGE EXACTLY STAND FOR?
	private double health;
	private int dir; //1=left, 2=up, 3=right, 4=down
	 
	/**
	 * Creates an enemy object that has velocity,
	 * health, damage, direction at a certain location
	 * @param x the x coordinate of this enemy
	 * @param y the y coordinate of this enemy
	 */
	public Enemy(double x, double y) {
		super(x,y,0,0,20,30);
		this.health = 100;
		this.damage = 0; 
		this.dir = 4;
	}
	
	/**
	 * Creates a default enemy object that has velocity,
	 * health, damage, direction at a random location
	 * @param x the x coordinate of this enemy
	 * @param y the y coordinate of this enemy
	 */
	public Enemy() {
		super(Math.random()*100,Math.random()*100,0,0,20,25);
		this.health = 100;
		this.damage = 0; 	
		this.dir = 4;
	}
	
	
	/**
	 * Return the damage this enemy deals
	 * @return the damage value this enemy deals
	 */
	public double getDmg()
	{
		return damage;
	}
	
	/**
	 * Return the health of this enemy 
	 * @return the health value of this enemy 
	 */
	public double getHealth()
	{
		return health;
	}

	/**
	 * Set the velocity of this enemy to a new velocity 
	 * @param xVel the x component of this enemy's velocity
	 * @param yVel the y component of this enemy's velocity
	 */
	public void setVelocity(double xVel, double yVel) {
		if(xVel > 0) {
			this.dir = 3;
		}
		else if (xVel < 0){
			this.dir = 1;
		}
		else if(yVel > 0) {
			this.dir = 4;
		}
		else if(yVel < 0) {
			this.dir = 2;
		}
		
		this.setxVel(xVel);
		this.setyVel(yVel);
	}

	/**
	 * Return the direction angle of this enemy 
	 * @return the direction value of this enemy 
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * Sets the direction of this enemy to direction that could
	 * either be 1,2,3,4
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}
		
}
