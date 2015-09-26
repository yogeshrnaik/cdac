import ncst.pgdst.*;


public class Ackermann{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int x = sin.readInt();
		int y = sin.readInt();
		System.out.println(acker(x,y));
		
	}
	static int acker(int x ,int y)
	{
		if(x==0) return y+1;
		if(y==0) return acker(x-1,1);
		return acker(x-1,acker(x,y-1));
	}
}
