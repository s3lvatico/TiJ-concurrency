package org.gmnz.tij.cc.exercises;

public class Exercise1Runner {

	private static final int N_THREADS = 5;



	public static void main(String[] args) {
		for (int i = 0; i < N_THREADS; i++) {
			new Thread(new Exercise1()).start();
		}
	}

}
