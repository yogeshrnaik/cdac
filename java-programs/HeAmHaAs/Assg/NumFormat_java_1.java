import ncst.pgdst.*;
public class NumFormat
{       public static void main(String[] args) throws IOException
        {
                SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                boolean done=false, dot_found=false, non_zero=false;
                char[] nip = new char[20];
                char[] nop = new char[20];
                int nipSize=0, nopSize=5, dot_pos=0;
                int i = 0;      char temp;

                while (!done)
                {
                        temp = sin.readChar();
                        if (temp=='\n')
                                done = true;
                        else
                        {
                                if (temp == '.')
                                {
                                        nip[i] = temp;
                                        dot_found = true;
                                        dot_pos = i;
                                }
                                if (!dot_found)
                                        nip[i] = temp;
                                else // dot found
                                {
                                        if ((temp != '0')&&(temp != '.'))
                                        {
                                                non_zero = true;
                                                nip[i] = temp;
                                        }
                                        else // temp = 0 or .
                                        {
                                                if (non_zero)
                                                        nip[i] = temp;
                                        }
                                }
                        }
                } // while
                nipSize = i;
                System.out.println("Size of input = "+nipSize);
                System.out.println("Dot pos = "+dot_pos);
                for (int j=0; j<=nipSize; j++)
                {
                        System.out.print (nip[j]);
                }
        } // main
} //class
