package org.gmnz.tij.cc.variations;

/* autogestito, è un task che guida un suo proprio thread */
public class SelfManaged implements Runnable {
	 
	private int countDown = 5;
	private Thread t;
	
	public SelfManaged() {
		t = new Thread(this);
		t.start();
	}
	
	public String toString() {
		return String.format("%s(%d) ", t.getName(), countDown);
	}

	public void run() {
		while(true){
			System.out.print(this);
			if (--countDown == 0) {
				System.out.println();
				return;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i<5; i++) {
			new SelfManaged();
		}
	}
}
