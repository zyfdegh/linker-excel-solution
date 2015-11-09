package com.linker.ferdinand.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SheetManager {
	public HSSFSheet readSheet(HSSFWorkbook workbook, int sheetIndex) {
		if (workbook != null) {
			int sheetNum = workbook.getNumberOfSheets();
			if (sheetIndex >= 0 && sheetIndex < sheetNum) {
				return workbook.getSheetAt(sheetIndex);
			} else {
				System.out
						.println("sheetIndex invalid.It should be >= 0 and < workbook.getNumberOfSheets()");
				return null;
			}
		} else {
			return null;
		}
	}
	
}
