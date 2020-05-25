import ncst.pgdst.*;

public class isect
{
	public static void main(String[] args) throws IOException
	{
		Hline Hlines[] = new Hline[10];
		Vline Vlines[] = new Vline[10];
		int H,V,count,i,j;
		count=0;
		SimpleInput sin = new SimpleInput();
		V = sin.readInt();
		H = sin.readInt();
		
		for (i=0;i<V;i++)
			Vlines[i] = new Vline();
		for (i=0;i<H;i++)
			Hlines[i] = new Hline();
		
			
		for (i=0;i<V;i++)
                        Vlines[i].getValues(sin);           

		for (i=0;i<H;i++)
                        Hlines[i].getValues(sin);
				
		for (i=0;i<H;i++)
			for (j=0;j<V;j++)
				if (Hlines[i].tellX1()<=Vlines[j].tellX())
					if (Hlines[i].tellX2()>=Vlines[j].tellX())
						if (Hlines[i].tellY()>=Vlines[j].tellY1())
							if (Hlines[i].tellY()<=Vlines[j].tellY2())
								count++;
		System.out.println(count);	
	}
}

class Hline
{
	private int Y,X1,X2;

	Hline()
	{
		Y=0;	
		X1=0;
		X2=0;
	}

        public void getValues(SimpleInput sin) throws IOException
	{
                //SimpleInput sin = new SimpleInput();
		Y = sin.readInt();
		X1 = sin.readInt();
		X2 = sin.readInt();
	}

	public int tellY()
	{
		return(Y);
	}

	public int tellX1()
	{
		return(X1);
	}
	
	public int tellX2()
	{
		return(X2);
	}
}

class Vline
{
	private int X,Y1,Y2;

	public Vline()
	{
		X=0;	
		Y1=0;
		Y2=0;
	}

        public void getValues(SimpleInput sin) throws IOException
	{
//                SimpleInput sin = new SimpleInput();
		X = sin.readInt();
		Y1 = sin.readInt();
		Y2 = sin.readInt();
	}

	public int tellX()
	{
		return(X);
	}

	public int tellY1()
	{
		return(Y1);
	}
	
	public int tellY2()
	{
		return(Y2);
	}
}
