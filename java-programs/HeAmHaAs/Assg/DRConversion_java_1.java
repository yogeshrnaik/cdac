import ncst.pgdst.*;
import java.lang.*;

public class DRConversion{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int numberOfInputs = sin.readInt();
		String[] input = new String[numberOfInputs];
		for(int i = 0 ; i < numberOfInputs ; i++)
		{
			sin.skipWhite();
			input[i] = sin.readLine();
		}
		for(int i = 0 ; i < numberOfInputs ; i++)
		{
			if (getValue(input[i].charAt(0))==0)
			{System.out.println(getDecimalToRomanConversion(input[i]));}
			else {System.out.println(getRomanToDecimalConversion(input[i]));}
//			System.out.println(input[i]);
		}
	}
	static String getDecimalToRomanConversion(String decimalString)
	{
		final int I = 1;
		final int V = 5;
		final int X = 10;
		final int L = 50;
		final int C = 100;
		final int D = 500;
		final int M = 1000;
		String roman = "";
		int decimal = Integer.parseInt(decimalString);
		while ( decimal != 0 )
		{
			if ( decimal >= M ) 
			{ 
				roman = roman + 'M' ; decimal = decimal - M ; 
			}
			if ( decimal >= D && decimal < M ) 
			{ 
				if ( decimal >= D && decimal < M - C ){ roman = roman + 'D' ; decimal = decimal - D ; }
				else { roman = roman + "CM" ; decimal = decimal - (M - C); }
			}
			if ( decimal >= C && decimal < D ) 
			{ 
				if ( decimal >= C && decimal < D - C ){ roman = roman + 'C' ; decimal = decimal - C ; }
				else { roman = roman + "CD" ; decimal = decimal - (D - C); }
			}
			if ( decimal >= L && decimal < C ) 
			{ 
				if ( decimal >= L && decimal < C - X ){ roman = roman + 'L' ; decimal = decimal - L ; }
				else { roman = roman + "XC" ; decimal = decimal - (C - X); }
			}
			if ( decimal >= X && decimal < L ) 
			{ 
				if ( decimal >= X && decimal < L - X ){ roman = roman + 'X' ; decimal = decimal - X ; }
				else { roman = roman + "XL" ; decimal = decimal - (L - X); }
			}
			if ( decimal >= V && decimal < X ) 
			{ 
				if ( decimal >= V && decimal < X - I ){ roman = roman + 'V' ; decimal = decimal - V ; }
				else { roman = roman + "IX" ; decimal = decimal - (X - I); }
			}
			if ( decimal >= I && decimal < V ) 
			{ 
				if ( decimal >= I && decimal < V - I ){ roman = roman + 'I' ; decimal = decimal - I ; }
				else { roman = roman + "IV" ; decimal = decimal - (V - I); }
			}
		}
		
		return roman;
	}

	static String getRomanToDecimalConversion(String romanString)
	{
		int decimal = 0;
		int length = romanString.length();
		for (int i = 0 ; i < length-1 ; i++)
		{
			if(getValue(romanString.charAt(i))<getValue(romanString.charAt(i+1)))decimal = decimal - getValue(romanString.charAt(i));
			else decimal = decimal + getValue(romanString.charAt(i));	
			//System.out.println(decimal + " " + getValue(romanString.charAt(i))+"< "+getValue(romanString.charAt(i+1))+" " + romanString.charAt(i)+ " " + romanString.charAt(i+1));
		}
		decimal = decimal + getValue(romanString.charAt(length - 1));
		return Integer.toString(decimal);
		
	}
	static int getValue(char c)
	{

		switch(c)
		{
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default : return 0;

		}
	}
}
