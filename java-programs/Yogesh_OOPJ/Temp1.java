class Temp1
{
   static int z (int y)
   {
     return (y < 2) ? y : z(y-1) + z(y-2);
   }
public static void main(String args[])
{
        System.out.println(z(3));
}
                                                                                                                        }

