import ncst.pgdst.*;
public class Snk_Lad
{
        public int start;
        public int end;
        Snk_Lad(SimpleInput in) throws IOException
        {
                start = in.readInt();
                end   = in.readInt();
        }

        public static void main (String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                int n = sin.readInt();
                Snk_Lad SL[] = new Snk_Lad[n];
                for (int i=0; i<n; i++)
                        SL[i] = new Snk_Lad(sin);
                int moves = sin.readInt();
                Player A = new Player ('A', 0);
                Player B = new Player ('B', 0);
                int[] M = new int[moves];                
                for (int i=0; i<moves; i++)
                        M[i] = sin.readInt();

                for (int i=0; i<moves; i++)
                {
                        Player X = new Player();  
                        if (i%2 == 0) X = A;
                        else X = B;
                        X.pos = X.pos + M[i];
                        if (X.pos > 99)
                        {
                                System.out.println (X.name + " " + 99);
                                System.exit(1);
                        }
                        boolean found = false;
                        do
                        {
                                found = false;
                                int j=0;
                                while ((!found)&&(j<n))
                                {
                                        if (X.pos == SL[j].start)
                                        {   X.pos = SL[j].end;
                                            found = true;
                                        }
                                        else j++;
                                }
                        } while (found);
                } // for

                if (A.pos > B.pos)
                        System.out.println (A.name + " " + A.pos);
                else
                        System.out.println (B.name + " " + B.pos);
        } // main
} // SnkLad
class Player
{
        public char name;
        public int pos;
        Player() { }
        Player(char aName, int aPos)
        {       name = aName;
                pos = aPos;
        }
}
