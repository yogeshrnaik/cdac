import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UI implements ActionListener
{
		JFrame frame = new JFrame("HTTP Proxy Administrator LOGIN");
		JLabel name = new JLabel("UserName");
		JLabel password = new JLabel("Password");
		JTextField jname = new JTextField(30);
		private static JPasswordField passwordField = new JPasswordField(15);
				
		UI()
		{
			frame.getContentPane().setLayout(null);
			frame.setSize(1400,900);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			passwordField.setEchoChar('*');
			passwordField.setActionCommand("LOGIN");
			passwordField.addActionListener(this);
			name.setBounds(300,200,100,20);
			jname.setBounds(400,200,100,20);
			password.setBounds(300,300,70,20);
			passwordField.setBounds(400,300,70,20);
			frame.getContentPane().add(name);
			frame.getContentPane().add(jname);
			frame.getContentPane().add(password);
			frame.getContentPane().add(passwordField);
			JButton jblogin = new JButton("LOGIN");
			jblogin.addActionListener(this);
			jblogin.setBounds(300,400,90,30);
			frame.getContentPane().add(jblogin);
			JButton jbcancel = new JButton("CANCEL");
			jbcancel.addActionListener(this);
			jbcancel.setBounds(400,400,90,30);
			frame.getContentPane().add(jbcancel);
			frame.show();
		}
		static char[] input = passwordField.getPassword();
		private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
       	char[] correctPassword ={'n','p','g','r','p','0','6'};
        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            for (int i = 0; i < input.length; i++) {
                if (input[i] != correctPassword[i]) {
                    isCorrect = false;
                }
            }
        }

        //Zero out the password.
        //for (int i = 0; i < correctPassword.length; i++) {
          //  correctPassword[i] = 0;
        //}

        return isCorrect;
    }
 public void actionPerformed(ActionEvent ae) {
   
	 String button = ae.getActionCommand();
	 String uname=jname.getText();
	 String password="npgrp06";
	 char[] input = passwordField.getPassword();
	 String inp=new String(input);
		 if(button.equals("LOGIN"))
			{
				if((uname.equals("Administrator")) && (inp.equals(password)))
				{	JOptionPane.showMessageDialog(frame,
                    "Success! You typed the right password.");
					 frame.dispose();
					 Wizard wizard = new Wizard();
		}
				else {
					JOptionPane.showMessageDialog(frame,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
					}
			}
			else if(button.equals("CANCEL"))
					{
						System.exit(0);
					}

  }
	public static void main(String[] args) 
	{
		UI ui = new UI();
	}
}
class Wizard extends JFrame implements ActionListener 
{
	public JFrame frame;
	JButton jbQUIT;
	public Wizard () 
	{	//static JFrame frame1 = null;
		frame = new JFrame("HTTP Proxy Server");
		frame.setSize(800,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane jtpproxy = new JTabbedPane();
		jbQUIT = new JButton("QUIT");
		jbQUIT.addActionListener(this);
		jbQUIT.setBounds (400,630,80,30);
		jtpproxy.setBounds(0,0,800,600);
		jtpproxy.addTab("Configuration", new ConfigurationPanel());
		jtpproxy.addTab("Blocked sites", new BlockedsitesPanel());
		jtpproxy.addTab("Bandwidth", new BandwidthPanel());
		frame.getContentPane().add( jtpproxy );
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(jtpproxy);
		frame.getContentPane().add(jbQUIT);
		frame.show();
    }
	 
	 public void actionPerformed(ActionEvent ae) 
	 {
		 System.out.println("Inside listener for quit");
		 if (ae.getSource().equals(jbQUIT))
		 {
		 
		 frame.dispose();
		 System.exit(0);
		 
		 }
	 }
	 
}




class ConfigurationPanel extends JPanel implements ActionListener
{
		JLabel jcachesizelabel = new JLabel("CACHE-SIZE");
		JLabel jserverportlabel =new JLabel("SERVER-PORT");
		JLabel jusertimeoutlabel = new JLabel("USER-TIME-OUT");
		JLabel jcachepolicylabel =new JLabel("CACHE-POLICY");
		JLabel jserverloglabel= new JLabel("SERVER-LOG PATH");
		JLabel jcacheloglabel = new JLabel("CACHE-LOG PATH");
		JLabel juserloglabel = new JLabel("USER-LOG PATH");
		JLabel jgroupbandwidthlabel = new JLabel("GROUP-BANDWIDTH-DETAILS FILE PATH");
		JLabel jblockedsitedlabel = new JLabel("BLOCKED-SITES FILE PATH");
		JLabel juserdetailslabel =  new JLabel("USER-DETAILS FILE PATH");
		JLabel jcachefilelabel = new JLabel("CACHE FILE");

