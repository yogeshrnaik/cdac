import ncst.pgdst.*;
class Vertex
{
  int id;
  int time;
  Vertex next;
  Vertex (int d)
  {
    id = d;
    time = 0;
    next = null;
  }
  Vertex (int d, int t)
  {
    id = d;
    time = t;
    next = null;
  }
} // Vertex
class Path
{
  int size;
  int total_time;
  int[] visited;
  Path ()
  {
    size = 0;
    total_time = 0;
    visited = new int[20];
  }
} // Path
/***************************************************************/
public class ShortestPath
{
  int height;
  int edges, vertices;
  Vertex[] vertex;
  int start, destination;
  Path path;
  Path shortest;

  ShortestPath (SimpleInput sin) throws IOException
  {
    height = sin.readInt();
    edges = sin.readInt();
    vertices = sin.readInt();
    vertex = new Vertex[vertices+1];
    for (int i=0; i<=vertices; i++)
      vertex[i] = new Vertex(i);

    for (int i=1; i<=edges; i++)        // reading & creating Graph
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
/***************************************************************/
    start = sin.readInt();
    destination = sin.readInt();
    path = new Path();
    shortest = new Path();

    Vertex current = vertex[start];
    boolean flag = getPath(current);

    printShortestPath();
  } // constructor

  public static void main (String[] args) throws IOException
  {
    ShortestPath graph = new ShortestPath (new SimpleInput());
  } // main

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
/***************************************************************/
  public boolean getPath (Vertex curr)
  {
    if (!isVisited (curr.id))
    {
      addToPath (curr.id);
      path.total_time += curr.time;
      if (destination == curr.id)
        return true;

      Vertex temp = search(curr.id);
      temp = temp.next;
      for (; temp!=null; temp=temp.next)
      {
        boolean flag = getPath(temp);
        if (flag)
        {
          if (shortest.size == 0 || shortest.total_time > path.total_time)
          {
            shortest.size = path.size;
            shortest.total_time = path.total_time;
            for (int i=0; i<path.size; i++)
              shortest.visited[i] = path.visited[i];
          }
          removeFromPath (temp.id);
          path.total_time -= temp.time;
        }
      }
      path.total_time -= curr.time;
      removeFromPath (curr.id);
    }
    return false;
  } // getPath
/***************************************************************/
  public boolean isVisited (int id)
  {
    for (int i=0; i<path.size; i++)
      if (path.visited[i] == id)
        return true;
    return false;
  }

  public Vertex search (int id)
  {
    for (int i=1; i<=vertices; i++)
      if (vertex[i].id == id)
        return vertex[i];
    return null;
  }

  public void addToPath (int id)
  {
    path.visited[path.size++] = id;
  }

  public void removeFromPath (int id)
  {
    path.visited[path.size] = -1;
    if (path.size != 0)
     path.size--;
  }
/***************************************************************/
  public void printShortestPath ()
  {
     for (int j=0; j<shortest.size; j++)
       System.out.print(shortest.visited[j] + " ");
     System.out.println();
  }
} // ShortestPath
