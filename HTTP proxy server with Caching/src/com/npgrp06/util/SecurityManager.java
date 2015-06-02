package com.npgrp06.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.regex.*;
import com.npgrp06.util.*;
/*****************************************************************************************************/
public class SecurityManager implements ActionListener
{
	//String blockedSites[];
	ArrayList blockedSites;
	ArrayList activeUsers;
	Timer timer;
	static SecurityManager sm = null;

	private SecurityManager()
	{
		activeUsers = new ArrayList();
		blockedSites = new ArrayList();
		initBlockedSitesData();
		timer = new Timer(ConfigProperties.getUserTimeOut() * 1000, this);	 // converting in milli-seconds
		timer.start();
	}
/*****************************************************************************************************/
	public static SecurityManager getSecurityManager()
	{
		if (sm == null)
		{
			sm = new SecurityManager();
		}
		return sm;
	}
/*****************************************************************************************************/
	// read from "blocked.txt" file and create an array of Strings
	public void initBlockedSitesData()
	{
		try
		{
			String fileName = ConfigProperties.getBlockedSitesFile(); //"/home/apgdst/np/d0331092/com/npgrp06/util/blockedSites.txt";
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while ((str = br.readLine()) != null)
			{
				blockedSites.add(new String(str));
			}
			br.close();
			fr.close();
		}
		catch (Exception e)
		{
			System.out.println("Error : Unable to initialise the blocked sites data.");
			//e.printStackTrace();
		}
	} // end of initBlockedSitesData() method
/*****************************************************************************************************/
	public  boolean checkIfBlocked(String url) throws PatternSyntaxException
	{
		// using ArrayList
		Pattern pattern;
	    Matcher matcher;
		boolean found;
		// blockedSites.trimToSize();
		for (int i=0; i<blockedSites.size(); i++)
		{
			try
			{
				pattern = Pattern.compile((String)blockedSites.get(i));
			}
			catch (PatternSyntaxException e)
			{
				System.out.println((String)blockedSites.get(i) + " is not a valid regular expression.");
				continue;
			}
			matcher = pattern.matcher(url);
			if (matcher.find())
			{
				return true;
			}
		}
		return false;
	} // end of checkIfBlocked
/*****************************************************************************************************/
	public boolean isLoggedIn(InetAddress ip)
	{
		String address = ip.toString();
		// activeUsers.trimToSize();

		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser au = (ActiveUser)activeUsers.get(i);
			if (au.getInetAddress().toString().equals(address))
			{
				return true;
			}
		}
		return false;
	}
/*****************************************************************************************************/
	// this method checks for authentication of the user
	// if user is a valid user then a new ActiveUser object is created and added to the ArrayList of ActiveUsers
	public boolean authenticate(Socket client, String encodedStr)
	{
		InetAddress ip = client.getInetAddress();
		
		/* open the users file where info of all users 
		is stored in format - encodedString : username : password : usergroup ; */
		String fileName = ConfigProperties.getUserDetailsFile(); //"/home/apgdst/np/d0331092/com/npgrp06/util/blockedSites.txt";
		FileReader fr ;
		BufferedReader br ;
		try
		{
			fr = new FileReader(fileName);			
			br = new BufferedReader(fr);
		}
		catch (FileNotFoundException e)
		{
			Logger.getLogger().logMessageTo("SERVER_LOG", "Unable to open the user details file.");
			return false;
		}
		String str = "";
		while (str != null)
		{
			try
			{
				str = br.readLine();				
			}
			catch (IOException e)
			{
				Logger.getLogger().logMessageTo("SERVER_LOG", "Unable to read from the user details file.");
				return false;
			}
			if (str == null)
				break;
			StringTokenizer st = new StringTokenizer(str, ":;");
			String validEncodedStr = st.nextToken().trim();
			String username = st.nextToken().trim();
			String password = st.nextToken().trim();
			String usergroup = st.nextToken().trim();

			// if username and password provided are correct
			if (validEncodedStr.equals(encodedStr))
			{
				// user is authenticated successfully 
				// create ActiveUser entry
				activeUsers.add(new ActiveUser(username, password, usergroup, ip));
				Logger.getLogger().logMessageTo("SERVER_LOG", "User : " + username + " logged in at " + new Date());
				Logger.getLogger().logMessageTo(username,"User logged in at "+ new Date());	
				try
				{
					fr.close();
					br.close();
				}
				catch (IOException e)
				{
					Logger.getLogger().logMessageTo("SERVER_LOG", "Unable to close user details file.");
					return true;
				}
				return true;
			}
		}
		try
		{
			fr.close();
			br.close();
		}
		catch (IOException e)
		{
			Logger.getLogger().logMessageTo("SERVER_LOG", "Unable to close user details file.");
		}
		return false;
	} // authenticate
/*****************************************************************************************************/
	// used to log message to a particular userlog given the username
	public  void logMessageTo(String username, String message)
	{
		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(i);
			if (curr.getUserName().equals(username))
			{
				curr.logMessage(message);
				return;
			}
		}
	}
/*****************************************************************************************************/
	// returns the username of the user logged in at a particular machine
	public  String getUserName(InetAddress ip)
	{
		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(i);
			if (curr.getInetAddress().toString().equals(ip.toString()))
			{
				return curr.getUserName();
			}
		}
		return "";
	}
/*****************************************************************************************************/
	// check and remove all those users who have not sent any request 
	// for the last ConfigProperties.getUserTimeOut() time
	public void actionPerformed (ActionEvent e)
	{
		int count = 0;
		while (count < activeUsers.size())
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(count);
			long lastRequestTime = (long)curr.getLastRequestAt().getTime();
			long currTime = (long)(new Date().getTime());
			long diff = (long)(currTime - lastRequestTime)/1000;		// in seconds
			if (diff > ConfigProperties.getUserTimeOut())
			{
				// logout the user
				//ActiveUser curr = (ActiveUser)activeUsers.get(count);
				String username = curr.getUserName();
				curr.closeLog();
				Logger.getLogger().logMessageTo("SERVER_LOG", "User : " + username + " logged out at " + new Date());
				Logger.getLogger().logMessageTo(curr.getUserName(), "User : logged out at " + new Date());
				curr.closeLog();
				activeUsers.remove(count);
				continue;
			}
			else
				count++;
		}
	}
/*****************************************************************************************************/
	public  int getUserCount(String usergroup)
	{
		int count = 0;
		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(i);
			String group = curr.getGroupName();
			System.out.println("SecurityManager:: getUserCount() : groupname == "+group);
			if (group.equals(usergroup))
			{
				count++;
			}
		}
		System.out.println("SecurityManager:: getUserCount(): total count == "+count);
		return count;
	}
/*****************************************************************************************************/
	// returns the usergroup of the user logged in at a particular machine
	public  String getUserGroup(InetAddress ip)
	{
		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(i);
			if (curr.getInetAddress().toString().equals(ip.toString()))
			{
				return curr.getUserGroup();
			}
		}
		return "";
	}
	public void setLastAccessAtFor(String username)
	{
		for (int i=0; i<activeUsers.size(); i++)
		{
			ActiveUser curr = (ActiveUser)activeUsers.get(i);
			if (curr.getUserName().equals(username))
			{
				curr.setLastRequestAt(new Date());
				break;
			}
			
		}	
	}
/*****************************************************************************************************/
	/* public static void main(String[] args)
	{
		SecurityManager sm = new SecurityManager();
		System.out.println("ncst's site is in blocked sites list = "
							+ SecurityManager.checkIfBlocked("www.ncst.ernet.in"));
	}*/
} // end of SecurityManager 
/*****************************************************************************************************/
