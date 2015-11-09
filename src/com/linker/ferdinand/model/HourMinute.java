package com.linker.ferdinand.model;

/**
 * 存储时分
 * 
 * @author Ferdinand
 *
 */
public class HourMinute {
	private boolean isNegative;// 用于标记负数，如-00：45,如果是-01：45，该值可以选择性设置
	private int hour;
	private int minute;

	public HourMinute() {
		super();
	}

	public HourMinute(boolean isNegative, int hour, int minute) {
		super();
		this.isNegative = isNegative;
		this.hour = hour;
		this.minute = minute;
	}

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * 用于将时分转换为11：25这样的形式输出，24h制
	 */
	public String toString() {
		String h = hour + "";
		String m = minute + "";
		if (isNegative) {
			if (hour < 10) {
				h = "-0" + hour;
			}
			if (minute < 10) {
				m = "0" + minute;
			}
		} else {
			if (hour < 10) {
				h = "0" + hour;
			}
			if (minute < 10) {
				m = "0" + minute;
			}
		}
		return h + ":" + m;
	}

	/**
	 * 判断时间是否迟于参数中给定的时间
	 * 
	 * @param hm
	 *            时分
	 * @return true 表示迟于。相同时返回false
	 */
	public boolean isLaterThan(HourMinute hm) {
		int h = hm.getHour();
		int m = hm.getMinute();
		if (this.hour > h) {
			return true;
		} else if (this.hour == h) {
			if (this.minute > m) {
				return true;
			} else if (this.minute == m) {
				return false;// 两个时间重复算false
			} else {
				return false;
			}
		} else {
			// <
			return false;
		}
	}

	/**
	 * 判断时间是否迟于参数中给定的时间
	 * 
	 * @param hm
	 *            时分
	 * @return true 表示早于。相同时返回false
	 */
	public boolean isEarlierThan(HourMinute hm) {
		int h = hm.getHour();
		int m = hm.getMinute();
		if (this.hour < h) {
			return true;
		} else if (this.hour == h) {
			if (this.minute < m) {
				return true;
			} else if (this.minute == m) {
				return false;// 两个时间重复算false
			} else {
				return false;
			}
		} else {
			// <
			return false;
		}
	}

	/**
	 * 判断时间是否迟于参数中给定的时间，相同时返回false
	 * 
	 * @param hm
	 * @return
	 */
	public boolean isEqualWith(HourMinute hm) {
		int h = hm.getHour();
		int m = hm.getMinute();
		if (this.hour == h && this.minute == m) {
			return true;
		} else {
			return false;
		}
	}
}
