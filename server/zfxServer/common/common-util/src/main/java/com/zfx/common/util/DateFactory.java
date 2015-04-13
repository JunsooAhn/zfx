package com.zfx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateFactory {
    private final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
    private final static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat ymd1 = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat h = new SimpleDateFormat("HH");

    public static String dateConvertToTimeStampBysd(String dateString) {
        try {
            Date date;
            date = sd.parse(dateString);
            Long tt = date.getTime();
            return tt.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateConvertToTimeStampByDf(String dateString) {
        try {
            Date date;
            date = df.parse(dateString);
            Long tt = date.getTime();
            return tt.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String dateConvertToTimeStampByHM(String dateString) {
        try {
            Date date;
            date = hm.parse(dateString);
            Long tt = date.getTime();
            return tt.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateConvertToTimeStampBySDf(String dateString) {
        try {
            Date date;
            date = sdf.parse(dateString);
            Long tt = date.getTime();
            return tt.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Long dateConvertToTimeStampBySDfReturnLong(String dateString) {
        try {
            Date date;
            date = sdf.parse(dateString);
            Long tt = date.getTime();
            return tt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long dateConvertToTimeStampByDfReturnLong(String dateString) {
        try {
            Date date;
            date = df.parse(dateString);
            Long tt = date.getTime();
            return tt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String timeStampConvertToDateByDf(String dateString) {
        String str = df.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateBySDf(String dateString) {
        String str = sdf.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateByHM(String dateString) {
        String str = hm.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateByYMD(String dateString) {
        String str = ymd.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateBysd(String dateString) {
        String str = sd.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateByymd1(String dateString) {
        String str = ymd1.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String timeStampConvertToDateByH(String dateString) {
        String str = h.format(new Date(Long.parseLong(dateString)));
        return str;
    }

    public static String getDateByMonth(int month) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -month);
        return ymd1.format(calendar.getTime()) + " 00:00:00";
    }

    public static String getDateByDay(int daily) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -daily);
        return ymd1.format(calendar.getTime()) + " 00:00:00";
    }
    
    public static Date getDateAfter(String dateStr, int day) {  
    	Date date;
		try {
			date = df.parse(dateStr);
			Calendar now = Calendar.getInstance();  
	        now.setTime(date);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
	        return now.getTime(); 
		} catch (ParseException e) {
		}
         return null;
    } 
    
    public static String toDayString(Calendar calendar) {
    	return ymd1.format(calendar.getTime());
    }
    
    public static Calendar toDayCalender(String dayStr) {
    	try {
			Date date = ymd1.parse(dayStr);
			Calendar result = Calendar.getInstance();
			result.setTime(date);
			return result;
		} catch (Exception e) {
		}
    	return null;
    }
    
    public static Integer getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar =  getYYMMDDCalendar(startDate);
  
        Calendar toCalendar = getYYMMDDCalendar(endDate);
  
        return Integer.parseInt(String.valueOf((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)));  
    } 
	
	public static Calendar getYYMMDDCalendar(Date date) {
		Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(date);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
        return fromCalendar;
	}
	
	public static String parseStringTime(SimpleDateFormat sdf ,String time) {
		try {
			return String.valueOf(sdf.parse(time).getTime());
		} catch (ParseException e) {
		}
		return null;
	}
	
	// 确保
	public static Integer getDaysBetween(Calendar startDay, Calendar endDay) {
		startDay.set(Calendar.HOUR_OF_DAY, 0);  
		startDay.set(Calendar.MINUTE, 0);  
		startDay.set(Calendar.SECOND, 0);  
		startDay.set(Calendar.MILLISECOND, 60);
		return (int) ((endDay.getTime().getTime() - startDay.getTime().getTime() + 30) / (1000 * 60 * 60 * 24));
	}
	
	public static int getIntDay(Calendar calendar) {
		int result = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
		return result;
	}
}