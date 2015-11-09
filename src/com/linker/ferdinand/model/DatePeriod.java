package com.linker.ferdinand.model;

/**
 * 日期起止时间
 * 
 * @author Ferdinand
 *
 */
public class DatePeriod {
	private String dayBegin;
	private String dayEnd;

	public DatePeriod() {
		super();
	}

	public DatePeriod(String dayBegin, String dayEnd) {
		super();
		this.dayBegin = dayBegin;
		this.dayEnd = dayEnd;
	}

	public String getDayBegin() {
		return dayBegin;
	}

	public void setDayBegin(String dayBegin) {
		this.dayBegin = dayBegin;
	}

	public String getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}

}
