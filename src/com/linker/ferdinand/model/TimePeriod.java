package com.linker.ferdinand.model;

/**
 * 上下班时间
 * 
 * @author Ferdinand
 *
 */
public class TimePeriod {
	private String inTime;
	private String outTime;

	public TimePeriod() {
		super();
	}

	public TimePeriod(String inTime, String outTime) {
		super();
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

}
