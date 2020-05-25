import ncst.pgdst.*;
class Process
{
  int id;
  Process next;
  Process (int id)
  {
    this.id = id;
    next = null;
  }
} // Process

public class DeadLock
{
  Process[] process;
  int nop;
  int[] visited;
  int size;
  DeadLock (SimpleInput sin) throws IOException
  {
    int row = sin.readInt();
    int col = 3;
    int p[] = new int[3*row];
    int pSize = 0;
    int[][] matrix = new int[row][col];
/*********************************************/
    for (int i=0; i<row; i++)
    {
      for (int j=0; j<col; j++)
      {
         matrix[i][j] = sin.readInt();
         if (matrix[i][j] != -1)
         {
           int k=0;
           for (; k<pSize; k++)
             if (p[k] == matrix[i][j]) break;
           if (k==pSize)
            p[pSize++] = matrix[i][j];
         }
      }
    }
    nop = pSize;
    process = new Process[nop];
    for (int i=0; i<nop; i++)
      process[i] = new Process(p[i]);
    for (int i=0; i<row; i++)
    {
      if (matrix[i][0] != -1)
      {
        if (matrix[i][1] != -1)
        {
          Process curr = search(matrix[i][1]);
          add (curr, new Process(matrix[i][0]));
        }
        if (matrix[i][2] != -1)
        {
          Process curr = search(matrix[i][0]);
          add (curr, new Process(matrix[i][2]));
        }
      }
    }
/****************************************************/
    visited = new int[3*nop];
    size = 0;
  } // constructor

  public static void main (String[] args) throws IOException
  {
    DeadLock dl = new DeadLock (new SimpleInput());
    for (int i=0; i<dl.nop; i++)
    {
      if (i != 0)
      {
        Process temp = dl.process[0];
        dl.process[0] = dl.process[i];
        dl.process[i] = temp;
      }
      dl.check (dl.process[0]);
    }
    System.out.println("NO");
  } // main

  public Process search (int id)
  {
    for (int i=0; i<nop; i++)
     if (process[i].id == id)
       return process[i];
    return null;
  }

  public void add (Process curr, Process newProcess)
  {
    Process prev = null;
    for (; curr!=null; curr=curr.next)
      prev = curr;
    prev.next = newProcess;
  }
/****************************************************/
  public void check (Process curr)
  {
    Process p = search (curr.id);
    if (p != null)
    {
      if (!isVisited(p.id))
      {
        addToVisited (p.id);
        Process temp = p.next;
        for (; temp!=null; temp=temp.next)
          check(temp);
        removeFromVisited(p.id);
      }
      else
      {
        System.out.println("YES");
        System.exit(1);
      }
    }
  } // check

  public boolean isVisited (int id)
  {
    for (int i=0; i<size; i++)
     if (visited[i] == id)
       return true;
    return false;
  } // isVisited
/****************************************************/
  public void addToVisited (int id)
  {
    visited[size] = id;
    size++;
  } // addToVisited

  public void removeFromVisited (int id)
  {
    for (int i=size; i>=0; i--)
    {
      int temp = visited[i];
      visited[i] = -1;
      if (size != 0)
        size--;
      if (temp == id)
        break;
    }
  } // removeFromVisited
} // DeadLock
