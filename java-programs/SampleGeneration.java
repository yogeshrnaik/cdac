import ncst.pgdst.*;
public class SampleGeneration
{
  int r, n;
  int ROW;
  int[] column;
  int[][] matrix;
  SampleGeneration (SimpleInput sin) throws IOException
  {
    System.out.print("Enter n : ");
    n = sin.readInt();
    System.out.print("Enter r : ");
    r = sin.readInt();
    ROW = 0;
    column = new int[r];
    int temp = raiseTo(n,r);
    matrix = new int[temp][r];
  }

  public void generate(int k)
  {
    column[k] = 0;
    while (column[k] < n)
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
  public int raiseTo(int n, int r)
  {
    int ans=1;
    for (int i=1; i<=r; i++)
       ans = ans * n;
    return (ans);
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
    SampleGeneration sample = new SampleGeneration(sin);
    sample.generate(0);
    sample.print();
  } // main
} // public
