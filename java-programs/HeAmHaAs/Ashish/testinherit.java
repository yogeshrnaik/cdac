
import ncst.pgdst.*;

class A
{
        int a ;
        void show(){System.out.println("Foa class A a= "+ a);}
        A(){System.out.println("Class A without parameters");a = 10;}
        A(int i){a = i;System.out.println("Class A with parameters a= "+a);}
}

class B extends A
{       boolean a;
        void show(){System.out.println("This is B");}
        void show(int i){System.out.println("For class B a= "+a);}
        B(){System.out.println("Class B without parameters");}
        B(int i){System.out.println("Class B with parameters");}
}

class testinherit extends B
{    testinherit(int i) { System.out.println("Class testiherit with parameters");}
      testinherit() {System.out.println("class testinherit without parameter ");}
    //  void show() {System.out.println("this is inheritance");}
public static void main(String [] args)
{
        A a = new A();
        A a1 = new B(1);
        B b = new B();
        B b1 = new B(1);
         B  c = new testinherit();
        testinherit c1 = new testinherit(0);
        testinherit x  = new testinherit();
        B h = new B();
//        h = (B) x ;
         x.show();
        System.out.println(a.a + " " + a1.a );
        System.out.println(b.a + " " + b1.a);
        a.show();
        a1.show();
        b.show();
        b1.show();
        ((B)a1).show();
        b.show(1);
        a1= b1;
        b1.a= true;
        ((B)a1).show(1);
        b1.show(0);

        }
        }
