import ncst.pgdst.*;

public class WordCount{
   	public static void main(String args[]) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int noOfWords=0;
		try
                {
                        do
                        {
                                sin.readWord();
// System.out.println(sin.readWord()+"   "+noOfWords);
                                noOfWords++;
                        } while(!sin.eof());
                } //try end
		catch(IOException e){}		
		System.out.println(noOfWords);
	}
}
