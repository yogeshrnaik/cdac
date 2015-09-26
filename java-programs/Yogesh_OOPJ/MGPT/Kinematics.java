------------------------------------
File: Kinematics.java
------------------------------------
import ncst.pgdst.*;
class Term
{
        double constant;
        int power;
        Term()
        {
                constant = power = 0;
        }
}
class Poly
{
        int size;
        Term term[];
        Poly (int s)
        {
                size = 0;
                term = new Term[s];
                for (int i=0; i<s; i++)
                        term[i] = new Term();         
	}
}
public class Kinematics
{
        int N;
        int valSize;
        int values[];
        Poly speed, deri, inti;
        public static void main (String[] args) throws IOException
        {
                Kinematics ki = new Kinematics(new SimpleInput());
        }
        Kinematics (SimpleInput sin) throws IOException
        {
                N = sin.readInt();
                speed = new Poly(N);
                for (int i=0; i<N; i++)
                {
                        int temp_const = sin.readInt();
                        if (temp_const != 0)
                        {
                           speed.term[speed.size].constant = temp_const;
                        }
                        else
	                        {
                           int temp_pow = sin.readInt();
                           continue;
                        }
                        speed.term[speed.size].power = sin.readInt();
                        speed.size++;
                } // for loop
                values = new int[50];
                valSize = 0;
                do
                {
                        int temp_val = sin.readInt();
                        if (temp_val == -1) break;
                        values[valSize++] = temp_val;
                } while (true);
                getDerivative();
                getIntegral();
                if (speed.size != 0)
                {
                  for (int i=0; i<valSize; i++)
                  {
                   System.out.print (getDisplacementAt(values[i]) + " ");
                   System.out.print (getSpeedAt(values[i]) + " ");
                   System.out.println ( getAccelerationAt(values[i]) );
                  }
                }
                else
                {
                  for (int i=0; i<valSize; i++)
                        System.out.println ("0 0 0");
                }
        } // constructor
        public void getDerivative()
        {
                deri = new Poly(N);
                for (int i=0; i<speed.size; i++)
                {
                   if (speed.term[i].power != 0)
                   {
                        deri.term[deri.size].constant = speed.term[i].constant *
 speed.term[i].power;
                        deri.term[deri.size].power = speed.term[i].power - 1;
                        deri.size++;
                   }
                }
        }
        public void getIntegral()
        {
                inti = new Poly (N);
                for (int i=0; i<speed.size; i++)
                {
                  inti.term[inti.size].constant = speed.term[i].constant/(speed.
term[i].power+1);
                  inti.term[inti.size].power = speed.term[i].power + 1;
                  inti.size++;
                }
        }

        public int getDisplacementAt(int time)
        {
                double total_val = 0;
                for (int i=0; i<inti.size; i++)
                {
                  total_val += inti.term[i].constant * Math.pow (time, inti.term
[i].power);

                }
                return ( (int) total_val );

        }
        public int getSpeedAt(int time)
        {
                double total_val = 0;
                for (int i=0; i<speed.size; i++)
                {
                  total_val += speed.term[i].constant * Math.pow (time, speed.te
rm[i].power);

                }

                return ( (int) total_val );
        }
        public int getAccelerationAt (int time)
        {
                double total_val = 0;
                for (int i=0; i<deri.size; i++)
                {
                  total_val += deri.term[i].constant * Math.pow (time, deri.term
[i].power);

                }
                return ( (int) total_val );

        }
