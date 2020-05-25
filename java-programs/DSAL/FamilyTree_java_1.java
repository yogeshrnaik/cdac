import ncst.pgdst.*;
class Person
{
  String name;
  int no_of_children;
  Person leftChild, rightChild;
  Person (String name)
  {
    this.name = name;
    no_of_children = 0;
    leftChild = rightChild = null;
  }
  Person (String name, int no, Person left, Person right)
  {
    this.name = name;
    no_of_children = no;
    leftChild = left;
    rightChild = right;
  }
} // Person
class Relation
{
  String parent, child;
  boolean isRelative;
  Relation (SimpleInput sin) throws IOException
  {
    parent = sin.readWord();
    child = sin.readWord();
    isRelative = false;
  }
} // Relation
public class FamilyTree
{
  int no;
  Person ancestor;
  Relation relative[];
  FamilyTree (SimpleInput sin) throws IOException
  {
    ancestor = null;
    constructTree(sin);
    no = sin.readInt();
    relative = new Relation[no];
    for (int i=0; i<no; i++)
    {
      relative[i] = new Relation(sin);
      relative[i].isRelative = setRelation(relative[i].parent, relative[i].child);
    }
    print();
  }
  public static void main (String[] args) throws IOException
  {
     FamilyTree family = new FamilyTree(new SimpleInput());
  }
  public void constructTree(SimpleInput sin) throws IOException
  {
    while (true)
    {
      String parent = sin.readWord();
      if (parent.equals("END")) break;
      int n = sin.readInt();
      if (n == 1)
      {
        String child1 = sin.readWord();
        if (ancestor == null)
          ancestor = new Person(parent, n, new Person(child1), null);
        else
        {
          Person p = search(ancestor, parent);
          p.leftChild = new Person(child1);
        }
      } // if (no==1)
      else if (n == 2)
      {
        String child1 = sin.readWord(), child2 = sin.readWord();
        if (ancestor == null)
          ancestor = new Person(parent, n, new Person(child1), new Person(child2));
        else
        {
          Person p = search(ancestor, parent);
          p.leftChild  = new Person(child1);
          p.rightChild = new Person(child2);
        }
      } // else if
    } // while
  } // constructTree

  public Person search (Person person, String aName)
  {
    Person p1=null, p2=null;
    if (person.name.equals(aName))
       return person;
    if (person.leftChild != null)
      p1 = search (person.leftChild, aName);
    if (p1 != null) return p1;
    if (person.rightChild != null)
      p2 = search (person.rightChild, aName);
    if (p2 != null) return p2;
    return null;
  }
  public boolean setRelation (String parent, String child)
  {
     Person p = search (ancestor, parent);
     Person c = search (p, child);
     return (c != null);
  }

  public void print()
  {
    for (int i=0; i<no; i++)
    {
      if (relative[i].isRelative)
         System.out.println("YES");
      else
         System.out.println("NO");
    }
  }

} // FamilyTree
