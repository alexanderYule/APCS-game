
import processing.core.PApplet;
import processing.core.PImage;

public class RangedEnemy extends Enemy
{
	private Gun eGun;
	
	/**
	 * Creates a default RangedEnemy object with a Gun
	 */
	public RangedEnemy() {
		super();
		this.eGun = new Gun(1,100,1,1,200,150);
		super.setVel(Math.random()*5, Math.random()*5);
	}
	
	/**
	 * Creates a custom RangedEnemy object with a Gun, specific velocity
	 * at location x,y
	 * @param x the x coordinate of this enemy
	 * @param y the x coordinate of this enemy
	 * @param xVel the x velocity component of this enemy
	 * @param yVel the y velocity component of this enemy
	 */
	public RangedEnemy(double x, double y, double xVel, double yVel) {
		super(x, y);
		this.eGun = new Gun(1,400,1,1,200,150);
		super.setVel(xVel, yVel);
	}
	
	/**
	 * Fires a bullet to the location of player
	 * @param player the player object this RangedEnemy has to shoot/target
	 */
	public void fireToPlayer(Player player) {
		double vx = player.getX() - this.getX();
		double vy = player.getY() - this.getY();

		double angle = 0;
		
		angle =  90-(Math.atan2(vy,vx)*(180/Math.PI));		
	
		this.getGun().fireBullet(this.getX(), this.getY(), angle, false);
	}
	
	/**
	 * Returns the Gun this enemy is holding
	 * @return the Gun object that belongs to this enemy
	 */
	public Gun getGun() {
		return eGun;
	}

	/**
	 *  Draws a graphical representation of this RangedEnemy at its respective location 
	 *  by checking the RangedEnemy's current direction
	 *  @post the x and y coordinate values of this RangedEnemy will be changed
	*/
	public void draw(PApplet drawer) {
	
		PImage eUp = drawer.loadImage("Resorces/enemy_sprites/upGoblin.png");
		PImage eDown = drawer.loadImage("Resorces/enemy_sprites/frontGoblin.png");
		PImage eRight = drawer.loadImage("Resorces/enemy_sprites/rightGoblin.png");
		PImage eLeft = drawer.loadImage("Resorces/enemy_sprites/leftGoblin.png");
			
		
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
	
		
		super.setX((getX() + getxVel()));
		super.setY((getY() + getyVel()));	
		
		
		if((this.getX() >= 900 || this.getX() <= 0)) {
			super.setVel(-this.getxVel(), this.getyVel());
		}
		
		if(this.getY() <= 0 ||this.getY() >= 900) {
			super.setVel(this.getxVel(), -this.getyVel());
		}
		
		drawer.rect((float)this.getX(), (float)this.getY(), 10f, 50f);
	}
}
