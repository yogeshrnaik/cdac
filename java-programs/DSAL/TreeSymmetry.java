import ncst.pgdst.*;
class Node
{ int no;
  Node left, right;
  Node (int no)
  { this.no = no; left = right = null;
  }
} // Node
public class TreeSymmetry
{
  Node root;
  TreeSymmetry (SimpleInput sin) throws IOException
  {
    root = null;
    read (sin);
    if (root != null)
    {
      if (root.left != null && root.right != null)
      {
          if (isSymmetric(root.left, root.right))
            System.out.println ("The tree is Symmetric.");
          else
            System.out.println ("The tree is not Symmetric.");
      }  
      else if (root.left == null && root.right == null)
        System.out.println ("The tree is Symmetric.");
      else
        System.out.println ("The tree is not Symmetric.");
    }
    else
      System.out.println ("The tree is empty.");
  }
/************************************************************/
  public static void main (String[] args) throws IOException
  {
    TreeSymmetry tree = new TreeSymmetry (new SimpleInput());
  } // main
  public boolean isSymmetric (Node r1, Node r2)
  {
    boolean flag = true;
    if (r1.left == null)
    {
      if (r1.right == null)
      { if (r2.left != null || r2.right != null) return false;
      }
      else
      { if (r2.left == null || r2.right != null) return false;
      }
    }
    else        // r1.left != null hence r2.right must not be == null
    {
      if (r1.right != null)
      { if (r2.left == null || r2.right == null) return false;
      }
      else      // r1.right == null hence r2.left must be == null
      { if (r2.left != null || r2.right == null) return false;
      }
    }
    if (r1.left != null && r2.left != null)
      flag = isSymmetric (r1.left, r2.left);
    if (flag)
      if (r1.right != null && r2.right != null)
        flag = isSymmetric (r1.right, r2.right);
    return flag;
  }
/******************************END***************************************/
  public void read (SimpleInput sin) throws IOException
  {
     while (true)
     {
        int temp = sin.readInt();
        if (temp == -1)
           break;
        else
           insert (root, new Node(temp));
     } // while
  } // read

  public void insert (Node aRoot, Node newNode)
  {
     if (root == null)
     {  root = newNode;
        return;
     }
     if (newNode.no < aRoot.no)
     {  if (aRoot.left == null)
          aRoot.left = newNode;
        else
          insert (aRoot.left, newNode);
     }
     else
     {  if (aRoot.right == null)
          aRoot.right = newNode;
        else
          insert (aRoot.right, newNode);
     }
  } // insert

} // TreeSymmetry
/******************************************************************/
