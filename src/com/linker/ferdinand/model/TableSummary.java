package com.linker.ferdinand.model;

import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;

public class TableSummary {
	private String title;
	// 几个颜色
	private HSSFColor colorJiaban;
	private HSSFColor colorWaichu;
	private HSSFColor colorShijia;
	// 勾号
	private String mark;
	private String[] signInOutTimeStrArr;
	// 制表日期
	private String creationDay;
	private List<WeekDate> weekDateList;
	private List<StaffSignRecord> staffSignRecordList;

	public TableSummary(String title, HSSFColor colorJiaban,
			HSSFColor colorWaichu, HSSFColor colorShijia, String mark,
			String[] signInOutTimeStrArr, String creationDay,
			List<WeekDate> weekDateList,
			List<StaffSignRecord> staffSignRecordList) {
		super();
		this.title = title;
		this.colorJiaban = colorJiaban;
		this.colorWaichu = colorWaichu;
		this.colorShijia = colorShijia;
		this.mark = mark;
		this.signInOutTimeStrArr = signInOutTimeStrArr;
		this.creationDay = creationDay;
		this.weekDateList = weekDateList;
		this.staffSignRecordList = staffSignRecordList;
	}

	public TableSummary() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HSSFColor getColorJiaban() {
		return colorJiaban;
	}

	public void setColorJiaban(HSSFColor colorJiaban) {
		this.colorJiaban = colorJiaban;
	}

	public HSSFColor getColorWaichu() {
		return colorWaichu;
	}

	public void setColorWaichu(HSSFColor colorWaichu) {
		this.colorWaichu = colorWaichu;
	}

	public HSSFColor getColorShijia() {
		return colorShijia;
	}

	public void setColorShijia(HSSFColor colorShijia) {
		this.colorShijia = colorShijia;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String[] getSignInOutTimeStrArr() {
		return signInOutTimeStrArr;
	}

	public void setSignInOutTimeStrArr(String[] signInOutTimeStrArr) {
		this.signInOutTimeStrArr = signInOutTimeStrArr;
	}

	public String getCreationDay() {
		return creationDay;
	}

	public void setCreationDay(String creationDay) {
		this.creationDay = creationDay;
	}

	public List<WeekDate> getWeekDateList() {
		return weekDateList;
	}

	public void setWeekDateList(List<WeekDate> weekDateList) {
		this.weekDateList = weekDateList;
	}

	public List<StaffSignRecord> getStaffSignRecordList() {
		return staffSignRecordList;
	}

	public void setStaffSignRecordList(List<StaffSignRecord> staffSignRecordList) {
		this.staffSignRecordList = staffSignRecordList;
	}

}
