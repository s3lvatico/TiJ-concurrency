package org.gmnz.tij.cc.critical;

/** task che manipola una coppia incrementandola di continuo */
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
