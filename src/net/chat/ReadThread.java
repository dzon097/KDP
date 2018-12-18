package net.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

	Socket client;
	InputStream in;
	BufferedReader pin;

	public ReadThread(Socket client) {
		try {
			this.client = client;
			this.in = client.getInputStream();
			pin = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			String s;
			while((s=pin.readLine()) != null)
				System.out.println(">"+s);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				pin.close();
				in.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
