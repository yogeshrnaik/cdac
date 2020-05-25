import ncst.pgdst.*;


class Scan
{
char Char;
Scan(){}
Scan(char Char){this.Char = Char;}
}
class Dot extends Scan
{
Dot(){this.Char = '.';}

}
class Star extends Scan
{
Star(){this.Char = '*';}
}

public class Patternmatch
{
static Scan scan[];
static String master =new String();
static int len=0;
static int mastlen,scanlen;
        public static void main(String [] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                sin.skipWhite();
                master = sin.readLine();
                //sin.skipWhite();
                String temp = sin.readLine();
                //sin.skipWhite();
                scan = new Scan[temp.length()];
                for(int i=0; i<temp.length(); i++)
                {
                        if(temp.charAt(i)=='.') scan[i] = new Dot();
                        else if(temp.charAt(i)=='*') scan[i] = new Star();
                        else scan[i] = new Scan(temp.charAt(i));
                }
                //for(int i=0;i<temp.length(); i++)
                //System.out.println(scan[i].Char);        
                mastlen = master.length(); 
                scanlen = scan.length;
                for(int j=0; j<mastlen; j++)
                {       int tempct = match(0,j,0);
                        if(tempct>len) len = tempct;
                }
                System.out.println(len);
        }
        public static int match(int i, int j, int count)
        {
                //if ((i==scanlen)&&(j==mastlen)) return(count);
                if((i==scanlen)||(j==mastlen)) return(count);
                else
                if (scan[i] instanceof Dot) return(match(i+1,j+1,count+1));
                else if(!(scan[i] instanceof Star))
                        {       if(scan[i].Char == master.charAt(j))
                                return(match(i+1,j+1,count+1));
                                else return(0);
                        }
                else {  if(scan[i-1] instanceof Dot)
                        {
                                int ct =0;
                                for(int k=(mastlen-1); k>=j; k--)
                                      {
                                        int t=0;
                                        if(master.charAt(k) == scan[i+1].Char)
                                        t=match(i+1,k,k);
                                        if(ct < t) ct = t;
                                       }
                                 return(ct);
                         }
                       else
                       {
                        if(master.charAt(j)==scan[i+1].Char)
                                return(match(i+1,j,count));
                        else if(master.charAt(j) == scan[i-1].Char)
                                return (match(i,j+1,count+1));
                        else return(0);
                        }
                     }
        }

}
