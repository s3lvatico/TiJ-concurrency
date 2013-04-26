package org.gmnz.concurrency.task;

public class LiftOff implements Runnable {

	private static int taskCount = 0;
	/**
	 * Serve a distinguere le varie istanze. Dichiarato final perché una volta
	 * inizializzato non serve cambiarlo.
	 */
	private final int id = ++taskCount;
	protected int countDown = 10;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Lift off!") + "). ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 * Tipicamente questo metodo ha un qualche tipo di loop che si ripete finché
	 * il compito ("task") non è più necessario. Occorre quindi stabilire una
	 * condizione secondo la quale si esca dal loop (la più semplice opzione è
	 * quella di un enunciato "return" all'interno del metodo run() ). Spesso
	 * accade che il metodo run() sia implementato sotto forma di loop infinito;
	 * questo significa che, salvo i fattori che causano l'interruzione del
	 * metodo run(), questo continuerà la sua esecuzione indefinitamente.
	 */
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			/*
			 * La chiamata a yield() è un suggerimento al thread scheduler. Il
			 * thread scheduler è quella parte del meccanismo di threading di
			 * Java che sposta la CPU da un thread all'altro. Questo
			 * "suggerimento" dice
			 * "ho completato le parti importanti del mio ciclo e questo è un buon momento per spostarsi su un altro thread se necessario"
			 * . E' totalmente opzionale, ma è usato qui perché tende a produrre
			 * un output più interessante nei vari esempi.
			 */
			Thread.yield();
		}
	}

	
}
