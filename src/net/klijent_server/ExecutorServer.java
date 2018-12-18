package net.klijent_server;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServer extends Server {
	public static final int MAXTHREADS = 10;
	protected ExecutorService pool;

	public ExecutorServer(int numOfThreads, String protocol, int port) {
		super(protocol, port);
		pool = new Executors.newFixedThreadPool(numOfThreads);
	}

	@Override
	public void processRequest(Socket client) {
		pool.execute(new WorkingThread(0, client, protocol));
	}
	
	public void stop() {
		super.stop();
		
		pool.shutdown();
		try {
			if(!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if(!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch(InterruptedException e) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

}
