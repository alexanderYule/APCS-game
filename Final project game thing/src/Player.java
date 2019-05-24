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
	private DrawingSurface d ;
	private PImage image;
	private int hardDir;
	private int counter;

	/**
	 * creates a player object with a Gun, at location x,y with 100 health
	 * @param x the x coordinate of the player.
	 * @param y the y coordiante of the player.
	 */
	public Player(double x, double y)
	{
		super(x,y,0,0,25,45);
		g = new Gun(50,400,1,1,400,150);
		//g = new Gun(100,400,1,0.1,400,150); // damage, range (WIP), number of bullets (WIP), fire rate(fires per second), bullet speed, ID
		dir = 1;
		up = false;
		down = false;
		left = false;
		right = false;
		health = 100;
		canLeaveRoom = false;
		d = null ;
		image = null ;
		hardDir = 0;
		counter = 0;
	}
	
	/**
	 * draws the player
	 * @param drawer - object that draws the player
	 */
	public void draw(DrawingSurface drawer, PImage up, PImage down, PImage right, PImage left)
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
		d = drawer;
		/*if(image == null) {
			image = d.loadImage("Resorces/hero_sprites/standingDown.png") ;
		}*/

		Rectangle r = getRect() ;
		int xoffset = (int) (r.getWidth()) ;
		int yoffset = (int) (r.getHeight()/2) ;
		//drawer.image(image,  (int)getX()-xoffset, (int)getY()-yoffset);

		
		drawer.pushStyle();
		drawer.strokeWeight(3);
		drawer.fill(255);
		drawer.fill(0);
		drawer.textSize(20);

		String levelStr = "Level " + (d.getLevelNumber()+1) + "   Room " + (d.getRoomNumber()+1);
		
		drawer.text(levelStr, 20, 28);

		drawer.text("Health", 530, 28);
		drawer.fill(255,0,0);
		drawer.rect(600,10,300,20,75,75,75,75);
		drawer.fill(255);
		
		if(health != 100) {
			if(health < 0) {
				drawer.fill(255);
				drawer.rect(200,10,(float)300,20,75,75,75,75);
			}
			else {
				drawer.rect(900,10,(float)(health/100)*300 - 300,20,0,75,75,0);
			}
		}
		
		drawer.popStyle();
		
		//drawer.noFill();
		//drawer.strokeWeight(5);
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
		
		if(health <= 0) {
			d.setGameOver();
			return ;
		}
		
		//image = d.loadImage("Resorces/hero_sprites/left.png") ;
/*		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				image = d.loadImage("Resorces/hero_sprites/standingDown.png") ;
				draw(d);				
			}
			
		};
		Timer t = new Timer() ;
		t.schedule(tt, 500);
		//draw(d);*/
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
	 * @param boosters - HEalth boosters in the room
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
