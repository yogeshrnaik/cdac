import ncst.pgdst.*;
import java.io.*;
public class FileCopy
{
  public static void main (String[] args) throws Exception
  {
    File inf = new File (args[0]);
    File outf = new File (args[1]);
    FileReader in  = new FileReader (inf);
    FileWriter out = new FileWriter (outf);

    int c;
    while ((c = in.read()) != -1)
      out.write(c);
    in.close();
    out.close();

  } // main

} // FileCopy
