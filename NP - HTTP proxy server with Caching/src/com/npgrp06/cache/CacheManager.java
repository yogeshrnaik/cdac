package com.npgrp06.cache;

import java.io.*;
import java.net.*;
import java.util.*;
import com.npgrp06.httpProxy.*;
import com.npgrp06.util.*;
/*****************************************************************************************************/
public class CacheManager extends Hashtable implements Serializable
{
	int cacheAvailableSize;
	int cacheSize;
	Vector cacheList;
	String cacheLog;
	String cachePolicy;
	static CacheManager cacheManager = null;

	private CacheManager()
	{
//		ConfigProperties.loadProperties();
		cacheSize = ConfigProperties.getCacheSize();
		cacheLog = ConfigProperties.getCacheLog();
		cachePolicy = ConfigProperties.getCachePolicy();
		cacheAvailableSize = cacheSize;
		cacheList = new Vector (100, 10);
	}
/*****************************************************************************************************/
	public static CacheManager getCacheManager ()
	{
		if (cacheManager == null)
		{
			System.out.println("CacheMgr:: initialising cachemgr for the first time");
			Logger.getLogger().logMessageTo("SERVER_LOG","Initializing cache manager");
			if(!loadCacheManager())
				cacheManager = new CacheManager();
			else
			{
				/*
				System.out.println("Printing CacheManager content after loading from file.....");
				//printCacheManager();
				System.out.println("Cache list = " + cacheManager.cacheList);
				System.out.println("Cache Available size = " + cacheManager.cacheAvailableSize);
				System.out.println("Cache size = " + cacheManager.cacheSize);
				System.out.println("Cache policy = " + cacheManager.cachePolicy);
				System.out.println("Cache cache log file path = " + cacheManager.cacheLog);
				*/
			}
		}
		return cacheManager;
	}
/*****************************************************************************************************/
	public static boolean loadCacheManager()
	{
		FileInputStream istream = null;
		ObjectInputStream oip = null;
		try
		{
			istream = new FileInputStream(ConfigProperties.getCacheFile());
		}
		catch(FileNotFoundException fne)
		{
			Logger.getLogger().logMessageTo("SERVER_LOG","Cache File not found");
			Logger.getLogger().logMessageTo("CACHE_LOG","Cache File not found");
			System.out.println("Cache File not found");
			return false;
		}
					
		try
		{
			oip = new ObjectInputStream(istream);
			cacheManager = (CacheManager)oip.readObject();	
			istream.close();
			oip.close();
			Logger.getLogger().logMessageTo("CACHE_LOG","Loading the cache file");
		}
		catch (ClassNotFoundException e )
		{
			Logger.getLogger().logMessageTo("SERVER_LOG","Unable to read object from file.");
			Logger.getLogger().logMessageTo("CACHE_LOG","Unable to read object from file.");
			System.out.println("Unable to read object from file.");
			return false;
		}
		catch(IOException ioe)
		{
			Logger.getLogger().logMessageTo("SERVER_LOG","Error in closing the cache file stream");
			Logger.getLogger().logMessageTo("CACHE_LOG","Error in closing the cache file stream");
			System.out.println("error in closing the cache file stream");
			return false;
		}
		return true;
	}

/*****************************************************************************************************/

