package com.linker.ferdinand.model;

import java.util.ArrayList;

/**
 * @author Ferdinand 考勤记录表
 */
public class TableAttendance {
	private String title;// 标题
	private DatePeriod datePeriod;// 考勤开始日、结束日
	private String creationDay;// 制表时间
	// private AttendanceRecord[] attendanceRecords;// 考勤记录
	private ArrayList<AttendanceRecord> attendanceRecordList;// 考勤记录

	public DatePeriod getDatePeriod() {
		return datePeriod;
	}

	public void setDatePeriod(DatePeriod datePeriod) {
		this.datePeriod = datePeriod;
	}

	public String getCreationDay() {
		return creationDay;
	}

	public void setCreationDay(String creationDay) {
		this.creationDay = creationDay;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<AttendanceRecord> getAttendanceRecordList() {
		return attendanceRecordList;
	}

	public void setAttendanceRecordList(
			ArrayList<AttendanceRecord> attendanceRecordList) {
		this.attendanceRecordList = attendanceRecordList;
	}

}
