import ncst.pgdst.*;

class Crossword
{


        static String [] Words;
	static char [] [] crossword;
        static int nos,row,col;

 	public static void main(String [] args) throws IOException

	{
		SimpleInput sin = new SimpleInput();
		row = sin.readInt(); col = sin.readInt();
	   	crossword = new char[row][col];	
		for(int i=0; i<row; i++)
			for(int j=0 ; j<col; j++)
			{
				sin.skipWhite();
			 	crossword[i][j] = sin.readChar();
			}
		nos = sin.readInt();
		Words = new String[nos];
		for(int i=0; i<nos; i++)
		{	sin.skipWhite();	Words[i] =  sin.readWord();
		}

		for(int i=0; i<nos-1; i++)
			for(int j=i+1; j<nos; j++)
			{	if(Words[i].length() > Words[j].length())
				{	String temp =  Words[i];
					Words[i] = Words[j];
                                        Words[j] = temp;
				}
			}
                
		
               for(int i=0; i<row; i++)
                { for(	int j = 0; j<col; j++)
                        { int k=0;
                        while((j<col)&&(crossword[i][j] !=   '#'))
                        { k++;j++; }
                        if (k>1)
                        {
                                for(int l=0; l<nos; l++)
                                { if (Words[l].length() == k)
                                {    boolean flag = true;
                                for(int h=0; h<k; h++)
                                { if(crossword[i][j-k+h] != '-')
                                if(crossword[i][j-k+h] != Words[l].charAt(h))
                                flag= false; }
                                if(flag) {for(int h=0; h<k; h++)
                                { crossword[i][j-k+h] = Words[l].charAt(h);
                                }  Words[l] = "#";       }
                                }
                                }
                                }
                                }
                                }
			
                for(int i=0; i<col; i++)
                { for( int j = 0; j<row; j++)
                 { int k=0;
                 while((j<row)&&(crossword[j][i] !=   '#'))
                 { k++;j++; }
                 if (k>1)
                 { for(int l=0; l<nos; l++)
                 { if (Words[l].length() == k)
                 { boolean flag = true;
                 for(int h=0; h<k; h++)
                 { if(crossword[j-k+h][i] != '-')
                 if(crossword[j-k+h][i] != Words[l].charAt(h))
                 flag= false;
                 }
                 if(flag) { //System.out.println("Success");

                 for(int h=0; h<k; h++)
                 { crossword[j-k+h][i] = Words[l].charAt(h);
                   //System.out.println(crossword[j-k+h][i] +" " + (j-k+h));
                 }  Words[l] ="#";
                 }
                 }
                 }
                 }
                 }
                 }

           for(int i=0; i<row; i++)
                {for(int j=0; j<col; j++)
                        
                        System.out.print(crossword[i][j]);
                 System.out.println();
                 } 
                 

	}
	
}
