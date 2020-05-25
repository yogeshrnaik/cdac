import ncst.pgdst.*;
class Number
{
  int val;
  String id;
  Number(String id) {val = 0; this.id = id;}
}
class Node
{
  Object data;
  Node next;
  Node(String str)
   {  this.data = new String(str); next = null;}
  Node(Number num)
       {
         data = new Number(num.id);
         next = null;
       }
  
}
public class Run
{
  Node top,temp,curr;
  void push(Node tmp)
  { if(top == null) top = tmp;else tmp.next = top;top = tmp;}
  void pop()
  { top = top.next;}
  Run(SimpleInput sin) throws IOException
  {
    String str;
    top = null;
   while(!sin.eof())
    {
      str = sin.readWord();
      sin.skipWhite();
      if(str.equals("start"))
        {
           Node temp = new Node(str);
           push(temp);
        }
      else if(str.equals("var"))
        {
           String ch1 = sin.readWord();
           Number num = new Number(ch1);
           Node temp =  new Node(num);
           push(temp);
        }
      else if(str.equals("print"))
        {
           String ch2 = sin.readWord();
           curr = top;
           boolean done = false;
           while(curr.next!= null && !done)
           {
            if(curr.data instanceof Number)
            if(((Number)curr.data).id.equals(ch2))
             { System.out.println(((Number)curr.data).val);
               done = true;
             }
             else
             curr = curr.next;
           }
           if(!done) System.out.println("error");
        }
       else if(str.equals("end"))
        {
          while(!(top.data instanceof String))
          {
            pop();
          }
          pop();
        }
        else
        {
           curr = top;
           boolean done = false;
           while(curr.next != null && !done)
           {
            if((!(curr.data instanceof String)) && (((Number)(curr.data)).id.equals(str)))
                   done = true; // System.out.println(((Number)(curr.data)).id);          
            else
               curr = curr.next;
           }
                   String str1 = sin.readWord();
                   if(str1.equals("="))
                   {
                    int no = sin.readInt();
                    ((Number)curr.data).val = no;
                   }
                   else if(str1.equals("+="))
                   {
                    int n = sin.readInt();
                    ((Number)curr.data).val += n; 
                   }                          
        }
    }
  }// Run
  public static void main(String [] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    Run program = new Run(sin);
  }
}



