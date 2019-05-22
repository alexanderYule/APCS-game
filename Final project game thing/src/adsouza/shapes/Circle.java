package adsouza.shapes;
import processing.core.PApplet;

/**
 *  This class represents a simple Ellipse. It can perform common calculations
 *  relevant to a Circle's attributes and can draw a delineation of an Ellipse
 *  to Processing PApplet
 *  
 *  @author D'Souza
 *  @version 10/26/2018
*/

public class Circle extends Shape2D{
	private double width;
	private double height;
	private double radius;
	private double centerX;
	private double centerY;
	
	/**
	 *  Creates a default Circle object with all dimensions and attributes of  
	 *  this circle set to 0.
	 *
	*/
	public Circle() {
		super(0,0,0,0,0);
		setStrokeWeight(1);
		setStrokeColor(0,0,0);
		this.width = 0;
		this.height = 0;
		this.radius = 0;
		this.centerX = 0;
		this.centerY = 0;
	}

	/**
	 *  Creates a custom Circle object at the location (x,y) with the width
	 *  and height inscribed in a rectangle. Radius and center points are
	 *  calculated with respect to this Circle's height and width
	 *  @param x x-coordinate of the center of the Circle
	 * 	@param y y-coordinate of the center of the Circle
	 * 	@param width width of the Circle
	 * 	@param height height of the Circle
	 * 	@param r is the amount of Red color the Circle will be filled with
	 * 	@param g is the amount of Green color the Circle will be filled with
	 * 	@param b is the amount of Blue color the Circle will be filled with
	*/
	public Circle(double x, double y, double width, double height, int r, int g, int b){
		super(Math.abs(x), Math.abs(y), r, g, b);
		setStrokeWeight(1);
		setStrokeColor(r , g, b);
		this.width = Math.abs(width);
		this.height = Math.abs(height);
		this.radius = width/2;
		this.centerX = (height-radius);
		this.centerY = (width-radius);
	}    
	public Circle(double x, double y, double width, double height){
		super(Math.abs(x), Math.abs(y), 0 , 0, 0);
		setStrokeWeight(1);
		setStrokeColor(0 , 0, 0);
		this.width = Math.abs(width);
		this.height = Math.abs(height);
		this.radius = width/2;
		this.centerX = (x-radius);
		this.centerY = (y-radius);
	}       

	/**
	 *  Calculates and returns the area of this circle
	 * 	@return area of this circle
	*/
	public double calcArea(){
		return (Math.PI)*(Math.pow(radius,2));
	}
	
	/**
	 *  Calculates and returns the perimeter(circumference) of this 
	 *  circle
	 * 	@return perimeter of this circle
	*/
	public double calcPerimeter() {
		return (2*Math.PI)*(Math.pow(radius,2));
		
	}

	// Determines whether the point x,y is contained inside this Circle
	/**
	 *  Checks whether the point (x,y) is inside this circle 
	 * 	@param x is the x-coordinate of the point to check for inclusion
	 * 	@param y is the y-coordinate of the point to check for inclusion
	 * 	@return true is the point is inside this circle or false otherwise
	*/
	public boolean isPointInside(double pointX, double pointY)
	{
		double distance = Math.sqrt((pointX - x) + (pointY - y));
		if(distance > radius)
		{
			return true;
		}
		if(distance <= radius)
		{
			return false;
		}
		return false;
	}	
	/**
	 *  Sets the width and height of the circle to width and height
	 *  @param width is the value that will be copied into the Circle object's
	 *  width instance
  	 *  @param height is the value that will be copied into the Circle object's
	 *  height instance
	 *  @post the width and height values will be modified into width and height 
	 *  respectively
	*/
	public void setDimen(double width, double height ) {
		this.width = width;
		this.height = height;
	}

	/**
	 *  Draws a new instance of the Circle object with the corners inscribed
	 *  at the top left edge of a rectangle, assuming the circle is inscribed in
	 *  a Rectangle.
	 *  @param drawer PApplet used to create a representation of the Circle object
	 *  @pre drawer must not be be null
	 *  @post the drawer will alter the Circle object's ellipse mode to drawer.CORNER
	*/
	public void draw(PApplet drawer){
		drawer.ellipseMode(drawer.CORNER);
		super.draw(drawer);
		if(getFillState()) {
			drawer.noFill();
			drawer.ellipse((float)x, (float)y, (float)width, (float)height);
		}
		else {
			drawer.fill(getFillR(), getFillG(), getFillB());
			drawer.ellipse((float)x, (float)y, (float)width, (float)height);
		}
	}
	
	/**
	 * @return The radius of this Circle
	 */
	public double getRadius() {
		return this.radius;
	}
	public double getCenterX()
	{
		return centerX;
	}
	public double getCenterY()
	{
		return centerY;
	}
	/**
	 * @return The x-coordinate of this Circle
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return The y-coordinate of this Circle
	 */
	public double getY() {
		return this.y;
	}

}
