package adsouza.shapes;
import processing.core.PApplet;

/**
 *  This class represents a simple Rectangle. It can perform common calculations
 *  regarding the rectangles attributes and can draw a delineation of a Rectangle
 *  to Processing PApplet
 *  
 *  @author D'Souza
 *  @version 10/26/2018
*/

public class Rectangle extends Shape2D{
	
	private double width;
	private double height;

	/**
	 *  Creates a default Rectangle
	 *  object with all dimensions and 
	 *  properties set to zero.
	*/
	public Rectangle() {
		super(0, 0, 0, 0, 0);
		setStrokeWeight(1);
		setStrokeColor(0,0,0);
		this.width = 0;
		this.height = 0;
	}

	/**
	 *  Creates a custom, Rectangle object with the specified dimensions with 
	 *  top and bottom edges y and y+height. The left and right edges are at 
	 *  x and x+width respectively. 
	 *  @param x is the x-coordinate of the upper left corner of the rectangle
	 * 	@param y is the y-coordinate of the upper left corner of the rectangle
	 * 	@param width is the width of the rectangle
	 * 	@param height is the height of the rectangle
	 * 	@param r is the amount of Red color the rectangle will be filled with
	 * 	@param g is the amount of Green color the rectangle will be filled with
	 * 	@param b is the amount of Blue color the rectangle will be filled with
	*/
	public Rectangle(double x, double y, double width, double height, int r, int g, int b)   {                                       
		super(Math.abs(x), Math.abs(y), r, g, b);
		setStrokeWeight(1);
		setStrokeColor(r, g, b);
		this.width = Math.abs(width);
		this.height = Math.abs(height);
	}
	
	/**
	 *  Creates a custom, Rectangle object with the specified dimensions with 
	 *  top and bottom edges y and y+height. The left and right edges are at 
	 *  x and x+width respectively. 
	 *  @param x is the x-coordinate of the upper left corner of the rectangle
	 * 	@param y is the y-coordinate of the upper left corner of the rectangle
	 * 	@param width is the width of the rectangle
	 * 	@param height is the height of the rectangle
	*/
	public Rectangle(double x, double y, double width, double height) {
		super(x ,y);
		this.width = width;
		this.height = height;
	}

	/**
	 * Calculates and returns the perimeter of this Rectangle
	 * @post The value of perimeter of this Rectangle is changed
	 * @return perimeter of this rectangle
	*/
	public double calcPerimeter() {
		return (2*height)+ (2*width);
	}

	/**
	 * Calculates and returns the area of this rectangle
	 * @post The value of area of this rectangle is changed
	 * @return area of this rectangle.
	*/
	public double calcArea(){
		return this.width*this.height;
	}

	/**
	 * Checks whether x and y are located inside this rectangle
	 * @param x is the x-coordinate of the point to check
	 * @param y is the y-coordinate of the point to check
	 * @return true if the point (x, y) is inside this rectangle,
	 * false otherwise
	*/
	public boolean isPointInside(double x, double y){
		if((x <= (this.x+width)  && x >= this.x) && 
			(y <= (this.y+height)  && y >= this.y)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether the Circle intersects with this rectangle
	 * @param cir the Circle object to test for intersection
	 * @return true if the Circle is inside this rectangle,
	 * false otherwise
	*/
	public boolean isCircleInside(Circle cir){
		double  closeX = 0;
		double  closeY = 0;

		if(cir.getX() < this.x) {
			closeX = this.x;
		}
		else if(cir.getX() > this.x+this.width) {
			closeX = this.x+this.width;
		}
		
		if(cir.getY() < this.y-this.height) {
			closeY = this.y-this.height;
		}
		else if(cir.getY() > this.y) {
			closeY = this.y;
		}
		
		double distX = cir.getX() - closeX;
		double distY = cir.getY() - closeY;
		
		return Math.pow(distX, 2) + Math.pow(distY, 2) < Math.pow(cir.getRadius(), 2);
	}

	/**
	 * Draws a new instance of the Rectangle using Processing's PApplet
	 * @param drawer PApplet used to draw the rectangle on
	 * @pre drawer must not be null 
	 * @post the method will change the mode of the Rectangle to PApplet.CORNER 
	 * used to designate the reference from which the Rectangle will be drawn
	 * @post the method will change the fill property of the Rectangle to the
	 * specified r, g, b value
	*/
	public void draw(PApplet drawer)
	{
		drawer.pushStyle();
		drawer.rectMode(PApplet.CORNER);
		super.draw(drawer);
		if(getFillState()) {
			drawer.noFill();
			drawer.rect((float)x, (float)y, (float)width, (float)height);
		}
		else {
			drawer.fill(getFillR(), getFillG(), getFillB());
			drawer.rect((float)x, (float)y, (float)width, (float)height);
		}
		//drawer.ellipse((float)x,(float) y, 5f, 5f);
		drawer.popStyle();
	}

	
	public boolean intersects(Line other) {
		Line l = new Line(x,y,x+width,y);
		if(l.intersects(other)){
			return true ;
		}
		
		l = new Line(x,y,x,y+height);
		if(l.intersects(other)){
			return true ;
		}

		l = new Line(x+width,y,x+width,y+height);
		if(l.intersects(other)){
			return true ;
		}

		l = new Line(x,y+height,x+width,y+height);
		if(l.intersects(other)){
			return true ;
		}

		return false ;
	}
	
	/**
	 * Checks and returns whether Rectangle other intersects this rectangle
	 * @param other Rectangle object that is used to check for intersection with
	 * this Rectangle object
	 * @pre other Rectangle and this Rectangle must not be null
	 * @return the method will return true is this Rectangle object overlaps on the 
	 * other Rectangle object or false otherwise
	*/
	public boolean intersects(Rectangle other) {

		if(this.x <= other.x+other.width && this.x+this.width >= other.x 
		 && this.y <= other.y+other.height && this.y + this.height >= other.y) { //Checks for y intersection...
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * Calculate and checks for equality between the diagonals of two Rectangle objects
	 * @param other Rectangle object that is used to check for intersection with
	 * this Rectangle object
	 * @pre other Rectangle and this Rectangle must not be null
	 * @return the method will return true if this Rectangle object's diagonal is equal to 
	 * the diagonal of the otherRectangle object or false otherwise
	*/
	public boolean checkDiagonal(Rectangle other) { //Checks is this rectangles has the same diagonal length as other
		double thisDiagonal = Math.sqrt((Math.pow(this.width, 2) + Math.pow(this.height, 2)));
		double otherDiagonal = Math.sqrt((Math.pow(other.width, 2) + Math.pow(other.height, 2)));

		if(thisDiagonal == otherDiagonal) {
			return true;
		}
		else {
			return false;
		}
	}

	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}

	public void setWidth(double w) {
		this.width = w ;
	}
	
	public void setHeight(double h) {
		this.height = h ;
	}
}
