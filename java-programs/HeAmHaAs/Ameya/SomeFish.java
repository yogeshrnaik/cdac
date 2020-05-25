//import java.io.File;
import ncst.pgdst.*;

class Fish
{
        int x,y;
        Fish(int x, int y)
        {       this.x = x; this.y = y; }
}

public class SomeFish
{
        static Fish David = new Fish(0,0);
        static Fish Goliath = new Fish(0,0);
        static  int m,n;
        public static void main(String [] args) throws IOException
        {
               
                SimpleInput sin = new SimpleInput();
                m=sin.readInt();
                n=sin.readInt();
                David.x = sin.readInt(); David.y = sin.readInt();
                Goliath.x = sin.readInt(); Goliath.y = sin.readInt();
                int time = sin.readInt();
                int i=0;
                for(i=0; i<time; i++)
                {
                        /*if (distance(David.x,David.y,Goliath.x,Goliath.y) ==0)
                            {
                                System.out.println(i+" "+David.x+" "+David.y);
                                i = time;
                            } */
                        if (i!=time)
                        {
                                move_Goliath();
                        if (distance(David.x,David.y,Goliath.x,Goliath.y) ==0)
                                { System.out.println((i+1)+" "+Goliath.x+" "+Goliath.y);
                                  i = time;
                                }
                        else move_David();
                        }
                }
                if(i==time) System.out.println(David.x+" "+David.y+" "+Goliath.x+" "+Goliath.y);
                
        }

        public static int distance(int x1, int y1, int x2, int y2)
        {
                return((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        }

        public static void move_Goliath()
        {
                int di [] = new int[4];
                di[0] = distance(Goliath.x,Goliath.y-1,David.x,David.y);
                di[1] = distance(Goliath.x-1,Goliath.y,David.x,David.y);
                di[2] = distance(Goliath.x,Goliath.y+1,David.x,David.y);
                di[3] = distance(Goliath.x+1,Goliath.y,David.x,David.y);
                if (Goliath.y==0) di[0] += di[1] + di[2] + di[3];
                if (Goliath.x==0) di[1] += di[0] + di[2] + di[3];
                if (Goliath.y==n-1) di[2] += di[0] + di[1] + di[3];
                if (Goliath.x==m-1) di[3] += di[0] + di[1] + di[2];
                int lowest= di[0] +di[1] + di[2] + di[3];
                int pos = 0;
                int current;
                for(int i=0; i<4; i++)
                { 
                     current = di[i];
                     if (current < lowest) { lowest = current; pos = i;}
                     }
                switch (pos)
                {
                case 0 : {Goliath.y -= 1; break;}
                case 1 : {Goliath.x -= 1; break;}
                case 2 : {Goliath.y += 1; break;}
                case 3 : {Goliath.x += 1; break;}
                }
        }

        public static void move_David()
        {
                
                int di[] = new int[8];
                di[0] = distance(David.x,David.y-1,Goliath.x,Goliath.y);
                di[1] = distance(David.x-1,David.y-1,Goliath.x,Goliath.y);
                di[2] = distance(David.x-1,David.y,Goliath.x,Goliath.y);
                di[3] = distance(David.x-1,David.y+1,Goliath.x,Goliath.y);
                di[4] = distance(David.x,David.y+1,Goliath.x,Goliath.y);
                di[5] = distance(David.x+1,David.y+1,Goliath.x,Goliath.y);
                di[6] = distance(David.x+1,David.y,Goliath.x,Goliath.y);
                di[7] = distance(David.x+1,David.y-1,Goliath.x,Goliath.y);
                

                if (David.y==0)
                      { di[0] = di[1] = di[7] = 0;}
                if (David.y==n-1)
                      { di[3] = di[4] = di[5] = 0;}

                if (David.x==0)
                      { di[1] = di[2] = di[3] = 0;}
                if (David.x == m-1)
                      { di[5] = di[6] = di[7] = 0;}
                

                int pos = 0;
                int current;
                int max = 0;
                for(int i=0; i<8; i++)
                { 
                     current = di[i];
                     if (current > max) { max = current; pos = i;}
                     }

                switch (pos)
                {
                case 0 : { David.y -= 1; break;} 
                case 1 : { David.y -= 1; David.x -= 1; break;}
                case 2 : { David.x -= 1; break;}
                case 3 : { David.x -= 1; David.y += 1; break;}
                case 4 : { David.y += 1; break;}
                case 5 : { David.x += 1; David.y += 1; break;}
                case 6 : { David.x += 1; break;}
                case 7 : { David.x += 1; David.y -= 1; break;}
                }



        }
        }
