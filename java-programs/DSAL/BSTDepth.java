import ncst.pgdst.*;
class Node
{
  int no;
  Node left, right;
  Node (int no)
  {  this.no = no; left = null; right = null;
  }
} // Node
public class BSTDepth
{
   Node root;
   BSTDepth()
   {  root = null;
   }
   public void read (SimpleInput sin) throws IOException
   {
      boolean stop = false;
      while (!stop)
      {
         int temp = sin.readInt();
         if (temp == -1)
            break;
         else
            insert (temp);
      } // while
   } // read

   public void insert (int no)
   {
      Node p = root, prev = null;
      if (root == null)
      {
         root = new Node(no);
         return;
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
   }

   public int getMinDepth (Node aRoot, int depth)
   {
      int min1=0, min2=0;

      if (aRoot.left == null && aRoot.right == null)
          return depth;
      
      if (aRoot.left != null)
        min1 = getMinDepth(aRoot.left, depth+1);
      if (aRoot.right != null)
        min2 = getMinDepth(aRoot.right, depth+1);
      if (min1 != 0 && min2 != 0)
        return Math.min(min1, min2);
      else return Math.max(min1, min2);

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
   
   public static void main (String[] args) throws IOException
   {
      SimpleInput sin = new SimpleInput();
      BSTDepth tree = new BSTDepth();
      tree.read(sin);
      int minDepth = tree.getMinDepth(tree.root, 1);
      int maxDepth = tree.getDepth(tree.root, 1);
      System.out.println("Minimum Depth = " + minDepth);
      System.out.println("Maximum Depth = " + maxDepth);
   } // main
} // BinaryTree
