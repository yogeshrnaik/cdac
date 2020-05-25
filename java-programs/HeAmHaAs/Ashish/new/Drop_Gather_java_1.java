import ncst.pgdst.*;
public class Drop_Gather
{
  public static void main (String[] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    int[][] G = new int[10][10];
    for (int i=0; i<10; i++)
      for (int j=0; j<10; j++)
         G[i][j] = 0;
    int no = sin.readInt();
    Insect[] I = new Insect[no];
    for (int i=0; i<no; i++)
      I[i] = new Insect(sin);
    int time = sin.readInt();
    int row = sin.readInt();
    int col = sin.readInt();

    for (int i=1; i<=time; i++)
    {
       for (int j=0; j<no; j++)
          I[j].drop(G);
       for (int j=0; j<no; j++)
          I[j].gather(G);
       for (int j=0; j<no; j++)
          I[j].update_position(i-1);
    }
    System.out.println(G[row][col]);

    for (int i=0; i<10; i++)
    {  for (int j=0; j<10; j++)
         System.out.print(G[i][j] + " ");
       System.out.println();
    }

  } // main
} // public
class Insect
{
  int id;
  char type;
  int posi, posj;
  String seq;
  boolean dead;
  Insect (SimpleInput in) throws IOException
  {
    id = in.readInt();
    in.skipWhite();
    type = in.readChar();
    posi = in.readInt();
    posj = in.readInt();
    seq  = in.readWord();
    if (posi>9 || posj>9 || posi<0 || posj<0) dead = true;
    else dead = false;
  }
  public char getMove(int next)
  {
    if (next >= seq.length())
       next = 0;
    return (seq.charAt(next));
  }
  public void drop(int[][] G)
  {
    if (type=='D' && !dead)
      G[posi][posj]++;
  }
  public void gather(int[][] G)
  {
    if (type == 'G' && !dead)
      if (G[posi][posj] > 0)
        G[posi][posj]--;
  }
  public void update_position(int next)
  {
    if (!dead)
    {
      switch (getMove(next))
      {
        case 'N' : if (posi == 0) dead = true;
                   else posi--;
                   break;
        case 'S' : if (posi == 9) dead = true;
                   else posi++;
                   break;
        case 'W' : if (posj == 0) dead = true;
                   else posj--;
                   break;
        case 'E' : if (posj == 9) dead = true;
                   else posj++;
                   break;
      }
    }
  }
} // Insect
