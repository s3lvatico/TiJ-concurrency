package org.gmnz.tij.cc.exercises;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Exercise3 {

	void execute(int nTasks, int threadPoolSize) {
		useCachedThreadPool(nTasks);
		useSingleThreadExecutor(nTasks);
		useFixedThreadPool(nTasks, threadPoolSize);
		useSmallFixedThreadPool(nTasks, threadPoolSize / 2);
	}



	private void useExecutor(ExecutorService exec, int nTasks) {
		for (int i = 0; i < nTasks; i++) {
			exec.execute(new Exercise1());
		}
		exec.shutdown();
		try {
			exec.awaitTermination(5L, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	private void useCachedThreadPool(int nTasks) {
		System.out.println("---- use of cached thread pool");
		useExecutor(Executors.newCachedThreadPool(), nTasks);
	}



	private void useSingleThreadExecutor(int nTasks) {
		System.out.println("---- use of single thread executor");
		useExecutor(Executors.newSingleThreadExecutor(), nTasks);
	}



	private void useFixedThreadPool(int nTasks, int threadPoolSize) {
		System.out.println("---- use of fixed thread pool");
		useExecutor(Executors.newFixedThreadPool(threadPoolSize), nTasks);
	}



	private void useSmallFixedThreadPool(int nTasks, int threadPoolSize) {
		System.out.println("---- use of narrower fixed thread pool (size :" + threadPoolSize + ")");
		useExecutor(Executors.newFixedThreadPool(threadPoolSize), nTasks);
	}



	public static void main(String[] args) {
//		System.out.println("Press [ENTER] to start");
//		Scanner s = new Scanner(System.in);
//		s.nextLine();
//		s.close();

		Exercise3 ex3 = new Exercise3();
		ex3.execute(5, 5);
	}

}
