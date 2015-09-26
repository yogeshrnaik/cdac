import ncst.pgdst.*;
class Node
{
  int no;
  Node left, right;
  Node (int no)
  {  this.no = no; left = null; right = null;
  }
} // Node

public class AVLTree_Old
{
  Node root;
  int rejected[];
  int count;
  AVLTree_Old ()
  {
    root = null;
    rejected = new int[10];
    count = 0;
  }
  public static void main (String[] args) throws IOException
  {
    AVLTree_Old avl = new AVLTree_Old();
    avl.read(new SimpleInput());
    avl.print();
  }
  public void read (SimpleInput sin) throws IOException
  {
    boolean end = false;
    while (!end)
    {
      int temp = sin.readInt();
      if (temp == -1) break;
      else
      {
        Node curr = insert (temp);
        if (!isAVLTree (root))
        {
          if (curr.left != null && curr.left.no == temp)
             curr.left = null;
          else curr.right = null;
          rejected[count] = temp;
          count++;
        }
      } // else
    } // while
  }

  public boolean isAVLTree (Node curr)
  {
    boolean flag = true;
    if (Math.abs(getDepth(curr.left, 1) - getDepth(curr.right, 1)) >= 2)
        return false;
    else
    {
      if (curr.left != null)
        flag = isAVLTree (curr.left);
      if (flag)
        if (curr.right != null)
          flag = isAVLTree (curr.right);
    }
    return flag;
  }

  public Node insert (int no)
  {
     Node p = root, prev = null;
     if (root == null)
     {
        root = new Node(no);
        return root;
     }
     while (p != null)
     {
        prev = p;
        if (no < p.no) p = p.left;
        else p = p.right;
     }
     if (no < prev.no)
        prev.left = new Node(no);
     else
        prev.right = new Node(no);
     return prev;
  }

  public int getDepth (Node aRoot, int depth)
  {
     int max1=0, max2=0;
     if (aRoot == null)  return 0;

     if (aRoot.left == null && aRoot.right == null)
          return depth;
      
     if (aRoot.left != null)
        max1 = getDepth(aRoot.left, depth+1);
     if (aRoot.right != null)
        max2 = getDepth(aRoot.right, depth+1);

     return Math.max(max1,max2);
  }

  public void print()
  {
    for (int i=0; i<count; i++)
      System.out.print (rejected[i] + " ");
    System.out.println(count);
  }

} // AVLTree
