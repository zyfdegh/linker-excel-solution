package com.linker.ferdinand.model;

/**
 * 用于存储迟到早退加班情况
 * 
 * @author Ferdinand
 *
 */
public class AttendanceStatus {
	private boolean isSignInLate;// 迟到
	private boolean isSignOutEarly;// 早退
	private boolean isWorkAtNight;// 加班
	private HourMinute overWorkTime;// 加班到了几点

	public AttendanceStatus() {
		super();
	}

	public AttendanceStatus(boolean isSignInLate, boolean isSignOutEarly,
			boolean isWorkAtNight, HourMinute overWorkTime) {
		super();
		this.isSignInLate = isSignInLate;
		this.isSignOutEarly = isSignOutEarly;
		this.isWorkAtNight = isWorkAtNight;
		this.overWorkTime = overWorkTime;
	}

	public boolean isSignInLate() {
		return isSignInLate;
	}

	public void setSignInLate(boolean isSignInLate) {
		this.isSignInLate = isSignInLate;
	}

	public boolean isSignOutEarly() {
		return isSignOutEarly;
	}

	public void setSignOutEarly(boolean isSignOutEarly) {
		this.isSignOutEarly = isSignOutEarly;
	}

	public boolean isWorkAtNight() {
		return isWorkAtNight;
	}

	public void setWorkAtNight(boolean isWorkAtNight) {
		this.isWorkAtNight = isWorkAtNight;
	}

	public HourMinute getOverWorkTime() {
		return overWorkTime;
	}

	public void setOverWorkTime(HourMinute overWorkTime) {
		this.overWorkTime = overWorkTime;
	}

}
