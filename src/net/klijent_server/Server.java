package net.klijent_server;

import java.io.IOException;
import java.net.*;

public abstract class Server implements Runnable {
	static int id = 0;
	public static final int DFAULTPORT = -1;
	public static final String DEFAULTPROTOCOL = "ServerProtocol";
	protected String protocol;
	protected String hot;
	protected int port;

	private Thread t;
	private boolean running;
	protected ServerSocket server = null;

	public Server(String protocol, int port) {
		this.protocol = protocol;
		this.port = port;
		t = null;
		running = false;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			while (running) {
				try {
					Socket client = server.accept();
					processRequest(client);
				} catch (Exception e) {
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}

	}

	private void close() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public abstract void processRequest(Socket client);
	
	public void stop() {
		running= false;
		close();
	}
	
	public void start() {
		if(t==null) {
			t = new Thread(this);
			running=true;
			t.start();
		}
	}

}
