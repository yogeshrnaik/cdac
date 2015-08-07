#include "pad_brick.h"
#include "player.h"
class GameHandler
{
	Brick *head;
	Ball ball;
	Player player;
	Paddle pad;
	int breakableBrks;
	double gmspd;

	public:
	GameHandler();
	void loadLevel (char*);
	void loadBricks();
	void removeBrick(Brick*, Brick*);

	int askOptions ();
	void doDftSettings();
	void doGameSettings();

	int play(char*);
	int initialise();

	int check();
	void chkHitsToBrks();
	int chkKeyPressed();

	int isLevelComplete();
	void gameCompleted();
	void clearLoadedLevel();
	void delay();
	void gameOver();

}; // Game Handler 


