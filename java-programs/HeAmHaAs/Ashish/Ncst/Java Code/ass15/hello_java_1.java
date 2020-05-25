import ncst.pgdst.*;
import java.lang.*;

public class ParagraphFormat{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		sin.skipWhite();
		int wordNumber = sin.readInt();
		final String HYPHEN = "-";
		String wordInput;
		WordComponent[] wordList = new WordComponent[wordNumber];
		for(int i = 0 ; i < wordNumber ; i++)
		{
			sin.skipWhite();
			wordInput = sin.readWord();
			wordList[i] = new WordComponent();
			wordList[i].separate(wordInput,HYPHEN);
		}
		sin.skipWhite();
		int lineLength = sin.readInt();	
		
		String lineWord;
		String line = "";
		boolean flag = true;



		String oldLine = "";
		String old = "";
		String middle = "";
		boolean found = false;
		do
		{
			sin.skipWhite();
			lineWord = sin.readWord();
			sin.skipWhite();
			//System.out.println("  lineWord=   "+lineWord);
			
			
			oldLine = line;
			line = line + middle + lineWord;
//			System.out.println("1 line ="+line+" lineLength ="+lineLength+" line.length() ="+line.length());
			middle = " ";
			if(line.length() > lineLength)
			{
				for(int i = 0 ; i < wordList.length ; i++)
				{
					old = wordList[i].getString(lineWord,wordList[i].word.length - 1);
					//System.out.println(" old ="+old);
					if(old.compareTo(lineWord) != 0) 
					{
						found = true;
						for(int j = wordList[i].word.length - 1 ; j >= 0 ; j--)////////////checkout -1
						{
							old = wordList[i].getString(lineWord,j);
							line = oldLine + middle + old + "-"; 
							//System.out.println(" line ="+line+" oldLine ="+oldLine+" middle ="+middle+" old="+old);
							if(line.length() <= lineLength)
							{
								//System.out.println(oldLine.charAt(0)+" oldLine = "+oldLine+" 2 line ="+line);
								System.out.print(oldLine.charAt(0));
								line = lineWord.substring(lineWord.indexOf(old)+old.length());
								//System.out.println(" line ="+line);
								break;
							}
						}
						break;
					}
				}	
				if(found == false)
				{
					//System.out.println(oldLine.charAt(0)+" found = false");
					System.out.print(oldLine.charAt(0));
					line = old;
				}
				found = false;
			///////////finally
				
			}
			else
			{
				if(line.length() == lineLength || line.length() == lineLength -1)
				{
					oldLine = line;

				//	System.out.println(oldLine.charAt(0)+" oldLine = "+oldLine+" 3 line ="+line);
					System.out.print(oldLine.charAt(0));
					line = "";
					middle = "";
				}
			}
			
			
			
			
			
			
			
		/*	
			
			
			String old = "";
			
			for(int i = 0 ; i < wordList.length ; i++)
			{
				old = wordList[i].getHyphenString(lineWord);
				if(old.compareTo(lineWord) != 0) break;
				
			}
			if(flag == true)
			{
				line = old;
				flag = false;
			}
			else line = line + " " + old;

		*/	


			
		}while(!sin.eof());
		System.out.println(line.charAt(0));
//		System.out.println(line);

/*
		int start = 0;
		int end = 0;
		
		int oldLength = 0;
		String addLine = "";	
		String copyAddLine = "";
		String checkLine = "";
		
		int checkLength = 0;
		String outputLine = "";
		String lastChar = "";
		String c;
		
		int hyphenPos = 0;
		int spacePos = 0;
		boolean done = false;
*/		
		/*
		while(!(spacePos == -1 && hyphenPos == -1))
		{
			hyphenPos = line.indexOf("-",hyphenPos);
			spacePos = line.indexOf(" ",spacePos);
			
			if(spacePos == -1 && hyphenPos == -1)
			{
				start = end;
				addLine = line.substring(start);
				done = true;
			}
			
			if(spacePos < hyphenPos && spacePos >= 0)
			{
				start = end; 
				end = spacePos;
				addLine = line.substring(start , end);
				
			 	spacePos = spacePos + 1;	
				end = spacePos;
			}
			if(hyphenPos > spacePos && hyphenPos >= 0)
			{
				start = end; 
				end = hyphenPos;
				addLine = line.substring(start , end);
				
				hyphenPos = hyphenPos + 1;
				end = hyphenPos;
			}
			
			outputLine = checkLine;
			checkLine = checkLine + addLine + lastChar;
			checkLength = checkLine.length();
			
			if(lineLength < checkLength || done = true)
			{
					System.out.print(outputLine.charAt(0));
					checkLine = copyAddLine.trim();
			}
			
		}
		*/
/*
		for(int i = 0 ; i < line.length() ; i++)
		{
			c = String.valueOf(line.charAt(i));
				
			outputLine = checkLine;
			oldLength = checkLine.length();
			
			if(c.compareTo("-") == 0 )
			{
				middle = "";
				if(i != line.length()-1)c = "";
				if((checkLine + addLine + "-").length() >= lineLength)checkLine = checkLine + addLine + "-";
				else checkLine = checkLine + addLine ;
				checkLength = checkLine.length();
				copyAddLine = addLine;
				addLine = "";
				gone = true;
			}
			else if(c.compareTo(" ") == 0 )
			{
				middle = " ";
				if(i != line.length()-1)c = "";
				checkLine = checkLine + addLine;
				checkLength = checkLine.length();
				//checkLine = checkLine + " ";
				copyAddLine = addLine;
				addLine = "";
				gone = true;
			}
			else middle = "";
			
			addLine = addLine + middle + String.valueOf(c);
			if(lineLength < checkLength)
			{
				if(gone == true)
				{
					System.out.print(outputLine.charAt(0));
					//System.out.println(outputLine);
					outputLine = copyAddLine;
					checkLine = copyAddLine.trim();
					gone = false;
				}
			}
//System.out.println("c ="+c+" addLine="+addLine+" checkLine="+checkLine+" middle="+middle+" outputLine="+outputLine);
		}
		System.out.println(outputLine.charAt(0));
		//if()System.out.println(addLine.charAt(0));	
*/
	}
	
}
class WordComponent{
	public String getString(String wordInLine,int number)
	{
		int index = compareWord(wordInLine);
		String string = "";
		if(number <= 0) return string;
		if(index != -1) 
		{
			String initial = wordInLine.substring(0,wordInLine.indexOf(getWord(word.length)));
			string = initial;
			string = string + getWord(number);
			//int start = (initial + getWord(word.length)).length();
			//string = string + wordInLine.substring(start);
		}
		else string = wordInLine;
		return string;
	}
	public String getHyphenString(String wordInLine)
	{
		int index = compareWord(wordInLine);
		String string = "";
		if(index != -1) 
		{
			String initial = wordInLine.substring(0,wordInLine.indexOf(getWord(word.length)));
			string = initial;
			string = string + getHyphenWord(word.length);
			int start = (initial + getWord(word.length)).length();
			string = string + wordInLine.substring(start);
		}
		else string = wordInLine;
		return string;
	}
	public int compareWord(String wordInLine)
	{
		int index = wordInLine.indexOf(getWord(word.length),0);
		return index;
	}
	public String getHyphenWord(int number)
	{
		if(number == 0) return "";
		String string = "";
		for(int i = 0 ; i < number ; i++)
		{
			if(i == 0)string = word[i];
			else string = string + "-" + word[i];
		}
		return string;
	}
	public String getWord(int number)
	{
		if(number == 0) return "";
		String string = "";
		for(int i = 0 ; i < number ; i++)
		{
			string = string + word[i];
		}
		return string;
	}
	public void separate(String string , String sep)
	{
		int start = 0;
		int end = 0;
		separator = sep;
		word = new String[getNumberOfSeparator(string) + 1];		
		for(int i = 0 ; i < word.length ; i++)
		{
			end = string.indexOf(separator,start);
			if(end == -1)end = string.length();
			word[i] = string.substring(start,end);
			start = end + separator.length();
		}
	}
	
