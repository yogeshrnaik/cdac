import ncst.pgdst.*;
public class Hash
{
    int a[],b[];
    int N,j,i,prevj,n;
    boolean inserted;
    Hash(SimpleInput sin) throws IOException
    {
      N = sin.readInt();
      a = new int[N];
      b = new int[N];
      for(int k=0;k<N;k++)
      a[k] = -1;
      boolean done = false;
      for(int k=0;!done;k++)
      {
       int m = sin.readInt();
       if(m != -1)  b[k] = m ;
       else { done = true; n = k;}
      }
      insert();
    }
    void insert()
    {
       for(int k=0;k<n;k++)
       {
         inserted = false;
         while(!inserted)
          {
            j = b[k]%N;
            wrap();
            System.out.print(j+ " ");
            if(a[j] == -1) {a[j] = b[k]; inserted = true; }
            while(!inserted && b[k]!=0)
             {
               i = b[k]%10;
               b[k] = (b[k]/10);
               prevj = j;
               j = b[k]%N;
               wrap();
               if(i%2 == 0)
                 {
                   j = prevj-j; wrap(); if(a[j] == -1) { a[j] = b[k]; inserted = true;}
                   System.out.print(j+ " ");
                 }
               else
                 {
                   j = j+prevj;wrap(); if(a[j] == -1){a[j] = b[k];inserted = true;}
                   System.out.print(j+ " ");
                 }

             }
             if(!inserted)
              { int c = j;
                if(i%2 == 0)
                {
                 for(int l=c-1;!inserted;l--)
                   {
                     j = l;
                     wrap();
                     if(a[j] == -1) { a[j] = b[k]; inserted = true;}
                     System.out.print(j+ " ");
                   }
                }
                else
                {
                 for(int l=c+1;!inserted;l++)
                  {
                    j=l;
                    wrap();
                    if(a[j] == -1){a[j] = b[k];inserted = true;}
                    System.out.print(j+ " ");
                  }
                }
              }

          }
          System.out.println();
       }
    }
     void wrap()
      {
        if(j<0 || j>N-1)
          j = Math.abs(N - Math.abs(j));
      }
  public static void main(String [] args) throws IOException
  {
   SimpleInput sin = new SimpleInput();
   Hash x = new Hash(sin); 
  }
}
