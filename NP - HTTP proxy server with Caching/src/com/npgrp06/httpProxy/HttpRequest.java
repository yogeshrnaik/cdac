package com.npgrp06.httpProxy;

//import java.net.*;
//import java.io.*;

public class HttpRequest
{
	RequestLine rLine;	
	HttpHeader header;
	String requestBody;
	byte[] httpRequest;

/***************************************************************************************************/
	
	HttpRequest()
	{
	}

	HttpRequest(RequestLine req , HttpHeader hdr ,String  body)
	{
		this.rLine = req;
		this.header=hdr;
		this.requestBody = body;
	}

	HttpRequest(RequestLine req , HttpHeader hdr)
	{
		this.rLine = req;
		this.header = hdr;
	}

/***************************************************************************************************/

	public String toString()
	{
		String httpReq = "";
		//String body = new String(requestBody);
		httpReq += rLine.toString();
		httpReq += header.toString();
//		if(!requestBody.equals(""))
//		httpReq += requestBody;
		
		return httpReq;
	}
	
/***************************************************************************************************/
	public HttpHeader getHttpHeader()
	{
		return this.header;
	}

/***************************************************************************************************/

	public RequestLine getRLine()
	{
		return this.rLine;
	}

/***************************************************************************************************/
	
	public String getRequestBody()
	{
		return this.requestBody;
	}

	public void setRequestBody(String req)
	{
		this.requestBody = req;
	}

/***************************************************************************************************/
	public byte[] getHttpRequest()
	{
		return this.httpRequest;
	}

	public void setHttpRequest(byte[] arr)
	{
		this.httpRequest = arr;
	}		

/***************************************************************************************************/
	public boolean hasAuthHeader()
	{
		String str = header.getProxyAuthorization();
		if (str == null || str.equals(""))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
/***************************************************************************************************/
	public String getAuthorization()
	{
		return header.getAuthorization();
	}
/*	
	public static void main(String[] args)
	{

	}
*/
} // end of class HttpRequest
/***************************************************************************************************/
