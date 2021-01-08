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

public class TestOctagon {

	public static void main(String[] args) throws IllegalArgumentException {
		
		
		double sideValue = 5;
		
		
		//Check whether the side value is acceptable if not throw an IllegalArgumentException
		
		if(sideValue <= 0) { 
			IllegalArgumentException ex = new IllegalArgumentException("Side value cannot be 0 or negative");
			throw ex;
		}
		
		// Create an Octagon object o1
		Octagon o1 = new Octagon(sideValue);	
		
		// Clone o1 to a new Octagon object o2
		Octagon o2 = (Octagon) o1.clone();
		
//		The following line was used to test compareTo method
//		Octagon o2 = new Octagon(sideValue -2 );
		
		//Compare two objects using compareTo method
		
		if(o1.compareTo(o2) > 0) {
			System.out.println("o1 is bigger than o2");
		} else if(o1.compareTo(o2) < 0) {
			System.out.println("o2 is bigger than o1");
		} else {
			System.out.println("o1 and o2 are equal");
		}
	}

}
