import ncst.pgdst.*;
class Node
{
        int data;
        Node next;
        Node (int d)
        {
                data = d;
                next = null;
        }
}
public class DsalTrial
{
        Node head;
        public static void main (String[] args) throws IOException
        {
                DsalTrial list = new DsalTrial();
                SimpleInput sin = new SimpleInput();
                while (true)
                {
                        int d = sin.readInt();
                        if (d==-1) break;
                        if (list.head == null)
                                list.head = new Node(d);
                        else
                        {
                           Node curr = list.head;
                           for (; curr.next!=null; curr=curr.next);
                           curr.next = new Node(d);
                        }
                }

                for (Node curr=list.head; curr!=null; curr=curr.next)
                   System.out.print (curr.data + " ");
                System.out.println();
        }
}

