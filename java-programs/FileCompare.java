import ncst.pgdst.*;
import java.io.*;
public class FileCompare
{
  public static void main (String[] args) throws Exception
  {
    File inf1 = new File (args[0]);
    File inf2 = new File (args[1]);
    FileReader in1  = new FileReader (inf1);
    FileReader in2  = new FileReader (inf2);

    int c1 = 0, c2 = 0;
    boolean flag = true;
    while (flag)
    {
      c1 = in1.read();
      c2 = in2.read();
//      System.out.println("C1 = "+c1+" C2 = "+c2);
      if ((c1==-1 && c2!=-1) || (c1!=-1 && c2==-1) || c1!=c2)
      {
          System.out.println ("NO");
//        System.out.print("The contents of the files "+args[0]);
//        System.out.println(" and "+args[1]+ " are not same.");
        flag = false;
      }
      if (c1 == -1 && c2 == -1) break;
    }
    if (flag)
    {
        System.out.println ("YES");
//      System.out.print("The contents of the files "+args[0]);
//      System.out.println(" and "+args[1]+ " are same.");
    }
    in1.close();
    in2.close();

  } // main

} // FileCompare
