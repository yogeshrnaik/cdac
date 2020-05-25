import ncst.pgdst.*;
class Node
{
  int no;
  Node left, right;
  Node (int no)
  {  this.no = no;
     left = right = null;
  }
} // Node
public class MaxDelete
{
  Node root;
  int depth;
  int leaves;
  MaxDelete () throws IOException
  {
    root = new Node(-1);
    SimpleInput sin = new SimpleInput();
    depth = sin.readInt();
    leaves = sin.readInt();
    constructTree(root, 0, sin);
    update (root);
    for (int i=1; i<=3; i++)
    {
      boolean flag = delete (root, root.no);
      update (root);
      print_inorder(root);
      System.out.println();
    }
  }
  public static void main (String[] args) throws IOException
  {
    MaxDelete maxdel = new MaxDelete();
  } // main

  public void constructTree (Node curr, int d, SimpleInput sin)
            throws IOException
  {
    if (d <= depth)
    {
      if (d == depth-1)
      {
        curr.left = new Node(sin.readInt());
        curr.right = new Node(sin.readInt());
      }
      else
      {
        curr.left = new Node(-1);
        curr.right = new Node(-1);
        constructTree (curr.left, d+1, sin);
        constructTree (curr.right, d+1, sin);
      }
//      curr.no = Math.max (curr.left.no, curr.right.no);
// if this statement is not commented then it is not necessary
// to call the update method before the for loop in the
// constructor

    }
  } // constructTree

  public void update (Node curr)
  {
    if (curr.left.left == null && curr.left.right == null)
    {  if (curr.right.left == null && curr.right.right == null)
           curr.no = Math.max (curr.left.no, curr.right.no);
    }
    else
    {
       update (curr.left);
       update (curr.right);
       curr.no = Math.max (curr.left.no, curr.right.no);
    }
  }
  public boolean delete (Node curr, int n)
  {
    boolean flag = false;
    if (curr.left == null && curr.right == null)
    {
      if (curr.no == n)
      {
        curr.no = -1;
        return true;
      }
    }
    else
    {
      flag = delete (curr.left, n);
      if (!flag)
       flag = delete (curr.right, n);
    }
    return flag;
  } // delete
  public void print_inorder (Node curr)
  {
    if (curr != null)
    {
        print_inorder(curr.left);
        System.out.print (curr.no + " ");
        print_inorder (curr.right);
    }
  }
} // MaxDelete
