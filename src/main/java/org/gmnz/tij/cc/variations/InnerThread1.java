package org.gmnz.tij.cc.variations;

/**
 * usa una classe-thread interna, che viene avviata appena si istanzia questa
 * classe
 */
class InnerThread1 {

   private int cd = 5;

   private class Inner extends Thread {

      Inner(String name) {
         super(name);
         start();
      }

      public void run() {
         try {
            while (true) {
               System.out.print(this + "  ");
               if (--cd == 0) {
                  System.out.println();
                  return;
               }
               sleep(100);
            }
         } catch (InterruptedException e) {
            System.err.println("interrupted");
         }
      }

      public String toString() {
         return getName() + ": " + cd;
      }
   }

   InnerThread1(String name) {
      new Inner(name);
   }

}
