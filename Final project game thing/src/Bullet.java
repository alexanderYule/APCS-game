
public class Bullet 
{
	private int x,y;
	private double xVelocity;
	private double yVelocity;
	private double damage;
	private boolean isGood; //if both ranged enemies and the player intend on using this class then it makes sense 
	public Bullet(int x, int y, double direction, double speed, double damage, boolean isGood)
	{
		this.x = x;
		this.y = y;
		yVelocity = speed*Math.sin(Math.toRadians(direction));
		xVelocity = speed*Math.cos(Math.toRadians(direction));
		this.damage = damage;
		this.isGood = isGood;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void move()
	{
		y += yVelocity/60;
		x += xVelocity/60;
	}
}
