package com.lxy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String date2Str(Date date,String pattern){
		
		SimpleDateFormat adf=new SimpleDateFormat(pattern);//2019-07-08
		return adf.format(date);
		
	}
}
