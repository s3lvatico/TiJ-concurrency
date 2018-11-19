package org.gmnz.tij.cc.critical;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {

    // prova i due approcci
    static void testApproaches(PairManager mgr1, PairManager mgr2) {
        ExecutorService xtor = Executors.newCachedThreadPool();

        /*
         * Ciascun PairManager ricevuto viene osservato/operato in due thread distinti
         * da un PairManipulator (che incrementa e memorizza un'immagine del pair) e da
         * un PairChecker che verifica la consistenza di stato del Pair.
         */

        PairManipulator manipulator1 = new PairManipulator(mgr1);
        PairManipulator manipulator2 = new PairManipulator(mgr2);

        PairChecker checker1 = new PairChecker(mgr1);
        PairChecker checker2 = new PairChecker(mgr2);

        xtor.execute(manipulator1);
        xtor.execute(manipulator2);
        xtor.execute(checker1);
        xtor.execute(checker2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("sleep interrupted");
        }
        System.out.printf("mgr1 : %s | mgr2: %s%n", manipulator1, manipulator2);
        System.exit(0);
    }

    /*
     * Il problema che si vuole risolvere è di avere a disposizione la classe Pair,
     * che per natura non è threadsafe e di doverla usare in un ambiente con thread.
     * 
     * A questo scopo si crea la classe PairManager, che è sostanzialmente un
     * decoratore di Pair che gestisce gli accessi verso il Pair stesso. PairManager
     * espone i metodi pubblici getPair() che è threadsafe (sincronizzato) mentre
     * per il metodo increment() la sincronizzazione viene gestita in modo
     * differente nelle implementazioni a valle. Il metodo increment() e in qualche
     * modo un template method. In una delle due implementazioni del PairManager, il
     * metodo increment() è totalmente sincronizzato, mentre nell'altra viene usato
     * un blocco sincronizzato.
     * 
     * PairManipulator è creato per testare i due tipi diversi di PairManager,
     * chiamando increment() in un task mentre un PairChecker gira in un altro task.
     * Per tracciare la frequenza con cui il checker riesce a eseguire il controllo,
     * il PairChecker esegue le sue chiamate in contemporanea.
     * 
     * I numeri mostreranno chiaramente che il PairManager con il metodo increment()
     * totalmente sincronizzato di fatto rallenta pesantemente (i.e. blocca)
     * l'accesso al metodo getPair() (che è invocato durante il processo di test).
     * Il risultato è che il PairManager con il metodo increment totalmente
     * sincronizzato permette un numero di chiamate al metodo di controllo
     * estremamente inferiore all'omologo metodo nella specializzazione in cui
     * increment() ha un blocco sincronizzato al suo interno.
     */

    public static void main(String[] args) {
        PairManager pman1 = new FullySynchronizedPairManager();
        PairManager pman2 = new SynchSectionPairManager();
        testApproaches(pman1, pman2);
    }

}
