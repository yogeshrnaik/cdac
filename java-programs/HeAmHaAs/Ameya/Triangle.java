import ncst.pgdst.*;

public class Triangle
{
        public double [] side = new double[3];

        Triangle(double a, double b, double c)
        {
                side[0] = a;  side[1] = b; side[2] = c;
                sort();
        }

        public void sort()
        {
                double temp;
                for(int i = 0; i<2 ; i++)
                        for(int j=i+1; j <=2; j++)
                                if (side[i] > side[j])
                                {
                                        temp = side[i];
                                        side[i] = side[j];
                                        side[j] = temp;
                                 }
        }

        public int Check()
        {
                if((side[0] + side[1]) > side[2]) return 1;
                else return 0;
        }

        public int Equilat()
        {
                if(side[0] == side[2]) return 1;
                else return 0;
        }

        public int Right_Angle()
        {
                double hyp = side[2]*side[2];
                double side_add = side[0]*side[0] + side[1]*side[1];
                if((hyp - side_add) > (-0.000001) && (hyp - side_add) < (0.000001))
                        return 1;
                else return 0;
        }

        public void Isosceles()
        {
                if ((side[0] == side[1]) || (side[1] == side[2]))
                System.out.print("isosceles");
                else System.out.print("notspecial");
        }

        public static void main (String[] args) throws IOException
        {
                double a,b,c;
                SimpleInput sin = new SimpleInput();
                String str;
                a = Double.valueOf(sin.readWord()).doubleValue();
                b = Double.valueOf(sin.readWord()).doubleValue();
                c = Double.valueOf(sin.readWord()).doubleValue();
                Triangle triangle = new Triangle(a,b,c);
                if (triangle.Check() > 0)
                        if(triangle.Equilat() ==0)
                                if(triangle.Right_Angle() ==0)
                                        triangle.Isosceles();
                                else System.out.print("right-angled");
                        else System.out.print("equilateral");
                else System.out.print("invalid");
        }
}


