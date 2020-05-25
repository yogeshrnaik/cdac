inport ncst.pgdst.*;
class david
{
  int di, dj;
  boolean dead;
  david()
  {di = dj = 0; dead = false;}
}
class goliath
{
  int gi,gj;
   
}
public class fishy
        {
            david  D = new david();
            goliath G = new goliath();
            int row,col,time;
            fishy (SimpleInput sin)
            {
              int to = 0;
              row = sin.readInt();
              col = sin.readInt();
              D.di = sin.readInt();
              D.dj = sin.readInt();
              G.gi = sin.redaInt();
              G.gj = sin.readInt();
              for(int i =0;i<time;i++)
              to = G.getmove();
              G.move();

            }
        }
