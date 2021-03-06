package com.foldercopy01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

/**
 * 完成文本文件之间的读写操作和复制操作；
 * 思路：用BufferedReader,BufferedWritter,FileRead,FileWrite来读写文件和复制文件；
 * 
 * @author 小鑫哦
 *
 */
public class CopyFile {
	private long currentSpace = 0;//以当前的复制了多少（以bit为单位的）
	
	
	@Test //测试方法
	public void test() {
		
//		CopyFile of = new CopyFile();
//		File f1 = new File("E:/1.txt");
//		File f2 = new File("E:/2.txt");
//		String content = "跟卫老师学Java，带你超神，带你飞；";
//		of.write(f1, content);
//		of.read(f1);
//		of.copy(f1, f2);
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
	 * 复制文件: 采用了BufferStream和FileStream
	 * 
	 * @param copyFile
	 *            被复制的文件
	 * @param newFile
	 *            复制出来的文件
	 */

	public boolean copy(File copyFile, File newFile) {
		InputStream r = null;
		BufferedInputStream br = null;
		OutputStream w = null;
		BufferedOutputStream bw = null;
		
		try {
			r = new FileInputStream(copyFile.getAbsolutePath());
			br = new BufferedInputStream(r, 1024);
			w = new FileOutputStream(newFile.getAbsolutePath());
			bw = new BufferedOutputStream(w, 1024);
			byte[] buf = new byte[1024]; 
			int n = -1;
			while ((n = br.read(buf)) != -1) {
				bw.write(buf, 0, n);
				this.setCurrentSpace(n); //设置当期的文件传输量；
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
		return true; 
	}

/*	public boolean copy(File copyFile, File newFile) {
		InputStream r = null;
		OutputStream w = null;
		
		try {
			r = new FileInputStream(copyFile.getAbsolutePath());
			w = new FileOutputStream(newFile.getAbsolutePath());
			byte[] buf = new byte[1024]; 
			int n = -1;
			while ((n = r.read(buf)) != -1) {
				w.write(buf, 0, n);
				this.setCurrentSpace(n); //设置当期的文件传输量；
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				w.flush();
				w.close();
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true; 
	}
	*/
	public long getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(long n) {
		this.currentSpace +=n;  //以bit为单位
	}
}