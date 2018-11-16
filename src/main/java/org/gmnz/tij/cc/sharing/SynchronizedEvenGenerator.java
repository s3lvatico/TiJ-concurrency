package org.gmnz.tij.cc.sharing;

public class SynchronizedEvenGenerator extends AbstractIntegerGenerator {

   private int currentEvenValue = 0;




   public synchronized int next() {
      ++currentEvenValue;
      Thread.yield();
      ++currentEvenValue;
      return currentEvenValue;
      /* stesso identico codice, ma sincronizzato */
   }




   public static void main(String[] args) {
      EvenChecker.test(new SynchronizedEvenGenerator());
   }
   
}