import ncst.pgdst.*;
public class CubeRoot
{
 public static void main(String[] args) throws IOException
 {
  SimpleInput sin = new SimpleInput();
  final double ERROR = 0.1;
  System.out.print("Enter a Positive Real Number : ");
  double num = sin.readDouble();
  double g1, g2;
  g1 = g2 = num/3;

  while (Math.abs(g2*g2*g2 - num) > ERROR)
  {
    g1 = g2;
    g2 = (g1 + num/g1)/3;
    System.out.println("g1 = "+g1 + " g2 = " +g2);
  }
  double cuberoot = g2;
  System.out.println("The Square Root of "+ num +" = "+ cuberoot);
 }
}
