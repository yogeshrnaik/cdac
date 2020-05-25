import ncst.pgdst.*;
public class RohitMGPT13
{
	public static void main(String[]args) throws IOException
	{
		SimpleInput sin=new SimpleInput();
		Post post=new Post(sin);
		int num=sin.readInt();
		int total=0;
		for(int i=0;i<num;i++)
		{
			total+=post.stamps(sin.readInt());
		}
		System.out.println(total);
	}//main

}//class

class Post
{
	public int sta;
	public int[] type=new int[1000];

	Post(SimpleInput in) throws IOException
	{
		sta=in.readInt();
		for(int j=0;j<sta;j++)
		type[j]=in.readInt();
	}

	public int stamps(int amt)
	{
		int r=0;
		for(int a=1;a<=10000;a++)
		{
			if(recurse(a,amt)) { r=a;break;}
		}
		return r;
	}//stamps

	public boolean recurse(int no,int val)
	{
		if(no==1)
		{
			for(int p=0;p<sta;p++)
			{ 
				if(type[p]==val) return true;
			}
			return false;
		}
		else
		{
			for(int q=0;q<sta;q++)
			{
				 if(recurse(no-1,val-type[q])) return true;
			}
		}	
		return false;
	}//recurse
}//class
