import ncst.pgdst.*;
public class ShellSort
{
  static int i,j,h,k,hcnt;
  static int increment[], a[];
  static int tmp,N,val;
  public static void main(String [] arsg) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    a = new int[20];
    increment = new int[20];
    val = 0;
    i=0;
     while(val != -1)
       {
         val = sin.readInt();
         a[i++] = val;
       }
     N = i-1;
     for( i=0,h = 1;h<N;i++)
     {
       increment[i] = h;
       h = 3*h+1; 
     }
     for(i--;i>=0;i--)
     {
       h = increment[i];
       for(hcnt = h;hcnt<2*h;hcnt++)
       {
         for(j=hcnt;j<N;j+=h)
          {
            tmp = a[j];
            k = j;
            while(k-h>=0 && tmp<a[k-h])
            {
              a[k] = a[k-h];
              k = k-h;
            }
            a[k] = tmp;
         for(int l=0;l<N;l++)
             System.out.print(a[l]+ " ");
               System.out.println();
          }
       }
     }
  }
}
