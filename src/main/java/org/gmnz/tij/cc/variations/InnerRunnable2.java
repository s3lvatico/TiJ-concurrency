package org.gmnz.tij.cc.variations;

import java.util.concurrent.TimeUnit;

/**
 * usa un runnable anonimo interno
 */
class InnerRunnable2 {
   private int cd = 5;
   private Thread t;

   InnerRunnable2(String name) {
      t = new Thread(new Runnable() {

         @Override
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
         }// ~run()

         public String toString() {
            return t.getName() + ": " + cd;
         }

      } // ~Runnable definition
            , name); // ~Thread(Runnable, String) ctor
      t.start();
   }// ~InnerRunnable2 ctor

}