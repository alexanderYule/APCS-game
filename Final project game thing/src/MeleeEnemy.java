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
	private int damageaAreaCounter;
	/**
	 * Creates a custom MeleeEnemy object with a weapon, specific velocity
	 * at location x,y.
	 * @param x the x coordinate of this enemy.
	 * @param y the x coordinate of this enemy.
	 * @param speed how fast the enemy can move.
	 */
	public MeleeEnemy(int damage, double x, double y, double speed, int maxHealth)
	{
		super(x, y,maxHealth);
		axe = new Weapon(damage,1,1);
		this.speed = speed;
		isAttacking = false;
		setDir(1);
		damageArea = new Circle(0,0,0,0,240,255,255);
		damageArea.setFill();
		damageArea.setOpacity(200);
		damageaAreaCounter = 0;
	}
	/**
	 * 
	 * @return how fast the enemy moves.
	 */
	public double getSpeed()
	{
		return speed;
	}
	/**
	 * 
	 * @param speed the new value for how fast the enemy should move.
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	/**
	 * 
	 * @param drawer the object used to draw the melee enemy on the screen
	 * @param eUp
	 * @param eDown
	 * @param eRight
	 * @param eLeft
	 */
	public void draw(PApplet drawer, PImage eUp, PImage eDown,PImage eRight,PImage eLeft) {
		
		if(isAlive())
		{			
			Rectangle r = getRect() ;
			int xoffset = (int) (r.getWidth()) ;
			int yoffset = (int) (r.getHeight()/2) ;
			if(getDir() == 1) { //LEFT
				drawer.image(eLeft, (int)(getX()-xoffset), (int)(getY()-yoffset));
			}
			else if(getDir() == 2) { //UP
				drawer.image(eUp, (int)getX()-xoffset, (int)getY()-yoffset);
			}
			else if(getDir() == 3) { //RIGHT
				drawer.image(eRight,  (int)getX()-xoffset, (int)getY()-yoffset);
			}
			else {  //DOWN
				drawer.image(eDown,  (int)getX()-xoffset, (int)getY()-yoffset);
			}
			getRect().move(getX(), getY());		
			if(damageArea != null && damageaAreaCounter <= 30)
			{			
				damageArea.draw(drawer);
				damageaAreaCounter++;
			}
			super.draw(drawer);
		}
	}
	public void move(Player p, ArrayList<Structure> s)
	{
		if(isAlive())
		{
			if(canSeePlayer(p, s))
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
		if(windUpTime >= 45)
		{
			damageArea = new Circle(getX() + getRect().getWidth()/2, getY() + getRect().getHeight()/2, 110, 110);	
			if(damageArea.isPointInside(p.getX(), p.getY()) || damageArea.isPointInside(p.getX() + p.getRect().getWidth(), p.getY()) || damageArea.isPointInside(p.getX() + p.getRect().getWidth(), p.getY()+ p.getRect().getHeight()) || damageArea.isPointInside(p.getX(), p.getY()+ p.getRect().getHeight()))
				p.takeDamage(axe.getDamage());
			windUpTime = 0;
			isAttacking = false;
			damageaAreaCounter = 0;
		}
		windUpTime++;
	}
	
	/**
	 * @return the axe weapon of this enemy
	 */
	public Weapon getAxe() {
		return axe;
	}
	
}
