package org.gmnz.concurrency.task;

/**
 * creato da simone in data 11/03/2017.
 */
public class Fib implements Runnable {

    private final _F f;

    public Fib(int n) {
        if (n > 0) {
            f = new _F(n);
        } else {
            f = null;
        }
    }

    @Override
    public void run() {
        if (f == null) return;
        int n = f.get();
        System.out.println(n);
    }

    private class _F {
        private final int N; // n-esimo termine della sequenza

        _F(int n) {
            this.N = n;
        }

        int get() {
            return f(N);
        }

        private int f(int n) {
            if (n == 1) return 0;
            if (n == 2) return 1;
            int f_n_1 = f(n - 1);
            Thread.yield();
            int f_n_2 = f(n - 2);
            return f_n_1 + f_n_2;
        }
    }
}
