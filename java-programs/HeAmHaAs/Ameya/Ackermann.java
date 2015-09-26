import ncst.pgdst.*;

public class Ackermann
{


        public long A(long x , long y)
        {
                if (x==0) return (y+1);
                else if(y==0) return A((x-1),1);
                        else return A((x-1),A(x,(y-1)));
                        }
        public static void main(String[] args) throws IOException
        {
                long x, y;
                SimpleInput sin = new SimpleInput();
                x = sin.readLong();
                y = sin.readLong();
                Ackermann ack = new Ackermann();
                System.out.println(ack.A(x,y));
                }
                }

        
