import ncst.pgdst.*;
public class BooleanCheck
{       public static void main (String[] args) throws IOException
        {
                boolean[][] B = new boolean[3][3];
                for (int i=0; i<3; i++)
                {
                    for (int j=0; j<3; j++)
                    {
                        B[i][j] = true;
                        System.out.print (B[i][j]);
                    }    
                    System.out.println();
                }
        } // main
} // class
