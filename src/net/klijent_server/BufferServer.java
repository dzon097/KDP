package net.klijent_server;

import java.awt.List;
import java.net.Socket;
import java.util.ArrayList;

public class BufferServer extends Server {
	public static final int MAXTHREADS = 10;
	protected Buffer<Runnable> buffer;

	public BufferServer(int num, String protocol, int port) {
		super(protocol, port);
		buffer = new ArrayBuffer<Runnable>();
		for(int i=0; i<num;i++) {
			Thread t = new BufferWorker(i, buffer);
			t.setDaemon(true);
			t.start();
		}
	}

	@Override
	public void processRequest(Socket client) {
		
		
	}

}
