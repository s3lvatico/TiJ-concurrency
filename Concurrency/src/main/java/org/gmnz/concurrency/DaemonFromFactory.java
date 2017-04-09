package org.gmnz.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Oguno dei metodi della ExecutorService ha un overload che accetta un oggetto
 * ThreadFactory che viene usato per creare nuovi thread
 */
public class DaemonFromFactory implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.err.println("sleep() interrupted");
		}

	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DaemonFromFactory());
		}

		System.out.println("all daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}