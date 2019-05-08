
public class Player 
{
	private double damage;
	private double health;
	private int x,y;
	private Weapon weapon;
	private int dir;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.dir = 0;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
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
