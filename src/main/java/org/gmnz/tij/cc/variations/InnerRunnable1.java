package org.gmnz.tij.cc.variations;

import java.util.concurrent.TimeUnit;

/**
 * uso di un'implementazione di Runnable con nome
 */
class InnerRunnable1 {

   private int cd = 5;

   private class Inner implements Runnable {

      private Thread t;

      Inner(String name) {
         t = new Thread(this, name);
         t.start();
      }

      public String toString() {
         return t.getName() + ": " + cd + "  ";
      }

      public void run() {
         try {
            while (true) {
               System.out.print(this + "  ");
               if (--cd == 0) {
                  System.out.println();
                  return;
               }
               TimeUnit.MILLISECONDS.sleep(100);
            }
         } catch (InterruptedException e) {
            System.err.println("interrupted");
         }
      }
   }

   public InnerRunnable1(String name) {
      new Inner(name);
   }
}