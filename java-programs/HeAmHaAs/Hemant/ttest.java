import ncst.pgdst.*;

public class ttest
{
	public static void main(String[] args) throws IOException
	{
		Triangle ABC = new Triangle();
		ABC.getSides();
		ABC.sort();
		ABC.check(); 
	}
}

class Triangle
{
	private double sides[] = new double[3];

	Triangle()
	{
		for (int i=0;i<3;i++)
			sides[i] = 0.0;
	}
	
	public void getSides() throws IOException
	{
		
		SimpleInput sin = new SimpleInput();
		for (int i=0;i<3;i++)
			sides[i] = sin.readDouble();
	}

	public void sort()
	{
		for (int i=0;i<2;i++)
			for (int j=i+1;j<3;j++)
				if (sides[i]>sides[j])
				{
					double temp;
					temp = sides[i];
					sides[i] = sides[j];
					sides[j] = temp;
				}
		//System.out.println("Sorted:"+sides[0]+" "+sides[1]+" "+sides[2]);
	}

	public void check()
	{
		if (sides[2] >= (sides[0] + sides[1]))
			System.out.println("invalid");
		else if (Math.abs((sides[2]*sides[2])-(sides[0]*sides[0] + sides[1]*sides[1]))<=0.000001)
			System.out.println("right-angled");
		else if (sides[0]==sides[1] && sides[1]==sides[2])
			System.out.println("equilateral");
		else if (sides[0]==sides[1] || sides[1]==sides[2])
			System.out.println("isosceles");
		else
			System.out.println("notspecial");
		System.out.println();
		//System.out.println(Math.abs((sides[2]*sides[2])-(sides[0]*sides[0] + sides[1]*sides[1]))); 
	}
}
		