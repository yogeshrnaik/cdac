import ncst.pgdst.*;
import java.util.Date;
class Jar
{
	int capacity;
	int volume;
	int rem;

	Jar (int c, int v)
	{
		capacity = c;
		volume = v;
		rem = c - v;
	}
	boolean isFull ()
	{
		return (capacity == volume);
	}
	boolean isEmpty ()
	{
		return (volume == 0);
	}
}

class Transfer
{
	int from, to;
	int amt;
	Transfer (int f, int t, int a)
	{
		from = f;
		to = t;
		amt = a;
	}
}

public class WaterJuggles
{
	int no_of_jars;
	Jar jar[];
	int amtReqd;
	int no_of_transfers;

	Transfer transfer[];		// this will hold the sequence of transfers for current solution
	Transfer minTransfer[];		// this will hold solution found with min transfers
	int size;

	WaterJuggles (SimpleInput sin) throws IOException
	{
		no_of_jars = sin.readInt();
		jar = new Jar[no_of_jars];
		for (int i=0; i<no_of_jars; i++)
		{
			jar[i] = new Jar(sin.readInt(), sin.readInt());
		}
		amtReqd = sin.readInt();
		no_of_transfers = 100;

		size = 0;
		transfer = new Transfer[7];

		long time1 = (new Date()).getTime();
		solve(0);
		long time2 = (new Date()).getTime();
		System.out.println("Total time required to arrive at solution = " + (time2 - time1) + " milliseconds.");
		
		System.out.println("Minimum No. of transfers required = "+no_of_transfers);
		System.out.println("From\tTo\tAmt");
		for (int i=0; i<no_of_transfers; i++)
		{
			System.out.println(minTransfer[i].from + "\t" + minTransfer[i].to + "\t" + minTransfer[i].amt);
		}
	}
	public boolean isTransferPossible (int from, int to)
	{
		if (jar[from].isEmpty())
		{
			return false;
		}
		if (jar[to].isFull())
		{
			return false;
		}
		return true;
	}

	public int getTransferedAmt(int from, int to)
	{
		int amtTransfered = jar[from].volume;
		if (amtTransfered > jar[to].rem)
		{
			amtTransfered = jar[to].rem;
		}

		jar[from].volume -= amtTransfered;
		jar[from].rem += amtTransfered;

		jar[to].volume += amtTransfered;
		jar[to].rem -= amtTransfered;

		transfer[size++] = new Transfer(from, to, amtTransfered);

		return amtTransfered;
	}

	public void undoTransfer (int from, int to, int amtTransfered)
	{
		jar[from].volume += amtTransfered;
		jar[from].rem -= amtTransfered;

		jar[to].volume -= amtTransfered;
		jar[to].rem += amtTransfered;

		if (size != 0)
		{
			size--;
		}
	}
	public boolean isDone()
	{
		for (int i=0; i<no_of_jars; i++)
		{
			if (jar[i].volume == amtReqd)
			{
				return true;
			}
		}
		return false;
	}

	public void solve(int steps)
	{
		if (steps >= 7) { return; }
		if (isDone())
		{
			//System.out.println("No of transfers required = "+steps);
			if (steps < no_of_transfers)
			{
				no_of_transfers = steps;
				minTransfer = new Transfer[no_of_transfers];
				for (int j=0; j<no_of_transfers; j++)
				{
					minTransfer[j] = new Transfer(transfer[j].from, transfer[j].to, transfer[j].amt);
				}
			}
			return;
			//System.exit(1);
		}
		int transferedAmt;
		for (int from=0; from<no_of_jars; from++)
		{
			for (int to=0; to<no_of_jars; to++)
			{
				if (from != to)
				{
					if (isTransferPossible (from, to))
					{
						transferedAmt = getTransferedAmt(from, to);
						solve(steps+1);
						undoTransfer(from, to, transferedAmt);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		WaterJuggles wj = new WaterJuggles(new SimpleInput());
	}
}
