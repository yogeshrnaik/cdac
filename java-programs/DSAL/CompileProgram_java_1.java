import ncst.pgdst.*;
class Brace
{
   char ch;
   Brace nextBrace;
   Brace (char ch, Brace nextBrace)
   {
      this.ch = ch;
      this.nextBrace = nextBrace;
   }
}
public class CompileProgram
{
  Brace firstBrace;
  int no_of_braces;
  CompileProgram ()
  {
    firstBrace = null;
    no_of_braces = 0;
  }
  public boolean isOpeningBrace(char ch)
  {
     return (ch == '(' || ch == '[' || ch == '{');
  }
  public boolean isClosingBrace(char ch)
  {
     return (ch == ')' || ch == ']' || ch == '}');
  }
/**********************************************************************/
  public void check (SimpleInput sin) throws IOException
  {
     boolean valid = true;
     while (valid && !sin.eof())
     {
       String input = sin.readLine();
       for (int i=0; valid && i<input.length(); i++)
       {
         char ch = input.charAt(i);
         if (isOpeningBrace(ch))
            push (new Brace(ch, null));
         else if (isClosingBrace(ch))
         {
           Brace brace = pop();
           if (brace == null)
           { valid = false;
             System.out.println("Too many closing Braces.");
             break;
           }
           switch (brace.ch)
           {
             case '(' : if (ch != ')') valid = false; break;
             case '[' : if (ch != ']') valid = false; break;
             case '{' : if (ch != '}') valid = false; break;
           } // switch
         } // else
       } // for
     } // while
/**********************************************************************/
     if (no_of_braces != 0)
     {   valid = false;
         System.out.println("Too many opening Braces.");
     }
     if (valid) System.out.println("Valid");
     else System.out.println("Invalid");
  }
  public void push (Brace newBrace) // insert at the beginning
  {
     newBrace.nextBrace = firstBrace;
     firstBrace = newBrace;
     no_of_braces++;
  }
  public Brace pop ()
  {
     Brace curr = firstBrace;
     if (firstBrace != null)
     { firstBrace = firstBrace.nextBrace;
       no_of_braces--;
     }
     return curr;
  }
  public static void main (String[] args) throws IOException
  {
     CompileProgram stack = new CompileProgram();
     stack.check(new SimpleInput());
  }
} // CompileProgram
/**********************************************************************/
