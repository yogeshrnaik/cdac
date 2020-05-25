import ncst.pgdst.*;

class poly
{
        public int coeff,power;
        poly()
        {
                coeff = power = 0;
        }
}





public class pol
{
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                                        
                boolean done = false;
                int termsA,termsB,temp1,temp2;
                termsA = termsB = 0;
                poly A[] = new poly[15];
                poly B[] = new poly[15];
                poly C[] = new poly[30];

                for (int i=0;i<15;i++)
                {
                        A[i] = new poly();
                        B[i] = new poly();
                }
                
                while (!done)
                {
                        temp1 = sin.readInt();
                        temp2 = sin.readInt();
//                        System.out.print(" temp1="+temp1+" temp2="+temp2);
                        if (temp1==-1 && temp2==-1)
                                done=true;
                        else
                        {
                                A[termsA].coeff = temp1;
                                A[termsA].power = temp2;
                                termsA++;
                         
                        }
                }
                done=false;
//                System.out.print("A Done");
                while (!done)
                {
                        temp1 = sin.readInt();
                        temp2 = sin.readInt();
  //                      System.out.print(" temp1="+temp1+" temp2="+temp2);
                        if (temp1==-1 && temp2==-1)
                                done=true;
                        else
                        {
                                B[termsB].coeff = temp1;
                                B[termsB].power = temp2;
                                termsB++;
                        }
                         
                }
                done=false;
                        
    /*            for (int i=0;i<termsA;i++)
                        System.out.print(A[i].coeff+" "+A[i].power+" ");
                System.out.println();
                for (int i=0;i<termsB;i++)
                        System.out.print(B[i].coeff+" "+B[i].power+" ");*/

                for (int i=0;i<30;i++)
                        C[i] = new poly();


                for (int i=0;i<termsA;i++)
                {
                        C[i].coeff = A[i].coeff;
                        C[i].power = A[i].power;
                }
                int termsC = termsA;

                for (int i=0;i<termsB;i++)
                {
                        for (int j=0;j<termsA;j++)
                               
                                if (B[i].power==C[j].power)
                                {
                                        C[j].coeff+=B[i].coeff;

                                        done=true;
                                }
                        
                        if (!done)
                        {
                                C[termsC].coeff = B[i].coeff;
                                C[termsC].power = B[i].power;
                                termsC++;
                        }
                        done = false;
                }

                for (int i=0;i<termsC-1;i++)
                        for (int j=i+1;j<termsC;j++)
                                if (C[i].power<C[j].power)
                                {
                                        temp1 = C[i].coeff;
                                        C[i].coeff = C[j].coeff;
                                        C[j].coeff = temp1;
                                        temp1 = C[i].power;
                                        C[i].power = C[j].power;
                                        C[j].power = temp1;
                                }



                for (int i=0;i<termsC;i++)
                {
                        if (C[i].coeff!=0)
                        {
                                System.out.print(C[i].coeff+" "+C[i].power);
                                if (i<termsC-1)
                                        System.out.print(" ");
                        }
                }
        }
}        
