package org.gmnz.tij.cc.interruptions;

import java.io.IOException;
import java.io.InputStream;

class IOBlocked implements Runnable {

    private InputStream in;

    IOBlocked(InputStream in) {
        this.in = in;
    }

    public void run() {
        try {
            System.out.println("waiting for read():");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.err.println("interrupted from blocked I/O");
            } else {
                throw new RuntimeException();
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}