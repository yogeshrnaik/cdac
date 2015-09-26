public class Stamp
{ public static void main( String[] args) throws IOException
  {
      SimpleInput sin = new SimpleInput();
      int d = sin.readInt();
      int D[] = new int[d];
      for (int i=0; i<d; i++)
      D[i] = sin.readInt();

      int customers = sin.readInt();
      int[] C = new int[customers];
      for (int i=0; i<customers; i++)
      C[i] = sin.readInt();

      Combinations[] combi = new Combinations[1000];
      for (int i=0; i<1000; i++)
         combi[i] = new Combinations();
      int n = 0;
/*      for (int i=0; i<d; i++)             // sum of all denominations
        combi[n].value += D[i];
      combi[n].nos = d; */

      int count=0;
      int sum=0;
      for (int i=0; i<d; i++)
      {
         for (int j=0; j<=5; j++)
         {
            sum = D[i]*j;
            count = 0;
            while (count <=5)
            {
               for (int m=0; m<d; m++)
               {
                  if (m != i)
                    sum += D[m]*count;
               }
               combi[n].value = sum;
               combi[n].nos = j + (d-1)*count;
               count++;
               n++;
            } // while
         } // inner for
      } // outer for

      
  } // main
} // StampChamp
class Combinations
{
   int value; int nos;
   Combinations()
   {  value = 0;
      nos = 0;
   }
}

