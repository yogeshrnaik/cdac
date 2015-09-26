import ncst.pgdst.*;
class Vertex
{
  int id;
  int time;
  Vertex next;
  Vertex (int d)
  { id = d;
    time = 0;
    next = null;
  }
  Vertex (int d, int t)
  { id = d;
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
  { size = 0;
    total_time = 0;
    visited = new int[20];
  }
} // Path
/******************************************************/
public class AllPaths
{
  int height;
  int edges, vertices;
  Vertex[] vertex;
  int start, destination, nop;
  Path[] path;
  Path p;
  AllPaths (SimpleInput sin) throws IOException
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
/******************************************************/
    start = sin.readInt();
    destination = sin.readInt();
    path = new Path[20];
    for (int i=0; i<20; i++)
      path[i] = new Path();
    p = new Path();
    nop = 0;

    Vertex current = vertex[start];
    boolean flag = getPath(current);

    sortPaths();
    printPaths();
    printShortestPath();
  } // constructor

  public static void main (String[] args) throws IOException
  {
    AllPaths graph = new AllPaths (new SimpleInput());
  } // main
/******************************************************/
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
  public boolean isVisited (int id)
  {
    for (int i=0; i<p.size; i++)
      if (p.visited[i] == id)
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
/******************************************************/
  public boolean getPath (Vertex curr)
  {
    if (!isVisited (curr.id))
    {
      addToPath (curr.id);
      p.total_time += curr.time;
      if (destination == curr.id) return true;
      Vertex temp = search(curr.id);
      temp = temp.next;
      for (; temp!=null; temp=temp.next)
      {
        boolean flag = getPath(temp);
        if (flag)
        {
          path[nop].size = p.size;
          for (int i=0; i<p.size; i++)
            path[nop].visited[i] = p.visited[i];
          path[nop].total_time = p.total_time;
          nop++;
          removeFromPath (temp.id);
          p.total_time -= temp.time;
        }
      }
      p.total_time -= curr.time;
      removeFromPath (curr.id);
    }
    return false;
  } // getPath
/******************************************************/
  public void addToPath (int id)
  {
    p.visited[p.size++] = id;
  }
  public void removeFromPath (int id)
  {
    p.visited[p.size] = -1;
    if (p.size != 0) p.size--;
  }
  public void printPaths()
  {
    for (int i=0; i<nop; i++)
    {
      System.out.print(path[i].total_time+" minutes required to go via ");
      for (int j=0; j<path[i].size; j++)
        System.out.print(path[i].visited[j] + " ");
      System.out.println();
    }
  } // printPath
  public void printShortestPath ()
  {
     System.out.print("The shortest Path --> ");
     for (int j=0; j<path[0].size; j++)
        System.out.print(path[0].visited[j] + " ");
     System.out.print("in "+path[0].total_time+" minutes.");
     System.out.println();
  }
/******************************************************/
  public void sortPaths ()
  {
    for (int i=0; i<nop-1; i++)
    {
      for (int j=i+1; j<nop; j++)
      {
        if (path[i].total_time > path[j].total_time)
        {
          Path temp = path[i];
          path[i] = path[j];
          path[j] = temp;
        }
        else if (path[i].total_time == path[j].total_time)
        {
          if (path[i].size > path[j].size)
          {
            Path temp = path[i];
            path[i] = path[j];
            path[j] = temp;
          }
        }
      }
    }
  } // sortPaths
} // AllPaths

