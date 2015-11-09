package com.linker.ferdinand.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.linker.ferdinand.model.TableSummary;

public class ExcelWriter {
	private String savePath;

	public ExcelWriter(String savePath) {
		super();
		this.savePath = savePath;
	}

	/**
	 * 写入tableSummary到给定Sheet
	 * 
	 * @param tableSummary
	 * @throws IOException
	 */
	public void writeSheetSummaryToExcel(HSSFWorkbook workbook,
			TableSummary tableSummary) throws IOException {
		File file = createFile(savePath);
		if (file != null) {
			if (workbook != null) {
				// 写入到Workbook
				HSSFSheet sheet = workbook.getSheetAt(0);
				if (sheet == null) {
					sheet = workbook.createSheet();
				}
				if (tableSummary != null) {
					sheet = new SheetTableConverter().tableSummaryToSheet(
							workbook, tableSummary);
					writeWorkbookToFile(workbook, file);
				}
				workbook.close();
			} else {
				// 新建Workbook
				workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet();
				if (tableSummary != null) {
					sheet = new SheetTableConverter().tableSummaryToSheet(
							workbook, tableSummary);
					writeWorkbookToFile(workbook, file);
				} else {
					System.out.println("Sheet is null");
				}
				workbook.close();
			}
		} else {
			System.out.println("创建文件失败");
		}
	}

	private void writeWorkbookToFile(HSSFWorkbook workbook, File file)
			throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		if (file.exists() && file.canWrite()) {
			// write workbook to file
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
			fos.close();
		} else {
			System.out.println("Cannot write to file");
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private File createFile(String filePath) throws IOException {
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				System.out.println("文件已存在,将覆盖文件:" + filePath);
				return file;
			} else {
				// 新建文件
				file.createNewFile();
				return file;
			}
		} else {
			System.out.println("保存路径为null");
			return null;
		}
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
