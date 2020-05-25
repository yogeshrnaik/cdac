import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.geom.Ellipse2D;

/*
<applet code="C4" width=1015 height=675>
</applet>
*/

class circ
{
	int x,y;
}

class MyFrame extends Frame 
{	String str;
	MyFrame(String title)
	{
		super(title);
		MyAdapter adapter = new MyAdapter(this);
		addWindowListener(adapter);
		Font f = new Font("Serif",Font.BOLD,40);
		setFont(f);
		
	}

	public void paint(Graphics g)
	{	
		if (str.equals("YELLOW"))
			g.setColor(Color.yellow);
		else
			g.setColor(Color.red);
		g.drawString(str+" Wins",140,250);
	}
}

class MyFrame1 extends Frame 
{	
	MyFrame1(String title)
	{
		super(title);
		MyAdapter1 adapter = new MyAdapter1(this);
		addWindowListener(adapter);
		Font f = new Font("Serif",Font.BOLD,40);
		setFont(f);
		
	}

	public void paint(Graphics g)
	{	
		g.setColor(Color.black);
		g.drawString("DRAW",140,250);
	}
}

class MyFrame2 extends Frame 
{	String str;
	MyFrame2(String title)
	{
		super(title);
		MyAdapter2 adapter = new MyAdapter2(this);
		addWindowListener(adapter);
		Font f = new Font("Serif",Font.BOLD,25);
		setFont(f);
		
	}

	public void paint(Graphics g)
	{	
		g.drawString("The column is full. Try another column.",50,80);
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

class MyAdapter1 extends WindowAdapter
{
	MyFrame1 frame;
	public MyAdapter1(MyFrame1 frame)
	{
		this.frame = frame;
	}

	public void windowClosing(WindowEvent we)
	{
		frame.setVisible(false);
	}
}

class MyAdapter2 extends WindowAdapter
{
	MyFrame2 frame;
	public MyAdapter2(MyFrame2 frame)
	{
		this.frame = frame;
	}

	public void windowClosing(WindowEvent we)
	{
		frame.setVisible(false);
	}
}



public class C4 extends Applet implements ActionListener
{
	String m="";
	Button a[] = new Button[7];
	int move=0;
	int count = 0;
	String mat[][] = new String[6][7];String str;
	int temp1[] = {3,0,4,0,5,0,5,1,5,2,5,3};
	int temp2[] = {3,6,4,6,5,6,5,5,5,4,5,3};
	int columns[] = new int[7];
	int count4=0;
	circ c[][] = new circ[6][7]; 
	boolean done = false;
	boolean clicked = false;
	MyFrame f;
	MyFrame1 f1;
	MyFrame2 f2;
	public void init()
	{
		for (int i=0;i<7;i++)
			a[i] = new Button("    Drop here    ");
		
		for (int i=0;i<7;i++)
		{
			a[i] = (Button) add(a[i]);
			a[i].addActionListener(this);
		}

		for (int i=0;i<7;i++)
			columns[i] = 5;

		for (int i=0;i<6;i++)
			for (int j=0;j<7;j++)
				mat[i][j] = "#"; 

		for (int i=0;i<6;i++)
			for (int j=0;j<7;j++)
				c[i][j] = new circ();
		
		f = new MyFrame("Game Over");
		f1 = new MyFrame1("Game Over");
		f2 = new MyFrame2("Error");
	}

	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		for (int i=0;i<7;i++)
			if (ae.getSource()==a[i])
				move = i;
		if (columns[move]<0)
		{
			f2.setSize(500,100);
			f2.setVisible(true);
			clicked = false;
		}	
		else
		{
			clicked = true;
			repaint(c[columns[move]][move].x,c[columns[move]][move].y,80,80);
		}
		
	}
			
	public void paint(Graphics g)
	{
		int i,j,k;
		Graphics2D g2 = (Graphics2D)g;
		
			for (i=0;i<6;i++)
			{	for (j=0;j<7;j++)
				{
					c[i][j].x = 170+100*j;
					c[i][j].y = 80+100*i;
					g.drawOval(c[i][j].x,c[i][j].y,80,80);
				}
			}
		
		
		
		if (clicked)
			{	
				if (count%2==0)
				{	f.str = str = "YELLOW";g.setColor(Color.yellow);}
				else
				{	f.str = str = "RED";g.setColor(Color.red);}
				
				mat[columns[move]][move] = str;
				g.fillOval(c[columns[move]][move].x,c[columns[move]][move].y,80,80);

				
				count++;clicked=false;
				columns[move]--;
				
				for (j=5;j>=0;j--)
					for (k=0;k<4;k++)
					if (mat[j][k]==str && mat[j][k+1]==str && mat[j][k+2]==str && mat[j][k+3]==str)
					{f.setSize(500,500);
					f.setVisible(true);	
					clear();
					}
			
				for (k=0;k<7;k++)
					for (j=0;j<3;j++)
					if (mat[j][k]==str && mat[j+1][k]==str && mat[j+2][k]==str && mat[j+3][k]==str)
					{f.setSize(500,500);
					f.setVisible(true);	
					clear();
					}
		
				for (j=0;j<12;j+=2)
					recurse1(temp1[j],temp1[j+1]);

				for (j=0;j<12;j+=2)
					recurse2(temp2[j],temp2[j+1]);
			}
		if (count==42)
			{
				f1.setSize(500,500);
				f1.setVisible(true);
				clear();
			}
		

	}


	public void recurse1(int x,int y)
	{
		if (mat[x][y]==str)
			count4++;
		else
			count4=0;
		if (count4==4)
		{	
			f.setSize(500,500);
			f.setVisible(true);
			clear();
		}
		
		if (x-1>=0 && y+1<=6)
			recurse1(x-1,y+1);
	}
	
	public void recurse2(int x,int y)
	{
		if (mat[x][y]==str)
			count4++;
		else 
			count4=0;
		if (count4==4)
		{	f.setSize(500,500);
			f.setVisible(true);
			clear();
		}
		
		if (x-1>=0 && y-1>=0)
			recurse2(x-1,y-1);
	}

	public void clear()
	{
		for (int i=0;i<7;i++)
			columns[i] = 5;
		for (int i=0;i<6;i++)
			for (int j=0;j<7;j++)
				mat[i][j] = "#";
		count = count4 = 0;
		done = clicked = false;
		repaint();
	}



}	
