package org.gmnz.tij.cc.interruptions;

import java.util.concurrent.TimeUnit;

class SleepBlocked implements Runnable {

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        }
        catch(InterruptedException e) {
            System.err.println("interrupted!");
        }
        System.out.println("exiting SleepBlocked.run()");
    }
}