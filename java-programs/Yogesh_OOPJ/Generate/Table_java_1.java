import ncst.pgdst.*;
public class Table
{
        public static void main(String[] args) throws IOException
        {
                final int COLUMN_WIDTH = 10;
                for (int i=1; i<=10; i++)
                {
                        for (int j=1; j<=8; j++)
                        {
                                int p = (int)Math.pow(i,j);
                                if (j>1)
                                {
                                        String pstr = " " + p;

                                        while (pstr.length() < COLUMN_WIDTH)
                                                pstr = " " + pstr;
                                        System.out.print(pstr);
                                }
                                else
                                        System.out.print(p);
                                
                        }
                        System.out.println();
                }
        }
}
