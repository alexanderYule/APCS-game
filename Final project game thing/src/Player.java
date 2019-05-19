import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;

public class Player extends GameEntity
{
	private double damage;
	private double health;
	private Gun g;
	private int dir; //1=left, 2=left & up, 3=up, 4= right & up 5 = right 6 = right & down 7 = down 8 = left & down
	private boolean up,down,left,right;
	private int time;
	
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
	}

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
	public void takeDamage(double dmg)
	{
		health -= dmg;
	}
	public ArrayList<Bullet> getExistingBullets()
	{		
		return g.getExistingBullets();
	}
	public Gun getGun()
	{
		return g;
	}
	public double getDmg()
	{
		return damage;
	}
	
	public double getHealth()
	{
		return health;
	}
	
	public int getDir() {
		return dir;
	}
	public void setup(boolean x)
	{
		up = x;
		if(x && down)
			down = false;
	}
	public void setdown(boolean x)
	{
		down = x;
		if(x && up)
			up = false;
	}
	public void setleft(boolean x)
	{
		left = x;
		if(x && right)
			right = false;
	}
	public void setright(boolean x)
	{
		right = x;
		if(x && left)
			left = false;
	}
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
 			/*setxVel(getxVel()*-1);
 			setyVel(getyVel()*-1);
 			setX(getX() + getxVel());
 			setY(getY() + getyVel());*/

 			
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
