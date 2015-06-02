package com.npgrp06.httpProxy;

import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import com.npgrp06.util.*;

public class ProxyChild implements Runnable
{
	Thread child;
	Socket browserSocket;

	ProxyChild(Socket sock)
	{
		this.browserSocket = sock;
		child = new Thread(this);
		child.start();
	}
	public void run() // of ProxyChild
	{
		try
		{
			// create an object of HttpRequestHandler and parser
			HttpRequestHandler reqHandler = new HttpRequestHandler();
			HttpParser parser = new HttpParser();

			// get the object of HttpRequest
			HttpRequest request = parser.parseRequest(browserSocket.getInputStream());
			
			if ( request == null)
			{
				Logger.getLogger().logMessageTo("SERVER_LOG","Request is null...terminating the thread");
				browserSocket.close();
				return;
			}
						
			// check whether the client is authenticated or not
			if (!com.npgrp06.util.SecurityManager.getSecurityManager().isLoggedIn(browserSocket.getInetAddress()))
			{
				// if client is not authenticated 
				//	- check if the current request contains the authentication header
				if (request.hasAuthHeader())
				{
					// authenticate
					String encodedStr = request.getHttpHeader().getProxyAuthorization();
				//	System.out.println("Encoded String is:: " + encodedStr );
					if (!com.npgrp06.util.SecurityManager.getSecurityManager().authenticate(browserSocket, encodedStr))
					{
						// if client is not authenticated
						// send error
						// prompt for username/password again
						// terminate thread
						Logger.getLogger().logMessageTo("SERVER_LOG","User: " + browserSocket.getInetAddress().toString() + " is not authenticated");
						System.out.println("User is not authenticated.");
						sendAuthenticationMessage();

					}
					else
						Logger.getLogger().logMessageTo("SERVER_LOG","Invalid Username and Password");
				}
				else // request does not have autherization header
				{
					// send error
					// prompt for username/password again
					// terminate thread
					Logger.getLogger().logMessageTo("SERVER_LOG","User: " + browserSocket.getInetAddress().toString() + " is not authenticated");
					System.out.println("User is not authenticated.");
					sendAuthenticationMessage();
					//return;
				}
			}
			// if thread is not terminated (i.e. everything is OK) 
			//	- then process the request
			if (com.npgrp06.util.SecurityManager.getSecurityManager().isLoggedIn(browserSocket.getInetAddress()) && browserSocket.isConnected())
			{
/*
				 if (request.hasAuthHeader())
                                {
                                        request.getHttpHeader().setProxyAuthorization("");
					System.out.println("ProxyChild:: Modified request is: " + request.toString());
                                        byte[] httpRequest  = request.toString().getBytes();
                                        request.setHttpRequest(httpRequest);
                                }

*/				String username = com.npgrp06.util.SecurityManager.getSecurityManager().getUserName(browserSocket.getInetAddress());
				com.npgrp06.util.SecurityManager.getSecurityManager().setLastAccessAtFor(username);
				System.out.println("Calling handleRequest from ProxyChild.");
				reqHandler.handleRequest(browserSocket, request);
			}
			
			if(browserSocket.isConnected())
			browserSocket.close();
			System.out.println("Thread execution completed.");
		} // end of try
		catch (Exception e)
		{
			System.out.println("Thread terminated due to error.");
		}
	} // end of run method
/***************************************************************************************************/
	public void sendAuthenticationMessage()
	{
		try
		{
			OutputStream op = browserSocket.getOutputStream();
			
			//String authenticationMessage =	"HTTP/1.1 401 Unauthorized\r\n" + "WWW-Authenticate: Basic\r\n";
			String authenticationMessage = "HTTP/1.1 407 Proxy Authentication Required\r\n" 
											+ "Proxy-Authenticate: Basic\r\n";
			
			
			//System.out.println("authenticationMessage to be sent = " + authenticationMessage);

			byte[] response = authenticationMessage.getBytes();
			/*for (int i=0; i<response.length; i++)	{ System.out.print((char)response[i]);}
			System.out.println();*/

			//System.out.println("sending authentication response");
			op.write(response);
			//System.out.println("authentication response sent");
			op.flush();
			op.close();
			browserSocket.close();
		}
		catch (IOException e)
		{
			System.out.println("Error in the sending authentication message.");
		}
	}

} // end of class
/***************************************************************************************************/





	/* Old method
	public void run()
	{
		HttpRequestHandler requestHandler = new HttpRequestHandler();
		HttpParser parser = new HttpParser();
		System.out.println("Spawned a child");
		try
		{
/*		while(socket.isConnected())
		{
*/
/*			if(socket.isConnected())
			{
				HttpRequest request = parser.parseRequest(socket.getInputStream()); 
				HttpResponse response = requestHandler.handleRequest(socket,request);
/*
				if(response != null)
				{	
				OutputStream oStream = socket.getOutputStream();

				try
				{
					oStream.write(response.getHttpResponse());
				}
				catch(IOException e)
				{
					e.printStackTrace();
					System.out.println("Connection closed");
				}
				} // end of if
//				else
//				break;
				

			} //end of if
//		} // end of while
			
/*			socket.close();
		} // end of try
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Socket stream IOException");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Socket Stream Exception");
		}

	} // end of method
	*/

