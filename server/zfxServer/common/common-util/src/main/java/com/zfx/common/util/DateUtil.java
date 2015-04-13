package com.zfx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static SimpleDateFormat ymdNoDash = new SimpleDateFormat("yyyyMMdd");

	public final static SimpleDateFormat ymdDash = new SimpleDateFormat("yyyy-MM-dd");

	public final static SimpleDateFormat ymdHmsDash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static long hour24InMillis = 24 * 60 * 60 * 1000;

	private final static long startMillis = DateUtil.toDate("1998-01-01", ymdDash).getTime();

	public static String toString(Calendar cal, SimpleDateFormat dateFormat) {
		return dateFormat.format(cal.getTime());
	}

	public static String toString(Date date, SimpleDateFormat dateFormat) {
		return dateFormat.format(date);
	}

	public static int toDayInt(Calendar cal) {
		return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Date toDate(String dateStr, SimpleDateFormat dateFormat) {
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static long dayToMilliSeconds(int days) {
		return days * hour24InMillis;
	}

	public static int getDayOffset(Date start, Date end) {
		return (int) ((end.getTime() - startMillis) / hour24InMillis - (start.getTime() - startMillis) / hour24InMillis);
	}
}
