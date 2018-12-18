package net.klijent_server;

import java.util.ArrayList;
import java.util.List;

public class ArrayBuffer<T> implements Buffer<T> {
	private int capacity;
	protected List<T> buffer;
	
	public ArrayBuffer() {
		this(MAXBUFFERSIZE);
	}
	
	public ArrayBuffer(int cap) {
		if(cap>0 && cap <= MAXBUFFERSIZE)
			capacity=cap;
		else
			capacity=MAXBUFFERSIZE;
		buffer= new ArrayList<T>();
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Runnable date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Runnable date) {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
