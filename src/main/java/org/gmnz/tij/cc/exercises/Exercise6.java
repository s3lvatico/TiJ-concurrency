package org.gmnz.tij.cc.exercises;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class RandomSleepingTask implements Runnable {

	private static int instances = 0;

	private int id = ++instances;

	private long sleepTime;



	public RandomSleepingTask() {
		Random r = new Random();
		sleepTime = r.nextInt(10) + 1;
	}



	@Override
	public void run() {
		try {
			Thread.yield();
			TimeUnit.SECONDS.sleep(sleepTime);
			System.out.printf("task [%d][%s] slept for %d seconds and has ended%n", id, Thread.currentThread().getName(), sleepTime);
		} catch (InterruptedException e) {
			System.err.println("");
		}
	}

}





public class Exercise6 {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("wrong number of arguments");
			System.exit(1);
		}

		int nThreads = -1;
		try {
			nThreads = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("invalid argument - must be an integer number");
			System.exit(2);
		}
		ExecutorService exec = Executors.newFixedThreadPool(nThreads);
		for (int i = 0; i < nThreads; i++) {
			exec.execute(new RandomSleepingTask());
		}
		exec.shutdown();
	}

}
