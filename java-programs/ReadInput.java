//import ncst.pgdst.*;
import java.io.*;
public class ReadInput
{
    public static void main (String[] args) throws IOException
    {
        char ch;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println ("Enter characters, 'q' to quit.");
        // read character
        do
        {
            ch = (char) br.read();
            System.out.println (ch);
        } while (ch != 'q');
    }
}
