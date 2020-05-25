import ncst.pgdst.*;

class Cell
{
	int i,j;int pherom;
	Cell()
	{
		i=j=pherom=0;
	}
}

class Insect
{
	int i,j;String str;int id;char c;
	boolean out;int current;
	Insect()
	{
		i=j=id=current=0;
		boolean out = false;
		str = "";
	}
}



public class DG2
{
	public static void main(String [] ars) throws IOException
	{	
		SimpleInput sin = new SimpleInput();
		DG2 dg = new DG2(sin);
	}

	DG2(SimpleInput sin) throws IOException
	{
		int N,i,j;
		N = sin.readInt();
		Insect a[] = new Insect[N];
		for (i=0;i<N;i++)
			a[i] = new Insect();
		for (i=0;i<N;i++)
		{
			a[i].id = sin.readInt();
			sin.skipWhite();
			a[i].c = sin.readChar();
			a[i].i = sin.readInt();
			a[i].j = sin.readInt();
			a[i].str = sin.readWord();
			System.out.println(a[i].c+" "+a[i].str);
		}
		int t = sin.readInt();
		Cell C = new Cell();
		C.i = sin.readInt();
		C.j = sin.readInt();
		Cell cx[][] = new Cell[10][10];
		for (i=0;i<10;i++)
			for (j=0;j<10;j++)
				cx[i][j] = new Cell();
		for (j=0;j<t;j++)
		{
			for (i=0;i<N;i++)
			{
				
				if (!a[i].out)	
				{	if (a[i].str.charAt(a[i].current)=='N')
						a[i].i--;
					else if (a[i].str.charAt(a[i].current)=='S')
						a[i].i++;
					else if (a[i].str.charAt(a[i].current)=='E')
						a[i].j++;
					else if (a[i].str.charAt(a[i].current)=='W')
						a[i].j--;
					System.out.print(a[i].id+":"+a[i].i+","+a[i].j+"("+a[i].str.charAt(a[i].current)+") ");
					if (a[i].current!=(a[i].str.length()-1))
						a[i].current++;
					else 
						a[i].current=0;
					if (a[i].c=='D')
					{	cx[a[i].i][a[i].j].pherom++;
						//if (cx[a[i].i][a[i].j].pherom==2)
						//System.out.println(" 2 ");	
					}
					else
					{	cx[a[i].i][a[i].j].pherom--;//System.out.print(cx[a[i].i][a[i].j].pherom);
					}
					if (cx[a[i].i][a[i].j].pherom<0)
						cx[a[i].i][a[i].j].pherom=0;
					//System.out.print(a[i].current+" "+a[i].str.charAt(a[i].current)+" ");
					
				}
				if (a[i].i<0 || a[i].i>9 || a[i].j<0 || a[i].j>9)
					a[i].out = true;
			}System.out.println(" [5][3]="+cx[5][3].pherom);
		}
		System.out.println(cx[C.i][C.j].pherom);
	}
}

		
			
			
			
