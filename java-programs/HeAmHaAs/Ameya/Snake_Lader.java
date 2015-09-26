import ncst.pgdst.*;

public class Snake_Lader
{
        int start,finish;
        Snake_Lader(int start, int finish)
        {
                this.start = start;
                this.finish = finish;
        }

        public static void main(String [] args) throws IOException
        {
                int nosl,str,fin,mov,posA,posB;
                posA=0; posB=0;
                SimpleInput sin = new SimpleInput();

                nosl = sin.readInt();
                Snake_Lader [] snld = new Snake_Lader[nosl];

                for(int i =0; i < nosl; i++)
                {
                        str = sin.readInt();
                        fin = sin.readInt();
                        snld[i] = new Snake_Lader(str,fin);
                }

                mov = sin.readInt();
                mov = mov/2;
                int [] A = new int[mov];
                int [] B = new int[mov];

                for(int i = 0; i < mov; i++)
                {
                        A[i] = sin.readInt();
                        B[i] = sin.readInt();
                }

                int tempa,tempb;
                boolean posa,posb ;
                for(int i =0; i < mov; i++)
                {       posa = true;
                        posA += A[i];
                        tempa = posA;
                        while(posa)
                        {
                                for(int j=0; j < nosl; j++)
                                if  (posA==snld[j].start) posA = snld[j].finish;
                                if (posA == tempa) posa = false;
                                else tempa = posA;
                                }
                        if (posA>=99) break;


                        posb = true;
                        posB += B[i];
                        tempb = posB;
                        while(posb)
                        {
                                for(int j=0; j < nosl; j++)
                                if  (posB==snld[j].start) posB = snld[j].finish;
                                if (posB == tempb) posb = false;
                                else tempb = posB;
                                }
                       if (posB>=99) break;
                       //if ((posA == 99) || (posB == 99)) i = mov;
                }    //for loop ends

                if (posA > posB) System.out.println("A "+posA);
                else System.out.println("B "+posB);
                }  //main ends
                }            //class ends


                        


