
package org.gmnz.tij.cc.critical;

/**
 * Specializzazione di {@link PairManager} con metodo {@link #increment}
 * implementato con una sezione protetta
 */
class SynchSectionPairManager extends PairManager {

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
