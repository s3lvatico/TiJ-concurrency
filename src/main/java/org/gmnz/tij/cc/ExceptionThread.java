package org.gmnz.tij.cc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {

    public void run() {
        throw new RuntimeException("esempio di eccezione");
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(new ExceptionThread());
    }
}