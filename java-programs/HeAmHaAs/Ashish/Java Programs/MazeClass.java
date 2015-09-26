import ncst.pgdst.*;

class MazeObj
{
 char Char;
 boolean North,South,East,West;

 MazeObj(char Char, boolean w)
 {
  this.Char = Char;
  North = w;
  South = w;
  East  = w;
  West  = w;
  }
}

public class MazeClass
{
        boolean Flag;
        int starti,startj,m,n,count;
        MazeObj[][] Maze  ;

        MazeClass(SimpleInput sin)  throws IOException
        {
                Flag = true;
                char o ='0';
                char start = '#';
                char tem;
                count =0;
                m = sin.readInt();
                n = sin.readInt();
                
                MazeObj [][] MMaze = new MazeObj[m+2][n+2];
                for(int i = 0; i < m+2; i++)
                {
                        MMaze[i][0] = new MazeObj(o,false);
                        MMaze[i][n+1] = new MazeObj(o,false);
                        }
                for(int j =0; j < n+2; j++)
                {
                        MMaze[0][j] = new MazeObj(o,false);
                        MMaze[m+1][j] = new MazeObj(o,false);
                        }
                for(int i=1; i<m+1; i++)
                     {   for(int j=1; j < n+1; j++)
                        {
                        
                        sin.skipWhite();
                        tem = sin.readChar();
                        MMaze[i][j] = new MazeObj(tem,false);

                        }
                        
                      }  

                for( int i=1; i<m+1; i++)
                      { //System.out.println();
                        for(int j =1; j<n+1; j++)
                        {   
                              
                        MMaze[i][j].North = ((MMaze[i-1][j].Char != o) ? true : false);
                        MMaze[i][j].South = ((MMaze[i+1][j].Char != o) ? true : false);
                        MMaze[i][j].East  = ((MMaze[i][j+1].Char != o) ? true : false);
                        MMaze[i][j].West  = ((MMaze[i][j-1].Char != o) ? true : false);
                        if (MMaze[i][j].Char == start)
                                {      
                                        starti =i; startj = j;
                                        
                                }

                        } }
                        
                Maze = MMaze ;

                        
                        Travel(starti, startj, count) ;
         
                        
        }

        public void Travel(int i, int j, int temp)
        {
                int count = temp;
                if (Maze[i][j].Char == '@') {
                                Flag = false;
                                System.out.println(count);
                                }
                else {if(Maze[i][j].North && Flag)
                        {      
                                Maze[i-1][j].South = false;
                                Travel(i-1,j,count+1);
                                }
                      if(Maze[i][j].East && Flag)
                      {
                                Maze[i][j+1].West = false;
                                Travel(i,j+1,count+1);
                                }
                      if(Maze[i][j].South && Flag)
                      {
                                Maze[i+1][j].North = false;
                                Travel(i+1,j,count+1);
                                }
                      if(Maze[i][j].West && Flag)
                      {
                                Maze[i][j-1].East = false;
                                Travel(i,j-1,count+1);
                                }
                      }    

        }
        public static void main(String [] args)   throws IOException
        {
         SimpleInput sin = new SimpleInput();
         MazeClass mazeclass = new MazeClass(sin);
         }

  }
