import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Structures the obstacles in the room.
 * @author sumukhipandey
 */
public class Structure 
{
	private int x;
	private int y;
	private Rectangle hitBox;
	private boolean isWall;
	private boolean isExit;
	/**
	 *  Creates a structure at the given spot x,y in the 23 by 23 array that represents the room, the object also has a Rectangle that acts as the structure's hit box
	 * @param x the x coordinate of the structure in an 23 by 23 array that is the room
	 * @param y the y coordinate of the structure in an 23 by 23 array that is the room
	 */
	public Structure(int x, int y)
	{
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x,y,40,40);
		isWall = true;
	}
	public Structure(Rectangle r, boolean isExit)
	{
		x = (int) r.getX();
		y = (int) r.getY();
		hitBox = r;
		isWall = false;
		this.isExit = isExit;
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
	 * @return true is this Structure is an exit
	 */
	public boolean isExit()
	{
		return isExit;
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
	 * Draws the structure at it's x,y position
	 * @param drawer the object that draws the structure
	 * @param obstacle the obstacle image that is to be drawn
	 * @pre drawer must not be null
	 */
	public void draw(PApplet drawer, PImage obstacle)
	{
		if(isWall)
			drawer.image(obstacle, x, y);
		else
		{
			drawer.pushStyle();
			drawer.fill(139,69,13);
			drawer.noStroke();
			drawer.rect(x, y, (float)hitBox.getWidth(),(float) hitBox.getHeight());
			drawer.popStyle();
			
		}	
		
	}
	
}
