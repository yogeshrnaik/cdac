import ncst.pgdst.*;

class Cell
{
	char c;int x,y;
	Cell()
	{
		c=' ';x=y=0;
	}
}

class Player
{
	int id;int x,y;
	boolean trapped=false;;
	Player()
	{
		id=0;x = y = 0;
		
	}
}

public class Gold
{	Cell cell[][];
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Gold gold = new Gold(sin);
	}

	Gold(SimpleInput sin) throws IOException
	{
		int rows,columns,N;int i,j;char temp;
		rows = sin.readInt();
		columns = sin.readInt();
		Cell cellX[][] = new Cell[rows][columns];
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
				cellX[i][j] = new Cell();
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
			{
				sin.skipWhite();
				temp = sin.readChar();
				if (temp=='D')
				{
					cellX[i][j].c = temp;
					cellX[i][j].x = sin.readInt();
					cellX[i][j].y = sin.readInt();
				}
				else
					cellX[i][j].c = temp;
			}
		/*for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
				System.out.print(cellX[i][j].c+" ");*/
		N = sin.readInt();
		
		Player p[] = new Player[N];
		for (i=0;i<N;i++)
			p[i] = new Player();
		for (i=0;i<N;i++)
		{
			p[i].id = sin.readInt();
			p[i].x = sin.readInt();
			p[i].y = sin.readInt();
		}
		i=0;int ntrapped=0;
		while(ntrapped!=N-1)
		{	
			if (p[i].x<0 || p[i].x>=rows || p[i].y<0 || p[i].y>=columns)
			{
				p[i].x = Math.abs(rows-Math.abs(p[i].x));
				p[i].y = Math.abs(columns-Math.abs(p[i].y));	
				//System.out.println("Changed:"+p[i].x+" "+p[i].y);
			}
			int X,Y;
			X = p[i].x;
			Y = p[i].y;
			if (!p[i].trapped)
			{	//System.out.print(p[i].x+" "+p[i].y+" ");
				if (cellX[X][Y].c=='D')
				{
					//System.out.print(cellX[p[i].x][p[i].y].x+" "+cellX[p[i].x][p[i].y].y+" ");
					p[i].x += cellX[X][Y].x;
					p[i].y += cellX[X][Y].y;
					//System.out.println(p[i].x+" "+p[i].y+" "+p[i].id);
				}
				else if (cellX[p[i].x][p[i].y].c=='T')
				{
					p[i].trapped = true;//System.out.println(p[i].id+" traddped");
					ntrapped++;
				}
				else if (cellX[p[i].x][p[i].y].c=='G')
				{
					System.out.println(p[i].id);
					System.exit(1);
				}
			}
			

	
		
		
			if (i==N-1)
				i=0;
			else
	 			i++;
		}
		i=0;
		while(p[i].trapped)
			i++;
		System.out.println(p[i].id);
	}
}