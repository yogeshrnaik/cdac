/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ncst.pgdst.*;
import java.io.File;
/************************************************************************************/
public class GameHandler extends JApplet implements KeyListener, ActionListener, CONSTANTS
{
    private final static Color bg = Color.black;
    private static Rectangle gameBorder;
	private JFrame frame;
	private Dimension dim;
	Timer timer;

	// for double buffering
	private Image dbImage;
	private Graphics dbg;

	private boolean initialScreen, gameInitialised, gameStarted, gamePaused;

	/* whether to clear the screen or not
		screen is cleared 
			- when initialising game from Initial Screen 
			- loading next or previous level
			- showing Initial Screen if user quits the running game in between
	*/
	private boolean clearScreen;

	private Player player;
	private Ball ball;
	private Paddle pad;
	private Setting set;
	private Brick brkHead;
	private String currLevelFile;
	private int currLevelNo;
	private int no_of_bricks;
	private int breakableBricks;

/*==========================================================================*/
	public GameHandler() throws IOException
	{
		// create a frame to hold the game graphics
		frame = new JFrame("Game of Bricks");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		frame.getContentPane().add("Center", this);
		init();
		frame.pack();
		frame.setResizable(false);
		dim = frame.getToolkit().getScreenSize();
		frame.setSize(dim);
		gameBorder = new Rectangle(10, 110, dim.width-30, dim.height-150);
		
		// to show the inital screen with the options
		initialScreen = true;
		clearScreen = gameInitialised = gameStarted = gamePaused = false;

		// initalise other components of the game
		player = new Player(dim, "Player");
		set = new Setting();
		ball = new Ball();
		pad = new Paddle();
		brkHead = null;
		currLevelNo = 0;
		currLevelFile = LEVEL_FILE[currLevelNo];
		timer = new Timer (GAMESPEED_MEDIUM, this);
		timer.start();
		
        frame.show();			// this calls the paint
		addKeyListener(this);

		// ask the player his name
		String name = (String)JOptionPane.showInputDialog(GameHandler.this,
			"Please enter your name : ",
			BOX_TITLE, JOptionPane.INFORMATION_MESSAGE, null, null,"Player");
		player.setName(name);
	}
/*==========================================================================*/
	// this method will load level data from the specifed file and create a linked list of bricks
	// present in that level
	public void loadLevel(String filename) throws IOException
	{
		// remove any old bricks of previous level present in the linked list 
		brkHead = null;
		breakableBricks = 0;

		// open the current level file
		File file = new File(filename);
		SimpleInput sin = new SimpleInput(file);

		// read data from file and create linked list of bricks
		no_of_bricks = sin.readInt();
		for (int i=1; i<=no_of_bricks; i++)
		{
			int x = sin.readInt();
			int y = sin.readInt();
			int color = sin.readInt();
			int hits = sin.readInt();
			// count how many bricks are breakable
			if (hits != -1)
			{
				breakableBricks++;
			}
			// add brick to the linked list
			if (brkHead == null)
			{
				brkHead = new Brick(x, y, color, hits);
			}
			else
			{
				addBrickToList(new Brick(x, y, color, hits));
			}
		}
	} //loadLevel
/*==========================================================================*/
	// add a brick at the end of the linked list of bricks
	public void addBrickToList(Brick newBrick)
	{
		Brick curr = brkHead;
		while (curr.next != null)
		{
			curr = curr.next;
		}
		curr.next = newBrick;
	}
/*==========================================================================*/	
	// display all the bricks present in the linked list
	public void displayBricks(Graphics g)
	{
		Brick curr = brkHead;
		while (curr != null)
		{
			curr.drawBrick(g);
			curr = curr.next;
		}
	}
/*==========================================================================*/
	public void init() 
	{
		// Initialize background color
		setBackground(bg);
	}
/*==========================================================================*/	
	// it shows the inital screen with some options like 1)Start game, 2) Change setting, etc.
	public void showInitialScreen (Graphics g)
	{
		// erase previous screen
		int maxWidth = (int)dim.getWidth();
		int maxHeight = (int)dim.getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, maxWidth, maxHeight);

