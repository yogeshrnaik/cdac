import ncst.pgdst.*;

public class perm2
{
	public static void main(String[] args) throws IOException
	{
		
		int i,N,nthPerm,count=1,flag,updated=0,done=0,inc=1,temp=0,j,updatePoint;
		int used[] = new int[26];
		char str[] = new char[26];
		char original[] = new char[26];
		char last;
		
		SimpleInput sin = new SimpleInput();
		N = sin.readInt();
		nthPerm = sin.readInt();
		for (i=0;i<N;i++)
			str[i] = original[i] = (char)('a'+i);
		last = (char)('a' + (N-1));	
		for (i=0;i<N;i++)
			used[i] = 0;
		updatePoint = N-2;
		while (count!=nthPerm)
		{
			flag=0;
			if (str[0]==(char)('a'+ (N-1)))
				last = (char)('a' + (N-2));


			for (i=0;i<updatePoint;i++)
				{
					j=0;
					while(str[i]!=original[j])
						j++;
					used[j] = 1;
				}
			if (str[updatePoint]==last)
				{
					if (updatePoint==0)
					{}
					else
					{
						str[updatePoint] = (char)(str[updatePoint-1]+1);
						i=0;
						while (str[updatePoint-1]!=original[i])
							i++;
						used[i] = 1;
						used[i-1]=0;
						updatePoint--;
					}
				}
			else
				{
					i=0;
					while (str[updatePoint]!=original[i])
						i++;
					while (used[i+1]==1)
						{
							inc++;i++;
						}
					str[updatePoint] = (char)(str[updatePoint]+inc);
					i=0;
					while (str[updatePoint]!=original[i])
						i++;
					used[i]=1;
					used[i-inc]=0;
				}
			for (i=updatePoint+1;i<N;i++)
				{
							j=0;
							while (used[j]==1)
								j++;
							str[i] = original[j];
							used[j]=1;
				}
			for (i=updatePoint+1;i<N;i++)
				{
							j=0;
							while (str[i]!=original[j])
								j++;
							used[j]=0;
				}
			for (i=updatePoint+1;i<N;i++)
				{
					j=0;
					while (str[i]!=original[j])
						j++;
					while ((j+1)<N)
						{
							if (used[j+1]!=1)
								{
									updatePoint=i;
									updated=1;
									j++;break;
								}
							else
								j++;
						}
				}
			if (updated==0)
				{
					for (i=0;i<N-1;i++)
						{
						for (j=i;j<N-1;j++)
							{
								if (str[j]>str[j+1])
									temp=1;
								else
									{
										temp=0;
										break;
									}
							}
						if (temp==1)
							break;
						}
					if (temp==1)
						updatePoint=i-1;
					else
						updatePoint--;

				}
			updated=0;done=0;inc=1;temp=0;
			for (i=0;i<N;i++)
				used[i]=0;
			count++;
		}
	for (i=0;i<N;i++)
		System.out.print(str[i]);
	System.out.println();
	
	}
}





