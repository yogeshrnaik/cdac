import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import javax.swing.JOptionPane;
import java.awt.geom.Line2D;
import java.lang.Math;
public class Clock extends Applet
{
	public void paint(Graphics g)
	{
		String input = JOptionPane.showInputDialog("Enter the time in hh:mm format:");	
		int hour = Integer.parseInt(input.substring(0,2));
		int min = Integer.parseInt(input.substring(3,5));
		double minX,minY,hourX,hourY;int r;
		Graphics2D g2 = (Graphics2D)g;
		Ellipse2D.Double circle = new Ellipse2D.Double(0,0,500,500);
		g2.draw(circle);
		r = 250;
		int centreX,centreY;
		centreX = centreY = r;
		double angleMin = (((min / 60.0) * 360.0)-90)*(-1);
		System.out.print(min+" "+angleMin);		
	
		angleMin = angleMin*Math.PI/180;
		r = 225;
		minX = centreX + r*Math.cos(angleMin);
		minY = centreY - r*Math.sin(angleMin);
		Line2D.Double minHand = new Line2D.Double(centreX,centreY,minX,minY);
				

		if (hour==12)		
			hour=0;
		double angleHour = (((hour / 12.0) * 360)-90)*(-1);
		
		double angleAdded = (min / 12.0)*(-6);
		System.out.print(angleAdded);
		
		angleHour = (angleHour+angleAdded)*Math.PI/180;
		r = 150;
		hourX = centreX + r*Math.cos(angleHour);
		hourY = centreY - r*Math.sin(angleHour);
		Line2D.Double hourHand = new Line2D.Double(centreX,centreY,hourX,hourY);

		g2.setColor(Color.red);


		g2.draw(hourHand);
		g2.draw(minHand);
	}
}
		