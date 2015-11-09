package com.linker.ferdinand.tools;

import java.io.File;

import com.linker.ferdinand.model.YMDate;

public class PathUtil {
	/**
	 * 根据源文件路径生成目标文件路径
	 * 
	 * @param srcFilePath
	 * @return
	 */
	public static String generateFileSavePath(String srcFilePath, YMDate ymDate) {
		int index = srcFilePath.lastIndexOf('\\');// For Windows
		if (index < 0 || index >= srcFilePath.length()) {
			index = srcFilePath.lastIndexOf('/');// For Linux and Unix
		}
		// get file path
		String dir = srcFilePath.substring(0, index);
		String fileName = generateDefaultDestFileName(srcFilePath, ymDate);
		return dir + fileName;
	}

	private static String generateDefaultDestFileName(String srcFilePath,
			YMDate ymDate) {
		// get file suffix
		String fileSuffix = getFileSuffix(srcFilePath);
		String fileName = "南京-员工考勤数据汇总" + "_" + ymDate.getYear() + "年"
				+ ymDate.getMonth() + "月" + "." + fileSuffix; // 南京-员工考勤数据汇总
		return fileName;
	}

	public static String getFileSuffix(File file) {
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf('.') + 1);
	}

	public static String getFileSuffix(String filePath) {
		return filePath.substring(filePath.lastIndexOf('.') + 1);
	}
}
