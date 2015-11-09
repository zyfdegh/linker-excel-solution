package com.linker.ferdinand.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.linker.ferdinand.model.TableAttendance;
import com.linker.ferdinand.other.Constants;
import com.linker.ferdinand.tools.PathUtil;

public class ExcelReader {

	private String filePath;

	/**
	 * 读取指定路径的文件为工作簿
	 * 
	 * @param filePath
	 * @return
	 */
	public HSSFWorkbook readExcel(String filePath) {
		// this.filePath = filePath;
		File file = openFile(filePath);
		if (file != null) {
			this.filePath = file.getAbsolutePath();
		}
		return readWorkbook(file);
	}

	/**
	 * 读取给定工作表中的给定位置Sheet，并转换为考勤记录表
	 * 
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	public TableAttendance readTableAttendance(HSSFWorkbook workbook,
			int sheetIndex) {
		HSSFSheet sheet = new SheetManager().readSheet(workbook, sheetIndex);
		TableAttendance tableAttendance = new SheetTableConverter()
				.sheetToTableAttendance(sheet);
		return tableAttendance;
	}

	/**
	 * 读取文件为工作薄
	 * 
	 * @param file
	 * @return
	 */
	public HSSFWorkbook readWorkbook(File file) {
		if (file != null) {
			try {
				if (isXlsFile(file)) {
					InputStream ins = new FileInputStream(file);
					return new HSSFWorkbook(ins);
				} else {
					System.out
							.println("ExcelReader.readWorkbook==>File name does not end with .xls, format not supported! Path:"
									+ file.getName());
					return null;
				}
			} catch (FileNotFoundException e) {
				System.out
						.println("ExcelReader.readWorkbook==>File not found! Path:"
								+ file.getAbsolutePath());
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				System.out
						.println("ExcelReader.readWorkbook==>Error occured while read workbook! Path:"
								+ file.getAbsolutePath());
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("ExcelReader.readWorkbook==>File is null!");
			return null;
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param path
	 * @return
	 */
	private File openFile(String path) {
		if (path != null) {
			File file = new File(path);
			if (file.exists()) {
				if (file.canRead()) {
					return file;
				} else {
					System.out
							.println("ExcelReader.openFile==>File cannot be read! Path:"
									+ path);
					return null;
				}
			} else {
				System.out
						.println("ExcelReader.openFile==>File does not exist! Path:"
								+ path);
				return null;
			}
		} else {
			System.out.println("ExcelReader.openFile==>File path is null!");
			return null;
		}
	}

	private boolean isXlsFile(File file) {
		if (PathUtil.getFileSuffix(file).equals(Constants.EXCEL_2003_SUFFIX)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isXlsxFile(File file) {
		if (PathUtil.getFileSuffix(file).equals(Constants.EXCEL_2007_SUFFIX)) {
			return true;
		} else {
			return false;
		}
	}

	public String getFilePath() {
		return filePath;
	}
}
