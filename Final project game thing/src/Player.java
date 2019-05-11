import java.util.ArrayList;

import processing.core.PApplet;

public class Player 
{
	private double damage;
	private double health;
	private double x,y;
	private double xVel,yVel;
	private double frict;
	private Gun g;
	private int dir; //1=left, 2=up, 3=right, 4=down
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		g = new Gun(1,400,1,1,200,150);
		this.dir = 0;
		this.xVel = 0;
		this.yVel = 0;
		this.frict = 0.95;
	}
	
	public void draw(PApplet drawer) {
	
		this.act();
//		
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
		drawer.noFill();
		drawer.strokeWeight(5);
		drawer.rect((int)x,(int) y, (int)100, (int)100);
	}

	
	public double getX()
	{
		return x;
	}
	
	public void fireWeapon(int targetX, int targetY)
	{
		double vx = targetX - x;
		double vy = targetY - y;

		double angle = 0;

		angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
	
		g.fireBullet(x-25, y-25, angle, true);
	}
	
	public double getY()
	{
		return y;
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
		
		this.xVel = xVel;
		this.yVel = yVel;
	}

	public void act() {
		this.x = this.x + xVel;
		this.y = this.y + yVel;
		
		xVel*=frict;
		yVel*=frict;
	}
	
	public double getXVel() {
		return this.xVel;
	}

	public double getYVel() {
		return this.yVel;
	}
}
