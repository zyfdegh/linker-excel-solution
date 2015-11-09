package com.linker.ferdinand.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.linker.ferdinand.excel.ExcelReader;
import com.linker.ferdinand.excel.ExcelWriter;
import com.linker.ferdinand.excel.TableTableConverter;
import com.linker.ferdinand.model.AttendanceRecord;
import com.linker.ferdinand.model.DayRecord;
import com.linker.ferdinand.model.TableAttendance;
import com.linker.ferdinand.model.TableSummary;
import com.linker.ferdinand.model.WeekDate;
import com.linker.ferdinand.model.YMDate;
import com.linker.ferdinand.other.Config;
import com.linker.ferdinand.other.Constants;
import com.linker.ferdinand.tools.DateTimeUtil;
import com.linker.ferdinand.tools.FileUtil;
import com.linker.ferdinand.tools.PathUtil;

public class ExcelTest {
	private static final String DEFAULT_EXCEL_PATH = "F://1_标准报表.xls";

	public static void main(String[] args) {
		String excelPath = DEFAULT_EXCEL_PATH;
		// 设置Excel文件路径
		if (args[0] != null) {
			excelPath = args[0];
		}
		ExcelReader excelReader = new ExcelReader();
		HSSFWorkbook workbook = excelReader.readExcel(excelPath);

		if (workbook != null) {
			// 读取考勤记录表
			TableAttendance tableAttendance = excelReader.readTableAttendance(
					workbook, Config.SHEET_INDEX_KAOQINJILU);
			System.out.println(tableAttendance.getTitle());
			System.out.println(tableAttendance.getDatePeriod().getDayBegin());
			System.out.println(tableAttendance.getDatePeriod().getDayEnd());
			System.out.println(tableAttendance.getCreationDay());
			for (int i = 0; i < tableAttendance.getAttendanceRecordList()
					.size(); i++) {
				AttendanceRecord attendanceRecord = tableAttendance
						.getAttendanceRecordList().get(i);
				System.out.println(attendanceRecord.getStaff().getName());
				// for (int j = 0; j < 31; j++) {
				// DayRecord dayRecord = attendanceRecord.getDayRecords()[j];
				// System.out.println(dayRecord.getSignInTime() + ","
				// + dayRecord.getSignOutTime());
				// }
				for (int j = 0; j < attendanceRecord.getDayRecordList().size(); j++) {
					DayRecord dayRecord = attendanceRecord.getDayRecordList()
							.get(j);
					System.out.println(dayRecord.getSignInTime() + ","
							+ dayRecord.getSignOutTime());
				}
			}
			// TODO 由考勤记录表分析年份月份，计算该月有多少天，1号为星期几
			String recordBeginDate = tableAttendance.getDatePeriod()
					.getDayBegin();
			YMDate ymDate = DateTimeUtil.yearMonthAnalyzer(recordBeginDate);
			List<WeekDate> weekDateList = DateTimeUtil.generateDateList(ymDate);
			// System.out.println("Week:"+weekDateList.get(0).getWeekn());
			for (int i = 0; i < weekDateList.size(); i++) {
				// System.out.println(weekDateList.get(i).toString());
				System.out.println(DateTimeUtil.weeknToString(weekDateList.get(
						i).getWeekn()));
				System.out.println(weekDateList.get(i).getDate().toString());
			}
			// TODO 读入模版表，获取三种说明的颜色，填充基本数据（制表时间）
			// 获取文件保存路径
			System.out
					.println(PathUtil.generateFileSavePath(excelPath, ymDate));
			String savePath = PathUtil.generateFileSavePath(excelPath, ymDate);
			// TODO 批量填入工号姓名、出勤时间，计算签到时间差值，并在出勤列打勾√并标准颜色
			TableSummary tableSummary = new TableTableConverter()
					.tableAttendancy2TableSummary(tableAttendance);

			// 输出文件
			String relativelyPath = System.getProperty("user.dir");
			File file = new File(relativelyPath + "\\file\\OutModel.xls");
			if (file.exists()) {
				System.out.println(file.getAbsolutePath());
				// 拷贝文件
				File dstFile = new File(savePath);
				int copyResult = new FileUtil().copyFile(file, dstFile);
				if (copyResult == Constants.COPY_RESULT_SUCCESS) {
					System.out.println("拷贝模版成功");
					// 写入数据
					ExcelWriter excelWriter = new ExcelWriter(
							dstFile.getAbsolutePath());
					HSSFWorkbook workbookOutput = excelReader
							.readWorkbook(file);
					try {
						excelWriter.writeSheetSummaryToExcel(workbookOutput,
								tableSummary);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("写入Excel时失败");
					}
					System.out.println("End");
				} else if (copyResult == Constants.COPY_RESULT_EXIST) {
					System.out.println("拷贝模版文件时出错，文件已存在，不会覆盖，请先删除文件："
							+ dstFile.getAbsolutePath());
				} else {
					System.out.println("拷贝模版文件时出错，其他原因");
				}
			} else {
				System.out.println("找不到模版文件");
			}

		}
	}
}
