import ncst.pgdst.*;

public class Connect
{	
	int moves[] = new int[42];int i,j,k;int count=0;
	String mat[][] = new String[6][7];String str;
	int temp1[] = {3,0,4,0,5,0,5,1,5,2,5,3};
	int temp2[] = {3,6,4,6,5,6,5,5,5,4,5,3};
	int columns[] = new int[7];
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Connect connect = new Connect(sin);
	}
	Connect(SimpleInput sin) throws IOException
	{
		


		
		for (i=0;i<42;i++)
			moves[i] = sin.readInt();
		for (i=0;i<6;i++)
			for (j=0;j<7;j++)
				mat[i][j] = " "; 
		
		for (i=0;i<7;i++)
			columns[i] = 5;
		
		for (i=0;i<42;i++)
		{
		
			
			if (i%2==0)
				str = "YELLOW";
			else
				str = "RED";
			mat[columns[moves[i]-1]] [moves[i]-1] = str;
			columns[moves[i]-1]--;
			
	/*	for (i=0;i<6;i++)
		{
			for (j=0;j<7;j++)
				System.out.print(mat[i][j]+" ");
			System.out.println();
		}		*/
			
			for (j=5;j>=0;j--)
				for (k=0;k<4;k++)
				if (mat[j][k]==str && mat[j][k+1]==str && mat[j][k+2]==str && mat[j][k+3]==str)
				{	i++;
					System.out.println(str+" "+i);print();
					System.exit(1);
				}
			
			for (k=0;k<6;k++)
				for (j=0;j<3;j++)
				if (mat[j][k]==str && mat[j+1][k]==str && mat[j+2][k]==str && mat[j+3][k]==str)
				{	i++;
					System.out.println(str+" "+i);print();
					System.exit(1);
				}
		
			for (j=0;j<12;j+=2)
				recurse1(temp1[j],temp1[j+1]);

			for (j=0;j<12;j+=2)
				recurse2(temp2[j],temp2[j+1]);
			
			






		}
		System.out.println("DRAW");
		print();

	}
	public void recurse1(int x,int y)
	{
		if (mat[x][y]==str)
			count++;
		else
			count=0;
		if (count==4)
		{	i++;
			System.out.println(str+" "+i);print();
			System.exit(1);
		}
		
		if (x-1>=0 && y+1<=6)
			recurse1(x-1,y+1);
	}
	
	public void recurse2(int x,int y)
	{
		if (mat[x][y]==str)
			count++;
		else count=0;
		if (count==4)
		{	i++;
			System.out.println(str+" "+i);print();
			System.exit(1);
		}
		
		if (x-1>=0 && y-1>=0)
			recurse2(x-1,y-1);
	}

	public void print()
	{
				
	for (k=0;k<6;k++)
	{
		for (j=0;j<7;j++)
			System.out.print(mat[k][j].charAt(0)+" ");
		System.out.println();
	}	
	}		

}
		