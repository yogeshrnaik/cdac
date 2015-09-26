import ncst.pgdst.*;
class Clue
{
  String word;
  int bulls, cows;
  Clue (SimpleInput sin) throws IOException
  {
    word = sin.readWord();
    bulls = sin.readInt();
    cows = sin.readInt();
  }
} // Clue
public class Bulls_Cows
{
  static int noc;
  static Clue clue[];
  static char[] column;
  static boolean[] bullmark;
  static char[] bull;

  Bulls_Cows (SimpleInput sin) throws IOException
  {
    noc = sin.readInt();
    clue = new Clue[noc];
    for (int i=0; i<noc; i++)
      clue[i] = new Clue(sin);
    column = new char[4];
    bull = new char[4];
    bullmark = new boolean[4];
    generate(0);
  }
  public static void main (String[] args) throws IOException
  {
    Bulls_Cows bc = new Bulls_Cows (new SimpleInput());
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
      unmarkBulls();
      for (int j=0; j<4; j++)
        if (clue[i].word.charAt(j) == ch[j])
        {
          bullmark[j] = true;
          if (!isRepeatedInClue(ch[j], i))
            bull[b] = ch[j];
          b++;
        }
      System.out.print (" Bulls = " + b);
      if (b != clue[i].bulls) return false;

      // check cows
      for (int j=0; j<4; j++)
      {
        for (int k=0; k<4; k++)
        {
          if (j!=k && !bullmark[k])
            if (ch[j] == clue[i].word.charAt(k) && !inBull(ch[j]))
            {
              bullmark[k] = true;
              c++;
            }
        }
      }
      System.out.print (" Cows = " + c);
      if (c != clue[i].cows) return false;
    } // for
    return true;
  } // check

  public static boolean isRepeatedInClue(char ch, int index)
  {
    int count = 0;
    for (int i=0; i<4; i++)
      if (clue[index].word.charAt(i) == ch)
        count++;
    return (count >= 2);
  }
  public static boolean inBull (char ch)
  {
    for (int i=0; i<4; i++)
      if (bull[i] == ch)
        return true;
    return false;
  }
  public static void unmarkBulls()
  {
    for (int i=0; i<4; i++)
      bullmark[i] = false;
  }

} // Bulls_Cows

