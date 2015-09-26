import ncst.pgdst.*;

public class Assgn14{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int row = sin.readInt();
		int col = sin.readInt();
		sin.skipWhite();
                Maze sheet = new Maze(row,col);
		sheet.readMaze(sin);
		sheet.move(sheet.hashRow,sheet.hashCol,0);
	}
}
class Maze{
	public void move(int r,int c,int direction)//direction 1=north;2=east;3=south;4=west;
	{
		//System.out.println("hops = "+hops+ " r = "+ r + " c = " + c);
		if(maze[r][c] == AT)System.out.println(hops);
		hops++;

		//north
		if(direction != 1)
		if(r > 0)
		if(maze[r-1][c] == ONE || maze[r-1][c] == AT)
		move(r-1,c,3);
			
		//east
		if(direction != 2)
		if(c < col-1)
		if(maze[r][c+1] == ONE || maze[r][c+1] == AT)
		move(r,c+1,4);

		//south
		if(direction != 3)
		if(r < row-1)
		if(maze[r+1][c] == ONE || maze[r+1][c] == AT)
		move(r+1,c,1);

		//west
		if(direction != 4)
		if(c > 0)
		if(maze[r][c-1] == ONE || maze[r][c-1] == AT)
		move(r,c-1,2);
		hops--;
	}
	public void readMaze(SimpleInput sin) throws IOException
	{
		for(int i = 0 ; i < row ; i++)
		{
			for(int j = 0 ; j < col ; j++)
			{
				maze[i][j] = sin.readChar();
				sin.skipWhite();
				if(maze[i][j] == HASH)
				{
					hashRow = i;
					hashCol = j;
				}
				else if (maze[i][j] == AT)
				{
					atRow = i;
					atCol = j;
				}
				//System.out.println("maze["+i+"]["+j+"] ="+ maze[i][j]);
			}
			sin.skipWhite();
		}
	}
	
	Maze(int rows,int columns)
	{
		row = rows;
		col = columns;
		maze = new char[row][col];
	}
	final char ZERO = '0';
	final char ONE = '1';
	final char AT = '@';
	final char HASH = '#';
	
	int prevRow;
	int prevCol;
	char[][] maze;
	int row;
	int col;
	int r;
	int c;
	int hops = 0;
	int hashRow;
	int hashCol;
	int atRow;
	int atCol;
}