	public static void storeCacheManager()
	{
		// writing object to file
		FileOutputStream ostream = null;
		synchronized(cacheManager)
		{
			try
			{
				//System.out.println("Storing cache manager in the file............*************************");
				ostream = new FileOutputStream(ConfigProperties.getCacheFile());
				ObjectOutputStream oos = new ObjectOutputStream(ostream);
				oos.writeObject(cacheManager);
				oos.flush();
				ostream.close();
				oos.close();
				/*
				System.out.println("cache manager written to the file++++++++++++++++++");
				System.out.println("reading cache manager just after writing------------");
				System.out.println("printing cache manager just after reading----------");
				loadCacheManager();
				try {
				cacheList = new Vector ((Collection)cacheManager.keySet());
				}
				catch(Exception e)
				{ System.out.println("error in re-building the cacheList.......");
				}
				System.out.println("cacheList after reading again == " + cacheList);
				printCacheManager();*/
				
			}


			catch(FileNotFoundException fnfe)
			{
				Logger.getLogger().logMessageTo("SERVER_LOG","File not found for storing cache manager");
				Logger.getLogger().logMessageTo("CACHE_LOG","File not found for storing cache manager");
				System.out.println("File not found for storing cache manager");
			//	fnfe.printStackTrace();
			}
			catch(IOException ioe)
			{
				Logger.getLogger().logMessageTo("SERVER_LOG","Unable to store cache manager");
				Logger.getLogger().logMessageTo("CACHE_LOG","Unable to store cache manager");
				System.out.println("Unable to store cache manager");
			//	ioe.printStackTrace();
			}	
		}
	}
	
/*****************************************************************************************************/

// to add a new page to the cache...not a modified page
	public void addToCache(URL url, HttpResponse httpResp)
	{ 
		System.out.println("CacheMgr:: AddToCache():: Adding page for the url == " + url);
		HttpResponse response = httpResp;
		Date lastAccess = new Date();
		Date expiryDate = (response.getHttpHeader()).getExpires();
		Date cleanupDate = expiryDate;
		if(expiryDate == null)
		{
			cleanupDate = DateFormatter.incrementDateBy(lastAccess,5);
		}
		System.out.println("CAcheMgr:: addToCache():: expiryDate == " + expiryDate + " lastAccessDate == " + lastAccess+ " cleanupDAte == " + cleanupDate);
		byte[] cachedpage = httpResp.getHttpResponse();
		int sizeofbody = cachedpage.length;	
		CachedContent c = new CachedContent(url,1,lastAccess,lastAccess,expiryDate,cleanupDate,cachedpage, sizeofbody);
		
		if(cacheAvailableSize < sizeofbody )
		{
			//perform the page replacement algorithm	
			System.out.println("CacheManager: addtoCache(): calling algorithm for page replacement");
			replacePage(sizeofbody);
		}
		
		synchronized(cacheManager)
		{
			put(url,c);
		}
		Logger.getLogger().logMessageTo("CACHE_LOG","Added page: " + url + " to cache");
		cacheAvailableSize = cacheAvailableSize - sizeofbody;
		addToCacheList(url);
		System.out.println("CacheMgr:: Page added to the cache");
		CacheManager.storeCacheManager();
		
		/*System.out.println("Printing cache manager after adding url : " + url);	
		printCacheManager();*/
		return;

	} // end of method

/*****************************************************************************************************/

//to add the update the  page expired page
public void refreshPage(URL url, HttpResponse response)
{
	System.out.println("CacheMgr:: refreshPage() :: Updating the Cached Page ");
	Logger.getLogger().logMessageTo("CACHE_LOG","Updating the cached page: "+ url);
	Date lastAccess = new Date();
	Date expiryDate = response.getHttpHeader().getExpires();
	Date cleanupDate = expiryDate;
	if(expiryDate == null)
	{
		cleanupDate = DateFormatter.incrementDateBy(lastAccess,5);
	}

	byte[] cachedpage = response.getHttpResponse();
	
	CachedContent page = (CachedContent)get(url);

	//reduce/increse the cacheavailable size by the difference between the 2 values
	int initialSize = page.getSizeOfPage();	
	int currentSize = cachedpage.length;
	int diff = currentSize - initialSize;
	cacheAvailableSize = cacheAvailableSize - diff;

	Date entryDate = page.getEntryDate();
	synchronized(cacheManager)
	{
		int hits = page.getHits();
		CachedContent c = new CachedContent(url,hits+1,entryDate,lastAccess,expiryDate,cleanupDate,cachedpage,currentSize);
		put(url,c);
	}
	CacheManager.storeCacheManager();
	// page refreshed

} // end of method
	
/*****************************************************************************************************/

//remove the page from the cache
	public synchronized void removeFromCache(URL url)
	{
		int sizeofbody = ((CachedContent)get(url)).getSizeOfPage();
		if(isPresentInCache(url))
		{
			remove(url);
			cacheAvailableSize =cacheAvailableSize + sizeofbody;
			removeFromCacheList(url);
		}
		Logger.getLogger().logMessageTo("CACHE_LOG","Removing page: " + url + " from cache.");
	}
/*****************************************************************************************************/

	public CachedContent getCachedPageForClient(URL url)
	{
		CachedContent cachedPage = null;

		if(isPresentInCache(url) == true)
		{
			System.out.println("CacheMgr:: getCachedPage():: Page is present in cache...fetching the page");
			cachedPage = (CachedContent)get(url);
			synchronized(cacheManager)
			{	
				int hits = cachedPage.getHits();
				System.out.println("CacheMgr:: getCachedPage():: OLD no. of hits == " + hits + " OLD accessdate = " + cachedPage.getLastAccessedDate());
				cachedPage.setHits(hits+1);
				cachedPage.setLastAccessedDate(new Date());
				put(url,cachedPage);
			}
			CacheManager.storeCacheManager();
		}
		
		Logger.getLogger().logMessageTo("CACHE_LOG","Sending page: " +  url + " to client.");	
		//just for checking purposes...to removed from the final implementation
		if(cachedPage != null)
		{
			cachedPage = (CachedContent) get(url);
			System.out.println("CacheMgr:: getCachedPage():: NEW no of hits == " + cachedPage.getHits() + " NEW lastAccessDate == " + cachedPage.getLastAccessedDate() );
		}
 
		return cachedPage;
	} 
/*****************************************************************************************************/

