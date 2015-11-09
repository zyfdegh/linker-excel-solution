package com.linker.ferdinand.excel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.linker.ferdinand.model.AttendanceRecord;
import com.linker.ferdinand.model.DatePeriod;
import com.linker.ferdinand.model.DayRecord;
import com.linker.ferdinand.model.DayRecordPlus;
import com.linker.ferdinand.model.HourMinute;
import com.linker.ferdinand.model.RowCol;
import com.linker.ferdinand.model.Staff;
import com.linker.ferdinand.model.StaffSignRecord;
import com.linker.ferdinand.model.TableAttendance;
import com.linker.ferdinand.model.TableSummary;
import com.linker.ferdinand.model.TimePeriod;
import com.linker.ferdinand.model.WeekDate;
import com.linker.ferdinand.other.Config;
import com.linker.ferdinand.tools.DateTimeUtil;

/**
 * Convert sheet to model(Table)
 * 
 * @author Ferdinand
 *
 */
public class SheetTableConverter {
	/**
	 * 转换考勤数据表到TableAttendance
	 * 
	 * @param attendanceSheet
	 * @return
	 */
	public TableAttendance sheetToTableAttendance(HSSFSheet attendanceSheet) {
		TableAttendance tableAttendance = new TableAttendance();
		// 设置标题(row0 row1)
		RowCol rc0 = Config.RC_SHEET_KQJL_TITLE;
		HSSFRow row_0 = attendanceSheet.getRow(rc0.getRow());
		HSSFCell cell_title = row_0.getCell(rc0.getCol());
		String title = cell_title.getStringCellValue();
		tableAttendance.setTitle(title);
		// 设置考勤开始日、结束日
		RowCol rc1 = Config.RC_SHEET_KQJL_PERIOD;
		HSSFRow row_2 = attendanceSheet.getRow(rc1.getRow());
		HSSFCell cell_period = row_2.getCell(rc1.getCol());
		String period = cell_period.getStringCellValue();
		DatePeriod datePeriod = DateTimeUtil.dateSpliter(period);
		tableAttendance.setDatePeriod(datePeriod);
		// 读取制表日期
		RowCol rc2 = Config.RC_SHEET_KQJL_CREATION;
		// HSSFRow row_2 = attendanceSheet.getRow(rc1.getRow());
		HSSFCell cell_creationdate = row_2.getCell(rc2.getCol());
		String creationDate = cell_creationdate.getStringCellValue();
		tableAttendance.setCreationDay(creationDate);

		// 获取表格行数
		int cowNum = attendanceSheet.getLastRowNum();// 从0开始
		// 计算有多少员工
		int staffCount = (cowNum + 1 - 4) / 2;// 4为表头行数
		// 职工考勤记录列表
		ArrayList<AttendanceRecord> attendanceRecordList = new ArrayList<AttendanceRecord>();
		int base_row = 4;
		for (int i = 0; i < staffCount; i++) {
			// 读取第i个人的月签到记录
			// AttendanceRecord[] attendanceRecords = new AttendanceRecord[1];
			AttendanceRecord attendanceRecord = new AttendanceRecord();
			HSSFRow row = attendanceSheet.getRow(base_row);
			if (row != null) {
				// 获取员工信息
				HSSFCell cell_id = row.getCell(2);
				HSSFCell cell_name = row.getCell(10);
				HSSFCell cell_dept = row.getCell(20);
				Staff staff = new Staff();
				// staff.setId((int) cell_id.getNumericCellValue());
				staff.setId(Integer.valueOf(cell_id.getStringCellValue()));
				staff.setName(cell_name.getStringCellValue());
				staff.setDept(cell_dept.getStringCellValue());
				attendanceRecord.setStaff(staff);

				// 获取签到记录
				// DayRecord[] dayRecords = new DayRecord[31];
				List<DayRecord> dayRecordList = new ArrayList<DayRecord>();
				HSSFRow row_record = attendanceSheet.getRow(base_row + 1);
				// for (int j = 0; j < 31; j++) {
				// HSSFCell cell_record = row_record.getCell(j);
				// String str_record = cell_record.getStringCellValue();
				// // convert time string
				// TimePeriod timePeriod = DateTimeUtil
				// .timeRecognizer(str_record);
				// dayRecords[j] = new DayRecord();
				// dayRecords[j].setSignInTime(timePeriod.getInTime());
				// dayRecords[j].setSignOutTime(timePeriod.getOutTime());
				// }
				for (int j = 0; j < 31; j++) {
					HSSFCell cell_record = row_record.getCell(j);
					String str_record = cell_record.getStringCellValue();
					// convert time string
					TimePeriod timePeriod = DateTimeUtil
							.timeRecognizer(str_record);
					DayRecord dayRecord = new DayRecord();
					dayRecord.setSignInTime(timePeriod.getInTime());
					dayRecord.setSignOutTime(timePeriod.getOutTime());
					dayRecordList.add(dayRecord);
				}
				attendanceRecord.setDayRecordList(dayRecordList);
			}
			// attendanceRecords[1] = attendanceRecord;
			attendanceRecordList.add(attendanceRecord);

			base_row += 2;// 行号下移2行
		}
		tableAttendance.setAttendanceRecordList(attendanceRecordList);

		return tableAttendance;
	}

