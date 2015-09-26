import ncst.pgdst.*;
public class RandomNumbers
{
 public static void main(String[] args) throws IOException
 {
   SimpleInput sin = new SimpleInput();
   final int A = 109;
   final int B = 853;
   final int M = 4096;
   final int COLUMN_WIDTH = 8;
   final int SIZE = 4096;
   int[] RandomNum = new int[SIZE];

   for (int i=0; i<SIZE; i++)
     RandomNum[i] = (A * (i+1) + B) % M;
   int count=0;

   for (int i=0; i<SIZE; i++)
   {
     String output = RandomNum[i]+ " ";
     while (output.length() < COLUMN_WIDTH)
        output += " ";
     System.out.print(output);
     count++;
     if (count==10)
     {  System.out.println();
        count = 0;
     }
   }
 }
}

