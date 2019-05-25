import java.util.ArrayList;

public class Gun extends Weapon
{
	private int range; // not implemented
	private int bulletCount;//number of bullets per shot
	private ArrayList<Bullet> bullets;
	private double bulletSpeed;
	private double sprayRange;
	private boolean canHold;
	
	/**
	 * Creates a custom Gun object that deals certain damage, can fire
	 * upto a particular range, shoots bullets at a rate, with a unique ID
	 * @param damage the damage the weapon can deal
	 * @param range the range of the weapon
	 * @param bulletCount the amount of bullets held by the gun
	 * @param fireRate a double representing the number of fires the gun can perform per second.
	 * @param speed the speed the bullet travels
	 * @param ID the ID of the weapon
	 * @param canHold whether the Gun holder can hold this Gun
	 * @param sprayRange the distance the Gun can fire
	 */
	public Gun(double damage, int range, double fireRate, double speed, int ID,boolean canHold, int sprayRange, int bulletCount)
	{		
		super(damage, fireRate, ID);
		bullets = new ArrayList<Bullet>();
		this.range = range;
		this.bulletCount = bulletCount;
		this.bulletSpeed = speed;
		this.sprayRange = sprayRange;
		this.canHold = canHold;
	}
	/**
	 *  Creates a custom Gun object that deals certain damage, can fire
	 * upto a particular range, shoots bullets at a rate, with a unique ID
	 * @param damage the damage dealt by this gun
	 * @param range the range of the gun
	 * @param fireRate the rate at which this gun fires
	 * @param speed the bullet speed of this gun
	 * @param ID the ID of this gun
	 */
	public Gun(double damage, int range, double fireRate, double speed, int ID,boolean canHold)
	{
		super(damage, fireRate, ID);
		bullets = new ArrayList<Bullet>();
		this.range = range;
		this.bulletSpeed = speed;
		bulletCount = 1;
		sprayRange = 0;
		this.canHold = canHold;
	}
	
	
	/**
	 * Fires a bullet from this weapon that travels across the screens.
	 * @param x the x coordinate of this weapon
	 * @param y the y coordinate of this weapon
	 * @param direction the direction of this weapon
	 * @param isGood whether the bullet hits the opposition entity
	 * @post adds a bullet into this weapon
	 */
	public void fireBullet(double x, double y, double direction, boolean isGood)
	{
		if(bulletCount == 1)
			bullets.add(new Bullet(x,y,direction,bulletSpeed,getDamage(),isGood));
		else
		{
			for(int i = 0; i < bulletCount; i++)
			{
				bullets.add(new Bullet(x,y,(direction - sprayRange/2) + (sprayRange * i/(bulletCount-1)),bulletSpeed,getDamage(),isGood));
			}
		}
	}
	
	/**
	 * Returns the Bullet objects in this Gun
	 * @return the list of all Bullet s in this Gun
	 */
	public ArrayList<Bullet> getExistingBullets()
	{
		return bullets;
	}
	
	/**
	 * Changes the bullet speed of this gun
	 * to speed
	 * @param speed The need speed of this gun's bullets
	 */
	public void setBulletSpeed(double speed) {
		this.bulletSpeed = speed;
	}
}
