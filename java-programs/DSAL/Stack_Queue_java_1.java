import ncst.pgdst.*;
class Letter
{
  char ch;
  Letter next;
  Letter (char ch, Letter next)
  {
     this.ch = ch;
     this.next = next;
  }
} // Letter

public class Stack_Queue
{
  String input;
  Letter front, back;
  Letter top;
  Stack_Queue (SimpleInput sin) throws IOException
  {
    input = sin.readWord();
    front = back = top = null;
  }

  public void push (Letter letter)
  {
     letter.next = top;
     top = letter;
  }

  public Letter pop()
  {
    Letter curr = top;
    if (top != null)
       top = top.next;
    return curr;
  }

  public void enqueue (Letter letter)
  {
    if (front == null)                  // Queue was empty
       front = back = letter;
    else
    {
       back.next = letter;
       back = letter;
    }
  }

  public Letter dequeue ()
  {
     Letter curr = front;
     if (front == back)
         back = null;
     if (front != null)
         front = front.next;
     return curr;
  }
  
  public boolean isPalindrome ()
  {
     for (int i=0; i<input.length(); i++)
     {
        char ch = input.charAt(i);
        push(new Letter(ch, null));
        enqueue(new Letter(ch, null));
     }
     int count=0;
     while (count++<input.length() && pop().ch == dequeue().ch);
     return (count >= input.length());
  }

  public static void main (String[] args) throws IOException
  {
     SimpleInput sin = new SimpleInput();
     Stack_Queue demo = new Stack_Queue(sin);
     if (demo.isPalindrome())
       System.out.println("The word is a Palindrome.");
     else
       System.out.println("The word is not a Palindrome.");
  }
}
