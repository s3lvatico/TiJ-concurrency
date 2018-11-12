package org.gmnz.tij.cc;

public class BasicThreads {

	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("waiting for liftoff");
	}

}
