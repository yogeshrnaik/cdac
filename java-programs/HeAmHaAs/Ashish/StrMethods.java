import ncst.pgdst.*;
public class StrMethods
{
 public static void main (String[] args) throws IOException
 {
  SimpleInput sin = new SimpleInput();
  String ip1 = "Yogesh R. Naik";
  String ip2 = "Ashish A. Deshpande";
  System.out.println(ip1.length());
  System.out.println(ip2.length());
  String op = ip1.concat(" "+ ip2);
  System.out.println(op);

  System.out.println("Starts = "+ip1.startsWith("Yogesh"));
  System.out.println("Starts = "+ip2.startsWith("Ashish"));
  System.out.println("Ends = "+ip1.endsWith("Naik"));
  System.out.println("Ends = "+ip2.endsWith("mande"));

  String ip3 = "Yogesh";
  System.out.println ("Equals = "+ip3.equals(ip1.substring(0, 6)));
  String ip4 = "yogesh";
  System.out.println ("Equals = "+ip4.equalsIgnoreCase(ip1.substring(0, 6)));

  int first = ip2.indexOf("sh");
  System.out.println("First occurence of 'sh' = " + first);

  int last = ip2.lastIndexOf("sh");
  System.out.println("Last occurence of 'sh' = " + last);

  System.out.println("New ip2 = " + ip2.replace('A', 'a'));
  System.out.println(ip2);
 } // main
} // class
