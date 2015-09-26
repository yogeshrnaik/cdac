import ncst.pgdst.*;
public class WordChain
{
  public static void main (String[] args) throws IOException
  {
      SimpleInput sin = new SimpleInput();
      int no = sin.readInt();
      String[] W = new String[no];
      for (int i=0; i<no; i++)
         W[i] = sin.readWord();

      WordChain.formChain(W, no, 0);
      System.out.println("IMPOSSIBLE");
  } // main
  public static void formChain (String[] W, int no, int start)
  {
     boolean found = false;
     if (start == no-1)
     {
        for (int i=0; i<no; i++)
          System.out.println(W[i]);
        System.exit(1);
     }
     else
     {
           for (int j=start+1; j<no; j++)
           {
              for (int l=3; !found && l<W[j].length(); l++)
              {
                found = false;
                if (W[start].endsWith(W[j].substring(0, l)) || W[start].endsWith(W[j]))
                {
                    found = true;
                    String temp = W[start+1];
                    W[start+1] = W[j];
                    W[j] = temp;
                }
              }
             if (found)
                 WordChain.formChain(W, no, j+1);
           }
     } //else
  } // form Chain
} // WordChain

