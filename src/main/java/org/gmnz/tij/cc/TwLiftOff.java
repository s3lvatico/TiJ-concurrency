package org.gmnz.tij.cc;

public class TwLiftOff implements Runnable {

	private static int taskCount = 0;

	private final int id = taskCount++;

	protected int countDown = 10;



	public TwLiftOff() {}



	public TwLiftOff(int countDown) {
		this.countDown = countDown;
	}



	public String status() {
		String threadName = Thread.currentThread().getName();
		return String.format("#%d-%s-(%s)  ", id, threadName, (countDown > 0 ? Integer.toString(countDown) : "GO!"));
	}



	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}

}
