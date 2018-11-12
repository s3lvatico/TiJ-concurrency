package org.gmnz.tij.cc.variations;

/**
 * usa una classe interna, anonima
 */
class InnerThread2 {

   private int cd = 5;
   private Thread t;

   InnerThread2(String name) {
      t = new Thread(name) {
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
      };
      t.start();
   }
}