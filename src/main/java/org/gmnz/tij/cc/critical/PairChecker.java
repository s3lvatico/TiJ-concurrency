package org.gmnz.tij.cc.critical;

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
