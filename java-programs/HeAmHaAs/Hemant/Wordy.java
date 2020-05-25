import ncst.pgdst.*;

public class Wordy
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Wordy wordy = new Wordy(sin);
	}

	String str;int y;
	String original[],a[];char arr[];
	int count,max,N;boolean done;
	Wordy(SimpleInput sin) throws IOException
	{
		int x;
		N = sin.readInt();
		String ax[] = new String[N];
		a = ax;original = ax;
		for (x=0;x<N;x++)
			ax[x] = sin.readWord();
		y = sin.readInt();
		char arrX[] = new char[y];
		for (x=0;x<y;x++)
			arrX[x] = sin.readChar();
		arr = arrX;
		str = str.copyValueOf(arrX);
		System.out.print(str);
		max = 0;
		for (int m=0;m<N;m++)
		{	
			for (int p=0;p<N;p++)
				a[p] = original[p];
			String temp = a[0];
			a[0] = a[m];
			a[m] = temp;
			arr = str.toCharArray();
			count=0;
			
			for (int n=0;n<a[0].length();n++)
			{
				if (str.indexOf(a[0].charAt(n))!=-1)
					done = true;
				else 
				
					done = false;
					
				
			}
			if (done)
			{	System.out.print(a[0]);
				for (int n=0;n<a[0].length();n++)
				{	System.out.print(" "+a[0].charAt(n)+" ");
					arr[str.indexOf(a[0].charAt(n))]='#';}
				for (x=0;x<y;x++)
					System.out.print(arr[x]);
				System.out.println();
				count++;
				recurse(1,count);
			}
			if (max<count)
				max=count;
		}
		System.out.println(max);
	}

	public void recurse(int i,int count)
	{	System.out.print(i);
		if (i==N)
			return;
		else
		{	
			for (int j=i;j<N;j++)
				a[j] = original[j];
			for (int j=i;j<N;j++)
			{
				String temp=a[i];
				a[i] = original[j];
				original[j] = temp;
				
				for (int k=0;k<a[j].length();k++)
				{
					if (str.indexOf(a[j].charAt(k))!=-1)
						done=true;
					else 
					
						done=false;
					
				}
				if (done)
				{	System.out.print(a[i]+" ");
					for (int k=0;k<a[j].length();k++)
					{	System.out.print(" "+a[j].charAt(k)+" ");
						arr[str.indexOf(a[j].charAt(k))]='#';	}
					for (int x=0;x<y;x++)
						System.out.print(arr[x]);
					System.out.println();
					count++;
					recurse(i+1,count);
				}
				if (max<count)
					max = count;
			
			}
		}
	}
}
	

		


	
