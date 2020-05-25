import java.io.InputStreamReader;
import java.io.BufferedReader; 
import java.io.IOException;


public class Input
{
	public static void main(String[] args)
	{
		try
		{	
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader console = new BufferedReader(reader);
			System.out.println("Enter the first time");
			String temp = console.readLine();
			int time1 = Integer.parseInt(temp);

			System.out.println("Enter the second time");
			temp = console.readLine();
			int time2 = Integer.parseInt(temp);
						
			final int x = 2360;

			time1 = x - time1;
			time2 = 


		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}
}