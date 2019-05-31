import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author sumukhi p
 * Represents objects that increasing health throughout the game
 */
public class HealthBooster {

	private boolean isMega ;
	private int x ;
	private int y ;
	
	private PImage potion ;
	private PImage booster ;
	
	/**
	 * Creates a custom HealthBooster object with (x,y)
	 * coordinates with a isMega characteristic
	 * @param x the x coordinate of this object
	 * @param y the y coordinate of this object
	 * @param isMega the status of this HealthBooster
	 */
	public HealthBooster(int x , int y, boolean isMega) {
		this.x = x ;
		this.y = y ;
		this.isMega = isMega ;
	}

	/**
	 * 
	 * @return the rectangle object that serves the structures hitbox.
	 */
	public Rectangle getHitBox()
	{
		return (new Rectangle(x,y,30,30));
	}

	/**
	 * 
	 * @return whether this HealthBooster isMega
	 */
	public boolean isMega() {
		return isMega;
	}

	/**
	 * @param isMega the new isMega value will
	 * be set to
	 */
	public void setMega(boolean isMega) {
		this.isMega = isMega;
	}

	/**
	 * 
	 * @return the x coordinate of this object
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets the x cocrdinate of this HealthBooster 
	 * to the new x
	 * @param x the new x coordinate value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return the y coordinate of this object
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y cocrdinate of this HealthBooster 
	 * to the new y
	 * @param y the new y coordinate value
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * draws the HealthBooster at it's x,y postion
	 * @param drawer the object that draws the structure
	 * @pre drawer must not be null
	 */
	public void draw(PApplet drawer)
	{
		if(potion == null) {
			potion = drawer.loadImage("Resorces/potion.png");
			potion.resize(40, 40);
		}

		if(booster == null) {
			booster = drawer.loadImage("Resorces/booster.png");
			booster.resize(40, 40);
		}

		if(isMega)
			drawer.image(booster, x, y);
		else 
			drawer.image(potion, x, y);
	}
	
}
