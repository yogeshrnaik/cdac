import ncst.pgdst.*;

class Node
{	
	String str;
	Node left,right;
		
	Node(String str)
	{
		this.str = str;
		left = right = null;
	}
}

public class ass12
{
	public static void main(String [] args) throws IOException
	{
		ass12 tree = new ass12();
	}
	
	Node temp,head=null;boolean flag=false;
	ass12() throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int n;String str,name1,name2;
		Node temp1,temp2;
		while(true)
		{
			str = sin.readWord();
			if (str.equals("END"))
				break;
			else 
			{
				if (head==null)
				{
	      				temp = new Node(str);
					head = temp;
				}	
				n = sin.readInt();
				if (n==1)
				{	
					name1 = sin.readWord();
					temp1 = new Node(name1);
					temp = null;
					getHead(head,str);
					temp.left = temp1;
				}
				else
				{
					name1 = sin.readWord();
					name2 = sin.readWord();
					temp1 = new Node(name1);
					temp2 = new Node(name2);
					temp = null;
					getHead(head,str);
					temp.left = temp1;
					temp.right = temp2;
				}
			}
		}
		//printInOrder(head);			
		n = sin.readInt();
		for (int i=0;i<n;i++)
		{	
			flag = false;
			name1 = sin.readWord();		
			name2 = sin.readWord();
			temp = null;
			getHead(head,name1);
			//System.out.println("temp:"+temp+"name1:"+name1+"head.str:"+head.str);
			//System.out.println(head.left.str+" "+head.left.left+" "+head.left.right);
			search(temp,name2);
			if (flag)
				System.out.println("YES");
			else			
				System.out.println("NO");
		}
	}

	public void getHead(Node newhead,String str)	
	{	//System.out.print(str);
		if (newhead.str.equals(str))
		{	temp = newhead;return;}
		else
		{
			if (newhead.left!=null)
				getHead(newhead.left,str);
			if (newhead.right!=null)
				getHead(newhead.right,str);	
			
		}
	}

	public void search(Node newhead,String str)
	{	//System.out.println(newhead.str);	
		if (newhead.str.equals(str))
		{
			flag = true;
			return;
		}
		else
		{
			if (newhead.left!=null)
				search(newhead.left,str);
			if (newhead.right!=null)
				search(newhead.right,str);
		}
	}

	public void printInOrder(Node newhead)
	{
		if (newhead!=null)
		{
			printInOrder(newhead.left);
			System.out.print(newhead.str+" ");
			printInOrder(newhead.right);
		}
	}
}
		
		
				





 