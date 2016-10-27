package com.foldercopy01;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

/**
 * 复制一个文件夹中的文件；
 * 
 * @author 小鑫哦
 *
 */
public class FolderCopy {
	private CopyFile of = new CopyFile();
	private long space; // 文件夹的大小
	private long currentSpace = 0;
	private static FolderCopy fc = null;

	//运用了单例模式
	private FolderCopy() {
	}

	public static FolderCopy getInstance() {
		if(fc == null) {
			fc = new FolderCopy();
		}
		return fc;
	}

	@Test // 测试方法
	public void text() throws IOException, InterruptedException, ExecutionException {
		int space = (int) (FolderCopy.getInstance()).getSpace("E://test");
		System.out.println(space/1024);
		String copyPath = "E://test";
		String newPath = "E://copy文档";
	    FolderCopy.getInstance().copyFolder(copyPath, newPath);
		System.out.println(fc.getCurrentSpace()/1024);
	}


	/**
	 * 得到指定文件路径下的空间大小
	 * @param path
	 * @return
	 */
	public long getSpace(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getSpace(f.getAbsolutePath());
			}
			space += f.length();
		}
		return space;
	}

	/**
	 * 得到在复制过程中的当前的大小；
	 * @return
	 */
	public long getCurrentSpace() {
		this.currentSpace += of.getCurrentSpace();
		return currentSpace;
	}

	/**
	 * 思路：通过调用递归来进行复制 递归获取指定目录下的所有的文件（包括子文件夹）
	 * 
	 * @param copyFolder
	 *            被复制的文件夹
	 * @param newFolder
	 *            复制出来的文件夹
	 */
	public void copyFolder(String copyPath, String newPath)  {
		File copyFolder = new File(copyPath);
		File newFolder = new File(newPath);

		if (!newFolder.exists()) {
			newFolder.mkdir(); // 建立一个目录:newFolder
		} else {
			System.out.println("文件已存在,无需新建！");
		}

		File[] fs = copyFolder.listFiles();
		for (File f : fs) {
			if (f.isDirectory()) {
//				// 若是一个目录，则在新文件夹中建立相同名称的子目录；
//				File subFolder = new File(newFolder, f.getName());
//				subFolder.mkdir();
				this.copyFolder(f.getAbsolutePath(), new File(newFolder, f.getName()).getAbsolutePath()); // 递归最终实现一个文件夹中所有文件的复制；
			}
			of.copy(f, new File(newFolder, f.getName())); // 单线程复制文件
		}
	}

}
