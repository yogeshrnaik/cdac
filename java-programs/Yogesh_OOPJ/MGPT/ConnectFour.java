import ncst.pgdst.*;
public class ConnectFour
{
  public static void main (String[] args) throws IOException
  {
     SimpleInput sin = new SimpleInput();
     int[] M = new int[42];
     for (int i=0; i<42; i++)
        M[i] = sin.readInt();
     char[][] C = new char[6][7];
     int[] F = new int[7];
     for (int j=0; j<7; j++)
       F[j] = 0;
     boolean win = false;
     int i=0, m=0, n=0;
     while (!win && i<42)
     {
        n = M[i]-1;
        m = 5 - F[n];
        if (i%2==0)
        {  C[m][n] = 'Y';
           F[n]++;           
        }
        else
        {  C[m][n] = 'R';
           F[n]++;
        }
        i++;
        for (int row=0; row<6; row++)
        {
           for (int col=0; col<7; col++)
           {
              int r = row; int c = col;
              // up
              while (r>0 && C[r-1][c] == C[r][c])
              {    r--;
                   if (row-r >= 3)
                       ConnectFour.result(C, row, col, i);
              } // up

              // down
              r = row; c = col;
              while (r+1<5 && C[r+1][c] == C[r][c])
              {    r++;
                   if (r-row >= 3)
                     ConnectFour.result(C, row, col, i);
              } // down

              // left
              r = row; c = col;
              while (c>0 && C[r][c-1] == C[r][c])
              {    c--;
                   if (col-c >= 3)
                     ConnectFour.result(C, row, col, i);
              } // left

              // right
              r = row; c = col;
              while (c+1<6 && C[r][c+1] == C[r][c])
              {    c++;
                   if (c-col >= 3)
                      ConnectFour.result(C, row, col, i);
              } // right

              // left up
              r = row; c = col;
              while (r>0 && c>0 && C[r-1][c-1] == C[r][c])
              {    r--; c--;
                   if (row-r >= 3)
                     ConnectFour.result(C, row, col, i);
              } // left up

              // left down
              r = row; c = col;
              while (r+1<5 && c+1<6 && C[r+1][c+1] == C[r][c])
              {    r++; c++;
                   if (r-row >= 3)
                     ConnectFour.result(C, row, col, i);
              }

              // right up
              r = row; c = col;
              while (r>0 && c+1<6 && C[r-1][c+1] == C[r][c])
              {    r--; c++;
                   if (row-r >= 3)
                      ConnectFour.result(C, row, col, i);
              } // right up

              // right down
              r = row; c = col;
              while (r+1<5 && c+1<6 && C[r+1][c+1] == C[r][c])
              {    r++; c++;
                   if (r-row >= 3)
                      ConnectFour.result(C, row, col, i);
              } // right down

           } // inner for
        } // outer for

     } // while !win
     if (!win) System.out.println("DRAW");
  } // main
  public static void result (char[][] C, int r, int c, int pos)
  {
      if (C[r][c] == 'Y')
      {  System.out.println("YELLOW "+pos);
         System.exit(1);
      }
      else if (C[r][c] == 'R')
      {  System.out.println("RED "+pos);
         System.exit(1);
      }
  } // result

} // public
