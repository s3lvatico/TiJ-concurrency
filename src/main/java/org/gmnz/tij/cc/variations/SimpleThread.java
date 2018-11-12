package org.gmnz.tij.cc.variations;

public class SimpleThread extends Thread {

	private static int threadCount = 0;

	private int countDown = 5;



	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
	}



	public String toString() {
		return String.format("#%s(%d) ", getName(), countDown);
	}



	public void run() {
		while (true) {
			System.out.print(this);
			if (--countDown == 0) {
				System.out.println();
				return;
			}
		}
	}



	/* ---- */

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}
}
