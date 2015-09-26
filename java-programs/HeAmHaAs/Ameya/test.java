import ncst.pgdst.*;
class test
{
        public static void main(String [] args)
        {
                SimpleInput sin = new SimpleInput();
                try {

                
                int temp = sin.readInt();
                System.out.println(temp);
                }
                catch (Exception e)
                {
                try {
                String tem = sin.readLine();
                  System.out.println(tem);
                 }
                catch (Exception e1){}
                }
                }
                   }
