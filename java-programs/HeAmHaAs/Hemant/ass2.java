import ncst.pgdst.*;
class Node
{
	int t;
	Node next;
		
	Node(int t)
	{
		this.t = t;
		next = null;
	}
}
class Queue
{
	Node head,curr,prev,tail;int count;	

	Queue()
	{
		head = null;count = 0;
	}
			
	public void insert(Node temp)
	{
		count++;
		if (head==null)
			head = temp;
		else
		{
			curr = head;
			while (curr.next!=null)
				curr = curr.next;
			curr.next = temp;
		
		}
	}

	public void remove()
	{	
		head = head.next;
		count--;
	}

	public void print()
	{	if (head==null)
			return;
		System.out.print(count);
		for (curr=head;curr!=null;curr=curr.next)
			System.out.print(" "+curr.t);
		System.out.println();
	}
}

public class ass2
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		boolean flag=true; int n;
		Node temp;
		int N = sin.readInt();
		Queue q = new Queue();
		do
		{	q.print();
			n = sin.readInt();
			if (n>N || n==-1)
				flag = false;
			else
			{
				temp = new Node(n);
				q.insert(temp);
			}
		} while(flag);

		for (int i=0;i<=N;i+=3)
		{
			q.remove();
		}

		q.print();
	}
}

		
				
		