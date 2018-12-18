package net.klijent_server;

import java.util.*;

public class ArrayBuffer<T> implements Buffer<T> {
	private int capacity;
	protected List<T> buffer;

	public ArrayBuffer() {
		this(MAXBUFFERSIZE);
	}

	public ArrayBuffer(int cap) {
		if (cap > 0 && cap <= MAXBUFFERSIZE)
			capacity = cap;
		else
			capacity = MAXBUFFERSIZE;
		buffer = new ArrayList<T>();
	}

	@Override
	public synchronized T get() {
		while (buffer.size() == 0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		T data = buffer.remove(0);
		notifyAll();
		return data;
	}

	@Override
	public synchronized void put(T date) {
		while (buffer.size() == capacity)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		buffer.add(date);
		notifyAll();
	}

	@Override
	public void remove(T date) {
		try {
			int index = buffer.indexOf(date);
			if (index < 0)
				return;
			buffer.remove(index);
		} catch (Exception e) {
		}

	}

	@Override
	public int size() {
		return buffer.size();
	}

	@Override
	public int capacity() {
		return capacity;
	}

}
