import adsouza.shapes.Rectangle;
import processing.core.PApplet;
/**
 * 
 * @author Aaron Dsouza
 *
 *Super class that has x,y , and velocity, and a rectangle hitbox
 */
public abstract class GameEntity {

	private double x,y;
	private double xVel, yVel;
	private Rectangle rect;
	private int time;
	
	/**
	 * Creates a GameEnetity object default xVelocity, yVelocity, 
	 * x and y coordinates, bounding rectangle's height and width
	 */
	public GameEntity() {
		this.x = 50;
		this.y = 50;
		rect = new Rectangle();
		xVel = 0;
		yVel = 0;
		this.time = 0;
	}
	/**
	 * Creates a GameEnetity object with custom xVelocity, yVelocity, 
	 * x and y coordinates, bounding rectangle's height and width
	 * @param x  x position of the Entity
	 * @param y y position of the Entity
	 * @param xVel x Velocity of the Entity
	 * @param yVel Y Velocity of the Entity
	 * @param rWidth Width of the entity's hit box
	 * @param rHeight Height of the entity's hit box
	 */
	public  GameEntity(double x, double y, double xVel, double yVel, double rWidth, double rHeight) {
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, rWidth, rHeight);
		rect.setNoFill();
		rect.setStrokeWeight(5);
		this.xVel = xVel;
		this.yVel = yVel;
		time = 0;
	}
	/** 
	 * Creates a GameEnetity object with default xVelocity, yVelocity, 
	 * bounding rectangle's height and width, and custom x and y 
	 * coordinates
	 * @param x x position of the Entity
	 * @param y y position of the Entity
	 */
	public GameEntity(double x, double y) {
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x + 25, y + 25, 50, 50);
		rect.setNoFill();
		rect.setStrokeWeight(5);
		this.xVel = 0;
		this.yVel = 0;
	}
	/**
	 * 
	 * @return the time since the entity last attacked with its weapon
	 */
	public double getTimeSinceFire() {
		return time;
	}
	/**
	 * 
	 * @param t the new time since the entity last attacked with its weapon
	 */
	public void setTimeSinceFire(int t) {
		this.time = t;
	}
	/** 
	 * Draws the bounding Rectangle of this box
	 * @param drawer the PAppler that draws the hitBox
	 * rectangle
	 */
	public void drawHitBox(PApplet drawer)
	{
		getRect().draw(drawer);
	}
	/**
	 * 
	 * @return x position of the Entity
	 */
	public double getX() {
		return x;
	}
	/**
	 * 
	 * @param x new x position of the Entity
	 */
	public void setX(double x) {
		this.x = x;
		this.rect.move(x, y);
	}
	/**
	 * 
	 * @return y position of the Entity
	 */
	public double getY() {
		return y;
	}
	/**
	 * 
	 * @param y new y position of the Entity
	 */
	public void setY(double y) {
		this.y = y;
		this.rect.move(x, y);
	}
	/**
	 * 
	 * @return x Velocity of the Entity
	 */
	public double getxVel() {
		return xVel;
	}
	/**
	 * 
	 * @return y Velocity of the Entity
	 */
	public double getyVel() {
		return yVel;
	}
	/**
	 *	Changes the xVel value of this GameEntity 
	 * @param xVel New x Velocity of the Entity
	 */
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	/**
	 * 
	 * @param yVel New y Velocity of the Entity
	 */
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	/**
	 * 
	 * @param xVel New x Velocity of the Entity
	 * @param yVel New y Velocity of the Entity
	 */
	public abstract void setVelocity(double xVel, double yVel);
	
	/**
	 * 
	 * @return The rectangle object that represents the entity's hit box.
	 */
	public Rectangle getRect() {
		return rect;
	}
	/**
	 * Sets the bounding rectangle of this GameEntity to rect
	 * @param rect The new rectangle object that represents the entity's hit box.
	 */
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	/**
	 * @post the x and y coordinate values of this 
	 * GameEntity will be changed based on x and y 
	 * Velocities
	 */
	public void step() 
	{
		x += xVel;
		y += yVel;		
	}
}
