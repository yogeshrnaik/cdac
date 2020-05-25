import ncst.pgdst.*;
class Person
{
    int arrival;
    Person next;
    Person (int arrival)
    {
       this.arrival = arrival;
       next = null;
    }
} // Person

public class Queue
{
  Person front, back;
  int no_of_persons;
  int total_time;
  Queue ()
  {
    no_of_persons = 0;
    front = back = null;
  }

  public static void main (String[] args) throws IOException
  {
     Queue queue = new Queue();
     queue.read(new SimpleInput());
     queue.serve();
     queue.print();
  } // main

  public void read (SimpleInput sin) throws IOException
  {
     total_time = sin.readInt();
     boolean stop = false;
     while (!stop)
     {
       int time = sin.readInt();
       if (time == -1) break;
       else if (time <= total_time)
          enqueue (new Person (time));
     }
  } // read

  public void enqueue (Person newPerson)
  {
     if (front == null)         // queue is empty
        front = back = newPerson;
     else
     {
        back.next = newPerson;
        back = newPerson;
     }
     no_of_persons++;
  }

  public void serve ()
  {
     for (int instance=0; instance <= total_time;)
     {
        if (front != null && front.arrival <= instance)
        {
           dequeue();
           instance += 3;
        }
        else instance++;
     }
  }

  public void dequeue ()
  {
     if (front == back)         // only one element is present
         back = null;
     front = front.next;
     no_of_persons--;
  }

  public void print ()
  {
     System.out.print (no_of_persons);
     if (no_of_persons != 0)
     {
        Person curr = front;
        for (; curr!=null; curr=curr.next)
            System.out.print (" "+curr.arrival);
     }
     System.out.println();
  }
} // Queue
