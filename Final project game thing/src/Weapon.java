
public class Weapon 
{
	private int ID; // just to keep track which level the player got this weapon
	private double damage;
	private double attackSpeed; //attack speed is a double that represents the number of attacks the user can perform in a second, not implemented yet
	public Weapon(double damage, double attackSpeed, int ID)
	{
		this.attackSpeed = attackSpeed;
		this.damage = damage;
		this.ID = ID;
	}
	public double getDamage()
	{
		return damage;
	}
	public double getAttackSpeed()
	{
		return attackSpeed;
	}
	public int getID()
	{
		return ID;
	}
}
