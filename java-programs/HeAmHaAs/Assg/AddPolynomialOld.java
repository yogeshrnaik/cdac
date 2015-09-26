import ncst.pgdst.*;
import java.lang.*;

public class AddPolynomial{
	public static void main(String [] args) throws IOException
	{
		final int LASTLIMIT = 15;
		int max1 = 0;
		int max2 = 0;
		SimpleInput sin = new SimpleInput();
		int[][][] polynomial = new int[2][LASTLIMIT][2];
		for(int j = 0 ; j < 2 ; j++)
		{
			for(int i = 0 ; i < LASTLIMIT; i++)
			{
				polynomial[j][i][0] = sin.readInt();
				polynomial[j][i][1] = sin.readInt();
				if(polynomial[j][i][0] == -1 && polynomial[j][i][1] == -1)
				{
					if(j == 0) max1 = i;
					else max2 = i;
					i = LASTLIMIT;
				}
			}

			sin.skipWhite();
		}
System.out.println(max1 + " " + max2);
		int [][] solution = new int[LASTLIMIT + LASTLIMIT][2];	
		int counter = 0;
		for(int i = 0 ; i < max1 ; i++)
		{
			for(int j = 0 ; j < max2 ; j++)
			{

				if(!(polynomial[1][j][0] == 0 && polynomial[1][j][1] == 0))
				if(polynomial[0][i][1] == polynomial[1][j][1])
				{
					solution[counter][0] = polynomial[0][i][0] + polynomial[1][j][0];
					solution[counter][1] = polynomial[0][i][1];
					counter++;
					polynomial[1][j][0] = 0;
					polynomial[1][j][1] = 0;
					polynomial[0][i][0] = 0;
					polynomial[0][i][1] = 0;

//System.out.println("solution["+(counter-1)+"]["+0+"]="+solution[counter-1][0]+" solution["+(counter-1)+"]["+1+"]="+solution[counter-1][1]);
				}

//System.out.println("polynomial["+0+"]["+i+"]["+0+"] = "+polynomial[0][i][0]+" "+polynomial[0][i][1]+" "+"\tpolynomial["+1+"]["+j+"]["+0+"] = "+polynomial[1][j][0]+" "+polynomial[1][j][1]  + " max1 = " + max1 + " max2 = "+ max2);
			}
			
//			System.out.println("\npol 2 " + polynomial[1][i][0] + " " + polynomial[1][i][1]);
		}
		// filling remaining in second polynomial
		for(int j = 0 ; j < max1 ; j++)
		{
			if(!(polynomial[0][j][0] == 0 && polynomial[0][j][1] ==0))
			{
				solution[counter][0] = polynomial[0][j][0];
				solution[counter][1] = polynomial[0][j][1];
				counter++;
			}
		}

		for(int j = 0 ; j < max2 ; j++)
		{
			if(!(polynomial[1][j][0] == 0 && polynomial[1][j][1] ==0))
			{
				solution[counter][0] = polynomial[1][j][0];
				solution[counter][1] = polynomial[1][j][1];
				counter++;
			}
		}
		for(int i = 0 ; i < counter ; i++)
		{
			int max00 = 0;
			int max01 = Integer.MIN_VALUE;
			int jValue = counter;
			for(int j = 0 ; j < counter ; j++)
			{
				if(!(solution[j][0] == 0 && solution [j][1] == 0))
				{

//System.out.println("solution["+(j)+"]["+0+"]="+solution[j][0]+" solution["+(j)+"]["+1+"]="+solution[j][1]);
					if(solution[j][1] >= max01)
					{
						max01 = solution[j][1];
						max00 = solution[j][0];
						jValue = j;
					}

//System.out.println("solution["+(j)+"]["+0+"]="+solution[j][0]+" solution["+(j)+"]["+1+"]="+solution[j][1]);
				}

			}
			if(jValue < counter)
			{
				solution[jValue][0] = 0;
				solution[jValue][1] = 0;
			}
			if(max00 != 0)System.out.print(max00 + " " + max01 + " ");
			
		}
		System.out.println();
	}
}
