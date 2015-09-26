import ncst.pgdst.*;
public class ExceptionTest1
{
  public static void main (String[] args) throws Exception
  {
    try
    {
      ExceptionTest1.throwException();
    }
    catch (Exception e)
    {
      System.out.println ("In the catch block of Exception");
    }
    finally
    {
      System.out.println ("In the finally block of main");
    }
  } // main
  public static void throwException() throws Exception
  {
    try
    {  throw new Exception();
    }
    catch (IOException e)
    {
      System.out.println ("In the catch block of IOException");
    }
    finally
    {
      System.out.println ("In the finally block of throwException");      
    }

  }

} // ExceptionTest1
