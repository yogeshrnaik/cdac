import ncst.pgdst.*;
class Node
{
  int no;
  Node left, right;
  Node (int no)
  {  this.no = no; left = null; right = null;
  }
} // Node
public class BinaryTree
{
   Node root;
   BinaryTree()
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

   public void preorder (Node aRoot)
   {
     if (aRoot != null)
     {
        System.out.print (aRoot.no + " ");
        preorder (aRoot.left);
        preorder (aRoot.right);
     }
   } // preorder

   public void inorder (Node aRoot)
   {
     if (aRoot != null)
     {
        inorder (aRoot.left);
        System.out.print (aRoot.no + " ");
        inorder (aRoot.right);
     }
   } // inorder

   public void postorder (Node aRoot)
   {
     if (aRoot != null)
     {
        postorder (aRoot.left);
        postorder (aRoot.right);
        System.out.print (aRoot.no + " ");
     }
   } // postorder

   public static void main (String[] args) throws IOException
   {
      SimpleInput sin = new SimpleInput();
      BinaryTree tree = new BinaryTree();
      tree.read(sin);

      System.out.print("The contents in pre-order are : ");
      tree.preorder(tree.root);
      System.out.println();

      System.out.print("The contents in in-order are : ");
      tree.inorder(tree.root);
      System.out.println();

      System.out.print("The contents in post-order are : ");
      tree.postorder(tree.root);
      System.out.println();

   } // main
} // BinaryTree
