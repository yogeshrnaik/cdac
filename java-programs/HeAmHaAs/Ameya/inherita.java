abstract class inheritb
{     inheritb(){System.out.println("B ");}
protected abstract void show();//{ System.out.println("This is B");}
}
class inheritc extends inheritb
{       inheritc(){ System.out.println("C ");}
public void show(){ System.out.println("This is C");}
public void showmod(){//super.show();
show();}
}
public class inherita extends inheritc
{        //inherita(){}
public static void main(String [] args)
        {
                inheritc c1 = new inheritc();
                //inheritb b1 = new inheritb();
                inherita t = new inherita();
                inherita a = new inherita();
                a.show();
                a.showmod();
                inheritc c = new inherita();
                c.show();
                c.showmod();
                inheritb b = a;
               // b.show();
                //(inheritb) c.show;
                if (b instanceof inherita) ((inherita) b).showmod();
                }
public void show(){System.out.println("Through A");super.show();
}
public void showmod(){System.out.println("Through A");super.showmod();
}

                }
