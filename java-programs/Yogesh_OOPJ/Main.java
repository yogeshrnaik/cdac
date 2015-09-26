import ncst.pgdst.*;
public class Main
{
 public static void main(String[] args) throws IOException
 {
    SimpleInput sin = new SimpleInput();
    int a = sin.readInt();
    if (a==0)
    { System.out.println("A = 0");
      return;
    }
    System.out.println("A != 0");
 }
}
