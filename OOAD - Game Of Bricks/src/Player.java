/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/************************************************************************************/
class Player implements CONSTANTS
{
	private static Rectangle infoBorder;
	private static Color borderColor = Color.white;
	private static Color textColor = Color.white;
	private String name;
	private int score;
	private int lives;
/*==========================================================================*/	
	Player(Dimension dim, String name)
	{
		infoBorder = new Rectangle(10, 10, dim.width-30, 90);
		this.name = name;
		score = 0;
		lives = MAX_LIVES;
	}
/*==========================================================================*/
	public String getName() {return name;}
	public void setName(String n)
	{
		name = n;
	}
	public int getScore() {return score; }
	public void setScore(int s)
	{
		score = s;
	}
	public void setLives (int l)
	{
		lives = l;
	}
	public int getLives() {return lives;}
/*==========================================================================*/
	// it displays the Player's information
	public void displayPlayerInfo(Graphics g, int levelNo)
	{
		g.setColor(borderColor);
		g.drawRect(infoBorder.x, infoBorder.y, infoBorder.width, infoBorder.height);
		g.setColor(Color.yellow);
		g.setFont(new Font("ARIAL", 1, 17));
		
		g.drawString("Player Name  : " + name,  infoBorder.x+10, infoBorder.y+20);
		g.drawString("Score            : " + score, infoBorder.x+10, infoBorder.y+40);
		g.drawString("No. of lives    : " + lives, infoBorder.x+10, infoBorder.y+60);
		g.drawString("Level No.       : " + levelNo, infoBorder.x+10, infoBorder.y+80);

		// displays the key-board shortcuts for various options
		g.drawString("p : Pause Game", infoBorder.width-150, infoBorder.y+20);
		g.drawString("q : Quit Game", infoBorder.width-150, infoBorder.y+40);
		g.drawString("n : Next Level", infoBorder.width-150, infoBorder.y+60);
		g.drawString("b : Previous Level", infoBorder.width-150, infoBorder.y+80);

		// displays the title "GAME OF BRICKS"
		g.setColor(Color.green);
		g.setFont(new Font("ARIAL", 1, 50));
		g.drawString("GAME OF BRICKS", infoBorder.width/2 - 200, infoBorder.y+65);
	}
/*==========================================================================*/
	public void updateScore(int s)
	{
		score += s;
	}
	public void reduceLives()
	{
		lives--;
	}
	public boolean hasLife()
	{
		return (lives > 1);
	}
} // Player
/************************************************************************************/