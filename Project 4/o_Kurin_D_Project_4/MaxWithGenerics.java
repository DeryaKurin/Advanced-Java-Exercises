/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: Implement the following method that returns the maximum element in an array.  
 * public static <E extends Comparable<E>> E max(E[] list)
 * Due: 10/04/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: _Derya Ozdemir Kurin_
*/

package o_Kurin_D_Project_4;

import java.util.ArrayList;

public class MaxWithGenerics {
	
	// A generic method to find the max value in an ArrayList 
	public static <E extends Comparable<E>> E max(ArrayList<E> list) {
		
        //Set the first object to the max 
		E max = list.get(0);
         
		// Iterate through a loop and compare each object to the max value
        for (int i = 1; i < list.size(); i++) {
        	//If the object is greater than the max, set new max value
            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }

        return max;
    }

	public static void main(String[] args) {
		//Declare a  list of Integer type
		ArrayList<Integer> list = new ArrayList<>();
		
        // Add integers to the list using a for loop
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        
        //Call generic max method on list and print
        System.out.println(max(list));
        
        
        // Declare a list of Character type
        ArrayList<Character> list1 = new ArrayList<>();
        
        //Add Characters to the list
        for(Character i = 'a'; i <= 'z'; i++) {
        	list1.add(i);
        }
        
        //Call generic max method on list and print
        System.out.println(max(list1));
        
        // Declare a list of type String
        ArrayList<String> list3 = new ArrayList<>();
        //Add Strings to the list
        list3.add("Windsor");
        list3.add("Wuhan");
        list3.add("Washington");
        list3.add("Wellington");
        
        //Call generic max method on list and print
        System.out.println(max(list3));

	}	
}
