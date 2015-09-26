import ncst.pgdst.*;
class Decision
{
  String data;
  Decision left, right;
  Decision (String data)
  {
    this.data = data;
    left = right = null;
  }
} // Decision
public class DecisionTree
{
  Decision root;
  int no_of_conditions;
  String condition[];
  DecisionTree (SimpleInput sin) throws IOException
  {
    root = null;
    constructTree(sin);
    no_of_conditions = sin.readInt();
    condition = new String[no_of_conditions];
    for (int i=0; i<no_of_conditions; i++)
    {
      int no = sin.readInt();
      for (int j=0; j<no; j++)
          condition[i] = sin.readWord();
    }
    makeDecision();
  }
  public static void main (String[] args) throws IOException
  {
    DecisionTree dt = new DecisionTree(new SimpleInput());
  }

  public void constructTree (SimpleInput sin) throws IOException
  {
    boolean end = false;
    while (!end)
    {
      String s1 = sin.readWord();
      String s2 = sin.readWord();
      if (s1.equals("END") && s2.equals("DATA")) break;

      if (s1.equals("()"))
         root = new Decision(s2);
      else
      {
        Decision curr = root;
        for (int i=1; i<s1.length()-1; i++)
        {
           switch (s1.charAt(i))
           {
             case 'L' : if (i != s1.length()-2) curr = curr.left;
                        else curr.left = new Decision(s2);
                        break;
             case 'R' : if (i != s1.length()-2) curr = curr.right;
                        else curr.right = new Decision(s2);
                        break;
           }
        } // for
      } // else
    } // while
  } // constructTree

  public void makeDecision()
  {
    for (int i=0; i<no_of_conditions; i++)
    {
      Decision curr = null;
      if (condition[i].charAt(0) == '!')
        curr = search (root, condition[i].substring(1));
      else
        curr = search (root, condition[i]);

      if (curr.left == null && curr.right == null)
         System.out.println (curr.data);
      else
      {
        if (condition[i].charAt(0) == '!')
           print (curr.left);
        else
          print (curr.right);
        System.out.println();
      }
    } // for
  } // makeDecision

  public Decision search (Decision decision, String condition)
  {
    Decision p1=null, p2=null;
    if (decision.data.equals(condition))
       return decision;
    if (decision.left != null)
      p1 = search (decision.left, condition);
    if (p1 != null)
      return p1;

    if (decision.right != null)
      p2 = search (decision.right, condition);
    if (p2 != null)
      return p2;
    return null;
  }

  public void print (Decision curr)
  {
    if (curr.left == null && curr.right == null)
      System.out.print (curr.data + " ");
    else
    {
      if (curr.left != null)
        print (curr.left);
      if (curr.right != null)
        print (curr.right);
    }
  } // print
 
} // DecisionTree