	public CachedContent getCachedPageForProxy(URL url)
	{
		CachedContent cachedPage = null;

		if(isPresentInCache(url))
			cachedPage = (CachedContent)get(url);

		return cachedPage;
	}

/*****************************************************************************************************/
 	public boolean isPresentInCache(URL url)
	{
		return containsKey(url);
	}
/*****************************************************************************************************/
// CACHELIST functions...add element , delete element , check if an element is present
	private void addToCacheList(URL url)
	{
		if(checkIfPresent(url) == false)
			cacheList.add(url);
	}
/*****************************************************************************************************/
	private boolean checkIfPresent(URL url)
	{
		return cacheList.contains(url);
	}
/*****************************************************************************************************/
	private void removeFromCacheList(URL url)
	{
		int idx = cacheList.indexOf(url);
		System.out.println("CacheMgr:: removeFromCacheList() :: element : " + url + " exists at index : " + idx);
		if( idx != -1)
		{
			 cacheList.removeElementAt(idx);
		}
	}	
/*****************************************************************************************************/
	/* methods:check page present()....isPresentInCache().
                   updatecache()..updateCache()			
		   lru()..PR	
		   fifo()..PR
	           cleanup() ..cleanUp()
		   getLastAccessedDate()....called by HttpRequestHandler..HandleRequest()		
	*/
		
	public void cleanUp()
	{
		System.out.println("Cleanup started....");
		//chk for the expiry date ....del the expired page.
		Date today = new Date();
		Logger.getLogger().logMessageTo("CACHE_LOG","Doing cleanup.");
		//for(each item in hash)..how to form a loop..?...is enumeration a solution..?
		System.out.println("getting iterator to iterate........." + " Cache list " + cacheList);
		Iterator iter = cacheList.iterator();	
		System.out.println("got iterator - starting to iterate....");
		while (iter.hasNext())
		{
			URL url = (URL)iter.next();
			System.out.println("Checking cleanup date for url : " + url);	
			Date expires = ((CachedContent)get(url)).getCleanupDate();
			System.out.println("expires date for url : " + url + " is = " + expires );
			if(expires != null)
			{
				boolean deadpage = today.after(expires);	//returns true ..if page has expired
				if (deadpage)
				{
					System.out.println("Calling removeFromCache from cleanup() - removing url = " + url );
					removeFromCache(url);
				} // end of if
			} // end of if
		} // end of while
		System.out.println("CacheManager::Done with cleanup");
	} // end of method

/*****************************************************************************************************/
	
	public void replacePage(int incomingPageSize)
	{
		System.out.println("Cache available size before cleanup = " + cacheAvailableSize );	
		cleanUp();
		System.out.println("Cache available size after cleanup = " + cacheAvailableSize);	
		URL oldestUrl=null;
		Date oldestDate=null;
		int  oldestEntryhits=0;
		
		if (cacheAvailableSize < incomingPageSize)
		{
			Logger.getLogger().logMessageTo("CACHE_LOG","Applying "+cachePolicy+ " for cached page replacement.");
			if (cachePolicy.equalsIgnoreCase("LRU"))
			{
		  			//perform LRU
				System.out.println("Page replacement()::performing lru");
				while(cacheAvailableSize < incomingPageSize)
				{
					Iterator iter = cacheList.iterator();
					
					if(iter.hasNext())
					{
						oldestUrl = (URL)iter.next();
						oldestDate	= ((CachedContent)get(oldestUrl)).getLastAccessedDate();
						oldestEntryhits  = ((CachedContent)get(oldestUrl)).getHits();
					}

					while(iter.hasNext())
					{
						URL uNext = (URL)iter.next();	
						Date dNext = ((CachedContent)get(uNext)).getLastAccessedDate();
						int hNext =  ((CachedContent)get(uNext)).getHits();
						
						int comparedValue = oldestDate.compareTo(dNext); //returns +ve if dNext is be4 oldestDate 
						if (comparedValue > 0)
						{
							oldestDate = dNext;
							oldestUrl = uNext;
							oldestEntryhits = hNext;
						} 
						else if(comparedValue == 0)   //with same last accessed date..second criteria..hits.
						{
							if(oldestEntryhits >= hNext)
							{
								oldestDate = dNext;
                              					oldestUrl = uNext;
                               					oldestEntryhits = hNext;
							} //  end of if
						} //end of else if
					} //traversed till the end of vector cacheList, end of while

					removeFromCache(oldestUrl);
				}//end of outer while
			}//end of if (lru)
			else 
			{
				while(cacheAvailableSize < incomingPageSize)
				{	

					//perform FIFO
					URL url = (URL)cacheList.elementAt(0);
					removeFromCache(url);
				
				} // end of while
			} //end of else.
		} // end of first if
	} //end of method
/*****************************************************************************************************/
	public void printCacheManager()
	{
		System.out.println("Cache list = " + cacheList);
		System.out.println("Cache Available size = " + cacheAvailableSize);
		System.out.println("Cache size = " + cacheSize);
		System.out.println("Cache policy = " + cachePolicy);
		System.out.println("Cache cache log file path = " + cacheLog);
	}
} //end of class
/*****************************************************************************************************/
