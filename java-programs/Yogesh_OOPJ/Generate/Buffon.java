import ncst.pgdst.*;
import java.util.Random;
public class Buffon
{
   public static void main(String[] args) throws IOException
   {
        SimpleInput  sin  = new SimpleInput();
        SimpleOutput sout = new SimpleOutput();
        Random generator = new Random();
        int hits = 0;
        System.out.print("Enter no. of tries : ");
        long tries = sin.readInt();

        for (int i=1; i<=tries; i++)
        {
           double ylow  = 2   * generator.nextDouble();
           //System.out.println("Y LOW = " + ylow);
           double angle = 180 * generator.nextDouble();
           //System.out.println("Angle = " + angle);
           double yhigh = ylow + Math.sin(Math.toRadians(angle));
           if (yhigh>=2)
              hits++;
        }
        System.out.println("Tries / Hits = PI = "+(tries*1.0)/hits);
   }
}
