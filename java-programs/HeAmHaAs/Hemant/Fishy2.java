import ncst.pgdst.*;

class Player
{
	int i,j;
	double dist;
}

public class Fishy2
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Fishy2 fishy = new Fishy2(sin);
	}
	
	Player D = new Player();
	Player G = new Player();
	int r,c;boolean done = false;
	Fishy2(SimpleInput sin) throws IOException
	{
		int t;int k;
		r = sin.readInt();
		c = sin.readInt();
		D.i = sin.readInt();
		D.j = sin.readInt();
		G.i = sin.readInt();
		G.j = sin.readInt();
		t = sin.readInt();
		G.dist = D.dist = d(G.i,G.j,D.i,D.j);
		for (k=0;k<t;k++)
		{	done = false;G.dist = D.dist;
			
			updateG();
			if (G.i==D.i && G.j==D.j)
			{
				System.out.println((k+1)+" "+G.i+" "+G.j);
				System.exit(1);
			}
			done = false;D.dist = G.dist;
			
			updateD();
			
		}
		System.out.println(D.i+" "+D.j+" "+G.i+" "+G.j);
	}
	public void updateG()
	{	int i,j;i=G.i;j=G.j;
		if (i+1<r && d(i+1,j,D.i,D.j)<=G.dist)
		{
			G.i=i+1;G.j=j;G.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}
		
		if (j+1<c && d(i,j+1,D.i,D.j)<=G.dist)
		{
			G.j=j+1;G.i=i;G.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (i-1>=0 && d(i-1,j,D.i,D.j)<=G.dist)
		{
			G.i=i-1;G.j=j;G.dist  = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (j-1>=0 && d(i,j-1,D.i,D.j)<=G.dist)
		{
			G.j=j-1;G.i=i;G.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}
		
		if (!done)
		{	
			if (G.i+1<r)
			{
				G.dist = d(G.i+1,G.j,D.i,D.j);
				updateG();
			}
			else if (G.j+1<c)
			{
				G.dist = d(G.i,G.j+1,D.i,D.j);
				updateG();
			}
			else 
			{	G.dist = d(G.i-1,G.j,D.i,D.j);
				updateG();
			}
		}
	}

	public void updateD()
	{	int i,j;i=D.i;j=D.j;
		if (i+1<r && j-1>=0 && d(i+1,j-1,G.i,G.j)>=D.dist)
		{
			D.i=i+1;D.j=j-1;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (i+1<r && d(i+1,j,G.i,G.j)>=D.dist)
		{
			D.i=i+1;D.j=j;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (i+1<r && j+1<c && d(i+1,j+1,G.i,G.j)>=D.dist)
		{
			D.i=i+1;D.j=j+1;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}
		
		if (j+1<c && d(i,j+1,G.i,G.j)>=D.dist)
		{
			D.j=j+1;D.i=i;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (i-1>=0 && j+1<c && d(i-1,j+1,G.i,G.j)>=D.dist)
		{
			D.i=i-1;D.j=j+1;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}
		
		if (i-1>=0 && d(i-1,j,G.i,G.j)>=D.dist)
		{
			D.i=i-1;D.j=j;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}

		if (i-1>=0 && j-1>=0 && d(i-1,j-1,G.i,G.j)>=D.dist)
		{
			D.i=i-1;D.j=j-1;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}
		
		if (j-1>=0 && d(i,j-1,G.i,G.j)>=D.dist)
		{
			D.j=j-1;D.i=i;D.dist = d(G.i,G.j,D.i,D.j);
			done = true;
		}	

		if (!done)
		{	
			if (D.i+1<r)
			{	
				if (D.j-1>=0)
				{	
					D.dist = d(D.i+1,D.j-1,G.i,G.j);
					updateD();
				}
				else 
				{
					D.dist = d(D.i+1,D.j,G.i,G.j);
					updateD();
				}
			}
			else if (D.j+1<r)
				{
					D.dist = d(D.i,D.j+1,G.i,G.j);
					updateD();
				}
			else
			{
				D.dist = d(D.i-1,D.j,G.i,G.j);
				updateD();
			}
		}
	}

	public double d(int x1,int y1,int x2,int y2)
	{
		double temp1 = (y2-y1)*(y2-y1);
		double temp2 = (x2-x1)*(x2-x1);
		double temp3 = temp1 + temp2;
		return(Math.sqrt(temp3));
	}
}
			