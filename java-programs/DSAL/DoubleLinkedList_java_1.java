class Node
{
   int no;
   Node prev;
   Node next;
   Node (int no)
   {
      this.no = no;
      prev = next = null;
   }
   Node (SimpleInput sin) throws IOException
   {
      no = sin.readInt();
      prev = next = null;
   }
}
public class DoubleLinkedList
{
  int no_of_nodes;
  Node firstNode;
  DoubleLinkedList()
  {
    no_of_nodes = 0;
    firstNode = null;
  }

  public void readList(SimpleInput in) throws IOException
  {
    boolean stop = false;
    Node curr = null;
    while (!stop)
    {
      int temp = in.readInt();
      if (temp == -1)
        break;
      if (size == 0)
      {
        head = new Node(temp);
        curr = head;
      }
      else 
      {
          curr.next = new Node(temp);
          curr = curr.next;
      }
      size++;
    }
  }

  public void insertAt (int index)
  {
  }
  public void insertThis (Node newNode)
  {
  }
  public void delNodeAt (int index)
  {
  }
  public void delThis (int no)
  {
  }
} // DoubleLinkedList
