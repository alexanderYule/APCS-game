import java.awt.event.KeyEvent;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private Player p;
	private Map m;
	public DrawingSurface() 
	{
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(255);

	}
	
	
	public void mousePressed() 
	{
		if (mouseButton == LEFT) 
		{
			
		} 
		
	}
	
	
	
	public void keyPressed()
	{
		if (keyCode == KeyEvent.VK_SPACE)
		{
			
		} 
	}
	
}