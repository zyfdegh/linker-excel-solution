package com.linker.ferdinand.other;

import com.linker.ferdinand.model.HourMinute;
import com.linker.ferdinand.model.RowCol;

public class Config {
	// 表下标顺序
	public static final int SHEET_INDEX_PAIBANXINXI = 0;// 排班信息
	public static final int SHEET_INDEX_KAOQINHUIZONG = 1;// 考勤汇总
	public static final int SHEET_INDEX_KAOQINJILU = 2;// 考勤记录
	public static final int SHEET_INDEX_YICHANGTONGJI = 3;// 异常统计

	// 表中数据位置
	// 源表
	public static final RowCol RC_SHEET_KQJL_TITLE = new RowCol(0, 0);// 考勤记录表中第1行第1列为标题
	public static final RowCol RC_SHEET_KQJL_PERIOD = new RowCol(2, 2);// 考勤记录表中第3行第3列为起止日期
	public static final RowCol RC_SHEET_KQJL_CREATION = new RowCol(2, 11);// 考勤记录表中第3行第12列为创建时间

	// 目的表
	public static final RowCol RC_SHEET_KQHZ_CREATION = new RowCol(1, 2);// 考勤汇总表第2行第3列为创建时间
	public static final RowCol RC_SHEET_KQHZ_WEEK = new RowCol(2, 2);// 考勤汇总表第3行第3列为星期开始
	public static final RowCol RC_SHEET_KQHZ_RECORD = new RowCol(4, 0);// 考勤汇总表第5行第1列为考勤记录开始

	/**
	 * 用于标记迟到早退时刻，以及加班时刻
	 */
	public static final HourMinute FLAG_TIME_SIGN_IN = new HourMinute(false, 10,
			00);// 迟到标记时间10：00
	public static final HourMinute FLAG_TIME_SIGN_OUT = new HourMinute(false,
			17, 30);// 早退标记时间17：30
	public static final HourMinute FLAG_TIME_OVERWORK = new HourMinute(false,
			20, 00);// 加班标记时间20：30
}
