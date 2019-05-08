import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

	private Player p;
	private Map m;
	private PImage playerUp, playerDown, playerRight, playerLeft;
	//private PImage background;
	private PImage Bullet;
	public DrawingSurface() 
	{
		p = new Player(100 , 100);

	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() 
	{
		playerUp = loadImage("Resorces/hero_sprites/up.png");
		playerDown = loadImage("Resorces/hero_sprites/standingDown.png");
		playerRight = loadImage("Resorces/hero_sprites/right.png");
		playerLeft = loadImage("Resorces/hero_sprites/left.png");

	//	background = loadImage("Resorces/TEST.jpg");
		Bullet = loadImage("Resorces/laser bullet thing.png");
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(255);
		for(Bullet b : p.getExistingBullets())
		{
			b.move();
			image(Bullet,b.getX(),b.getY());
		}

		if(p.getDir() == 1) {
			image(playerLeft, p.getX(), p.getY());
		}
		else if(p.getDir() == 2) {
			image(playerUp, p.getX(), p.getY());
		}
		else if(p.getDir() == 3) {
			image(playerRight, p.getX(), p.getY());
		}
		else {
			image(playerDown, p.getX(), p.getY());
		}

	}
	
	
	public void mousePressed() 
	{
		if (mouseButton == LEFT) 
		{
			p.FireWeapon(mouseX, mouseY);
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