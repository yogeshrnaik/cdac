abstract class A
{
public  void show(){System.out.println("This is a");}
}
class B extends A
{
public void show(){System.out.println("This is b");}
}
class C extends A
{
public void show(){System.out.println("This is c");}
}
public class abstracttest
{
public void show(A a){a.show();}
public static void main(String [] args)
{
B b = new B();
C c = new C();
abstracttest abs = new abstracttest();
abs.show(b);
abs.show(c);
}
}


