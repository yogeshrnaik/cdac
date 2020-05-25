import ncst.pgdst.*;
public class ArraySort
{
        public static void main (String[] args) throws IOException
        {
             SimpleInput sin = new SimpleInput();
             System.out.print("Enter the no. of elements : ");
             int num = sin.readInt();

             int[] A = new int[num];

             System.out.println ("Enter the elements of the array below : ");

             // Read Array
             for (int i=0; i<num; i++)
                  A[i] = sin.readInt();

             // sort the array in ascending order
             for (int i=0; i<num-1; i++)
             {
                for (int j=i+1; j<num; j++)
                {
                    if (A[i] > A[j])
                    {
                        int temp = A[i];
                        A[i] = A[j];
                        A[j] = temp;
                    }
                }
             }
             // print the new array
             System.out.println("Array elements in ascending order are : ");
             for (int i=0; i<num; i++)
                System.out.print (A[i] + " ");
             System.out.println();

             // sort the array in descending order
             for (int i=0; i<num-1; i++)
             {
                for (int j=i+1; j<num; j++)
                {
                    if (A[i] < A[j])
                    {
                        int temp = A[i];
                        A[i] = A[j];
                        A[j] = temp;
                    }
                }
             }   
             // print the new array
             System.out.println("Array elements in descending order are : ");
             for (int i=0; i<num; i++)
                System.out.print (A[i] + " ");
             System.out.println();
        } // main
} // Array Sort
