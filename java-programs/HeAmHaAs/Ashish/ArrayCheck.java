import ncst.pgdst.*;
public class ArrayCheck
{
        public static void main(String[] args) throws IOException
        {
                int[] A = new int[5];
                for (int i=0; i<3; i++)
                        A[i] = i+1;

                for (int i=0; i<A.length; i++)
                        System.out.print(A[i] + " ");
        }
}
