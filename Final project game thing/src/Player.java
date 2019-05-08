import java.util.ArrayList;

public class Player 
{
	private double damage;
	private double health;
	private int x,y;
	private Gun g;
	private int dir;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		g = new Gun(1,200,1,1,1,150);
		this.dir = 0;
	}
	public int getX()
	{
		return x;
	}
	public void FireWeapon(int targetX, int targetY)
	{
		double vx = targetX - x;
		double vy = targetY - y;
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
	public void moveLeft()
	{
		x -= 10;
		dir = 1;
	}
	public void moveRight()
	{
		x += 10;
		dir = 3;
	}
	public void moveUp()
	{
		y -= 10;
		dir = 2;
	}
	public void moveDown()
	{
		y += 10;
		dir = 4;
	}
	
	public int getDir() {
		return dir;
	}
}
