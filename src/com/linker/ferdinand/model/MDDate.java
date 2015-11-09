package com.linker.ferdinand.model;

public class MDDate {
	private int month;
	private int day;

	public MDDate(int month, int day) {
		super();
		this.month = month;
		this.day = day;
	}

	public MDDate() {
		super();
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

	public String toString() {
		return month + "月" + day + "日";
	}
}
