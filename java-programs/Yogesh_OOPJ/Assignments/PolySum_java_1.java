import ncst.pgdst.*;
public class PolySum
{
        public static void main (String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                Terms[] sum = new Terms[40];
                // call the constructor
                for (int i=0; i<40; i++)
                        sum[i] = new Terms();
                // read & compute sum
                int i=0;
                for (int j=1; j<=2; j++)
                {
                        boolean done = false;
                        int t1=0, t2=0;
                        while(!done)
                        {
                                t1 = sin.readInt();
                                t2 = sin.readInt();
                                if ((t1==-1)&&(t2==-1))
                                        done = true;
                                if (!done)
                                {
                                   if ((t1!=0)&&(j==1))
                                   {
                                      sum[i].cof = t1;
                                      sum[i].pow = t2;
                                      i++;
                                   }
                                   else
                                   { if ((t1!=0)&&(j==2))
                                     {
                                        int k=0;
                                        boolean found = false;
                                        while ((k<i)&&(!found))
                                        {
                                          if (sum[k].pow == t2)
                                          {  sum[k].cof += t1;
                                             found = true;
                                          }
                                          else k++;
                                        }
                                        if (!found)
                                        {
                                           sum[i].cof = t1;
                                           sum[i].pow = t2;
                                           i++;
                                        }
                                     }
                                   }
                                } // if (!done)
                        } // while
                } // for 
                int sumSize = i;

                // sorting in decending order of powers
                for (int m=0; m<sumSize-1; m++)
                {
                        for (int n=m; n<sumSize; n++)
                        {
                                if (sum[m].pow<sum[n].pow)
                                {
                                  int p = sum[m].pow;
                                  sum[m].pow = sum[n].pow;
                                  sum[n].pow = p;
                                  int c = sum[m].cof;
                                  sum[m].cof = sum[n].cof;
                                  sum[n].cof = c;
                                }
                        }
                }
                // print sum
                for (int j=0; j<sumSize; j++)
                {
                   if (sum[j].cof != 0)
                   {
                     if (j<sumSize-1)
                        System.out.print(sum[j].cof + " " + sum[j].pow + " ");
                     else
                        System.out.print(sum[j].cof + " " + sum[j].pow);
                   } 
                }
                System.out.println();
    
        } // main
} // PolySum

class Terms
{
        public int cof, pow;
        Terms()
        {       cof = pow = 0;
        }
} // Terms
