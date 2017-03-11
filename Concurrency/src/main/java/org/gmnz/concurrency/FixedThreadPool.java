package org.gmnz.concurrency;

import org.gmnz.concurrency.task.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * creato da simone in data 11/03/2017.
 */
public class FixedThreadPool {
    static final int N_THREADS = 5;

    public static void main(String[] args) {
        /*
            il vantaggio di usare la fixed thread pool è che la allocazione
            dei thread per i task è fatta tutta all'inizio, quindi si riduce
            l'overhead di creazione
        */
        ExecutorService exec = Executors.newFixedThreadPool(N_THREADS);
        for (int i = 0; i < N_THREADS; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
