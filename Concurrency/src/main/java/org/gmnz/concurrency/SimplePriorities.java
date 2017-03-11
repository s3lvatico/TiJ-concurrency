package org.gmnz.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * La priorità di un thread indica l'importanza di un thread allo schedulatore.
 * Sebbene l'ordine con cui una CPU esegua un insieme di thread sia
 * indeterminato, lo schedulatore terrà più in considerazione l'esecuzione del
 * thread in attesa con la priorità più alta. Questo, tuttavia, non significa
 * che i thread con priorità più bassa non saranno eseguiti (in altre parole:
 * non si arriva mai ad un deadlock a causa delle priorità). I thread a priorità
 * inferiore, semplicemente, tendono ad essere eseguiti meno spesso.
 * 
 * Nella stragrande maggioranza dei casi, tutti i thread dovrebbero essere
 * eseguiti alla priorità predefinita. Tentare di manipolare le priorità dei
 * thread è normalmente considerata una cattiva pratica.
 * 
 * @author gemini
 *
 */
public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d; // no optimization
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i<5;i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
