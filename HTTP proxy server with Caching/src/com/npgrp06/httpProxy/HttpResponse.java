package com.npgrp06.httpProxy;

//import java.net.*;
//import java.io.*;
import java.lang.*;

public class HttpResponse
{

	StatusLine statusline;
	HttpHeader httpheader;
//	String responseBody;
	byte[] httpResponse;

/*****************************************************************************************************/
/*
        HttpResponse(StatusLine statline,HttpHeader httphder,String ebody)
        {
                this.statusline = statline;
                this.httpheader = httphder;
                this.responseBody = ebody;
        }
*/

	HttpResponse(StatusLine statline,HttpHeader httphder)
        {
                this.statusline = statline;
                this.httpheader = httphder;
        }

/*****************************************************************************************************/

	public StatusLine getStatusLine()
	{
		return this.statusline;
	}
	public void setStatusLine(StatusLine statline)
	{
		this.statusline = statline;
	}

/*****************************************************************************************************/

	public HttpHeader getHttpHeader()
	{
		return this.httpheader;
	}
        public	void setHttpHeader(HttpHeader httphder)
	{
		this.httpheader = httphder;
	}

/*****************************************************************************************************/
/*
	public String getResponseBody()
	{
		return this.responseBody;
	}
	public void setResponseBody(String ebody)
	{
		this.responseBody = ebody;
	}
*/
	
/*****************************************************************************************************/

	public byte[] getHttpResponse()
	{
		return this.httpResponse;
	}
	public void setHttpResponse(byte[] resp)
	{
		this.httpResponse = resp;
	}

/*****************************************************************************************************/
/*
	public String toString()
	{
		String httpResp="";
		httpResp += statusline.toString() + httpheader.toString() +  "\r\n";
		return httpResp;			
	}
*/
}

