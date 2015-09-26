import ncst.pgdst.*;
class Path
{ boolean adj; double weight;
  Path() { adj = false; weight = 1/0.0;}
}
class Graph
{
  Path road[][];
  int max;
  Graph(int max)
  {
    this.max = max;
    road = new Path[max+1][max+1];
    for(int i=0;i<=max;i++)
     for(int j=0;j<=max;j++)
      {
       road[i][j] = new Path();
      }    
  }
}
public class ShortestPath
{
  Graph route;
  int perm[],precede[];
  double distance[];
  int Xh, n , num,current;
  double dc;
  int start,end;
  ShortestPath(SimpleInput sin) throws IOException
  {
    double smalldist,newdist;
    int [] output;
    Xh = sin.readInt();
    n = sin.readInt();
    num = sin.readInt();
    route = new Graph(num);
    for(int k=1;k<=n;k++)
    {
      int i = sin.readInt();
      int j = sin.readInt();
      int n1 = sin.readInt();
      int n2 = sin.readInt();
      if(n2<Xh)
       {
         route.road[i][j].adj = route.road[j][i].adj = true;
         route.road[i][j].weight = route.road[j][i].weight = (double)n1;
       }
    }
     perm = new int [route.max+1];
     precede = new int [route.max+1];
     distance = new double[route.max+1];
    for(int k=0;k<=route.max;k++)
     {
         perm[k] = 0;
         distance[k] = 1/0.0;
     }
    start = sin.readInt();
    end = sin.readInt();
    perm[start] = 1;
    distance[start] = 0.0;
    current = start;
    int k = 0;
   while(current != end)
    {
      smalldist = 1/0.0;
      for(int i=0;i<route.max+1;i++)
       if(perm[i] == 0)
       {
         newdist = distance[current] + route.road[current][i].weight;
         if(newdist < distance[i])
         {
           distance[i] = newdist;
           precede[i] = current;
         }
         if(distance[i] < smalldist)
          {
           smalldist = distance[i];
           k = i;
          }
        }//for..if
           perm[k] = 1; 
           current = k;
    }//while
    k = end;
    output = new int[route.max+1];
    int i = 0;
    output[i++] = end;
    while(k>0)
    {      
      output[i] = precede[k];
      k = precede[k];
      i++;
    }
    for(int j= route.max;j>=0;j--)
    if(output[j] !=0)
    System.out.print(output[j] + " " ); 
  }
   public static void main(String [] args) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    ShortestPath MrX = new ShortestPath(sin);
  }
}


















