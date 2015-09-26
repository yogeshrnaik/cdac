import ncst.pgdst.*;
public class WordChain
{
  public static void main (String[] args) throws IOException
  {
      SimpleInput sin = new SimpleInput();
      int no = sin.readInt();
      String[] W = new String[no];
      String[] T = new String[no];
      for (int i=0; i<no; i++)
      {
         W[i] = sin.readWord();
         T[i] = W[i];
      }
      for (int i=0; i<no; i++)
      {
         for (int j=0; j<no; j++)   // get the original Array of String
              W[j] = T[j];
         String temp = W[0];
         W[0] = W[i];
         W[i] = temp;
         WordChain.formChain(W, no, 0);
      }
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
        for (int i=start+1; i<no; i++)
        {
           found = false;
           for (int l=3; l<W[i].length(); l++)
              if (W[start].endsWith(W[i].substring(0, l)))
                    found = true;
           if (W[start].endsWith(W[i])) 
	        found = true;
           if (found)
           {
               String temp = W[i];
               W[i] = W[start+1];
               W[start+1]= temp;
               WordChain.formChain(W, no, start+1);
           }
        } // for
     } //else
  } // form Chain
} // WordChain
