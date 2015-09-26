//import ncst.pgdst.*;

public class Pemutation
{
        //public char[] list_of_let = new char[26];
        public char [] list_of_let =  {'a','b','c' 
,'d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        public double factrl(int no)
        {
                double fact = 1;
                if (no > 1){
                                for (int i = 2; i <= no; i++)
                                fact = fact * i;
                           }
                //System.out.print(fact+" ");
                return fact;
        }

        public void flushlist(int no)
        {
                char temp;
                for (int i = no; i < 25; i++)
                {
                        temp = list_of_let[i];
                        list_of_let[i] = list_of_let[i+1];
                        list_of_let[i+1] = temp;
                }       //System.out.println(list_of_let[no]);
        }

        public void prnlet(int no1, int no2)
        {
                        //System.out.print(no1+" ");
                        int no3  =  (int)(factrl(no1)/(double)no1);
                        //System.out.println(no3);
                        int no4  =  (no2-1)/no3;
                        //System.out.println(no4);
                        //SimpleOutput sout = new SimpleOutput();
                        //sout.writeChar(list_of_let[no4]);
                        System.out.print(list_of_let[no4]);
                        flushlist(no4);
                        int no5 = no1 -1;
                        int no6 = no2 - (no4 * no3) ;
                        if (no1 > 1) { prnlet(no5 , no6); }
        }
        public static void main(String[] args)
        {
                int No1,No2;
                //SimpleInput sin = new SimpleInput();
                //No1 = sin.readInt();
                //No2 = sin.readInt();
                Pemutation obj = new Pemutation();
                No1 = 4;  No2 = 8;
                double testfact = obj.factrl(No1);
                if ((No1 > 0) && (testfact >= (double)No2))
                {       obj.prnlet(No1,No2);
                }
        }
}

