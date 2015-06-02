package com.npgrp06.cache;

import java.io.*;
import java.net.*;
import java.util.*;
/*****************************************************************************************************/
public class CachedContent implements Serializable
{
	URL url;
	int hits;
	Date entryDate;
	Date lastAccessedDate;
	Date expiryDate;
	Date cleanupDate;
	byte[] cachedPage;
	int sizeOfPage;
		
	CachedContent()
	{
		//Default constructor
	}
	CachedContent(URL url,int hits , Date entry,Date lastAccess,Date expirydt,Date clDate,byte[] entirepage, int size)
	{
		this.url = url;
		this.hits = hits;
		this.entryDate = entry;
		this.lastAccessedDate = lastAccess;
		this.expiryDate =expirydt;
		this.cleanupDate = clDate;
		//cachedPage = new byte[entirepage.length];
		this.cachedPage = entirepage;
		this.sizeOfPage = size;
	}
/*****************************************************************************************************/
	public URL getUrl()
	{
		return this.url;
	}
	public void setUrl(URL url)
	{
		this.url = url;
	}
/*****************************************************************************************************/
	public int getHits()
	{
		return this.hits;
	}
	public void setHits(int hits)
	{
		this.hits = hits;
	}
/*****************************************************************************************************/
	public Date getEntryDate()
	{
		return this.entryDate;
	}
	public void setEntryDate(Date entry)
	{
		this.entryDate = entry;
	} 
/*****************************************************************************************************/
	public Date getLastAccessedDate()
	{
		return this.lastAccessedDate;
	}
	public void setLastAccessedDate(Date lastAccess)
	{
		this.lastAccessedDate = lastAccess;
	}
/*****************************************************************************************************/
	public Date getExpiryDate()
	{	
		return this.expiryDate;
	}
	public void setExpiryDate(Date expiry)
	{
		this.expiryDate = expiry;
	}
/*****************************************************************************************************/

	public Date getCleanupDate()
	{
		return this.cleanupDate;
	}

	public void setCleanupDate(Date date)
	{
		this.cleanupDate = date;
	}

/*****************************************************************************************************/

	public byte[] getCachedPage()
	{
		return this.cachedPage;
	}
	public void setCachedPage(byte[] bodyOfPage)
	{
		this.cachedPage = bodyOfPage;
	}
/*****************************************************************************************************/
	public int getSizeOfPage()
	{
		return this.sizeOfPage;
	}
	public void setSizeOfPage(int size)
	{
		this.sizeOfPage = size;
	}
}
/*****************************************************************************************************/
