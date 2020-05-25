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

class Restriction
{
  int no_of_conditions;
  String condition[];
  Restriction (SimpleInput sin) throws IOException
  {
    no_of_conditions = sin.readInt();
    condition = new String[no_of_conditions];
    for (int i=0; i<no_of_conditions; i++)
       condition[i] = sin.readWord();
  }
} // Restriction

public class DecisionTree_Old
{
  Decision root;
  int no_of_restrictions;
  Restriction restriction[];

  DecisionTree_Old (SimpleInput sin) throws IOException
  {
    root = null;
    constructTree(sin);
    no_of_restrictions = sin.readInt();
    restriction = new Restriction[no_of_restrictions];
    for (int i=0; i<no_of_restrictions; i++)
       restriction[i] = new Restriction(sin);
    makeDecision();
  }

  public static void main (String[] args) throws IOException
  {
    DecisionTree_Old dt = new DecisionTree_Old(new SimpleInput());
  }

  public void constructTree (SimpleInput sin) throws IOException
  {
    boolean end = false;
    while (!end)
    {
      String s1 = sin.readWord();
      String s2 = sin.readWord();
     
      if (s1.equals("END") && s2.equals("DATA"))
         break;
      else
      {
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
        }
      } // else
    } // while
  } // constructTree

  public void makeDecision()
  {
    for (int i=0; i<no_of_restrictions; i++)
    {
      Decision curr = root;
      for (int j=0; j<restriction[i].no_of_conditions; j++)
      {
        switch (restriction[i].condition[j].charAt(0))
        {
          case '!' : if (curr.left != null)
                        curr = curr.left;
                     break;
          default  : if (curr.right != null)
                        curr = curr.right;
                     break;
        } // switch
      } // for
      if (curr.left == null && curr.right == null)
         System.out.println (curr.data);
      else
      {
        if (curr.left != null)
           System.out.print (curr.left.data);
        if (curr.right != null)
          System.out.println (" " + curr.right.data);
        else System.out.println();
      }
    } // for
  } // makeDecision
 
} // DecisionTree
