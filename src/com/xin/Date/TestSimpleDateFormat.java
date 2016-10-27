package com.xin.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//Data,Calendar,SimpleDateFormat类的练习
public class TestSimpleDateFormat {
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd, HH:mm DDD z ");//设置时间格式
		sb.setTimeZone(TimeZone.getTimeZone("GMT+8"));//我国处在东8时区
		System.out.println(sb.format(date));//打印当前时间
		System.out.println(date);
		
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		System.out.println(sb.format(c.getTime()));
		System.out.println(c.getTime());
		System.out.println(c.getTimeInMillis());//毫秒数 ---常用来做随机数；
		
		System.out.println(c.get(Calendar.DAY_OF_YEAR));//当前年天
		System.out.println(c.get(Calendar.DATE));//当前月日
		System.out.println(c.get(Calendar.MONTH) + 1);//当前月
	}
}
