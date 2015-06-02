package com.npgrp06.httpProxy;

import java.net.*;
import java.io.*;
import com.npgrp06.util.*;
import com.npgrp06.cache.*;

/*****************************************************************************************************/

public class ProxyServer
{
	public static void main (String[] args) throws IOException 
	{
		// initialise the properties from the property file
		ConfigProperties.loadProperties();	
		BandwidthProperties.loadProperties();
		// initialise the Logger
		Logger logger = Logger.getLogger();
		CacheManager.getCacheManager();
		logger.logMessageTo("SERVER_LOG","Initializing server...Reading from Configuration file");
		System.out.println("Initializing server...Reading from Configuration file");
		// start the server to listen to a port specified in the properties file
		InetSocketAddress inetsocketadd = new InetSocketAddress(ConfigProperties.getServerPort());
		ServerSocket server = new ServerSocket();
		server.setReuseAddress(true);
		server.bind((SocketAddress)inetsocketadd);
       		boolean flag = true;

		if(inetsocketadd != null)
		{
			while(flag)
			{
				System.out.println("Listening at port: " +  ConfigProperties.getServerPort());
				logger.logMessageTo("SERVER_LOG", "Listening at port: " +  ConfigProperties.getServerPort());
				Socket connection = server.accept();			// accept requests from browser
				logger.logMessageTo("SERVER_LOG","Spawning a new thread for a new request");
				ProxyChild child = new ProxyChild(connection);	// create a new proxy child for each request
			} //end of while

		} //end of if

	} //end of main
} //end of class
/*****************************************************************************************************/
