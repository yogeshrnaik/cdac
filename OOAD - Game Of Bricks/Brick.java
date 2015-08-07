/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/************************************************************************************/
class Brick implements CONSTANTS
{
	int startx, starty;
	Color color;
	int hits;
	Brick next;
/*==========================================================================*/
	Brick (int x, int y, int c, int h)
	{
		startx = x;
		starty = y;
		setColor(h);
		hits = h;
		next = null;
	}
/*==========================================================================*/
	public void setColor(int hits)
	{
		switch (hits)
		{
			// 1 : red, 2 : orange, 3 : yellow 4 : green
			case 0	: color = Color.black;		break;
			case 1	: color = Color.red;			break;
			case 2	: color = Color.pink;		break;		
			case 3	: color = Color.yellow;		break;
			case -1: color = Color.blue;		break;	// hits = -1
			default : color = Color.red;
		}
	}
/*==========================================================================*/
	public int getPoints()		// returns the points obtained by user depending upon the color of brick
	{
		switch (hits)
		{
			// 1 : red, 2 : pink, 3 : yellow 4 : blue
			case 1	: return 10;
			case 2	: return 20; 
			case 3	: return 30;
			default : return 0;
		}
	}
/*==========================================================================*/
	public int getHits() {return hits; }
	public boolean isRigid()
	{
		return (hits == -1);
	}
	public boolean isAboutToBreak()
	{
		return (hits == 1);
	}
	public void drawBrick(Graphics g)
	{
		g.setColor(color);
		g.fillRect(startx, starty, BRK_WIDTH, BRK_HEIGHT);
	}
/*==========================================================================*/
	public void reduceHits()
	{
		hits--;
	}
/*==========================================================================*/
	public boolean isBallHitBrkHorWall (Ball ball)
	{
		if (ball.x >= startx && ball.x+2*RADIUS <= startx+BRK_WIDTH)
		{
			if ( (		ball.y >= starty-2*RADIUS			&& ball.y <= starty)
				|| (	ball.y <= starty+BRK_HEIGHT	&& ball.y >= starty))
			{
				return true;
			}
		}
		return false;
	}
/*==========================================================================*/
	public boolean isBallHitBrkVerWall (Ball ball)
	{
		if (ball.y >= starty && ball.y+2*RADIUS <= starty+BRK_HEIGHT)
		{
			if ( (		ball.x >= startx-2*RADIUS			&& ball.x <= startx)
				|| (	ball.x <= startx+BRK_WIDTH	&& ball.x >= startx))
			{
				return true;
			}
		}
		return false;
	}
} // Brick
/************************************************************************************/