	public int getNumberOfSeparator(String string)
	{
		int number = 0;
		int index = 0;
		do
		{
			index = string.indexOf(separator,index + 1);
			if(index != -1)number++;
		}while(index != -1);
		return number;
	}
	public void printWord()
	{
		for( int i = 0 ; i < word.length ; i++)
		System.out.println("word[" + i + "]=" + word[i] + " " + separator);
	}
	String[] word;
	String separator;

}

/*			
			if(lineLength < (line + " " + lineWord).length())
			{
				for(int i = 0 ; i < wordList.length ; i++)
				{
					int index = compareWord(wordList[i].getWord(wordList[i].word.length),lineWord);
					if(index != -1)	
					{
						String initial = lineWord.substring(0,index);
						String wordGot;
						for(int j = 1 ; j <= wordList[i].word.length ; j++)
						{
							int requiredLength = lineLength - 1 - line.length();
							
							wordGot = wordList[i].getWord(wordList[i].word.length - j);
							wordGot = initial + wordGot;
							if(requiredLength == -1)requiredLength++;
							//System.out.println("wordGot = " + wordGot + " rl = "+ requiredLength);
							if(requiredLength >= wordGot.length())
							{
								line = line + " " + wordGot;
								//System.out.println("1 line = " + line + " " +line.charAt(0));
								System.out.print(line.charAt(0));
								line = lineWord.substring(wordGot.length());
								//System.out.println("2 line = " + line + " " +line.charAt(0));
								break;
							}
						}
					}
				}
			}
			else if(flag == true)
			{
				line = lineWord;
				flag = false;	
			}
			else line = line + " " + lineWord;
			
			//System.out.println("  end line = "+line);

*/
