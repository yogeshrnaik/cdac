import ncst.pgdst.*;
import java.lang.*;

public class SnakeAndLadder{
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                int total = sin.readInt();
                Cell cell = new Cell();
                if(total > 0)cell.readCell(sin,total);
                sin.skipWhite();
                total = sin.readInt();
                if(total > 0)cell.readMove(sin,total);
                int a = cell.movePlayer(0);
                int b = cell.movePlayer(1);

                String charAB;
                if(a >= 99 && b >= 99)
                {
                        if(cell.throwAB[0] <= cell.throwAB[1])
                        {
                                charAB = "A";
                                total = a;
                        }
                        else
                        {
                                charAB = "B";
                                total = a;
                        }
                }
                else
                {
                        if(a > b)
                        {
                                charAB = "A";
                                total = a;
                        }
                        else if (b > a)
                        {
                                charAB = "B";
                                total = b;
                        }
                        else
                        {
                                if(cell.throwAB[0] < cell.throwAB[1])
                                {
                                        charAB = "A";
                                        total = a;
                                }
                                else
                                {
                                        charAB = "B";
                                        total = b;
                                }
                        }
                //      if(a >= 99)total = 99;
                //      if(b >= 99)total = 99;
                }

                System.out.println( charAB + " " + total );

        }
}
class Cell{
        Cell()
        {
                number[0] = 0;
                number[1] = 0;
        }
        public int checkSnake(int pos)
        {
                for(int i = 0 ; i < snakes ; i++)
                {
                        if(pos == snake[i][0]) return snake[i][1];
                }
                return pos;
        }
        public int checkLadder(int pos)
        {
                for(int i = 0 ; i < ladders ; i++)
                {
                        if(pos == ladder[i][0]) return ladder[i][1];
                }
                return pos;
        }
        public int movePlayer(int player)
        {
                int newPos;
                for(int i = player ; i < move.length ; i = i + 2)
                {
                        number[player] = number[player] + move[i];
                        newPos = number[player];
                        do
                        {
                                number[player] = newPos;
                                newPos = checkSnake(number[player]);
                                //number[player] = newPos;
                                if(newPos != number[player])continue;
                                newPos = checkLadder(number[player]);
//System.out.println("newPos="+newPos+" number["+player+"]="+number[player]);
                        }while(newPos != number[player]);
                        if(number[player] >= 99)
                        {
                                throwAB[player] = 1+(i - player)/2;
                                break;
                        }
//                      System.out.println("movePlayer " + i+" " + move.length + "number["+player+"]="+number[player]);
                }
                return number[player];
        }
        public void readMove(SimpleInput sin,int total) throws IOException
        {
                move = new int[total];
                for(int i = 0 ; i < total ; i++)
                {
                        move[i] = sin.readInt();
//                      sin.skipWhite();

//              System.out.println("readMove "+ "move["+i+"]="+move[i]);
                }
        }
        public void readCell(SimpleInput sin,int total) throws IOException
        {
                int[][] s = new int[total][2];
                int[][] l = new int[total][2];
                int first;
                int second;
                snakes = 0;
                ladders = 0;
                for(int i = 0 ; i < total ; i++)
                {
                        first = sin.readInt();
                        second = sin.readInt();
                        sin.skipWhite();
                        if(first > second)
                        {
                                s[snakes][0] = first;
                                s[snakes][1] = second;
                                snakes++;
                        }
                        else
                        {
                                l[ladders][0] = first;
                                l[ladders][1] = second;
                                ladders++;
                        }
                }
                snake = new int[snakes][2];
                for(int i = 0 ; i < snakes ; i++)
                {
                        snake[i][0] = s[i][0];
                        snake[i][1] = s[i][1];
//              System.out.println("readMove "+ "snake["+i+"]["+0+"]="+snake[i][0]+"snakes="+snakes+"s["+i+"]="+s[i][0]);
                }
                ladder = new int[ladders][2];
                for(int i = 0 ; i < ladders ; i++)
                {
                        ladder[i][0] = l[i][0];
                        ladder[i][1] = l[i][1];
                }
        }
        int[] number = new int[2];
        int snakes = 0;
        int ladders = 0;
        int[] throwAB = new int[2];
        int[][] snake;
        int[][] ladder;
        int[] move;
}

