package org.gmnz.tij.cc.variations;

class ThreadMethod {

   private int cd = 5;

   private Thread t;

   private String name;

   public ThreadMethod(String name) {
      this.name = name;
   }

   public String toString() {
      return t.getName() + ": " + cd;
   }

   public void runTask() {
      if (t == null) {
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
            }// ~run()

            public String toString() {
               return getName() + ": " + cd;
            }
         };// ~Thread construction
         t.start();
      } // ~ if
   }
}