import ncst.pgdst.*;
public class Remove
{

public static void main(String [] args) throws IOException
        {
int m,n;
SimpleInput sin = new SimpleInput();

        m=sin.readInt();
        n=sin.readInt();
        int [][] mat = new int[m][n];
        for(int i=0; i<m; i++)
                for(int j=0; j<n; j++)
                mat[i][j] = sin.readInt();
        int [] temprow = new int[n];
        int [] tempcol = new int[m];
        for(int i=0; i<m; i++)
                {
                int count = 0;
                for(int j=0; j<n; j++)
                        if(mat[i][j] == 0) count++;
                if (count == n){
                                temprow = mat[i];
                                for(int k=i; k<m-1; k++)
                                {mat[k] = mat[k+1];
                                 mat[k+1] = temprow;
                                 }
                                 m--;i--;
                                 }
                }
        for(int j=0; j<n; j++)
                {
                int count = 0;
                for(int i=0; i<m; i++)
                        if(mat[i][j] == 0) count++;
                if (count == m){
                                for(int k=0; k<m; k++)
                                for(int h=j; h<n-1; h++)
                                {
                                int temp = mat[k][h];
                                mat[k][h] = mat[k][h+1];
                                mat[k][h+1] = temp;
                                }
                                n--; j--;
                                }
                }
        for(int i = 0; i<m; i++)
        { for(int j=0; j<n; j++)
                System.out.print(mat[i][j]+" ");
                System.out.println();
                }
                }
                        }


                
                
                       
