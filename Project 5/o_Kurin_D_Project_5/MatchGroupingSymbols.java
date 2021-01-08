/*
 * Class: CMSC214 
 * Instructor: Prof Mark Estep
 * Description: A Java program contains various pairs of grouping symbols, such as:
 *     Parentheses: ( and )
 *	   Braces: { and }
 *	   Brackets: [ and ]
 * Note that the grouping symbols cannot overlap. For example, (a{b)} is illegal.
 * Write a program to check whether a Java source-code file has correct pairs of grouping symbols. 
 * Pass the source-code file name as a command-line argument.
 * Due: 10/11/2020
 *  I pledge that I have completed the programming assignment independently.
 *  I have not copied the code from a student or any source.
 *  I have not given my code to any student.
 *  Print your Name here: Derya Ozdemir Kurin
*/



  
package o_Kurin_D_Project_5;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {
	
	
	public static void main(String[] args) throws IOException {
		// Check command-line argument
		if (args.length != 1) {
			System.out.println("Source code file name not entered");
			System.exit(0);
		}

		// Check if file exists
		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("The file " + args[0] + " does not exist!");
			System.exit(1);
		}

		// Create a stack
		Stack<Character> symbols = new Stack<>();

		try ( // Create an input stream for file
				Scanner input = new Scanner(file);
		) {
			// Continuously read chars from input
			while (input.hasNext()) {
				String line = input.nextLine();
				for (int i = 0; i < line.length(); i++) {
					char ch = line.charAt(i);
					// Push symbols (, {, and [ on to the stack
					if (ch == '(' || ch == '{' || ch == '[') {
						symbols.push(ch);
					} // Process stack
					else if (ch == ')' || ch == '}' || ch == ']') {
						checkSymbolOrder(symbols, ch);
					}
				}
			}
		}
		
		System.out.println("The Java source code file " +
			(symbols.isEmpty() ? "has" : "does not have") + " correct symbol pairs.");	
	}

	/** Matches the grouping symbols */
	private static void checkSymbolOrder(Stack<Character> stack, Character ch) {
		// Remove matching symbols from stack 
		if ((stack.peek() == '(' && ch == ')') ||
			 (stack.peek() == '[' && ch == ']') ||
			 (stack.peek() == '{' && ch == '}')) {
			stack.pop();	
		}
		else if ((stack.peek() != '(' && ch == ')') ||
			 (stack.peek() != '[' && ch == ']') ||
			 (stack.peek() != '{' && ch == '}')) {
			System.out.println("The Java source code does not have correct pairs.");
			System.exit(1);
		}
	}

}
