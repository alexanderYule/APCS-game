import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

	private Player p;
	private Map m;
	private PImage Player;
	public DrawingSurface() 
	{
		p = new Player(100 , 100);
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() 
	{
		Player = loadImage("Resorces/Hero dude.png");
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(255);
		image(Player, p.getX(), p.getY());

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
		if(keyCode == KeyEvent.VK_W)
		{
			p.moveUp();
		}
		if(keyCode == KeyEvent.VK_A)
		{
			p.moveLeft();
		}
		if(keyCode == KeyEvent.VK_D)
		{
			p.moveRight();
		}
		if(keyCode == KeyEvent.VK_S)
		{
			p.moveDown();
		}
	}
	
}