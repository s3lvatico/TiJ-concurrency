package org.gmnz.tij.cc.sharing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}

// protect a Pair inside a thread-safe class
abstract class PairManager {

    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    synchronized Pair getPair() {
        // make a copy, to keep the original safe
        return new Pair(p.getX(), p.getY());
    }

    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            /* ignored */ }
    }

    abstract void increment();
}