import BankApp.*;
import java.io.*;
import java.util.StringTokenizer;

public class AccountImpl extends BankApp._AccountImplBase
{
	public int withDraw(float amount,String acctno)
	{
		int success = 0;
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("data.txt"));
			String str = null;
			String id = null,pwd = null,custname=null,type = null,city = null;
			float balance;

			while((str = in.readLine())!= null)
			{
				StringTokenizer st = new StringTokenizer(str,"$$");
				id = st.nextToken();
				if(id.compareTo(acctno) == 0)
				{
					System.out.println("Tokenizing");
					pwd = st.nextToken();
					Integer bal = new Integer(st.nextToken());
					balance	= bal.intValue();
					if(balance >= amount)
					{
						custname = st.nextToken();
						city = st.nextToken();
						type= st.nextToken();
						balance = balance-amount;
						String rec = id+"$$"+pwd+"$$"+balance+"$$"+custname+"$$"+city+"$$"+type;
						writer("temp.txt",rec);	
						success = 1;			
					}
				}
				else
				{	
					writer("temp.txt",str);
					//write to file.
				}
		//	File file = new File("temp.txt");
		//	File file1 = new File("data.txt");
		//	boolean succ = file.renameTo(file1);
			}
		}
		catch(IOException e){}
		catch(NullPointerException npe){}
		return success;
	//	return  99;//check in the database..retrieve his record..update his balance..and write it to file..
	}

	public int deposit(float amount,String acctno)
	{
		return  101;
	}
	public float balanceQuery(String acctno)
	{
		return 102;
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
}


