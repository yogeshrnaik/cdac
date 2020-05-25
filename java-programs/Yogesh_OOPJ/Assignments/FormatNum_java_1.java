import ncst.pgdst.*;
public class FormatNum
{       public static void main (String[] args) throws IOException
        {       SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                boolean done = false, dot_found=false, non_zero=false;
                char temp;
                char[] nip = new char[20];
                char[] nop = new char[20];
                int nipSize=0, nopSize=0;
                int dot_pos=0, non_zero_pos=0, i=0;

                while (!done)
                {
                        temp = sin.readChar();
                        if (temp=='\n')
                                done=true;
                        else
                        {
                                nip[i] = temp;
                                if (temp=='.') dot_pos=i;
                                i++;
                        }
                } // while
                nipSize = i-2;

/*              System.out.println("Ip Size = " + nipSize);
                System.out.println("Dot_pos = " + dot_pos);
                for (int j=0; j<=nipSize; j++)
                        System.out.print(nip[j]); */

                for (int j=0; j<6; j++)
                {
                        if (j<5) nop[j] = '#';
                        else nop[j] = '.';
                }

                non_zero_pos = dot_pos + 1;
                while (!non_zero)
                {
                        if ((nip[non_zero_pos] == '0')&&(non_zero_pos<=nipSize))
                                non_zero_pos++;
                        else non_zero = true;
                }
                for (int j=0; j<=nipSize-non_zero_pos; j++)
                        nop[j] = nip[j+non_zero_pos];

                non_zero_pos = dot_pos - 1;
                non_zero = false;
                while (!non_zero)
                {
                        if ((nip[non_zero_pos] == '0')&&(non_zero_pos>=0))
                                non_zero_pos--;
                        else
                                non_zero = true;
                        if (non_zero_pos<0) non_zero = true;
                }
                for (int j=0; j<=non_zero_pos; j++)
                        nop[6+j] = nip[j];

                for (int j=0; j<nop.length; j++)
                        System.out.print(nop[j]);
                System.out.println();
        } // main
} // class
