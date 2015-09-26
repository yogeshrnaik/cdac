import ncst.pgdst.*;
public class ChPermute
{
  public static void main (String[] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    long n = sin.readInt();
    String input = "";
    for (int i=0; i<n; i++)
        input = input + (char)(i+97);
    long size = ChPermute.getFact(input.length());
    String op[] = new String[(int)size];
    for (int i=0; i<size; i++)
       op[i] = new String("");
    long times;

    for (int i=0; i<size; i++)
    {
       String ip = new String(input);
       long pos = i+1;
       while(op[i].length()<n)
       {
         if (pos == 1)
         {
           op[i] = op[i] + ip;
           break;
         }
         times = Nstring.getFact(ip.length()-1);
         if (pos <= times)
         {
            op[i] = op[i] + ip.charAt(0);
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
            op[i] = op[i] + ip.charAt(c);
            if (c+1 != ip.length())
               ip = ip.substring(0, c) + ip.substring(c+1);
            else
               ip = ip.substring(0, c);
         } // else
       } // while
    } // for
    for (int i=0; i<size; i++)
       System.out.print(op[i]+"\t");
  } // main

  public static long getFact (int a)
  {
    if (a==0)
      return 1;
    else
      return(a * getFact(a-1));
  } // fact
}
