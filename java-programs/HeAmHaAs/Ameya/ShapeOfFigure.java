import ncst.pgdst.*;

public class ShapeOfFigure
{
        static int row,col;
        static int [][] mat;
        
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                row = sin.readInt();
                col = sin.readInt();
                mat = new int[row+2][col+2];
                for(int i=1; i<row+1; i++)
                        for(int j=1; j<col+1; j++)
                        mat[i][j] =sin.readInt();
                for(int i=1; i<row+1; i++)
                    for(int j=1; j<col+1; j++)
                        if (mat[i][j] == 1)
	            if(check(i,j)) System.out.println(shape(i,j));
        }

        public static boolean check(int i, int j)
        {

                if((mat[i][j-1] != 1) && (mat[i-1][j] != 1) && (mat[i-1][j+1] != 1) && (mat[i-1][j-1] != 1)) 		return true;
               else return false;
        }

        public static String shape(int i, int j)
        {

                int hcnt=0;
                int vcnt=0;
                while(mat[i][j+hcnt] ==1 ) hcnt++;
                while(mat[i+vcnt][j] ==1 ) vcnt++;

                if((hcnt == 1) || (vcnt == 1)) return "triangle";
                else if(mat[i+1][j+hcnt-1] != 1) return "triangle" ;
                        else if(hcnt==vcnt) return "square";
                                else return "rectangle";
        }
}



