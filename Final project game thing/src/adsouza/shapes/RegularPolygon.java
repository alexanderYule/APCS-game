package adsouza.shapes;
import processing.core.PApplet;

/**
 *  This class represents a simple Polygon Shape. It can create a 
 *  representation of any Regular Polygon shape and perform 
 *  various trigonometric calculations regarding a polygon shape.
 *  
 *  @author D'Souza
 *  @version 10/26/2018
*/

public class RegularPolygon extends Shape2D{
	
	private double sideLength;
	private int numSides;
	private double inscr;
	private double circR;
	

		/**
		*  Creates a default RegularPolygon
		*  object with all dimensions and 
		*  properties set to zero.
		*/		
		public RegularPolygon(){
			super(0,0,255,255,255);
			this.numSides = 3;
			this.sideLength = 100;
			this.calcr();			   
			this.calcR();
		}
		   
		/**
		 *  Creates a custom, RegularPolygon object with the specified number of
		 *  sides and side length
		 *  @param numSides is the number of sides this Regular Polygon will have
		 * 	@param sideLength is the side length of this RegularPolygon object 
		*/
		   public RegularPolygon(double x, double y, int numSides, double sideLength){
			   super(x,y,255,255,255);
			   this.numSides = numSides;
			   this.sideLength = sideLength;
			   this.calcr();			            //Initializes inscribed and circumscribed radiuses
			   this.calcR();						//through methods
		   }


		   /**
			*  Calculates the Radius of the inscribed Circle of this 
			*  RegularPolygon object
			*  @post the value of the inscribed angle will be changed
		   */		   
		   public void calcr(){ //Radius of Inscribed Circle
			   this.inscr = (0.5)*sideLength*(1/Math.tan(Math.PI/numSides));
		   }

		   /**
			*  Calculates the Radius of the circumscribed Circle 
			*  of this RegularPolygon object
			*  @post the value of the circumcribed angle will
			*  be changed 
		   */	
		   public void calcR(){  //Radius of Circumscribed circle
			   this.circR = (0.5)*sideLength*(1/Math.sin(Math.PI/numSides));
		   }   

		   /**
			*  Calculates and returns the vertex angle 
			*  value of this RegularPolygon object
			*  @return the vertex angle value of this 
			*  RegularPolygon 
			*  @post the vertex Angle value of this
			*  RegularPolygon will be changed
		   */
		   public double calcVertexAngle(){
			   return (((double)this.numSides-2)/this.numSides)*180;
		   }

		   /**
			*  Calculates and returns the perimeter 
			*  value of this RegularPolygon object
			*  @return the perimeter of this 
			*  RegularPolygon 
		   */
		   public double calcPerimeter(){
			   return numSides*sideLength;
		   }
		   
		   /**
			*  Calculates and returns the area 
			*  value of this RegularPolygon object
			*  @return the area value of this 
			*  RegularPolygon 
		   */
		   public double calcArea(){
			   return (0.5)*numSides*Math.pow(circR,2)
					   *Math.sin(2*Math.PI/numSides);
		   }

		   /**
			*  Calculates and returns the number of sides
			*  in this RegularPolygon object
			*  @return the number of sides of this 
			*  RegularPolygon
		   */
		   public int getNumSides(){
			   return this.numSides;
		   }

		   /**
			*  Calculates and returns the side lengths
			*  in this RegularPolygon object
			*  @return the side length value of this
			*  RegularPolygon object
		   */
		   public double getSideLength(){
			   return this.sideLength;
		   }
		  
		   /**
			 * Draws a new instance of the RegularPolygon object using
			 * Processing's PApplet
			 * @param drawer PApplet used to draw the RegularPolygon on
			 * @pre drawer must not be null 
			*/
		   public void draw(PApplet drawer) {

			   super.draw(drawer);	   
			   
			   double initX = this.x+(circR*Math.cos(2*Math.PI/numSides));
			   double initY = this.y-(circR*Math.sin(2*Math.PI/numSides));

			   new Line(this.x+circR, this.y, initX, initY).draw(drawer);
			   
			   for(int i = 	2; i <= numSides; i++) {
				   new Line(initX, initY, this.x+(circR*Math.cos((2*Math.PI/numSides)*i)), this.y-(circR*Math.sin((2*Math.PI/numSides)*i))).draw(drawer);
				   initX = this.x+(circR*Math.cos((2*Math.PI/numSides)*i));
				   initY = this.y-(circR*Math.sin((2*Math.PI/numSides)*i));
			   }
		   
		   }
			   
		   /**
			 * Draws a Circle that bounds this RegularPolygon object 
			 * at its edges using Processing's PApplet
			 * @param drawer PApplet used to draw the RegularPolygon on
			 * @pre drawer must not be null 
			*/
		   public void drawBoundingCircles(PApplet drawer){
			   
			Circle circumC = new Circle(this.x-circR, this.y-circR, circR*2, circR*2, 0, 0, 0);
			circumC.setNoFill();
			circumC.draw(drawer);
			
			Circle inscrC = new Circle(this.x-inscr, this.y-inscr, inscr*2, inscr*2, 0, 0, 0);
			inscrC.setNoFill();
			inscrC.draw(drawer);
			
		   }

		   /**
			 *  Checks whether the point (x,y) is inside the rectangle 
			 *  circumscribed in this polygon's circumscribed circle 
			 * 	@param x is the x-coordinate of the point to check for inclusion
			 * 	@param y is the y-coordinate of the point to check for inclusion
			 * 	@return true is the point is inside this circumscribed rectangle
			 *  or false otherwise
			*/
			public boolean isPointInside(double x, double y) {

				if((x >= this.x-circR && x <= this.x+circR) && 
						(y >= this.y-circR && y <= this.y+circR)) { 
					return true;
				}
				else {
					return false;
				}
			}

		   


}
