import ncst.pgdst.*;

class Node 
{
	char c;
	Node next;

	Node(char ac)
	{
		c = ac;
		next = null;
	}
}

public class eqn
{	
	int ans,N;
	Node head;
	int a[];

	public static void main(String[] args) throws IOException
	{
		eqn e = new eqn();
		e.solve();
		System.out.println("No solution exists using '+','-' & '*'");
	}

	public void solve() throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int i;
		
		Node temp;
		
		System.out.print("How many total numbers in LHS and RHS:");		
		N = sin.readInt();
		int ax[] = new int[N-1];
		
		System.out.print("Enter the LHS:");
		for (i=0; i<N-1 ;i++ )
		{
			ax[i] = sin.readInt();
		}
		System.out.print("Enter the RHS:");
		ans = sin.readInt();
		a = ax;
		temp = new Node('+');
		temp.next = new Node(' ');
		head = temp;
		recurse(ax[0],'+',ax[1],'+',2,temp.next);
		recurse(ax[0],'+',ax[1],'-',2,temp.next);
		recurse(ax[0],'+',ax[1],'*',2,temp.next);

		temp = new Node('-');
		temp.next = new Node(' ');
		head = temp;
		recurse(ax[0],'-',ax[1],'+',2,temp.next);
		recurse(ax[0],'-',ax[1],'-',2,temp.next);
		recurse(ax[0],'-',ax[1],'*',2,temp.next);

		temp = new Node('*');
		temp.next = new Node(' ');
		head = temp;
		recurse(ax[0],'*',ax[1],'+',2,temp.next);
		recurse(ax[0],'*',ax[1],'-',2,temp.next);
		recurse(ax[0],'*',ax[1],'*',2,temp.next);
	}

	public void recurse(int n1,char op1,int n2,char op2,int x,Node temp)
	{		
		temp.c = op2;
		int newn1,newn2,fans;
		char newop;
		if (x==N-2)
		{
			int ans2;
			if (op2=='*')
			{
				ans2 = calc(n2,op2,a[x]);
				fans = calc(n1,op1,ans2);
			}
			else
			{
				ans2 = calc(n1,op1,n2);
				fans = calc(ans2,op2,a[x]);
			}
			if (ans == fans)
			{
				int i=0;
				for (temp=head; temp!=null ;temp=temp.next)
				{	
					System.out.print(a[i]+" ");
					System.out.print(temp.c+" ");
					i++;
				}
                                System.out.print(a[x]+" = "+ans+"\n");
                                System.exit(1);
			}
		}

		else
		{
			if (op2=='*')
			{
				newn1 = n1;
				newn2 = n2 * a[x];
				newop = op1;
			}
			else
			{
				newn1 = calc(n1,op1,n2);
				newn2 = a[x];
				newop = op2;
			}
			
			temp.next = new Node(' ');
			recurse(newn1,newop,newn2,'+',x+1,temp.next);
			recurse(newn1,newop,newn2,'-',x+1,temp.next);
			recurse(newn1,newop,newn2,'*',x+1,temp.next);
		}
	}

	public int calc(int n1,char op,int n2)
	{
		if (op == '+')
			return(n1 + n2);
		else if (op == '-')
			return(n1 - n2);
		else
			return(n1 * n2);
	}
}
