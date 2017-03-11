package org.gmnz.concurrency.task;

import java.util.concurrent.Callable;

/**
 * creato da simone in data 11/03/2017.
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "Result of TaskWithResult " + id;
    }
}
