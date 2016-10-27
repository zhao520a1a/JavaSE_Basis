package com.xin.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/**
 * 将一个大文件分割成多个小文件
 * 思路：读取每个小文件的输入流，将所有的内容顺序写入目标文件的输出流中；
 * 
 * @author 小鑫哦
 *
 */
public class SmallFile {
	public static final String SUFFIX = ".txt"; // 分割后的文件名后缀
	// 将指定的文件按着给定的文件的字节数进行分割文件，其中name指的是需要进行分割的文件名，size指的是指定的小文件的大小

	public static String[] divide(String name, long size) throws Exception {
		File file = new File(name);// 定义一个文件--
		if (!file.exists() || (!file.isFile())) {
			throw new Exception("指定文件不存在！");
		}
		// 获得被分割文件父文件，将来被分割成的小文件便存在这个目录下
		File parentFile = file.getParentFile();
		// 取得文件的大小
		long fileLength = file.length();
		if (size <= 0) {
			size = fileLength / 2;
		}
		// 取得被分割后的小文件的数目
		int num = (fileLength % size != 0) ? (int) (fileLength / size + 1) : (int) (fileLength / size);
		// 存放被分割后的小文件名
		String[] fileNames = new String[num];
		// 输入文件流，即被分割的文件
		FileInputStream in = new FileInputStream(file);
		// 读输入文件流的开始和结束下标
		long end = 0;
		int begin = 0;
		// 根据要分割的数目输出文件
		for (int i = 0; i < num; i++) {
			// 对于前num - 1个小文件，大小都为指定的size
			File outFile = new File(parentFile, file.getName() + i + SUFFIX);
			// 构建小文件的输出流
			FileOutputStream out = new FileOutputStream(outFile);
			// 将结束下标后移size
			end += size;
			end = (end > fileLength) ? fileLength : end;
			// 从输入流中读取字节存储到输出流中
			for (; begin < end; begin++) {
				out.write(in.read());
			}
			out.close();
			fileNames[i] = outFile.getAbsolutePath();
		}
		in.close();
		return fileNames;
	}

	public static void readFileMessage(String fileName) {// 将分割成的小文件中的内容读出
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String string = null;
			// 按行读取内容，直到读入null则表示读取文件结束
			while ((string = reader.readLine()) != null) {
				System.out.println(string);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(final String[] args) throws Exception {
		String name = "e:/1.txt";// 最好是个大文件
		long size = 1024 * 10;// 每个文件的分割大小
		String[] fileNames = SmallFile.divide(name, size);
		System.out.println("文件" + name + "分割的结果如下：");
		for (int i = 0; i < fileNames.length; i++) {
			System.out.println(fileNames[i] + "的内容如下：");
			SmallFile.readFileMessage(fileNames[i]);
			System.out.println();
		}
	}
}
