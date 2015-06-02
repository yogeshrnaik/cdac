package com.npgrp06.httpProxy;

import java.io.*;
import java.net.*;
import com.npgrp06.cache.*;
import com.npgrp06.util.*;
import java.util.Date;

public class HttpRequestHandler
{
	CacheManager cacheManager = CacheManager.getCacheManager();
	com.npgrp06.util.SecurityManager securityManager = com.npgrp06.util.SecurityManager.getSecurityManager();
	HttpParser httpParser = new HttpParser();

        public HttpResponse handleRequest(Socket clientSocket,HttpRequest httpReq) throws Exception
        {

	        HttpHeader httpheader;
        	boolean pragma = false;
		boolean refresh = false;
        	String stringResp = " ";
    		HttpResponse httpResp = null;
            	CachedContent cachedPage;
		String username = securityManager.getUserName(clientSocket.getInetAddress());

 
		System.out.println("HttpRequestHandler:: within handler"); 

		if(httpReq == null)
			return httpResp;

		Logger.getLogger().logMessageTo(username,"Request is : "+ httpReq.toString());		
		URL url = httpReq.getRLine().getUrl();
		String method = httpReq.getRLine().getMethod();
		String strurl = url.toString();
		
		// check if the requested url is blocked

		if(securityManager.checkIfBlocked(strurl))
		{
			Logger.getLogger().logMessageTo("SERVER_LOG","Page request by " + username+ " is blocked....Access forbidden");
			Logger.getLogger().logMessageTo(username,"Requested page is blocked....Access forbidden");
                        String ebody = "<HTML><HEAD><TITLE>";
                        ebody += "403 Forbidden";
                        ebody += "</TITLE></HEAD>";
                        ebody += "<BODY><H2>";
                        ebody += "ERROR 403 FORBIDDEN";
                        ebody += "</H2>";
                        ebody += "<P><H3>";
                        ebody += "You do not have Permission to access this site";
                        ebody += "</BODY></HTML>";
			stringResp = ebody;     				 //resp in string format
			byte[] blockedToClient = stringResp.getBytes();		 //resp in byte format 
			DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
			try
			{
				dout.write(blockedToClient);
				clientSocket.close();
			}
			catch(IOException e)
			{
				System.out.println("Connection closed while sending cached page");
			}
			return httpResp;		
		}

		// do not cache the response page if method is POST
		else if(method.equalsIgnoreCase("POST"))
		{
			System.out.println("HttpRequestHandler:: handlerequest(): Method is Post");
			Logger.getLogger().logMessageTo("SERVER_LOG","Posting the page requested by " + username);
			Logger.getLogger().logMessageTo(username,"Posting the request page.");
			httpResp = getFromOriginServer(true,clientSocket,httpReq);
			return httpResp;
		}	

		// check for pargma no-cache
		
		else if((pragma = httpReq.getHttpHeader().getPragma()) == true ) 
		{
			System.out.println("HttpRequestHandler:: page request is set with pragma no-cache");
			Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by "+username+" is set with pragma:no-cache");
			Logger.getLogger().logMessageTo(username,"Requested page is set with pragma:no-cache");
			if(cacheManager.isPresentInCache(url))
			refresh = true;
			httpResp = getFromOriginServer(true,clientSocket,httpReq);
		}

		// check if the page is present in cache

		else if(cacheManager.isPresentInCache(url))
		{
			boolean expired = false;
			cachedPage = cacheManager.getCachedPageForProxy(url);
			Date currentDate = new Date();
			Date expiryDate = cachedPage.getExpiryDate();
			Date cleanupDate = cachedPage.getCleanupDate();
			Date lastAccessDate = cachedPage.getLastAccessedDate();
			System.out.println("HttpRequestHandler:: page present in cache ");
			System.out.println("HttpRequestHandler:: expiryDate == " + expiryDate);
			System.out.println("HttpRequestHandler:: cleanupDate == " + cleanupDate);
			Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by "+username+" is present in cache");
			Logger.getLogger().logMessageTo(username,"Requested page is present in cache");
	
			// check for expiry date if set or else check for cleanupdate

			if( expiryDate == null)
			{
				expired = currentDate.after(cleanupDate);
			}
			else
			{
				expired = currentDate.after(expiryDate);
			}
			
			// if the page has expires then send a request with if-modified-since set and get the refreshed page
			// in this case do not send the page to the client before checking 


			if(expired)
			{
				System.out.println("HttpRequestHandler:: page has expired...fetch refreshed page");
				Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by " + username + " has expired");
				Logger.getLogger().logMessageTo(username,"Requested page has expired");
				refresh = true;
				httpReq.getHttpHeader().setIfModifiedSince(lastAccessDate);
				System.out.println("HttpRequestHandler:: new request with if-modified-since :: " + httpReq.toString());
				byte[] newReq = (httpReq.toString()).getBytes();
				httpReq.setHttpRequest(newReq);
				httpResp = getFromOriginServer(false,clientSocket,httpReq);
			}

			else
			{	
				System.out.println("HttpRequestHandler:: page has not expired...fetch page from cache");
				Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by " + username + " has not expired");
				Logger.getLogger().logMessageTo(username,"requested page has not expired");
				sendCachedPage(url,clientSocket);
				return httpResp;
			/*
				byte[] response = cachedPage.getCachedPage();
				DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
				try
				{
					dout.write(response);
					clientSocket.close();
				}
				catch(IOException e)
				{
					System.out.println("Connection closed while sending cached page");
				}
			*/
			} //end of else
			
		} // end of if
		
		// get the request page from the origin server
		else	
		{
			System.out.println("HttpRequestHandler:: page not present in cache...fetch page from origin server");
			Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by " + username + " is not in cache..fetching from Origin Server");
			Logger.getLogger().logMessageTo(username,"Requested page is not present in cache...fetching from origin server");
			httpResp = getFromOriginServer(true,clientSocket,httpReq);
		}

		//deal with the response for caching purposes

		if(httpResp != null)
		{
			int statusCode = httpResp.getStatusLine().getStatusCode();
			Date expiryDate= httpResp.getHttpHeader().getExpires();
			Date currentDate = new Date();
				
			// for if-modified-since if the status code is 304 then send cached page

			if( statusCode == 304)
			{
				System.out.println("HttpRequestHandler:: page has not been modified");
				Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by "+ username + " has not been modified..fetch it from cache");
				Logger.getLogger().logMessageTo(username,"Requested page has not been modified..fetch it from cache");
				sendCachedPage(url,clientSocket); 
			}
	
			else if(statusCode == 200)
			{

				// if pragma no-cache is set for response do not cache the page
				//either expiry date is null or it is an expired page and pragma is not set
	
				if(!(httpResp.getHttpHeader().getPragma()) && ((expiryDate == null) || !(currentDate.after(expiryDate))))
				{
					//refresh may be set for pragma no-cache or an expired page
						
					if(refresh)
					{
						Logger.getLogger().logMessageTo("SERVER_LOG","Page requested by "+ username + " has been modified..refresh page in cache");
                                		Logger.getLogger().logMessageTo(username,"Requested page has been modified..refresh page in cache");	
						cacheManager.refreshPage(url,httpResp);
								
						if(!pragma)
						{
					//		sendCachedPage(url,clientSocket);
							sendOriginalResponse(httpResp,clientSocket);
						}
						
					}
					else
					{
						cacheManager.addToCache(url,httpResp);
					}
					
					return httpResp;
				} // end of if
				else
				{
					// pragma no-cache is set then for if-modified pages send the page directly to client 	
					if(refresh)
					{
						if(!pragma)
						{
							
							Logger.getLogger().logMessageTo("SERVER_LOG","Sending modified page requested by "+ username + "without caching");
                                			Logger.getLogger().logMessageTo(username,"Sending modified page without caching");
							sendOriginalResponse(httpResp,clientSocket);
	/*
							DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
							try
							{
								dout.write(httpResp.getHttpResponse());
								clientSocket.close();
							}
							catch(IOException e)
							{
								System.out.println("Connection closed while sending cached page");
							}
	*/
							return httpResp;		
						}
					}
				}// end of else
			} //end of else if
		} // end of if
		
		return httpResp;
       } // end of method

/*****************************************************************************************************/

