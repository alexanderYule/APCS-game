import processing.core.PApplet;

/**
 * One of the enemies whose behavior has certain common traits from the enemy class , but otherwise has its own characteristic behavior.
 * code TBD
 * @author sumukhipandey
 */
public class MeleeEnemy extends Enemy
{
	private Weapon axe;  //JUST FOR NOW
	private boolean canAttack;
	
	/**
	 * Creates a default MeleeEnemy object with a Gun
	 */
	public MeleeEnemy() {
		super();
		this.axe = new Weapon(1,100,1);
		super.setVel(Math.random()*5, Math.random()*5);
		this.canAttack = false;
	}
	
	/**
	 * Creates a custom MeleeEnemy object with a weapon, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param xVel the x velocity component of this enemy
	 * @param yVel the y velocity component of this enemy
	 */
	public MeleeEnemy(double x, double y, double xVel, double yVel) {
		super(x, y);
		this.axe = new Weapon(1,400,1);
		super.setVel(xVel, yVel);
		this.canAttack = true;
	}
	
	public void attack() {
		this.canAttack = true;
	}
	
	public void draw(PApplet drawer) {
		if(canAttack) {
			
		}
	}
	
}
