import ncst.pgdst.*;
//Yellow = 1   	Red  = 0
class Cell
{
	int move,coin;
	Cell(int coin, int move)
	{
		this.coin = coin;	this.move = move;
	}
}
public class Game
{
	static int Four = 43;
	static int Coinfour = 2;
	static Cell [][] cell = new Cell[6][7];
	
public static void main(String [] args) throws IOException
	{
		int [] col={0,0,0,0,0,0,0};
		int c;
		SimpleInput sin = new SimpleInput();
		for(int i = 1; i<43; i++)
		{
			c = sin.readInt();
			cell[(col[c-1]++)][(c-1)] = new Cell(i%2,i);
		}

		for(int i=0; i<6; i++)
			for(int j = 0; j<4; j++)
			{
				int cn = cell[i][j].coin;
				int temp = 0;
				int k;
				for( k=0; k<4; k++)
				{
				if (cell[i][j+k].coin != cn) k = 5;
				else 
				temp=(temp>cell[i][j+k].move)?temp:cell[i][j+k].move;
   				}
				if(k==4)
					if(temp < Four)
					{ Four = temp; Coinfour = cn;}
			}
		for(int i=0; i<7; i++)
			for(int j=0; j<3; j++)
			{
				int cn = cell[j][i].coin;
				int temp = 0; int k;
				for( k=0; k<4; k++)
				{
				if(cell[j+k][i].coin != cn) k=5;
				else
				temp =(temp>cell[j+k][i].move)?temp:cell[j+k][i].move;
				}
				if(k==4)
					if(temp<Four)
					{ Four=temp; Coinfour = cn; }
			}
		for(int i=0; i<3; i++)
			for(int j=0; j<4; j++)
			{
				int cn = cell[i][j].coin; int temp =0;
				int k;
				for(k=0; k<4; k++)
				{
				if(cell[i+k][j+k].coin != cn) k=5;
				else
				temp =(temp>cell[i+k][j+k].move)?temp:cell[i+k][j+k].move;
				}
				if(k==4)
					if(temp<Four)
					{ Four = temp; Coinfour = cn;}
			}
		for(int i =0; i<3; i++)
			for(int j =3; j<7; j++)
			{
				int cn = cell[i][j].coin; int temp = 0;
				int k;
				for(k=0; k<4; k++)
				{
				if(cell[i+k][j-k].coin != cn) k=5;
				else
				temp =(temp>cell[i+k][j-k].move)?temp:cell[i+k][j-k].move; 						
				}
				if(k==4)
					if(temp<Four)
					{ Four = temp; Coinfour = cn;}
			}

		if (Four == 43) System.out.println("Draw");
		else
			if (Coinfour == 1) System.out.println("Yellow "+Four);
			else System.out.println("Red "+Four);
}
}

    
	

		

