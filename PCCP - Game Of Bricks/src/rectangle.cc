#include "rectangle.h"
Rectangle :: Rectangle()
{
}
int Rectangle :: get_color()
{
	return color;
}	
void Rectangle :: set_color(int col)
{
	color = col;
}
int Rectangle :: get_startx()
{
	return startx;
}
int Rectangle :: get_starty()
{
	return starty;
}
void Rectangle :: set_startx(int x)
{
	startx = x;
}
void Rectangle :: set_starty(int y)
{
	starty = y;
}	
