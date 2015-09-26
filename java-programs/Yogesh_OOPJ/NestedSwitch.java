import ncst.pgdst.*;
public class NestedSwitch
{
 public static void main (String[] args) throws IOException
 {
  SimpleInput sin = new SimpleInput();
  System.out.print("Enter count 1 & count 2 : ");
  int count1 = sin.readInt();
  int count2 = sin.readInt();
  switch(count1)
  {
    case 1 : System.out.println("Outer switch case 1");
             switch(count2)
             {
               case 1  : System.out.println("Inner switch case 1");
                         break;
               case 2  : System.out.println("Inner switch case 2");
                         break;
               default : System.out.println("Inner switch Default");
             }
             break;
    case 2 : System.out.println("Outer switch case 2");
             switch(count2)
             {
               case 1  : System.out.println("Inner switch case 1");
                         break;
               case 2  : System.out.println("Inner switch case 2");
                         break;
               default : System.out.println("Inner switch Default");
             }
             break;
    default : System.out.println("Outer switch Default");
  }
 }
}
