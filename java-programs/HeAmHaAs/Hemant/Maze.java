import ncst.pgdst.*;

public class Maze
{	MazeX A[][];
	int count=0,rows,columns;boolean flag=false;
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();	
		Maze maze = new Maze(sin);
	}
	Maze(SimpleInput sin) throws IOException
	{
		int i,j,starti=0,startj=0;
		rows = sin.readInt();
		columns = sin.readInt();
		MazeX Ax[][] = new MazeX[rows][columns];
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)	
				Ax[i][j] = new MazeX();
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
			{
				sin.skipWhite();
				Ax[i][j].Char = sin.readChar();
			}
		
		for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)	
			{
				
			Ax[i][j].left  = j>0 ? ((Ax[i][j-1].Char=='1'||Ax[i][j-1].Char=='@') ? true : false) : false;
			Ax[i][j].right = j<columns-1 ? ((Ax[i][j+1].Char=='1'||Ax[i][j+1].Char=='@') ? true : false) : false;
			Ax[i][j].up    = i>0 ? ((Ax[i-1][j].Char=='1'||Ax[i-1][j].Char=='@') ? true : false) : false;
			Ax[i][j].down = i<rows-1 ? ((Ax[i+1][j].Char=='1'||Ax[i+1][j].Char=='@') ? true : false) : false;
			if (Ax[i][j].Char=='#')
				{
					starti=i;startj=j;
				}
			}			

		/*for (i=0;i<rows;i++)
			for (j=0;j<columns;j++)
				System.out.println(Ax[i][j].left+" "+Ax[i][j].right+" "+Ax[i][j].up+" "+Ax[i][j].down);	*/	

		
		A = Ax;
		
		search(starti,startj);
		
		
	}
	
	public void search(int x,int y)
	{
		
		
		//System.out.println(x+" "+y+" "+count);
		if (A[x][y].Char=='@')
		{
			System.out.println(count);
			System.exit(1);
		}
		else
		{
			if (y>0)
				if ((A[x][y-1].Char=='1'||A[x][y-1].Char=='@') && A[x][y].left!=false)
				{
					A[x][y-1].right=false;
					count++;
					search(x,y-1);
				}
			 if (y<columns-1)
				if ((A[x][y+1].Char=='1'||A[x][y+1].Char=='@') && A[x][y].right!=false)
				{
					A[x][y+1].left=false;
					count++;
					search(x,y+1);
				}
			 if (x>0)
				if ((A[x-1][y].Char=='1'||A[x-1][y].Char=='@') && A[x][y].up!=false)
				{
					A[x-1][y].down=false;					
					count++;
					search(x-1,y);
				}
			if (x<rows-1)
				if ((A[x+1][y].Char=='1'||A[x+1][y].Char=='@') && A[x][y].down!=false)
				{
					A[x+1][y].up=false;
					count++;
					search(x+1,y);
				}
			count--;
		}
		
		
	}
}

class MazeX
{
	public boolean up,down,left,right;char Char;
		
	MazeX()
	{
		up = down = left = right = false;
	}

}
	
		
	
		
			