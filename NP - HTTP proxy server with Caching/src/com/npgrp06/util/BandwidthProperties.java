package com.npgrp06.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
/*****************************************************************************************************/
public class BandwidthProperties
{
	public static final String bandwidthFile  =ConfigProperties.getBandwidthFile();
	//"/home/apgdst/np/d0112041/proxy/server/bandwidth.txt";
	public static Properties properties = null;

	public static void loadProperties()
	{
		try
		{
			File in_file = new File(bandwidthFile);
			BufferedInputStream sin = null;
			sin = new BufferedInputStream(new FileInputStream(in_file));

			properties = new Properties();
			properties.load(sin);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
/*****************************************************************************************************/
	public static int getBandwidth(String groupname)
	{
	//	String formattedname = groupname;//.toUpperCase();
		System.out.println("BandwidthProperties:: getBandwidth: bandwidth is == " +properties.getProperty(groupname));
		return Integer.parseInt(properties.getProperty(groupname));
	}
/*****************************************************************************************************/
	public static void setBandwidth(String groupname,int val)
	{
		String formattedname = groupname.toUpperCase();
		String tmp = val+"";
		properties.setProperty(formattedname,tmp);
	}
}
/*****************************************************************************************************/
