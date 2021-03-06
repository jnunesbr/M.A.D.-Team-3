package com.team3.basics;

public class Date {
	private int year, month, day, hour, minute;

	public Date(String dateTime) {
		String[] parts = dateTime.split(" ");
		String[] date = parts[0].split("-");
		String[] time = parts[1].split(":");

		year = Integer.parseInt(date[0]);
		month = Integer.parseInt(date[1]);
		day = Integer.parseInt(date[2]);

		hour = Integer.parseInt(time[0]);
		minute = Integer.parseInt(time[1]);
	}

	public Date() {

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getDateString() {
		return month + "/" + day + "/" + year;
	}

	public String getHourString() {
		String str;
		if (minute < 10) {
			str = hour + ":0" + minute;
		} else {
			str = hour + ":" + minute;
		}
		return str;
	}

	public String getDateTimeString() {
		String str;
		if (minute < 10) {
			str = year + "-" + month + "-" + day + " " + hour + ":0" + minute;
		} else {
			str = year + "-" + month + "-" + day + " " + hour + ":" + minute;
		}
		return str;
	}
}
