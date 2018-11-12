package org.gmnz.tij.cc;


import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class TaskWithResult implements Callable<String> {

	private int id;



	TaskWithResult(int id) {
		this.id = id;
	}



	@Override
	public String call() throws Exception {
		return String.format("this is the result of task %d", id);
	}

}





public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Collection<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}
		try {
			for (Future<String> result : results) {
				System.out.println(result.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			exec.shutdown();
		}
	}

}
