import ncst.pgdst.*;

class Signal
{
	public int start,end;
	Signal()
	{
		start = end = 0;
	}
}

public class Or
{
		
	Signal C[];boolean flag=false;
	int highA,highB,highC,i,j;
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Or or = new Or(sin);
		
	}

	Or(SimpleInput sin) throws IOException
	{		
		highA = sin.readInt();
		
		Signal Cx[] = new Signal[100];		

		for (i=0;i<100;i++)
			Cx[i] = new Signal();
		for (i=0;i<highA;i++)
		{
			Cx[i].start = sin.readInt();
			Cx[i].end = sin.readInt();
		}
		highB = sin.readInt();
		highC = highA + highB;
		for (i=highA;i<highC;i++)
		{
			Cx[i].start = sin.readInt();
			Cx[i].end = sin.readInt();
		}
		
		for (i=0;i<highC-1;i++)
			for (j=i+1;j<highC;j++)
				if (Cx[i].start > Cx[j].start)
				{
					int temp = Cx[i].start;
					Cx[i].start = Cx[j].start;
					Cx[j].start = temp;
					temp = Cx[i].end;
					Cx[i].end = Cx[j].end;
					Cx[j].end = temp;
				}
		C = Cx;
		
		i=0;
		while (true)
		{	
			if (highC-1==i)
				break;
			if (Cx[i].end>=Cx[i+1].start)
			{
				if (Cx[i].end>=Cx[i+1].end)
				{
					update(i+1);
					flag=true;
				}
				else
				{
					Cx[i].end = Cx[i+1].end;
					update(i+1);
					flag=true;
				}
			}
			if (flag==false)
				i++;
			flag=false;
		}

		System.out.print(highC);
		for (i=0;i<highC;i++)
			System.out.print(" "+Cx[i].start+" "+Cx[i].end);	
		System.out.println();
	}

	public void update(int x)
	{	
		for (int j=x;j<highC-1;j++)
		{
			C[j].start = C[j+1].start;
			C[j].end = C[j+1].end;
		}
		highC--;
	}
}
				

		
			
		
		