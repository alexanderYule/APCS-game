import java.util.ArrayList;

import adsouza.shapes.Circle;
import adsouza.shapes.Line;
import adsouza.shapes.Rectangle;
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
	private Circle damageArea ;
	
	/**
	 * Creates a default MeleeEnemy object with a Weapon
	 */
	public MeleeEnemy() {
		super();
		this.axe = new Weapon(1,100,1);
		super.setVelocity(Math.random()*5, Math.random()*5);
		setDir(1);
		windUpTime = 0;
		speed = 0;
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
	public void draw(PApplet drawer, PImage eUp, PImage eDown,PImage eRight,PImage eLeft, PImage attack) {
		if(isAlive())
		{			
			if(getDir() == 1) { //LEFT
				drawer.image(eLeft, (int)(getX()), (int)(getY()));
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
			//getRect().draw(drawer);
		}
		if(damageArea != null)
		damageArea.draw(drawer);
		
		if(isAttacking && isAlive()) {
			drawer.image(attack, (float)(getX()-(getRect().getWidth())), (float)(getY()-(getRect().getHeight()/2)));
		}
		
		drawer.noFill();
		drawer.strokeWeight(3f);
		drawer.rect((float)this.getRect().getX(),(float) getRect().getY(), (float)getRect().getWidth(), (float)getRect().getHeight());

		//drawer.((float)getX(), (float)getY(), 4, 4);
	}
	public void move(Player p, ArrayList<Structure> s)
	{
		if(isAlive())
		{
			if(canSeePlayer(p, s)) //FIX TOMORROW!!!!!!!!!!!!!!!!!!!!!!!!!!
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
		}
		
	}
	private void attack(Player p)
	{
		isAttacking = true;
		if(windUpTime >= 20)
		{
			damageArea = new Circle(getX()-(getRect().getWidth()), getY()-(getRect().getHeight()/2), 60, 60);	
			if(damageArea.isPointInside(p.getX(), p.getY()) || p.getRect().isCircleInside(damageArea))
				p.takeDamage(10);
			windUpTime = 0;
			isAttacking = false;
		}
		windUpTime++;
	}
	
}
