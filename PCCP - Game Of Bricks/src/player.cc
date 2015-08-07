#include "player.h"
#include "header.h"
#include <string.h>
Player :: Player ()
{
	strcpy (name, DFT_NAME);
	score = 0;
	lives = 3;
}
int Player :: get_score()
{
	return score;
}
void Player :: updateScore (int s)
{
	score += s;
}
void Player :: set_score (int s)
{
	score = s;
}
int Player :: get_lives()
{
	return lives;
}
void Player :: set_lives (int l)
{
	lives = l;
}

void Player :: reduceLives ()
{
	lives--;
}
void Player :: readName()
{
	if (strcmp (name, DFT_NAME) == 0)
	{
		printw ("Enter your name : ");
		scanw  ("%s", name);
	}
}
/*
char& Player :: get_name()
{
	return *name;
}
*/

void Player :: set_name (char *nm)
{
}

int Player :: hasLife()
{
	return (lives > 1);
}
void Player :: displayInfo()
{
	mvprintw (0,0, "Player : %s", name);
	mvprintw (0,COLS-20, "p : Pause, q : Quit");
	mvprintw (1,0, "Score : %d", score);
	mvprintw (1, COLS-20, "Lives Remaining : %d", lives);
		for (int i=0; i<COLS; i++)
			mvaddch(TOP_EDGE-1,i,ACS_HLINE);
	refresh();
}



