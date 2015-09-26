import ncst.pgdst.*;
public class Array
{
        public static void main (String[] args) throws IOException
        {
                SimpleInput  sin =  new SimpleInput();
                SimpleOutput sout = new SimpleOutput();

                int[] data = new int[50];
                int i;
                for (i=0; i<=5; i++)
                       data[i] = sin.readInt();
                int dataSize = i;
        }
}
         
