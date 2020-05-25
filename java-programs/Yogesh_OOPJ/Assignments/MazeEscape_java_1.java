import ncst.pgdst.*;
public class MazeEscape
{       public static void main(String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                int row = sin.readInt();
                int col = sin.readInt();
                int starti=0, startj=0;
                Cell[][] C = new Cell[row+2][col+2];
                for (int i=0; i<=row+1; i++)
                   for (int j=0; j<=col+1; j++)
                        C[i][j] = new Cell();

                for (int i=1; i<=row; i++)
                {
                   for (int j=1; j<=col; j++)
                   {
                        sin.skipWhite();
                        C[i][j].value = sin.readChar();
                        if (C[i][j].value == '#')
                        {       starti = i;
                                startj = j;
                        }
                   }
                }

                for (int i=1; i<=row; i++)
                   for (int j=1; j<=col; j++)
                      C[i][j].setTag(C, i, j);

                Cell.getPath (C, starti, startj, 0);
        } // main
} // MazeEscape
class Cell
{
        char value;
        boolean left, right, up, down;
        Cell ()
        {       value = '0';
                left = right = up = down = false;
        }
        public void setTag(Cell[][] C, int i, int j)
        {
                if ((C[i-1][j].value == '1')||(C[i-1][j].value == '@'))
                        up = true;
                if ((C[i+1][j].value == '1')||(C[i+1][j].value == '@'))
                        down = true;
                if ((C[i][j-1].value == '1')||(C[i][j-1].value == '@'))
                        left = true;
                if ((C[i][j+1].value == '1')||(C[i][j+1].value == '@'))
                        right = true;
        }
        public static void getPath (Cell[][] C, int i, int j, int hops)
        {
                if (C[i][j].up)
                {
                   if (C[i-1][j].value == '@')
                   {
                        hops++;
                        System.out.println(hops);
                        System.exit(1);
                   }
                   else // value == 1
                   {
                        //hops++;
                        C[i-1][j].down = false;
                        Cell.getPath (C, i-1, j, hops+1);
                   }
                }
                if (C[i][j].left)
                {
                   if (C[i][j-1].value == '@')
                   {
                        hops++;
                        System.out.println(hops);
                        System.exit(1);
                   }
                   else // value == 1
                   {
                        //hops++;
                        C[i][j-1].right = false;
                        Cell.getPath (C, i, j-1, hops+1);
                   }
                }
                if (C[i][j].down)
                {
                   if (C[i+1][j].value == '@')
                   {
                        hops++;
                        System.out.println(hops);
                        System.exit(1);
                   }
                   else // value == 1
                   {
                        //hops++;
                        C[i+1][j].up = false;
                        Cell.getPath (C, i+1, j, hops+1);
                   }
                }
                if (C[i][j].right)
                {
                   if (C[i][j+1].value == '@')
                   {
                        hops++;
                        System.out.println(hops);
                        System.exit(1);
                   }
                   else // value == 1
                   {
                        //hops++;
                        C[i][j+1].left = false;
                        Cell.getPath (C, i, j+1, hops+1);
                   }
                }
        } // getPath
}





