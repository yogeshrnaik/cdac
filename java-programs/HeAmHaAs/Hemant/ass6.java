import ncst.pgdst.*;

class Node
{	
	int x;
	Node left;
	Node right;
	
	Node(int x)
	{
		this.x = x;
		left = right = null;
	}
}	

public class ass6
{
	public static void main(String [] args) throws IOException
	{
		ass6 tree = new ass6();
	}
		
	int count1,count2;boolean flag;int count=0,max=0;
	ass6() throws IOException
	{	
		int N;int n;
		Node temp;
		SimpleInput sin = new SimpleInput();
		N = sin.readInt();
		Node head[] = new Node[5];
		
		for (int i=0;i<N;i++)
		{
			while(true)
			{	
				n = sin.readInt();
				if (n==-1)
					break;	
				else
				{
					temp = new Node(n);
					if (head[i]==null)
						head[i] = temp;
					else
						insert(head[i],temp);
				}
			}
		
		}

	
		if (head[0]==null)
			count1 = 0;
		else
		{	
			getDepth(head[0]);
			count1 = max;
		}
		//System.out.println("Count1:"+count1);
		for (int i=1;i<N;i++)
		{	
			flag = false;
			if (head[0]==null && head[i]==null) 
				flag=true;
			else if (head[0]!=null && head[i]!=null) 
			{
				flag = true;
				check(head[0],head[i]);
			}
			
			if (flag)
				System.out.println("YES 0");
			else
			{
				count2 = count = max = 0;
				if (head[i]==null)
					count2 = 0;
				else
				{	
					getDepth(head[i]);
					count2 = max;
				}
				//System.out.println("Count "+(i+1)+":"+count2);
				System.out.println("NO "+Math.abs(count1-count2));
			}
				/*count2 = count = 0;
				if (head[i]==null)
						count2 = 0;
				else
				{	System.out.println("i:"+i);
					getDepth(head[i]);
					count2 = max;
				}
				System.out.println(count2);*/
			
		}
	}
	
	public void check(Node new1,Node new2)
	{	
		if (!flag)
			return;
		//System.out.println("new1:"+new1.x+" " +new1.left+" "+new1.right);
		//System.out.println("new2:"+new2.x+" " +new2.left+" "+new2.right);
		if (new1.left==null && new2.left==null)
			flag = true;
		else if (new1.left!=null && new2.left!=null && flag)
		{
			flag = true;
			check(new1.left,new2.left);
		}
		else 
		{	
			flag = false;
			return;
		}
		if (!flag)
			return;
		if (new1.right==null && new2.right==null)
			flag = true;
		else if (new1.right!=null && new2.right!=null && flag)
			{	
				flag = true;
				check(new1.right,new2.right);
			}
		else 
		{
			flag = false;
			return;
		}
	}	
	
	public void insert(Node newhead,Node temp)
	{
		if (temp.x < newhead.x)
		{
			if (newhead.left==null)
				newhead.left = temp;
			else 
				insert(newhead.left,temp);
		}
		else
		{
			if (newhead.right==null)
				newhead.right = temp;
			else 
				insert(newhead.right,temp);
		}
	}
	
	public void getDepth(Node newhead)
	{	//System.out.println("Node:"+newhead.x+"max:"+max+"count:"+count);
		if (count>max)
			max = count;
		if (newhead.left!=null)
		{
			count++;
			getDepth(newhead.left);
			count--;
		}

		if (newhead.right!=null)
		{
			count++;
			getDepth(newhead.right);
			count--;
		}
	}
	
	public void printInOrder(Node newhead)
	{
		if (newhead!=null)
		{
			printInOrder(newhead.left);
			System.out.print(newhead.x+" ");
			printInOrder(newhead.right);
		}
	}
}