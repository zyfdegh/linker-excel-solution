package com.linker.ferdinand.main;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		String relativelyPath = System.getProperty("user.dir");
		File file = new File(relativelyPath + "\\file\\OutModel.xls");
		if (file.exists()) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
