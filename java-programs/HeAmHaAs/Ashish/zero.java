import ncst.pgdst.*;
public class zero
{
    int m,n;
    int [][] a = new int[50][50];
    zero(SimpleInput sin) throws IOException
     {

       m = sin.readInt();
       n = sin.readInt();
        for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
        { a[i][j] = sin.readInt();
        }
                int i,j,cnt1,cnt2;i=j=cnt1=cnt2=0;

      for(i=0;i<m;i++)
      {   boolean done = false;
      for(j=0;j<n;j++)
        {     
         if(a[i][j] !=0) {System.out.print(a[i][j]+" ");done = true;}
         else
            {
              cnt1=0;
             for(int k=0;k<n;k++)
              if(a[i][k]!=0) cnt1++;       
              cnt2 = 0;
                for(int k=0;k<m;k++)
                  if(a[k][j]!=0) cnt2++;
              if((cnt1>0)&&(cnt2>0))
               { System.out.print(a[i][j] + " ");done= true;}
             }
         }
            if(done)  System.out.println();
      }

    }
     public static void main(String[] args) throws IOException
      { SimpleInput sin = new SimpleInput();
        zero z = new zero(sin);
      }
  }
