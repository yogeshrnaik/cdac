import ncst.pgdst.*;

public class ass3
{	static int N;
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		
		int temp,temp2;
		N = sin.readInt();
		int X[] = new int[N];
		int Y[] = new int[N];
		for (int i=0;i<N;i++)
		{
			X[i] = sin.readInt();
			Y[i] = -1;
		}		
		int head,tail,count;
		head = tail = 0;
		for (int i=0;i<N;i++)
		{	count = 0;
			if (i==0)
				Y[i] = X[i];		
			else if (X[i] < Y[head])
			{
				head = updateIndex(head-1);
				Y[head] = X[i];
			}
			else if (X[i] >= Y[tail])
			{
				tail = updateIndex(tail+1);
				Y[tail] = X[i];
			}
			else
			{
				temp = head;
				while (X[i] >= Y[temp])
				{	//System.out.println("temp:"+temp);
					temp++;
					temp = updateIndex(temp);
					count++;
				}
				if (count < i-count)
				{
					temp2 = head;
					for (int j=0;j<count;j++)
					{
						Y[updateIndex(temp2-1)] = Y[temp2];
						temp2++;
						temp2 = updateIndex(temp2);
					}
					Y[updateIndex(temp-1)] = X[i];
					head = updateIndex(head-1);
				}
				else
				{	
					temp2 = tail;
					for (int j=0;j<i-count;j++)
					{
						Y[temp2+1] = Y[temp2];
						temp2--;
					}
					Y[temp] = X[i];
					tail++;
				}
			}
			System.out.print(Y[0]);
			for (int j=1;j<N;j++)
				System.out.print(" "+Y[j]);
			System.out.print(" Head:"+head+" Tail:"+tail);
			System.out.println();
		}
	}

	public static int updateIndex(int x)
	{
		if (x<0)
			return(N-1);
		else if (x==N)
			return(0);
		else return(x);
	}
}
				 
	