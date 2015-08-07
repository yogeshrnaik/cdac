/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/************************************************************************************/
class Ball implements CONSTANTS
{
	int x, y;				// co-ordinates of the top-left corner and not the co-ordinates of the center of the ball
	int xi, yi;
	Color ballColor = Color.yellow;
/*==========================================================================*/
	Ball()
	{
		init ();
	}
	Ball (int x, int y, int xi, int yi)
	{
		this.x = x;
		this.y = y;
		this.xi = xi;
		this.yi = yi;
	}
/*==========================================================================*/	
	public void init ()
	{
		x = (RIGHT_EDGE - LEFT_EDGE)/2 - RADIUS;
		y = BOTTOM_EDGE - 2 * RADIUS;
		xi = BALL_INCREMENT;
		yi = -BALL_INCREMENT;
	}
/*==========================================================================*/
	public void drawBall(Graphics g)
    {
        g.setColor(ballColor);
        g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
    }
/*==========================================================================*/
	// move the ball
	public void moveBall()
	{
		x += xi;
		y += yi;
	} // moveBall
/*==========================================================================*/
	public void moveBall(int padx, int padLen)
	{
		x = padx + padLen/2 - RADIUS;
	}
/*==========================================================================*/
	public void revBallDir()
	{
		flipxi();
		flipyi();
	}
	public void flipxi()	{ xi = -xi; }
	public void flipyi()	{ yi = -yi; }
/*==========================================================================*/
	public void showDir(Graphics g)
	{
		g.setFont(new Font("ARIAL", 1, DIR_FONT));
		g.setColor(Color.white);
		if (isDirRightUp())
		{
			g.drawString(RIGHT_UP, x+DIR_CONST, y-DIR_CONST);
		}
		if (isDirLeftUp())
		{
			g.drawString(LEFT_UP, x-DIR_CONST+5, y-DIR_CONST);
		}
	}
/*==========================================================================*/
	public void changeDir ()
	{
		flipxi();
	}
	public void eraseDir (Graphics g)
	{
		g.setFont(new Font("ARIAL", 1, DIR_FONT));
		g.setColor(Color.black);
		g.fillRect(LEFT_EDGE+5, y-2*DIR_CONST-5, RIGHT_EDGE-LEFT_EDGE-5, 2*DIR_CONST+3);
	}
/*==========================================================================*/
	public boolean isDirRightDown()
	{
		return (xi > 0 && yi > 0);
	}
	public boolean isDirLeftDown()
	{
		return (xi < 0 && yi > 0);
	}
	public boolean isDirRightUp()
	{
		return (xi > 0 && yi < 0);
	}
	public boolean isDirLeftUp()
	{
		return (xi < 0 && yi < 0);
	}
/*==========================================================================*/
	public boolean isNearPaddle()
	{
		return (y +2*RADIUS >= BOTTOM_EDGE);
	}
	public boolean isHitHorScrEd()
	{
		if (y <= TOP_EDGE+5)
		{
			return true;
		}
		return false;
	}
	public boolean isHitVerScrEd()
	{
		if (x <= LEFT_EDGE+5 || x+2*RADIUS >= RIGHT_EDGE)
		{
			return true;
		}
		return false;
	}
} // Ball
/************************************************************************************/
