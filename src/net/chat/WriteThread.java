package net.chat;

import java.io.*;
import java.net.*;



public class WriteThread extends Thread {
	Socket client;

	OutputStream out;
	PrintWriter pout;

	public WriteThread(Socket client) {
		try {
			this.client=client;
			this.out = client.getOutputStream();
			pout = new PrintWriter(out, true);
	
			}
		 catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void run() {
		try {
			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while(!pout.checkError() && (s=br.readLine())!=null)
				pout.print(s);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				pout.close();
				out.close();
				client.close();
			}catch(IOException e) {}
		}
	}

}
