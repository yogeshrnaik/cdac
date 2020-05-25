import ncst.pgdst.*;

public class Bridge_Crossing
{

        public static void main(String [] args) throws IOException
        {
                int [] men = new int[5];
                SimpleInput sin = new SimpleInput();
                String temp;
                for(int i=0; i<5; i++)
                {
                        sin.skipWhite();
                        temp = sin.readWord();
                        sin.skipWhite();
                        men[i] = sin.readInt();
                        }
                for(int i=0; i<4; i++)
                        for(int j=i+1; j<5; j++)
                        if(men[i]>men[j])
                                {        int t = men[i];
                                         men[i] = men[j];
                                         men[j] = t;
                                         }
                int time1 = 2*men[0] + 3*men[1] + men[2] + men[4];
                int time2 = 3*men[0] + men[1] + men[2] + men[3] + men[4];
                System.out.println((time1<time2)?time1:time2);
                }
}
