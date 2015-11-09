package com.linker.ferdinand.model;

import java.util.List;

public class StaffSignRecord {
	private Staff staff;
	private List<DayRecordPlus> dayRecordPlusList;

	public StaffSignRecord() {
		super();
	}

	public StaffSignRecord(Staff staff, List<DayRecordPlus> dayRecordPlusList) {
		super();
		this.staff = staff;
		this.dayRecordPlusList = dayRecordPlusList;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<DayRecordPlus> getDayRecordPlusList() {
		return dayRecordPlusList;
	}

	public void setDayRecordPlusList(List<DayRecordPlus> dayRecordPlusList) {
		this.dayRecordPlusList = dayRecordPlusList;
	}

}
