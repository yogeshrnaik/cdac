import BankApp.*;
import java.util.StringTokenizer;
import java.io.*;

// BankServer will use the naming service.
import org.omg.CosNaming.*;
// The package containing special exceptions thrown by the name service.
import org.omg.CosNaming.NamingContextPackage.*;
// All CORBA applications need these classes.
import org.omg.CORBA.*;



public class BankServer 
{
  public static void main(String args[])
  {
    try{
    
      // Create and initialize the ORB
      ORB orb = ORB.init(args, null);
      
      // Create the servant and register it with the ORB
      BankServant bankRef = new BankServant();
      orb.connect(bankRef);
      
      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
      // Bind the object reference in naming
      // Make sure there are no spaces between ""
      NameComponent nc = new NameComponent("Bank", "");
      NameComponent path[] = {nc};
      ncRef.rebind(path, bankRef);
      
      // Wait for invocations from clients
      java.lang.Object sync = new java.lang.Object();
      synchronized(sync){
        sync.wait();
      }
      
    } catch(Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }  
  }
}



class BankServant extends _BankImplBase
{

	public  int createAccount(String accountid,String passwd,String bal,String custname,String city,String acctType)
 	{
		int user = chkUser(accountid,passwd);
		if(user == 3)
		{
			String rec = accountid+"$$"+passwd+"$$"+bal+"$$"+custname+"$$"+city+"$$"+acctType; 
			writer("data.txt",rec);
			String signin = accountid+"$$"+passwd;
			writer("users.txt",signin);
 			return 1;
		}
		else {return 0;}
 	}
	
	public int login( String uname,String passwd)
 	{
		int user;
	 	if ((uname.compareTo("admin")==0)&&(passwd.compareTo("admin")==0))	
			{ user =  0;}
		else	{
			  user = chkUser(uname,passwd);
			}
		return user;
 	}
	public Account getAccount(String acctno)
	{
		BankApp.Account account = null;
		account = new AccountImpl();
		return account;
	}
	public void writer(String filename,String outToFile)
	{	
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));
			out.write(outToFile);
			out.newLine();
			out.close();
		}
		catch(IOException e){}
	}  
	public int chkUser(String uid,String pw)
	{//chk if user already exists.
		int user = 4;
                try
                {
                       BufferedReader in = new BufferedReader(new FileReader("users.txt"));
                       String str = null;
                       while((str = in.readLine())!= null)
                       {	
				user = 3;	
                               StringTokenizer st = new StringTokenizer(str,"$$");
                               String a = st.nextToken();
				System.out.println(a);
                               String b = st.nextToken();
				System.out.println(b);
                               if((a.compareTo(uid))==0)
                                      {user =  2;}
                              
                               if(((a.compareTo(uid))==0)&&((b.compareTo(pw))==0))
                                      {user =  1;}
                        }
                }
                catch(IOException e){}
                catch(java.util.NoSuchElementException e){}
		return user;
	}
}
