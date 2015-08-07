//#include "header.h"
#include "rectangle.h"
#include "ball.h"

class Paddle : public Rectangle
{
	int length, speed;
	
	public:
	Paddle();
	int get_length();
	int get_speed();
	void set_length (int);
	void set_speed (int);
	void init();
	void movePaddle (int);
	void drawPaddle();
	void erasePaddle();
	int isBallHitPadTop (Ball);
	int isBallHitPadCor (Ball);
}; // Paddle


// CLASS BRICK
class Brick : public Rectangle
{
	int hits;
	Brick *next;

	public:
	Brick(int x, int y, int c, int h);
	int get_hits();
	void set_hits(int);
	Brick* get_next();
	void set_next(Brick*);
	void drawBrick();
	void eraseBrick();
	void changeColor();
	void reduceHits();
	int isAboutToBreak();
	int isRigid();
	int isBallHitBrkCor(Ball);
	int isBallHitBrkHorWall (Ball);
	int isBallHitBrkVerWall(Ball);
}; // Brick

