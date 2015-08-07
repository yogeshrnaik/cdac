/************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/************************************************************************************/
class Setting extends JFrame implements CONSTANTS
{
	private int gameSpeed;
	private int padSpeed;
/*==========================================================================*/
	Setting()
	{
		gameSpeed = GAMESPEED_MEDIUM;			// default value
		padSpeed = PAD_LOW;										// default value
	}
/*==========================================================================*/
	public int getGameSpeed()
	{
		return gameSpeed;
	}
	public int getPadSpeed()
	{
		return padSpeed;
	}
/*==========================================================================*/
	public void changeSetting() 
	{
		String[] padSetting =	{	"Low",
												"Medium",
												"High",
												"Maximum"
											};
		// Paddle Speed Setting
		String speed = (String)JOptionPane.showInputDialog(Setting.this,
		"Please select Paddle Speed : ",
		"Paddle Speed", JOptionPane.INFORMATION_MESSAGE,
		null, padSetting, "Low");
		if (speed != null)
		{
			if(speed.equals("Low"))
			{
				padSpeed = PAD_LOW;
			}
			else if(speed.equals("Medium"))
			{
				padSpeed = PAD_MEDIUM;
			}
			else if(speed.equals("High"))
			{
				padSpeed = PAD_HIGH;
			}
			else if (speed.equals("Maximum"))
			{
				padSpeed = PAD_MAX;
			}
		}
		else	// if (speed == null)
			return;

		// Game Speed Setting
		String[] gameSetting =	{	"Low",
													"Medium",
													"High"
												};
		speed = (String)JOptionPane.showInputDialog(Setting.this,
		"Please select Game Speed : ",
		"Game Speed", JOptionPane.INFORMATION_MESSAGE, 
		null, gameSetting, "Low");
		if (speed != null)
		{
			if(speed.equals("Low"))
			{
				gameSpeed = GAMESPEED_LOW;
			}
			else if(speed.equals("Medium"))
			{
				gameSpeed = GAMESPEED_MEDIUM;
			}
			else if(speed.equals("High"))
			{
				gameSpeed = GAMESPEED_HIGH;
			}
		}
	} //changeSetting
} // Setting
/************************************************************************************/