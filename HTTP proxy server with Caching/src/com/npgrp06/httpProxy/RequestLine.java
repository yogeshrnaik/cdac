package com.npgrp06.httpProxy;

import java.net.URL;
//import java.io.*;

public class RequestLine
{

	String method;
	URL url;
	String version;


	RequestLine()
	{
		//do nothing
	}
	
	RequestLine(URL url , String vrsn)
	{
		this.url = url;
		this.version = vrsn;
	}

	RequestLine(String mthd , URL url , String vrsn)
	{

		this.method = mthd;
		this.url = url;
		this.version = vrsn;

	}


	public void setMethod(String mthd)
	{
		this.method= mthd;
	}

	public void setUrl(URL url)
	{
		this.url = url;
	}

	public void setVersion(String vrsn)
	{
		this.version = vrsn;
	}

	public String getMethod()
	{
		return this.method;
	}

	public URL getUrl()
	{
		return this.url;
	}

	public String getVersion()
	{
		return this.version;
	}

	public String toString()
	{

		String rline = "" ;
		rline += this.method + " " + url + " " + version + "\r\n";
		return rline;

	}

	
}		

		
