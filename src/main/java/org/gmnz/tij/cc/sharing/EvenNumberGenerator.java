package org.gmnz.tij.cc.sharing;

public class EvenNumberGenerator extends AbstractIntegerGenerator {

   private int currentEvenValue = 0;




   public int next() {
      currentEvenValue++;
      /*
       * la chiamata a yield() aiuta a generare l'errore.
       * Si "consiglia" al thread scheduler di poter passare ad un altro thread,
       * e così facendo si aumenta la probabilità che un client chiami il metodo next()
       * quando il valore corrente è stato incrementato una sola volta (e che quindi sia
       * un numero dispari)
       */
      Thread.yield(); // questo aiuta a generare l'errore
      /*
       * ti deve servire a capire che i problemi con il multithreading possono essere "nascosti", e/o che si possono
       * manifestare in condizioni non deterministiche
       */
      currentEvenValue++;
      return currentEvenValue;
   }




   public static void main(String[] args) {
      EvenChecker.test(new EvenNumberGenerator());
   }
}