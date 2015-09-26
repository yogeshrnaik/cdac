import ncst.pgdst.*;
import java.util.Date;
class Character
{
	char ch;
	boolean used;
	Character(char c)
	{
		ch = c;
		used = false;
	}
}
class Word
{
	String str;
	boolean found;
	Word (String s)
	{
		str = s;
		found = false;
	}
}
public class WordyEnough
{
	int no_of_words;
	Word word[];
	Character ip[];
	int no_of_chars;
	int possibleWords;
	
	String[] formed;
	int size;
	
	String[] wordsFormed;

	WordyEnough(SimpleInput sin) throws IOException
	{
		no_of_words = sin.readInt();
		word = new Word[no_of_words];
		formed = new String[no_of_words];
		size = 0;

		for (int i=0; i<no_of_words; i++)
		{
	
			word[i] = new Word(sin.readWord());
		}
		no_of_chars = sin.readInt();
		ip = new Character[no_of_chars];
		for (int i=0; i<no_of_chars; i++)
		{
			sin.skipWhite();
			ip[i] = new Character(sin.readChar());
		}
		possibleWords = 0;

		long time1 = (new Date()).getTime();
		solve(0);
		long time2 = (new Date()).getTime();
		System.out.println("Total time required to arrive at solution = " + (time2 - time1) + " milliseconds.");

		System.out.println("Max no. of words that can be formed = "+possibleWords);
		System.out.print("The words are : ");
		for (int i=0; i<wordsFormed.length; i++)
		{
			System.out.print(wordsFormed[i] + " ");
		}
		System.out.println();
	}

	public void solve(int w)
	{
		// check whether atleast one word can be formed or not
		if (!isDone())
		{
			// go for recursion
			for (int i=0; i<no_of_words; i++)
			{
				if (!word[i].found)
				{
					if (isPossibleToForm(word[i].str))
					{
						word[i].found = true;
						int markedChars[] = getMarkedChars(word[i].str);
						formed[size++] = word[i].str;
						solve(w+1);
						word[i].found = false;
						unMarkChars(markedChars);
						if (size != 0)
						{
							size--;
						}
					}
				}
			}
		}
		else if (w > possibleWords)
		{
			possibleWords = w;
			wordsFormed = new String[possibleWords];
			for (int i=0; i<possibleWords; i++)
			{
				wordsFormed[i] = formed[i];
			}
		}
	}
	public void unMarkChars(int[] markedChars)
	{
		// unmark used characters
		for (int i=0; i<markedChars.length; i++)
		{
			ip[markedChars[i]].used = false;
		}
	}

	public int[] getMarkedChars(String s)
	{
		int markedChars[] = new int[s.length()];
		int count = 0;
		int j;
		for (int i=0; i<s.length(); i++)
		{
			for (j=0; j<no_of_chars; j++)
			{
				if (!ip[j].used && s.charAt(i) == ip[j].ch)
				{
					ip[j].used = true;
					markedChars[count] = j;
					count++;
					break;
				}
			}
		}
		return markedChars;
	}

	public boolean isDone()
	{
		for (int i=0; i<no_of_words; i++)
		{
			if (!word[i].found)
			{
				if (isPossibleToForm(word[i].str))
				{
					return false;
				}
			}
		}
		return true;
	}

	public boolean isPossibleToForm (String str)
	{
		int count = 0;
		int j;
		int temp[] = new int[str.length()];

		for (int i=0; i<str.length(); i++)
		{
			for (j=0; j<no_of_chars; j++)
			{
				if (!ip[j].used && str.charAt(i) == ip[j].ch)
				{
					ip[j].used = true;
					temp[count] = j;
					count++;
					break;
				}
			}
			if (j == no_of_chars)
			{
				// unmark used characters
				for (int k=0; k<count; k++)
				{
					ip[temp[k]].used = false;
				}
				return false;
			}
		}
		// unmark used characters
		for (int k=0; k<count; k++)
		{
			ip[temp[k]].used = false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException
	{
		WordyEnough we = new WordyEnough(new SimpleInput());
	}
}
