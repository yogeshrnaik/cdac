import ncst.pgdst.*;
public class TriangleType
{
        public static void main(String[] args) throws IOException
        {
                Triangle ABC = new Triangle();
                ABC.getSides();
                System.out.println(ABC.getType());
        } // main
} // Class TriangleType

class Triangle
{
        SimpleInput  sin  = new SimpleInput();
        SimpleOutput sout = new SimpleOutput();
        
        double a, b, c;
        public Triangle()
        {
                a=b=c=0.0;
        }
        public void getSides() throws IOException
        {
                this.a = sin.readDouble();
                this.b = sin.readDouble();
                this.c = sin.readDouble();
        }
        public String getType()
        {
                if ((a>0)&&(b>0)&&(c>0))
                {
                        if ((a+b>c)&&(b+c>a)&&(a+c>b))
                        {
                             if ((a==b)&&(b==c)&&(c==a))
                                     return("equilateral");
                             if ((Math.abs(a*a+b*b-c*c) <= 0.000001)
                               ||(Math.abs(b*b+c*c-a*a) <= 0.000001)
                               ||(Math.abs(c*c+a*a-b*b) <= 0.000001))
                                     return("right-angled");
                             if ((a==b)||(b==c)||(c==a))
                                     return("isoscales");
                             return("notspecial");
                        }
                }
                return("invalid");
        }
} // End of Triangle Class
