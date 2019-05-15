import java.util.ArrayList;

import adsouza.shapes.Rectangle;
import processing.core.PApplet;

public class Player extends GameEntity
{
	private double damage;
	private double health;
	private double x,y;
	private double xVel,yVel;
	private double frict;
	private Gun g;
	private int dir; //1=left, 2=left & up, 3=up, 4= right & up 5 = right 6 = right & down 7 = down 8 = left & down
	private boolean up,down,left,right;
	private Rectangle rect;
	
	public Player(double x, double y)
	{
		this.x = x;
		this.y = y;
		g = new Gun(1,400,1,1,200,150);
		dir = 0;
		this.xVel = 0;
		this.yVel = 0;
		this.frict = 0.95;
		up = false;
		down = false;
		left = false;
		right = false;
		this.rect = new Rectangle(x,y, 50, 60); //LAST TWO PARAMETERS NEED TO BE CHANGED BASED ON .IMG DIMENSIONS.
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
			setVelocity(-super.getxVel(), this.getyVel());
		}
		
		if(this.getY() <= 0 ||this.getY() >= 900) {
			setVelocity(this.getxVel(), -this.getyVel());
		}
		
		drawer.noFill();
		drawer.strokeWeight(5);

		drawer.rect((int)x,(int) y, (int)40, (int)40);
	}

	public void fireWeapon(int targetX, int targetY)
	{
		double vx = targetX - x;
		double vy = targetY - y;

		double angle = 0;

		angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
	
		g.fireBullet(x-25, y-25, angle, true);
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
		
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void setXVel(double x)
	{
		xVel = x;
	}
	public void setYVel(double y)
	{
		yVel = y;
	}
	public void act(ArrayList<Structure> structures) 
	{
		if(up)
			setYVel(-5);
		if(down)
			setYVel(5);
		if(right)
			setXVel(5);
		if(left)
			setXVel(-5);
		
		for(Structure s : structures)
		{
			if(s.getHitBox().intersects(rect))
			{
				xVel = 0;
				yVel = 0;
			}
		}
		if(x >= 870 || x <= 0)
			xVel = 0;
		if(y >= 900 || y <= 0)
			yVel = 0;
		this.x = this.x + xVel;
		this.y = this.y + yVel;
		
		xVel*=frict;
		yVel*=frict;
		
		if(xVel > 0) 
		{
			if(Math.abs(yVel) < 0.05)
				dir = 5; // only right
			else if(yVel < 0)
				dir = 4; // right and up
			else
				dir = 6; // right and down
		}
		else if (xVel < 0)
		{
			if(Math.abs(yVel) < 0.05)
				dir = 1; // only left
			else if(yVel < 0) 
				dir = 4; //left and up
			else
				dir = 6; //left and down
		}
		else if(yVel < 0) {
			dir = 3;
		}
		else {
			dir = 7;
		}
		
	}
	

}
