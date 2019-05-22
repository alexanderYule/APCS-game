import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

public class TransportPad extends Structure
{
	private int x;
	private int y;
	private Rectangle hitBox;
	private boolean canGo;
	public TransportPad(int x, int y)
	{
		super(x,y);
		hitBox = new Rectangle(x,y,40,40);
		canGo = false;
	}
	public void setCanGo(boolean b)
	{
		canGo =b;
	}
	public void draw(PApplet drawer, PImage obstacle)
	{
		if(canGo)
			super.draw(drawer, obstacle);
		else
			super.draw(drawer, drawer.loadImage("Resorces/portal.jpg"));
	}
	
}
