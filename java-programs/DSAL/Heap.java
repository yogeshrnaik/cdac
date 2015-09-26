import ncst.pgdst.*;
public class Heap
{
  int X[];
  int size;
  Heap (SimpleInput sin) throws IOException
  {
     size = 0;
     X = new int[50];
     int no = sin.readInt();
     for (int i=0; i<no; i++)
     {
       X[i] = sin.readInt();
       size++;
     }
     no = sin.readInt();
     for (int i=0; i<no; i++)
       insert (sin.readInt());
     print (sin.readInt());
  }

  public static void main (String[] args) throws IOException
  {
    Heap heap = new Heap (new SimpleInput());
  } // main

  public void insert (int n)
  {
    int index = size;
    X[size++] = n;
    while (index != 0 && X[(index-1)/2] < X[index])
    {
      int temp = X[(index-1)/2];
      X[(index-1)/2] = X[index];
      X[index] = temp;
      index = (index-1)/2;
    }
  } // insert

  public void print (int index)
  {
     while (index < size)
     {
       System.out.print(X[index] + " ");
       index = 2*index + 1;
     }
     System.out.println();
  } // print

} // Heap
