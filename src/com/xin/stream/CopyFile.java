package com.xin.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 完成文本文件之间的读写操作和复制操作；
 * 思路：用BufferedReader,BufferedWritter,FileRead,FileWrite来读写文件和复制文件；
 * 
 * @author 小鑫哦
 *
 */
public class CopyFile {
	public static void main(String[] args) {
		CopyFile of = new CopyFile();
		File f1 = new File("E:/1.txt");
		File f2 = new File("E:/2.txt");
		String content = "跟卫老师学Java，带你超神，带你飞；";
		of.write(f1, content);
		of.read(f1);
		of.copy(f1, f2);
	}

	/**
	 * 写文件；
	 * 
	 * @param file
	 *            要往里写的文件
	 * @param content
	 *            写的内容
	 */
	public void write(File file, String content) {
		Writer fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file.getAbsolutePath());
			bw = new BufferedWriter(fw, 1024);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				fw.flush();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读文件
	 * 
	 * @param file
	 *            要读的文件
	 */
	public void read(File file) {
		Reader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file.getAbsolutePath());
			br = new BufferedReader(fr, 1024);
			char[] cbuf = new char[1024];

			int n = -1;
			while ((n = br.read(cbuf)) != -1) {
				System.out.println(new String(cbuf, 0, n).trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 复制文件
	 * 
	 * @param file1
	 *            被复制的文件
	 * @param file2
	 *            复制出来的文件
	 */
	public void copy(File file1, File file2) {
		Reader r = null;
		BufferedReader br = null;
		Writer w = null;
		BufferedWriter bw = null;
		
		
		try {
			r = new FileReader(file1.getAbsolutePath());
			br = new BufferedReader(r, 1024);
			w = new FileWriter(file2.getAbsolutePath());
			bw = new BufferedWriter(w, 1024);
			char[] cbuf = new char[1024];
			int n = -1;
			while ((n = br.read(cbuf)) != -1) {
				bw.write(cbuf, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				w.flush();
				bw.flush();
				bw.close();
				w.close();
				br.close();
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}