import ncst.pgdst.*;
public class Words
{
  String [] a = new String[20];
  String [] b = new String[20];
  int no;
  Words(SimpleInput sin) throws IOException
  {
     int i = 0;
    no = sin.readInt();
    for( i=0;i<no;i++)
        {     a[i] = sin.readWord();
              b[i] = a[i];
        }
      
     int j = 0;
     for(int k=0;k<no;k++)
     {
      for (int x=0;x<no;x++)
           a[i] = b[i];
      String temp = a[0];
      a[0] = a[k];
      a[k]=temp;
      enlist(0);
     }
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
           for(k=3;k<=a[i].length();k++)
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
