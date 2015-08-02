package com.amb.mm.travel.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.amb.mm.travel.Constants;

public class DateTimeHelper {
	public static final int getDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIME_ZONE_ID));
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
