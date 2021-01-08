/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: A program that reads a Java source-code file and 
 * reports the number of keywords (including null, true, and false) in the file. 
 * If a keyword is in a comment or in a string, don’t count it. 
 * Pass the Java file name from the command line.
 * Due: 10/18/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya O. Kurin
*/



package o_Kurin_D_Project_6_1;

import java.io.File;
import java.util.*;


public class CountingJavaKeyWords {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Java source file: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (file.exists() && filename.substring(
			filename.lastIndexOf(".")).equals(".java")) {
			System.out.println("The number of keywords in " + filename 
				+ " is " + countKeywords(file));
		}
		else {
			System.out.println("File " + filename + " does not exist");
		}
	}

	/** read in a Java source code file and count the occurrence of each keyword in 
	  * the file. Keywords in a comment or in a string literal are ont counted */
	public static int countKeywords(File file) throws Exception {
		// Array of all Java Keywords + ture, false and null
		String[] keywordString = {"abstract", "assert", "boolean",
			"break", "byte", "case", "catch", "char", "class", "const",
			"continue", "default", "do", "double", "else", "enum",
			"extends", "for", "final", "finally", "float", "goto",
			"if", "implements", "import", "instanceof", "int",
			"interface", "long", "native", "new", "package", "private",
			"protected", "public", "return", "short", "static",
			"strictfp", "super", "switch", "synchronized", "this",
			"throw", "throws", "transient", "try", "vo:id", "volatile",
			"while", "true", "false", "null"};

		Set<String> keywordSet = 
			new HashSet<>(Arrays.asList(keywordString));
		int count = 0;

		Scanner input = new Scanner(file);

		while (input.hasNext()) {
			String word = input.next();
			if (word.equals("//"))
				input.nextLine();
			else if (word.contains("\""))
				while (input.hasNext() && !input.next().contains("\"")) {}
			else if (word.contains("/*"))
				while (input.hasNext() && !input.next().contains("*/")) {}
			else if (keywordSet.contains(word))
				count++;
		}

		return count;
	}
}
