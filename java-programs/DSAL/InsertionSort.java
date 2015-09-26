import ncst.pgdst.*;
public class InsertionSort
{
  static int a[];
  static int N,tmp;
  static int val;
  public static void main(String [] args) throws IOException
  {
    a = new int[20];
    SimpleInput sin = new SimpleInput();
    val = 0 ;
    int i=0;
    int j=0;
    do
    {
     val = sin.readInt();
     a[i++] = val;
    }while(val != -1);
    N = i-1;
    for(i=1;i<N;i++)
    {
      tmp = a[i];
      for(j=i;j>0&&tmp<a[j-1];j--)
       a[j] = a[j-1];
       a[j] = tmp;
    for(int k=0;k<N;k++)
    System.out.print(a[k] + " " );
    System.out.println(); 
    }
  }
}
