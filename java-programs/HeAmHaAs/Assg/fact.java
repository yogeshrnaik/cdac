/******************************************************************************
* This is a simple program to calculate the value of n factorial (n!)         *
* It is done using recursion, because everyone grew to love recursion         *
* in CS 130/134 :)                                                            *
******************************************************************************/

import java.io.*;

public class fact {
    /**************************************************************************
    * The factorial function will return the value of num factorial           *
    * 0! = 1, n! = n * (n-1)!                                                 *
    * PRE: num >= 0                                                           *
    * POST: result = num!                                                     *
    **************************************************************************/
    public static int factorial (int num) {
	int calc = 0;  // The value of num! to be returned

	if (num <= 1)
	    // num == 0 or num == 1 (assume num >= 0)
	    calc = 1;
	else
	    calc = num * factorial (num - 1);

	return calc;
    }

    /**************************************************************************
    * The main routine                                                        *
    **************************************************************************/
    public static void main (String [] args) {
	int number = 0;       // The number read in

	// See if we have the correct number of input parameters
	if (args.length != 1) {
	    System.out.println ("You must provide a paramater of 1 number.");
	    System.exit (-1);
	}

	// Try to convert the parameter to a number
	try {
	    number = Integer.parseInt (args [0]);
	} catch (NumberFormatException x) {
	    System.out.println ("The parameter must be a number.");
	    System.exit (-1);
	}

	// Check that it is at least 0
	if (number < 0) {
	    System.out.println ("Can only calculate non-negative factorials.");
	    System.exit (-1);
	}

	// Calculate the result
	System.out.println (factorial (number));
    }

    private fact () {}
}