	public void sendOriginalResponse(HttpResponse response , Socket clientSocket)
	{
		 DataOutputStream dout ;
                 try
                 {
		      dout = new DataOutputStream(clientSocket.getOutputStream());
                      dout.write(response.getHttpResponse());
                      clientSocket.close();
                 }
                 catch(IOException e)
                 {
                      System.out.println("Connection closed while sending cached page");
                 }
	}

/*****************************************************************************************************/
 
       	public HttpResponse getFromOriginServer(boolean toSend,Socket clientSocket,HttpRequest httpReq) throws Exception
       	{
		HttpResponse httpResp;
		Socket sock = null;
       		byte[] reqToOriginServer = httpReq.getHttpRequest();
        	URL url = (httpReq.getRLine()).getUrl();
		System.out.println("HttpRequestHandler::  Host name == " + url.getHost());
		String host = url.getHost();
        	try
		{
			sock = new Socket(host,80);
	//		sock.setSoTimeout(0);
		}
	 	catch(UnknownHostException uhe)
        	{
               		 //write a page to be sent to the client
                	uhe.printStackTrace();
                	System.out.println("Improper Address");
        	}

	    	DataOutputStream proxyOut = new DataOutputStream(sock.getOutputStream());
		System.out.println("HttpRequestHandler:: getfromoriginServer length of request array ==  " + reqToOriginServer.length);
		try
		{
       		proxyOut.write(reqToOriginServer);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error in sending request to the origin server");
		}

        	InputStream inp = sock.getInputStream();
		System.out.println("HttpRequestHandler:: got input stream ...sending for parsing to parser");
		httpResp = httpParser.parseResponse(toSend,clientSocket,inp);
		inp.close();
		sock.close();	
		return httpResp;

       	} // end of method

/*****************************************************************************************************/

	public void sendCachedPage(URL url , Socket clientSocket)
	{	
		CachedContent cachedPage = cacheManager.getCachedPageForClient(url);
		byte[] response = cachedPage.getCachedPage();
		DataOutputStream dout;
		try
		{
			dout = new DataOutputStream(clientSocket.getOutputStream());
			dout.write(response);
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.out.println("Connection closed while sending cached page");
		}
		return;
	}

} // end of class

/*****************************************************************************************************/
