import ncst.pgdst.*;
class insect
{
int id;
int ii, ij ;
char type;
String s;
int sti;
boolean dead ;
insect()
 {
  id = ii = ij = sti = 0;
  type = '0';
  String s = null;
  dead = false;
 }
void getinsect(SimpleInput sin) throws IOException
 {
  id = sin.readInt();
   sin.skipWhite();
  type = sin.readChar();
  ii = sin.readInt();
  ij = sin.readInt();
  s = sin.readWord();
 }
char getmove()
 {
  int n = s.length();
  if(sti > n-1) sti = 0 ;
  return s.charAt(sti);
  }
}
public class Insect
{  int ino;
  insect [] a  = new insect [11];
  int[][] grid = new int[10][10];
  int time;
  int posi,posj;
  Insect(SimpleInput sin) throws IOException
  {
    ino = sin.readInt();
    for (int i=0;i<ino;i++)
    {
      a[i] = new insect();
      a[i].getinsect(sin);
    }
   time = sin.readInt();
   posi = sin.readInt();
   posj = sin.readInt();
  }
 void traverse ()
 {
  for(int i=1;i<=time;i++)
  {
    for(int j=0;j<ino;j++)
    {
      if(!a[j].dead)
     {
      char ch = a[j].getmove();
      a[j].sti++;
       switch(ch)
       {
        case 'N' : a[j].ii--; break;
        case 'E' : a[j].ij++;break;
        case 'S' : a[j].ii++; break;
        case 'W' : a[j].ij--;break;
        }       //switch
     }//if
    }//for
     for(int j=0;j<ino;j++)
          {
            if(a[j].type == 'D')
                                  
                        {
                               
                               if(a[j].ii>=0)
                               { grid[a[j].ii][a[j].ij] += 1;
                                
                               }
                              else  a[j].dead = true;
                        }
                      else
                        {      
                             if(a[j].ii>=0 && grid[a[j].ii][a[j].ij]>0)
                              { grid[a[j].ii][a[j].ij] -= 1;
                               
                              }
                              else  a[j].dead = true;
                        }

              } // for

  }  // end of time

   if(ino==0) 
   System.out.println(grid[posi][posj]);
          else      System.out.println(grid[posi][posj]-1);
}// end of traverse
 public static void main(String[] args) throws IOException
 {
  SimpleInput sin  = new SimpleInput();
 Insect ash = new Insect(sin);
  ash.traverse();
 }
}
