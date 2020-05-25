import ncst.pgdst.*;

public class zero
{
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int rows,columns,i,j;
		int matrix[][] = new int[10][10];
		boolean used[][] = new boolean[10][10];
		boolean done=false,flag=false;;
		rows = sin.readInt();
		columns = sin.readInt();
		

		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
			{
				matrix[i][j] = sin.readInt();
				used[i][j] = false;
			}
		for (i=0;i<rows;i++)
		{
			for (j=0;j<columns;j++)
			{
				if (matrix[i][j]==0)
					done=true;				
				else
				{
					done=false;
					break;
				}
			}
			if (done==true)
			{
				for (j=0;j<columns;j++)
					used[i][j] = true;
				
			}
			done=false;
		}
			
		for (i=0;i<columns;i++)
		{
			for (j=0;j<rows;j++)
			{
				if (matrix[j][i]==0)
					done=true;				
				else
				{
					done=false;
					break;
				}
			}
			if (done==true)
			{
				for (j=0;j<rows;j++)
					used[j][i] = true;
				
			}
			done=false;
		}
		done = false;
		for (i=0;i<rows;i++)
		{
			if (used[i][0]==false)
			{
				System.out.print(matrix[i][0]);
				flag=true;
			}
			for (j=1;j<columns;j++)
			{
				if (used[i][j]==false)
				{
					if (flag==true)
						System.out.print(" ");
					System.out.print(matrix[i][j]);
					done = true;
				}
			}
			if (done==true)
				{
					System.out.print("END");
					System.out.println();
				}
			done=false;flag=false;
		}
	}
}
