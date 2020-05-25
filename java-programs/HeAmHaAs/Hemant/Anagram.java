import ncst.pgdst.*;

public class Anagram
{
	public static void main(String [] args) throws IOException
	{
		String str="";int count;
		
		int r,c,i,j;int temp,k;
		SimpleInput sin = new SimpleInput();
		str = sin.readWord();
		boolean used[] = new boolean[str.length()];
		r = sin.readInt();
		c = sin.readInt();
		char m[][] = new char[r][c];
		for (i=0;i<r;i++)
			for (j=0;j<c;j++)
			{
				sin.skipWhite();
				m[i][j] = sin.readChar();
			}
		for (i=0;i<str.length();i++)
			used[i] = false;
		count=0;temp=0;
		/*for (i=0;i<r;i++)
			for (j=0;j<c;j++)
				System.out.print(str.indexOf(m[i][j])+" ");*/
		for (i=0;i<r;i++)
		{
			j=0;temp=0;
			for (k=0;k<str.length();k++)
				used[k]=false;
			while (j<c)
			{	System.out.print(m[i][j]+" ");
				if (str.indexOf(m[i][j])==-1)
				{
					temp=0;//System.out.print("wrong ");
					for (k=0;k<str.length();k++)
						used[k]=false;
				}
				else if (!used[str.indexOf(m[i][j])])		
				{
					temp++;//System.out.print("taken ");
					used[str.indexOf(m[i][j])]=true;
				}
				else while (str.indexOf(m[i][j])!=-1)
					{	
						
						temp=0;//System.out.print("used ");
						for (k=0;k<str.length();k++)
						used[k]=false;
						if (j<c-1)
							j++;
						else break;
					}
				
				if (temp==str.length())
				{
					if (j!=c-1)	
						if (str.indexOf(m[i][j+1])!=-1)
							while (str.indexOf(m[i][j])!=-1)
							{	
							temp=0;//System.out.println("wrong2 ");
							for (k=0;k<str.length();k++)
							used[k]=false;
							if (j<c-1)
								j++;
							else break;
							}
						else
						{
							temp=0;count++;//System.out.println("gotit");
							for (k=0;k<str.length();k++)
								used[k]=false;
						}
					else	
					{
						temp=0;count++;//System.out.println("gotit");
						for (k=0;k<str.length();k++)
							used[k]=false;
					}
				}

				j++;//System.out.print(temp+" ");
			}
			
		}
			
		for (j=0;j<c;j++)
		{	for (k=0;k<str.length();k++)
				used[k]=false;
			i=0;temp=0;
			while (i<r)
			{	
				if (str.indexOf(m[i][j])==-1)
				{
					temp=0;
					for (k=0;k<str.length();k++)
						used[k]=false;
				}
				else if (!used[str.indexOf(m[i][j])])		
				{
					temp++;
					used[str.indexOf(m[i][j])]=true;
				}
				else while (str.indexOf(m[i][j])!=-1)
					{
						
						temp=0;
						for (k=0;k<str.length();k++)
						used[k]=false;if (i<r-1)
							i++;
						else break;
					}
				
				if (temp==str.length())
				{
					if (i!=r-1)	
						if (str.indexOf(m[i+1][j])!=-1)
							while (str.indexOf(m[i][j])!=-1)
							{
								temp=0;
								for (k=0;k<str.length();k++)
									used[k]=false;
								if (i<r-1)
									i++;
								else break;
							}
						else
						{
							temp=0;count++;
							for (k=0;k<str.length();k++)
								used[k]=false;
						}
					else	
					{
						temp=0;count++;
						for (k=0;k<str.length();k++)
							used[k]=false;
					}
				}

				i++;
			}
		}
		System.out.println(count);
			

	}
}


		
					