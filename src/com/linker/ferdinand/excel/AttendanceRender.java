package com.linker.ferdinand.excel;

import java.util.ArrayList;
import java.util.List;

import com.linker.ferdinand.model.AttendanceStatus;
import com.linker.ferdinand.model.DayRecordPlus;
import com.linker.ferdinand.model.HourMinute;
import com.linker.ferdinand.model.StaffSignRecord;
import com.linker.ferdinand.other.Config;

/**
 * 用于转换显示效果,迟到早退加班时间检测工具
 * 
 * @author Ferdinand
 *
 */
public class AttendanceRender {

	/**
	 * 用于判断上班状态，是否迟到早退，是否加班
	 * 
	 * @param signInTime
	 *            签到时间
	 * @param signOutTime
	 *            签退时间
	 * @return
	 */
	public static AttendanceStatus analyzeAttendanceStatus(
			HourMinute signInTime, HourMinute signOutTime) {
		AttendanceStatus attendanceStatus = new AttendanceStatus();
		if (signInTime.isLaterThan(Config.FLAG_TIME_SIGN_IN)) {
			attendanceStatus.setSignInLate(true);
		}
		if (signOutTime.isEarlierThan(Config.FLAG_TIME_SIGN_OUT)) {
			attendanceStatus.setSignOutEarly(true);
		}
		if (signOutTime.isLaterThan(Config.FLAG_TIME_OVERWORK)) {
			attendanceStatus.setWorkAtNight(true);
			// // 计算加班时长
			// HourMinute hm = DateTimeUtil.calcHourMinuteGap(signOutTime,
			// Config.FLAG_TIME_OVERWORK);
			attendanceStatus.setOverWorkTime(signInTime);
		}
		return attendanceStatus;
	}

	private static List<StaffSignRecord> renderStaffSignList(
			List<StaffSignRecord> staffSignRecordList) {
		List<StaffSignRecord> signRecordListResult = new ArrayList<StaffSignRecord>();
		for (int i = 0; i < staffSignRecordList.size(); i++) {
			List<DayRecordPlus> dayRecordPlusList = staffSignRecordList.get(i)
					.getDayRecordPlusList();
			// 遍历每个员工每天的签到记录，得出AttendanceStatus
			for (int j = 0; j < dayRecordPlusList.size(); j++) {
				// DayRecordPlus dayRecord=dayRecordPlusList.get(j);
				// HourMinute dayRecord.getSignInTime();
			}
		}
		return signRecordListResult;
	}
}
