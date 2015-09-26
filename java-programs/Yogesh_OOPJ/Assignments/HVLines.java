import ncst.pgdst.*;
public class HVLines
{       public static void main (String[] args) throws IOException
        {       SimpleInput sin = new SimpleInput();
                int v = sin.readInt();
                int h = sin.readInt();
                Line V[] = new Line[v];
                Line H[] = new Line[h];
                for (int i=0; i<v; i++)
                        V[i] = new Line(sin);
                for (int i=0; i<h; i++)
                        H[i] = new Line(sin);

                // count intersections
                int count = 0;
                for (int i=0; i<v; i++)
                {   for (int j=0; j<h; j++)
                    {
                        if ((V[i].xy >= H[j].xy1)&&(V[i].xy <= H[j].xy2))
                           if ((H[j].xy >= V[i].xy1)&&(H[j].xy <= V[i].xy2))
                                count++;
                    }
                }
                System.out.println(count);
        } // main
} // HVLines
class Line
{
        int xy, xy1, xy2;
        Line(SimpleInput in) throws IOException
        {       
                xy  = in.readInt();
                xy1 = in.readInt();
                xy2 = in.readInt();
        }
} // Line
