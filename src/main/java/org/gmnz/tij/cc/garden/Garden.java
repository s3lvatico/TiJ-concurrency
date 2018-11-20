package org.gmnz.tij.cc.garden;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Garden {

   public static void main(String[] args) throws Exception {
      ExecutorService xtor = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++) {
         xtor.execute(new Entrance(i));
      }

      TimeUnit.SECONDS.sleep(3);

      Entrance.cancel();
      xtor.shutdown();

      if (!xtor.awaitTermination(250, TimeUnit.MILLISECONDS)) {
         System.err.println("some tasks were not terminated");
      }

      System.out.println("Total: " + Entrance.getTotalCount());
      System.out.println("Sum of entrances: " + Entrance.sumEntrances());
   }

}
/*
 * Un singolo oggetto Counter tiene traccia di tutti i visitatori del giardino, ed è rappresentato da una variabile di
 * classe nella classe Entrance. I metodi Counter.increment() e Counter.value() sono sincronizzati per controllare
 * l'accesso al campo count. Il metodo increment() usa un oggetto Random per invocare yield approssimativamente la metà
 * delle volte che viene chiamato. Se si toglie il "synchronized" al metodo increment() il programma fallisce il
 * conteggio, perché task multipli accederanno e modificheranno il contatore simultaneamente - e qui la chiamata a
 * yield() è intenzionale, proprio per far manifestare il problema più facilmente).
 * 
 * Ogni oggetto Entrance mantiene un valore locale, "number", che contiene il numero di visitatori che sono passati
 * attraverso quella particolare Entrance. Questo fornisce un doppio controllo sull'oggetto "counter" per far sì che
 * venga registrato il numero corretto di visitatori. Il metodo run() incrementa il contatore e poi si ferma per 100 ms.
 * 
 * Poiché Entrance.canceles è dichiarato "volatile" ed è una flag che è solo letta e scritta indipendentemente da altri
 * campi, è possibile sfruttarla senza un accesso sincronizzato.
 * 
 * Dopo 3 secondi, main() invia il messaggio cancel() alla class Entrance e poi chiama lo shutdown dell'executor. L'uso
 * di awaitTermination() sull'executor attende che ogni task completi la sua esecuzione; viene anche impostato un
 * timeout d'attesa per dar modo ad ogni task di completare l'esecuzione in modo normale. Diversamente viene scritto un
 * messaggio informativo. E' interessante vedere come i task che terminano la loro esecuzione non vengano presi dal
 * garbage collector perché sono comunque referenziati all'interno della lista; per questo il metodo sumEntrances()
 * lavora ancora con oggetti Entrance validi.
 */