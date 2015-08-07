#include "header.h"
#include "gamehandler.h"
#include <string.h>

GameHandler :: GameHandler()
{
	head = NULL;
	breakableBrks = 0;
	gmspd = GMSPD_SLOW;
}
void GameHandler :: clearLoadedLevel()
{
	Brick *curr;
	while (head != NULL)
	{
		curr = head;
		head = head->get_next();
		delete (curr);
	}
	breakableBrks = 0;
}
void GameHandler :: loadLevel (char *levelName)
{
	FILE *fp;
	int N;
	int brk_x, brk_y, brk_color, brk_hits;
	Brick *tail, *curr;
	clearLoadedLevel();
	tail = NULL;		
	fp = fopen(levelName, "r");
	fscanf(fp,"%d ",&N);
	for (int i=0; i<N; i++)
	{
		fscanf(fp, "%d ", &brk_x);
		fscanf(fp, "%d ", &brk_y);			
		fscanf(fp, "%d ", &brk_color);				
		fscanf(fp, "%d\n",&brk_hits);
		curr = new Brick(brk_x, brk_y, brk_color, brk_hits);
		if (!curr->isRigid())
			breakableBrks++;
		if (head==NULL)
		{
			head = curr;
			tail = curr;
		}
		else
		{
			tail->set_next(curr);
			tail = curr;
		}
	}
} // loadFile

void GameHandler :: loadBricks()
{
	Brick *curr;
	for (curr=head;curr!=NULL;curr=curr->get_next())
		curr->drawBrick();
} // loadBricks

void GameHandler :: removeBrick(Brick *prev, Brick *curr)
{
	if (curr==prev)
		head = head->get_next();
	else
		prev->set_next(curr->get_next());
	delete(curr);
} // removeBrick

int GameHandler :: askOptions ()
{

	int option;
	clear();
	echo();

	player.set_score(0);
	breakableBrks = 0;

	mvprintw(0, 0, "0 : Game Settings\n");
	printw  ("1 : Use Default Settings\n");
	printw  ("2 : Quit\n");
	printw  ("Please Enter your Option here : ");
	refresh();
	do
	{
		scanw ("%d", &option);
		switch (option)
		{
			case 0	: doGameSettings();	break;
			case 1	: doDftSettings();	break;
			case 2	: return QUIT;
			default	: printw  ("Please Enter Correct Option (0/1/2) : ");	refresh();
		}
	} while (option != 0 && option != 1 && option != 2);
	return OK;
} // askOptions

void GameHandler :: doDftSettings()
{
	// Difficulty Level - Default is EASY
	player.set_lives (DFT_LIVES);		// DFT_LIVES = 5
	
	// Paddle Speed - Default is SLOW
	pad.set_speed (PAD_SLOW);
	
	// Paddle Length - Default is LONG
	pad.set_length (PAD_LONG);
	
	// Game Speed - Default is SLOW
	gmspd = GMSPD_SLOW;
} // doDftSettings

void GameHandler :: doGameSettings()
{
	player.readName();
	echo();
	// Difficulty Level
	int option;
	clear();
	printw ("Difficulty Level\n");
	printw ("0 : EASY\n");
	printw ("1 : HARD\n");
	printw ("Please Enter your Option here : ");
	refresh();
	do
	{
		scanw ("%d", &option);
		switch (option)
		{
			case EASY : 	pad.set_length (PAD_LONG);
					player.set_lives (MORE);
					break;
			case HARD :	pad.set_length (PAD_SHORT);
					player.set_lives (LESS);
					break;
			default	: 	printw ("Please Enter Correct Option (0/1) : ");	refresh();
		}
	} while (option != EASY && option != HARD);
	
	// Paddle Speed
	clear();
	printw ("Paddle Speed\n");
	printw ("0 : SLOW\n");
	printw ("1 : FAST\n");
	printw ("Please Enter your Option here : ");
	refresh();
	do
	{
		scanw ("%d", &option);
		switch (option)
		{
			case SLOW : 	pad.set_speed(PAD_SLOW);
					break;
			case FAST :	pad.set_speed(PAD_FAST);
					break;
			default	: 	printw ("Please Enter Correct Option (0/1) : ");	refresh();
		}
	} while (option != SLOW && option != FAST);


	// Game Speed
	clear();
	printw ("Game Speed\n");
	printw ("0 : SLOW\n");
	printw ("1 : FAST\n");
	printw ("Please Enter your Option here : ");
	refresh();
	do
	{
		scanw ("%d", &option);
		switch (option)
		{
			case SLOW : 	gmspd = GMSPD_SLOW;
					break;
			case FAST :	gmspd = GMSPD_FAST;
					break;
			default	: 	printw ("Please Enter Correct Option (0/1) : ");	refresh();
		}
	} while (option != SLOW && option != FAST);

} // doGameSettings

