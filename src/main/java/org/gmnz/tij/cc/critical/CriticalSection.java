package org.gmnz.tij.cc.critical;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        PairManager pman1 = new FullySynchronizedPairManager();
        PairManager pman2 = new SynchSectionPairManager();
        testApproaches(pman1, pman2);
    }
}
