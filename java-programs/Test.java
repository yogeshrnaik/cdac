import ncst.pgdst.*;
public class test
{
        public static void main (String[] args) throws IOException
        {
                Inner i = new Inner();
                i.display();
        }
}
private class Inner
{
        int no;
        Inner()
        {
                no = 0;
        }
        void display()
        {
                System.out.println ("No = "+no);
        }
}