int GameHandler :: play (char *levelName)
{
	loadLevel(levelName);
	int key = initialise();
	if (key == QUIT || key == PREV_LEVEL || key == SKIP_LEVEL)
		return key;
	while (!isLevelComplete())
	{
		key = chkKeyPressed();
		if (key == QUIT || key == PREV_LEVEL || key == SKIP_LEVEL)
			return key;
		ball.moveBall();
		player.displayInfo();
		//mvprintw (MSG_POSY,MSG_POSX,"Breakable = %d\t",breakableBrks);
		//refresh();
		int status = check();
		if (status == NOT_OK)
		{
			// Player losses one turn
			if (player.hasLife())
			{
				player.reduceLives();
				mvprintw (MSG_POSY, MSG_POSX, "Sorry, You lost one chance\n");
				printw ("Press any key to start\n");
				refresh();
				timeout (HALT);
				getch();
				key = initialise();
				if (key == QUIT) return QUIT;
			}
			else
			{
				// Game Over
				gameOver();
				return QUIT;
			}
		}
		delay();
	}
	return (strcmp(levelName, LAST_LEVEL)==0) ? GAME_COMPLETED : LEVEL_COMPLETED;
} // play
int GameHandler :: initialise()
{
	clear();
	int key = 0;
	noecho();
	player.displayInfo();
	pad.init();
	ball.init();
	ball.showDir();
	loadBricks();
	do
	{
		key = wgetch(stdscr);
		if (key == KEY_LEFT)
		{
			pad.movePaddle (LEFT);
			ball.moveBall (pad.get_startx(), pad.get_length());
		}
		if (key == KEY_RIGHT)
		{
			pad.movePaddle (RIGHT);
			ball.moveBall (pad.get_startx(), pad.get_length());
		}
		if (key == KEY_UP)
			ball.changeDir();
		if (key == QUIT)
		{
			mvprintw (MSG_POSY, MSG_POSX, "Do you want to quit (y/n)? : ");
			refresh();
			timeout (HALT);
			key = getch();
			if (key == YES)
				return QUIT;
			else
			{
				move(MSG_POSY, MSG_POSX);
				clrtoeol ();
			}
			refresh();
		}

	} while (key != SPACE_BAR && key != PREV_LEVEL && key != SKIP_LEVEL);
	return key;
} // initialise


int GameHandler :: isLevelComplete()
{
	return (breakableBrks == 0);
}

void GameHandler :: delay()
{
	for (double i=0.0; i<DELAY; i+=gmspd);
}

void GameHandler :: gameOver()
{
	clear();
	mvprintw (0, 0, "GAME OVER!\n");
	printw ("Your Score is : %d\n", player.get_score());
	printw ("Press any key to continue\n");
	refresh();
	timeout (HALT);
	getch();
} // Game Over


int GameHandler :: check()
{
	int hor_ed = ball.isHitHorScrEd();
	int ver_ed = ball.isHitVerScrEd();

	// Ball Hits the Screen Corner
	if (hor_ed && ver_ed)
	{
		ball.revBallDir();
		return OK;
	}
	
	// Ball Hits only the Horizontal Screen Edge (i.e. Top Edge)
	if (hor_ed)
	{
		ball.flipyi();
		return OK;
	}

	// If ball is about to hit the Paddle
	int near_pad = ball.isNearPad();

	// Ball hits only the Vertical Screen Edge
	if (ver_ed && !near_pad)
	{
		ball.flipxi();
		return OK;
	}

	// Ball hits the pad corner or the pad top
	int pad_cor = pad.isBallHitPadCor(ball);
	int pad_top = pad.isBallHitPadTop(ball);
	if (near_pad)
	{
	/*	if (!ver_ed)
			ball.flipyi();
		else	ball.revBallDir();
		return OK;
	*/
		// Ball hits only the top of the paddle
		if (pad_top && !ver_ed)
		{
			ball.flipyi();
			beep();
			return OK;
		}
		
		// Ball hits the only pad_cor or pad_top and ver_ed
		// or pad_cor and ver_ed
		if ((pad_cor && !ver_ed) || (pad_top && ver_ed))
		{
			ball.revBallDir();
			beep();
			return OK;
		}
		return NOT_OK;
	}
	else
		chkHitsToBrks();
	return OK;
} // check