	/**
	 * 将考勤汇总表转换为Sheet
	 * 
	 * @param tableSummary
	 * @return
	 */
	public HSSFSheet tableSummaryToSheet(HSSFWorkbook workbook,
			TableSummary tableSummary) {
		HSSFSheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			sheet = workbook.createSheet();
		}
		RowCol rc_creation = Config.RC_SHEET_KQHZ_CREATION;
		HSSFRow row_creation = sheet.createRow(rc_creation.getRow());// 制表日期 那一行
		HSSFCell cell_creation = row_creation.createCell(rc_creation.getCol());
		// if (cell_creation == null) {
		// cell_creation = row_creation.createCell(rc_creation.getCol());
		// }
		// HSSFCellStyle style = cell_creation.getCellStyle();
		// cell_creation.setCellStyle(style);

		// 取数据 创建时间
		// String creationDate = tableSummary.getCreationDay();
		// String ymd[] = creationDate.split("-");
		// 获取当前系统时间作为创建时间
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String cell_creation_str = "制表日期：" + year + "年" + month + "月" + day
				+ "日";
		cell_creation.setCellValue(cell_creation_str);
		// 合并单元格

		// 写入周几、几月几号
		List<WeekDate> weekDateList = tableSummary.getWeekDateList();
		RowCol rc_week = Config.RC_SHEET_KQHZ_WEEK;
		HSSFRow row_week = sheet.createRow(rc_week.getRow());// 周几 那一行
		HSSFRow row_date = sheet.createRow(rc_week.getRow() + 1);// 几月几号 那一行
		for (int i = 0; i < weekDateList.size(); i++) {
			// 周几
			HSSFCell cell_week = row_week.createCell(rc_week.getCol() + i);
			int weekn = weekDateList.get(i).getWeekn();
			String cell_week_str = DateTimeUtil.weeknToString(weekn);
			cell_week.setCellValue(cell_week_str);
			// 几月几日
			HSSFCell cell_date = row_date.createCell(rc_week.getCol() + i);
			String cell_date_str = weekDateList.get(i).getDate().toString();
			cell_date.setCellValue(cell_date_str);
		}

