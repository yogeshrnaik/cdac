import ncst.pgdst.*;
class Node
{
  int no;
  Node left, right;
  Node (int no)
  {  this.no = no; left = null; right = null;
  }
} // Node
public class BSTSimilar
{
   Node root;
   int depth;
   BSTSimilar()
   {  root = null;
      depth = 0;
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

   public static boolean check (Node r1, Node r2)
   {
      boolean flag;
      if (r1==null && r2==null)
          return true;
      if (r1!=null && r2!=null)
          flag = check(r1.left, r2.left);
      else return false;
      if (flag)
         flag = check(r1.right, r2.right);
      return flag;
   }

   public static void main (String[] args) throws IOException
   {
      SimpleInput sin = new SimpleInput();
      int no = sin.readInt();
      BSTSimilar tree[] = new BSTSimilar[no];
      for (int i=0; i<no; i++)
      {
        tree[i] = new BSTSimilar();
        tree[i].read(sin);
        tree[i].depth = tree[i].getDepth(tree[i].root, 1);
        if (i != 0)
        {
          if (check(tree[0].root, tree[i].root))
            System.out.println("YES 0");
          else
            System.out.println("NO "+Math.abs(tree[0].depth-tree[i].depth));
        }
      }
   } // main
} // BinaryTree