		Font fancyFont = new Font("Serif", Font.BOLD | Font.ITALIC, 50);
		g.setColor(Color.red);
		g.drawRect(START_X,START_Y,maxWidth-OFF_X,maxHeight-OFF_Y);
		String Title="Game of Bricks";
		String key1="Press 'S' or 's' to start the game";
		String key2="Press 'C' or 'c' to change settings";
		String key3="Press H or 'h'to see Help for the Game";
		String key4="Press 'Q' or 'q' to quit game";
		Font f1=new Font("Serif",Font.PLAIN | Font.ITALIC,25);
		g.setFont(fancyFont);
		g.setColor(Color.green);
		g.drawString(Title,START_X+maxWidth/4-25,START_Y+maxHeight/4-25);
		g.setFont(f1);
		g.setColor(Color.blue);
		g.drawString(key1,START_X+maxWidth/4-OFFSET,START_Y+maxHeight/4+OFFSET);
		g.drawString(key2,START_X+maxWidth/4-OFFSET,START_Y+maxHeight/4+2*OFFSET);
		g.drawString(key3,START_X+maxWidth/4-OFFSET,START_Y+maxHeight/4+3*OFFSET);
		g.drawString(key4,START_X+maxWidth/4-OFFSET,START_Y+maxHeight/4+4*OFFSET);
	} // showInitialScreen
/*==========================================================================*/
    public void paint(Graphics g) 
	{
		if (initialScreen)
		{
			showInitialScreen(g);
		}
		else if (gameInitialised)
		{
			if (clearScreen)
			{
				clearScreen = false;
				g.setColor(Color.black);
				g.fillRect(0, 0, dim.width, dim.height);
			}
			player.displayPlayerInfo(g, currLevelNo+1);
			g.setColor(Color.white);
			g.drawRect(gameBorder.x, gameBorder.y, gameBorder.width, gameBorder.height);
			displayBricks(g);

			pad.drawPaddle(g);
			ball.drawBall(g);
			ball.showDir(g);
		}
		else if (gameStarted)
		{
			player.displayPlayerInfo(g, currLevelNo+1);
			g.setColor(Color.white);
			g.drawRect(gameBorder.x, gameBorder.y, gameBorder.width, gameBorder.height);
			displayBricks(g);

			pad.drawPaddle(g);
			ball.drawBall(g);
		}
	} // paint
/*==========================================================================*/
	/** Update - Method, implements double buffering */
	public void update (Graphics g)
	{ 
		// initialize buffer
		if (dbImage == null)
		{ 
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}
		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		// draw elements in background
		dbg.setColor (getForeground());
		paint(dbg);

		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);
	} // update
