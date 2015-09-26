import ncst.pgdst.*;
import java.lang.*;


public class Assg5{
	public static void main(String[] args) throws IOException
	{

		final char HASH ='#';
		final char AT_THE_RATE = '@';
		SimpleInput sin = new SimpleInput();
		int row = sin.readInt();
		int column = sin.readInt();
		sin.skipWhite();
		char[][] current = new char[row][column];
		char[][] next = new char[row][column];
		
		for(int i = 0 ; i < row ; i++)
		{
			for(int j = 0 ; j < column ; j++)
			{
				current[i][j] = sin.readChar();
				sin.skipWhite();
			}
		}
		sin.skipWhite();
		int generation = sin.readInt();
		for(int d = 0 ; d < generation ; d++)
		{
			
			next = getNextGeneration(current,row,column);
			current = next;
		}
//		System.out.println("row = "+row+"column "+column);
		int surviving=0;
		for(int i = 0 ; i < row ; i++)
			for(int j = 0 ; j < column ; j++)
			{
				if(next[i][j]==AT_THE_RATE)surviving++;
			//	System.out.print(next[i][j]);
			//	if(j==column-1)System.out.println();
				//System.out.println("next[" + i + "][" + j + "]="+next[i][j]);
			}
		
		System.out.println(surviving);		
	}
	static char[][] getNextGeneration(char[][] current,int row,int column)
	{
		int count = 0;
		final char HASH ='#';
		final char AT_THE_RATE = '@';
		char[][] next = new char[row][column];
		for(int i = 0 ; i < row ; i++)
			for( int j = 0 ; j < column ; j++)
			{
				
				{//getCount
					int startRow,endRow,startColumn,endColumn;
					if(i-1<0)startRow = 0;
					else startRow = i-1;
					if(j-1<0)startColumn = 0;
					else startColumn = j-1;
					if(i+1>row-1)endRow = row-1;
					else endRow = i+1;
					if(j+1>column-1)endColumn = column-1;
					else endColumn = j+1;
					
					count = 0;
					for(int a = startRow ; a <= endRow ; a++)
						for(int b =  startColumn ; b <= endColumn ; b++)
						{
							if(a != i || b != j)
							{
								if(current[a][b] == AT_THE_RATE)count++;	
							
							}
				//			System.out.println(startRow+" " + endRow+" " + startColumn+ " " + endColumn+ " " + "current["+a+"]["+b+"]="+current[a][b]);
						}
				}
			//	System.out.println("i="+i+" j="+j+" count="+count );
				if(current[i][j] == AT_THE_RATE)
				{
					if(count == 2 || count == 3) next[i][j] = AT_THE_RATE;
					else next[i][j] = HASH;
				}
				else 
				{
					if (count == 3) next[i][j] = AT_THE_RATE;
					else next[i][j] = HASH;
				}

			}
		return next;
	}
}
