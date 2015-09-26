import ncst.pgdst.*;
class Prime
{
  public static void main(String [] args) throws IOException
  {
    int max;        // Max num upto which primes have to be generated
    SimpleInput sin = new SimpleInput();
    max = sin.readInt();
    Prime prime = new Prime();
    prime.computePrime(max);
  }
  public void computePrime(int max)
  {
    int [] intArr = new int[max+1];
    //Array of integers. For convenience we are making
    //an array of max + 1 instead of max

    SimpleOutput sout = new SimpleOutput();
    for(int i = 2; i <= max; i++)
    {
        if (intArr[i] != 1)
        {
           System.out.print(i+"\t");
           //sout.writelnInt(i);
           for(int j=2*i; j <= max; j+=i)
               intArr[j] = 1;
        }
        //System.out.println();
    }
  }
}
