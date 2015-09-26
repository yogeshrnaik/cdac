//import ncst.pgdst.*;
import java.io.*;
public class ReadInt
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader sin = new BufferedReader (new InputStreamReader(System.in));
        String input = sin.readLine();
        int a = Integer.parseInt(input);
        System.out.println ("A = " + a);
    }
}
