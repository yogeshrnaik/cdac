package com.npgrp06.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
/*****************************************************************************************************/
public class ConfigProperties
{	
	public static final String configFile ="/home/apgdst/np/d0331092/new_final_demo/server/configuration.txt";
	public static Properties properties = null;

	public static void loadProperties()
	{
		try
		{
			File in_file = new File(configFile);
			BufferedInputStream sin = null;
			sin = new BufferedInputStream(new FileInputStream(in_file));

			properties = new Properties();
			properties.load(sin);
			sin.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
/*****************************************************************************************************/
	public static void saveProperties()
	{
		try
		{
			File file = new File(configFile);
			BufferedOutputStream sout = null;
			sout = new BufferedOutputStream(new FileOutputStream(file));
			properties.store(sout, null);
			sout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
/*****************************************************************************************************/
	public static int getCacheSize()
	{
		return Integer.parseInt(properties.getProperty("CACHE_SIZE"));
	}

	public static void setCacheSize(int val)
	{
		String tmp = val+"";
		properties.setProperty("CACHE_SIZE",tmp);
	}
/*****************************************************************************************************/
	public static int getServerPort()
	{
		return Integer.parseInt(properties.getProperty("SERVER_PORT"));
	}

	public static void setServerPort(int val)
	{
		String tmp = val+"";
		properties.setProperty("SERVER_PORT",tmp);
	}
/*****************************************************************************************************/
	public static String getServerLog()
	{
		return properties.getProperty("SERVER_LOG");
	}

	public static void setServerLog(String val)
	{
		properties.setProperty("SERVER_LOG",val);
	}
/*****************************************************************************************************/
	public static String getCacheLog()
	{
		return properties.getProperty("CACHE_LOG");
	}

	public static void setCacheLog(String val)
	{
		properties.setProperty("CACHE_LOG",val);
	}
/*****************************************************************************************************/
	public static String getUserLog()
	{
		return properties.getProperty("USER_LOG");
	}

	public static void setUserLog(String val)
	{
		properties.setProperty("USER_LOG",val);
	}
/*****************************************************************************************************/
	public static String getBandwidthFile()
	{
		return properties.getProperty("BANDWIDTH_FILE");
	}

	public static void setBandwidthFile(String path)
	{
		properties.setProperty("BANDWIDTH_FILE",path);
	}
/*****************************************************************************************************/
	public static String getCacheFile()
	{
		return properties.getProperty("CACHE_FILE");
	}

	public static void setCacheFile(String path)	
	{
		properties.setProperty("CACHE_FILE",path);
	}
	
/*****************************************************************************************************/
	public static String getUserDetailsFile()
	{
		return properties.getProperty("USER_DETAILS_FILE");
	}

	public static void setUserDetailsFile(String path)
	{
		properties.setProperty("USER_DETAILS_FILE",path);
	}
/*****************************************************************************************************/
	public static String getBlockedSitesFile()
	{
		return properties.getProperty("BLOCKEDSITES_FILE");
	}

	public static void setBlockedSitesFile(String path)
	{
		properties.setProperty("BLOCKEDSITES_FILE",path);
	}
/*****************************************************************************************************/
	public static int getUserTimeOut()
	{
		return Integer.parseInt(properties.getProperty("USER_TIMEOUT"));
	}
	public static void setUserTimeOut(int time)	// time in seconds
	{
		String tmp = time + "";
		properties.setProperty("USER_TIMEOUT", tmp);
	}

/*****************************************************************************************************/
	public static String getCachePolicy()
	{
		return properties.getProperty("CACHE_POLICY");
	}

	public static void setCachePolicy(String policy)
	{
		properties.setProperty("CACHE_POLICY",policy);
	}

} // end of class
/*****************************************************************************************************/

