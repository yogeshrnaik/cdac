import ncst.pgdst.*;
class Statement
{
  String s1;            // main, endmain, var, print
  String s2;            // variable name, operator = or +=
  int no;               // value of the variable
  Statement next;       // next statement
  Statement (String s1)
  {
    this.s1 = s1;
    s2 = "";  no = -1;
    next = null;
  }
  Statement (String s1, String s2, int no)
  {
    this.s1 = s1;
    this.s2 = s2;
    this.no = no;
    next = null;
  }
} // Statement
public class RunProgram
{
  Statement head;
  RunProgram (SimpleInput sin) throws IOException
  { head = null;
    executeProgram (sin);
  }
  public static void main (String[] args) throws IOException
  {
    RunProgram rp = new RunProgram (new SimpleInput());
  }
/********************************************************************/
  public void executeProgram (SimpleInput sin) throws IOException
  {
    while (true)
    {
      boolean flag = false;
      String s1 = sin.readWord();
      if (s1.equals("endmain"))
        break;
      if (s1.equals("main") || s1.equals("start"))
      {
        push (new Statement(s1));
        flag = true;
      }
      if (s1.equals("end"))
      {
        pop();
        flag = true;
      }
      if (s1.equals("var"))
      {
        push (new Statement (s1, sin.readWord(), 0));
        flag = true;
      }
      if (s1.equals("print"))
      {
        Statement curr = search (sin.readWord());
        if (curr != null)
          System.out.println(curr.no);
        else
          System.out.println("ERROR");
        flag = true;
      }
/********************************************************************/
      if (!flag)                // Expression x=10 or x+=10
      {
        Statement curr = search (s1);
        String op = sin.readWord();
        if (op.equals("="))
          curr.no = sin.readInt();
        else curr.no += sin.readInt();
      }
    } // while
  } // executeProgram

  public void push (Statement newStatement)
  {
    if (head != null)
      newStatement.next = head;
    head = newStatement;
  }

  public void pop ()
  {
     for (; !head.s1.equals("start"); head = head.next);
     head = head.next;
  }

  public Statement search (String s)
  {
    Statement curr = head;
    for (; curr!=null && !curr.s2.equals(s); curr=curr.next);
    return curr;
  }
} // RunProgram
/********************************************************************/
