import ncst.pgdst.*;
class Cell
{ char value;
  int neighbours;
   Cell(char value, int neighbours)
   { this.value = '0';
     this.neighbours = 0;
   }
}
public class Life
 {
     int row,col,N;
     Cell[][] a ; 
     Life(SimpleInput sin) throws IOException
    {

     row = sin.readInt();
     col = sin.readInt();
     Cell[][] b = new Cell[row+2][col+2];
    
     for(int i=0;i<=row+1;i++)
     { for(int j=0;j<=col+1;j++)
       {  b[i][j] = new Cell('0',0); 
       }
     }

     for(int i=1;i<=row;i++)
     { for(int j=1;j<=col;j++)
       { sin.skipWhite();
         b[i][j].value = sin.readChar();
       }
     }

      N = sin.readInt();
      a = b;
            // getNeighbours();
             Survivors(N);
    }
  public void getNeighbours()
   {
     for(int i=1;i<=row;i++)
      {  for(int j=1;j<=col;j++)
         { int count = 0;
           if(a[i][j+1].value=='@') count++;
           if(a[i][j-1].value=='@') count++;
           if(a[i+1][j+1].value=='@') count++;
           if(a[i+1][j-1].value=='@') count++;
           if(a[i-1][j+1].value=='@') count++;
           if(a[i-1][j-1].value=='@') count++;
           if(a[i+1][j].value=='@') count++;
           if(a[i-1][j].value=='@') count++;
           a[i][j].neighbours = count;
//           System.out.println(a[i][j].neighbours);
         }
      }
    }

  public void Survivors(int N)
    {    
       for(int k=1;k<=N;k++)
         {   getNeighbours();
          for(int i=1;i<=row;i++)
            {  for(int j=1;j<=col;j++)
              {
               if(a[i][j].value=='@')
                  { if((a[i][j].neighbours<2))
              
                      a[i][j].value ='#';

                    else if(a[i][j].neighbours>=4)
                     a[i][j].value='#';
                   }
               else if(a[i][j].value=='#')
                 {  if(a[i][j].neighbours==3)
                    { a[i][j].value='@';
                    }
                 }
               
               }
             }
          }
          int cnt=0;
          for(int i=1;i<=row;i++)
            {  for(int j=1;j<=col;j++)
              { if(a[i][j].value=='@') cnt++;
              }
            }
           System.out.println(cnt);
         
    }
    public static void main(String[] args) throws IOException
    {   SimpleInput sin = new SimpleInput();
        Life game ;
        game = new Life(sin);
    }
}
