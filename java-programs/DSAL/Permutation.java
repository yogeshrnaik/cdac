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
    int temp = 1000;
    System.out.println("Temp = " + temp);
    matrix = new int[temp][r];
  }

  public void generate(int k)
  {
     column[k] = 0;
     while (column[k] < n)
     {
       while (isUsed(++column[k], k));
       if (k<r-1)
          generate(k+1);
       else
       {
          System.out.print("ROW = " + ROW + " ");
          for (int j=0; j<r; j++)
          {
             matrix[ROW][j] = column[j];
             System.out.print (column[j]);
          }
          System.out.println();
          ROW++;
       } // else
    } // while
  } // generate
  public boolean isUsed (int n, int size)
  {
    for (int i=0; i<size; i++)
      if (column[i] == n)
        return true;
    return false;
  }
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
    per.generate(0);
//    per.print();
  } // main
} // public
