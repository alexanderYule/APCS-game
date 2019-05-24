import adsouza.shapes.Rectangle;


public class Home {
	private boolean started ;
	private boolean overStart ;
	private boolean overExit ;
	private boolean paused ;
	
	public Home(){
		started = false ;
		paused = false ;
	}
	
	public boolean isStarted() {
		return started ;
	}
	
	public void start(boolean s) {
		started = s ;
	}
	
	public boolean isPaused() {
		return paused ;
	}
	
	public void pause() {
		paused = !paused ;
	}
	
	public void draw(DrawingSurface drawer) {
		Rectangle startBtn = new Rectangle(150, 550, 300, 100);
		Rectangle exitBtn = new Rectangle(500, 550, 300, 100);
		
		if(startBtn.isPointInside(drawer.mouseX, drawer.mouseY)) {
			overStart = true ;
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

		drawer.fill(255);
		drawer.rect(60, 60, 825, 450);
		drawer.fill(0);
		drawer.rect(50, 50, 825, 450);

		drawer.fill(255,0,0);
		drawer.textSize(100);
		drawer.text("Hell Shadow", 190, 300);
		drawer.fill(0);
		drawer.rect(160, 560, 300, 100);
		drawer.rect(510, 560, 300, 100);
		drawer.fill(255);

		startBtn.draw(drawer);
		exitBtn.draw(drawer);

		if(overStart || overExit) {
			drawer.noFill();
			drawer.stroke(0,0,255);
			drawer.strokeWeight(5);
		} else {
			drawer.noFill();
			drawer.stroke(0);
			drawer.strokeWeight(5);			
		}
		drawer.rect((int)startBtn.getX(),(int)startBtn.getY(),(int)startBtn.getWidth(),(int)startBtn.getHeight());
		drawer.rect((int)exitBtn.getX(),(int)exitBtn.getY(),(int)exitBtn.getWidth(),(int)exitBtn.getHeight());

		drawer.fill(255,0,0);
		drawer.textSize(50);
		drawer.text("Start", 250, 620);		
		drawer.text("Exit", 600, 620);		

	}
	
	void mouseClicked(DrawingSurface d) {
		if(overStart)
			started = true ;
		if(overExit) 
			d.exit();
	}
}
