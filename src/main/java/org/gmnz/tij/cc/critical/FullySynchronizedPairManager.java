package org.gmnz.tij.cc.critical;

/**
 * Specializzazione di {@link PairManager} con metodo {@link PairManager#increment}
 * completamente sincronizzato
 */
class FullySynchronizedPairManager extends PairManager {

   synchronized void increment() {
      p.incrementX();
      p.incrementY();
      store(getPair());
   }

}
