package org.gmnz.tij.cc.critical;

/**
 * Task che esercita un {@link PairManager} chiamando di continuo il metodo {@link PairManager#increment()}.
 * Questo provoca l'aggiornamento del {@link Pair} gestito dal PairManager e la memorizzazione di una sua immagine nello
 * storage locale del PairManager.
 * 
 */
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
