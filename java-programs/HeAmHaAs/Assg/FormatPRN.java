import ncst.pgdst.*;
import java.lang.*;

public class FormatPRN{
        public static void main(String args[]) throws IOException
        {
                final char DECIMAL = '.';
                final char HASH = '#';
                SimpleInput sin = new SimpleInput();
                String prn = sin.readLine();
                int index;
                index = prn.indexOf(DECIMAL);
                String integral = prn.substring(index+1);
                String fractional = prn.substring(0,index);

                integral = reverse(integral);
                integral = significantDigits(integral);
                integral = reverse(integral);

                int length = integral.length();
                for (int i=0 ; i<5-length ; i++)integral = integral + HASH;


                fractional = significantDigits(fractional);
                System.out.println(integral + "." + fractional);

        }
        static String significantDigits(String number)
        {
                final char ZERO = '0';
                int index;
                index = number.lastIndexOf(ZERO);
                if(index==number.length()-1)
                {
                        for(int i=0;i<index;index--)
                        {
                                if(index-1 == number.lastIndexOf(ZERO,index-1));
                                else return number.substring(0,index);

                        }
                        return "#####";
                }
                else return number;
        }
        static String reverse(String number)
        {
                if(number.length()==1 && number.compareTo("0")==0)return "#####";
                String r = "";
                int length = number.length();
                for(int i=0;i<length;i++)
                {
                        r=number.charAt(i) + r;
                }
                return r;
        }
}

