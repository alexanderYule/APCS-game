import java.util.ArrayList;

import adsouza.shapes.Line;
import adsouza.shapes.Rectangle;
import processing.core.PApplet;

/**
 * 
 * @author ayule801
 *
 *super class that represents the enemies that the player needs to fight
 */
public class Enemy extends GameEntity
{
	private double damage; 
	private double health;
	private int dir; //1=left, 2=up, 3=right, 4=down
	private boolean isAlive;
	private final int maxHealth;

	 
	/**
	  * Creates a default enemy object that with
	 * x/ y coordinates, health, damage, direction, and a
	 * bounding Rectangle
	 * @param x the x coordinate of this enemy
	 * @param y the y coordinate of this enemy
	 * @param maxHealth the health of this enemy
	 */
	public Enemy(double x, double y, int maxHealth) {
		super(x,y,0,0,20,40);
		this.maxHealth = maxHealth;
		this.health = 100;
		this.damage = 0; 
		this.dir = 4;
		isAlive = true;
	}
	/**
	 * Creates an enemy object that has a custom
	 * x/ y coordinate health, damage, direction, and a
	 * bounding Rectangle
	 * certain location
	 * @param x the x coordinate of this enemy
	 * @param y the y coordinate of this enemy
	 * @param rectWidth the width of the Rectangle that Bounds this Enemy
	 * @param rectHeight the width of the Rectangle that Bounds this Enemy
	 * @param maxHealth the health of this enemy
	 */
	public Enemy(double x, double y, int maxHealth, int rectWidth, int rectHeight)
	{
		super(x,y,0,0,rectWidth,rectHeight);
		this.maxHealth = maxHealth;
		this.health = 100;
		this.damage = 0; 
		this.dir = 4;
		isAlive = true;
	}
	/**
	 *  Draws a new instance of the Enemy object
	 *  @param drawer PApplet used to create a representation of the Circle object
	 *  @pre drawer must not be be null
	*/
	public void draw(PApplet drawer)
	{
		drawer.pushStyle();
		drawer.fill(255,0,0);
		drawer.rect((int)getX(),(int)(getY() + getRect().getHeight()+ 10),(int) getRect().getWidth(), -5);
		drawer.fill(0,0,0);
		drawer.rect((int)(getX() + getRect().getWidth()) , (int)(getY() + getRect().getHeight()+ 10) , (int) (health/maxHealth * getRect().getWidth() - getRect().getWidth()) , -5);
		drawer.popStyle();
	}
	/**
	 * Draws the bounding rectangle of this enemy
	 * @param drawer the hitBox of this enemy
	 */
	public void drawHitBox(PApplet drawer)
	{
		if(isAlive())
			super.drawHitBox(drawer);
	}
	/**
	 * Creates a default enemy object that has velocity,health, damage, 
	 * direction at a random location.
	 */
	public Enemy() {
		super(Math.random()*100,Math.random()*100,0,0,20,25);
		maxHealth = 100;
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
	 * 
	 * @return the maximum health the enemy can have
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}
	/**
	 * 
	 * @param damage how much damage the enemy takes
	 * @return true if the enemy's health is zero or below, false otherwise.
	 */
	public boolean takeDmg(double damage) 
	{
		health -= damage;
		if(health <= 0)
			return true;
		return false;
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
	 * Kills this enemy
	 * @post isAlive value of this 
	 * enemy is set to false
	 */
	public void die()
	{
		isAlive = false;
	}
	/**
	 * @return the isAlive status of this enemy
	 */
	public boolean isAlive()
	{
		return isAlive;
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
	public boolean findCollitions(ArrayList<Structure> structures)
	{
		boolean found = false ;
		for(Structure r : structures) {
			if(r.getHitBox().intersects(getRect())) 
			{
				found = true;
			}
		}

		return found ;
	}
	/**
	 * Draws a line between this enemy and the player to check whether the enemy
	 * can see the player
	 * @param p the Player object
	 * @param structures the list of all structures to check 
	 * @return true if the enemy can see the player or false otherwise
	 */
	public boolean canSeePlayer(Player p, ArrayList<Structure> structures)
	{
		Rectangle rect = getRect() ;
		int rx = (int) (rect.getX() + rect.getWidth()/2) ;
		int ry = (int) (rect.getY() + rect.getHeight()/2) ;
		Rectangle prect = p.getRect() ;
		int px = (int) (prect.getX() + prect.getWidth()/2) ;
		int py = (int) (prect.getY() + prect.getHeight()/2) ;
		
		Line l = new Line(rx,ry,px,py);
	
		boolean found = true ;
		for(Structure r : structures) {
			if(r.getHitBox().intersects(l)) {
				found = false ;
			}
		}
		return found;
	}

	/**
	 * Sets the direction of this enemy to direction that could
	 * either be 1,2,3,4
	 * @param dir the new direction of this enemy
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}
		
}
