// convert string to date object in a prper format
// input to your method will be a string
// convert that string to date which should be in format "EEE, DD MMM YYYY hh:mm:ss z"

package com.npgrp06.util;
import java.util.*;
import java.text.*;
/*****************************************************************************************************/
public class DateFormatter  
{
	public static Date getDate(String inputDate)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.getDefault()); 
		SimpleDateFormat sdf2 = new SimpleDateFormat ("EEEE, dd-MMM-yy hh:mm:ss z", Locale.getDefault()); 
		SimpleDateFormat sdf3 = new SimpleDateFormat ("EEE MMM d hh:mm:ss yyyy", Locale.getDefault()); 
		Date outputDate = null;
		try
		{
			outputDate = sdf1.parse(inputDate);
		}
		catch (ParseException e1)
		{
			try
			{
				outputDate = sdf2.parse(inputDate);
			}
			catch (ParseException e2)
			{
				try
				{
					outputDate = sdf3.parse(inputDate);
				}
				catch (ParseException e3)
				{
					System.out.println("Cannot convert input string to date - Unknown format");
					return null;
				}
			}
		}
		String output = sdf1.format(outputDate);
		return outputDate;
		//System.out.println("Output = " + output);
	} // end of getDate
/*****************************************************************************************************/
	public static Date incrementDateBy(Date startDate, int days)
	{
		Date nextDate = null;
		for (int i=1; i<=days; i++)
		{	
			long dayLengthInMillis = (long)(24*60*60*1000);
			nextDate = new Date(startDate.getTime() + dayLengthInMillis);
			startDate = nextDate;
			//System.out.println("Tomorrow " + i + " = " + tommorow);
		}
		return nextDate;
	} // end of incrementDateBy
/*****************************************************************************************************/
	public static String getCurrentDate()
	{
		Calendar calendar = new GregorianCalendar();
		//System.out.println(new Date().toString());
		calendar.setTime(new Date());

		String formattedDate =	calendar.get(Calendar.DAY_OF_MONTH) + "_"
								+ calendar.get(Calendar.MONTH) + "_"
								+ calendar.get(Calendar.YEAR) + "_"
								+ calendar.get(Calendar.HOUR_OF_DAY) + "h"
								+ calendar.get(Calendar.MINUTE) + "m"
								+ calendar.get(Calendar.SECOND) + "s";
		return formattedDate;
	}
} // class DateFormatter
/*****************************************************************************************************/
