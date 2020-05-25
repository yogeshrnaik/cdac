import ncst.pgdst.*;

public class R2DD2R
{	
	public static void main(String[] agrs) throws IOException
	{	
		
		SimpleInput sin = new SimpleInput();
		int N = sin.readInt();
		String in[] = new String[N];
		for (int i=0;i<N;i++)
		{
			sin.skipWhite();
			in[i] = sin.readLine();
		}

		String str = "MDCLXVI";
		int num[] = {1000,500,100,50,10,5,1};

		for (int i=0;i<N;i++)
		{
		try
		{	
			
			int dec = Integer.parseInt(in[i]);
			System.out.println(in[i]);
			
			boolean done=false;
			int j;
			j=0;
			
			
			while(!done)
			{
				div = dec / num[j];
				rem = dec % num[j];
				if (rem==0)
				{
					
					
						for (k=0;k<div;k++)
							System.out.print(str.charAt(j));
						done=true;
					
					
				}
				else
				{
					if (div==0)
					{
						 dec>=(num[j]-num[j+2])*/
						
		}
		catch(Exception e) 
		{
			
			System.out.println("String entered:"+in[i]);

		}
		}
	}
}