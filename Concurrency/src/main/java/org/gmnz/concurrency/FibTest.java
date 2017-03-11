package org.gmnz.concurrency;

import org.gmnz.concurrency.task.Fib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * creato da simone in data 11/03/2017.
 */
public class FibTest {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++)
            exec.execute(new Fib(i));
        exec.shutdown();
    }
}
