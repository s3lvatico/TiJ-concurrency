package org.gmnz.tij.cc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tentativo semplicistico ma fallace di gestione di una eccezione che viene lanciata da un task
 * all'interno di un executorService.
 * Non si prendono così le eccezioni.
 * Vedrai che viene comunque fatta emergere fino al thread principale.
 */
public class NaiveExceptionHandling {

    public static void main(String[] args) {
        try {
            ExecutorService exe = Executors.newCachedThreadPool();
            exe.execute(new ExceptionThread());
        } catch (Exception e) {
            System.err.println("l'ho presa!");
            e.printStackTrace();
        }
    }
}