package com.linker.ferdinand.model;

public class DayRecordPlus extends DayRecord {
	private String workTime;

	public DayRecordPlus() {
		super();
	}

	public DayRecordPlus(String signInTime, String sighOutTime, String workTime) {
		super(signInTime, sighOutTime);
		this.workTime = workTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

}
