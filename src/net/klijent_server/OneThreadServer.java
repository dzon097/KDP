package net.klijent_server;

import java.net.Socket;

public class OneThreadServer extends Server {

	public OneThreadServer(String protocol, int port) {
		super(protocol, port);
	}

	@Override
	public void processRequest(Socket client) {
		try {
			WorkingThread tserver = new WorkingThread(id++, client, protocol);
			tserver.run();
		}catch(Exception e) {}

	}

}
