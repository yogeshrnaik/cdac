import ncst.pgdst.*;

public class GameOfLife
{
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int rows,columns,i,j,k,count,x,y,N;
		String temp;
		char matrix[][] = new char[30][30];
		char nextG[][] = new char[30][30];
		rows = sin.readInt();
		columns = sin.readInt();
		
		
		for (i=0;i<rows;i++)
		{
			temp = sin.readWord();
			for (j=0;j<columns;j++)
				matrix[i][j] = temp.charAt(j);
		}
		
		
		
		N = sin.readInt();
		for (k=0;k<N;k++)
		{
			for (i=0;i<rows;i++)
				for (j=0;j<columns;j++)
				{
					count=0;
					y=i-1;
					for (x=j-1;x<=j+1;x++)
						if (x>=0 && x<columns)
							if (y>=0 && y<rows)
								if (matrix[y][x]=='@')
									count++;
					y++;x=j-1;	
					if (x>=0 && x<columns)
						if (y>=0 && y<rows)
							if (matrix[y][x]=='@')
								count++;
					x=j+1;
					if (x>=0 && x<columns)
						if (y>=0 && y<rows)
							if (matrix[y][x]=='@')
								count++;
					y++;
					for (x=j-1;x<=j+1;x++)
						if (x>=0 && x<columns)
							if (y>=0 && y<rows)
								if (matrix[y][x]=='@')
									count++;
					if (count==1 || count>=4 || count==0)
						nextG[i][j] = '#';
					else if (count==2)
						if (matrix[i][j]=='@')
							nextG[i][j] = '@';
						else
							nextG[i][j] = '#';
					else if (count==3)
						nextG[i][j] = '@';
					
				}
				for (x=0;x<rows;x++)
					for (y=0;y<columns;y++)
						matrix[x][y] = nextG[x][y];
		}
		count=0;
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
				if (matrix[i][j]=='@')
					count++;		
		System.out.println(count);
	}
}

					