		JTextField jcachetext =new JTextField(4);
		JTextField jserverport = new JTextField(4);
		JTextField jtimeout = new JTextField(4);
		JTextField jserverlog = new JTextField();
		JTextField jcachelog = new JTextField();
		JTextField juserlog = new JTextField();
		JTextField jgroupbandwidth = new JTextField(); 
		JTextField jblockedsites = new JTextField();
		JTextField juserdetails = new JTextField();
		JTextField jcachefile = new JTextField();

		JRadioButton jradiofifo = new JRadioButton("FIFO");
		JRadioButton jradiolru = new JRadioButton("LRU");
		ButtonGroup bg = new ButtonGroup();
		JButton jok = new JButton("OK");
		JButton jcancel = new JButton("CANCEL");
		Properties properties;

		String cachePolicy;
		final String configFile = "D:\\users\\d0331067\\configuration.txt";



	    public ConfigurationPanel()            //constructor
		{
				setLayout(null);             //layout
				
				int x=150, y=25, ygap=40, xgap=250;
				int a=200, b=20 ,extra=30,c=30;
				jcachesizelabel.setBounds(x,y,a,b);
				jserverportlabel.setBounds(x,y+ygap,a,b);
				jusertimeoutlabel.setBounds(x,y+2*ygap,a,b);
				jcachepolicylabel.setBounds(x,y+3*ygap,a,b);
				jserverloglabel.setBounds(x,y+4*ygap,a,b);
				jcacheloglabel.setBounds(x,y+5*ygap,a,b);
				juserloglabel.setBounds(x,y+6*ygap,a,b);
				jgroupbandwidthlabel.setBounds(x,y+7*ygap,a+extra,b);
				jblockedsitedlabel.setBounds(x,y+8*ygap,a,b);
				juserdetailslabel.setBounds(x,y+9*ygap,a,b);
				jcachefilelabel.setBounds(x,y+10*ygap,a,b);

				add(jcachesizelabel);
				add(jserverportlabel);
				add(jusertimeoutlabel);
				add(jcachepolicylabel);
				add(jserverloglabel);
				add(jcacheloglabel);
				add(juserloglabel);
				add(jgroupbandwidthlabel);
				add(jblockedsitedlabel);
				add(juserdetailslabel);
				add(jcachefilelabel);
				int d=300;
				jcachetext.setBounds(x+xgap,y,d,b);
				jserverport.setBounds(x+xgap,y+ygap,d,b);
				jtimeout.setBounds(x+xgap,y+2*ygap,d,b);
				jserverlog.setBounds(x+xgap,y+4*ygap,d,b);
				jcachelog.setBounds(x+xgap,y+5*ygap,d,b);
				juserlog.setBounds(x+xgap,y+6*ygap,d,b);
				jgroupbandwidth.setBounds(x+xgap,y+7*ygap,d,b);
				jblockedsites.setBounds(x+xgap,y+8*ygap,d,b);
				juserdetails.setBounds(x+xgap,y+9*ygap,d,b);
				jcachefile.setBounds(x+xgap,y+10*ygap,d,b);

				add(jcachetext);
				add(jserverport);
				add(jtimeout);
				add(jserverlog);
				add(jcachelog);
				add(juserlog);
				add(jgroupbandwidth);
				add(jblockedsites);
				add(juserdetails);
				add(jcachefile);
				
				jradiofifo.setBounds(x+xgap,y+3*ygap,a/2,b);
				jradiolru.setBounds(x+xgap+100,y+3*ygap,a/2,b);
				add(jradiofifo);
				add(jradiolru);
				
				bg.add(jradiolru);
				bg.add(jradiofifo);

				jradiolru.addActionListener(this);
				jradiofifo.addActionListener(this);
			
				add(jok);
				//add(jcancel);
				jok.setBounds(x+xgap,y+11*ygap,a/2,c);
				jcancel.setBounds(x+xgap,y+11*ygap-20,a/2,c);
				jok.addActionListener(this);
				//jcancel.addActionListener(this);
				readFromConfig();
				
	  }	 
		
