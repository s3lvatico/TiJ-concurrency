package org.gmnz.concurrency;


import org.gmnz.concurrency.task.LiftOff;


/**
 * La metodologia tradizionale per convertire un oggetto {@link Runnable} in un
 * task vero e proprio è fornirlo come parametro al costruttore della classe
 * {@link Thread}.
 *
 * @author smonotti
 *
 */
public class BasicThreads {

   /*
    * Invocare il metodo start() della classe Thread eseguirà le
    * inizializzazioni necessarie per il thread e invocherà il metodo run()
    * dell'oggetto Runnable associato, che verrà eseguito in un thread diverso
    * da quello corrente.
    *
    * Sebbene start() sembri eseguire una chiamata a un metodo elaborato, che
    * potenzialmente può impiegare molto tempo per il suo completamento, si può
    * notare che la riga stampata a console appare prima che il conto alla
    * rovescia sia iniziato. Questo significa che il metodo start() ritorna
    * subito al chiamante.
    *
    * Ciò che è stato fatto in effetti, è una chiamata al metodo LiftOff.run(),
    * metodo che non è ancora rientrato. Ma poiché run() è eseguito in un thread
    * a parte, si possono eseguire altre operazioni nel thread corrente.
    * Per questo motivo il programma principale esegue due metodi
    * contemporaneamente.
    */
   public static void main(String[] args) {
      Thread t = new Thread(new LiftOff());
      t.start();
      System.out.println("waiting for liftoff...");
   }

}
