import ncst.pgdst.*;
public class CircularArray
{
  int size;
  int[] X, Y;
  int head, tail;
  CircularArray () throws IOException
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
    for (int i=0; i<size; i++)
       System.out.print(Y[i] + " ");
    System.out.println();
    insert();
  }
  public static void main (String[] args) throws IOException
  {
     CircularArray circle = new CircularArray();
  } // main

  public void insert ()
  {
    int ls = 0, rs = 0;
    for (int i=1; i<size; i++)
    {
      if (X[i] < Y[head])
      {
        head = wrap (--head);
        Y[head] = X[i];
      }
      else if (X[i] > Y[tail])
      {
         tail = wrap(++tail);
         Y[tail] = X[i];
      }
      else
      {
        ls = 0; rs = 0;
        int c;
        c = tail;               // To calculate how many nos.
        while (X[i] < Y[c])    // are to be shifted to the right side
        { c = wrap(--c);
          rs++;
        }

        c = head;               // To calculate how many nos.
        while (X[i] > Y[c])    // are to be shifted to the left side
        { c = wrap(++c);
          ls++;
        }

        if (rs <= ls)           // Shift the nos. to right side
        {
          tail = wrap (++tail);
          for (int j=1; j<=rs; j++)
             Y[wrap(tail-j+1)] = Y[wrap(tail-j)];
          Y[wrap(tail-rs)] = X[i];
        }
        else                    // Shift the nos. to left side
        {
          head = wrap(--head);
          for (int j=1; j<=ls; j++)
             Y[wrap(head+j-1)] = Y[wrap(head+j)];
          Y[wrap(head+ls)] = X[i];
        }
      } // else
      for (int j=0; j<size; j++)
         System.out.print(Y[j] + " ");
/*
      System.out.print("head=" + head + " tail=" + tail+" ");
      System.out.print("ls=" + ls + " rs=" + rs);
*/
      System.out.println();
    } // for
  } // insert

  public int wrap (int index)
  {
    if (index < 0)
      while (index<0)
        index += size;
    return (index % size);
  }
} // CircularArray
