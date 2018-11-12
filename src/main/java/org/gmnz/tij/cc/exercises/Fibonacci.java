package org.gmnz.tij.cc.exercises;


import java.util.ArrayList;
import java.util.List;


public class Fibonacci {

	private List<Integer> sequence;



	public Fibonacci() {
		sequence = new ArrayList<>();
		sequence.add(0);
		sequence.add(1);
	}



	/**
	 * Ottiene il k-esimo elemento della sequenza
	 * 
	 * @param k
	 * @return
	 */
	public int get(int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("invalid element");
		}
		if (k > sequence.size()) {
			for (int i = sequence.size(); i < k; i++) {
				sequence.add(sequence.get(i - 1) + sequence.get(i - 2));
			}
		}
		return sequence.get(k - 1);
	}
}
