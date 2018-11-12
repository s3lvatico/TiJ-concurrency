package org.gmnz.tij.cc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		
		final int nTasks = 5;
		final int threadPoolSize = 3;
		
		ExecutorService exec = Executors.newFixedThreadPool(threadPoolSize);
		for (int i = 0; i < nTasks; i++) {
			exec.execute(new TwLiftOff());
		}
		exec.shutdown();

	}
}
