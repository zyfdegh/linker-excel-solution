package com.linker.ferdinand.model;

/**
 * 存储某一天的上下班签到时间
 * 
 * @author Ferdinand
 *
 */
public class DayRecord {
	private String signInTime;// 上班时间
	private String signOutTime;// 下班时间

	public DayRecord() {
		super();
	}

	public DayRecord(String signInTime, String signOutTime) {
		super();
		this.signInTime = signInTime;
		this.signOutTime = signOutTime;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public String getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

}
