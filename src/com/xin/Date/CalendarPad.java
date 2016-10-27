package com.xin.Date;

//制作一个日历；
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalendarPad {
	String[] stringOfWeek = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	int year, month, days;
	int dayOfWeek; // 星期几；
	int sum = 0; // 表示从1900年到该月之前的总天数
	Calendar c = Calendar.getInstance();

	public CalendarPad(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public void show() {
		dayOfWeek = this.getdayOfWeek();
		days = this.getDayOfMonth();

		// 打印日历头；
		System.out.println(year + "年" + month + "月");
		for (String i : stringOfWeek) {
			System.out.print(i + '\t');
		}
		System.out.println();
		// 打印空地；
		for (int i = 0; i < dayOfWeek; i++) {
			System.out.print("\t");
		}
		// 输入一个月的天数排布情况；
		for (int i = 1; i <= days; i++) {
			// 如果是今天就特殊标记一下;
			if (year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) + 1 && i == c.get(Calendar.DATE)) {
				System.out.print("*");
			}
			if (sum % 7 == 6) { // 让其每输入7个数字换一次行；
				System.out.println(i);
			} else {
				System.out.print(i + "\t");
			}
			sum += 1;
		}
	}

	// 计算出该月有多少天；
	private int getDayOfMonth() {
		// 获得该月的天数；
		if (month == 2) {
			if (runnian(year)) {// 判断闰年
				days = 29;
			} else {
				days = 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		} else {
			days = 31;
		}
		return days;
	}

	// 计算出该月从星期几开始；
	public int getdayOfWeek() {
		for (int i = 1900; i < year; i++) { // 计算从1900年到该年之前的总天数
			if (runnian(i)) { // 判断闰年
				sum += 366;
			} else {
				sum += 365;
			}
		}
		for (int j = 1; j < month; j++) { // 计算从1900年该月之前的总天数
			if (j == 2) {
				if (runnian(year)) { // 判断闰年
					sum += 29;
				} else {
					sum += 28;
				}
			} else if (j == 4 || j == 6 || j == 9 || j == 11) {
				sum += 30;
			} else {
				sum += 31;
			}
		}
		return sum % 7; // 计算出星期几;例：若为0为星期日，若为1则是星期一;
	}

	// 判断闰年；
	public boolean runnian(int year) {
		return (year % 4 == 0 && year % 100 == 0 || year % 400 == 0);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入年份：");
		int year = scanner.nextInt();
		System.out.println("请输入月份：");
		int month = scanner.nextInt();

		CalendarPad cp = new CalendarPad(year, month);
		cp.show();

	}

}