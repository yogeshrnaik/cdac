import ncst.pgdst.*;
public class SelectionSort
{
  static int i,j;
  static int N;
  static int a[];
  static int val;
  static int least;
  public static void main(String [] args) throws IOException
  {
   a = new int[20];
   SimpleInput sin = new SimpleInput();
   val = 0;
   i=0;
   while(val != -1)
   {
     val = sin.readInt();
     a[i++] = val;
   }
   N = i-1;
   for( i=0;i<N;i++)
   {
    for(j=i+1,least = i;j<N;j++)
     if(a[j]<a[least])
      least = j;
     val = a[least];
     a[least] = a[i];
     a[i] = val;
     for(int k =0;k<N;k++)
      System.out.print(a[k] + " " );
      System.out.println();
   }
  }
}
