import ncst.pgdst.*;

public class Chain
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Chain chain = new Chain(sin);
	}
	String w[];int N;
	Chain(SimpleInput sin) throws IOException
	{
		N = sin.readInt();
		String wx[] = new String[N];
		for (int i=0;i<N;i++)
			wx[i] = sin.readWord();
		w = wx;
		search(0);
		System.out.println("IMPOSSIBLE");
	}

	public void search(int i)
	{
		if (i==N-1)
		{
			for (int k=0;k<N;k++)
				System.out.println(w[k]);
			System.exit(1);
		}
		else
		{
			int temp;
			
			for (int j=i+1;j<N;j++)
			{	temp = (w[i].length() > w[j].length()) ? w[j].length() : w[i].length();
				
				//System.out.println(w[i]+" "+w[j]+" ");
				for (int k=3;k<=temp;k++)
					if (w[i].endsWith(w[j].substring(0,k)))
					{	//System.out.print("match ");
						String temp1 = w[i+1];
						w[i+1] = w[j];
						w[j] = temp1;
						/*for (int x=0;x<N;x++)
							System.out.print(w[x]+" ");
						System.out.println(i);*/
						search(i+1);
					}
			}
		}
	}
}
