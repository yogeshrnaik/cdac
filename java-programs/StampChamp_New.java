import ncst.pgdst.*;
public class StampChamp_New
{
  int d, ROW;
  int[] D;
  int noc, total_stamps;
  int[] C;
  int[] minStamps;
  int[] column;
  int[][] matrix;

  StampChamp_New (SimpleInput sin) throws IOException
  {
    d = sin.readInt();
    D = new int[d];
    for (int i=d-1; i>=0; i--)
      D[i] = sin.readInt();
    noc = sin.readInt();
    C = new int[noc];
    minStamps = new int[noc];
    for (int i=0; i<noc; i++)
      C[i] = sin.readInt();

    ROW = raiseTo(d , 8);

    matrix = new int[ROW][d];
    column = new int[d];
    ROW = 0;
    generate (0);

    total_stamps = 0;

    getStamps();
    for (int i=0; i<noc; i++)
    {
      total_stamps += minStamps[i];
      if (i != noc-1)
        System.out.print (minStamps[i] + " + ");
      else
        System.out.print (minStamps[i] + " = ");
    }
    System.out.println(total_stamps);

  }
  public static void main (String[] args) throws IOException
  {
    StampChamp_New champ = new StampChamp_New (new SimpleInput());
  }
  public void generate(int k)
  {
    column[k] = -1;
    while (column[k] < 8)
    {
      column[k]++;
      if (k<d-1)
         generate(k+1);
      else
      {
//        System.out.print("ROW = " + ROW);
        for (int j=0; j<d; j++)
        {
           matrix[ROW][j] = column[j];
//           System.out.print(" " + column[j] + " ");
        }
//        System.out.println();
        ROW++;
      } // else
    } // while
  } // generate

  public int raiseTo(int n, int r)
  {
    if (n == 1) return r+1;
    int ans=1;
    for (int i=1; i<=r; i++)
       ans = ans * n;
    return (ans);
  }

  public void getStamps ()
  {
    for (int i=0; i<ROW; i++)
    {
       int sum = 0;
       int stamps = 0;
       for (int j=0; j<d; j++)
       {
         sum += D[j]*matrix[i][j];
         stamps += matrix[i][j];
       }
       for (int j=0; j<noc; j++)
       {
         if (sum == C[j])
          if (minStamps[j] == 0 || minStamps[j] > stamps)
            minStamps[j] = stamps;
       }
    }
  }
} // StampChamp_New
