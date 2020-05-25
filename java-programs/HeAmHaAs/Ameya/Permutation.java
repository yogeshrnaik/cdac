import ncst.pgdst.*;

class Permutation
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
                }
        }

        public void prnlet(int no1, int no2)
        {

                        int no3  =  (int)(factrl(no1)/no1);
                        int no4  =  (no2-1)/no3;
                        //SimpleOutput sout = new SimpleOutput();
                        //sout.writeChar(list_of_let[no4]);
                        System.out.print(list_of_let[no4]);
                        flushlist(no4);
                        int no5 = no1 -1;
                        int no6 = no2 - (no4 * no3) ;
                        if (no1 > 1) { prnlet(no5 , no6); }
        }
        public static void main(String[] args)    throws IOException
        {
                int No1,No2;
                SimpleInput sin = new SimpleInput();
                No1 = sin.readInt();
                No2 = sin.readInt();
                Permutation obj = new Permutation();
                double testfact = obj.factrl(No1);
                if ((No1 > 0) && (testfact >= (double)No2))
                {       obj.prnlet(No1,No2);
                }
        }
}

