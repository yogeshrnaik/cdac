import ncst.pgdst.*;

class Segments
{
          double fix,low,max;
          int count = 0;
          Segments(int maxv, int maxh, SimpleInput sin) throws IOException
              {  Crossing [] Vert = new Crossing[maxv];
                 for(int i=0; i<maxv; i++)
                {       
                        fix = Double.valueOf(sin.readWord()).doubleValue();
                        low = Double.valueOf(sin.readWord()).doubleValue();
                        max = Double.valueOf(sin.readWord()).doubleValue();
                        if(low > max) Vert[i] = new Crossing(fix,max,low);
                        else Vert[i] = new Crossing(fix,low,max);
                }

                
                Crossing [] Hoiz = new Crossing[maxh];

                for(int i=0; i<maxh; i++)
                {       
                        fix = Double.valueOf(sin.readWord()).doubleValue();
                        low = Double.valueOf(sin.readWord()).doubleValue();
                        max = Double.valueOf(sin.readWord()).doubleValue();
                        
                        if(low > max)  Hoiz[i] = new Crossing(fix,max,low);
                        else Hoiz[i] = new Crossing(fix,low,max);
                }

                for(int i=0; i <maxv; i++)
                        for(int j=0; j<maxh; j++)
                        if((( Vert[i].low <= Hoiz[j].fix) && (Vert[i].max >= Hoiz[j].fix)) &&((Hoiz[j].low <= Vert[i].fix) && (Hoiz[j].max >= Vert[i].fix)))
                                count++;
                System.out.print(count);

                                         }
                                 }



public class Crossing
{
        public double fix,low,max;

        Crossing(double fix, double low, double max)
        {
                this.fix = fix;
                this.low = low;
                this.max = max;
        }

        public static void main(String [] args) throws IOException
        {
                int maxv,maxh;
                
                
                SimpleInput sin = new SimpleInput();
                maxv = sin.readInt();
                maxh = sin.readInt();
                Segments seg = new Segments(maxv,maxh,sin);

                }
                    }
