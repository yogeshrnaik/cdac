class cara
{
cara(){System.out.println("This is car a");}
public static void main(){ cara d = new cars();}
}
class cars extends cara
{
cars(){System.out.println("This is car b");
//super.this;
}
public static void main(String [] args)
{
 cara a = new cara();
 cara ab = new cars();
 cars b = new cars();
 System.out.println();
 cara.main();
 }}

