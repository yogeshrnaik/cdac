import ncst.pgdst.*;
class tp4
{
	int n[] = new int[2];
}
class tp3
{
	tp4 z[] = new tp4[2];
	tp3()
	{
		for (int i=0;i<2;i++)
			z[i] = new tp4();
	}
}
	

public class tp
{	int xx[];
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		tp tpX = new tp(sin);
	}	
	tp(SimpleInput sin) throws IOException
	{	tp3 x[] = new tp3[2];
		for (int i=0;i<2;i++)
			x[i] = new tp3();
		for (int i=0;i<2;i++)
			for (int j=0;j<2;j++)
				for (int k=0;k<2;k++)
					x[i].z[j].n[k]=sin.readInt();
		for (int i=0;i<2;i++)
			for (int j=0;j<2;j++)
				for (int k=0;k<2;k++)
					System.out.print(x[i].z[j].n[k]);
		
		
	}
	public void temp()
	{
		System.out.println(xx[0]);
	}
}	
