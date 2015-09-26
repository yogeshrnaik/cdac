import ncst.pgdst.*;

class Fortune
{
 public static void main(String[] args)
   throws IOException
 {
  SimpleInput  sin  = new SimpleInput();
  SimpleOutput sout = new SimpleOutput();

  sout.writelnString("My name is HAL");
  sout.writelnString("What is yours?");
  String name = sin.readWord();

  sout.writelnString("Hello, "+name+"!");
  sout.writelnString("What is your ID? : ");
  int id = sin.readInt();

  sout.writeString("Welcome to PGDST!\n"+"Your Lucky number is ");
  sout.writelnInt(id%9==0?9:id%9);
 }
}

