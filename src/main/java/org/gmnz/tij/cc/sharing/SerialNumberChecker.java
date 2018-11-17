package org.gmnz.tij.cc.sharing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CircularSet {

    private int[] array;
    private int len;
    private int index = 0;

    CircularSet(int size) {
        array = new int[size];
        len = size;

        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    /**
     * aggiunge un intero all'insieme
     * 
     * @param i
     */
    synchronized void add(int i) {
        array[index] = i;
        /*
         * la posizione della prossima operazione di scrittura è fatta scorrere in
         * modulo, così si realizza la circolarità
         */
        index = ++index % len;
    }

    synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }
}

class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    static synchronized int nextSerialNumber() {
        return serialNumber++;
    }

}

public class SerialNumberChecker {

    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService xtor = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(1);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            xtor.execute(new SerialChecker());
        }
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("no duplicates detected");
            System.exit(0);
        }
    }

}