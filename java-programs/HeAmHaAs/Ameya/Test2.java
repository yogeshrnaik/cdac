import ncst.pgdst.*; 
class TestA{ 
public static void main(String[] args) throws IOException 
{ 
SimpleInput sin = new SimpleInput(); 
SimpleOutput sout = new SimpleOutput(); 
sout.writelnString(" I am in TestA class"); 

Test1 j = new Test1(); 
Test2 i = new Test2(); 

j.m1(); 
i.m1(); 
} 
} 

class Test1 
{ 
public static void main(String[] args) throws IOException 
{ 
SimpleInput sin = new SimpleInput(); 
SimpleOutput sout = new SimpleOutput(); 
sout.writelnString("I am in main() of Test1"); 
} 

static int x = 1; 
static int y = 2; 
public static void m1() 
{ 

SimpleOutput sout = new SimpleOutput(); 
sout.writelnString("I am in m1() of Test1"); 
} 
} 

class Test2 extends Test1 
{ 
public static void main(String[] args) throws IOException 
{ 
SimpleInput sin = new SimpleInput(); 
SimpleOutput sout = new SimpleOutput(); 
sout.writelnString("I am in main() of Test2"); 

} 

static int p = 10; 
static int q = 20; 

public static void m1() 
{ 

SimpleOutput sout = new SimpleOutput(); 
sout.writelnString("I am in m1() of Test2"); 
} 

} 