
public class Enemy 
{
	private double damage; //WHAT DOES DAMAGE EXACTLY STAND FOR?
	private double health;
	private double x,y;
	private double xVel, yVel;
	private int dir; //1=left, 2=up, 3=right, 4=down
	 
	/**
	 * Creates an enemy object that has velocity,
	 * health, damage, direction at a certain location
	 * @param x the x coordinate of this enemy
	 * @param y the y coordinate of this enemy
	 */
	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
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
		this.x = Math.random()*100;
		this.y = Math.random()*100;
		this.health = 100;
		this.damage = 0; 	
		this.dir = 4;
	}

	/**
	 * Return the x coordinate of this bullet
	 * @return the x coordinate value of this bullet
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Return the y coordinate of this bullet
	 * @return the y coordinate value of this bullet
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Sets the x coordinate to a new value
	 * @param x the new x value enemy's location
	 */
	public void setX(double x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate to a new value
	 * @param y the new y value enemy's location
	 */
	public void setY(double y)
	{
		this.y = y;
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
	 * Return the xVelocity of this enemy 
	 * @return the xVelocity value of this enemy 
	 */
	public double getxVel() {
		return xVel;
	}

	/**
	 * Return the yVelocity of this enemy 
	 * @return the yVelocity value of this enemy 
	 */
	public double getyVel() {
		return yVel;
	}
	
	/**
	 * Set the velocity of this enemy to a new velocity 
	 * @param xVel the x component of this enemy's velocity
	 * @param yVel the y component of this enemy's velocity
	 */
	public void setVel(double xVel, double yVel) {
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
		
		this.xVel = xVel;
		this.yVel = yVel;
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
