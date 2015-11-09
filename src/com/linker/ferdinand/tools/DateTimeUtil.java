package com.linker.ferdinand.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.linker.ferdinand.model.DatePeriod;
import com.linker.ferdinand.model.HourMinute;
import com.linker.ferdinand.model.MDDate;
import com.linker.ferdinand.model.TimePeriod;
import com.linker.ferdinand.model.WeekDate;
import com.linker.ferdinand.model.YMDate;

/**
 * 时间日期转换工具
 * 
 * @author Ferdinand
 *
 */
public class DateTimeUtil {
	/**
	 * 用于把2015-07-01 ~ 2015-07-30 这样的日期分割为包含起止日期的对象DatePeriod
	 * 
	 * @param datePeriod
	 * @return
	 */
	public static DatePeriod dateSpliter(String datePeriod) {
		String[] strs = datePeriod.split(" ~ ");
		return new DatePeriod(strs[0], strs[1]);
	}

	/**
	 * 用于将签到时间记录转换为TimePeriod 签到时间记录格式如下 09:3009:3918:57
	 * 或者09:3619:01或者09:47或者08:4819:2919:3920:57
	 * 
	 * 
	 * @param times
	 * @return
	 */
	public static TimePeriod timeRecognizer(String times) {
		TimePeriod timePeriod = new TimePeriod();
		// timePeriod.setInTime("");
		// timePeriod.setOutTime("");
		if (times != null) {
			int length = times.length();
			if (length % 5 == 0) {
				if (length == 5) {
					timePeriod.setInTime(times);
				} else if (length == 10) {
					timePeriod.setInTime(times.substring(0, 5));
					timePeriod.setOutTime(times.substring(5, 10));
				} else if (length >= 15) {
					timePeriod.setInTime(times.substring(0, 5));
					// get last 5 chars at the end
					timePeriod.setOutTime(times.substring(length - 5, length));// 如(10，15)
				}
			} else {
				System.out
						.println("DateTimeUtil.timeSpliter==>签到时间转换出错，时间长度有误");
			}
		}
		return timePeriod;
	}

	/**
	 * 给定年份月份，生成该月星期、日期列表
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<WeekDate> generateDateList(YMDate ymDate) {
		int year = ymDate.getYear();
		int month = ymDate.getMonth();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		List<WeekDate> weekDateList = new ArrayList<WeekDate>();
		// 获取该月中每一天是几月几号 星期几
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			WeekDate weekDate = new WeekDate();
			cal.set(Calendar.DAY_OF_MONTH, i);
			weekDate.setWeekn(cal.get(Calendar.DAY_OF_WEEK));

			// MDDate mdDate=new MDDate();
			// mdDate.setDay(i);
			// mdDate.setMonth(month);

			weekDate.setDate(new MDDate(month, i));
			weekDateList.add(weekDate);
		}
		return weekDateList;
	}

	/**
	 * 将2015-07-30这样的日期转换为年月数据
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static YMDate yearMonthAnalyzer(String yearMonth) {
		YMDate ymDate = new YMDate();
		String year = yearMonth.split("-")[0];
		String month = yearMonth.split("-")[1];
		ymDate.setYear(Integer.parseInt(year));
		ymDate.setMonth(Integer.parseInt(month));
		return ymDate;
	}

	/**
	 * 将数字格式的星期几转换为字符串，如将1转换为星期日
	 * 
	 * @param weekn
	 * @return
	 */
	public static String weeknToString(int weekn) {
		String result = "";
		switch (weekn) {
		case Calendar.SUNDAY:
			result = "周日";
			break;
		case Calendar.MONDAY:
			result = "周一";
			break;
		case Calendar.TUESDAY:
			result = "周二";
			break;
		case Calendar.WEDNESDAY:
			result = "周三";
			break;
		case Calendar.THURSDAY:
			result = "周四";
			break;
		case Calendar.FRIDAY:
			result = "周五";
			break;
		case Calendar.SATURDAY:
			result = "周六";
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 计算上下班时间差值，即计算时长
	 * 
	 * @param period
	 *            包含上下班时间
	 * @return
	 */
	public static HourMinute calcTimePeriodGap(TimePeriod period) {
		HourMinute[] hourMinuteArr = timePeriod2HourMinuteArray(period);
		// 计算两个时分之间的差值
		return calcHourMinuteGap(hourMinuteArr[0], hourMinuteArr[1]);
	}

	/**
	 * 计算两个HourMinute之间的时间差值,用hm1减去hm2
	 * 
	 * @param hm1
	 *            时分1
	 * @param hm2
	 *            时分2
	 * @return 返回hm1-hm2之间的时间差，并存储在HourMinute之中，可以是负值
	 */
	public static HourMinute calcHourMinuteGap(HourMinute hm1, HourMinute hm2) {
		HourMinute hm = null;
		if (hm1 != null && hm2 != null) {
			hm = new HourMinute();
			int h1 = hm1.getHour();
			int m1 = hm1.getMinute();
			int h2 = hm2.getHour();
			int m2 = hm2.getMinute();

			int h = h2 - h1;
			int m = m2 - m1;
			if (h2 >= h1) {
				if (m2 >= m1) {
					// 如09:25 18:47 得09:22
					hm.setHour(h);
					hm.setMinute(m);
				} else {
					// 如09:36 18:20 得08:44
					hm.setHour(h - 1);
					hm.setMinute(m + 60);
				}
			} else {
				if (m2 >= m1) {
					// 如10:25 09:40 得-00:45
					// TODO 这里如果是-00:45则得到结果为00：45
					hm.setHour(h + 1);// 得0，应该是-00
					hm.setNegative(true);
					hm.setMinute(60 - m);
				} else {

					// 如10:45 09:20 得-01:25
					hm.setHour(h);
					hm.setMinute(-m);
					// hm.setNegative(true);
				}
			}
		} else {
			System.out.println("输入项存在null值，无法计算时间差值");
		}
		return hm;
	}

	/**
	 * 将上下班时间分割为上班时间、下班时间，并转换为HourMinute对象，存储到数组中，数组长度为2
	 * 
	 * @param period
	 * @return
	 */
	public static HourMinute[] timePeriod2HourMinuteArray(TimePeriod period) {
		HourMinute[] hourMinuteArr = new HourMinute[2];
		hourMinuteArr[0] = inOutTimeStr2HourMinute(period.getInTime());
		hourMinuteArr[1] = inOutTimeStr2HourMinute(period.getOutTime());
		return hourMinuteArr;
	}

	/**
	 * 如将09：15转换为HourMinute对象
	 * 
	 * @param inOrOutTimeStr
	 * @return
	 */
	public static HourMinute inOutTimeStr2HourMinute(String inOrOutTimeStr) {
		if (inOrOutTimeStr != null) {
			String hour = inOrOutTimeStr.split(":")[0];
			String min = inOrOutTimeStr.split(":")[1];
			if (hour != null && min != null) {
				return new HourMinute(false, Integer.parseInt(hour),
						Integer.parseInt(min));
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String getTodayDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return year + "-" + month + "-" + day;
	}
}
