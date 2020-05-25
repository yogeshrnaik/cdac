import ncst.pgdst.*;

public class perm
{
	public static void main(String[] args) throws IOException
	{
		char str[] = new char[26];
		char original[] = new char[26];
		String temp;
		double nthPerm,rem;
		int i,j,N,count,div;
		boolean done=false;
		SimpleInput sin = new SimpleInput();
		N = sin.readInt();
		temp = sin.readWord();
		nthPerm = Double.parseDouble(temp);
		for (i=0;i<N;i++)
		{
			original[i] = (char)('a'+i);
			
		}
		
		count=N;
		rem = div = 0;
		while(!done)
		{
			
			if (count==2)
			{
				if (rem==1)
				{
					str[N-2]=original[0];
					str[N-1]=original[1];
				}
				else
				{
					str[N-2]=original[1];
					str[N-1]=original[0];
				}
				done=true;
			}			
			else
			{
				div = (int)(nthPerm / (fact(count-1)));
				rem = nthPerm % (fact(count-1));											
								if (rem!=0)
				{
					nthPerm=rem;
					//System.out.println("N-count:"+(N-count)+" div:"+div);
					str[N-count] = original[div];
					
					
				}
				else
				{
					str[N-count] = original[div-1];
					j=count-1;
					for (i=div-1;i<count-1;i++)
						original[i] = original[i+1];
					
					for (i=N-count+1;i<N;i++)
					{
						str[i] = original[j-1];
						j--;
					}
					done=true;
					
				}
			}
			if (done!=true)
			{
				for (i=div;i<count-1;i++)
					original[i] = original[i+1];
			}
			count--;
			
		}
		for (i=0;i<N;i++)
			System.out.print(str[i]);
		System.out.println();
								
	}

	public static double fact(int x)
	{
		if (x==0)
			return(1);
		else
			return(x*fact(x-1));
	}
}		
