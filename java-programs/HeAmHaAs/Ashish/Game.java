import ncst.pgdst.*;
public class Game
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
                for (int i=1; i<=row; i++)
                {
                        for (int j=1; j<=col; j++)
                        {

                           System.out.print(cel[i][j].status+" ");
                        }
                        System.out.println();
                }


                int generations = sin.readInt();
                for (int i=1; i<=generations; i++)
                {
                  for (int j=1; j<=row; j++)
                  {
                    for (int k=1; k<=col; k++)
                    {
                        int ab = 0;
                       
                        if (cel[j-1][k-1].status  == '@') ab++;
                        if (cel[j-1][k].status    == '@') ab++;
                        if (cel[j-1][k+1].status  == '@') ab++;
                        if (cel[j][k-1].status    == '@') ab++;
                        if (cel[j][k+1].status    == '@') ab++;
                        if (cel[j+1][k-1].status  == '@') ab++;
                        if (cel[j+1][k].status    == '@') ab++;
                        if (cel[j+1][k+1].status  == '@') ab++;
                        cel[j][k].no_of_orgs = ab;
                         System.out.print(cel[j][k].no_of_orgs+" ");
                         cel[j][k].setStatus(ab);
                   
                    }
                             System.out.println();
                    
                  }
                     System.out.println();
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
        public void setStatus( int orgs)
        {
                switch(orgs)
                {
                        case 0 :
                        case 1 :
                        case 4 :
                        case 5 :
                        case 6 :
                        case 7 :
                        case 8 : status = '#'; break;
                        case 2 : break;
                        case 3 : status = '@'; break;
                        default: break;
                }
        }
} // cell
