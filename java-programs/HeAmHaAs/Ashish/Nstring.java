import ncst.pgdst.*;
public class Nstring
{  int no,thno;
   int fact (int n)
   { if(n==1) return  1;
     if(n==0) return 1;
     return n* fact(n-1);
   }
Nstring(SimpleInput sin) throws IOException
   { char a[] =new char[26];
     a[0] = 'a'; a[1] = 'b'; a[2] = 'c'; a[3] = 'd'; a[4] = 'e';
     a[5] = 'f'; a[6] = 'g'; a[7] = 'h'; a[8] = 'i'; a[9] = 'j';
     a[10] = 'k'; a[11] = 'l'; a[12] = 'm'; a[13] = 'n'; a[14] = 'o';
     a[15] = 'p';a[16] = 'q'; a[17] = 'r'; a[18] = 's'; a[19] = 't';
     a[20] = 'u';a[21] = 'v'; a[22] = 'w'; a[23] = 'x'; a[24] = 'y'; a[25]= 'z';
     no = sin.readInt();
     char b[] = new char[no+1];
     thno = sin.readInt();
     thno--;
     int  num = no;
     int  i=0;
     while(i<=num && no!=0)
      {
        int n = fact(no);
        int times = n/no;
        int k = 0;
        k = (thno/times);
        b[i] = a[k];
        thno = thno%times;
        no--;
        for(int j = k;j<no;j++)
          a[j] = a[j+1];
        i++;
      }
    for(int j=0;j<num;j++)
       System.out.print(b[j]);
     }
     Nstring()
     { thno=1;no=1;}
     public static void main(String[] args) throws IOException
     {
      SimpleInput sin = new SimpleInput();
      Nstring s = new Nstring(sin);
     }
}
