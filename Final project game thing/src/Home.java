import adsouza.shapes.Rectangle;
import processing.core.PFont;

/**
 * 
 * @author Alex
 *
 * Represents the Home screen object of the game
 */
public class Home {
	private boolean started ;
	private boolean overStart ;
	private boolean overExit ;
	private boolean paused ;
	
	/**
	 * Creates a new Home object 
	 * that has not started and is 
	 * not paused
	 */
	public Home(){
		started = false ;
		paused = false ;
	}
	/**
	 * 
	 * @return whether the game has started
	 */
	public boolean isStarted() {
		return started ;
	}
	
	/**
	 * Sets the started status to the value of
	 * s
	 * @param s the new started value
	 */
	public void start(boolean s) {
		started = s ;
	}

	/**
	 * Reverses the status of
	 * the game
	 */
	public void pause() {
		paused = !paused ;
	}
	/**
	 * 
	 * @return the paused value of this
	 * Home
	 */
	public boolean isPaused() {
		return paused ;
	}
	/**
	 * draws the Home on DrawingSurface
	 * @param drawer the surface upon which this Home
	 * will be drawn on
	 * @pre drawer must not be null
	 */
	public void draw(DrawingSurface drawer) {

		drawer.background(drawer.loadImage("Resorces/room_structures/backGround.png"));

		Rectangle startBtn = new Rectangle(150, 550, 300, 100);
		Rectangle exitBtn = new Rectangle(500, 550, 300, 100);
		
		if(startBtn.isPointInside(drawer.mouseX, drawer.mouseY)) {
			overStart = true;
		} else {
			overStart = false ;
		}

		if(exitBtn.isPointInside(drawer.mouseX, drawer.mouseY)) {
			overExit = true ;
		} else {
			overExit = false ;
		}

		drawer.stroke(0);
		drawer.strokeWeight(1);

		PFont f = drawer.createFont("Century Gothic", 100);
		
		drawer.textFont(f);
		drawer.fill(255,0,10);
		drawer.textSize(100);
		drawer.text("Hell Shadow", 185, 300);
		drawer.fill(0);
		drawer.rect(160, 560, 300, 100);
		drawer.rect(510, 560, 300, 100);
		drawer.fill(25,80,9);
		startBtn.draw(drawer);
		exitBtn.draw(drawer);

		if(overStart || overExit) {
			drawer.noFill();
			drawer.stroke(0,0,200);
			drawer.strokeWeight(5);
		} else {
			drawer.noFill();
			drawer.stroke(0);
			drawer.strokeWeight(5);			
		}

		if(overStart)
			drawer.rect((int)startBtn.getX(),(int)startBtn.getY(),(int)startBtn.getWidth(),(int)startBtn.getHeight());
		
		if(overExit)
			drawer.rect((int)exitBtn.getX(),(int)exitBtn.getY(),(int)exitBtn.getWidth(),(int)exitBtn.getHeight());

		drawer.fill(0);
		drawer.textSize(50);
		drawer.text("Start", 250, 620);		
		drawer.text("Exit", 600, 620);		

	}
	
	/**
	 *	Starts or ends running the game
	 *	based on the current overStart
	 *	value of this home
	 * @param d the DrawingSurface object
	 */
	void mouseClicked(DrawingSurface d) {
		if(overStart)
			started = true ;
		if(overExit)
			d.exit();
	}
}
