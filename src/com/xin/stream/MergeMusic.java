package com.xin.stream;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 熟悉RandomAccessFile类(即可当输入流又可以当输入流)的方法； 将两首歌曲合并成一首歌曲；
 */
public class MergeMusic {
	public static void main(String[] args) {
		merge();
	}

	private static void merge() {
		RandomAccessFile ri = null;
		RandomAccessFile ro = null;
		try {
			ri = new RandomAccessFile("E://1.txt", "r"); // 输入流
			ro = new RandomAccessFile("E://2.txt", "rw");// 输出流
			byte[] buffer = new byte[1024 * 1024];
			int n = -1;
			System.out.println(ri.getFilePointer());
			// ro.seek(ro.length()); //在song2后加song1;
			ro.seek(ro.length() / 2); // 在song2的一半后加song1，但song2的另一半就没有了;

			while ((n = ri.read(buffer)) != -1) {
				ro.write(buffer, 0, n);
				System.out.println(ri.getFilePointer());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ro.close();
				ri.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
