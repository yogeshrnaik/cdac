import ncst.pgdst.*;

class Stack
{
	Object a[] = new Object[10];
	int top;
	
	Stack()
	{
		top = a.length;
	}

	public void push(Object o)
	{
		if (top>0)
		{
			top--;
			a[top] = o;
		}
		else
		{
			System.out.println("Stack Full");
			System.exit(1);
		}
	}

	public Object pop()
	{
		if (top<a.length)
		{
			Object temp = a[top];
			top++;
			return(temp);
		}
		else
		{
			System.out.println("Stack Empty");
			Object temp = new Integer(-1);
			System.exit(1);return(temp);
		}
	}

	public boolean isEmpty()
	{
		return(top<a.length);
	}

	public boolean isFull()
	{
		return(top>0);
	}

	public Object topEl()
	{
		if (top<a.length)
		{
			Object temp = a[top];
			return(temp);
		}
		else
		{
			System.out.println("Stack Empty");
			Integer temp = new Integer(-1);
			System.exit(1);return(temp);
		}
	}
}

public class StackDemo
{
	public static void main(String [] args) throws IOException
	{	Stack s = new Stack();
		Integer ints[] = new Integer[10];
		for (int i=0;i<10;i++)
		{	ints[i] = new Integer(i);
			s.push(ints[i]);
		}
		for (int i=0;i<11;i++)
		{
			Object tmp = s.pop();
			
			System.out.println(tmp);
		}
	}
}