/*==========================================================================*/
	public void actionPerformed (ActionEvent ae)
	{
		if (gameStarted)
		{
			if (!isLevelCompleted())
			{
				ball.moveBall();
				int status = detectCollision();
				if (status == NOT_OK) // player has lost one turn
				{
					if (player.hasLife())
					{
						// - stop the timer
						timer.stop();
						player.reduceLives();
						// show a message box that player have lost once life
						JOptionPane.showMessageDialog(GameHandler.this, "You lost one life.",
							BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
						gameInitialised = true;
						gameStarted = false;
						initGame();
						timer.setDelay(set.getGameSpeed());
						timer.restart();
					}
					else		// player has lost all the lives and hence game is over
					{
						gameOver();
					}
				}
			}
			else // level completed and hence load next level
			{
				// if level completed is the last level
				if (currLevelNo == LAST_LEVEL)
				{
					gameCompleted();
				}
				else
				{
					// show message that level is completed
					JOptionPane.showMessageDialog(GameHandler.this, "Level Completed.",
							BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
					interpretKey(NEXT_LEVEL);
				}
			}
		} // if (gameStarted)
		repaint();
	} // actionPerformed
/*==========================================================================*/
	public int detectCollision()
	{
		// detect collision of the ball with the screen edges
		boolean hor_ed = ball.isHitHorScrEd();
		boolean ver_ed = ball.isHitVerScrEd();
		// ball hits screen corner - TOP_LEFT or TOP_RIGHT corner
		if (hor_ed && ver_ed)
		{
			ball.revBallDir();
			return OK;
		}
		// ball hits only TOP_EDGE
		if (hor_ed)
		{
			ball.flipyi();
			return OK;
		}
		
		boolean near_pad = ball.isNearPaddle();
		// ball hits only LEFT_EDGE or RIGHT_EDGE
		if (ver_ed && !near_pad)
		{
			ball.flipxi();
			return OK;
		}

		// ball hits the the paddle top edge
		boolean pad_top = pad.isBallHitPadTop	(ball);
		if (near_pad)
		{
			// ball hits only the paddle top edge
			if (pad_top && !ver_ed)
			{
				ball.flipyi();
				return OK;
			}
			return NOT_OK;
		}
		else
		{
			checkHitsToBrick();
		}
		return OK;
	} // detectCollision
/*==========================================================================*/
	// traverse through the linked list of bricks and check whether ball hits a brick
	public void checkHitsToBrick()
	{
		Brick curr = brkHead;
		Brick prev = brkHead;
		boolean flag = false;
		while (curr != null)
		{
			if (curr.isBallHitBrkHorWall(ball))
			{
				ball.flipyi();
				flag = true;
			}
			if (curr.isBallHitBrkVerWall(ball))
			{
				ball.flipxi();
				flag = true;
			}
			if (flag)
			{
				int score = 0;
				if (!curr.isRigid())
				{
					score = curr.getPoints();
					if (!curr.isAboutToBreak())	// change the color of the bricks
					{
						curr.reduceHits();
						curr.setColor(curr.getHits());
					}
					else	// brick is to be destroyed
					{
						breakableBricks--;
						curr.setColor(0);
						removeBrick(prev, curr);
					}
					player.updateScore(score);
				}
				break;
			}
			prev = curr;
			curr = curr.next;
		}
	} // checkHitsToBrick
/*==========================================================================*/
	public void removeBrick(Brick prev, Brick curr)
	{
		if (prev == curr)	// brick to be removed is the first brick
		{
			brkHead = brkHead.next;
		}
		else
		{
			prev.next = curr.next;
		}
	} // removeBrick
/*==========================================================================*/
	public void gameOver()
	{
		/*	 - stop the timer - timer.stop(); 
			 - show a message box with the player's score 
			 - then show initial screen
			 - initialScreen = clearScreen = true;
			 - gameInitialised =  gameStarted = false;
			 - initGame();
		*/
		timer.stop();
		JOptionPane pane = new JOptionPane("Game Over");
		JOptionPane.showMessageDialog(GameHandler.this, "Game Over! Your Score is "+player.getScore(),
			BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
		initialScreen = clearScreen = true;
		gameInitialised =  gameStarted = false;
		initGame();
		player.setScore(0);
		player.setLives(MAX_LIVES);
	}
/*==========================================================================*/
	public void gameCompleted()
	{
		/* - stop the timer - timer.stop();
			- show a message box with the player's score and a congrats message
			- then show initial screen
			- initialScreen = clearScreen = true;
			- gameInitialised =  gameStarted = false;
			- initGame();
			- set player's info
			- set level no = FIRST_LEVEL
		*/
		timer.stop();
		JOptionPane pane = new JOptionPane("Game Completed");
		JOptionPane.showMessageDialog(GameHandler.this, "Congrats!! You have completed the Game of Bricks!!! Your Score is "+player.getScore(),
			BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
		initialScreen = true;
		clearScreen = true;
		gameInitialised =  gameStarted = false;
		initGame();
		player.setLives(MAX_LIVES);
		player.setScore(0);
		currLevelNo = FIRST_LEVEL;
	}
/*==========================================================================*/
	public boolean isLevelCompleted()
	{
		return (breakableBricks == 0);
	}
/*==========================================================================*/
	// Invoked when a key has been pressed. 
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		interpretKey (key);
	}
/*==========================================================================*/
	// total no. of keys to be interpreted are = 11
	public void interpretKey (int key) 
	{
		switch (key)
		{
			case START_GAME				:	if (initialScreen)
																{
																	clearScreen = true;
																	initialScreen = false;
																	gameInitialised = true;
																	try { loadLevel(currLevelFile); }
																	catch (Exception ex) {}
																	repaint();
																}
																return;
			case RUN_GAME					:	if (gameInitialised)
																{
																	gameStarted = true;
																	clearScreen = false;
																	initialScreen = false;
																	gameInitialised = false;
																	timer.setDelay(set.getGameSpeed());
																	timer.restart();
																}
																return;

			// always interpret this but respond differently depending upon the game status
			case CHANGE_SETTING	:	if (initialScreen || gameInitialised)
																{
																	set.changeSetting();
																}
																else if (gameStarted)
																{
																	timer.stop();
																	set.changeSetting();
																	timer.setDelay(set.getGameSpeed());
																	timer.restart();
																}
																return;

			// always interpret this but respond differently depending upon the game status
			case HELP								:	if (initialScreen || gameInitialised)
																{
																	JOptionPane pane = new JOptionPane(HELP_MSG);
																	JOptionPane.showMessageDialog(GameHandler.this, HELP_MSG,
																		BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
																}
																else if (gameStarted)
																{
																	timer.stop();
																	JOptionPane pane = new JOptionPane(HELP_MSG);
																	JOptionPane.showMessageDialog(GameHandler.this, HELP_MSG,
																		BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
																	timer.setDelay(set.getGameSpeed());
																	timer.restart();
																}
																return;

			case LEFT_ARROW				:	if (gameInitialised || gameStarted)
																{
																	pad.movePaddle(LEFT, set.getPadSpeed());
																	if (gameInitialised)
																	{
																		ball.moveBall(pad.getStartx(), pad.getLength());
																	}
																	repaint();
																}
																return;
			case RIGHT_ARROW			:	if (gameInitialised || gameStarted)
																{
																	pad.movePaddle(RIGHT, set.getPadSpeed());
																	if (gameInitialised)
																	{
																		ball.moveBall(pad.getStartx(), pad.getLength());
																	}
																	repaint();
																}
																return;
			case UP_ARROW					:	if (gameInitialised)
																{
																	ball.changeDir();
																	repaint();
																}
																return;
			case PAUSE							:	if (gameStarted)
																{
																	timer.stop();
																	JOptionPane pane = new JOptionPane("Game Paused");
																	JOptionPane.showMessageDialog(GameHandler.this, "Game Paused",
																		BOX_TITLE, JOptionPane.INFORMATION_MESSAGE);
																	timer.start();
																}
																return;
			case NEXT_LEVEL				:	if (!initialScreen)
																{
																	if (currLevelNo != LAST_LEVEL)
																	{
																		// initGame() - re-init ball, paddle co-ordinates
																		clearScreen = true;
																		gameStarted = false;
																		gameInitialised = true;
																		initGame();
																		currLevelNo++;
																		try { loadLevel(LEVEL_FILE[currLevelNo]); }
																		catch (Exception ex) {}
																		if (timer.isRunning())
																		{
																			timer.stop();
																		}
																		repaint();
																	}
																}
																return;
			case PREV_LEVEL				:	if (!initialScreen)
																{
																	if (currLevelNo != FIRST_LEVEL)
																	{
																		// initGame() - re-init ball, paddle co-ordinates
																		clearScreen = true;
																		gameStarted = false;
																		gameInitialised = true;
																		initGame();
																		currLevelNo--;
																		try { loadLevel(LEVEL_FILE[currLevelNo]); }
																		catch (Exception ex) {}
																		if (timer.isRunning())
																		{
																			timer.stop();
																		}
																		repaint();
																	}
																}
																return;
			case QUIT								:	if (initialScreen)
																{
																	if (JOptionPane.showConfirmDialog(GameHandler.this,
																		"Do you want to quit this application?",
																		BOX_TITLE, JOptionPane.YES_NO_OPTION)
																		== JOptionPane.YES_OPTION)
																			System.exit(0);
																	// return;
																}
																else if (gameInitialised || gameStarted) 
																{
																	if (gameStarted)
																	{
																		timer.stop();
																	}
																	if (JOptionPane.showConfirmDialog(GameHandler.this,
																		"Do you want to quit this application?",
																		BOX_TITLE, JOptionPane.YES_NO_OPTION)
																		== JOptionPane.YES_OPTION)
																	{
																		// initGame() - re-init ball, paddle co-ordinates
																		initGame();
																		// init player's score to zero
																		player.setScore(0);
																		// init player's lives to MAX_LIVES
																		player.setLives(MAX_LIVES);
																		currLevelNo = 0;
																		gameStarted = false;
																		gameInitialised = false;
																		initialScreen = true;
																		clearScreen = true;
																		repaint();
																		// return;
																	}
																	else
																	{
																		if (gameStarted)
																		{
																			timer.setDelay(set.getGameSpeed());
																			timer.restart();
																		}
																		// return;
																	}
																}
																return;
			case CHEAT_CODE				:	if (gameStarted)
																{
																	if (ball.isDirLeftDown() || ball.isDirRightDown())
																	{
																		ball.flipyi();
																	}
																}
		} // switch
	} // interpretKey
/*==========================================================================*/	
	// it re-sets the ball and paddle co-ordinates to default values
	public void initGame()
	{
		ball.init();
		pad.init();
	}
/*==========================================================================*/
	// Invoked when a key has been released. 
	public void keyReleased(KeyEvent e) {}
/*==========================================================================*/
	// Invoked when a key has been typed. 
	public void keyTyped(KeyEvent e) {}
/*==========================================================================*/
	public static void main(String s[]) throws IOException
	{
        JApplet applet = new GameHandler();
    }
} // GameHandler
/************************************************************************************/
