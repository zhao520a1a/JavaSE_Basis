package com.xin.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String[] args) {
		try {
			// 运行结果如下：两个都会执行，但程序只会使用一个线程来运行。
			// new C().testDemo();

			// 输出的结果如下：从中可以发现，每四个一组输出，即一共创建了四个线程，
			// 每次每个线程都会执行输出，但不按顺序：位每一次输出都四个算是一组
			new TestThreadPool().testDemo3();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDemo() throws Exception {
		// 单例线程，任意时间(同一时间)池中只能有一个线程
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(new Runnable() {
			@Override
			public void run() {
				System.err.println("线程启动并运行" + Thread.currentThread().getName());
			}
		});
		// 执行两次，
		es.execute(new Runnable() {
			@Override
			public void run() {
				System.err.println("第二个也运行了" + Thread.currentThread().getName());
			}
		});

		es.shutdown();
		// 用于等待子线程结束，再继续执行下面的代码。该例中我设置一直等着子线程结束。
		es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
	}

	private int j = 0;// 是否执行线程run方法的开关

	public void testDemo2() throws Exception {
		// 声明一个带缓存的线程池,线程池为无限大，可以自动进行线程回收，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，
		// 而不用每次新建线程。
		ExecutorService es = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			final int a = i;// 内部类

			// 每一次execute方法，都是向池中放入一个对象
			es.execute(new Runnable() {// 先放入4个线程，再去执行run
				public void run() {

					while (j < 8) { // 4个线程不停的反复使用
						try {
							System.err.println("测试...." + a + ">" + Thread.currentThread().getName());
							j++;
							Thread.sleep(2000);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			});
		}
		es.shutdown();
		// 用于等待子线程结束，再继续执行下面的代码。该例中我设置一直等着子线程结束。
		es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

	}

	/*
	 *  声明一个类，可以被调用，类似于线程，但它可以拥有返回值
	 *  返回结果并且可能抛出异常的任务。实现者定义了一个不带任何参数的叫做 call 的方法。
	 */
	public void testDemo3() throws Exception {
	
		// Executor 提供了管理终止的方法，
		// 以及可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。
		ExecutorService es = Executors.newCachedThreadPool();// 创建无限线程池对象
		List<Future<String>> result = new ArrayList<Future<String>>();// 放结果用的集合
		for (int i = 0; i < 3; i++) {
			// 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future
			Future<String> f = es.submit(new MyCall(i));// 线程执行完成以后可以通过引用获取返回值
			result.add(f);
		}
		for (Future<String> f : result) {
			System.out.println("遍历：" + System.currentTimeMillis());
			System.out.println("返回值：" + f.get());// 输出返回的值
		}
		// 表示不在接收新的任务，并不是马上关闭正在运行的程序；
		es.shutdown();
		// 用于等待子线程结束，再继续执行下面的代码。该例中我设置一直等着子线程结束。
		es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		System.out.println("完成....");
	}
}

/*
 * Callable接口类似于Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。 但是Runnable不会返回结果，
 * 并且无法抛出返回结果的异常，而Callable功能更强大一些，被线程执行后，可以返回值，
 * 这个返回值可以被Future拿到， 即：Callable和Future,一个产生结果，一个拿到结果。从而实现回调功能；
 */
class MyCall implements Callable<String> {// callable:回调
	private int seq;

	public MyCall(int seq) {
		this.seq = seq;
	}

	// 抛出异常并可以拥有返回值
	public String call() throws Exception {
		System.out.println("执行" + seq + "," + Thread.currentThread().getName());
		System.out.println(System.currentTimeMillis());
		return "完成" + seq;// 这是返回值
	}
}
