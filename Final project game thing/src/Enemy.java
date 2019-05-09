
public class Enemy 
{
	private double damage; //WHAT DOES DAMAGED EXACTLY STAND FOR?
	private double health;
	private double x,y;
	
	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
		this.health = 100;
		this.damage = 0; 
	}
	
	public Enemy() {
		this.x = 10;
		this.y = 10;
		this.health = 100;
		this.damage = 0; 	
	}

	public double getX()
	{
		return x;
	}
	public double getY()
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
}
