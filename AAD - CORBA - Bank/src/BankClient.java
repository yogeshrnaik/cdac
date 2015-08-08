import BankApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

public class BankClient
{
	static Bank bankImpl;
	public static void main(String args[])
	{
		try{
        		// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
	 	       	org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        		// Use NamingContextExt instead of NamingContext. This is part of the Interoperable naming Service.  
        		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
       			// resolve the Object Reference in Naming
        		String name = "Bank";
        		bankImpl = BankHelper.narrow(ncRef.resolve_str(name));
			
			userInterface();	
        		//System.out.println("Obtained a handle on server object: " + bankImpl);
        		//System.out.println("+");
        		//bankImpl.shutdown();
			
		 }
		 catch (Exception e)
		 {
         		 System.out.println("ERROR : " + e) ;
	  		 e.printStackTrace(System.out);
	       	 }


	} 	
	//To read from console
	static String readString()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String inputString = null;
		try { 
         		inputString = br.readLine(); 
     		    }
	        catch (IOException ioe) 
		{
			System.out.println("IO error trying to read from console!");
			System.exit(1); 
		} 
	/*	try
		{
			br.close();
		}
		catch (IOException ioe)
		{
			
			System.out.println("IO error trying to read from console!");
			System.exit(1); 
		}
	*/
		return inputString;
	}

	//To provide the UI
	static void userInterface()
	{
			
		System.out.println("**************************************");
		System.out.print("Please Enter Your User Name:");
		String uname = readString();
		System.out.print("\n"+"Please Enter Your Password:");
		String pwd = readString();
		int user= bankImpl.login(uname,pwd);
		System.out.println(user);
		int option = 0;
		if (user == 0)
		{
			while (true)
			{
				System.out.println("***************************************");
				System.out.println("Options");
				System.out.println("=======");
				System.out.println("1. Create New Account");
				System.out.println("2. View Customer Profile");
				System.out.println("3. Exit");
				System.out.println("***************************************");
				System.out.println("Please Select an option");
				String choice = readString();
				Integer no = new Integer(choice);
				int num = no.intValue();
				switch (num)
				{
					case 1 :getCreateAccount();
						break;
					case 2 :getViewProfile();
						break;
					case 3 :System.exit(1);
						break;
					default :break;
				}
			}
		}
		else if(user == 1) 
		{
			while (true)
			{
				System.out.println("***************************************");
				System.out.println("Options");
				System.out.println("=======");
				System.out.println("1. Withdraw");
				System.out.println("2. Deposit");
				System.out.println("3. Balance Query");
				System.out.println("4. Exit");
				System.out.println("***************************************");
				System.out.println("Please Select an option");
				String choice = readString();
				Integer no = new Integer(choice);
                                int num = no.intValue();
                                switch (num)
                                {
                                        case 1  :getWithdraw(uname);
                                                break;
                                        case 2  :getDeposit(uname);
                                                break;
                                        case 3  :getBalanceQuery(uname);
                                                break;
                                        case 4  :System.exit(1);
                                                break;
                                        default :break;
                                }
			}
		}
		else 
		{
			System.out.println("You have entered the wrong password.Please enter it Carefully again:");
		}
	}
	//To create a new Account
	static void getCreateAccount()
	{	
		System.out.println("Enter Details of the new account to be Created");
		System.out.println("Enter Customer Name:");
		String custname = readString();
		System.out.println("Set an account Number for the account created");
		String Acctno = readString();
		System.out.println("Set a Password for the Account Created for"+custname); 
		String passwd = readString();
		System.out.println("Enter Initial Balance:");
		String balance = readString();
		System.out.println("Enter City:");
		String city = readString();
		System.out.println("Enter Account Type:");
		String acctType = readString();
		int success= bankImpl.createAccount(Acctno,passwd,balance,custname,city,acctType);
		if (success == 1)
		{
			System.out.println("Creating Account for --"+custname);
		}
		else
		{
			System.out.println("The following Account no. already exists.Please make a new Account again for --"+custname);
		}
	}
	static void getViewProfile()
	{
	}
	//To Withdraw Amount
	static void getWithdraw(String uname)
	{
		BankApp.Account account = null;
		account =bankImpl.getAccount(uname);
		System.out.println("Enter the amount you wish to withdraw:");
		String amount = readString();
		Integer no = new Integer(amount);
                int amt = no.intValue();
		int out = account.withDraw(amt,uname);
		if(out ==1)
		{	
			System.out.println("Amount withdrawn :"+amt);
		}
		else if(out == 0)
		{
			System.out.println("Balance is less than withdrawal amount.Get married to an NRI.");
		}
	
	}

	//To deposit Amount
	static void getDeposit(String uname)
	{
		
		BankApp.Account account = null;
		account =bankImpl.getAccount(uname);
		System.out.println("Enter the amount you wish to Deposit :");
		String amount = readString();
		Integer no = new Integer(amount);
                int amt = no.intValue();
		account.deposit(amt,uname);
		System.out.println("Amount deposited for You:Rs."+amt);
	}
	//To enquire about the balance
	static void getBalanceQuery(String uname)
	{
		BankApp.Account account = null;
		account =bankImpl.getAccount(uname);
		float bal = account.balanceQuery(uname);
		System.out.println("Your Current balance is:" +bal);
	}

}