void GameHandler :: chkHitsToBrks()
{
	Brick *prev, *curr;
	curr = head;
	prev = head;
	
	int flag = FALSE;	// To check whether Ball has hit a Brick
	for (; curr!=NULL; curr=curr->get_next())
	{
		if (curr->isBallHitBrkCor(ball))
		{
			ball.revBallDir();
			flag = TRUE;
		}
		if (curr->isBallHitBrkHorWall(ball))
		{
			ball.flipyi();
			flag = TRUE;
		}
		if (curr->isBallHitBrkVerWall(ball))
		{
			ball.flipxi();
			flag = TRUE;
		}
		if (flag)
		{
			int score = 0;
			if (!curr->isRigid())
			{
				if (!curr->isAboutToBreak())
				{
					score = 60;
					curr->changeColor();
					curr->reduceHits();
				}
				else
				{
					score = 10;
					breakableBrks--;
					curr->eraseBrick();
					removeBrick(prev, curr);
				}
				player.updateScore(score);
			}
			//beep();
			break;
		}
		prev = curr;
	} // for
	
} // chkHitsToBrks


int GameHandler :: chkKeyPressed()
{
	timeout(0);
	int key = wgetch(stdscr);
	if (key == KEY_LEFT)
		pad.movePaddle(LEFT);
	if (key == KEY_RIGHT)
		pad.movePaddle(RIGHT);
	if (key == PAUSE)
	{
		timeout(HALT);
		getch();
		timeout(0);
	}
	if (key == SPACE_BAR)
	{
		if (!ball.isHitHorScrEd())
			if (ball.isDirLeftDown() || ball.isDirRightDown())
				ball.flipyi();
	}
	if (key == QUIT)
	{
		mvprintw (MSG_POSY, 0, "Quit (y or n)? : ");
		refresh();
		timeout (HALT);
		int option = getch();
		if (option == YES) 
			return QUIT;
		else
		{
			move (MSG_POSY, MSG_POSX);
			clrtoeol ();
			refresh();
			return option;
		}
	}
	return key;
}
void GameHandler :: gameCompleted ()
{
	clear();
	attron (A_BLINK);
	mvprintw (0,0,"CONGRAGULATIONS !!\n");
	printw ("You have successfully completed the GAME OF BRICKS");
	refresh();
	getch();
}

int  main()
{ 	
	char filename[20];
	char *levels[] = { 	"level01.txt", "level02.txt", "level03.txt",
				"level04.txt", "level05.txt", "level06.txt",
				"level07.txt", "level08.txt", "level09.txt",
				"level10.txt"
			};
	/*			
	printf("Enter the file name:");
	scanf("%s",&filename);
	*/
	initscr();
   	noecho();
	cbreak();

	keypad(stdscr,TRUE);
	attron(A_BOLD);
	start_color();
	init_pair(BRK_HITS1, COLOR_RED,    COLOR_RED);	  // BRICK HITS = 1
	init_pair(BRK_HITS2, COLOR_YELLOW, COLOR_YELLOW); // BRICK HITS = 2
	init_pair(BRK_RIGID, COLOR_CYAN,   COLOR_CYAN);	  // Rigid Brick
	init_pair(PAD_COLOR, COLOR_BLUE,   COLOR_BLUE);	  // Paddle
	init_pair(BALL_COLOR,COLOR_YELLOW,  COLOR_BLACK);  // Ball

	GameHandler gmhdr;
	int option;
	do
	{
		option = gmhdr.askOptions();
		if (option == QUIT) break;
		int status;
		for (int i=0; i<10; i++)
		{	
			status = gmhdr.play(*(levels+i));
			if (status == QUIT)
				break;
			if (status == PREV_LEVEL)
				if (i != 0)
					i-=2;
				else i = -1;
			if (status == SKIP_LEVEL || status == LEVEL_COMPLETED)
				if(strcmp(*(levels+i), LAST_LEVEL) != 0)
					continue;
				else
					status = GAME_COMPLETED;
			clear();
			refresh();
		}
		if (status == GAME_COMPLETED)
		{
			gmhdr.gameCompleted();
			break;
		}
	} while (option != QUIT);
/*
	gmhdr.play(filename);
	clear();
*/
	gmhdr.clearLoadedLevel();
//	getch();
	endwin();
	return 1;
} // main





