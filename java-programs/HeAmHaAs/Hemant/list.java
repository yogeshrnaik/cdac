import ncst.pgdst.*;

class Node
{
	int x;
	Node next;
	
	Node(int x)
	{
		this.x = x;
		next = null;
	}
}

public class list 
{
	public static void main(String [] args)
	{	
		boolean choice=true;int i;
		Node head,curr,temp,prev;
		head = null;
		curr = prev = null;
		
		while (choice)
		{	try {
			SimpleInput sin = new SimpleInput();
			System.out.println("Add?");
			i = sin.readInt();
			
			if (i==1)
			{	choice=true;
				System.out.print("Enter the number:");
				i = sin.readInt();
				
				temp = new Node(i);
				
				if (head==null)
					head = temp;
				else
				{	
					curr = head;prev = null;
					if (temp.x<=head.x)
					{
						temp.next = head;
						head = temp;
					}
					else 
					{
						while (temp.x>=curr.x && curr.next!=null)	
						{
							prev = curr;
							curr = curr.next;
						}
						if (temp.x>=curr.x)
						{	
							curr.next = temp;
						}
						else
						{
							temp.next = curr;
							prev.next = temp;
						}
					}
				}
				if (prev!=null && curr!=null)
					System.out.println("Prev:"+prev.x+" curr:"+curr.x);
				for (curr=head;curr!=null;curr=curr.next)
					System.out.print(curr.x+" ");
				System.out.println();
			}
			else
				choice = false;
			}
			catch(Exception e)
			{
				System.out.println("Invalid number");
			}
		}
		
	}
}

				
			
				