import ncst.pgdst.*;

public class Art
{
	int visible[][],moves[][],M,N,centre,i;
	public static void main(String []args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Art art = new Art(sin);
	}

	Art(SimpleInput sin) throws IOException
	{
		M = sin.readInt();
		N = sin.readInt();
		int visibleX[][] = new int[M][2];
		int moves[][] = new int[N][2];
		int j;
		for (i=0;i<M;i++)
			visibleX[i][0] = visibleX[i][1] = 0;
		
		centre = (M-1)/2;
		for (i=0;i<N;i++)
		{
			moves[i][0] = sin.readInt();
			moves[i][1] = sin.readInt();
		}
		visible = visibleX;
		
		int temp=0;
		for (i=0;i<N;i++)
		{
			updateCamera(temp);
/*			System.out.print(temp+" ");
			for (j=0;j<=centre;j++)
				System.out.print(visibleX[j][0]+" "+visible[j][1]+" ");
			System.out.println(); */
			if (temp==7)
				temp=0;
			else
	    			temp++;

			for (j=0;j<=centre;j++)
  			   if (moves[i][0]==visibleX[j][0] && moves[i][1]==visibleX[j][1])
			   {
				System.out.println(moves[i][0]+" "+moves[i][1]);
				System.exit(1);
			   }
		}
		System.out.println("DONE");
	}

	public void updateCamera(int x)
	{
		int k=0,j,count=0;
		switch(x)
		{	
			case 0 : 	for (j=centre;j>=0;j--)
				{	
					visible[k][0] = j;
					visible[k][1] = centre;
			     		k++;
				}break;

			case 1 : 	count = centre;
				for (j=centre;j>=0;j--)
				{	
					visible[k][0] = j;
					visible[k][1] = count;
					count++;k++;
				}break;

			case 2 :	for (j=centre;j<M;j++)
			     	{	
					visible[k][0] = centre;
					visible[k][1] = j;
					k++;
				}break;

			case 3 : for (j=centre;j<M;j++)
				{	
					visible[k][0] = j;
					visible[k][1] = j;
					k++;
				}break;

			case 4 : for (j=centre;j<M;j++)
				{	
					visible[k][0] = j;
					visible[k][1] = centre;
					k++;
				}break;

			case 5 : count = centre;
				for (j=centre;j<M;j++)
				{
					visible[k][0] = j;
					visible[k][1] = count;
					k++;count--;
				}break;

			case 6 : for (j=centre;j>=0;j--)
				{	;
					visible[k][0] = centre;
					visible[k][1] = j;
					k++;
				}break;

			case 7 : for (j=centre;j>=0;j--)
				{
					visible[k][0] = j;
					visible[k][1] = j;
					k++;
				}break;
		}
	}
}
				