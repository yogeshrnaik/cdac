import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
<applet code="Butt" width=600 height=600>
</applet>
*/


class MyFrame extends Frame 
{
	MyFrame(String title)
	{
		super(title);
		MyAdapter adapter = new MyAdapter(this);
		addWindowListener(adapter);
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}

	public void paint(Graphics g)
	{
		g.drawString("This is in frame window",10,70);
	}
}

class MyAdapter extends WindowAdapter
{
	MyFrame frame;
	public MyAdapter(MyFrame frame)
	{
		this.frame = frame;
	}

	public void windowClosing(WindowEvent we)
	{
		frame.setVisible(false);
	}
}











public class Butt extends Applet implements ActionListener
{
	String m="";
	Button a[] = new Button[7];
	Button x;
	Frame f;
	public void init()
	{
		for (int i=0;i<7;i++)
			a[i] = new Button("Drop here");		
		x = new Button("Positioned");		
		for (int i=0;i<7;i++)
		{
			a[i] = (Button) add(a[i]);
			a[i].addActionListener(this);
		}
		x = (Button) add(x,50,50,10,10);
		x.addActionListener(this);
		f = new MyFrame("Frame");
		
	}

	public void actionPerformed(ActionEvent ae)
	{	
		String str = ae.getActionCommand();
		for (int i=0;i<7;i++)
			if (ae.getSource()==a[i])
			{	m = "Column"+i;
				
				f.setSize(250,250);
				f.setVisible(true);
				
			}
		repaint();
	}
			
	public void paint(Graphics g)
	{
		g.drawString(m,6,100);
	}
}	
