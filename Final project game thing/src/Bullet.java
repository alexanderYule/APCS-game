import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author ayule801
 *
 * Represents a bullet fired by a gun
 */
public class Bullet 
{
	private double x,y;
	private double xVelocity;
	private double yVelocity;
	private double damage;
	private boolean isGood; //if both ranged enemies and the player intend on using this class then it makes sense 
	private double speed;
	/**
	 * Creates a Bullet object that travels across the screen at an
	 * angle, speed, and certain amount of damage
	 * @param x the x coordinate of the bullet
	 * @param y the y coordinate of the bullet
	 * @param direction the angle at which this bullet is moving to the horizontal
	 * @param speed the speed of the bullet
	 * @param damage the amount of damage the bullet deals
	 * @param isGood if the player fires a bullet it "isGood", other wise it is not so that the enemies can't kill each other, and the player can't run into its own bullet.
	 */
	public Bullet(double x, double y, double direction, double speed, double damage, boolean isGood)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		yVelocity = speed*Math.sin(Math.toRadians(direction));
		xVelocity = speed*Math.cos(Math.toRadians(direction));
		this.damage = damage;
		this.isGood = isGood;
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
	 * Moves this bullet to a new location (x,y)
	 * @post the x and y coordinate values of this bullet 
	 * will change 
	 */
	public boolean move()
	{
		y += xVelocity/20;
		x += yVelocity/20;
		if(x >= 870 || x <= 0 || y >= 900 || y <= 0)
			 return true;
		return false;
	}
	public void draw(PApplet drawer, PImage Bullet)
	{
		drawer.image(Bullet,(int)x,(int)y);
	}

}
