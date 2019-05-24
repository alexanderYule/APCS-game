package adsouza.shapes;
import processing.core.PApplet;

/**
 *  This class represents a simple Line. It can draw an
 *  instance of a Line using two points and perform various
 *  calculations that reflect the attributes of Lines
 *  
 *  @author D'Souza
 *  @version 10/10/2018
*/

public class Line extends Shape {
	private double x2, y2;
	private double solX, solY;

	/**
	 *  Creates a custom Line object with two specified coordinates
	 *  @param x1 is the x-coordinate of the first point of the Line
	 *	@param y1 is the y-coordinate of the first point of the Line
	 *  @param x2 is the x-coordinate of the second point of the Line
	 *  @param y2 is the y-coordinate of the second point of the Line
	*/
	public Line(double x1, double y1, double x2, double y2) {
		super(x1,y1);
		setStrokeColor(0, 0, 0);
		setStrokeWeight(1);
		this.x = x1;
		this.y = y1;
		this.x2 = x2;
		this.y2 = y2;    
	}

	/**
	 * Draws a new instance of the Circle using Processing's PApplet
	 * @param drawer PApplet that will be drawn on to
	 * @pre drawer must not be null 
	*/
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.line((float)x, (float)y, (float)x2, (float)y2);
	}
	public double getLength()
	{
		return Math.sqrt(Math.pow(x2-x,2)+Math.pow(y2-y,2));
	}

	/**
	 * Checks whether two Lines intersect each other
	 * @param l2 is the other line that will be checked for intersection
	 * @return returns true if the two lines intersect or false otherwise
	*/
	public boolean intersects(Line l2) {
		
		double numX = ( (((this.x*this.y2)-(this.y*this.x2))*((l2.x-l2.x2)) -  (((this.x - this.x2))*((l2.x*l2.y2) - (l2.y*l2.x2))))); //Numerator of the x coordinate of Solution
		double  denomX = (((this.x-this.x2)*(l2.y-l2.y2)  -  ((this.y-this.y2)*(l2.x-l2.x2))));										//Numerator of the x coordinate of Solution
		
		double numY = ((((this.x*this.y2)-(this.y*this.x2))*(l2.y-l2.y2)) - ((this.y-this.y2)* ((l2.x*l2.y2) - (l2.y*l2.x2))));
				
		solX = numX/denomX;
		solY = numY/denomX;
		
		if((Math.min(this.x, this.x2) <= solX && solX <= Math.max(this.x, this.x2)) && 
			((Math.min(l2.x, l2.x2) <= solX && solX <= Math.max(l2.x, l2.x2)))) {
				if((Math.min(this.y, this.y2) <= solY && solY <= Math.max(this.y, this.y2)) && 
						((Math.min(l2.y, l2.y2) <= solY && solY <= Math.max(l2.y, l2.y2)))) {
						return true;
				}
				else {
					return false;
				}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the second coordinate of the Line to (x2, y2)
	 * @param x2 is the x-coordinate that will be the new second point 
	 * of the Line
	 * @param y2 is the y-coordinate that will be the new second coordinate
	 * of the Line
	 * @post The coordinates of the second point of this Line will be changed
	*/
	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Calculates and checks whether the point (x,y) is inside this
	 * Shape
	 * @param x The x-coordinate of the point to check for inclusion
	 * @param y The y-coordinate of the point to check for inclusion
	 * @return True is the the point (x,y) is inside the Line or False
	 * otherwise
	 */
	public boolean isPointInside(double x, double y) {
		if(x >= Math.min(x, x2) && x <= Math.max(x, x2)) {
			if(y >= Math.min(y, y2) && y <= Math.max(y, y2)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

}
