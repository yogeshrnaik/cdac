import ncst.pgdst.*;
class Vertex
{
  int id;
  int time;
  Vertex next;
  Vertex ()
  {
    id = -1;
    time = -1;
    next = null;
  }
  Vertex (int d, int t)
  {
    id = d;
    time = t;
  }
} // Vertex

public class Graph_List
{
  int height;
  int edges, vertices;
  Vertex[] vertex;
  Graph_List (SimpleInput sin) throws IOException
  {
    height = sin.readInt();
    edges = sin.readInt();
    vertices = sin.readInt();
    vertex = new Vertex[vertices+1];
    for (int i=0; i<=vertices; i++)
      vertex[i] = new Vertex();
    for (int i=1; i<=edges; i++)
    {
      int c1 = sin.readInt();
      int c2 = sin.readInt();
      int t = sin.readInt();
      int depth = sin.readInt();
      if (depth < height)
      {
        add (c1, new Vertex (c2, t));
        add (c2, new Vertex (c1, t));
      } // if (depth < height)
    } // for
    print();
  } // constructor

  public void add (int c, Vertex newVertex)
  {
     if (vertex[c].next == null)
       vertex[c].next = newVertex;
     else
     {
        Vertex curr = vertex[c], prev = null;
        for (; curr!=null && newVertex.time>curr.time; curr=curr.next)
           prev = curr;
        newVertex.next = prev.next;
        prev.next = newVertex;
      } // else
  } // add

  public static void main (String[] args) throws IOException
  {
    Graph_List graph = new Graph_List (new SimpleInput());
  } // main

  public void print()
  {
    for (int i=1; i<=vertices; i++)
    {
      Vertex curr = vertex[i].next;
      for (; curr!=null; curr=curr.next)
      {
        System.out.print ("From " +i+ " To " +curr.id);
        System.out.println (" in Time "+curr.time);
      }
    }
  } // print
} // Graph
