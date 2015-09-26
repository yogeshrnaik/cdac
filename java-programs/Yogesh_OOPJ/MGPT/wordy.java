import ncst.pgdst.*;
class chars
{
  char ch;
  boolean used;
  chars()
  { ch  = '0';
    used = false;
  }
}
public class wordy  
{
        chars [] letter = new chars[50];
        String [] word = new String[20];
        String [] b = new String[20];
        int [] count = new int[50];
        int wordsno, lettersno;
       wordy(SimpleInput sin) throws IOException
       {
        wordsno = sin.readInt();
        for(int i =0;i<wordsno;i++)
         { word[i] = sin.readWord();
           b[i] = word[i];
         }
        lettersno = sin.readInt();
        for(int i=0;i<lettersno;i++)
           {
            sin.skipWhite();
            letter[i] = new chars();
            letter[i].ch = sin.readChar();
           }
         for(int i=0;i<wordsno;i++)
           for(int j =i;j<wordsno;j++)
            {  if(b[i].length() > b[j].length())
               {
                String temp = b[i];
                b[i] = b[j];
                b[j] = temp;
               }
            }
                for(int k=0;k<wordsno;k++)
                 {
                  for (int x=0;x<wordsno;x++)
                    word[x] = b[x];
                    String temp = word[0];
                    word[0] = word[k];
                    word[k]=temp;
                    count[k] = getcount(0);
                 }
         for(int i=0;i<wordsno;i++)
           for(int j=i;j<wordsno;j++)
           {
            if(count[i]<count[j])
              {
             
               int temp = count[i];
               count[i] = count[j];
               count[j] = temp;
              }
           }
          System.out.println(count[0]);
       }
      int getcount(int k)
      {  for(int j = 0;j<lettersno;j++)
            letter[j].used = false;
        int output = 0;
         boolean done = false;
         while(k<wordsno)
         {    int count = 0;
              for(int i=0;i<word[k].length();i++)
               {
                 //System.out.print(word[k].charAt(i)+ " " );
                 int j =0;
                 done = false;
                 while(j<lettersno && !done)
                 {
                  if(letter[j].used == false)
                     {
                       if(word[k].charAt(i) == letter[j].ch)
                       
                            {   count++;    
                                done = true;
                            }
                     }
                    j++;
                 }
               }//for
               if(count == word[k].length())
                     {  System.out.print(word[k] + " ");
                          output++;
                   for(int i=0;i<word[k].length();i++)
                      {
                        int j =0;
                        done = false;
                        while(j<lettersno && !done)
                        {
                         if(letter[j].used == false)
                          {
                          if(word[k].charAt(i) == letter[j].ch)
                            {
                               letter[j].used = true;
                               System.out.print(" "+letter[j].ch);
                               done = true;
                            }
                          }
                          j++;
                        }
                      }//for
                    }           
                    k++;
               }// end of while
                  System.out.println();
         return  output; 
      }
     public static void main(String [] args)  throws IOException
     {
      SimpleInput sin = new SimpleInput();
      wordy ans = new wordy(sin);
     }
}  
