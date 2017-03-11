package org.gmnz.concurrency;

import org.gmnz.concurrency.task.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * creato da simone in data 11/03/2017.
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
/*
        con il SingleThreadExecutor non importa quanti task vengano
        pianificati per l'esecuzione; ne verrà eseguito sempre uno
        alla volta, mentre gli altri attenderanno in coda
         */

/*
        che alla fine può anche essere considerato un modo per
        sincronizzare dei compiti, o serializzarli
 */
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
