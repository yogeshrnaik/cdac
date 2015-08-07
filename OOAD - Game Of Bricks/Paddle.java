/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/************************************************************************************/
class Paddle implements CONSTANTS
{
	int startx, starty;
	Color color = Color.pink;
	int padLength;
/*==========================================================================*/	
	Paddle()
	{
		padLength = PADDLE_LENGTH;
		startx = (RIGHT_EDGE -LEFT_EDGE)/2 - padLength/2;
		starty = BOTTOM_EDGE;
	}
/*==========================================================================*/
	Paddle (int x, int y, int len)
	{
		startx = x;
		starty = y;
		padLength = len;
	}
/*==========================================================================*/
	public int getStartx() {return startx;}
	public int getLength() {return padLength;}
/*==========================================================================*/
	public void init()
	{
		startx = (RIGHT_EDGE - LEFT_EDGE)/2 - padLength/2;
		starty = BOTTOM_EDGE;
	}
/*==========================================================================*/
	public void movePaddle(int dir, int speed)
	{
		if (dir == LEFT)
		{
			startx -= speed;
			if (startx < LEFT_EDGE)
			{
				startx = LEFT_EDGE;
			}
		}
		if (dir == RIGHT)
		{
			startx += speed;
			if (startx+padLength >= RIGHT_EDGE)
			{
				startx = RIGHT_EDGE - padLength;
			}
		}
	} // movePaddle
/*==========================================================================*/	
	public void drawPaddle(Graphics g)
	{
		g.setColor(color);
		g.fillRect(startx, starty, padLength, PADDLE_HEIGHT);
	}
/*==========================================================================*/	
	public boolean isBallHitPadTop(Ball ball)
	{
		return (ball.x >= startx-RADIUS && ball.x <= startx+padLength);
	}
} // Paddle
/************************************************************************************/