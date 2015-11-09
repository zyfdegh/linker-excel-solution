package com.linker.ferdinand.excel;

import java.util.ArrayList;
import java.util.List;

import com.linker.ferdinand.model.AttendanceRecord;
import com.linker.ferdinand.model.DayRecord;
import com.linker.ferdinand.model.DayRecordPlus;
import com.linker.ferdinand.model.HourMinute;
import com.linker.ferdinand.model.StaffSignRecord;
import com.linker.ferdinand.model.TableAttendance;
import com.linker.ferdinand.model.TableSummary;
import com.linker.ferdinand.model.WeekDate;
import com.linker.ferdinand.model.YMDate;
import com.linker.ferdinand.tools.DateTimeUtil;

/**
 * 表与表之间的转换
 * 
 * @author Ferdinand
 *
 */
public class TableTableConverter {
	/**
	 * 将考勤记录表转换为考勤汇总表
	 * 
	 * @param tableAttendance
	 * @return
	 */
	public TableSummary tableAttendancy2TableSummary(
			TableAttendance tableAttendance) {
		TableSummary tableSummary = new TableSummary();
		// tableSummary.setCreationDay(tableAttendance.getCreationDay());
		// tableSummary.setMark("√");
		// tableSummary.setColorJiaban(null);
		// tableSummary.setColorShijia(null);
		// tableSummary.setColorWaichu(null);
		// 生成时间（周几、几月几日列表）
		String recordBeginDate = tableAttendance.getDatePeriod().getDayBegin();
		YMDate ymDate = DateTimeUtil.yearMonthAnalyzer(recordBeginDate);
		List<WeekDate> weekDateList = DateTimeUtil.generateDateList(ymDate);
		tableSummary.setWeekDateList(weekDateList);
		// 转换考勤记录列表
		ArrayList<AttendanceRecord> attendanceRecordList = tableAttendance
				.getAttendanceRecordList();
		ArrayList<StaffSignRecord> staffSignRecordList = new ArrayList<StaffSignRecord>();
		for (int i = 0; i < attendanceRecordList.size(); i++) {
			AttendanceRecord attendanceRecord = attendanceRecordList.get(i);
			StaffSignRecord staffSignRecord = attendanceRecord2StaffSignRecord(attendanceRecord);
			staffSignRecordList.add(staffSignRecord);
		}
		tableSummary.setStaffSignRecordList(staffSignRecordList);
		return tableSummary;
	}

	/**
	 * 转换
	 * 
	 * @param attendanceRecord
	 * @return
	 */
	private StaffSignRecord attendanceRecord2StaffSignRecord(
			AttendanceRecord attendanceRecord) {
		StaffSignRecord staffSignRecord = new StaffSignRecord();
		staffSignRecord.setStaff(attendanceRecord.getStaff());
		List<DayRecordPlus> dayRecordPlusList = new ArrayList<DayRecordPlus>();
		for (int i = 0; i < attendanceRecord.getDayRecordList().size(); i++) {
			DayRecord dayRecord = attendanceRecord.getDayRecordList().get(i);
			DayRecordPlus dayRecordPlus = dayRecord2DayRecordPlus(dayRecord);
			dayRecordPlusList.add(dayRecordPlus);
		}
		staffSignRecord.setDayRecordPlusList(dayRecordPlusList);
		return staffSignRecord;
	}

	/**
	 * 转换
	 * 
	 * @param dayRecord
	 * @return
	 */
	private DayRecordPlus dayRecord2DayRecordPlus(DayRecord dayRecord) {
		DayRecordPlus dayRecordPlus = new DayRecordPlus();
		String inTime = dayRecord.getSignInTime();
		String outTime = dayRecord.getSignOutTime();
		dayRecordPlus.setSignInTime(inTime);
		dayRecordPlus.setSignOutTime(outTime);
		HourMinute hm1 = DateTimeUtil.inOutTimeStr2HourMinute(inTime);
		HourMinute hm2 = DateTimeUtil.inOutTimeStr2HourMinute(outTime);
		HourMinute workTime = DateTimeUtil.calcHourMinuteGap(hm1, hm2);
		if (workTime != null) {
			dayRecordPlus.setWorkTime(workTime.toString());
		}
		return dayRecordPlus;
	}
}
