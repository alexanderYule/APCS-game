import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;
/**
 * 
 * @author alex Y
 * The class that represents the player of the game.
 */
public class Player extends GameEntity
{
	private double health;
	private Gun g;
	private int dir; //1=left, 2=left & up, 3=up, 4= right & up 5 = right 6 = right & down 7 = down 8 = left & down
	private boolean up,down,left,right;
	private int time;
	private boolean canLeaveRoom;
	/**
	 * creates a player object with a Gun, at location x,y with 100 health
	 * @param x the x coordinate of the player.
	 * @param y the y coordiante of the player.
	 */
	public Player(double x, double y)
	{
		super(x,y,0,0,30,30);
		g = new Gun(50,400,1,1,400,150);
		dir = 0;
		up = false;
		down = false;
		left = false;
		right = false;
		health = 100;
		time = 0;
		canLeaveRoom = false;
		
		
	}
	/**
	 * draws the player
	 * @param drawer - object that draws the player
	 */
	public void draw(PApplet drawer) {
//		if(dir == 1) { //LEFT
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/left.png"), (int)getX(), (int)getY());
//		}
//		else if(dir == 2) { //UP
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/up.png"), (int)getX(), (int)getY());
//		}
//		else if(dir == 3) { //RIGHT
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/right.png"), (int)getX(), (int)getY());
//		}
//		else {  //DOWN
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/standingDown.png"), (int)getX(), (int)getY());
//		}		
		
		drawer.pushStyle();
		drawer.fill(255);
		drawer.rect(0,921, 919, 60);
		drawer.fill(0);
		drawer.textSize(25);
		drawer.textSize(25);
		drawer.text("Health", 100, 960);
		drawer.fill(255,0,0);
		drawer.rect(200,940,300,20,75);
		drawer.fill(255);
		
		if(health != 100) {
			if(health < 0) {
				drawer.fill(255);
				drawer.rect(200,940,(float)300,20,75,75,75,75);
			}
			else {
				drawer.rect(500,940,(float)(health/100)*300 - 300,20,0,75,75,0);
			}
		}
		
		drawer.popStyle();
		
		drawer.noFill();
		drawer.strokeWeight(5);
		getRect().draw(drawer);
	}
	/**
	 * Fires the weapon that the player has, needs the milliseconds to determine when the player has last fired so that the player cannot continuously fire.
	 * @param targetX the x coordinate of where the weapon is to be fired
	 * @param targetY the y coordinate of where the weapon is to be fired
	 * @param millis the amount of time has passed since the program has begin in milliseconds.
	 */
	public void fireWeapon(int targetX, int targetY, int millis)
	{
		if((millis - time)/1000.0 >g.getAttackSpeed())
		{
			time = millis;
			double vx = targetX - getX();
			double vy = targetY - getY();
	
			double angle = 0;
	
			angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
			
			g.fireBullet(getX()-20, getY()-20, angle, true);
		}
	}
	/**
	 * The player takes damage
	 * @param dmg the amount of heath the player is to take.
	 */
	public void takeDamage(double dmg)
	{
		health -= dmg;
	}
	/**
	 * 
	 * @return the existing bullets that has been fired from the gun the player has
	 */
	public ArrayList<Bullet> getExistingBullets()
	{		
		return g.getExistingBullets();
	}
	/**
	 * 
	 * @return the gun object the player has
	 */
	public Gun getGun()
	{
		return g;
	}
	/**
	 * 
	 * @return the amount of health the player has.
	 */
	public double getHealth()
	{
		return health;
	}
	/**
	 * 
	 * @return the direction the player is facing where 1=left, 2=left & up, 3=up, 4= right & up 5 = right 6 = right & down 7 = down 8 = left & down
	 */
	public int getDir() {
		return dir;
	}
	/**
	 * 
	 * @param x if x is true the player is to move up, otherwise the player is no longer moving up.
	 */
	public void setup(boolean x)
	{
		up = x;
		if(x && down)
			down = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move down, otherwise the player is no longer moving down.
	 */
	public void setdown(boolean x)
	{
		down = x;
		if(x && up)
			up = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move left, otherwise the player is no longer moving left.
	 */
	public void setleft(boolean x)
	{
		left = x;
		if(x && right)
			right = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move right, otherwise the player is no longer moving right.
	 */
	public void setright(boolean x)
	{
		right = x;
		if(x && left)
			left = false;
	}
	/**
	 * stops the player from moving entirely
	 */
	public void notMoving()
	{
		up = false;
		down = false;
		right = false;
		left = false;
				
	}
	public void setVelocity(double xVel, double yVel) {
		setxVel(xVel);
		setyVel(yVel);
	}
	/**
	 * 
	 * @param canLeave whether the player can or can't leave the current room.
	 */
	public void setRoomStat(boolean canLeave) {
		this.canLeaveRoom = canLeave;
	}
	/**
	 * 
	 * @return whether the player can or can't leave the current room.

	 */
	public boolean getRoomStat() {
		return this.canLeaveRoom;
	}
	/**
	 * moves the player and stops him if he hits an obstruction
	 * @param structures - the structures in the room that the player is in.
	 */
	public void move(ArrayList<Structure> structures) 
	{
		if(up)
			setyVel(-5);
		if(down)
			setyVel(5);
		if(right)
			setxVel(5);
		if(left)
			setxVel(-5);
		
		boolean colDetected = false;
		Rectangle struc = getRect();
		
		if(this.getRoomStat() == false && this.getX() >= 900) {
				colDetected = false;
			}
		
 		for(int x = 0; x < structures.size(); x++)
 		{
 			Structure str  = structures.get(x);
			if(str.getHitBox().intersects(getRect())) {
				colDetected = true;
				struc = str.getHitBox();
			}
 		}
		
		
 		if(!colDetected) {
			setX(getX() + getxVel());
			setY(getY() + getyVel());
 		}
 		else {

 			
 			if(up)
 			{
 				if(getY() < struc.getY())
 				{
 					setY(getY() + getyVel());
 				}
 			}
 			if(down)
 			{
 				if(getY() > struc.getY())
 				{
 					setY(getY() + getyVel());
 				}
 			}
 			if(left)
 			{
 				if(getX() < struc.getX())
 				{
 					setX(getX() + getxVel());
 				}
 			}
 			if(right)
 			{	
 				if(getX() > struc.getX())
				{
 					setX(getX() + getxVel());
				}
 			}
			
 		}
 		getRect().move(getX(), getY());
 		setVelocity(getxVel()*0.3, getyVel()*0.3);
		
		if(getxVel() > 0) 
		{
			if(Math.abs(getyVel()) < 0.05)
				dir = 5; // only right
			else if(getyVel() < 0)
				dir = 4; // right and up
			else
				dir = 6; // right and down
		}
		else if (getxVel() < 0)
		{
			if(Math.abs(getyVel()) < 0.05)
				dir = 1; // only left
			else if(getyVel() < 0) 
				dir = 4; //left and up
			else
				dir = 6; //left and down
		}
		else if(getyVel() < 0) {
			dir = 3;
		}
		else {
			dir = 7;
		}
	}

	

}
