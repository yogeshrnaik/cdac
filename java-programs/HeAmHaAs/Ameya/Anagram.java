import ncst.pgdst.*;

public class Anagram
{

        static String Word;
        static char Chars [][] ;
        static char [] letters;
        static int row,col,len;
        static int count =0;
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                sin.skipWhite();
                
                Word =  sin.readWord();
                row = sin.readInt();
                col = sin.readInt();
                sin.skipWhite();
                Chars = new char[row][col];
                for(int i=0; i<row; i++)
                        for(int j=0; j<col; j++)
                        {
                                sin.skipWhite();
                                Chars[i][j] = sin.readChar();
                        }
                letters = new char[Word.length()];
                len = Word.length();
                for(int i=0; i<len; i++)
                        letters[i] = Word.charAt(i);
                for(int i=0; i<len-1; i++)
                        for(int j=i+1; j<len; j++)
                        if(letters[i]>letters[j])
                        {
                                char temp = letters[i];
                                letters[i] = letters[j];
                                letters[j] =temp;
                        }
                for(int i=0; i<row; i++)
                        for(int j=0; j<col-len+1; j++)
                        {
                                boolean flag = true;
                                char [] compar = new char[len];
                                int k;
                                for(k=0; k<len; k++)
                                compar[k] = Chars[i][j+k];
                                if(j>0)
                                        if(Chars[i][j-1] != '#')
                                        flag = false;
                                if(j+len-1<col-1)
                                        if(Chars[i][j+len] != '#')
                                        flag = false;
                                for(int a=0; a<len-1; a++)
                                        for(int b = a+1; b<len; b++)
                                        if(compar[a]>compar[b])
                                        {
                                                char temp = compar[a];
                                                compar[a] = compar[b];
                                                compar[b] = temp;
                                        }
                                if(flag)
                                for(int c=0; (c<len); c++)
                                        if(letters[c] != compar[c])
                                                flag = false;
                                if (flag) count++;

                        }

                for(int i=0; i<col; i++)
                        for(int j=0; j<row-len+1; j++)
                        {
                                boolean flag = true;
                                char [] compar = new char[len];
                                int k;
                                for(k=0; k<len; k++)
                                compar[k] = Chars[j+k][i];
                                if(j>0)
                                        if(Chars[j-1][i] != '#')
                                        flag = false;
                                if(j+len-1<row-1)
                                        if(Chars[j+len][i] != '#')
                                        flag = false;
                                for(int a=0; a<len-1; a++)
                                        for(int b = a+1; b<len; b++)
                                        if(compar[a]>compar[b])
                                        {
                                                char temp = compar[a];
                                                compar[a] = compar[b];
                                                compar[b] = temp;
                                        }
                                if (flag)
                                for(int c=0; (c<len); c++)
                                        if(letters[c] != compar[c])
                                                flag = false;
                                if (flag) count++;

                        }
                System.out.println(count);
        }
}        
                


                





