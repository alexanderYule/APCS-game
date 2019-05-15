package adsouza.shapes;

/**
 *  This class represents a simple Two-Dimensional Shape. It can 
 *  create Shape instances with attributes specific to a Two-
 *  Dimensional shape using Shapes
 *  
 *  @author D'Souza
 *  @version 10/26/2018
*/

public abstract class Shape2D extends Shape {
		
	private int fillR, fillG, fillB;
	private boolean noFill;
		
	/**
	 * Creates a new 2DShape instance with coordinates
	 * based on its type
	 * @param x The x-coordinate of this 2DShape 
	 * @param y The y-Coordinate of this 2DShape
	 * @param r The amount of Red this shape will be 
	 * filled with 
	 * @param g The amount of Green this shape will be 
	 * filled with 
	 * @param b The amount of Blue this shape will be
	 * filled with 
	*/
	public Shape2D(double x, double y, int r, int g, int b) {
		super(x, y);
		this.fillR = r;
		this.fillG = g;
		this.fillB = b;
		this.noFill = false;
	}
	
	/**
	 * Creates a 2DShape instance with coordinates
	 * with a default fill color of black with 
	 * noFill 
	 * @param x The x-coordinate of this 2DShape 
	 * @param y The y-Coordinate of this 2DShape
	*/
	public Shape2D(double x, double y) {
		super(x, y);
		this.fillR = 255;
		this.fillG = 255;
		this.fillB = 255;
		this.noFill = false;
	}
	
	
	
	/**
	 * The method returns the Red value to fill in the 
	 * Polygon
	 * @return fillR
	*/
	public int getFillR() {
		return this.fillR;
	}
	
	/**
	 * The method returns the Green value to fill in the 
	 * Polygon
	 * @return fillG
	*/
	public int getFillG() {
		return this.fillG;
	}
	
	/**
	 * The method returns the Blue value to fill in the 
	 * Polygon
	 * @return fillB
	*/
	public int getFillB() {
		return this.fillB;
	}

	/**
	 * The method sets the Fill property of this 2DShape
	 * to noFill
	 * @post the fill property of this 2DShape will
	 * be changed to noFill
	*/
	public void setNoFill() {
		 noFill = true;;
	}
	
	/**
	 * The method sets the Fill property of this 2DShape
	 * to fill
	 * @post the fill property of this 2DShape will
	 * be changed to noFill
	*/
	public void setFill() {
		 noFill = false;
	}
	
	/**
	 * The method returns the fillState of this 2DShape
	 * @return True or False depending on the Fill state of this 
	 * 2DShape
	 * @return true is noFill is true or false otherwise
	*/
	public boolean getFillState() {
		return noFill;
	}

	/**
	 * Calculates and returns a floating point area value for this
	 * Shape2D object 
	 * @return the area of this 2DShape
	*/
	public abstract double calcArea(); //Only for Circle or Rectangle

	/**
	 * Calculates and returns a floating point perimeter value for 
	 * this Shape2D object 
	 * @return the perimeter of this 2DShape
	*/
	public abstract double calcPerimeter(); //Only for Circle or Rectangle	
	
}
