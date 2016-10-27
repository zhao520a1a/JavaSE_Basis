package com.foldercopy02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.foldercopy01.CopyFile;

public class ThreadPool extends Thread {
	
	// 声明一个带缓存的线程池,线程池为无限大，可以自动进行线程回收，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程， 而不用每次新建线程。
	private static final ExecutorService ES = Executors.newCachedThreadPool();
	//Executort提供了可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。 
	//List<Future<String>> futures = new ArrayList<Future<String>>();
	public ExecutorService getES() {
		return ES;
	}
//	public List<Future<String>> getFutures() {
//		return futures;
//	}
//	public void addFuture(Future<String> future) {
//		futures.add(future);
//	}
//	public void traverFutures() throws InterruptedException, ExecutionException {
//		for(Future<String> f: futures){
//			System.out.println("返回值："+f.get());//输出返回的值
//		}
//	}
	
	
}

/**
 * 目的：创建一个返回结果并且可能抛出异常的任务（复制文件），实现回调功能；
 * Callable接口类似于Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。
 * 但是Runnable不会返回结果，并且无法抛出返回结果的异常，
 * 而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，Future可以拿到异步执行任务的返回值;
 * 即：Callable和Future,一个产生结果，一个拿到结果。
 */
class MyCall implements Callable<String> {
	private File file;
	private File newFolder;
	//这里必须是FolderCopy中CopyFile的对象才行，否则数据会不一致；
	private CopyFile of = FolderCopy.getInstance().getOf();
	
	public MyCall(final File file, final File newFolder) {
		this.file = file;
		this.newFolder = newFolder;
	}
	
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + "开始运行……");
		of.copy(file, new File(newFolder, file.getName())); //复制文件
		String s = Thread.currentThread().getName() + "完成！";
		return s;
	}
	
}