		// 写入考勤列表
		RowCol rc_record = Config.RC_SHEET_KQHZ_RECORD;
		HSSFRow row_signIn = sheet.createRow(rc_record.getRow());
		HSSFRow row_signOut = sheet.createRow(rc_record.getRow() + 1);
		HSSFRow row_workTime = sheet.createRow(rc_record.getRow() + 2);
		List<StaffSignRecord> staffSignRecordList = tableSummary
				.getStaffSignRecordList();
		for (int i = 0; i < staffSignRecordList.size(); i++) {
			// 下移行号
			row_signIn = sheet.createRow(rc_record.getRow() + i * 3);
			row_signOut = sheet.createRow(rc_record.getRow() + 1 + i * 3);
			row_workTime = sheet.createRow(rc_record.getRow() + 2 + i * 3);
			// 写入员工信息
			HSSFCell cell_staff = row_signIn.createCell(rc_record.getCol());
			Staff staff = staffSignRecordList.get(i).getStaff();
			String cell_staff_str = staff.getId() + "." + staff.getName();
			cell_staff.setCellValue(cell_staff_str);
			// 三个固定文本
			HSSFCell cell_text_0 = row_signIn
					.createCell(rc_record.getCol() + 1);
			cell_text_0.setCellValue("签到");
			HSSFCell cell_text_1 = row_signOut
					.createCell(rc_record.getCol() + 1);
			cell_text_1.setCellValue("签退");
			HSSFCell cell_text_2 = row_workTime
					.createCell(rc_record.getCol() + 1);
			cell_text_2.setCellValue("出勤");

			// 取出某个员工月签到记录

			List<DayRecordPlus> dayRecordPlusList = staffSignRecordList.get(i)
					.getDayRecordPlusList();
			for (int j = 0; j < dayRecordPlusList.size(); j++) {
				// *********如果不需要标记，则使用代码块2

				// ****************************************
				// * 代码块1 此处写入的是签到签退状态信息（勾号、标记）
				// * **************************************
				String cell_signIn_str = "";
				String cell_signOut_str = "";
				String cell_workTime_str = "";

				DayRecordPlus dayRecord = dayRecordPlusList.get(j);
				HourMinute hmSignIn = DateTimeUtil
						.inOutTimeStr2HourMinute(dayRecord.getSignInTime());
				HourMinute hmSignOut = DateTimeUtil
						.inOutTimeStr2HourMinute(dayRecord.getSignOutTime());

				if (hmSignIn != null) {
					if (hmSignIn.isLaterThan(Config.FLAG_TIME_SIGN_IN)) {
						// attendanceStatus.setSignInLate(true);
						cell_signIn_str = "迟到";
					} else {
						cell_signIn_str = "√";
					}
				}
				if (hmSignOut != null) {
					if (hmSignOut.isEarlierThan(Config.FLAG_TIME_SIGN_OUT)) {
						// attendanceStatus.setSignOutEarly(true);
						cell_signOut_str = "早退";
					} else {
						cell_signOut_str = "√";
					}
					if (hmSignOut.isLaterThan(Config.FLAG_TIME_OVERWORK)) {
						// attendanceStatus.setWorkAtNight(true);
						// // 计算加班时长
						// HourMinute hm =
						// DateTimeUtil.calcHourMinuteGap(signOutTime,
						// Config.FLAG_TIME_OVERWORK);
						// attendanceStatus.setOverWorkTime(signInTime);
						cell_workTime_str = "加班";
						cell_signOut_str = dayRecord.getSignOutTime();
					} else {
						// cell_workTime_str = "";
					}
				}

				HSSFCell cell_signIn = row_signIn.createCell(rc_record.getCol()
						+ 2 + j);
				cell_signIn.setCellValue(cell_signIn_str);
				HSSFCell cell_signOut = row_signOut.createCell(rc_record
						.getCol() + 2 + j);
				cell_signOut.setCellValue(cell_signOut_str);
				HSSFCell cell_workTime = row_workTime.createCell(rc_record
						.getCol() + 2 + j);
				cell_workTime.setCellValue(cell_workTime_str);

				// ****************************************
				// * 代码块2 此处写入的是签到签退时间
				// * **************************************
				// HSSFCell cell_signIn =
				// row_signIn.createCell(rc_record.getCol()
				// + 2 + j);
				// String cell_signIn_str = dayRecordPlusList.get(j)
				// .getSignInTime();
				// cell_signIn.setCellValue(cell_signIn_str);
				// HSSFCell cell_signOut = row_signOut.createCell(rc_record
				// .getCol() + 2 + j);
				// String cell_signOut_str = dayRecordPlusList.get(j)
				// .getSignOutTime();
				// cell_signOut.setCellValue(cell_signOut_str);
				// HSSFCell cell_workTime = row_workTime.createCell(rc_record
				// .getCol() + 2 + j);
				// String cell_workTime_str = dayRecordPlusList.get(j)
				// .getWorkTime();
				// cell_workTime.setCellValue(cell_workTime_str);
			}

		}
		return sheet;
	}
}
