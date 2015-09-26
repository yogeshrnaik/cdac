import ncst.pgdst.*;
public class testing
{
        public int first,second;
        testing(int x, int y)
        {       first = x;
                second =y;
                }
          public static void main(String [] args) throws IOException
          {
                int x,y,n;
                SimpleInput sin = new SimpleInput();
                n=sin.readInt();
                testing [] test = new testing[n];
                for(int i=0; i<n; i++)
                {       x = sin.readInt();
                        y = sin.readInt();
                        test[i] = new testing(x,y);
                        }
                System.out.println(" here is the list");
                for(int j=0;j<n;j++)
                {
                        System.out.println(test[j].first+"  "+test[j].second);
                        }
                        }
                        }



