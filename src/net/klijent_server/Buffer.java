package net.klijent_server;

public interface Buffer<T> {
	public static final int MAXBUFFERSIZE = 150;
	public T get();
	public void put(T date);
	public void remove(T date);
	public int size();
	public int capacity();
}
