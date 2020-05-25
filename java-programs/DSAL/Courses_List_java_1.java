import ncst.pgdst.*;
class Subject
{
  String name;
  boolean visited;
  Subject next;
  Subject()
  {
    name = "";
    visited = false;
    next = null;
  }
  Subject (String s)
  {
    name = s;
    visited = false;
    next = null;
  }
} // Subject

public class Courses_List
{
  int nos;  // no_of_subjects
  Subject[] subject;
  Courses_List (SimpleInput sin) throws IOException
  {
    nos = sin.readInt();
    subject = new Subject[nos];
    for (int i=0; i<nos; i++)
    {
      subject[i] = new Subject(sin.readWord());
      Subject curr = subject[i];
      int n = sin.readInt();
      for (int j=1; j<=n; j++)
      {
        curr.next = new Subject (sin.readWord());
        curr = curr.next;
      }
    }
    print();
  }

  public static void main (String[] args) throws IOException
  {
    Courses_List course = new Courses_List(new SimpleInput());
  } // main

  public void print()
  {
    for (int i=0; i<nos; i++)
    {
      System.out.print (subject[i].name + " --> ");
      Subject curr = subject[i].next;
      for (; curr!=null; curr=curr.next)
        System.out.print(curr.name + " ");
      System.out.println();
    }
  } // print
} // Courses
