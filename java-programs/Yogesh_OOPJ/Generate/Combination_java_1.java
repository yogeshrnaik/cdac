import ncst.pgdst.*;
public class CombinationGeneration
{
  int r, n;
  int ROW;
  int[] column;
  int[][] matrix;
  Combination (SimpleInput sin) throws IOException
  {
    System.out.print("Enter n : ");
    n = sin.readInt();
    System.out.print("Enter r : ");
    r = sin.readInt();
    ROW = 0;
    column = new int[r];
    int temp = fact(n) / (fact(r) * fact(n-r));
    matrix = new int[temp][r];
  }

  public void generate(int k)
  {
    if (k == 0)
      column[k] = 0;
    else
      column[k] = column[k-1];
    while (column[k] < n-r+k+1)
    {
      column[k]++;
      if (k<r-1)
         generate(k+1);
      else
      {
        for (int j=0; j<r; j++)
           matrix[ROW][j] = column[j];
        ROW++;
      } // else
    } // while
  } // generate
  public int fact(int a)
  {
     if (a == 0) return 1;
     return (a * fact(a-1));
  }
  public void print()
  {
    for (int i=0; i<ROW; i++)
    {  for (int j=0; j<r; j++)
          System.out.print(matrix[i][j] + " ");
       System.out.println();
    }
  }

  public static void main (String[] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    Combination combinations = new Combination(sin);
    combinations.generate(0);
    combinations.print();
  } // main
} // public
