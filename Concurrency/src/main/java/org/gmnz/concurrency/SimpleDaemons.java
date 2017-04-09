package org.gmnz.concurrency;

import java.util.concurrent.TimeUnit;

/*
 * Un thread detto "daemon" ("demone" in qualche modo tradotto in italiano) è
 * inteso fornire un servizio generale che gira in background, finché il
 * programma principale è in esecuzione, senza però essere parte dell'essenza
 * del programma stesso.
 * 
 * Per questo motivo, quando tutti i thread non-daemon completano, il programma
 * viene terminato, arrestando automaticamente tutti i thread demoni nel
 * processo. D'altra parte, se esistono thread non-daemon in esecuzione, il
 * programma non termina. Ad esempio, è ovvio che il metodo main() è eseguito da
 * un thread non-daemon.
 * 
 * Occorre esplicitamente impostare la flag di daemon su un thread chiamando il
 * metodo setDaemon() prima di avviare il thread.
 * 
 * Non c'è nulla che impedisca al programma principale una volta che il metodo
 * main() completa il suo lavoro, poiché tutti i thread avviati sono demoni.
 */
public class SimpleDaemons implements Runnable {

	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);

			}
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true); // va dichiarato esplicitamente
			daemon.start();
		}
		System.out.println("all daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
