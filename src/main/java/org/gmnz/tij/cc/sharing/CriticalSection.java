package org.gmnz.tij.cc.sharing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Una coppia di interi positivi.
 * 
 * Le componenti possono solo essere incrementate singolarmente. Ci sono metodi
 * per l'accesso in lettura alle componenti.
 * 
 * Il controllo di stato viene effettuato controllando se le componenti sono
 * uguali. Una difformità causa un'eccezione.
 */
class Pair {
    private int x;
    private int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pair() {
        this(0, 0);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void incrementX() {
        x++;
    }

    void incrementY() {
        y++;
    }

    public String toString() {
        return String.format("[%d, %d]", x, y);
    }

    public class PairValuesNotEqualException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public PairValuesNotEqualException() {
            super("Pair values not equal " + Pair.this);
        }
    }

    /**
     * Controlla lo stato.
     * 
     * Se le due componenti sono diverse viene lanciata una
     * {@link PairValuesNotEqualException}
     */
    void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}

/**
 * Gestisce una collezione di {@link Pair}
 */
abstract class PairManager {

    /**
     * 
     */
    AtomicInteger checkCounter = new AtomicInteger(0);

    /**
     * 
     */
    protected Pair p = new Pair();

    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    synchronized Pair getPair() {
        // make a copy, to keep the original safe
        return new Pair(p.getX(), p.getY());
    }

    /**
     * memorizza nella collezione la {@link Pair} specificata.
     * 
     * Aggiunge all'operazione un minimo ritardo.
     * 
     * @param p
     */
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            /* ignored */ }
    }

    /**
     * incrementa il valore di entramble le componenti della {@link Pair} e poi la
     * memorizza nello storage
     */
    abstract void increment();
}

/**
 * Specializzazione di {@link PairManager} con metodo {@link #increment}
 * completamente sincronizzato
 */
class PairManager1 extends PairManager {

    synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

/**
 * Specializzazione di {@link PairManager} con metodo {@link #increment}
 * implementato con una sezione protetta
 */
class PairManager2 extends PairManager {

    void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

// task che manipola una coppia incrementandola di continuo
class PairManipulator implements Runnable {

    private PairManager pm;

    PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true) {
            pm.increment();
        }
    }

    public String toString() {
        return String.format("Pair: %s | checkCounter: %d", pm.getPair(), pm.checkCounter.get());
    }
}

// task che controlla lo stato di una coppia
class PairChecker implements Runnable {

    private PairManager pm;

    PairChecker(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {

    // prova i due approcci
    static void testApproaches(PairManager pm1, PairManager pm2) {
        ExecutorService xtor = Executors.newCachedThreadPool();

        PairManipulator m1 = new PairManipulator(pm1);
        PairManipulator m2 = new PairManipulator(pm2);

        PairChecker check1 = new PairChecker(pm1);
        PairChecker check2 = new PairChecker(pm2);

        xtor.execute(m1);
        xtor.execute(m2);
        xtor.execute(check1);
        xtor.execute(check2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("sleep interrupted");
        }
        System.out.printf("pm1 : %s | pm2: %s%n", m1, m2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pman1 = new PairManager1();
        PairManager pman2 = new PairManager2();
        testApproaches(pman1, pman2);
    }
}
