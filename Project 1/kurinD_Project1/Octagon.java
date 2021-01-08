/*
 * Class: CMSC214 
 * Instructor: Prof Mark Estep
 * Description: Designing an Octagon class that extends from GeometricObject abstract class 
 * and implements Cloneable and Comparable interfaces. 
 * Due: 09/13/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya Ozdemir Kurin_
*/



package kurinD_Project1;

public class Octagon extends GeometricObject implements Cloneable, Comparable<Octagon> {
    //Date field
	private double side;
	
	
	// Getter and setter methods
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

	//Constructor method for Octagon
	public Octagon(double side) {
			super();
			this.side = side;
	}
	
	
	// compareTo method returns -1, 0 or 1 if this object is bigger, equal or smaller than the object o
	@Override
	public int compareTo(Octagon o) {
 		if(getArea() > o.getArea()) {
 			return 1;
 		} else if(getArea() < o.getArea()) {
 			return -1;
 		} else {
 			return 0;
 		}
	}
	
	//clone method to create a new object with the same content of the calling object
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch(CloneNotSupportedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

	@Override
	public double getPerimeter() {	
		return this.side * 8;
	}

	@Override
	public double getArea() {
		return ((2 + (4 / Math.sqrt(2)) * this.side * this.side));
	}

}


  