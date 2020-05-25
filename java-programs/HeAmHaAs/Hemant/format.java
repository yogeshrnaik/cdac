import ncst.pgdst.*;

public class format
{
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		char intpart[] = new char[20];
		char fractpart[] = new char[20];
		int lengthI,lengthF;

		char temp = ' ';
		int i=0;
		while (temp!='.')
		{
			temp = sin.readChar();
			intpart[i] = temp;
			i++;
		}
		lengthI = i-1;
		
		i=0;
		while (temp!='\n')
		{
			temp = sin.readChar();
			fractpart[i] = temp;
			i++;
		}
		lengthF = i-2;
		
		i=0;
		while (fractpart[i]=='0' && lengthF!=0)
		{
			for (int j=0;j<lengthF-1;j++)
				fractpart[j] = fractpart[j+1];
			lengthF--;
		}

		while (intpart[lengthI-1]==0)
			if (intpart[i]=='0')
				lengthI--;


		for (i=0;i<lengthF;i++)
			System.out.print(fractpart[i]);					
		for (i=0;i<5-lengthF;i++)
			System.out.print("#");
		System.out.print(".");
		for (i=0;i<lengthI;i++)
			System.out.print(intpart[i]);	
		
	}
}		