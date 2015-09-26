import ncst.pgdst.*;
class Person
{
  int age;
  String name;
  Person nextPerson;
  Person (SimpleInput sin) throws IOException
  {
    name = sin.readWord();
    age =  sin.readInt();
    nextPerson = null;
  }
} // Person
public class LinkList
{
  Person firstPerson;
  int no_of_persons;
  LinkList()
  {
    no_of_persons = 0;
    firstPerson = null;
  }

  public static void main(String[] args) throws IOException
  {
    LinkList list = new LinkList();
    list.readInstructions(new SimpleInput());
  } // main

  public void readInstructions(SimpleInput sin) throws IOException
  {
    boolean stop = false;
    while (!stop)
    {
      String command = sin.readWord();
      if (command.equals("stop"))  break;
      if (command.equals("insert"))
        addPerson(new Person(sin));
      if (command.equals("remove"))
        removePerson(sin.readInt());
      if (command.equals("print"))
        print(sin.readInt());
    }
  }

  public void addPerson(Person newPerson)
  {
    if (firstPerson == null)                  //List is empty
        firstPerson = newPerson;
    else if (newPerson.age < firstPerson.age) //Add at the start
    {
      newPerson.nextPerson = firstPerson;
      firstPerson = newPerson;
    }
    else                                      //Add in between & at the end
    {
      Person curr=firstPerson, prev=null;
      for (; curr!=null && curr.age <= newPerson.age; curr=curr.nextPerson)
             prev = curr;
      newPerson.nextPerson = prev.nextPerson;
      prev.nextPerson = newPerson;
    }
    no_of_persons++;
  }
  
  public void removePerson(int index)
  {
    if (index >= 1 && index <= no_of_persons)
    {
      if (index==1)
        firstPerson = firstPerson.nextPerson;
      else
      {
        Person curr = firstPerson;
        Person prev = null;
        for (int skip=1; skip<index; skip++)
        {  prev = curr;
           curr = curr.nextPerson;
        }
        prev.nextPerson = curr.nextPerson;
      }
      no_of_persons--;
    }    
  }

  public void print(int index)
  {
    if (index >= 1 && index <= no_of_persons)
    {
       Person curr = firstPerson;
       for (int skip=1; skip<index; skip++)
            curr = curr.nextPerson;
       System.out.println(curr.name + " " + curr.age);
    }
  }
} // LinkList
