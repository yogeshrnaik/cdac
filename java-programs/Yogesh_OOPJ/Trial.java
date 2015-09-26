import ncst.pgdst.*;
public class Trial
{
     public static void main (String[] args) throws IOException
     {
        SimpleInput sin = new SimpleInput();
        SimpleOutput sout = new SimpleOutput();
//      sout.writeln("Input a Double No. : ");
        sout.writeString("Input a Double No. : ");
        sout.flush();
        double num = sin.readDouble();
        double n = num-(int)num;
        sout.writelnString("Output = "+n);
//      sout.writelnDouble(n);
     }
}
