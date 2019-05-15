import adsouza.shapes.Rectangle;

public abstract class GameEntity {

	private double x,y;
	private double xVel, yVel;
	private Rectangle rect;
	
	public GameEntity() {
		this.x = 50;
		this.y = 50;
		rect = new Rectangle();
		xVel = 0;
		yVel = 0;
	}
	
	public  GameEntity(double x, double y, double xVel, double yVel, double rWidth, double rHeight) {
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, rWidth, rHeight);
		this.xVel = xVel;
		this.yVel = yVel;
	}

	public GameEntity(double x, double y) {
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, 10, 10);
		this.xVel = 0;
		this.yVel = 0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxVel() {
		return xVel;
	}
	
	public double getyVel() {
		return yVel;
	}
	
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	
	public abstract void setVelocity(double xVel, double yVel);
	
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	
	}
