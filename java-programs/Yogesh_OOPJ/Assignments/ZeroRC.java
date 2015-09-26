import ncst.pgdst.*;
public class ZeroRC
{       public static void main (String[] args) throws IOException
        {
                SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                int row, col;
                row = sin.readInt();
                col = sin.readInt();
                int[][] M = new int[row][col];
                int[] temp = new int[col];
                int i=0;
                while (i<row)
                {    
                        int cnt=0;
                        for (int j=0; j<col; j++)
                        {
                                temp[j] = sin.readInt();
                                if (temp[j]==0) cnt++;
                        }
                        if (cnt != col)
                        {
                                for (int j=0; j<col; j++)
                                        M[i][j] = temp[j];
                                i++;
                        }
                        else row--;
                } // while
                i = 0;
                while (i<col)
                {
                        int cnt=0;
                        for (int j=0; j<row; j++)
                            if (M[j][i] == 0) cnt++;
                        if (cnt == row)
                        { // remove column
                             for (int m=0; m<row; m++)
                             {   for (int n=i; n<col-1; n++)
                                    M[m][n] = M[m][n+1];
                             }
                             col--;
                        }
                        else i++;
                } // while

                for (int j=0; j<row; j++)
                {   for (int k=0; k<col; k++)
                    {   if (k==col-1)
                           System.out.print(M[j][k]);
                        else 
                           System.out.print(M[j][k]+" ");
                    }
                    System.out.println();
                }
        } // main
} // ZeroRC
