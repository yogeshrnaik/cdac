import ncst.pgdst.*;
class Subject
{
  String name;
  Subject next;
  Subject (String s)
  {
    name = s;
    next = null;
  }
} // Subject

public class Courses
{
  int nos;  // no_of_subjects
  Subject[] subject;
  int size;
  String[] visited;

  Courses (SimpleInput sin) throws IOException
  {
    nos = sin.readInt();
    size = nos;
    subject = new Subject[nos];
    for (int i=0; i<nos; i++)
    {
      subject[i] = new Subject(sin.readWord());
      Subject curr = subject[i];
      int n = sin.readInt();
      size += n;
      for (int j=1; j<=n; j++)
      {
        curr.next = new Subject (sin.readWord());
        curr = curr.next;
      }
    }
    visited = new String[size];
    for (int i=0; i<size; i++)
      visited[i] = new String("");
    size = 0;
    for (int i=0; i<nos; i++)
    {
      if (i != 0)
      {
        Subject temp = subject[0];
        subject[0] = subject[i];
        subject[i] = temp;
      }
      check (subject[0]);
//      size = 0;
    }
    System.out.println("YES");
  } // Constructor

  public static void main (String[] args) throws IOException
  {
    Courses course = new Courses(new SimpleInput());
  } // main

  public void check (Subject curr)
  {
    Subject sub = search (curr.name);
    if (sub != null)
    {
      if (!isVisited(sub.name))
      {
        addToVisited (sub.name);
        Subject temp = sub.next;
        for (; temp!=null; temp=temp.next)
          check(temp);
        removeFromVisited(sub.name);
      }
      else
      {
        System.out.println("NO");
/*        for (int i=0; i<=size; i++)
          System.out.print(visited[i]+" ");
        System.out.println(sub.name);
*/
        System.exit(1);
      }
    }
  } // check

  public Subject search (String s)
  {
    for (int i=0; i<nos; i++)
     if (subject[i].name.equals(s))
       return subject[i];
    return null;
  } // search

  public boolean isVisited (String s)
  {
    for (int i=0; i<size; i++)
     if (visited[i].equals(s))
       return true;
    return false;
  } // isVisited

  public void addToVisited (String s)
  {
    visited[size] = s;
    size++;
  } // addToVisited

  public void removeFromVisited (String s)
  {
    for (int i=size; i>=0; i--)
    {
      String temp = visited[i];
      visited[i] = "";
      if (size != 0) size--;
      if (temp.equals(s)) break;
    }
  } // removeFromVisited
} // Courses
