import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Structures the obstacles in the room.
 * code TBD
 * @author sumukhipandey
 */
public class Structure 
{
	private int x;
	private int y;
	private Rectangle hitBox;
	/**
	 *  creates a structure at the given spot x,y in the 23 by 23 array that represents the room, the object also has a Rectangle that acts as the structure's hit box
	 * @param x the x coordinate of the structure in an 23 by 23 array that is the room
	 * @param y the y coordinate of the structure in an 23 by 23 array that is the room
	 */
	public Structure(int x, int y)
	{
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x*40,y*40,40,40);
	}
	/**
	 * 
	 * @return the x coordinate of the object
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * 
	 * @return the y coordinate of the object
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * 
	 * @return the rectangle object that serves the structures hitbox.
	 */
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	/**
	 * draws the structure at it's x,y postion
	 * @param drawer the object that draws the structure
	 * @param obstacle the obstacle image that is to be drawn
	 */
	public void draw(PApplet drawer, PImage obstacle)
	{
		//if(this.x* 40 == x && this.y * 40 == y)
			drawer.image(obstacle, x*40, y*40);
	}
	
}
