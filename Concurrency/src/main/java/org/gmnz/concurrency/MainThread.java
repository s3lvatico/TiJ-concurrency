package org.gmnz.concurrency;

import org.gmnz.concurrency.task.LiftOff;

public class MainThread {
    public static void main(String[] args) {
        LiftOff l = new LiftOff();
        l.run();
    }
}
