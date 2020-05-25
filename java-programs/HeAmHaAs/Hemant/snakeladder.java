import ncst.pgdst.*;

public class snakeladder
{
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Snake snakes[] = new Snake[10];
		Ladder ladders[] = new Ladder[10];
		int moves[] = new int[100];
		int N,Nmoves,i,j,k,snakeN,ladderN;
		int temp1,temp2;
		boolean done;
		snakeN = ladderN = 0;
		N = sin.readInt();
		for (i=0;i<N;i++)
		{
			ladders[i] = new Ladder();
			snakes[i] = new Snake();
		}		
		for (i=0;i<N;i++)
		{
			temp1 = sin.readInt();
			temp2 = sin.readInt();
			if (temp1<temp2)
			{
				ladders[ladderN] = new Ladder(temp1,temp2);
				ladderN++;
			}
			else
			{
				snakes[snakeN] = new Snake(temp1,temp2);
				snakeN++;
			}
		}
		Nmoves = sin.readInt();
		for (i=0;i<Nmoves;i++)
			moves[i] = sin.readInt();
		
		player A = new player();
		player B = new player();
		
		for (i=0;i<Nmoves;i++)
		{
			if (i % 2 == 0)
			{
				A.setPos(A.getPos()+moves[i]);
				j=0;	
				done=false;			
				while (!done)
				{
					for (j=0;j<snakeN;j++)
						if (A.getPos()==snakes[j].getStart())
							A.setPos(snakes[j].getEnd());
				
					for (j=0;j<ladderN;j++)
						if (A.getPos()==ladders[j].getStart())
							A.setPos(ladders[j].getEnd());
				
					for (j=0;j<snakeN;j++)
						if (A.getPos()!=snakes[j].getStart() && A.getPos()!=ladders[j].getStart())
							done=true;
						else
							done=false;
					if (A.getPos()>=99)
					{
						System.out.println("A"+A.getPos());
						System.exit(1);
					}

				}
					
			}
			else
			{
				B.setPos(B.getPos()+moves[i]);
				j=0;
				done=false;
				while (!done)
				{
					for (j=0;j<snakeN;j++)
						if (B.getPos()==snakes[j].getStart())
							B.setPos(snakes[j].getEnd());										
					for (j=0;j<ladderN;j++)
						if (B.getPos()==ladders[j].getStart())
							B.setPos(ladders[j].getEnd());
				
					for (j=0;j<snakeN;j++)
						if (B.getPos()!=snakes[j].getStart() && B.getPos()!=ladders[j].getStart())
							done=true;
						else
							done=false;
					if (B.getPos()>=99)
					{
						System.out.println("B"+B.getPos());
						System.exit(1);
					}
				}
			}
		}
		
		if (A.getPos() > B.getPos())
			System.out.print("A"+" "+A.getPos());
		else
			System.out.print("B"+" "+B.getPos());
		System.out.println();
	}
}

class Snake
{
	private int start,end;
	Snake()
	{
		start = end = 0;
	}
	Snake(int thestart,int theend)
	{
		start = thestart;
		end = theend;
	}
	public int getStart()
	{
		return (start);
	}
	public int getEnd()
	{
		return (end);
	}
}

class Ladder
{
	private int start,end;
	Ladder()
	{
		start = end = 0;
	}
	Ladder(int thestart,int theend)
	{
		start = thestart;
		end = theend;
	}
	public int getStart()
	{
		return (start);
	}
	public int getEnd()
	{
		return (end);
	}
}
class player
{
	private int pos;
	public int getPos()
	{
		return(pos);
	}
	public void setPos(int apos)
	{
		pos = apos;
	}
}


	
