import ncst.pgdst.*;
public class maze
{
  public static void main(String[] args) throws IOException

    { int row,col; //no. of rows & columns
      int  starti=0, startj=0;int i,j;
      SimpleInput sin = new SimpleInput();
      SimpleOutput sout= new SimpleOutput();
      System.out.println("Input:");
      row = sin.readInt();
      col = sin.readInt();

      Array[][] a = new Array[row+2][col+2];    // Array declaration
      for(i=0;i<=row+1;i++)
       { for(j=0;j<=col+1;j++)                 
          {
            a[i][j] = new Array();
          }
       }
      for(i=1;i<=row;i++)
       { for(j=1;j<=col;j++)                 // read the Array values
          {
             sin.skipWhite();
             a[i][j].value = sin.readChar();
             
          }
       }
	for(i=1;i<=row;i++)
        for(j=1;j<=col;j++)
	{    if((a[i][j].value) =='#')
               { 
                 starti = i;
                 startj = j;
               }                    //System.out.print(" "+a[i][j].value);
        }
     int count = 0;          
     Array.settag(a,row,col);
     Array.search(a,starti,startj,count);

    }

}
class Array
 {
   SimpleInput sin = new SimpleInput();
   SimpleOutput sout= new SimpleOutput();
   
   char value,left,right,up,down;
   int count=0;
   static boolean done = false;  

   Array()
   {
        value=left=right=up=down='0';
   }
static void settag(Array[][] a,int row,int col)
  {
      for(int i=1;i<=row;i++)
       { for(int j=1;j<=col;j++)
           {
             a[i][j].left = a[i][j-1].value;
             a[i][j].right = a[i][j+1].value;
             a[i][j].up = a[i-1][j].value;
             a[i][j].down = a[i+1][j].value;
           }                                     // set tags to positions
       }
  }
 static void search(Array[][] a,int i,int j,int cnt) throws IOException
 {
     int  count= cnt;
       if (a[i][j].value=='@')
                  {
                    System.out.println(count);   
                    done= true;
                  }
      
      else {   if(((a[i][j].left=='1')||(a[i][j].left=='@'))&&(!done))
                  {
                   a[i][j-1].right='0';
                   search(a,i,j-1,count+1);
                  }
   
               if(((a[i][j].right=='1')||(a[i][j].right=='@'))&&(!done))
                  {
                  a[i][j+1].left='0';
                   search(a,i,j+1,count+1);
  
                 }
               if(((a[i][j].up=='1')||(a[i][j].up=='@'))&&(!done))
                      {
                    a[i-1][j].down='0';
                    search(a,i-1,j,count+1);
  
                  }
        
               if(((a[i][j].down=='1')||(a[i][j].down=='@'))&&(!done))
                   {
                      a[i+1][j].up='0';
                      search(a,i+1,j,count+1);
                    
                   }
            }
  }                                           // end of search




 }
                  
