import ncst.pgdst.*;
public class HashTable
{
  int size;
  int[] hash;
  HashTable (SimpleInput sin) throws IOException
  {
    size = sin.readInt();
    int nos = size;
    hash = new int[size];
    int[] temp = new int[size];
    for (int i=0; i<size; i++)
       hash[i] = -1;
    for (int i=0; i<size; i++)
    {  int t = sin.readInt();
       if (t != -1)
         temp[i] = t;
       else
       {  nos = i;
          break;
       }
    }
    for (int i=0; i<nos; i++)
      insert(temp[i]);
    if (nos == size) sin.readInt(); // To read -1
  }
  public static void main (String[] args) throws IOException
  {
    HashTable ht = new HashTable (new SimpleInput());
  } // main

  public void insert (int no)
  {
    int i = no;
    int rd = -1;
    int index = no % size;

    while (!isEmpty(index))
    {
      System.out.print(index + " ");
      if (i != 0)
      {
        rd = i % 10;
        i =  i / 10;
        int moves = i % size;
        if (rd%2 != 0)
          index = wrap(index+moves);
        else
          index = wrap(index-moves);
      }
      else
      {
        if (rd%2 == 0) index = wrap(--index);
        else index = wrap(++index);
      }
    } // while
    hash[index] = no;
    System.out.println(index);

  } // insert

  public boolean isEmpty (int index)
  {
    return (hash[index] == -1);
  } // isEmpty

  public int wrap (int index)
  {
    while (index<0)
     index += size;
    return (index % size);
  }

} // Hash
