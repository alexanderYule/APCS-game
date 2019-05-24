import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;


public class HealthBooster {

	private boolean isMega ;
	private int x ;
	private int y ;
	
	private PImage potion ;
	private PImage booster ;
	
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

	public boolean isMega() {
		return isMega;
	}

	public void setMega(boolean isMega) {
		this.isMega = isMega;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * draws the structure at it's x,y postion
	 * @param drawer the object that draws the structure
	 * @param obstacle the obstacle image that is to be drawn
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
