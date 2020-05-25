import ncst.pgdst.*;

class Node
{
	int age;String name;
	Node next;
	
	Node(String aname,int anage)
	{
		name = aname;
		age = anage;
		next = null;
	}
}

public class ass1
{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		ass1 ass = new ass1(sin);
	}

	Node prev,curr,head,temp;int count=0;
	
	ass1(SimpleInput sin) throws IOException
	{
		String str;int x;String name;
		while(true)
		{
			str = sin.readWord();
			if (str.equals("stop"))
				System.exit(1);
			else if (str.equals("insert"))
			{
				name = sin.readWord();
				x = sin.readInt();
				temp = new Node(name,x);
				insert();
			}
			else if (str.equals("remove"))
			{
				x = sin.readInt();
				remove(x);
			}
			else if (str.equals("print"))
			{
				x = sin.readInt();
				print(x);
			}
			else if (str.equals("all"))
			{
				for (curr=head;curr!=null;curr=curr.next)
					System.out.println(curr.name+" "+curr.age);
			}
			//System.out.println("Count:"+count);
		}
	}
	public void insert()
	{	
		count++;
		if (head==null)
			head = temp;
		else 
		{
			if (temp.age<head.age)
			{
				temp.next = head;
				head = temp;
			}

			else 
			{	
				curr = head;prev = null;
				while (temp.age>=curr.age && curr.next!=null)	
				{
					prev = curr;
					curr = curr.next;
				}
			
				if (temp.age>=curr.age)
					curr.next = temp;
			
				else
				{
					temp.next = curr;
					prev.next = temp;
				}
			}
		}
	}

	public void remove(int x)
	{
		if (x>count)
			return;
		else
		{
			if (x==1)
				head = head.next;
			else
			{
				curr=head;
				for (int i=1;i<x;i++)
				{
					prev  = curr;
					curr = curr.next;
				}
				prev.next = curr.next;
			}
			count--;
		}
		
	}
			
	public void print(int x)
	{
		if (x>count)
			return;
		else
		{
			curr = head;
			for (int i=1;i<x;i++)
				curr = curr.next;
			System.out.println(curr.name+" "+curr.age);
		}
	}			
	








}
