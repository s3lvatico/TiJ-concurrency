package org.gmnz.concurrency;

import java.util.concurrent.ThreadFactory;

/*
 * L'uso di una threadfactory specifica può servire nel caso in cui thread
 * demoni si vogliano utilizzare con gli Executors.
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
