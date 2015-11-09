package com.linker.ferdinand.model;

import java.util.List;

/**
 * 考勤记录模型
 * 
 * @author Ferdinand
 */
public class AttendanceRecord {
	private Staff staff;
	// private DayRecord[] dayRecords;// length 31
	private List<DayRecord> dayRecordList;

	public AttendanceRecord() {
		super();
	}

	public AttendanceRecord(Staff staff, List<DayRecord> dayRecordList) {
		super();
		this.staff = staff;
		this.dayRecordList = dayRecordList;
	}

	public List<DayRecord> getDayRecordList() {
		return dayRecordList;
	}

	public void setDayRecordList(List<DayRecord> dayRecordList) {
		this.dayRecordList = dayRecordList;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
