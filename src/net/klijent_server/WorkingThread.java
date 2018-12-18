package net.klijent_server;

import java.net.*;

public class WorkingThread extends Thread {
	protected long id;
	protected String protocol;
	protected Socket client;
	
	public WorkingThread(int id, Socket client, String protocol) {
		super("Working Thread" + id);
		this.id = id;
		this.client = client;
		this.protocol = protocol;
	}

	public void run() {
		work();
	}
	
	public void work() {
		Communicator communicator = getCommunicator();
		try {
			Protocol prot = ProtocolFactory.pf.createProtocol(protocol);
			if(protocol == null)return;		//nepostoji zahtevani protokol za isvravanje
			communicator.init();
			prot.addComunicator(communicator);
			prot.conversation();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			communicator.close();
		}
	}

	private Communicator getCommunicator() {
		return new ObjectSocketCommunicator(getClient());
	}
	
	public Socket getClient() {
		System.out.print("getClient" + this.getClass());
		return client;
	}

}
