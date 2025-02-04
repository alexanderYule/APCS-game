import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
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
	private boolean canLeaveRoom;
	private PImage image;
	private int hardDir;
	private int counter;

	/**
	 * Creates a Player object with a Gun, at location x,y with 100 health
	 * @param x the x coordinate of the player.
	 * @param y the y coordiante of the player.
	 */
	public Player(double x, double y)
	{
		super(x,y,0,0,25,45);
		getNewGun(0);
		dir = 1;
		up = false;
		down = false;
		left = false;
		right = false;
		health = 100;
		canLeaveRoom = false;
		image = null ;
		hardDir = 0;
		counter = 0;
	}
	/**
	/**
	 * Draws the instances that pertain to the Player object
	 * @param drawer the object used to draw the Player  on the screen
	 * @param up the up facing image of this Player
	 * @param down the down facing image of this Player
	 * @param right the right facing image of this Player
	 * @param left the left facing image of this Player
	 * @pre drawer must not be null
	 */
	public void draw(PApplet drawer, PImage up, PImage down, PImage right, PImage left)
	{
		if(hardDir == 0)
		{
			if(dir == 1 || dir == 2 || dir == 8)  //LEFT
				drawer.image(left, (int)getX(), (int)getY());
			
			else if(dir == 4 || dir == 5 || dir == 6)  //right
				drawer.image(right, (int)getX(), (int)getY());	
			else if(dir == 3)  //up
				drawer.image(up, (int)getX(), (int)getY());
			
			else if (dir == 7)  //DOWN
				drawer.image(down, (int)getX(), (int)getY());				
		}
		else
		{
			counter++;
			if(hardDir == 1)  //LEFT
				drawer.image(left, (int)getX(), (int)getY());
			
			else if(hardDir == 2)  //right
				drawer.image(up, (int)getX(), (int)getY());
			
			else if(hardDir == 3)  //up
				drawer.image(right, (int)getX(), (int)getY());
			
			else if (hardDir == 4)  //DOWN
				drawer.image(down, (int)getX(), (int)getY());
			if(counter == 20)
			{
				counter = 0; 
				hardDir = 0;
			}
		}
	}
	/**
	 * Changes the gun this Player has based on the ID
	 * @param ID the ID of the new gun
	 */
	public void getNewGun(int ID)
	{
		//damage, range (WIP), fire rate(fires per second), bullet speed, ID (doesn't do anything),sprayRange, number of bullets
		switch(ID)
		{
			case 0:
				g = new Gun(25,0,0.75,400,150,false );// default gun
				break;
			case 1:   // 1-3 is first wave
				g = new Gun(34,0,0.75,400,ID,false ); // slightly faster attack speed, slightly more damage
				break;
			case 2:
				g = new Gun(5,0,0.1,400,ID,true); // much faster attack speed much less damage
				break;
			case 3:
				g = new Gun(8,0,1.25,250,ID,false, 20,7); // shotgun
				break;
			case 4: // 4-6 is second wave
				g = new Gun(10,0,0.1,400,ID,true, 3,2); //twin machine gun
				break;
			case 5:
				g = new Gun(12,0,1,400,ID,false,20,7 ); //upgraded shotgun
				break;
			case 6:
				g = new Gun(25,0,0.75,400,ID,false ); 
				break;
			case 100:
				g = new Gun(100,0,0.1,400,150,false,30,5 ); //op gun for testing purposes
				break;
		}
	}
	/**
	 * Fires the weapon that the player has, needs the milliseconds to determine when the player has last fired so that the player cannot continuously fire.
	 * @param targetX the x coordinate of where the weapon is to be fired
	 * @param targetY the y coordinate of where the weapon is to be fired
	 * @param millis the amount of getTimeSinceFire() has passed since the program has begin in milliseconds.
	 */
	public void fireWeapon(int targetX, int targetY, int millis)
	{

		if((millis - getTimeSinceFire())/1000.0 >g.getAttackSpeed())
		{
			setTimeSinceFire(millis);
			double vx = targetX - getX();
			double vy = targetY - getY();
	
			double angle = 0;
	
			angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
			g.fireBullet(getX(), getY(), angle, true);
			if(angle > 45 && angle <= 135)
			{
				hardDir = 3;
				dir = 4;
			}
			else if (angle > 135 && angle <= 205)
			{
				hardDir = 2;
				dir = 3;
			}
			else if (angle > 205 || angle <= -45)
			{
				hardDir = 1;
				dir = 1;
			}
			else if(angle > -45 && angle <= 45)
			{
				hardDir = 4;
				dir = 7;
			}
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
	 * @return the direction the player is facing where 1=left, 
	 * 2 is left & up, 3 is up, 4 is right & up 5 is right 6 is 
	 * right & down 7 is down 8 is left & down
	 */
	public int getDir() {
		return dir;
	}
	/**
	 * 
	 * @param x if x is true the player is to move up, otherwise the player is no longer moving up.
	 */
	public void setUp(boolean x)
	{
		up = x;
		if(x && down)
			down = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move down, otherwise the player is no longer moving down.
	 */
	public void setDown(boolean x)
	{
		down = x;
		if(x && up)
			up = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move left, otherwise the player is no longer moving left.
	 */
	public void setLeft(boolean x)
	{
		left = x;
		if(x && right)
			right = false;
	}
	/**
	 * 
	 * @param x if x is true the player is to move right, otherwise the player is no longer moving right.
	 */
	public void setRight(boolean x)
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
	/**
	 * Sets the x and y velocities to the
	 * respective parameters
	 * @param xVel the new xVelocity of this Player
	 * @param yVel the new yVelocity of this Player

	 */
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
	 * Moves the player and stops him if he hits an obstruction
	 * @param structures - the structures in the room that the player is in.
	 * @param boosters - Health booster in the room
	 * @param room the Room object 
	 * @return true if the player's move was valid or false otherwise
	 */
	public boolean move(ArrayList<Structure> structures, ArrayList<HealthBooster> boosters,Room room) 
	{
		//Room room = DrawingSurface.getCurrentRoom() ;
		if(up)
			setyVel(-4);
		if(down)
			setyVel(4);
		if(right)
			setxVel(4);
		if(left)
			setxVel(-4);
		
		boolean xColDetected = false;
		boolean yColDetected = false;
		Rectangle h = getRect();
		Rectangle potentialXHitBox = new Rectangle(h.getX() + getxVel(), h.getY(), h.getWidth(),h.getHeight());
		Rectangle potentialYHitBox = new Rectangle(h.getX(), h.getY() + getyVel(), h.getWidth(),h.getHeight());

		/*
		colDetected = room.findCollison(potentialHitBox);
		if(room.enemyCount() <= 0 && room.canExit()) {
			notMoving();
		}
		*/
		
		if(health < 100) {
			for(HealthBooster b : boosters) {
				if( b.getHitBox().intersects(potentialXHitBox) ||
						b.getHitBox().intersects(potentialYHitBox)) {
					if(b.isMega()) {
						health = 100 ;
					} else { 
						health += 10 ;
					}
					if(health > 100) 
						health = 100 ;
					room.removeHealthBooster(b);
					break ;
				}
			}
		}
		
		for(int x = 0; x < structures.size(); x++)
 		{
 			Structure str  = structures.get(x);
			if(str.getHitBox().intersects(potentialXHitBox)) 
			{
				if(str.isExit() && canLeaveRoom)
				{
					return true;
				}
				xColDetected = true;
			}
			if(str.getHitBox().intersects(potentialYHitBox)) 
			{
				if(str.isExit() && canLeaveRoom)
				{
					return true;
				}
				yColDetected = true;
			}
 		}
 		
		
 		if(!xColDetected) {
			setX(getX() + getxVel());
 		}
 		
 		if(!yColDetected)
 		{
 			setY(getY() + getyVel());
 		}
 		
 		
 		getRect().move(getX() + 5, getY()); // + 5 to make it closer to the actual player sprite
 		setVelocity(getxVel()*0.3*-1, getyVel()*0.3*-1);
		
		if(getxVel() < -1) 
		{
			if(Math.abs(getyVel()) < 0.05)
				dir = 5; // only right
			else if(getyVel() < 0)
				dir = 4; // right and up
			else
				dir = 6; // right and down
		}
		else if (getxVel() > 1)
		{
			if(Math.abs(getyVel()) < 0.05)
				dir = 1; // only left
			else if(getyVel() < 0) 
				dir = 2; //left and up
			else
				dir = 8; //left and down
		}
		else if(getyVel() < -1) {
			dir = 7;
		}
		else if(getyVel() > 1){
			dir = 3;
		}
		return false;
	}

	

}
