package org.gmnz.concurrency;


import org.gmnz.concurrency.task.LiftOff;

/**
 * Nota che il metodo {@link Thread#start()} non è mai bloccante
 */
public class MoreBasicThreads {

    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.out.println("waiting for liftoff...");
        }
    }
}
