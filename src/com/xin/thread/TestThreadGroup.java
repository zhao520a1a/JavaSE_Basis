package com.xin.thread;

public class TestThreadGroup implements Runnable {
	public static void main(String[] args) {
		TestThreadGroup tg = new TestThreadGroup();
		tg.func();
	}

	public void func() {

		ThreadGroup pGroup = new ThreadGroup("父亲线程组");

		ThreadGroup cGroup = new ThreadGroup(pGroup, "儿子线程组");

		Thread t1 = new Thread(pGroup, this);// 创建线程，加入父线程组
		System.out.println("Starting " + t1.getName() + "...");
		t1.start();

		Thread t2 = new Thread(cGroup, this);// 创建线程，加入儿线程组
		System.out.println("Starting " + t2.getName() + "...");
		t2.start();

		// 返回此线程组中活动线程的估计数。结果并不能反映并发活动，并且可能受某些系统线程的存在状态的影响。
		// 由于结果所固有的不精确特性，建议只将此方法用于信息目的。
		Thread[] list = new Thread[pGroup.activeCount()];
		int count = pGroup.enumerate(list);// 得到不确定的活动线程数量
		for (int i = 0; i < count; i++) {// 遍历每个线程
			System.out.println("线程组 " + list[i].getName() + " found");
		}

	}

	public void run() {

		for (int i = 0; i < 1000; i++) {
			i++;
		}
		System.out.println(Thread.currentThread().getName() + " finished executing.");
	}

}
