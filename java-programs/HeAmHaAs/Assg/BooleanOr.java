import ncst.pgdst.*;
public class BooleanOr
{       public static void main(String[] args) throws IOException
        {       SimpleInput sin = new SimpleInput();
                Signal S[] = new Signal[100];
                for (int i=0; i<100; i++)
                        S[i] = new Signal();
                int h1 = sin.readInt();
                for (int i=0; i<h1; i++)
                {       S[i].low  = sin.readInt();
                        S[i].high = sin.readInt();
                }
                int Size = h1;
                int t1=0, t2=0;
                int h2 = sin.readInt();
                int i=0;
                for (int j=0; j<h2; j++)
                {
                     t1 = sin.readInt();
                     t2 = sin.readInt();
        //             if (i<h1)
          //           {
                        if (t1 < S[i].low)
                        {
                           if (t2 < S[i].high)
                           {
                              for (int k=Size; k>i; k--)
                              {   S[k].low = S[k-1].low;
                                  S[k].high = S[k-1].high;
                              }
                              S[i].low = t1; S[i].high = t2;
                              Size++; i+=2;
                           }
                           else // t2 > S[i].high
                           {
                              S[i].low = t1;
                              S[i].high = t2;
                              i++;
                           }
                        }
                        else // t1 > S[i].low
                        {
                           if (t2 > S[i].high)
                           {
                              S[i].high = t2;
                              i++;
                           }
                           else // t2 < S[i].high
                           {
                              i++;
                           }
                        }
            //         } // if (i<h1)
                } // for
                System.out.print(Size+" ");
                for (int j=0; j<Size; j++)
                {
                    if (j < Size-1)
                        System.out.print(S[j].low+" "+S[j].high+" ");
                    else
                        System.out.print(S[j].low+" "+S[j].high);
                }
                System.out.println();


        } // main
} // BooleanOr
class Signal
{       int low, high;
        Signal()
        {
                low = high = 0;
        }
}

