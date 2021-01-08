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

import java.util.Date;

public abstract class GeometricObject {
	
	//  Data fields
	private String color;
	private boolean filled;
	private Date dateCreated;
	
	// Getters and Setters for the data fields
	
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public boolean isFilled() {
		return filled;
	}


	public void setFilled(boolean filled) {
		this.filled = filled;
	}


	public Date getDateCreated() {
		return dateCreated;
	}

	
	
	// Default constructor
	protected GeometricObject() {
		this.color = "black";
		this.filled = false;
		this.dateCreated = new Date();
	}
	
	// toString method to print out the object to the console
	public String toString() {
		return "Created on: " + dateCreated + "\nColor: " + color + "\nFilled: " + filled;	
	}
	

	public abstract double getPerimeter();
	
	public abstract double getArea();
}
