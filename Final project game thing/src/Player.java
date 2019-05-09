import java.util.ArrayList;

public class Player 
{
	private double damage;
	private double health;
	private int x,y;
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
		this.frict = 0.8;
	}
	
	public int getX()
	{
		return x;
	}
	public void FireWeapon(int targetX, int targetY)
	{
		double vx = targetX - x;
		double vy = -(targetY - y);
		boolean isGoingUp;
		boolean isGoingRight;
		double angle = 0;
		if(vx >= 0)
			isGoingRight = true; //this chain of if's and eles's is so we can find the whole angle not just from -90 to 90.
		else
			isGoingRight = false;
		if(vy <= 0)
			isGoingUp = true;
		else
			isGoingUp = false;
		angle = Math.abs((Math.atan(vy/vx)*(180/Math.PI)));
		if(!isGoingRight && isGoingUp)
			angle += 90;
		if(!isGoingRight && !isGoingUp)
			angle += 180;
		if(isGoingRight && !isGoingUp)
			angle +=270;
		if(vy == 0 && isGoingRight)
		{
			angle = 0; // the previous code has trouble when either vx or vy is zero so it has to be done "manually"
		}
		if(vy == 0 && !isGoingRight)
		{
			angle = 180;
		}
		if(vx == 0 && isGoingUp)
		{
			angle = 90;
		}
		if(vx == 0 && !isGoingUp)
		{
			angle = 270;
		}
		g.fireBullet(x, y, angle);
	}
	public int getY()
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
		this.x += xVel;
		this.y += yVel;
		
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
