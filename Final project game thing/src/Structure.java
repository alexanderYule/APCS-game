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
	public Structure(int x, int y)
	{
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x*40,y*40,40,40);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	public void draw(PApplet drawer, PImage obstacle, int x, int y)
	{
		if(this.x* 40 == x && this.y * 40 == y)
			drawer.image(obstacle, x, y);
	}
}
