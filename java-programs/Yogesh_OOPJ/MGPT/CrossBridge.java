import ncst.pgdst.*;
public class CrossBridge
{
  public static void main (String[] args) throws IOException
  {
     SimpleInput sin = new SimpleInput();
     Person[] P = new Person[5];
     for (int i=0; i<P.length; i++)
        P[i] = new Person(sin);
     Person.sort(P);
     int total1 = 2*P[0].time + 3*P[1].time + P[2].time + P[4].time;
     int total2 = 3*P[0].time + P[1].time + P[2].time + P[3].time + P[4].time;

     int total = total1>total2 ? total2 : total1;
     System.out.println(total);
  } // main
} // CrossBridge
class Person
{
        char name;
        int time;
        Person (SimpleInput in) throws IOException
        {
                in.skipWhite();
                name = in.readChar();
                time = in.readInt();
        }
        public static void sort(Person[] P)
        {
                for (int i=0; i<P.length-1; i++)
                {
                   for (int j=i+1; j<P.length; j++)
                   {
                      if (P[i].time > P[j].time)
                      {
                          Person t = P[i];
                          P[i] = P[j];
                          P[j] = t;
                      }
                   }
                }

        } // sort
}
