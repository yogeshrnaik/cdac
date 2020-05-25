import ncst.pgdst.*;
class Node
{
  int depth;
  int data;
  Node left, right;
  Node (int n, int d)
  {
     data  = n;
     depth = d;
     left  = right = null;
  }
} // Node

public class TreeNodeDepth
{
  Node root;
  TreeNodeDepth (SimpleInput sin) throws IOException
  {
     root = null;
     read (sin);
     inorder(root);
  }
  public static void main (String[] args) throws IOException
  {
    TreeNodeDepth tree = new TreeNodeDepth(new SimpleInput());
  } // main
/************************************************************/
  public void read (SimpleInput sin) throws IOException
  {
     while (true)
     {
        int temp = sin.readInt();
        if (temp == -1) break;
        else
           insert (temp);
     } // while
  } // read
  public void insert (int n)
  {
     Node p = root, prev = null;
     if (root == null)
     {
        root = new Node(n, 0);
        return;
     }
     while (p != null)
     {
        prev = p;
        if (n < p.data) p = p.left;
        else p = p.right;
     }
     if (n < prev.data)
        prev.left = new Node(n, prev.depth+1);
     else
        prev.right = new Node(n, prev.depth+1);
  } // insert
/************************************************************/
  public void inorder (Node aRoot)
  {
    if (aRoot != null)
    {
       inorder (aRoot.left);
       System.out.println (aRoot.data + " " + aRoot.depth);
       inorder (aRoot.right);
    }
  } // inorder
} // TreeNodeDepth


