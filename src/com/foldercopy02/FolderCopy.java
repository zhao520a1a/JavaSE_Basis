package com.foldercopy02;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.foldercopy01.CopyFile;

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
	ThreadPool threadPool = new ThreadPool();
	private static FolderCopy fc;

	// 运用单例模式
	private FolderCopy() {
	}

	public static FolderCopy getInstance() {
		if (fc == null) {
			fc = new FolderCopy();
		}
		return fc;
	}

	@Test // 测试方法
	public void text() throws IOException, InterruptedException, ExecutionException {
		int space = (int) (FolderCopy.getInstance()).getSpace("E://test");
		System.out.println(space / 1024);
		String copyPath = "E://test";
		String newPath = "E://copy文档";
		FolderCopy.getInstance().copyFolder(copyPath, newPath);
		System.out.println(fc.getCurrentSpace() / 1024);
	}

	/**
	 * 得到指定文件路径下的空间大小
	 * 
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
	 * 
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
	public synchronized void copyFolder(String copyPath, String newPath)
			throws InterruptedException, ExecutionException {
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
				// 若是一个目录，则在新文件夹中建立相同名称的子目录；
				File subFolder = new File(newFolder, f.getName());
				subFolder.mkdir();
				this.copyFolder(f.getAbsolutePath(), subFolder.getAbsolutePath()); // 递归最终实现一个文件夹中所有文件的复制；
			}

			// 在线程池中开一个新的线程；
			Future<String> future = threadPool.getES().submit(new MyCall(f, newFolder));
			// 输入线程返回的结果；
			System.out.println("返回值：" + future.get());
		}
		// 遍历返回值
		// threadPool.getES().shutdown();
		// 用于等待子线程结束，再继续执行下面的代码。该例中我设置一直等着子线程结束。
		// threadPool.getES().awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		System.out.println("完成....");
	}

	public CopyFile getOf() {
		return of;
	}

}
