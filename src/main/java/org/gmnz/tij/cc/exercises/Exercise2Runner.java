package org.gmnz.tij.cc.exercises;


import java.util.Random;


public class Exercise2Runner {

	private static final int N_THREADS = 5;



	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < N_THREADS; i++) {
			int sequenceLength = r.nextInt(20) + 1;
			new Thread(new Exercise2(sequenceLength)).start();
		}
	}

}
