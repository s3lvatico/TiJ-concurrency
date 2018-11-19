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

   /**
    * repository dei {@link Pair}
    */
   private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());




   /**
    * Ottiene una immagine-copia della {@link Pair} nel suo stato attuale.
    */
   synchronized Pair getPair() {
      return new Pair(p.getX(), p.getY());
   }




   /**
    * incrementa il valore di entramble le componenti della {@link Pair} e poi la
    * memorizza nello storage. Questo è solo il contratto del metodo.
    */
   abstract void increment();




   /**
    * Memorizza nella collezione la {@link Pair} specificata.
    * Le specializzazioni di questa classe devono chiamare questo metodo all'interno del metodo
    * {@link PairManager#increment()}
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

}
