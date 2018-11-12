package org.gmnz.tij.cc.exercises;

public class Exercise1 implements Runnable {

	private static int instances = 0;

	private int id = instances++;



	public Exercise1() {
		System.out.println("ex1 created with id " + id);
	}



	public void run() {
		System.out.printf("[#%d] run message 1%n", id);
		Thread.yield();
		System.out.printf("[#%d] run message 2%n", id);
		Thread.yield();
		System.out.printf("[#%d] run message 3%n", id);
		Thread.yield();

		System.out.printf("[#%d] end of task%n", id);
	}
}
