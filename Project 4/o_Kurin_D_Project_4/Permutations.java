/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: A recursive method to print all the permutations of a string.
 * Due: 10/04/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: _Derya Ozdemir Kurin_
*/

package o_Kurin_D_Project_4;

public class Permutations {
	
	public static void displayPermutation(String s) {
		// In the first iteration, we don't set any fixed part yet and pass and empty string instead 
		// and let the permutation function create its fixed part with each iteration in the loop 
		displayPermutation("", s);
	}
	
	// This method is called in showPermutation, so the 
	public static void displayPermutation(String fixed, String str) {
		// n will store the length of str 
	    int n = str.length();
	    // In order to end the recursive calls, when the remaining part of the str becomes empty, 
	    // it prints out the permutation stored in the fixed part (in first argument of this method)
		if(n == 0) {
		   System.out.println(fixed);
		} else {
			// If the fixed part is not complete yet, 
			// it changes the fixed part arrangement with each iteration and makes a call to itself
			for(int i = 0; i < n; i ++) {
				displayPermutation(fixed + str.charAt(i), str.substring(0, i) + str.substring(i + 1,n));
			}
		}
	}



	public static void main(String[] args) {
		String str = "abd";
		// Calling displayPermutation static method with a string argument
		displayPermutation(str);

	}

}
