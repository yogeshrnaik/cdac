package com.npgrp06.util;

import java.io.*;
/*****************************************************************************************************/
public class Logger
{
	FileWriter serverLog;
	FileWriter cacheLog;
	static Logger logger=null;
	
	private Logger()
	{
		try
		{
			File serverLogFile = new File(ConfigProperties.getServerLog() + "server_" + DateFormatter.getCurrentDate() + ".txt");
			serverLog = new FileWriter(serverLogFile);

			File cacheLogFile = new File(ConfigProperties.getCacheLog() + "cache_" + DateFormatter.getCurrentDate() + ".txt");
			cacheLog = new FileWriter(cacheLogFile);
		}
		catch (IOException e)
		{
			System.out.println("Unable to open server and cache log files.");
		}
	}
/*****************************************************************************************************/
	public static Logger getLogger()
	{
		if (logger == null)
		{
			logger = new Logger();
		}
		return logger;
	}
/*****************************************************************************************************/
	public void logMessageTo (String log, String message)
	{
		if (log.equals("SERVER_LOG"))
		{
			updateServerLog(message);
		}
		else if (log.equals("CACHE_LOG"))
		{
			updateCacheLog(message);
		}
		else // update user log
		{
			SecurityManager.getSecurityManager().logMessageTo(log, message);
		}
	}
/*****************************************************************************************************/
	public void updateServerLog(String message)
	{
		synchronized (serverLog)
		{
			try
			{
//				System.out.println(message);
				serverLog.write(message+"\n");
				serverLog.flush();
			}
			catch (IOException e)
			{
				System.out.println("Unable to write to server log file.");
			}
		}
	}
/*****************************************************************************************************/
	public void updateCacheLog(String message)
	{
		synchronized (cacheLog)
		{
			try
			{
////				System.out.println(message);
				cacheLog.write(message+"\n");
				cacheLog.flush();	
			}
			catch (IOException e)
			{
				System.out.println("Unable to write to cache log file.");
			}
		}
	}
/*****************************************************************************************************/
	public void closeLog()
	{
		try
		{
			serverLog.close();
			cacheLog.close();
		}
		catch (IOException e)
		{
			System.out.println("Unable to close cache and server log files.");
		}
	}
} // end of Logger
/*****************************************************************************************************/
