import ncst.pgdst.*;
class Character
{
  char abcd;
  boolean bull;
  Character (SimpleInput sin) throws IOException
  {
    sin.skipWhite();
    abcd = sin.readChar();
    bull = false;
  }
}
class Clue
{
  Character chars[];
  int bulls, cows;
  Clue (SimpleInput sin) throws IOException
  {
    chars = new Character[4];
    for (int i=0; i<4; i++)
      chars[i] = new Character(sin);
    bulls = sin.readInt();
    cows = sin.readInt();
  }
} // Clue
public class Bulls_Cows_New
{
  static int noc;
  static Clue clue[];
  static char[] column;

  Bulls_Cows_New (SimpleInput sin) throws IOException
  {
    noc = sin.readInt();
    clue = new Clue[noc];
    for (int i=0; i<noc; i++)
      clue[i] = new Clue(sin);
    column = new char[4];
    generate(0);
  }
  public static void main (String[] args) throws IOException
  {
    Bulls_Cows_New bc = new Bulls_Cows_New (new SimpleInput());
  }
  public void generate (int k)  // A65, B66, C67, D68
  {
    column[k] = (char)64;
    while (column[k] < 68)
    {
      column[k]++;
      if (k < 3)
        generate (k+1);
      else
      {
        for (int i=0; i<4; i++)
          System.out.print (column[i]);
        boolean flag = check (column);
        System.out.println();
        if (flag)
        {
           for (int i=0; i<4; i++)
             System.out.print(column[i]);
           System.out.println();
           System.exit(1);
        }
      }
    }
  } // generate

  public static boolean check (char[] ch)
  {
    int b=0, c=0;
    for (int i=0; i<noc; i++)
    {
      b = c = 0;
      unmarkBulls(clue[i]);
      for (int j=0; j<4; j++)
        if (clue[i].chars[j].abcd == ch[j])
        {
          clue[i].chars[j].bull = true;
          b++;
        }
      System.out.print (" Bulls = " + b);
      if (b != clue[i].bulls) return false;

      // check cows
      for (int j=0; j<4; j++)
      {
        for (int k=0; k<4; k++)
        {
          if (j!=k && !clue[i].chars[k].bull)
            if (ch[j] == clue[i].chars[k].abcd)
            {
              clue[i].chars[k].bull = true;
              c++;
            }
        }
      }
      System.out.print (" Cows = " + c);
      if (c != clue[i].cows) return false;
    } // for
    return true;
  } // check

  public static void unmarkBulls (Clue curr)
  {
    for (int i=0; i<4; i++)
      curr.chars[i].bull = false;
  }

} // Bulls_Cows

