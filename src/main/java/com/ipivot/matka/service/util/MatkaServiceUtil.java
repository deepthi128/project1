package com.ipivot.matka.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatkaServiceUtil {

	/**
	 * Takes a date value as String and returns Date object The incoming format
	 * has to be specified
	 */
	public static Date getDateFromString(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFormattedDate(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static void main (String[] args) {
		System.out.println(getDateFromString("20160801", "yyyyMMdd"));
		System.out.println(getFormattedDate(new Date(), "yyyy-MM-dd"));
		System.out.println(Double.parseDouble("4.0"));
	}
}
