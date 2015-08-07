#include "header.h"
class Ball
{
	int x, y, xi, yi;
	int color;

	public:
	Ball();
	int getx();
	int gety();
	void setx(int);
	void sety(int);
	int  getxi();
	int getyi();
	void setxi(int);
	void setyi(int);

	void init();
	void moveBall();
	void moveBall (int, int);

	void putBall();
	void eraseBall();

	void flipyi();
	void flipxi();
	void revBallDir();
	void setDftDir();

	int isHitHorScrEd();
	int isHitVerScrEd();
	int isNearPad();
	int isDirRightDown();
	int isDirLeftDown();
	int isDirRightUp();
	int isDirLeftUp();

	void showDir();
	void eraseDir();
	void changeDir();
};

