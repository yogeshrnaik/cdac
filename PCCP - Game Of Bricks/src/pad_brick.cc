#include "header.h"
#include "pad_brick.h"

Paddle :: Paddle()
{
	set_startx((COLS - length)/2);
	set_starty(LINES-PAD_HEIGHT);
	set_color(PAD_COLOR);
	length = PAD_LONG;
	speed = PAD_SLOW;
}

int Paddle :: get_length()
{
	return length;
}
int Paddle :: get_speed()
{
	return speed;
}
void Paddle :: set_length (int len)
{
	length = len;
}
void Paddle :: set_speed (int spd)
{
	speed = spd;
}
void Paddle :: init()
{
	set_startx((COLS-length)/2);
	set_starty(LINES-PAD_HEIGHT);
	drawPaddle();
}

void Paddle :: movePaddle (int dir)
{
	erasePaddle();
	if (dir == LEFT)
	{
		set_startx(get_startx()-speed);
		if (get_startx() < LEFT_EDGE)	set_startx(LEFT_EDGE);
	}
	else if (dir == RIGHT)
	{
		set_startx(get_startx()+speed);
		if (get_startx() + length > RIGHT_EDGE)
			set_startx (RIGHT_EDGE - length+1);
	}
	drawPaddle();
}

void Paddle :: drawPaddle()
{
	attron (COLOR_PAIR(get_color()));
	for (int i=get_startx(); i<get_startx()+length; i++)
		mvaddch (get_starty(), i, ACS_CKBOARD);
	refresh();
	attroff (COLOR_PAIR(get_color()));
}

void Paddle :: erasePaddle()
{
	for (int i=get_startx(); i<get_startx()+length; i++)
		mvaddch (get_starty(), i, WHITE_SPACE);
	refresh();
}

int Paddle :: isBallHitPadTop (Ball ball)
{
	return (ball.getx() >= get_startx() && ball.getx() <= get_startx()+length-1);
}

int Paddle :: isBallHitPadCor (Ball ball)
{
	if (ball.getx() == get_startx()-1)
	{
		if (ball.isDirRightDown())
			return TRUE;
	}
	if (ball.getx() == get_startx()+length)
	{
		if (ball.isDirLeftDown())
			return TRUE;
	}
	return FALSE;
}


// CLASS BRICK

Brick :: Brick(int x, int y, int c, int h)
{
	set_startx(x);
	set_starty(y);
	set_color(c);
	hits = h;
	next = NULL;
}

int Brick :: get_hits()
{
	return hits;
}

void Brick :: set_hits(int h)
{
	hits = h;
}

Brick* Brick :: get_next()
{
	return next;
}

void Brick :: set_next(Brick *nxt)
{
	next = nxt;
}

void Brick :: drawBrick()
{	
	int i,j;
	attron(COLOR_PAIR(get_color()));
	for (j=get_starty(); j < get_starty()+BRK_HEIGHT; j++)
		for (i=get_startx(); i < get_startx()+BRK_WIDTH; i++)
				mvaddch(j,i,ACS_CKBOARD);
	attroff(COLOR_PAIR(get_color()));
	refresh();
}

void Brick :: eraseBrick()
{
	int i,j;
	for (j=get_starty(); j < get_starty()+BRK_HEIGHT; j++)
		for (i=get_startx(); i < get_startx()+BRK_WIDTH; i++)
			mvaddch(j,i,WHITE_SPACE);
	refresh();
}

void Brick :: changeColor()
{
	set_color(get_color()-1);
	drawBrick();
}

void Brick :: reduceHits()
{
	hits--;
}

int Brick :: isAboutToBreak()
{
	return (hits == 1);
}

int Brick :: isRigid()
{
	return (hits == -1);
}

int Brick :: isBallHitBrkCor(Ball ball)
{
	int ballx = ball.getx();
	int bally = ball.gety();
	
	// Ball hits Top Left Corner of the Brick
	if (ballx == get_startx()-1 && bally == get_starty()-1)
		if (ball.isDirRightDown())
			return TRUE;

	// Ball hits Bottom Left Corner of the Brick
	if (ballx == get_startx()-1 && bally == get_starty()+BRK_HEIGHT)
		if (ball.isDirRightUp())
			return TRUE;

	// Ball hits Top Right Corner of the Brick
	if (ballx == get_startx()+BRK_WIDTH && bally == get_starty()-1)
		if (ball.isDirLeftDown())
			return TRUE;

	// Ball hits Bottom Right Corner of the Brick
	if (ballx == get_startx()+BRK_WIDTH && bally == get_starty()+BRK_HEIGHT)
		if (ball.isDirLeftUp())
			return TRUE;
	return FALSE;
}

int Brick :: isBallHitBrkHorWall (Ball ball)
{
	int ballx = ball.getx();
	int bally = ball.gety();
	
	if (ballx >= get_startx() && ballx < get_startx()+BRK_WIDTH)
		if (bally == get_starty()-1 || bally == get_starty()+BRK_HEIGHT)
			return TRUE;
	return FALSE;
}


int Brick :: isBallHitBrkVerWall(Ball ball)
{
	int ballx = ball.getx();
	int bally = ball.gety();

	if (bally >= get_starty() && bally < get_starty()+BRK_HEIGHT)
		if (ballx == get_startx()-1 || ballx == get_startx()+BRK_WIDTH)
			return TRUE;
	return FALSE;
}



