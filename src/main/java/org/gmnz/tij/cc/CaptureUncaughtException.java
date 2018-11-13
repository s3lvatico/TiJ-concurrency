package org.gmnz.tij.cc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {

    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("[ET2] run() inside " + t);
        System.out.println("[ET2] eh : " + t.getUncaughtExceptionHandler());

        throw new RuntimeException("<from ET2> here's the exception");
    }

    public String toString() {
        return getClass().getSimpleName() + hashCode();
    }
    
}

class GzExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("[GZEH] [%s] caught a [[%s]] from thread %s%n", this, e, t);
    }

}

class HandledExceptionThreadFactory implements ThreadFactory {

    @Override
	public Thread newThread(Runnable r) {
        System.out.println("[TF] " + this + " creating new Thread for " + r);
        Thread t = new Thread(r);
        System.out.println("[TF] thread created: " + t);
        t.setUncaughtExceptionHandler(new GzExceptionHandler());
        System.out.println("[TF] the exception handler is " + t.getUncaughtExceptionHandler());
		return t;
	}
}

/*
Vale veramente la pena eseguirlo perché si scoprono comportamenti molto 
interessanti.
Ad esempio, sembra che vengano creati due thread dalla threadfactory dello 
stesso executorService, mentre ex tabula sembra che la factory debba creare 
solo un thread.
*/
public class CaptureUncaughtException {

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool(new HandledExceptionThreadFactory());
        exe.execute(new ExceptionThread2());
    }
}
