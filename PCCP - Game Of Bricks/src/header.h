#include <curses.h>
#include <stdlib.h>

#define HGAP 1
#define VGAP 1 

// Definitions related to Bricks
// Dimensions
#define BRK_WIDTH 4
#define BRK_HEIGHT 1

//Color
#define BRK_HITS1 1
#define BRK_HITS2 2
#define BRK_RIGID 3


// Definitions related to Paddle
// Dimensions
#define PAD_HEIGHT 1
#define PAD_LONG 13		// Default Length
#define PAD_SHORT 11

// Paddle Speed
#define PAD_SLOW 3
#define PAD_FAST 6
#define SLOW 0
#define FAST 1

// Color
#define PAD_COLOR 4

// Paddle Movement
#define LEFT 0
#define RIGHT 1


// Definitions related to Sceen Edges
#define TOP_EDGE 3
#define BOTTOM_EDGE LINES-1
#define LEFT_EDGE 0
#define RIGHT_EDGE COLS-1


// Definitions related to Ball
#define BALL_CHAR 'o'
#define BALL_COLOR 5
#define WHITE_SPACE ' '
#define LEFT_UP '\\'
#define RIGHT_UP '/'

// Difficulty level
#define EASY 0
#define HARD 1

// Keys
#define QUIT 113	// 'q'
#define SPACE_BAR 32
#define PAUSE 112	// 'p'
#define PREV_LEVEL 98	// 'b'
#define SKIP_LEVEL 110	// 'n'


// Definitions related to Player
#define DFT_LIVES 5
#define DFT_NAME "Player"
#define MORE 5
#define LESS 3

// Definitions related to Game Speed
#define GMSPD_SLOW 3.8
#define GMSPD_FAST 5.5
#define DELAY 9000000


// Definitions related to Game
#define LEVEL_COMPLETED 1
#define GAME_COMPLETED 2
#define LAST_LEVEL "level10.txt"

// General
#define HALT 100000000
#define FALSE 0
#define TRUE 1
#define NO 110		// 'n'
#define YES 121		// 'y'
#define NOT_OK 0
#define OK 1

#define MSG_POSX 0
#define MSG_POSY LINES-10




