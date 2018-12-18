package net.klijent_server;

import javax.naming.CommunicationException;

public interface Communicator {

	public boolean init();
	public boolean close();
	
	public <T> Message<T> readMessage() throws CommunicationException;
	public <T> void writeMessage(Message<T> data) throws CommunicationException;
	public Object readObject() throws CommunicationException;
	public void writeObject(Object data) throws CommunicationException;
	public String readString() throws CommunicationException;
	public void writeString(String data) throws CommunicationException;
	public byte[] read() throws CommunicationException;
	public void write(byte[] data) throws CommunicationException;
	public void flush() throws CommunicationException;
	public void reset() throws CommunicationException;

}
