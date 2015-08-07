package com.npgrp06.util;

import java.io.*;
import java.util.*;
import java.net.*;
import com.npgrp06.util.*;
/*****************************************************************************************************/
public class ActiveUser 
{
	String username;
	String password;
	String usergroup;

	/* - used as a criteria to logout the user if he does not send request for some time 
		 - this variable stores the date and time when the user has sent the last request. */
	Date lastRequestAt;

	InetAddress ipAddress;
	FileWriter userLog;
	
	ActiveUser (String username, String password, String usergroup, InetAddress ipAddress)
	{
		this.username = username;
		this.password = password;
		this.usergroup = usergroup;
		this.ipAddress = ipAddress;
		try
		{
			// open the a new user log file
			String logFileName = ConfigProperties.getUserLog() + username + "_" +	DateFormatter.getCurrentDate() + ".txt";
			userLog = new FileWriter(new File(logFileName));
		}
		catch (IOException e)
		{
			System.out.println("Cannot open log file for user : " + username);
		}
	}
/*****************************************************************************************************/
	public void logMessage(String message)
	{
		synchronized (userLog)
		{
			try
			{
//				System.out.println(message);
				userLog.write(message+"\n");
				userLog.flush();
			}
			catch (IOException e)
			{
				System.out.println("Enable to write to user log file of user : " + username);
			}
		}
	}
/*****************************************************************************************************/
	public Date getLastRequestAt()
	{
		return lastRequestAt;
	}
	public void setLastRequestAt(Date now)
	{
		lastRequestAt = now;
	}
/*****************************************************************************************************/
	public String getUserName ()
	{
		return username;
	}
/*****************************************************************************************************/
	public String getGroupName ()
	{
		return usergroup;
	}
	public String getUserGroup()
	{
		return usergroup;
	}
	public InetAddress getInetAddress()
	{
		return ipAddress;
	}
/*****************************************************************************************************/
	public void  closeLog()
	{
		try
		{
			userLog.close();			
		}
		catch (IOException e)
		{
			System.out.println("Unable to close user log file of user : " + username);
		}
	}
} // end of ActiveUser
/*****************************************************************************************************/
