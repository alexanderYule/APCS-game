import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;

public class Player extends GameEntity
{
	private double damage;
	private double health;
	private double frict;
	private Gun g;
	private int dir; //1=left, 2=left & up, 3=up, 4= right & up 5 = right 6 = right & down 7 = down 8 = left & down
	private boolean up,down,left,right;
	
	public Player(double x, double y)
	{
		super(x,y,0,0,20,25);
		g = new Gun(1,400,1,1,200,150);
		dir = 0;
		this.frict = 0.95;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	public void draw(PApplet drawer, ArrayList<Structure> s) {
	
		this.act(s);

//		if(dir == 1) { //LEFT
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/left.png"), (int)x, (int)y);
//		}
//		else if(dir == 2) { //UP
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/up.png"), (int)x, (int)y);
//		}
//		else if(dir == 3) { //RIGHT
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/right.png"), (int)x, (int)y);
//		}
//		else {  //DOWN
//			drawer.image(drawer.loadImage("Resorces/hero_sprites/standingDown.png"), (int)x, (int)y);
//		}
		

		if((this.getX() >= 900 || this.getX() <= 0)) {
			setxVel(-super.getxVel());
		}
		
		if(this.getY() <= 0 ||this.getY() >= 900) {
			setyVel(-super.getyVel());
		}
		
		drawer.noFill();
		drawer.strokeWeight(5);

		drawer.rect((int)getX(),(int) getY(), (int)this.getRect().getWidth(), (int)this.getRect().getHeight());
	}

	public void fireWeapon(int targetX, int targetY)
	{
		double vx = targetX - getX();
		double vy = targetY - getY();

		double angle = 0;

		angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
	
		g.fireBullet(getX()-20, getY()-20, angle, true);
	}
	
	public ArrayList<Bullet> getExistingBullets()
	{
		
		return g.getExistingBullets();
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

	public void act(ArrayList<Structure> structures) 
	{
		if(up)
			setyVel(-5);
		if(down)
			setyVel(5);
		if(right)
			setxVel(5);
		if(left)
			setxVel(-5);
		
		double tempX = getX();
		double tempY = getY();
		boolean colDetected = false;
		
 		for(Structure str : structures) 
			if(str.getHitBox().intersects(getRect())) {
				colDetected = true;
				System.out.println("fd");
			}
		
		
 		if(!colDetected) {
			this.setX(tempX + getxVel());
			this.setY(tempY + getyVel());
			this.getRect().move(this.getX(), this.getY());
 		}
 		else {
 			this.setxVel(getxVel()*-1);
 			this.setyVel(getyVel()*-1);
 			this.setX(tempX + getxVel());
 			this.setY(tempY + getyVel());
 			
			this.getRect().move(this.getX(), this.getY());
 		}
		
		setxVel(getxVel() * frict); //slow down
		setyVel(getyVel() * frict);
		
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
