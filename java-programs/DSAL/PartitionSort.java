import ncst.pgdst.*;
public class PartitionSort
{
  int[] X;
  int size;
  PartitionSort () throws IOException
  {
    SimpleInput sin = new SimpleInput();
    size = sin.readInt();
    X = new int[size];
    for (int i=0; i<size; i++)
       X[i] = sin.readInt();
    sort (0,size-1);
  }
  public static void main (String[] args) throws IOException
  {
    PartitionSort ass44 = new PartitionSort();
  }

  public void sort (int lb, int ub)
  {
    if (lb >= ub) return;
    int middle = (lb+ub)/2;

    int temp = X[lb];
    X[lb] = X[middle];
    X[middle] = temp;

    int pivot = lb;
    for (int i=lb+1; i<=ub; i++)
    {
      if (X[i] < X[lb])
      {
        pivot++;
        temp = X[pivot];
        X[pivot] = X[i];
        X[i] = temp;
      }
    }
    temp = X[pivot];
    X[pivot] = X[lb];
    X[lb] = temp;

    for (int i=0; i<size; i++)
      System.out.print (X[i] + " ");
    System.out.println();

    sort (lb, pivot-1);
    sort (pivot+1, ub);

  } // sort
} // PartitionSort
