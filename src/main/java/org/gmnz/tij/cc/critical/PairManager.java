package org.gmnz.tij.cc.critical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Gestisce una collezione di {@link Pair}
 */
abstract class PairManager {

   /**
    * 
    */
   AtomicInteger checkCounter = new AtomicInteger(0);

   /**
    * {@link Pair} gestita
    */
   protected Pair p = new Pair();

   
   // TODO come viene iniettata la Pair nel PairManager?!?


   /**
    * repository dei {@link Pair}
    */
   private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());




   /**
    * ottiene il {@link Pair} corrente
    */
   synchronized Pair getPair() {
      return new Pair(p.getX(), p.getY());
   }




   /**
    * memorizza nella collezione la {@link Pair} specificata.
    * 
    * Aggiunge all'operazione un minimo ritardo.
    * 
    * @param p
    */
   protected void store(Pair p) {
      storage.add(p);
      try {
         TimeUnit.MILLISECONDS.sleep(50);
      } catch (InterruptedException e) {
         /* ignored */ }
   }




   /**
    * incrementa il valore di entramble le componenti della {@link Pair} e poi la
    * memorizza nello storage
    */
   abstract void increment();
}
