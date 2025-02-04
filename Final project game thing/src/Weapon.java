
/**
 * @author aaron d, alex y
 * Represents the parent class of objects 
 * that have attacking characteristics 
 */
public class Weapon 
{
	private int ID; // just to keep track which level the player got this weapon
	private double damage;
	private double attackSpeed; //attack speed is a double that represents the # of seconds between each attack
	
	/**
	 * Creates a custom weapon that deals a certain amount of damage at a
	 * speed. Each weapon has a unique ID
	 * @param damage the damage dealt by this weapon
	 * @param attackSpeed the rate of attack of this weapon
	 * @param ID the ID of this weapon
	 */
	public Weapon(double damage, double attackSpeed, int ID)
	{
		this.attackSpeed = attackSpeed;
		this.damage = damage;
		this.ID = ID;
	}
	
	/**
	 * Returns the damage dealt by this weapon
	 * @return the damage value of this weapon
	 */
	public double getDamage()
	{
		return damage;
	}
	
	/**
	 * Returns the attack speed of this weapon
	 * @return the attack speed value of this weapon
	 */
	public double getAttackSpeed()
	{
		return attackSpeed;
	}
	
	/**
	 * Returns the attack speed of this weapon
	 * @param speed the new speed of this weapon
	 */
	public void setAttackSpeed(double speed)
	{
		this.attackSpeed = speed;
	}
	
	/**
	 * Sets of the damage value of this weapon to a new value
	 * @post dmg the new damage value 
	 */
	public void setDamage(double dmg)
	{
		this.damage = dmg;
	}
	
	
	/**
	 * Returns the ID value  of this weapon
	 * @return the ID  value of this weapon
	 */
	public int getID()
	{
		return ID;
	}
}

