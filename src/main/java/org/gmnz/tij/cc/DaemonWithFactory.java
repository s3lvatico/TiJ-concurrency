package org.gmnz.tij.cc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class DaemonWithFactory implements Runnable {

	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(250);
				System.out.println(Thread.currentThread() + " " + this);
			}

		} catch (InterruptedException e) {
			System.err.println("interrupted");
		}
	}



	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DaemonWithFactory());
		}
		System.out.println("all daemons have been started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}
