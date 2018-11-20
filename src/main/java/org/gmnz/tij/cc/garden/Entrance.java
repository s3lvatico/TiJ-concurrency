package org.gmnz.tij.cc.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Entrance implements Runnable {

   private final int id;

   /**
    * numero di accessi attraverso questa Entrance
    */
   private int number = 0;

   // tutte le Entrance fanno riferimento ad un unico Counter
   // (che è quindi la risorsa condivisa)
   private static Counter counter = new Counter();

   // elenco di tutte le Entrance
   private static List<Entrance> entrances = new ArrayList<>();

   // flag di arresto
   private static volatile boolean canceled = false;




   static void cancel() {
      canceled = true;
   }




   // CTOR
   Entrance(int id) {
      this.id = id;
      entrances.add(this);
   }




   /**
    * finché il task non viene interrotto, incrementa il numero di accessi attraverso questa Entrance
    */
   public void run() {
      while (!canceled) {
         synchronized (this) {
            ++number;
         }
         System.out.format("%s Total: %d%n", this, counter.increment());
         try {
            TimeUnit.MILLISECONDS.sleep(100);
         } catch (InterruptedException e) {
            System.err.println("sleep interrupted");
         }
      }
      System.out.println("Stopping: " + this);
   }



   /**
    * ottiene il numero di accessi attraverso questa Entrance
    * @return numero di accessi
    */
   synchronized int getValue() {
      return number;
   }




   public String toString() {
      return String.format("Entrance [%d]: %d", id, getValue());
   }




   static int getTotalCount() {
      return counter.value();
   }




   static int sumEntrances() {
      int sum = 0;
      for (Entrance e : entrances) {
         sum += e.getValue();
      }
      return sum;
   }

}