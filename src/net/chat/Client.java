package net.chat;

import java.io.IOException;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[1]);
			String host = args[0];

			Socket server = new Socket(host, port);
			Chat s = new Chat(server);
			s.communicate();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
