import ncst.pgdst.*;
public class Assg4
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int x = sin.readInt();
		int y = sin.readInt();
                System.out.println(Acker(x,y));
		
	}
        static int Acker(int x ,int y)
	{
		if(x==0) return y+1;
                if(y==0) return Acker(x-1,1);
                return Acker(x-1, Acker(x,y-1));
	}
}
