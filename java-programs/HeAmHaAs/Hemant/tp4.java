import ncst.pgdst.*;
class Box
{
	int w,h,d;
	Box(int a,int b,int c)
	{	
		w=a;h=b;d=c;
 	}
	public String toString()	
	{
		return("The BOX:"+w+" "+h+" "+d);
	}


}


public class tp4
{
	public static void main (String [] ags) throws IOException
	{	
		SimpleInput sin = new SimpleInput();
		Runtime r = Runtime.getRuntime();
		double x = -4.7,y = 9;;
		System.out.println("ceil(x):"+Math.ceil(x));
		System.out.println("floor(x):"+Math.floor(x));
		System.out.println("rint(x):"+Math.rint(x));
		System.out.println("round(x):"+Math.round(x));
		System.out.println("Max:"+Math.max(x,y));	
		System.out.println("MAX_FLOAT:"+Float.MAX_VALUE);
		/*System.out.println(r.totalMemory());
		int X[] = new int[1000];
		Box b = new Box(10,12,14);
		Box c = b;
		
		System.out.println("Totla Memory:"+r.totalMemory());
		char x = sin.readChar();
		if (Character.isDigit(x))
			System.out.println("Digit");
		if (Character.isLetter(x))
			if (Character.isLowerCase(x))
				System.out.println("letter");
			else 
				System.out.println("LETTER");
		if (Character.isSpaceChar(x))
			System.out.println("Space");
		if (Character.isWhitespace(x))
			System.out.println("White Space");
		if (Character.isDefined(x))
			System.out.println("Defined");
		System.out.println("Initial Memory:"+r.freeMemory());
		r.gc();
		System.out.println("After Garbage Collection:"+r.freeMemory());
		for (int i=0;i<1000;i++)
			X[i] = i;
		System.out.println("Final Memory:"+r.freeMemory());*/
		
	}
}