import ncst.pgdst.*;

public class Shapes
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Shapes shapes = new Shapes(sin);
	}

	Shapes(SimpleInput sin) throws IOException
	{
		int r,c,i,j;boolean done;boolean flag[][];
		int hcount,vcount;
		r = sin.readInt();
		c = sin.readInt();
		int a[][] = new int[r][c];
		flag = new boolean[r][c];
		for (i=0;i<r;i++)
		{	for (j=0;j<c;j++)
			{	a[i][j] = sin.readInt();
				flag[i][j] = false;
				//System.out.print(a[i][j]+" ");
			}
			//System.out.println();
		}
		for (i=0;i<r;i++)
			for (j=0;j<c;j++)
			{	if (a[i][j]==1)
				{
					System.out.print(i+","+j+" ");
					if (i==0)
					{	if (j==0)
							flag[i][j]=true;			
						else if (a[i][j-1]!=1)
							flag[i][j]=true;
					}
					else if (j==0)
					{	if (a[i-1][j]!=1 && a[i-1][j+1]!=1)
							flag[i][j]=true;}
					else if (j==c-1)
					{	if (a[i-1][j-1]!=1 && a[i-1][j]!=1 && a[i][j-1]!=1)
							flag[i][j]=true;}
					else if (a[i-1][j-1]!=1 && a[i-1][j]!=1 && a[i-1][j+1]!=1 && a[i][j-1]!=1)
							flag[i][j]=true;
				}
			}
		System.out.println();
		for (i=0;i<r;i++)
		{ 	for (j=0;j<c;j++)
				if (flag[i][j])
					System.out.print("1 ");
				else System.out.print("0 ");
			System.out.println();
		}
		int x,y;hcount=vcount=0;
		for (i=0;i<r-1;i++)
		{	j=0;
			while (j<c)
			{	done = false;//System.out.println(i+" "+j);		
				if (a[i][j]==1 && flag[i][j])
				{
					if (j==c-1)
						System.out.println("triangle");
					else if (a[i][j+1]==1 && a[i+1][j]==1)
					{
						x=i;y=j;hcount=0;vcount=0;
						while (x<r && a[x++][y]==1)
							vcount++;
						x=i;y=j;
						while (y<c && a[x][y++]==1)
							hcount++;
						//System.out.println(i+" "+j);
						//System.out.println(hcount+" "+vcount+" "+(i+vcount-1)+" "+(j+hcount-1));
					
						if (a[i+vcount-1][j+hcount-1]==1)
						{	if (hcount==vcount)
								System.out.println("square");
							else 
								System.out.println("rectangle");
							j+=hcount;done = true;//System.out.println(i+" "+j);
						}	
						else System.out.println("triangle");
					}
					else
						System.out.println("triangle");
				}
				if (!done) j++;
			}
		}
	}
}	
						
				
				