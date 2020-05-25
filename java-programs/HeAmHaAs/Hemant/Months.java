import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.*;

public class Months
{
  JButton jButton1 = new JButton();
	public static void main(String[] args)
	{
		try
		{
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader console = new BufferedReader(reader);
			System.out.print("Enter the number:");
			String temp = console.readLine();
			int number = Integer.parseInt(temp);
			String months = "January  February March    April    May      June     July     August   SeptemberOctober  November December ";
			System.out.println("The month is " + months.substring((number-1)*9,(number-1)*9+9));
		}
	catch(IOException e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}

  public Months() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jButton1.setText("jButton1");
  }
}