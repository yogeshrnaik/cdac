import ncst.pgdst.*;
import java.lang.*;

public class LineCrossing{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int v = sin.readInt();
		int h = sin.readInt();
		
		sin.skipWhite();
		Line[] line = new Line[h + v];
		for(int i = 0 ; i < h + v ; i++)
		{
			line[i] = new Line();
			line[i].readLine(sin,(i >= h));
//			line[i].printLine();
		}
		int noOfIntersection = 0;
		
		for(int i = 0 ; i < h ; i++)
		{
			for(int j = 0 ; j < v ; j++)
			{
				if(line[v + i].intersectionRefH(line[j]) == true)noOfIntersection++;
			}
		}
		System.out.println(noOfIntersection);
		
	}
	
}
class Line{
	public void readLine(SimpleInput sin,boolean h) throws IOException
	{
		sin.skipWhite();
		int first = sin.readInt();
		int second = sin.readInt();
		int third = sin.readInt();
		int temp;
		if(second > third)
		{
			temp = third;
			third = second;
			second = temp;
		}
		if(h == false)
		{
			x1 = first;
			x2 = first;
			y1 = second;
			y2 = third;
		}
		else
		{
			y1 = first;
			y2 = first;
			x1 = second;
			x2 = third;
		}
	}
	public boolean intersectionRefH(Line line)
	{
		if( (x1 <= line.x1) && (x2 >= line.x2) && (y1 >= line.y1) && (y2 <= line.y2) )return true;
		else return false;
	}
	public void printLine()
	{
		System.out.println(" x1 = " + x1 + " x2 = " + x2 + " y1 = " + y1 + " y2 = " + y2);
	}
	int x1;
	int x2;
	int y1;
	int y2;
}
