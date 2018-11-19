package org.gmnz.tij.cc.threadlocal;

class Accessor implements Runnable {

    private final int id;

    Accessor(int id) { this.id = id; }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return String.format("#%d: %d", id, ThreadLocalVariableHolder.get());
    }
}