import java.util.ArrayList;

public class Gun extends Weapon
{
	private int range; // not implemented
	private int bulletCount;//number of bullets per shot
	private ArrayList<Bullet> bullets;
	private double bulletSpeed;
	
	/**
	 * Creates a custom Gun object that deals certain damage, can fire
	 * upto a particular range, shoots bullets at a rate, with a unique ID
	 * @param damage the damage the weapon can deal
	 * @param range the range of the weapon
	 * @param bulletCount the amount of bullets held by the gun
	 * @param fireRate a double representing the number of fires the gun can perform per second.
	 * @param speed the speed the bullet travels
	 * @param ID the ID of the weapon
	 */
	public Gun(double damage, int range, int bulletCount, double fireRate, double speed, int ID)
	{		
		super(damage, fireRate, ID);
		bullets = new ArrayList<Bullet>();
		this.range = range;
		this.bulletCount = bulletCount;
		this.bulletSpeed = speed;
	}
	
	
	/**
	 * Fires a bullet from this weapon that travels across the screen
	 * @param x the x coordinate of this weapon
	 * @param y the y coordinate of this weapon
	 * @param direction the direction of this weapon
	 * @post adds a bullet into this weapon
	 */
	public void fireBullet(double x, double y, double direction, boolean isGood)
	{
		bullets.add(new Bullet(x,y,direction,bulletSpeed,getDamage(),isGood));
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
