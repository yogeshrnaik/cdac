all : mygame lvledit

mygame : ball.o player.o pad_brick.o gamehandler.o rectangle.o
	g++ -o mygame ball.o player.o pad_brick.o rectangle.o gamehandler.o -lncurses

lvledit : lvledit.o
	g++ -o lvledit lvledit.o -lncurses

lvledit.o : lvledit.cc header.h
	g++ -c lvledit.cc

gamehandler.o : header.h ball.o player.o pad_brick.o gamehandler.cc gamehandler.h
	g++ -c gamehandler.cc

ball.o : ball.cc ball.h header.h
	g++ -c ball.cc

player.o : player.cc player.h header.h
	g++ -c player.cc

pad_brick.o : pad_brick.cc pad_brick.h header.h rectangle.o
	g++ -c pad_brick.cc

rectangle.o : rectangle.cc rectangle.h
	g++ -c rectangle.cc


