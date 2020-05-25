import ncst.pgdst.*;
public class MagicSquare
{
  int size;
  int[][] matrix;
  MagicSquare (SimpleInput sin) throws IOException
  {
    System.out.print ("Enter the size of magic square (odd no.) = ");
    size = sin.readInt();
    matrix = new int[size][size];
    constructMagicSquare();
    print();
  }
  public static void main (String[] ars) throws IOException
  {
    MagicSquare ms = new MagicSquare (new SimpleInput());
  }

  public void constructMagicSquare()
  {
    int r=size-1, c=size/2;
    matrix[r][c] = 1;
    for (int i=2; i<=size*size; i++)
    {
      if (matrix[wrap(r+1)][wrap(c+1)] == 0)
      {
        r = wrap (++r); c = wrap (++c);
      }
      else
        while (matrix[wrap(--r)][c] != 0);
      matrix[r][c] = i;
    }
  } // constructMagicSquare

  public int wrap (int index)
  {
    while (index < 0)
     index += size;
    return (index%size);
  } // wrap

  public void print ()
  {
    int sum = 0;
    System.out.println("Magic square is as follows.");
    for (int i=0; i<size; i++)
    {
      for (int j=0; j<size; j++)
      {
        if (i==0) sum += matrix[i][j];
        String s = " "+matrix[i][j];
        while (s.length() != 5)
            s += " ";
        System.out.print(s);
      }
      System.out.println();
    }
    System.out.println ("Sum = "+ sum);
  } // print

} // MagicSquare
