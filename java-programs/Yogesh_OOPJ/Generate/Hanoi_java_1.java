/* Problem: Towers of Hanoi
The Tower of Hanoi is a puzzle consisting of a tower of disks of different
diameters, each of which has a hole in the middle so that it can be
slipped down onto one of three posts. The object of the puzzle is to move
the stack of disks, initially ordered by size with the smallest on top,
from its initial post to another one (in our examples, the problem will be
to move the tower from the leftmost post to the rightmost post),
while satisfying three constraints:

1) the player may move only one disk at a time;
2) disks must be on the posts or in the player's hand;
   (i.e. no external cache is allowed);
3) a disk must never be placed upon another disk of smaller diameter. */

/* ALGORITHM
1. IF n = 1, move disk from A to C . Stop.
2. Move top n-1 disks from A to B using C as auxilliary peg
3. Move remaining disk from A to C
4. Move top n-1 disks from B to C using A as auxilliary peg */

import ncst.pgdst.*;

public class Hanoi
{
  private int pegno;
  private SimpleInput sin;
  private SimpleOutput sout;

  public Hanoi()
  {
    sin = new SimpleInput();
    sout = new SimpleOutput();
  }
  public void move(int n, char fromPeg, char toPeg, char auxPeg)
  {
    if(n==0)
    {
      return;
    }
    if(n==1)
    {
      sout.writelnString("Move disk " + n + " from disk " + fromPeg + " to disk " + toPeg);
      return;
    }
    move(n-1,fromPeg,auxPeg,toPeg);
    sout.writelnString("Move disk " + n + " from disk " + fromPeg + " to disk " + toPeg);
    move(n-1,auxPeg,toPeg, fromPeg);
  }

  public static void main (String args[]) throws IOException
  {
    SimpleInput sin = new SimpleInput();
    Hanoi problem = new Hanoi();
    int n = sin.readInt();
    char fromPeg = 'A';
    char toPeg = 'C';
    char auxPeg = 'B';
    problem.move(n,fromPeg, toPeg, auxPeg);
  }
}
