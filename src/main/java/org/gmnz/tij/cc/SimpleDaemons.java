package org.gmnz.tij.cc;


import java.util.concurrent.TimeUnit;


public class SimpleDaemons implements Runnable {

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



	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("all daemons started");
		TimeUnit.SECONDS.sleep(30);
	}
}
