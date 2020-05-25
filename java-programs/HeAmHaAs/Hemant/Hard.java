import java.io.InputStreamReader;
import java.io.BufferedReader; 
import java.io.IOException;


public class Hard
{
	public static void main(String[] args)
	{
		try
		{	
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader console = new BufferedReader(reader);
			
			System.out.print("Number of gallons of gas:");
			String temp = console.readLine();
			int gas = Integer.parseInt(temp);

			System.out.print("Fuel efficiency:");
			temp = console.readLine();
			int efficiency = Integer.parseInt(temp);

			System.out.print("Distance you wanna travel:");
			temp = console.readLine();
			int desired_distance = Integer.parseInt(temp);

			int actual_distance = gas * efficiency;

			String answer = " not ";
			
			int x = actual_distance - desired_distance;
			int n = (x + Math.abs(x))/(2*x);
			System.out.println("You will" + answer.substring(0,5-4*n) + "make it");

		}
		catch(IOException e)
		{
			System.out.println("e");
			System.exit(1);
		}
		catch(ArithmeticException e)
		{
			System.out.println("You will make it");
			System.exit(1);
		}
}
}