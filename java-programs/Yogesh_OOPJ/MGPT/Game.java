import ncst.pgdst.*;
class Player
{
	char name;
	int posi, posj;
	boolean gotSeat;
	Player(int i)
	{
		name = (char)(i+65);
		posi = i;
		posj = 0;
		gotSeat = false;
	}
} // Player
class Chair
{
	boolean occupied;
	Chair()
	{
		occupied = false;
	}
} // Chair
public class Game
{
	Player[] player;
	Chair[] chair;
	int nop, noc;
	int row, col;
	int[] time;

	Game (SimpleInput sin) throws IOException
	{
		nop = sin.readInt();
		time = new int[nop-1];
		for (int i=0; i<time.length; i++)
			time[i] = sin.readInt();
		noc = nop-1;
		row = noc+2;
		col = 3;
		player = new Player[nop];
		chair = new Chair[noc];
		for (int i=0; i<nop; i++)
			player[i] = new Player(i);
                for (int i=0; i<noc; i++)
                        chair[i] = new Chair();
                play();
                System.out.println(player[0].name);    
        }
	public static void main (String[] args) throws IOException
        {
                Game game = new Game(new SimpleInput());
        }

        void play()
        {
                for (int count=0; count<time.length; count++)
                {
                        for (int i=0; i<nop; i++)
                                player[i].gotSeat = false;
                        for (int i=0; i<time[count]; i++)
                        {
				for (int j=0; j<nop; j++)
					movePlayer(j);
                        }
                        boolean flag = false;
                        while (!allGotChairs())
                        {
                                flag = false;
				for (int j=0; j<nop; j++)
                                {
                                        if (!player[j].gotSeat)
                                        {
                                            flag = playerocchair(j);
                                            if (!flag)
                                                movePlayer(j);
                                        }
                                }
                        }
                        removeChair();
                        removePlayer();
                } // for count
	} // play

        void movePlayer(int i)
        {
                if (player[i].gotSeat) return;
                switch (player[i].posj)
                {
			case 0 : if (player[i].posi == row-1)
                                        player[i].posj = 1;
                                 else player[i].posi++;
                                        break;
                        case 1 : if (player[i].posi == 0)
                                        player[i].posj = 0;
                                 else if (player[i].posi == row-1)
                                        player[i].posj = 2;
                                        break;
                        case 2 : if (player[i].posi == 0)
                                        player[i].posj = 1;
                                 else player[i].posi--;
                                  break;
		}  // switch
	} // move player

        boolean allGotChairs()
        {
                int count=0;
                for (int i=0; i<nop; i++)
			if (player[i].gotSeat)
                                count++;
                if (count == nop-1)
                        return true;
                else
                        return false;
        }
        // return whether a player[i] has got the chair without moving
        boolean playerocchair (int i)
        {
                int pi = player[i].posi;
                int pj = player[i].posj;
                //System.out.println("Player pi " + pi);
		//System.out.println("Noc " + noc);
		if (pi == 0 || pi == row-1)
                        return false;
                //System.out.println("Player pi " + pi);
                if (pj==0)
                {
                        if (pi%2 == 0 && !chair[pi-1].occupied)
			{
                                player[i].gotSeat = true;
                                chair[pi-1].occupied = true;
                                return true;
                        }
                }
                else if (pj == 2)
                {
                        if (pi%2 != 0 && !chair[pi-1].occupied)
                        {
                                player[i].gotSeat = true;
                                chair[pi-1].occupied = true;
                                return true;
			}
		}
                return false;
        } // playerocchair()
        public void removeChair()
        {
                for (int i=0; i<noc; i++)
			chair[i].occupied = false;
                noc--;
                row--;
        }
        public void removePlayer()
        {
                int i=0;
                for (; i<nop; i++)
                        if (!player[i].gotSeat)
                                break;
                if (i == nop-1)
                        nop--;
                else
		{
			for (int j=i; j<nop-1; j++)
                                player[j] = player[j+1];
                        nop--;
                }
        }

} // Game