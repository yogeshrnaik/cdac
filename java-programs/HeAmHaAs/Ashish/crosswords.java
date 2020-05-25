import ncst.pgdst.*;
class matrix
{
  char ch ;
  int  rightl, downl;
  boolean left;
  boolean up;
  matrix()
  {
         char ch = '0';
         rightl = downl = 0;
         left = up = false;
  }
}
class words
{
  String w ;
  boolean inserted;
  words()
  {
          w  = null;
        inserted  = false;
  }
}

public class crosswords
{
        matrix  [][] a = new matrix[50][50];
        words word[] = new words[10];
        int row,col;
        int wordsno;
        crosswords(SimpleInput sin)  throws IOException
        {
	          row = sin.readInt();
	          col  = sin.readInt();
	          for(int i=0;i<row;i++)
	            for(int j =0;j<col ;j++)
                	a[i][j] = new matrix();
	          for(int i=0;i<row;i++)
	                for(int j=0;j<col;j++)
                	{ 	sin.skipWhite();
	                	  a[i][j].ch = sin.readChar();
                	}
	           sin.skipWhite();
	          wordsno = sin.readInt();
	          for(int i =0;i<wordsno;i++)
	          { 
		word[i] = new words();
	                word[i].w = sin.readWord();
	          }
	        for(int i=0;i<row;i++)
	          for(int j=0;j<col;j++)
	               setlength_tag(i,j);
                       
	        for(int i = 0;i<row;i++)
	        { 
		for(int j=0;j<col;j++)
	               {
             		   if(a[i][j].ch != '#')
		  {
		           for(int k=0;k<wordsno;k++)
		           {
		                    if(word[k].inserted != true)
		                    {
			                if(a[i][j].rightl == word[k].w.length())
			               {
			                        boolean rightflag = true;
			                        for(int m = 0;m<word[k].w.length();m++)
			       { if(a[i][j+m].ch!= '-' && a[i][j+m].ch !=  word[k].w.charAt(m))
		                                  rightflag  = false;
			               }
			                if(rightflag) 
		                               {
			                      for(int count = 0;count<word[k].w.length();count++)
			                           a[i][j+count].ch = word[k].w.charAt(count);
			                      word[k].inserted = true;
		                               }
		                    } // if(a[i][j].rightl == word[k].w.length())
		            } // if(word[k].inserted != true)

		            if(word[k].inserted != true)
		            {
		                    if(a[i][j].downl == word[k].w.length())
		                     {
			         boolean downflag = true;
		                        for(int m =0;m<word[k].w.length();m++)
                       		  { if((a[i+m][j].ch!='-')&&(a[i+m][j].ch != word[k].w.charAt(m)))
		                              downflag   = false;
	                         }
                	         if(downflag)  
	                         {
               		           for(int count = 0;count<word[k].w.length();count++)
		                           a[i+count][j].ch  = word[k].w.charAt(count);
	                           word[k].inserted = true;
               		          }
                      }
                    }
               } // end of while
             }

           }
          }
          for(int i=0;i<row;i++)
           { for(int j=0;j<col;j++)
              System.out.print(a[i][j].ch);
              System.out.println();
           }
        }
      void setlength_tag(int i,int j)
      {
             if(j==0) a[i][j].left = true;
             else if(a[i][j-1].ch == '#') a[i][j].left = true;
             if(i==0) a[i][j].up = true;
             else if(a[i-1][j].ch == '#') a[i][j].up = true;
             if(a[i][j].ch != '#')
            {
                  int count = 0;
                  if(a[i][j].left)
                 {
                     while((j+count)<col && a[i][j+count].ch != '#')
	                count++;
                     a[i][j].rightl = count;
                 }
                 count = 0;
                 if(a[i][j].up)
                 {
	     while( (i+count)<row && a[i+count][j].ch != '#')
                	count++;
                       a[i][j].downl =  count;
                 }
           }
      }// settag

     public static void main(String [] args) throws IOException
     {
      SimpleInput sin = new SimpleInput();
      crosswords a = new crosswords(sin);
     }
}
