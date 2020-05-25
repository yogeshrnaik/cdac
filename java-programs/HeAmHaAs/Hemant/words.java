import ncst.pgdst.*;
public class Words
{
  String [] a = new String[20];
  int no;
  Words(SimpleInput sin) throws IOException
  {
     int i = 0;
    no = sin.readInt();
    for( i=0;i<no;i++)
     a[i] = sin.readWord();
    int j = 0;
     enlist(j);
  System.out.println("Impossible");

  } // end of Words
   void enlist (int j)

   {
      boolean flag = false;
      int k = 1;
      if (j==no-1)
      {           for (int x=0;x<no;x++)
                          System.out.println(a[x]);
                  System.exit(1);
      }
      else
       {
         for(int i=j+1;i<no;i++)
          {  flag = false;
           for(k=1;k<=a[i].length();k++)
             {
                if(a[j].endsWith(a[i].substring(0,k))) flag = true;
             }
             if(a[j].endsWith(a[i])) flag = true;
          if (flag)
            {
                 String temp = a[i];
                 a[i] = a[j+1];
                 a[j+1] = temp;
                 enlist(j+1);
            }   
          }
       }
 
   }
  public static void main(String [] args)  throws IOException
  {
   SimpleInput sin = new SimpleInput();
   Words ash = new Words(sin);
  }
}
