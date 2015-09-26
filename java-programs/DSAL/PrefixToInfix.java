import ncst.pgdst.*;
class Node
{
  String data;
  Node left, right;
  Node (String str)
  {
    data = str;
    left = right = null;
  }
} // Node
public class PrefixToInfix
{
  Node root;
  String ip;
  int count;
  PrefixToInfix (SimpleInput sin) throws IOException
  {
    root = null;
    ip = sin.readWord();
    count = 0;
    root = createExpTree();
    System.out.print("Inorder = ");
    inorder(root);
    System.out.println();
    toInfix(root);
    System.out.println("Infix with braces = "+ root.data);
  }
  public static void main (String[] args) throws IOException
  {
    PrefixToInfix pi = new PrefixToInfix (new SimpleInput());
  }
/****************************************************************/
  public Node createExpTree ()
  {
    Node r = null;
    if (count < ip.length())
    {
      String s = ip.substring(count, count+1);
      if (isOperator(s))
      {
        r = new Node(s);
        count++;
        r.left = createExpTree ();
        r.right = createExpTree ();
      }
      else
      {
        count++;
        return new Node (s);
      }
    }
    return r;
  } // createExpTree

  public boolean isOperator(String s)
  {
   return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
  }
/****************************************************************/
  public void inorder (Node curr)
  {
    if (curr != null)
    {
      inorder(curr.left);
      System.out.print(curr.data);
      inorder(curr.right);
    }
  }
  public void toInfix (Node curr)
  {
    if (curr.left != null && curr.right != null)
    {
      toInfix(curr.left);
      toInfix(curr.right);
      curr.data = "(" + curr.left.data + curr.data + curr.right.data + ")";
//      System.out.println("Curr = "+ curr.data);
    }
  }
} // PrefixToInfix
/****************************************************************/
