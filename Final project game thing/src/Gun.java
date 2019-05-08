import java.util.ArrayList;

public class Gun extends Weapon
{
	private int range; // not implemented
	private int bulletCount;//number of bullets per shot
	private ArrayList<Bullet> bullets;
	private double bulletSpeed;
	public Gun(double damage, int range, int bulletCount, double fireRate, double speed, int ID)
	{		
		super(damage, fireRate, ID);
		bullets = new ArrayList<Bullet>();
		this.range = range;
		this.bulletCount = bulletCount;
		this.bulletSpeed = speed;
	}
	public void fireBullet(int x, int y, double direction)
	{
		bullets.add(new Bullet(x,y,direction,bulletSpeed,getDamage(),true));
	}
	public ArrayList<Bullet> getExistingBullets()
	{
		return bullets;
	}
}
