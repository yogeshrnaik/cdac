import ncst.pgdst.*;
public class Nstring
{
        public static void main (String[] args) throws IOException
        {
           SimpleInput sin = new SimpleInput();
           long n = sin.readInt();
           long pos = sin.readInt();
           String ip = "";
           for (int i=0; i<n; i++)
                ip = ip + (char)(i+97);
           String op = "";
           long times;

           while(op.length()<n)
           {
               times = Nstring.getFact(ip.length()-1);
               if (pos <= times)
               {
                        op = op + ip.charAt(0);
                        if (ip.length() != 1)
                                ip = ip.substring(1);
               }
               else // pos > times
               {
                        int c=0;
                        while (pos > times)
                        {
                                pos = pos - times;
                                c++;
                        }
                        op = op + ip.charAt(c);
                        if (c+1 != ip.length())
                                ip = ip.substring(0, c) + ip.substring(c+1);
                        else
                                ip = ip.substring(0, c);
                } // else
           } // while
           System.out.println(op);
        } // main

        public static long getFact (int a)
        {
                if (a==0)
                   return 1;
                else
                   return(a * getFact(a-1));
        } // fact

} // Nstring
