package com.npgrp06.httpProxy;

import java.util.Date;

public class HttpHeader
{
	String authorization="";
	Date ifModifiedSince=null;
	Date lastModified=null;
	Date date=null;
	Date expires=null;
	String userAgent="";
	boolean pragma=false;
	int contentLength=0; 
	String contentType="";
	String location="";
	String authenticate="";
	String acceptType="";
	String acceptLanguage="";
	String acceptEncoding="";
	String proxyAuthorization="";
	String host="";
	boolean connectionKeepAlive=false;

/*****************************************************************************************************/
	HttpHeader()
	{
		//do nothing
	}

/*****************************************************************************************************/

	public String getAuthorization()
	{
		return this.authorization;
	}

	public void setAuthorization(String auth)
	{
		this.authorization = auth;
	}

/*****************************************************************************************************/

	public Date getLastModified()
	{
		return this.lastModified;
	}	
	
	public void setLastModified(Date lm)
	{
		this.lastModified = lm;
	}	

/*****************************************************************************************************/

	public Date getIfModifiedSince()
	{
		return this.ifModifiedSince;
	}

	public void setIfModifiedSince(Date dt)
	{
		this.ifModifiedSince = dt;
	}
	
/*****************************************************************************************************/

	public Date getExpires()
	{
		return this.expires;
	}

	public void setExpires(Date date)
	{
		System.out.println("HttpHeader: setting expirydate with value :: " + date);
		this.expires = date;
	}
	
/*****************************************************************************************************/

	public String getUserAgent()
	{
		return this.userAgent;
	}

	public void setUserAgent(String uagent)
	{
		this.userAgent = uagent;
	}

/*****************************************************************************************************/

	public String getLocation()
	{
		return this.location;
	}

	public void setLocation(String Location)
	{
		this.location = Location;
	}

/*****************************************************************************************************/

	public Date getDate()
	{
		return this.date;
	}

	public void setDate(Date dt)
	{
		this.date = dt;
	}

/*****************************************************************************************************/

	public boolean getPragma()
	{
		return this.pragma;
	}
	
	public void setPragma(boolean flg)
	{
		this.pragma = flg;
	}

/*****************************************************************************************************/

	public int getContentLength()
	{
		return this.contentLength;
	}

	public void setContentLength(int num)
	{
		this.contentLength = num;
	}

/*****************************************************************************************************/

	public String getContentType()
	{
		return this.contentType;
	}

	public void setContentType(String str)
	{
		this.contentType = str;
	}

/*****************************************************************************************************/

	public String getAcceptType()
	{
		return this.acceptType;
	}

	public void setAcceptType(String str)
	{
		this.acceptType = str;
	}

/*****************************************************************************************************/

	public boolean getConnectionKeepAlive()
	{
		return this.connectionKeepAlive;
	}

	public void setConnectionKeepAlive(boolean bl)
	{
		this.connectionKeepAlive = bl;
	}

/*****************************************************************************************************/

	public void setAcceptLanguage(String str)
	{	
		this.acceptLanguage = str;
	}

/*****************************************************************************************************/

	public void setAcceptEncoding(String str)
	{
		this.acceptEncoding = str;
	}

/*****************************************************************************************************/

	public void setHost(String ht)
	{
		this.host = ht;
	}

/*****************************************************************************************************/

	public String getProxyAuthorization()
	{
		return this.proxyAuthorization;
	}

	public void setProxyAuthorization(String auth)
	{
		this.proxyAuthorization = auth;
	}

/*****************************************************************************************************/

	public String toString()
	{
		String header="";
		if(!acceptType.equals(""))	
		header += "Accept: " + acceptType + "\r\n";
		if(!acceptLanguage.equals(""))
		header += "Accept-Language: " + acceptLanguage + "\r\n";
		if(!acceptEncoding.equals(""))
		header += "Accept-Encoding: " + acceptEncoding + "\r\n";
		if(!userAgent.equals(""))
		header += "User-Agent: " + userAgent + "\r\n";
		if(!host.equals(""))
		header += "Host: " + host + "\r\n";
		if(!authorization.equals(""))	
		header += "Authorization: " + authorization + "\r\n";
		if(lastModified != null)
		header += "Last-Modified: " + lastModified.toString() + "\r\n";
		if(ifModifiedSince != null)
		header += "If-Modified-Since: " + ifModifiedSince.toString() + "\r\n";
		if(date != null)
		header += "Date: " + date.toString() + "\r\n";
		if(expires != null)
		header += "Expires: " + expires.toString() + "\r\n";
		if(pragma)
		header += "Pragma: " + "no-cache" + "\r\n";
		if( contentLength != 0)
		header += "Content-Length: " + contentLength + "\r\n";
		if(!contentType.equals(""))
		header += "Content-Type: " + contentType + "\r\n";
		if(!location.equals(""))
		header += "Location: " + location + "\r\n";
		header += "Connection: " + "Keep-Alive" + "\r\n";
		if(!proxyAuthorization.equals(""))
		header += "Proxy-Authorization: " + proxyAuthorization + "\r\n";
		header += "\r\n";
		return header;
	}

} //end of class

/*****************************************************************************************************/
