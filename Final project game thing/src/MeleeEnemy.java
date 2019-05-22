import java.util.ArrayList;

import adsouza.shapes.Circle;
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
	private double speed;
	private int windUpTime;
	private boolean isAttacking;
	private Circle damageArea;
	/**
	 * Creates a default MeleeEnemy object with a Gun
	 */
	public MeleeEnemy() {
		super();
		this.axe = new Weapon(1,100,1);
		super.setVelocity(Math.random()*5, Math.random()*5);
		setDir(1);
	}
	
	/**
	 * Creates a custom MeleeEnemy object with a weapon, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param speed how fast the enemy can move
	 */
	public MeleeEnemy(double x, double y, double speed)
	{
		super(x, y);
		axe = new Weapon(1,1,1);
		this.speed = speed;
		isAttacking = false;
		setDir(1);
	}
	public double getSpeed()
	{
		return speed;
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
			getRect().draw(drawer);
		}
//		if(damageArea != null)
//			damageArea.draw(drawer);
		drawer.ellipse((float)getX(), (float)getY(), 4, 4);
	}
	public void move(Player p, ArrayList<Structure> s)
	{
		
		double vx = p.getX() - this.getX();
		double vy = p.getY() - this.getY();
		if(Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2)) >= 30 && !isAttacking)
		{
			double angle = 0;
			
			angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));	
			
			setyVel(Math.cos(Math.toRadians(angle)) * speed);
			setxVel(Math.sin(Math.toRadians(angle)) * speed);
			step();
		}
		else
			attack(p);
		
	}
	private void attack(Player p)
	{
		isAttacking = true;
		if(windUpTime >= 30)
		{
			damageArea = new Circle(getX(), getY(), 60, 60);	
			if(damageArea.isPointInside(p.getX(), p.getY()))
				p.takeDamage(30);
			windUpTime = 0;
			isAttacking = false;
		}
		windUpTime++;
	}
	
}
