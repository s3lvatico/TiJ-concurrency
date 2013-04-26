package org.gmnz.concurrency.task;

public class LiftOff implements Runnable {

	private static int taskCount = 0;
	/**
	 * Serve a distinguere le varie istanze. Dichiarato final perch� una volta
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
	 * Tipicamente questo metodo ha un qualche tipo di loop che si ripete finch�
	 * il compito ("task") non � pi� necessario. Occorre quindi stabilire una
	 * condizione secondo la quale si esca dal loop (la pi� semplice opzione �
	 * quella di un enunciato "return" all'interno del metodo run() ). Spesso
	 * accade che il metodo run() sia implementato sotto forma di loop infinito;
	 * questo significa che, salvo i fattori che causano l'interruzione del
	 * metodo run(), questo continuer� la sua esecuzione indefinitamente.
	 */
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			/*
			 * La chiamata a yield() � un suggerimento al thread scheduler. Il
			 * thread scheduler � quella parte del meccanismo di threading di
			 * Java che sposta la CPU da un thread all'altro. Questo
			 * "suggerimento" dice
			 * "ho completato le parti importanti del mio ciclo e questo � un buon momento per spostarsi su un altro thread se necessario"
			 * . E' totalmente opzionale, ma � usato qui perch� tende a produrre
			 * un output pi� interessante nei vari esempi.
			 */
			Thread.yield();
		}
	}

	
}
