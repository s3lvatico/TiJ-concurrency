package org.gmnz.tij.cc.interruptions;

class SynchronizedBlocked implements Runnable {

    synchronized void f() {
        while (true) { // praticamente non rilascia mai il lock
            Thread.yield();
        }
    }

    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }

    SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f();
            }
        }.start();
    }
}