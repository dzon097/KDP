package net.klijent_server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

import javax.naming.CommunicationException;

public class ObjectSocketCommunicator implements Communicator {

	protected Socket client;
	protected ObjectOutputStream oout;
	protected ObjectInputStream oin;

	public ObjectSocketCommunicator(Socket client) {
		this.client = client;
	}

	public ObjectSocketCommunicator() {
	}

	@Override
	public boolean init() {
		boolean ok = true;
		ok = getObjectOutputstream();
		ok |= getObjectInputStream();
		return ok;
	}

	private boolean getObjectOutputstream() {
		oout=null;
		try {
			oout = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (oout!=null);
	}
	
	private boolean getObjectInputStream() {
		oin=null;
		try {
			oin = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oin!=null;
	}
	
	
	public void init(Socket client) {
		this.client = client;
		init();
	}

	@Override
	public boolean close() {
		boolean ok = true;
		try {
			oin.close();
		} catch (IOException ex) {
			ok = false;
		}
		try {
			oout.close();
		} catch (IOException ex) {
			ok = false;
		}
		try {
			client.close();
		} catch (IOException ex) {
			ok = false;
		}
		return ok;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Message<T> readMessage() throws CommunicationException {
		return (Message<T>) this.readObject();
	}

	@Override
	public <T> void writeMessage(Message<T> data) throws CommunicationException {
		this.writeObject(data);

	}

	@Override
	public Object readObject() throws CommunicationException {
		try {
			Object m = oin.readObject();
			return m;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CommunicationException();
		}
	}

	@Override
	public void writeObject(Object m) throws CommunicationException {
		try {
			oout.writeObject(m);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CommunicationException();
		}

	}

	@Override
	public String readString() throws CommunicationException {
		return (String) this.readObject();
	}

	@Override
	public void writeString(String data) throws CommunicationException {
		this.writeObject(data);

	}

	@Override
	public byte[] read() throws CommunicationException {
		byte[] b = new byte[1024];
		try {
			int num = oin.read(b);
			b= Arrays.copyOf(b, num);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public void write(byte[] data) throws CommunicationException {
		try {
			oout.write(data);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void flush() throws CommunicationException {
		try {
			oout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void reset() throws CommunicationException {
		try {
			oout.reset();
			
			// mora da se resetuju jer se u strimovima pamte svi podaci koje smo slali i to
			// je konacna kolicina prostora, da bi izbegli neko prepunjanje to treba da se
			// radi posle svake transakcije veceg tipa
			
			oin.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
