
/**
 * 守护线程 在多数情况下，实际上想创建的是一个能够在应用程序中做一些简单的、周期性任务的后台线程。可调用 setDaemon()方法标记一个线程是守护线程。
 * 用户线程和守护线程的区别： 用户线程：Java虚拟机在它所有非守护线程都离开后则Java虚拟机才离开。
 * 守护线程：守护线程则是用来服务用户线程的，如果没有其他用户线程在运行，那么就没有可服务对象，
 * 也就没有理由继续下去，守护线程会自动离开。一旦系统内都剩下守护线程，并不影响JVM的离开。
 * 
 * 
 */

public class DefendThread extends Thread {
	public DefendThread(){//在构造函数中创建守护线程
		this.setDaemon(true);//false：为非守护线程, 
		//true：为守护线程 伺候很多个用户线程，知道用户线程over
		start();
	}

	public void run() {
		// 守护线程
		for (int i = 0; i <= 300; i++) {// 定300范围可以看出效果
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("守护线程E:" + i);

		}
	}

	public static void main(String[] args) {
		DefendThread e = new DefendThread();// 守护线程 伺候两个线程
		UserThread e1 = new UserThread();// 用户线程
		/*
		 * 用户线程
		 */
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("用户线程:" + i);
		}

	}

}

/*
 * 设为用户线程
 */
class UserThread extends Thread {
	public UserThread() {
		this.setDaemon(false);// 由于设置成用户线程，该程序的守护线程等待其退出后一并退出。如果设置成守护线程，则用户线程退出后，他和另一个守护线程一并退出
		this.start();
	}

	public void run() {
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("用户线程E1:" + i);
		}

	}
}