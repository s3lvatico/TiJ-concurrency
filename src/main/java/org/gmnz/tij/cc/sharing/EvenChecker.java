package org.gmnz.tij.cc.sharing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

   private IntGenerator intGenerator;
   private final int id;




   public EvenChecker(IntGenerator intGenerator, int id) {
      this.intGenerator = intGenerator;
      this.id = id;
   }




   public void run() {
      while (!intGenerator.isCanceled()) {
         int value = intGenerator.next();
         if (value % 2 != 0) {
            System.err.printf("[!%d!] value not even <%d>%n", id, value);
            intGenerator.cancel();
         }
      }
   }




   public static void test(IntGenerator intGenerator, int count) {
      System.out.println("Press CTRL-C to exit");
      ExecutorService xtor = Executors.newCachedThreadPool();
      for (int i = 0; i < count; i++) {
         xtor.execute(new EvenChecker(intGenerator, i));
      }
      xtor.shutdown();
   }




   public static void test(IntGenerator intGenerator) {
      test(intGenerator, 10);
   }
   
}