import ncst.pgdst.*;


public class Afunc 
{
	private int x,y;
	
	public static void main(String[] args) throws IOException
	{
		
		Afunc Ack = new Afunc();
		Ack.getValues();
		System.out.println(Ack.getAns());
	}

	Afunc()
	{	
		x=0;y=0;
	}
	
	public void getValues() throws IOException
	{
		SimpleInput sin = new SimpleInput();	
		x = sin.readInt();
		y = sin.readInt();
	}

	public int getAns()
	{
		return(Ackermann(x,y));
	}


	public int Ackermann(int x,int y)
	{
		if (x==0)
			return (y+1);
		else if (y==0)
			return (Ackermann(x-1,1));
		else	
			return (Ackermann(x-1,Ackermann(x,y-1)));
	}
}
