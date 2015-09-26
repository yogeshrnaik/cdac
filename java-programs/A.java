import ncst.pgdst.*;
public class  A
{
	public A A (A A)
	{
		A: for (int i=0; i<10; i++)
		{
			if (i != 8)
					System.out.println(i);
			else 
				break A;
		}
		return (new A());
	}
}
