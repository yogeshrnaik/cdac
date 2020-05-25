import ncst.pgdst.*;
public class StrArray
{
        public static void main (String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                String[][] s = new String[2][2];
                for (int i=0; i<2; i++)
                    for (int j=0; j<2; j++)
                        s[i][j] = sin.readWord();

                for (int i=0; i<2; i++)
                {
                    for (int j=0; j<2; j++)
                        System.out.print (s[i][j] + " = "+ s[i][j].length());
                    System.out.println();
                }
        } // main
} // StrArray
