public interface CONSTANTS
{
	// Paddle Details
	final static int PADDLE_HEIGHT = 20;
	final static int PADDLE_LENGTH = 150;
	final static int LEFT = 1;
	final static int RIGHT = 2;
/*==========================================================================*/
	// Screen Edge Details
	final static int TOP_EDGE = 110;
	final static int BOTTOM_EDGE = 728 - PADDLE_HEIGHT;
	final static int LEFT_EDGE = 10;
	final static int RIGHT_EDGE = 1004;
/*==========================================================================*/
	// Brick Details
	final static int BRK_WIDTH = 50;
	final static int BRK_HEIGHT = 30;
/*==========================================================================*/
	// Ball Details
	final static int RADIUS = 7;			// so that the dia will be 10
	final static String LEFT_UP = "\\";
	final static String RIGHT_UP = "/";
	final static int DIR_CONST = 12;
	final static int DIR_FONT = 20;
	final static int BALL_INCREMENT = 12;		// 10 OK
/*==========================================================================*/
	// Levels
	final static String[] LEVEL_FILE  = 
		{	"level01.txt", "level02.txt", "level03.txt", 
			"level04.txt", "level05.txt", "level06.txt", 
			"level07.txt", "level08.txt", "level09.txt", 
			"level10.txt"
		};
/*==========================================================================*/
	final static int FIRST_LEVEL = 0;
	final static int LAST_LEVEL = 9;
/*==========================================================================*/
	// Key Constants
	final static int RUN_GAME = 32;					// space bar
	final static int LEFT_ARROW = 37;				// left arrow key
	final static int RIGHT_ARROW = 39;			// right arrow key
	final static int UP_ARROW = 38;					// up arrow key
	final static int CHANGE_SETTING = 67;		// c
	final static int PREV_LEVEL = 66;				// b
	final static int HELP = 72;								// h
	final static int NEXT_LEVEL = 78;				// n
	final static int PAUSE = 80;								// p
	final static int QUIT = 81;								// q
	final static int START_GAME = 83;				// s
	final static int YES = 89;									// y
	final static int CHEAT_CODE =	90;				// z
/*==========================================================================*/
	// Speed Constants
	final static int PAD_LOW = 50;
	final static int PAD_MEDIUM = 75;
	final static int PAD_HIGH = 100;
	final static int PAD_MAX = 125;
	final static int GAMESPEED_LOW = 10;
	final static int GAMESPEED_MEDIUM = 5;
	final static int GAMESPEED_HIGH = 1;	// 1 OK
/*==========================================================================*/
	// Initial Screen Constants
	final static String BOX_TITLE = "Game of bricks";
	final static int BORDER_WIDTH=140, BORDER_HEIGHT=70;
	final static int START_X=70, START_Y=70;
	final static int OFFSET=50;
	final static int OFF_X=150;
	final static int OFF_Y=150;
/*==========================================================================*/
	final static String HELP_MSG =  "Help : \n"+
					"1) Left/Right Arrow keys to move the Paddle\n"+
					"2) Up Arrow key to set Ball Direction\n"+
					"3) <Space Bar> to lauch the Ball\n"+
					"4) h or H : For Help\n"+
					"5) p or P : Pause Game\n"+
					"6) n or N : Play next Level\n"+
					"7) b or B : Play previous Level\n"+
					"8) q or Q : Quit Game\n";
/*==========================================================================*/
	final static int MAX_LIVES = 3;
	final static int DELAY = 10;
	final static int NOT_OK = 0;
	final static int OK = 1;
/*==========================================================================*/
}
