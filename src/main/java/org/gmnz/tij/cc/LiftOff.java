package org.gmnz.tij.cc;

public class LiftOff implements Runnable {

	private static int taskCount = 0;

	private final int id = taskCount++;

	protected int countDown = 10;



	public LiftOff() {}



	public LiftOff(int countDown) {
		this.countDown = countDown;
	}



	public String status() {
		return String.format("#%d(%s) ", id, (countDown > 0 ? Integer.toString(countDown) : "GO!"));
	}



	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}

}
