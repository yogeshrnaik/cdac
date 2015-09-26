import ncst.pgdst.*;
public class GameOfLife
{       public static void main (String[] args) throws IOException
        {       SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                int row = sin.readInt();
                int col = sin.readInt();
                Cell[][] cel = new Cell[row+2][col+2];

                // call the constructor
                for (int i=0; i<=row+1; i++)
                        for (int j=0; j<=col+1; j++)
                                cel[i][j] = new Cell();
                                         
                for (int i=1; i<=row; i++)
                {
                        for (int j=1; j<=col; j++)
                        {
                                sin.skipWhite();
                                cel[i][j].status = sin.readChar();
                        }
                }

                int generations = sin.readInt();
                for (int i=1; i<=generations; i++)
                {
                  for (int j=1; j<=row; j++)
                    for (int k=1; k<=col; k++)
                      cel[j][k].no_of_orgs = cel[j][k].getNo_of_orgs(cel,j,k);

                  for (int j=1; j<=row; j++)
                    for (int k=1; k<=col; k++)
                         cel[j][k].setStatus(cel[j][k].no_of_orgs);                             
                }

                int count = 0;
                for (int i=1; i<=row; i++)
                {
                        for (int j=1; j<=col; j++)
                        {
                                if (cel[i][j].status == '@')
                                        count++;
                        }
                }

                System.out.println(count);

        } // main
} // Game..
class Cell
{
        char status;
        int  no_of_orgs;
        Cell()
        {       status = '0'; no_of_orgs = 0;
        }
        public int getNo_of_orgs (Cell[][] C, int j, int k)
        {
                int orgs = 0;
                if (C[j-1][k-1].status  == '@') orgs++;
                if (C[j-1][k].status    == '@') orgs++;
                if (C[j-1][k+1].status  == '@') orgs++;
                if (C[j][k-1].status    == '@') orgs++;
                if (C[j][k+1].status    == '@') orgs++;
                if (C[j+1][k-1].status  == '@') orgs++;
                if (C[j+1][k].status    == '@') orgs++;
                if (C[j+1][k+1].status  == '@') orgs++;
                return(orgs);
        }
        public void setStatus(int orgs)
        {
                switch(orgs)
                {
                        case 0 : case 1 : case 4 : case 5 :
                        case 6 : case 7 :
                        case 8 : status = '#'; break;
                        case 2 : break;
                        case 3 : status = '@'; break;
                        default: break;
                }
        }
} // cell
