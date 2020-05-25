import ncst.pgdst.*;


public class Triangle{
        public static void main(String args[]) throws IOException
        {
                double[] side=new double[3];
                SimpleInput sin = new SimpleInput();
                side[0]=sin.readDouble();
                side[1]=sin.readDouble();
                side[2]=sin.readDouble();
                Tri triangle1=new Tri(side[0],side[1],side[2]);
                triangle1.sort();
                System.out.println(triangle1.getTriangle());
                System.out.println();

        }
}
class Tri{
        public String getTriangle()
        {
                if(side[2]+side[1]-side[0]<=0.000001)return "invalid";
                else if(side[0]==side[1] && side[1]==side[2]) return "equilateral";
                else if(((side[0]*side[0]-(side[1]*side[1]+side[2]*side[2]))<=0.000001) &&
                        ((side[0]*side[0]-(side[1]*side[1]+side[2]*side[2]))>=-0.000001)) return "right-angled";
                else if(side[0]==side[1] || side[1]==side[2]) return "isosceles";
                else return "notspecial";
        }
        public void sort()
        {
                double max;
                if(side[1] > side[2]) max = side[1];
                else
                {
                        max = side[2];
                        side[2] = side[1];
                        side[1] = max;
                }
                if(side[0] >= max) max = side[0];
                else
                {
                        if( side[0] >= side[2] )
                        {
                                max = side[1];
                                side[1] = side[0];
                                side[0]=max;
                        }
                        else
                        {
                                max=side[1];
                                side[1]=side[2];
                                side[2]=side[0];
                                side[0]=max;
                        }
                }

//                System.out.println("\n side[0]="+side[0]+"\n side[1]="+side[1]+"\n side[2]="+side[2]);

        }
        Tri(double side0,double side1,double side2)
        {
                side = new double[3];
                side[0]=side0;
                side[1]=side1;
                side[2]=side2;
        }
        double side[];
}

