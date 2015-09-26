import ncst.pgdst.*;
public class Permutation
{
  int r, n;
  int ROW;
  int[] column;
  int[][] matrix;
  Permutation (SimpleInput in) throws IOException
  {
    System.out.print("Enter n : ");
    n = in.readInt();
    System.out.print("Enter r : ");
    r = in.readInt();
    ROW = 0;
    column = new int[n];
    int temp = fact(n) / fact(n-r);
    matrix = new int[temp][r];
  }

  public void generate(int k)
  {
    for (int i=k; i<n; i++)
    {
       int temp1 = column[k];
       column[k] = column[i];
       column[i] = temp1;
       if (k<r-1)
          generate(k+1);
       else
       {
          for (int j=0; j<r; j++)
             matrix[ROW][j] = column[j];
          ROW++;
       } // else
       temp1 = column[k];
       column[k] = column[i];
       column[i] = temp1;
    } // for
  } // generate
  public static int fact(int a)
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
    Permutation per = new Permutation(sin);
    for (int i=0; i<per.n; i++)
       per.column[i] = i+1;
    per.generate(0);
    per.print();
  } // main
} // public
