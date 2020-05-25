import java.text.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Circ
{
	public static void main(String[] args)
	{
		try
		{
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader console = new BufferedReader(reader);
			System.out.print("Enter the radius of the circle:");
			String name = console.readLine();
			int radius = Integer.parseInt(name);
			double area = Math.PI * Math.pow(radius,2);
			double circumference = 2 * Math.PI * radius;
			NumberFormat formatter = NumberFormat.getNumberInstance();								formatter.setMaximumFractionDigits(2);	
			System.out.println("Area = " + formatter.format(area));
			System.out.println("Circumference = " + formatter.format(circumference));
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}
}