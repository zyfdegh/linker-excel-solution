package com.linker.ferdinand.model;

/**
 * 存储年份月份
 * 
 * @author Ferdinand
 *
 */
public class YMDate {
	private int year;
	private int month;

	public YMDate() {
		super();
	}

	public YMDate(int year, int month) {
		super();
		this.year = year;
		this.month = month;
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

	public String toString() {
		return year + "年" + month + "月";
	}
}
