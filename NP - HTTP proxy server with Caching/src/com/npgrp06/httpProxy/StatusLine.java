package com.npgrp06.httpProxy;

import java.io.*;
import java.net.*;

public class StatusLine
{
	String httpVersion;	
	int statusCode;
	String reasonPhrase;

	StatusLine()
	{
	}
	
	StatusLine(String httpVer ,int statcode , String rPhrase)
	{
		this.httpVersion = httpVer;
		this.statusCode = statcode;
		this.reasonPhrase =rPhrase;
	}
	public void setHttpVersion(String httpVer)
	{
		this.httpVersion = httpVer;
	}
	public void setStatCode(int statCode)
	{
		this.statusCode = statCode;
	}
	public void setReasonPhrase(String rPhrase)
	{
		this.reasonPhrase = rPhrase;
	}

	public String getHttpVersion()
	{
		return this.httpVersion;
	}
	public int getStatusCode()
	{
		return this.statusCode;
	}
	public String getReasonPhrase()
	{
		return this.reasonPhrase;
	}
	

	public String toString()
	{
		String sLine = " Http-Version: " + httpVersion + " Status-Code: " + statusCode + " Reason-Phrase: " + reasonPhrase + "\r\n" ; 
		return sLine;
	}



}

