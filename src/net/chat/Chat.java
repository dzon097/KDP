package net.chat;

import java.net.Socket;

public class Chat {
	Socket client;

	public Chat(Socket client) {
		this.client = client;
	}

	public void communicate() {
		try {
			ReadThread read = new ReadThread(client);
			WriteThread write = new WriteThread(client);
			read.start();
			write.start();
			read.join();
			write.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
