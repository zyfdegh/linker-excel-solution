package com.linker.ferdinand.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.linker.ferdinand.other.Constants;

public class FileUtil {
	/**
	 * 用于拷贝文件源文件srcFile到新的文件dstFile
	 * 
	 * @param srcFile
	 *            源文件
	 * @param dstFile
	 *            目标文件
	 * @return
	 */
	public short copyFile(File srcFile, File dstFile) {
		// 如果文件已经存在
		if (dstFile != null && dstFile.exists() && dstFile.length() >= 0) {
			return Constants.COPY_RESULT_EXIST;// 文件已经存在
		} else {
			FileChannel inChannel;
			FileChannel outChannel;
			try {
				inChannel = new FileInputStream(srcFile).getChannel();
				outChannel = new FileOutputStream(dstFile).getChannel();
				inChannel.transferTo(0, inChannel.size(), outChannel);
				if (inChannel != null)
					inChannel.close();
				if (outChannel != null)
					outChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
				return Constants.COPY_RESULT_FAIL;// 失败
			} catch (Exception e2) {
				e2.printStackTrace();
				return Constants.COPY_RESULT_FAIL;// 失败
			}

			if (dstFile != null && dstFile.exists() && dstFile.length() >= 0) {
				return Constants.COPY_RESULT_SUCCESS;// 拷贝成功
			} else {
				return Constants.COPY_RESULT_FAIL;// 失败
			}
		}
	}
}
