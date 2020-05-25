import ncst.pgdst.*;
import java.lang.*;

public class PermutationNumber{
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                int length = sin.readInt();
                int number = sin.readInt();
                String string = getString(length);
 		printPermutation(string,number);
		System.out.println();
        }
        static void printPermutation(String string, int number)
        {
		int length = string.length();
		if(length == 0)return ;
		int divisor = getFactorial(length - 1);
		int charNumber = number / divisor;
		if(charNumber != 0 && number % divisor == 0) charNumber--;
		int nextNumber = number % divisor;
		if(nextNumber == 0 && number != 0) nextNumber = divisor;
		System.out.print(string.charAt(charNumber));
		String newString = string.substring(0,charNumber) + string.substring(charNumber + 1);
		printPermutation(newString,nextNumber);

        }
        static String getString(int length)
        {
		final int CHARa = 97;
                String string = "";
		for(int i = 0 ; i < length ; i++)
		{
			string = string + (char)(CHARa + i);
		}
//                System.out.println(string);
                return string;
        }
	static int getFactorial(int number)
	{
		if(number <= 0) return 1;
		else return number * getFactorial(number - 1);
	}
}
