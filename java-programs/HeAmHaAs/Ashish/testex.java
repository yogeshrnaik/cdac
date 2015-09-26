import ncst.pgdst.*;
public class testex
{
      public static void main(String [] agrs) throws IOException
      {
        SimpleInput sin = new SimpleInput();
        int n;
        char m;
        try
        {
         n = sin.readInt();
         m = sin.readChar();
        }
        catch(IOException e)
        {
          System.out.println("excepted");
         // sin.readInt();
        }
      }
}
