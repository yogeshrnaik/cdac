import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.geom.Ellipse2D;
/*
<applet code="ConnectFour" width=1015 height=675>
</applet>
*/
class circ
{
	int x,y;
}
public class ConnectFour extends Applet implements ActionListener
{
	String m="";
	Button a[] = new Button[7];
	int move=1;
	int count = 0;
	String mat[][] = new String[6][7];String str;
	int temp1[] = {3,0,4,0,5,0,5,1,5,2,5,3};
	int temp2[] = {3,6,4,6,5,6,5,5,5,4,5,3};
	int columns[] = new int[7];
	//Ellipse2D.Double circ[][] = new Ellipse2D.Double[6][7];
	circ c[][] = new circ[6][7]; 
	boolean done = false;
	boolean clicked = false;
	
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

		
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		for (int i=0;i<7;i++)
			if (ae.getSource()==a[i])
				move = i;
		clicked = true;
		repaint();
		
	}
			
	public void paint(Graphics g)
	{
		int i,j;
		Graphics2D g2 = (Graphics2D)g;
		
			for (i=0;i<6;i++)
			{	for (j=0;j<7;j++)
				{
					c[i][j].x = 170+100*j;
					c[i][j].y = 80+100*i;
					g2.drawOval(c[i][j].x,c[i][j].y,80,80);
				}
			}
		
		
		
		if (clicked)
			{
				if (count%2==0)
					str = "YELLOW";
				else
					str = "RED";
				
				mat[columns[move]][move] = str;
				//System.out.println("mat["+columns[move]+"]["+move+"]="+str);
				/*for (i=0;i<6;i++)
					for (j=0;j<7;j++)			
					{	if (mat[i][j].equals("YELLOW"))
						{	//System.out.println(i+"yellow"+j);
							g2.setColor(Color.yellow);
							g2.fill(circ[columns[move]][move]);
						}
						else if (mat[i][j].equals("RED"))
						{	//System.out.println(i+"red"+j);
							g2.setColor(Color.red);
							g2.fill(circ[columns[move]][move]);
						}
						//else System.out.print(" *");
					}*/
				/*for (i=0;i<6;i++)
				{	for (j=0;j<7;j++)			
						System.out.print(mat[i][j].charAt(0)+" ");
					System.out.println();
				}*/

				
				count++;clicked=false;
				columns[move]--;
				System.out.println("count:"+count);
			}
		

	}
}	
