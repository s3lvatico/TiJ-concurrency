package org.gmnz.tij.cc.garden;

import java.util.Random;

class Counter {

   private int count = 0;
   private Random random = new Random(41);

   // togli il synchronized per far fallire il conteggio
   // synchronized 
   int increment() {
      int temp = count;
      if (random.nextBoolean()) {
         Thread.yield();
      }
      count = ++temp;
      return count;
   }

   synchronized int value() { return count; }
}