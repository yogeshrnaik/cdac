import ncst.pgdst.*;
public class GoldHunt
{
        public static void main (String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                int row = sin.readInt();
                int col = sin.readInt();
                Cell[][] C = new Cell[row][col];
                for (int i=0; i<row; i++)
                   for (int j=0; j<col; j++)
                       C[i][j] = new Cell(sin);
                int nop = sin.readInt();
                Player[] P = new Player[nop];
                for (int i=0; i<nop; i++)
                     P[i] = new Player(sin);
                boolean gold = false;
                while (!gold)
                {
                  for (int p=0; p<nop; p++)
                  {
                    if (C[P[p].posi][P[p].posj].type == 'G')
                    {     System.out.println(P[p].id);
                          gold = true;
                          System.exit(1);
                    }
                    else if (C[P[p].posi][P[p].posj].type == 'T')
                           P[p].trap = true;
                    else if (!P[p].trap) // not trapped
                    {
                        int t = P[p].posi;
                        P[p].posi += C[P[p].posi][P[p].posj].di;
                        P[p].posj += C[t][P[p].posj].dj;
                        while (P[p].posi<0)
                            P[p].posi += row;
                        while (P[p].posi>=row)
                            P[p].posi -= row;
                        while (P[p].posj<0)
                            P[p].posj += col;
                        while (P[p].posj>=col)
                            P[p].posj -= col;
                    } // not trapped
                  } // for
                } //while !gold
        } // main
} // GoldHunt

class Player
{
        int id;
        int posi, posj;
        boolean trap;
        Player(SimpleInput in) throws IOException
        {
                id    = in.readInt();
                posi  = in.readInt();
                posj  = in.readInt();
                trap  = false;
        }

} // Player

class Cell
{
        char type;
        int di, dj;
        Cell (SimpleInput in) throws IOException
        {
                in.skipWhite();
                type = in.readChar();
                if (type != 'D')
                   di = dj = 0;
                else
                {  di = in.readInt();
                   dj = in.readInt();
                }
        } // Constructor
} // Cell
