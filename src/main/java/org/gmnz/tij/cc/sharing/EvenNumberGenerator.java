package org.gmnz.tij.cc.sharing;

public class EvenNumberGenerator extends AbstractIntegerGenerator {

   private int currentEvenValue = 0;

   public int next() {
      currentEvenValue++;
      currentEvenValue++;
      return currentEvenValue;
   }

   public static void main(String[] args) {
      EvenChecker.test(new EvenNumber
   Generator());
   }
}