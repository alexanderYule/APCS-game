import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * One of the enemies whose behavior has certain common traits from the enemy class , but otherwise has its own characteristic behavior.
 * code TBD
 * @author sumukhipandey
 */
public class MeleeEnemy extends Enemy
{
	private Weapon axe;  //JUST FOR NOW
	private boolean canAttack;
	private double speed;
	
	/**
	 * Creates a default MeleeEnemy object with a Gun
	 */
	public MeleeEnemy() {
		super();
		this.axe = new Weapon(1,100,1);
		super.setVelocity(Math.random()*5, Math.random()*5);
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
		axe = new Weapon(1,400,1);
		super.setVelocity(xVel, yVel);
		this.canAttack = true;
	}
	public MeleeEnemy(double x, double y, double speed)
	{
		super(x, y);
		axe = new Weapon(1,400,1);
		this.speed = speed;
		this.canAttack = true;
	}
	/**
	 *  @param a the new state of whether the enemy can attack or not
	 */
	public void canAttack( boolean a) 
	{
		canAttack = a;
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	public void draw(PApplet drawer, PImage eUp, PImage eDown,PImage eRight,PImage eLeft) {
		if(isAlive())
		{			
			if(getDir() == 1) { //LEFT
				drawer.image(eLeft, (int)getX(), (int)getY());
			}
			else if(getDir() == 2) { //UP
				drawer.image(eUp, (int)getX(), (int)getY());
			}
			else if(getDir() == 3) { //RIGHT
				drawer.image(eRight,  (int)getX(), (int)getY());
			}
			else {  //DOWN
				drawer.image(eDown,  (int)getX(), (int)getY());
			}
			getRect().move(getX(), getY());		
			drawer.rect((float)this.getX() + 20, (float)this.getY()+10, 10f, 50f);
		}
	}
	public void move(Player p, ArrayList<Structure> s)
	{
		
		double vx = p.getX() - this.getX();
		double vy = p.getY() - this.getY();

		double angle = 0;
		
		angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));	
		
		setyVel(Math.cos(Math.toRadians(angle)) * speed);
		setxVel(Math.sin(Math.toRadians(angle)) * speed);
		step();
		
	}
	
}
