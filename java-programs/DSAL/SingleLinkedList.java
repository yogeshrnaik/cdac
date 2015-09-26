import ncst.pgdst.*;
public class SingleLinkedList
{
  Node head;
  int no_of_nodes;
  SingleLinkedList()
  {
    no_of_nodes = 0;
    head = null;
  }
  public static void main(String[] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    SingleLinkedList list = new SingleLinkedList();
    list.readList(sin);
    list.printList();

    int option=1;
    while (option==1 || option==2 || option==3 || option==4)
    {
      System.out.println("Options\n1 --> Insert At \n2 --> Insert This");
      System.out.println("3 --> Delete Node At\n4 --> Delete This");
      System.out.print("Enter your option : ");
      option = sin.readInt();
      switch(option)
      {
        case 1 : System.out.print("Insert where? : ");
                 list.insertAt(sin.readInt());
                 list.printList();
                 break;
        case 2 : list.insertThis(new Node(sin));
                 list.printList();
                 break;
        case 3 : System.out.print("Delete from where? : ");
                 list.delNodeAt(sin.readInt());
                 list.printList();
                 break;
        case 4 : System.out.print("Delete what? : ");
                 list.delThis(sin.readInt());
                 list.printList();
                 break;
      }
    }
  }// main
  public void readList(SimpleInput sin) throws IOException
  {
    boolean stop = false;
    Node curr = null;
    while (!stop)
    {
      int temp = sin.readInt();
      if (temp == -1)
      {  stop = true;
         break;
      }
      else if (no_of_nodes == 0)
      {
          head = new Node(temp);
          curr = head;
      }
      else 
      {
         curr.next = new Node(temp);
         curr = curr.next;
      }
      no_of_nodes++;
    } // while
  }
  public void printList()
  {
    for (Node curr=head; curr!=null; curr=curr.next)
       System.out.print(curr.item + " ");
    System.out.println();
  }

  public Node find(int index)
  {
    Node curr = head;
    for (int skip=1; skip<index; skip++)
       curr = curr.next;
    return curr;
  }

  public void insertAt(int index) throws IOException
  {
    if (index>=1 && index<=no_of_nodes+1)
    {
      if (index == 1)
      {
        Node newNode = new Node(new SimpleInput(), head);
        head = newNode;
      }
      else
      {
        Node prev = find(index-1);
        Node newNode = new Node(new SimpleInput(), prev.next);
        prev.next = newNode;
      }
      no_of_nodes++;
    }
    else if (no_of_nodes == 0)
      System.out.println("Since the list is empty, enter index = 1");
    else
      System.out.println("Please enter an index >=1 and <= "+(no_of_nodes+1));
  }

  public void insertThis(Node newNode)
  {
    if(head == null)
      head = newNode;
    else if (newNode.item < head.item) // insert at the start
    {
      newNode.next = head;
      head = newNode;
    }
    else
    {
      Node curr=head, prev=null;
      for (; curr!=null && curr.item <= newNode.item; curr=curr.next)
             prev = curr;
      newNode.next = prev.next;
      prev.next = newNode;
    }
    no_of_nodes++;
  }
  
  public void delNodeAt(int index)
  { 
    if (index>=1 && index<=no_of_nodes)
    {
      if (index == 1)
        head = head.next;
      else
      {
        Node prev = find(index-1);
        Node curr = prev.next;
        prev.next = curr.next;
      }
      no_of_nodes--;
    }
    else if (no_of_nodes == 0)
     System.out.println("Cannot delete, since the list is empty.");
    else if (no_of_nodes == 1)
     System.out.println("Please enter an index = 1");
    else System.out.println("Please enter an index >= 1 and <= "+no_of_nodes);
  }
  public void delThis(int item)
  {
     if (head == null)
     {   System.out.println("The list is empty.");
         return;
     }
     else if (head.item == item)
        head = head.next;
     else
     {
       Node curr = head, prev = null;
       for (; curr!=null && curr.item!=item; curr=curr.next)
              prev = curr;
       if (curr == null)
       {
          System.out.println("The item is not there in the list.");
          return;
       }
       //prev.next = curr.next;
       // or
       prev.next = prev.next.next;
     }
     no_of_nodes--;
  }
} // SingleLinkedList

class Node
{
  int item;
  Node next;
  Node (int item)
  {
    this.item = item;
    next = null;
  }
  Node (SimpleInput sin) throws IOException
  {
    System.out.print("Enter item to be inserted : "); 
    item = sin.readInt();
    next = null;
  }
  Node (SimpleInput sin, Node nextNode) throws IOException
  {
    System.out.print("Insert what? : "); 
    item = sin.readInt();
    next = nextNode;
  }
} // Node
