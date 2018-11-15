package org.gmnz.tij.cc.sharing;

public abstract class AbstractIntegerGenerator {

   private volatile boolean canceled = false;

   public abstract int next();

   public void cancel() {
      this.canceled = true;
   }

   public boolean isCanceled() {
      return canceled;
   }

}