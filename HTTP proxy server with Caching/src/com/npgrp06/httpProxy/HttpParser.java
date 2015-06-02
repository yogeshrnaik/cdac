package com.npgrp06.httpProxy;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedInputStream;
import com.npgrp06.util.*;

public class HttpParser
{
	HttpResponse httpresponse;
	DateFormatter dateFormatter;
//	BandwidthProperties.loadProperties();
// create httprequest out of the input stream received fron the client.

HttpParser()
{
	dateFormatter = new DateFormatter();
}

public HttpRequest parseRequest(InputStream in) throws Exception
{
	HttpRequest request;
	RequestLine requestline = new RequestLine();
	HttpHeader header = new HttpHeader(); 
	boolean flag = false;
	DataInputStream sin = new DataInputStream(in);
	int reads=0;
	int totread = 0;
	byte[] arr = new byte[1];
	byte[] finalarr = new byte[1024*1024];
	Date dt = new Date();
	//DateFormat df = dt.getDateInstance();

	System.out.println("HttpParser::within Parser");

/*	if(sin.available() > 0 )
	reads = sin.read(arr);
*/

	while(reads != -1)
	{	
		if(sin.available() > 0 )
		{
			reads = sin.read(arr);

			for(int i=0 ; i<reads ; i++)
				finalarr[totread++] = arr[i];
		}

		else
		break;
	}

	System.out.println("HttpParser::read data");
	byte[] reqBody = new byte[totread];
	
	if(reqBody.length == 0)
	{
		request = null;
		System.out.println("HttpParser::Parse Request:: Request is null");
		return request;
	}

	System.out.println("HttpParser:: request array size is == " + reqBody.length);
	for(int i=0 ; i< totread ; i++)
		reqBody[i] = finalarr[i];

	String req = new String(reqBody);
	System.out.println("HttpParser:: Complete String for request is == " + req);
	Logger.getLogger().logMessageTo("SERVER_LOG","Complete String for request is == "+ req);
	String strtoparse="";
	String substr = "";
//	String body = "";

	int idx  = req.indexOf("\r\n\r\n");
	if(idx != -1)
	{
		strtoparse = req.substring(0,idx);	
//		body = req.substring(idx+4);
//		System.out.println("HttpParser::Body of the parser is== " + body );
	}
			
	else
	{
		System.out.println("HttpParser:: parseRequest() :: thre is no body to parse ");
		strtoparse = req;
	}	

//	System.out.println("HttpParser::String to parse is== " + strtoparse);

// 	this will be the string containing header and request line but no body
			
	StringTokenizer stoken = new StringTokenizer(strtoparse,"\n");

	while(stoken.hasMoreTokens())
	{
		substr = stoken.nextToken();
		
		if(substr.equals("\r"))	
			continue;

		if(substr.charAt(substr.length()-1) == '\r')
			substr = substr.substring(0,substr.length()-1);

		System.out.println("HttpParser::The parsed string is==  " + substr);
		if(!flag)
		{
			System.out.println("HttpParser::parsing requestline");
			requestline = parseRequestLine(requestline,substr);
			flag = true;
		}
		else
			header = parseHeader(header,substr);

	} //end of while 
	
	request = new HttpRequest(requestline,header);	
	request.setHttpRequest(reqBody); // set the byte array for the request
//	request.setRequestBody(body);
	return request;

} // end of method


/*********************************************************************************************************/
//for parsing response received from the originserver

public HttpResponse parseResponse(boolean toSend,Socket clientSocket,InputStream in) throws Exception
{
	HttpResponse response = null;
	HttpHeader header = new HttpHeader();
	StatusLine sline = new StatusLine();
	boolean flag = false;
	
/*
	int read = 0;
	int totread = 0;
	byte[] tmpArr = new byte[1024];
	byte[] tmpBackup;
	byte[] finalArr = new byte[totread];
	byte[] copyArr;
//	DataInputStream sin = new DataInputStream(in);
	
	// for writing to the client socket
	DataOutputStream sout = new DataOutputStream(clientSocket.getOutputStream());

	BufferedInputStream sin = new BufferedInputStream(in);
	
	System.out.println("HttpParser:: Parsing Response");
	// read max of 1Mb at a time

	while(read != -1)
	{
		read = sin.read(tmpArr,0,tmpArr.length);
		if(read == -1)
		break;

//		System.out.println("HttpParser:: Within while loop, no of bytes read is == " + read);  

	// create backup of read data for writing to client

		tmpBackup = new byte[read];

		for(int b = 0 ; b < read ; b++)
			tmpBackup[b] = tmpArr[b];
		
	// copy existing data of finalArr so that size of the array can be 
	// increased dynamically

		copyArr = new byte[finalArr.length];
		for(int i = 0 ; i < finalArr.length ; i++)
			copyArr[i] = finalArr[i];

	// define new size for finalArr
		
		finalArr = new byte[totread + read];
	// copy existing data

		for(int j=0 ; j < copyArr.length ; j++)
			finalArr[j] = copyArr[j];

	// copy new data

		for(int k = 0 ; k < read ; k++)
			finalArr[totread++] = tmpArr[k];

	//send data to the user if toSend is true
//	System.out.println("HttpPrser:: parseResponse(): data to be sent flag is set to =="+ toSend);
	if(toSend)
	{
		try
		{
			sout.write(tmpBackup);
		}
		catch(IOException e)
		{
			System.out.println("Connection closed ... within parser");
			e.printStackTrace();
		}
	}
		
	} // end of while
*/
	String username = com.npgrp06.util.SecurityManager.getSecurityManager().getUserName(clientSocket.getInetAddress());
	byte[] finalArr = readFromServer (toSend,clientSocket, in) ;
	byte[] respArr = new byte[finalArr.length];
	
	for(int g = 0 ; g < finalArr.length ; g++)
		respArr[g] = finalArr[g];

	
	String resp = new String (respArr);
	String strtoparse = "";
	String substr = "";

	int idx = resp.indexOf("\r\n\r\n");

	if(idx == -1)
		strtoparse = resp;
	else
		strtoparse = resp.substring(0,idx);

	System.out.println("HttpParser:: parsing response for string == " + strtoparse);	
	Logger.getLogger().logMessageTo("SERVER_LOG","Response headers for request by " + username + "  is "+ strtoparse);
	Logger.getLogger().logMessageTo(username,"Response headers for request is "+ strtoparse);
	StringTokenizer stoken = new StringTokenizer(strtoparse,"\n");

	while( stoken.hasMoreTokens())
	{
		substr = stoken.nextToken();
		
		if(substr.equals("\r"))
		continue;
		
		if((substr.charAt(substr.length()-1)) == '\r')
			substr = substr.substring(0,substr.length()-1);	

		if(!flag)
		{
			sline = parseStatusLine(substr);
			System.out.println("HttpParser:: Status Line Is ==  "+ substr);	
			flag = true;
		}
		else
		{
			header = parseHeader(header,substr);
			System.out.println("HttpParser:: Header Is == "+ substr);	
		}

	} //end of while 
	
	response = new HttpResponse(sline,header);
	response.setHttpResponse(respArr);
	return response;

} // end of method 

/*********************************************************************************************************/
//parse the request line for the httprequest...called from parseRequest()

public RequestLine parseRequestLine(RequestLine rline,String reqLine)
{
	StringTokenizer stoken = new StringTokenizer(reqLine);
	String mthd = "";
	String url = null;
	String version = "";
//	RequestLine rline=null;

	try
	{
		mthd = stoken.nextToken().toUpperCase();
		url = stoken.nextToken();
		version = stoken.nextToken();
	}
	catch(NoSuchElementException e)
	{
		System.out.println("Token does not exist");
	}		

	try
	{
		URL clienturl = new URL (url);
		rline = new RequestLine(mthd,clienturl,version); 
	}
	catch(java.net.MalformedURLException e)
	{
		System.out.println("Malformed URL");
	}

//	System.out.println("HttpParser:: Method== " + mthd + " URL == " + url + " Version== " + version);
	return rline;

}//end of method


/*********************************************************************************************************/
//parse the header for the httprequest as well as httpresponse 

public HttpHeader parseHeader(HttpHeader header,String hdr)
{

	int idx = hdr.indexOf(":");
	if( idx != -1)
	{
	String headName=hdr.substring(0,idx);
	String headVal = hdr.substring(idx+1).trim();
//	System.out.println("HttpParser::Header ==" + headName + "    HeaderValue == " + headVal);
//**********still to write for dates ... conversion from string to date to be resolved

	if(headName.equalsIgnoreCase("AUTHORIZATION"))
		header.setAuthorization(headVal);
	else if (headName.equalsIgnoreCase("PRAGMA"))
	{
		if(headVal.equalsIgnoreCase("NO-CACHE"))
			header.setPragma(true);
	}
	else if (headName.equalsIgnoreCase("CONTENT-LENGTH"))
		header.setContentLength(Integer.parseInt(headVal));
	else if (headName.equalsIgnoreCase("CONTENT-TYPE"))
		header.setContentType(headVal);
	else if (headName.equalsIgnoreCase("USER-AGENT"))
		header.setUserAgent(headVal);
	else if (headName.equalsIgnoreCase("CONNECTION"))
	{
		if(headVal.equalsIgnoreCase("KEEP-ALIVE"))
			header.setConnectionKeepAlive(true);
	}
	else if (headName.equalsIgnoreCase("LOCATION"))
		header.setLocation(headVal);
	else if (headName.equalsIgnoreCase("ACCEPT"))
		header.setAcceptType(headVal);
	else if (headName.equalsIgnoreCase("ACCEPT-LANGUAGE"))
		header.setAcceptLanguage(headVal);
	else if (headName.equalsIgnoreCase("ACCEPT-ENCODING"))
		header.setAcceptEncoding(headVal);
	else if (headName.equalsIgnoreCase("PROXY-AUTHORIZATION"))
		header.setProxyAuthorization(headVal);
	else if (headName.equalsIgnoreCase("HOST"))
		header.setHost(headVal);
/*	else if(headName.equalsIgnoreCase("IF-MODIFIED-SINCE"))
	{
		if(headVal != null && !(headVal.equals("-1")))
		{
			header.setIfModifiedSince(dateFormatter.getDate(headVal));
		}
	}
*/
	else if(headName.equalsIgnoreCase("LAST-MODIFIED"))
	{
		if(headVal !=null && !(headVal.equals("-1")))
		{
			header.setLastModified(dateFormatter.getDate(headVal));
		}
	}
	else if(headName.equalsIgnoreCase("DATE"))
	{
		if(headVal != null && !(headVal.equals("-1")))
		{
			header.setDate(dateFormatter.getDate(headVal));
		}
	}
	else if(headName.equalsIgnoreCase("EXPIRES"))
        {
		System.out.println("HttpParser:: header is expires");
		if(headVal != null && !(headVal.equals("-1"))) 
                {
			System.out.println("HttpParser:: expires field is not null");
                        header.setExpires(dateFormatter.getDate(headVal));
                }
	}

	}
	else
		System.out.println("HttpParser:: parsing header..the string is not a header");
			
	
	return header;
} //end of method

/*********************************************************************************************************/
public StatusLine parseStatusLine(String str)
{
	StringTokenizer slineToken = new StringTokenizer(str);
	String version = null;
	String code = null;
	String reason = null;

	try	
	{
		version = slineToken.nextToken();
		code = slineToken.nextToken();
		reason = slineToken.nextToken();
	}
	catch(NoSuchElementException e)
        {
                System.out.println("Token does not exist");
        }

		
	StatusLine sline = new StatusLine(version,Integer.parseInt(code),reason);
	return sline;

} // end of method


/*********************************************************************************************************/

public byte[] readFromServer(boolean toSend,Socket client, InputStream in)
{
	int totread = 0;		// total data read from server
	byte[] finalArr = new byte[totread];
	byte[] copyArr;
	byte[] tmpBackup;

	// for writing to the client socket
	DataOutputStream sout = null;
	BufferedInputStream sin = null;
	try
	{
		sout = new DataOutputStream(client.getOutputStream());
		// for reading from origin server
		sin = new BufferedInputStream(in);
	}
	catch (IOException e)
	{
		System.out.println("Unable to create input/output streams for read/write.");
	}
	/*------------------------------------------------------------------------------------*/
	String usergroup = com.npgrp06.util.SecurityManager.getSecurityManager().getUserGroup(client.getInetAddress());
	String username = com.npgrp06.util.SecurityManager.getSecurityManager().getUserName(client.getInetAddress());
	System.out.println("HttpParser:: ReadFromServer(): usergroup == "+ usergroup);
	int bandwidthLimit = BandwidthProperties.getBandwidth(usergroup)/com.npgrp06.util.SecurityManager.getSecurityManager().getUserCount(usergroup);
	System.out.println("HttpParser:: readFromServre() : bandwdth limit == "+bandwidthLimit);
	//int bandwidthLimit = 5*1024;		// in bytes
	int bufferSize = 512;	 // in bytes
	if(bandwidthLimit <= bufferSize)
		bufferSize = bandwidthLimit;
	System.out.println("bandwidth limit == " + bandwidthLimit + " bufferSize == " + bufferSize);
	byte[] buffer = new byte[bufferSize];
	int read = 0;
	int totalRead = 0;		// in one iteration of outer while loop
	long bandwidthTime = 1000;	// in milli-seconds
	double usage = 0.0;
	/*------------------------------------------------------------------------------------*/
	// reading 
	long startTime = 0, endTime = 0;
	while (read != -1)
	{
		totalRead = 0;
		startTime = (long)(new Date().getTime());
		while (totalRead < bandwidthLimit && read != -1)
		{
			//System.out.println("Inner while loop.");
			try
			{
				read = sin.read(buffer, 0, bufferSize);
			}
			catch (IOException e)
			{
				System.out.println("Unable to read data from origin server.");
			}
			if (read == -1)
			{
				break;
			}
			totalRead += read;
			tmpBackup = new byte[read];

			for(int b = 0 ; b < read ; b++)
				tmpBackup[b] = buffer[b];
				
			// copy existing data of finalArr so that size of the array can be 
			// increased dynamically

				copyArr = new byte[finalArr.length];
				for(int i = 0 ; i < finalArr.length ; i++)
					copyArr[i] = finalArr[i];

			// define new size for finalArr
				
				finalArr = new byte[totread + read];
			// copy existing data

				for(int j=0 ; j < copyArr.length ; j++)
					finalArr[j] = copyArr[j];

			// copy new data

				for(int k = 0 ; k < read ; k++)
					finalArr[totread++] = buffer[k];

			//send data to the user if toSend is true
		//	System.out.println("HttpPrser:: parseResponse(): data to be sent flag is set to =="+ toSend);
			if(toSend)
			{
				try
				{
					sout.write(tmpBackup);
				}
				catch(IOException e)
				{
					System.out.println("Connection closed ... within parser");
					e.printStackTrace();
				}
			}

		} // inner while
	//	System.out.println("Outside inner while loop.");
		endTime = (long)(new Date().getTime());
		long time = (endTime - startTime) <= 0 ? 1 : (endTime - startTime);
		usage = totalRead / time;
		Logger.getLogger().logMessageTo("SERVER_LOG","Bandwidth usage for user: " + username + " is: " + usage + " bytes/milli-second.");
		Logger.getLogger().logMessageTo(username,"Bandwidth usage is: " + usage + " bytes/milli-second.");
		System.out.println("Usage : " + usage + " bytes/milli-second.");
		if (time < bandwidthTime && read != -1)
		{
			long sleepTime = bandwidthTime - time;
			sleepTime = sleepTime < 0 ? 0 : sleepTime;
			Logger.getLogger().logMessageTo("SERVER_LOG","Usage exceeded for user: " + username + " - Sleeping thread for " + sleepTime + " milli-seconds.");
			Logger.getLogger().logMessageTo(username,"Usage exceeded - Sleeping thread for " + sleepTime + " milli-seconds.");
			System.out.println("Usage exceeded for user: " + username + " for user: " + username + " for user: " + username + " for user: " + username + " - Sleeping thread for " + sleepTime + " milli-seconds.");
			try
			{
				Thread.sleep(sleepTime);					
			}
			catch (InterruptedException e)
			{
				System.out.println("Interrupted sleeping thread.");
			}
		}
	} // outer while
	System.out.println("Out of outer while loop - total data read = " + finalArr.length);
	return finalArr;
} // end of readFromServer
} //end of class
								
	

