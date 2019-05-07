
public class Player 
{
	private double damage;
	private double health;
	private int x,y;
	private Weapon weapon;
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
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
		x -= 5;
	}
	public void moveRight()
	{
		x += 5;
	}
	public void moveUp()
	{
		y -= 5;
	}
	public void moveDown()
	{
		y += 5;
	}
}
