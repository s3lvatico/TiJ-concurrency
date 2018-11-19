package org.gmnz.tij.cc.critical;

/**
 * Controlla lo stato di un {@link PairManager}.
 * <p>
 * E' un task che esercita il PairManager incrementando il contatore interno al PairManager e facendo una verifica sullo
 * stato del Pair
 */
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
