import ncst.pgdst.*;
public class CircularArray_Old
{
  int size;
  int[] X, Y;
  int head, tail;
  CircularArray_Old () throws IOException
  {
    SimpleInput sin = new SimpleInput();
    size = sin.readInt();
    X = new int[size];
    Y = new int[size];
    for (int i=0; i<size; i++)
    {
      X[i] = sin.readInt();
      if (i == 0)
       Y[i] = X[i];
      else 
       Y[i] = -1;
    }
    head = tail = 0;
    for (int j=0; j<size; j++)
       System.out.print(Y[j] + " ");
    System.out.println();
    insert();
  }

  public void insert ()
  {
    int ls = 0, rs = 0;
    for (int i=1; i<size; i++)
    {
      if (X[i] < Y[head])
      {
        if (head == 0)  head = size - 1;
        else head--;
        Y[head] = X[i];
      }
      else if (X[i] > Y[tail])
      {
         tail++;
         Y[tail] = X[i];
      }
      else
      {
        ls = 0; rs = 0;
        int count1 = tail;

/*        while (X[i] < Y[count1%size])
        {
          rs++;
          count1--;
          if (count1 == -1) count1 = size-1;
        }
*/
        for (int j=tail; j%size <= head; j--)
        {
          if (j == -1) j = size-1;
          if (X[i] < Y[j%size]) rs++;
          else break;
        }

        int count2 = head;
        while (X[i] > Y[count2%size])
        {
          ls++;
          count2++;
        }

        if (rs <= ls)
        {
          tail++;
          for (int j=0; j<=rs; j++)
             Y[tail-j] = Y[tail-j-1];
          Y[tail-rs-1] = X[i];
        }
        else
        {
          head--;
          if (head == -1) head = size-1;
          for (int j=0; j<=ls; j++)
          {
            if (j != ls)
              Y[(head+j)%size] = Y[(head+j+1)%size];
            else
              Y[(head+j)%size] = X[i];
          }
        }
      } // else
      for (int j=0; j<size; j++)
         System.out.print(Y[j] + " ");
      System.out.print("head=" + head + " tail=" + tail+" ");
      System.out.print("ls=" + ls + " rs=" + rs);
      System.out.println();
    } // for
  } // insert

  public static void main (String[] args) throws IOException
  {
     CircularArray_Old circle = new CircularArray_Old();

  } // main


} // CircularArray