	  public void readFromConfig()
		{
			try
			  {
					File in_file = new File(configFile);
					BufferedInputStream sin = null;
					sin = new BufferedInputStream(new FileInputStream(in_file));

					properties = new Properties();      //use properties class
					properties.load(sin);
					sin.close();
			  }
			catch (FileNotFoundException fe)
			  {
					System.out.println(fe.getMessage());
			  }
			  catch (Exception e)
			{
				System.out.println(e.getMessage());
			
			}

			  jcachetext.setText(properties.getProperty("CACHE_SIZE"));
			  jserverport.setText(properties.getProperty("SERVER_PORT"));
			  jtimeout.setText(properties.getProperty("USER_TIMEOUT"));
			  jserverlog.setText(properties.getProperty("SERVER_LOG"));
			  jcachelog.setText(properties.getProperty("CACHE_LOG"));
			  juserlog.setText(properties.getProperty("USER_LOG"));
			  jgroupbandwidth.setText(properties.getProperty("BANDWIDTH_FILE"));
			  jblockedsites.setText(properties.getProperty("BLOCKEDSITES_FILE"));
			  juserdetails.setText(properties.getProperty("USER_DETAILS_FILE"));
			  jcachefile.setText(properties.getProperty("CACHE_FILE"));
			  String selbutton = properties.getProperty("CACHE_POLICY");
			  if(selbutton.equals("LRU"))
					jradiolru.setSelected(true);
			  else
					jradiofifo.setSelected(true);
		}

	  public void actionPerformed(ActionEvent ae)
		{
			String button = ae.getActionCommand();
			if(button.equals("LRU"))
			{
				cachePolicy = "LRU";
			}
			else if (button.equals("FIFO"))
			{
				cachePolicy = "FIFO";
			}
			else if (button.equals("OK"))
			{
				saveProperties();
			}
		}

	public void saveProperties()
	{

		properties.setProperty("CACHE_SIZE",jcachetext.getText());
		properties.setProperty("SERVER_PORT",jserverport.getText());
		properties.setProperty("USER_TIMEOUT",jtimeout.getText());
		properties.setProperty("CACHE_POLICY",cachePolicy);
		properties.setProperty("SERVER_LOG",jserverlog.getText());
		properties.setProperty("CACHE_LOG",jcachelog.getText());
		properties.setProperty("USER_LOG",juserlog.getText());
		properties.setProperty("BANDWIDTH_FILE",jgroupbandwidth.getText());
		properties.setProperty("BLOCKEDSITES_FILE",jblockedsites.getText());
		properties.setProperty("USER_DETAILS_FILE",juserdetails.getText());
		properties.setProperty("CACHE_FILE",jcachefile.getText());
		try
		{	
			File file = new File(configFile);
			BufferedOutputStream sout = null;
			sout = new BufferedOutputStream(new FileOutputStream(file));
			properties.store(sout, null);
			sout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}  
}
/***********************************************************************************************/

class BlockedsitesPanel extends JPanel implements ActionListener
{
	JLabel sitename = new JLabel("ADD Blocked Site");
	JTextField jsite = new JTextField(30);
	JList sitelist = new JList();
	JButton jbADD = new JButton("ADD");
	JButton jbDELETE = new JButton("DELETE");
	JButton jbSAVE = new JButton("SAVE");
	//String fileName= "D:\\users\\d0331082\\trial.txt"; 
	
	Vector blockedSites = new Vector();
	int selectedIndex=-1;
	String blocked ="\\D:\\users\\d0331067\\blockedsites.txt";
	
	File file1 ;
	FileOutputStream fileout ;
	PrintStream p ;
	public BlockedsitesPanel() {
 
		 MouseListener mouseListener = new MouseAdapter() 
	  {
     public void mouseClicked(MouseEvent e) {
         if (e.getClickCount() == 1) {
             selectedIndex = sitelist.getSelectedIndex();
             System.out.println("Double clicked on Item " + selectedIndex);
			 System.out.println("value of selected index " + sitelist.getSelectedValue());
		  }
     }
 }; 
    setLayout(null);
	sitename.setBounds(150,80,150,50);
	add(sitename);
	jsite.setBounds(300,100,200,20);
	add(jsite);
	sitelist.setBounds(550,100,200,250);
	//sitelist.add(jsblist);88
	add(sitelist);
    jbADD.setBounds(300,400,90,30);
    add(jbADD);
	jbADD.addActionListener(this);
    jbDELETE.setBounds(400,400,90,30);
    add(jbDELETE);
	jbDELETE.addActionListener(this);
	jbSAVE.setBounds(600,400,90,30);
	 add(jbSAVE);
	jbSAVE.addActionListener(this);
	sitelist.addMouseListener(mouseListener);
	
	try
	  {
			
			//System.out.println("Opening file reader");
			FileReader fr = new FileReader(blocked);
			//System.out.println("file reader opened");	
			BufferedReader br = new BufferedReader(fr);
			//System.out.println("file reader opened");
			String str;
			int elementAt=0;
			while ((str = br.readLine()) != null)
			{
				blockedSites.add(elementAt,str);
				//System.out.println("value of vector "+ blockedSites.elementAt(elementAt));
				elementAt++;
			}
			//br.close();
			fr.close();
	  }
	  catch (IOException e)
	  {
		  System.out.println("Unable to open file bloockedSites.txt");
	  }
	  sitelist.setListData(blockedSites);
    }//end of constructor
  
