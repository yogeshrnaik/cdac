#include "ball.h"
#include "header.h"

Ball :: Ball()
{
	x = COLS/2;
	y = LINES-PAD_HEIGHT-1;
	xi = 1;
	yi = -1;
	color = BALL_COLOR;
}
int Ball :: getx()
{
	return x;
}
int Ball :: gety()
{
	return y;
}
void Ball :: setx(int ax)
{
	x = ax;
}
void Ball :: sety(int ay)
{
	y = ay ;
}
        
int  Ball :: getxi()
{
	return xi;
}

int Ball :: getyi()
{
	return yi;
}
void Ball :: setxi(int i)
{
	xi = i;
}
		
void Ball :: setyi(int i)
{
	yi = i;
}



void Ball :: moveBall()
{
	eraseBall();
	x += xi;
	y += yi;
	putBall();
}
void Ball :: moveBall (int padx, int padlen)
{
	eraseBall();
	eraseDir();
	x = padx + padlen/2;
	showDir();
	putBall();
}

void Ball :: putBall()
{
	attron (COLOR_PAIR(color));
	mvaddch (y, x, BALL_CHAR);
	refresh();
	attroff (COLOR_PAIR(color));
}	
void Ball :: eraseBall()
{
	mvaddch(y, x, WHITE_SPACE);
	refresh();
}

void Ball :: flipyi()
{
	yi = -yi;
}

void Ball :: flipxi()
{
	xi = -xi;
}

void Ball :: setDftDir()
{
	xi = 1;
	yi = -1;
}
void Ball :: init()
{
	x = COLS/2-1;
	y = LINES-PAD_HEIGHT-1;
	xi = 1;
	yi = -1;
	putBall();
}

void Ball :: revBallDir()
{
	flipyi();
	flipxi();
}

int Ball :: isHitHorScrEd()
{
	return (y == TOP_EDGE);
}

int Ball :: isHitVerScrEd()
{
	return (x==LEFT_EDGE || x==RIGHT_EDGE);
}

int Ball :: isDirRightDown()
{
	return (xi==1 && yi==1);
}

int Ball :: isDirLeftDown()
{
	return (xi==-1 && yi==1);
}

int Ball :: isDirRightUp()
{
	return (xi==1 && yi==-1);
}

int Ball :: isDirLeftUp()
{
	return (xi==-1 && yi==-1);
}

void Ball :: showDir()
{
	if (isDirRightUp())
		mvaddch (LINES-PAD_HEIGHT-2, x+1, RIGHT_UP);
	if (isDirLeftUp())
		mvaddch (LINES-PAD_HEIGHT-2, x-1, LEFT_UP);
	refresh();
}

void Ball :: changeDir()
{
	eraseDir();
	flipxi();
	showDir();
}
void Ball :: eraseDir()
{
	if (isDirRightUp())
		mvaddch (LINES-PAD_HEIGHT-2, x+1, WHITE_SPACE);
	if (isDirLeftUp())
		mvaddch (LINES-PAD_HEIGHT-2, x-1, WHITE_SPACE);
	refresh();
}

int Ball :: isNearPad()
{
	return (y == LINES-PAD_HEIGHT-1);
}


