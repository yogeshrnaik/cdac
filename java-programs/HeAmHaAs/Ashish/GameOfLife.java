import ncst.pgdst.*;
public class GameOfLife
{       public static void main (String[] args) throws IOException
        {       SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                int row = sin.readInt();
                int col = sin.readInt();
                Cell[][] cel = new Cell[row+2][col+2];

                // To call Constructor
                for (int i=0; i<=row+1; i++)
                {
                        for (int j=0; j<=col+1; j++)
                        {
                                cel[i][j] = new Cell();
                        }
                }

                // Read matrix
                for (int i=1; i<=row; i++)
                {
                        for (int j=1; j<=col; j++)
                        {
                                sin.skipWhite();
                                cel[i][j].status = sin.readChar();
                             //   sin.skipWhite();
                        }
                }

                int generations = sin.readInt();
                for (int i=1; i<=generations; i++)
                {
                  for (int j=1; j<=row; j++)
                  {
                    for (int k=1; j<=col; k++)
                    {
                     cel[j][k].no_of_orgs = cel[j][k].getNo_Of_Orgs(cel,j,k);
                     int temp = cel[j][k].no_of_orgs;
                     cel[j][k].setStatus(cel, temp, j,k);
                    }
                  }
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
} // class Game...
class Cell
{
        public char status;
        public int  no_of_orgs;

        // Constructor
        Cell()
        {       status = '0'; no_of_orgs = 0;
        }
        // Methods
      //  public char getStatus(Cell[][] C, int m, int n)
       // {       return C[m][n].status;
      //  }
        public int getNo_Of_Orgs(Cell[][] C, int m, int n)
        {      
                int orgs=0;
                if (m!=0)
                {
                if (C[m-1][n-1].status  == '@') orgs++;
                if (C[m-1][n].status    == '@') orgs++;
                if (C[m-1][n+1].status  == '@') orgs++;
                if (C[m][n-1].status    == '@') orgs++;
                if (C[m][n+1].status    == '@') orgs++;
                if (C[m+1][n-1].status  == '@') orgs++;
                if (C[m+1][n].status    == '@') orgs++;
                if (C[m+1][n+1].status  == '@') orgs++;
                }
                return (orgs);
        }
        public void setStatus(Cell[][] C, int orgs, int m, int n)
        {
                switch(orgs)
                {
                        case 0 :
                        case 1 :
                        case 4 :
                        case 5 :
                        case 6 :
                        case 7 :
                        case 8 : C[m][n].status = '#'; break;
                        case 2 : C[m][n].status = C[m][n].status;
                                 break;
                        case 3 : C[m][n].status = '@'; break;
                        default: break;
                }
        }
} // cell