	public void actionPerformed(ActionEvent ae) {
		 System.out.println("action performed");
		 if (ae.getSource().equals(jbADD))
		 {
			String sitename =jsite.getText() ;
			blockedSites.add(sitename);
			sitelist.setListData(blockedSites);
	//		int x=sitelist.getSelectedIndex();
	//		jsite.setText(str[x]); 
		 }
		 if (ae.getSource().equals(jbDELETE)) 
		 {
			System.out.println("inside listener for delete");
			blockedSites.removeElementAt(selectedIndex);
			sitelist.setListData(blockedSites);
		 }
		
		if(ae.getSource().equals(jbSAVE))
		{
			try
		{
			file1 = new File(blocked);
			fileout =new FileOutputStream(file1);
			p = new PrintStream(fileout);
		}
		catch (FileNotFoundException fe)
		{
			System.out.println(fe.getMessage());
		}
			System.out.println("inside listener for save");
			for(int i = 0;i<blockedSites.size();i++) 
			{
				System.out.println("element getting added "+blockedSites.elementAt(i).toString());
				p.println(blockedSites.elementAt(i).toString());
					
			}
		
		}
		try
		{
		fileout.close();
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in closing the file");
		}
  }

 }

class BandwidthPanel extends JPanel implements ActionListener
{
	JLabel bandwidth = new JLabel("User-Group Bandwidth Limit");
	JTextField jbandwidth = new JTextField(30);
	JLabel ugrp = new JLabel("User-Group");
	JList ugrplist = new JList();
	JButton jbOK = new JButton("OK");
	JButton jbCANCEL = new JButton("CANCEL");
     Vector usergroup = new Vector();
	int selectedIndex =-1;
	String selval;
	Properties properties = new	Properties();
	public String bandwidthfile = "\\D:\\users\\d0331067\\bandwidth.txt";

	public BandwidthPanel() 
	{
		usergroup.add(0,"student");
		usergroup.add(0,"staff");
		usergroup.add(0,"admin");
		MouseListener mouseListener = new MouseAdapter() 
		{
		 public void mouseClicked(MouseEvent e) {
			 if (e.getClickCount() == 1) {
				 selectedIndex = ugrplist.getSelectedIndex();
				 System.out.println("Double clicked on Item " + selectedIndex);
				 System.out.println("value of selected index " + ugrplist.getSelectedValue());
					selval = ugrplist.getSelectedValue().toString();
					jbandwidth.setText(properties.getProperty(selval));
					
		  
		}
     }
 }; 

			setLayout(null);
			bandwidth.setBounds(420,60,200,100);
			add(bandwidth);
			jbandwidth.setBounds(600,100,150,20);
			add(jbandwidth);
			ugrp.setBounds(100,80,100,50);
			add(ugrp);
			ugrplist.setBounds(200,100,200,100);
			ugrplist.setListData(usergroup);
			ugrplist.addMouseListener(mouseListener);
			add(ugrplist);
			jbOK.setBounds(300,400,90,30);
			add(jbOK);
			jbOK.addActionListener(this);
			jbCANCEL.setBounds(400,400,90,30);
		//	add(jbCANCEL);
			jbCANCEL.addActionListener(this);
	   	   
	//using properties class to bring in data from bandwidth.txt   
	   	  try
				{
					File in_file = new File(bandwidthfile);
					BufferedInputStream sin = null;
					sin = new BufferedInputStream(new FileInputStream(in_file));

					properties = new Properties();
					properties.load(sin);
					sin.close();
				}
			catch (Exception e)
				{
					System.out.print("cannot open file");
					e.printStackTrace();
				}	   
	   }

	public void actionPerformed(ActionEvent ae) 
		{
			System.out.println("actionperformed");
			if (ae.getSource().equals(jbOK))
			{
				System.out.println("inside ok");
				System.out.println("selval=>"+selval+" textbox=>"+jbandwidth.getText());
			properties.setProperty(selval,jbandwidth.getText());
			try
		{	
			File file = new File(bandwidthfile);
			BufferedOutputStream sout = null;
			sout = new BufferedOutputStream(new FileOutputStream(file));
			properties.store(sout, null);
			sout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
			}
			if(ae.getSource().equals(jbCANCEL))
			{
				//System.exit(0);
			}
		}
	
}