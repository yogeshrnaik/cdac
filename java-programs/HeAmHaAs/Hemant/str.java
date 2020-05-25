import ncst.pgdst.*;

public class str
{
	public static void main(String[] args) throws IOException
	{	int x=100;boolean flag=false;
		String a="hemant";
		String b="Bhatye";
		a=a.concat(b);
		System.out.println(a);
		char arr[] = new char[6];
		
		SimpleInput sin = new SimpleInput();
		a = sin.readWord();
		b = sin.readWord();
		if ((a.toLowerCase()).compareTo(b.toLowerCase())>=0)
			System.out.println(b+a);
		else
			System.out.println(a+b);
		if (a.equals(b))
			System.out.println("Abso match");
		if (a.equalsIgnoreCase(b))
			System.out.println("char match");
		if (a.startsWith("hem"))
			System.out.print("Starts with hem");
		if (a.endsWith("ant"))
			System.out.print("Ends with ant");
		System.out.println(a.hashCode());
	}
}
	