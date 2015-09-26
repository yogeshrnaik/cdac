import ncst.pgdst.*;

public class WordCount
{
        public static void main(String [] args)  throws IOException
        {
                int count =0; String temp;
                SimpleInput sin = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();
                boolean flag = true;
                try {
                sin.skipWhite();
                while(!(sin.eof()))
                {      
                       sin.skipWhite();
                       temp = sin.readWord();
                       count++;
                       
                       }
                       }
                catch(Exception e){System.out.println(count);
                                        flag = false;}
                sout.flush();
                if (flag) System.out.println(count);
                }                 }
