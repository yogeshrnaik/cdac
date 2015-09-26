import ncst.pgdst.*;
public class ExceptionTest2
{
  static void procA() throws Exception
  {
    try
    {
      System.out.println("Inside procA");
      throw new RuntimeException("demo");
    }
    finally
    { System.out.println("procA's finally");
    }
  } // procA

  static void procB() throws Exception
  {
    try
    {
      System.out.println("Inside procB");
      return;
    }
    finally
    {  System.out.println("procB's finally");
    }
  } // procB

  static void procC() throws Exception
  {
    try
    {  System.out.println("Inside procC");
    }
    finally
    {  System.out.println("procC's finally");
    }
  } // procC

  public static void main (String[] args) throws Exception
  {
    try
    {  procA();
    }
    catch (Exception e)
    {  System.out.println("Exception caught");
    }
    finally
    {  System.out.println("main's finally");
    }
    procB();
    procC();
  } // main

} // ExceptionTest2
