import ncst.pgdst.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Font;
import javax.swing.JOptionPane;
public class RectangleApplet extends Applet
{
	public void paint(Graphics g) 
	{	
		String input = JOptionPane.showInputDialog("Enter the dimension:");
		int n = Integer.parseInt(input);
		Graphics2D g2 = (Graphics2D)g;
		Rectangle box = new Rectangle(n,n,200,200);
		g2.draw(box);
		Ellipse2D.Double egg = new Ellipse2D.Double(n,n,200,200);
		g2.draw(egg);
		Color magenta = new Color(1.0F,0.0F,1.0F);
		g2.setColor(Color.red);
		g2.fill(box);
		g2.setColor(Color.blue);
		g2.fill(egg);
		g2.setColor(Color.red);
		Font hugefont = new Font("Helvetica",Font.BOLD,36);
		g2.setFont(hugefont);
		g2.drawString("APPLET",125,212);
	}
}