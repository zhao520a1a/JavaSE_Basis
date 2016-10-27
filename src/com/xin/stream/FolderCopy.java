package com.xin.stream;

import java.io.File;
import java.io.IOException;

import com.xin.stream.CopyFile;

/**
 * 复制一个文件夹中的文件；
 * @author 小鑫哦
 *
 */
public class FolderCopy {
	CopyFile of = new CopyFile();

	public static void main(String[] args) throws IOException {
		FolderCopy fc = new FolderCopy();

		String path1 = "./src/com/homework/stream/folder1";
		File folder1 = new File(path1);
		if (!folder1.exists()) {
			folder1.mkdir(); // 建立一个目录:folder1(被复制的文件夹)
		} else {
			System.out.println("文件存在！");
		}
		String path2 = "./src/com/homework/stream/folder2";
		File folder2 = new File(path2);
		if (!folder2.exists()) {
			folder2.mkdir(); // 建立一个目录:folder2(复制的文件夹)
		} else {
			System.out.println("文件存在！");
		}

		fc.makeFiles(folder1);

		fc.copyFolder(folder1, folder2);
	}

	private void makeFiles(File f) throws IOException {
		File f1 = new File(f, "1.txt"); // 建立一个文本文件；
		f1.createNewFile();
        of.write(f1, "你好，I miss you!");
		
		File f2 = new File(f, "folder1.1"); // 建立下一层目录；
		f2.mkdir();
		System.out.println(f2.canExecute() );
		File f3 = new File(f2, "2.txt"); // 建立一个文本文件；
		f3.createNewFile();
		of.write(f3, "I miss you too!");
		
		System.out.println(f.getAbsolutePath());
	}

	/**
	 * 思路：通过调用递归来进行复制
	 * 递归获取指定目录下的所有的文件（包括子文件夹）
	 * @param folder1 被复制的文件夹
	 * @param folder2 复制出来的文件夹
	 */
	private void copyFolder(File folder1, File folder2) {
		File[] fs = folder1.listFiles();
		for (File f : fs) {
			if (f.isDirectory()) {
				//若是一个目录，则在复制的文件夹中建立相同名称的目录；
				File newFolder = new File(folder2, f.getName());
				newFolder.mkdir();
				this.copyFolder(f, newFolder);  //递归最终实现一个文件夹中所有文件的复制；
			}
			of.copy(f, new File(folder2, f.getName())); //复制文件
			System.out.println(f.getAbsolutePath());
		}
	}
}
