
/*
 * Class: CMSC214 
 * Instructor: Prof Mark Estep
 * Description: A program to decrypt the file whose content was 
 * previously encrypted by adding 5 to every byte. 
 * Due: 09/27/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya Ozdemir Kurin
*/


package o_Kurin_D_Project03_17;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileEncrypting {
	public static void main(String[] args) throws Exception {
		// Open an instance of Scanner 
	    Scanner input = new Scanner(System.in);
	    
	    // Prompt user to enter a file name to decrypt
	    System.out.print("Enter a file name to decrypt: ");
	    
	    //Create an instance of BufferedInputStream to read the file
	    BufferedInputStream in = new BufferedInputStream(
	    		//Create an instance of File for FileInputStream
	      new FileInputStream(new File(input.next())));

	    // Prompt the user to name the decrypted file to be created
	    System.out.print("Enter a name for the decrypted output file: ");
	    BufferedOutputStream output = new BufferedOutputStream(
	      new FileOutputStream(new File(input.next())));

	    int value;
	    while ((value = in.read()) != -1) {
	      output.write(value - 5);
	    }
	    
	    // close the input and output 
	    input.close();
	    output.close();
	  }
}
