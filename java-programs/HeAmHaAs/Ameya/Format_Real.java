import ncst.pgdst.*;

public class Format_Real
{

public String str;

public static void main(String [] args) throws IOException
{
        SimpleInput sin = new SimpleInput();
        Format_Real prn = new Format_Real();
        prn.str = sin.readLine();
        String fract,intg,GH,J;
        fract ="";
        intg ="";
        GH ="";
        J = "";
        int i=0;
        int strlen = prn.str.length()-1;
        boolean flag = true;
        boolean flagg = true;
        while (flag&&(i<=strlen))
        {
                if(prn.str.charAt(i) == '.')
                {
                        fract = prn.str.substring(0,i);
                        flag = false;
                        }
                        i++;
        }
        i-=1;
        if (i==strlen)  {fract = prn.str;
                         GH = "#####"; flagg = false;
                         }
        else intg = prn.str.substring(i+1);
        i=fract.length()-1;
        flag = true;
        while(flag)
        {
                if (fract.charAt(i)=='0') i--;
                else {
                J += fract.substring(0,i+1);
                flag = false;
                        }
                        }
        
        i=0;
        int cnt = 0;
        while(flagg)
        {
                if (intg.charAt(i) == '0') i++;
                else { GH = intg.substring(i); flagg = false;}
                }
        int len = GH.length()-1;

                for(int j=len+1; j <5; j++)
                GH = GH+"#";
        System.out.println(GH+"."+J);
        }
        }
                



                

        
