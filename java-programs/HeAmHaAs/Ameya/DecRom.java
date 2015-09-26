import ncst.pgdst.*;

class Convert
{       
        public int I = 1;
        public int V = 5;
        public int X = 10;
        public int  L = 50;
        public int C = 100;
        public int D = 500;
        public int M = 1000;

        Convert(int num)
        {
                int temp = num / M;
                for(int i=0 ; i <temp; i++) System.out.print("M");
                num = num - temp*M;
                if (num >= (M-C)) {      System.out.print("CM");
                                        num = num - (M - C);
                                 }
                temp = num / D;
                for(int i=0 ; i <temp; i++) System.out.print("D");
                num = num - temp*D;
                if (num >= (D-C)) {      System.out.print("CD");
                                        num = num - (D - C);
                                 }
                temp = num / C;
                for(int i=0 ; i <temp; i++) System.out.print("C");
                num = num - temp*C;
                if (num >= (C - X)){     System.out.print("XC");
                                        num = num - (C - X);
                                  }
                if (num >= L) {         System.out.print("L");
                                        num = num - L;
                                        }
                temp = num / X;
                for(int i=0 ; i <temp; i++) System.out.print("X");
                num = num - temp*X;
                if (num >= (X -I)){     System.out.print("IX");
                                        num = num - (X - I);
                                  }
                if (num >= V)     {     System.out.print("V");
                                        num = num - V;
                                  }
                if (num >= (V-I)) {     System.out.print("IV");
                                        num = num -(V - I);
                                        }
                for(int i =0 ; i<num; i++) System.out.print("I");
                }

        Convert(String str)
        {
                int temp = 0;
                char t;
                for (int i =0; i <str.length(); i++)
                {
                        t = str.charAt(i);
                        if ((t == 'M') || (t == 'D'))
                        {
                                temp = temp + ((t == 'M') ? M :D);
                                temp -= (((temp % D) == 0) ? 0 : 2*C);
                                }
                        if ((t == 'C') || (t == 'L'))
                        {
                                temp += ((t == 'C') ?  C : L);
                                temp -= (((temp % L) == 0) ? 0 : 2*X);
                                }
                        if ((t == 'X') || (t == 'V'))
                        {
                                temp += ((t == 'X') ? X : V);
                                temp -= (((temp % V) == 0) ? 0 : 2*I);
                                }
                        if (t == 'I') temp += I;
                        }
                        System.out.print(temp);
                        }
         }
public class DecRom
{



        public static void main(String [] args) throws IOException

        {
                SimpleInput sin = new SimpleInput();
                int no;
                String nums[];
                //try {
                        no = sin.readInt();
                        
                
                String [] nu = new String[no];
                String tt;
                for (int i =0; i<no; i++)
                {       sin.skipWhite();
                        tt = sin.readLine();
                        nu[i] = tt;
                        }
                nums = nu;
                   //     }
                //catch (Exception e){}             
                
                for (int i=0; i<nums.length; i++)
                {
                try {
                        int temp = Integer.valueOf(nums[i]).intValue();
                        Convert tem = new Convert(temp);
                        System.out.println();
                        }
                catch (Exception e)
                {       String ttm = nums[i];
                        Convert tm = new Convert(ttm);
                        System.out.println();
                        }
                        }
                }
                                           
}
