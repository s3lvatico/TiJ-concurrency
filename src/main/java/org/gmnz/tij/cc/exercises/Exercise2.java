package org.gmnz.tij.cc.exercises;

public class Exercise2 implements Runnable {

	/*
	 * creare un task che produce una successione di n numeri di Fibonacci, dove n è
	 * passato come parametro del costruttore del task
	 */

	private static int instances = 0;

	private int id;
	private int idxMax;

	private Fibonacci fib;



	public Exercise2(int n) {
		id = instances++;
		idxMax = n;
		System.out.printf("[%d] initialized - sequence length : %d%n", id, idxMax);
		fib = new Fibonacci();
	}



	public void run() {
		for (int i = 1; i <= idxMax; i++) {
			System.out.format("[%d]:%d  ", id, fib.get(i));
			Thread.yield();
		}
		System.out.format("[%d]:END%n", id);
	}
}
