package net.chat;

import java.io.IOException;
import java.net.*;

public class Server {
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			ServerSocket server = new ServerSocket(port);
			Socket client = server.accept();
			Chat c = new Chat(client);
			c.communicate();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
