import java.awt.Rectangle;
import ncst.pgdst.*;

public class MoveRectangle
{
	public static void main(String[] args)
	{
		SimpleOutput sout = new SimpleOutput();
		Rectangle cerealBox = new Rectangle(5,10,20,30);
		cerealBox.translate(15,25);
		sout.writelnString(cerealBox);
		//System.out.println(cerealBox);
	}
}