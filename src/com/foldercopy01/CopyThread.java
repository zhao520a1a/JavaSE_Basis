package com.foldercopy01;

import java.util.concurrent.ExecutionException;

public class CopyThread implements Runnable {
	private FolderCopy fc = FolderCopy.getInstance();
	private String copyPath;
	private String newPath;
	
	public CopyThread(String copyPath, String newpath) {
		this.copyPath = copyPath;
		this.newPath = newpath;
	}
	
	@Override
	public void run() {
				fc.copyFolder(copyPath, newPath);
System.out.println("CopyThread运行完毕！");
	}

	

}
