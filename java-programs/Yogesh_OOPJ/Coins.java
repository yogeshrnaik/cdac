import ncst.pgdst.*;
public class Coins
{
        public static void main (String[] args) throws IOException
        {
                SimpleInput  sin  = new SimpleInput();
                SimpleOutput sout = new SimpleOutput();

                Purse thePurse = new Purse();

                Coin coin1 = new Coin ("Pennies", 0, 0.01);
                Coin coin2 = new Coin ("Nickels", 0, 0.05);
                Coin coin3 = new Coin ("Dimes",   0, 0.10);
                Coin coin4 = new Coin ("Quarters",0, 0.25);

                sout.writeString("\nHow many "+coin1.getName()+" do you have? : ");
                sout.flush();
                coin1.count = sin.readInt();

                sout.writeString("\nHow many "+coin2.getName()+" do you have? : ");
                sout.flush();
                coin2.count = sin.readInt();

                sout.writeString("\nHow many "+coin3.getName()+" do you have? : ");
                sout.flush();
                coin3.count = sin.readInt();

                sout.writeString("\nHow many "+coin4.getName()+" do you have? : ");
                sout.flush();
                coin4.count = sin.readInt();

                thePurse.addCoins (coin1);
                thePurse.addCoins (coin2);
                thePurse.addCoins (coin3);
                thePurse.addCoins (coin4);

                sout.writeln("\nThe total value is $"+thePurse.getTotal());
        } // End of main
} // End of Coins


class Coin
{
        private String name;
        public  int    count;
        private double value;

        // Constructor
        public Coin (String aName, int aCount, double aValue)
        {
                name  = aName;
                count = aCount;
                value = aValue;
        }

        // Methods
        public String getName()
        {  return name;
        }
        public int getCount()
        {  return count;
        }
        public double getValue()
        {  return value;
        }
} // End of Coin             

class Purse
{
        private double total;

        // Constructor
        public Purse()
        {
                total = 0;
        }

        // Methods
        public void addCoins (Coin aCoin)
        {
                double value = aCoin.getCount() * aCoin.getValue();
                total = total + value;
        }
        public double getTotal()
        {   return total;
        }
} // End of Purse
