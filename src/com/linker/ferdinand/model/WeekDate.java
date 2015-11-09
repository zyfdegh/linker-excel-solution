package com.linker.ferdinand.model;

import com.linker.ferdinand.tools.DateTimeUtil;

/**
 * 存储某天星期几，几月几号
 * 
 * @author Ferdinand
 *
 */
public class WeekDate {
	private int weekn;
	private MDDate date;

	public WeekDate() {
		super();
	}

	public WeekDate(int weekn, MDDate date) {
		super();
		this.weekn = weekn;
		this.date = date;
	}

	public int getWeekn() {
		return weekn;
	}

	public void setWeekn(int weekn) {
		this.weekn = weekn;
	}

	public MDDate getDate() {
		return date;
	}

	public void setDate(MDDate date) {
		this.date = date;
	}

	public String toString() {
		return date.toString() + " " + DateTimeUtil.weeknToString(weekn);
	}
}
