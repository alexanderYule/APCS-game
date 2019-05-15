package adsouza.shapes;

import processing.core.PApplet;

/**
 *  This class represents a simple Shape. It can perform common calculations
 *  that alter a Shapes with respect to its attributes and can draw common 
 *  qualities of a Shape to Processing PApplet 
 *  
 *  @author D'Souza
 *  @version 10/26/2018
 *  
 *  +: The classes are written together well.
 *  +: you can set the color of stroke and fill.
 *  -: protected field should have comment 
 *  -: doesn't use super for x and y.
 *  Delta: maybe have a wider variety of methods
 *  Delta: you can store r,g,b values in one variable of Color and then get the individual r,g,b values from color. 
*/

public abstract class Shape{
	
	/**
	 * x, y- protected fields
	 */
	protected double x , y;
	
	private int strokeWeight;        
	private int strokeR, strokeG, strokeB;
	
	/**
	 *  Creates a Shape instance with reference to 
	 *  the coordinates of (x,y) 
	 *  
	*/
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
		this.strokeWeight = 0;
		this.strokeR = 0;
		this.strokeG = 0;
		this.strokeB = 0;
	}
			
	/** 
	 * 	Moves this Shape to a new location with coordinates at (x,y)
	 *  @param x is the x-coordinate of the location this Shape will be
	 *  moved to 
	 *  @param y is the y-coordinate of the location this Shape will be
	 *  moved to 
	 *  @post the x and y coordinates through which this Shape is drawn
	 *  will be changed
	*/
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/** 
	 * 	Sets the weight of the sides of this shape
	 *  @param x is the weight value
	 *  @post the strokeWeight property of this Shape 
	 *  will be changed
	*/
	public void setStrokeWeight(int x) {
		this.strokeWeight = x;
	}	
	
	/**
	 * Sets this Shape's stroke color with an r, g, b
	 * value
	 * @param r The amount of Red this shape's stroke will be 
	 * colored with
	 * @param g The amount of Green this shape's stroke will be 
	 * colored with
	 * @param b The amount of Blue this shape's stroke will be 
	 * colored with
	 * @post the r, g, b stroke color values will be changed for 
	 * this Shape
	*/
	public void setStrokeColor(int r, int g, int b) {
		this.strokeR = r;
		this.strokeG = g;
		this.strokeB = b;
	}
	
	/**
	 * Returns the x-coordinate of this Shape based on which this
	 * Shape is drawn
	 * @return x The x-coordinate of this Shape
	*/
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns the y-coordinate of this Shape based on which this
	 * Shape is drawn
	 * @return y The y coordinate of this Shape
	*/
	public double getY() {
		return this.y;
	}
	
	/**
	 * Draws a new instance of a Shape using Processing's PApplet
	 * @param drawer PApplet used to draw this Shape
	 * @pre drawer must not be null 
	 * @post the method will change the stroke properties of weight
	 * and color of this Shape
	*/
	public void draw(PApplet drawer) {
		drawer.strokeWeight(strokeWeight);
		drawer.stroke(strokeR, strokeG, strokeB);
	}

	public abstract boolean isPointInside(double x2, double y2);
	

}
