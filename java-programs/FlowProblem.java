import ncst.pgdst.*;
class Vertex
{
        char name;
        Pipe head;
        Vertex (char name)
        {
                this.name = name;
                head = null;
        }
}
class Pipe
{
        char name;
        int capacity, flow;
        Pipe next;
        Pipe (char n, int c, int f)
        {
                name = n;
                capacity = c;
                flow = f;
        }
}
class Path
{
        int size;
        char visited[];
        int flowIncre;
        Path (int vertices)
        {
                size = 1;
                visited = new char[vertices+10];
                visited[0] = 'S';
                flowIncre = 100;
        }
}
public class FlowProblem
{
        int vertices;
        Vertex vertex[];
        int pipes;
        Path[] path;
        int nopaths;
        Path p;

        public static void main (String[] args) throws IOException
        {
                FlowProblem fp = new FlowProblem (new SimpleInput());
        } // main

        FlowProblem (SimpleInput sin) throws IOException
        {
                vertices = sin.readInt();
                vertex = new Vertex[vertices];
                for (int i=0; i<vertices; i++)
                {
                        sin.skipWhite();
                        vertex[i] = new Vertex(sin.readChar());
                }
                pipes = sin.readInt();
                for (int i=0; i<pipes; i++)
                {
                        sin.skipWhite();
                        char name1 = sin.readChar();
                        sin.skipWhite();
                        char name2 = sin.readChar();

                        Vertex curr1 = search (name1);
                        Vertex curr2 = search (name2);

                        int cap = sin.readInt();
                        int flow = sin.readInt();
                        Pipe pipe1 = new Pipe (name1, cap, flow);
                        Pipe pipe2 = new Pipe (name2, cap, flow);
                        if (pipe2.name != 'S' && curr1.name != 'T')
                                addPipe (curr1, pipe2);
                        if (pipe1.name != 'S' && curr2.name != 'T')
                                addPipe (curr2, pipe1);
                }
                nopaths = 0;
                path = new Path[10];
                for (int i=0; i<10; i++)
                        path[i] = new Path(vertices);
                p = new Path(vertices);

                displayGraph();
                int flag;

                for (Pipe curr=search('S').head; curr!=null; curr=curr.next)
                {
                    flag = getPath(curr);
                    p.size = 1;
                }
                calFlowIncrease();
                sortPaths();
                displayPaths();
        } // Constructor
        void addPipe (Vertex vertex, Pipe newPipe)
        {
                if (vertex.head == null)
                        vertex.head = newPipe;
                else
                {
                        Pipe curr = vertex.head, prev = null;
                        for (; curr!=null; curr=curr.next)
                                prev = curr;
                        newPipe.next = prev.next;
                        prev.next = newPipe;
                }
        } // addPipe
        Vertex search (char name)
        {
                for (int i=0; i<vertices; i++)
                        if (vertex[i].name == name)
                                return vertex[i];
                return null;
        }
        int getPath (Pipe curr)
        {
                if (!isVisited(curr.name))
                {
                        // add to visited
                        p.visited[p.size++] = curr.name;

                        if (curr.name == 'T')
                                return 1;

                        Vertex vertex = search (curr.name);
                        Pipe temp = vertex.head;
                        for (; temp!=null; temp=temp.next)
                        {
                                int flag = getPath (temp);
                                if (flag == 1)
                                {
                                   path[nopaths].size = p.size;
                                   for (int i=1; i<p.size; i++)
                                     path[nopaths].visited[i] = p.visited[i];
                                   nopaths++;
                                }
                                // remove from visited
                                else if (flag == 2)
                                {
                                        if (p.size > 1)
                                                p.visited[--p.size] = ' ';
                                        else
                                                p.visited[1] = ' ';
                                }
                        }
                        return 2;
                }
                return 0;
        }
        boolean isVisited (char name)
        {
                for (int i=0; i<p.size; i++)
                        if (p.visited[i] == name)
                                return true;
                return false;
        }
        void displayPaths()
        {
                for (int i=0; i<nopaths ; i++)
                {
                        for (int j=0; j<path[i].size; j++)
                                System.out.print (path[i].visited[j]+ " ");
                        System.out.print (path[i].flowIncre);
                        System.out.println();
                }
        }

        void displayGraph()
        {
                for (int i=0; i<vertices; i++)
                {
                        System.out.print (vertex[i].name + "\t");
                        Pipe curr = vertex[i].head;
                        for (; curr!=null; curr=curr.next)
                                System.out.print (curr.name+ " ");
                        System.out.println();
                }
        }
        Pipe searchPipe (Vertex curr, char pipeName)
        {
                Pipe p = curr.head;
                for (; p!=null; p=p.next)
                        if (p.name == pipeName)
                                return p;
                return null;
        }
        void calFlowIncrease()
        {
                for (int i=0; i<nopaths; i++)
                {
                        for (int j=0; j<path[i].size-1; j++)
                        {
                                Pipe curr = searchPipe
                                            (search(path[i].visited[j]) ,
                                             path[i].visited[j+1]);
                                if ((curr.capacity - curr.flow) < path[i].flowIncre)
                                        path[i].flowIncre = curr.capacity - curr.flow;
                        }
                }
        }
        void sortPaths()
        {
                for (int i=0; i<nopaths-1; i++)
                        for (int j=i+1; j<nopaths; j++)
                                if (path[i].flowIncre < path[j].flowIncre)
                                {
                                        Path temp = path[i];
                                        path[i] = path[j];
                                        path[j] = temp;
                                }
        }
} // FlowProblem
