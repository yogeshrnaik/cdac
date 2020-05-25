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

public class btree
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		btree tree = new btree();
	}
		
	Node head=null;
	btree() throws IOException
	{	
		
		SimpleInput sin = new SimpleInput();
		int choice=1;
		while(true)
		{	
			System.out.println("Add?");
			choice = sin.readInt();
			if (choice==1)
			{	
				System.out.print("Enter data:");
				int x = sin.readInt();
				Node temp = new Node(x);
				insert(head,temp);
			}
			else
				break;
		}
		//System.out.print(head.x+" "+head.left.x+" "+head.right.x);
		System.out.print("In order:");
		printInOrder(head);
		System.out.println();
		System.out.print("Pre order:");
		printPreOrder(head);
		System.out.println();
		System.out.print("Post order:");
		printPostOrder(head);
	}

	public void insert(Node newhead,Node temp)
	{
		
		if (newhead==null)
		{	head = temp;System.out.println("head was null");}
		
		else
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
		//System.out.print("In insert: "+"newhead.x:"+newhead.x+" ");
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

	public void printPreOrder(Node newhead)
	{
		if (newhead!=null)
		{
			System.out.print(newhead.x+" ");
			printPreOrder(newhead.left);
			printPreOrder(newhead.right);
		}
	}

	public void printPostOrder(Node newhead)
	{
		if (newhead!=null)
		{
			printPostOrder(newhead.left);
			printPostOrder(newhead.right);
			System.out.print(newhead.x+" ");
		}
	}
}

	