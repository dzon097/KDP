package proconbaffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedBufferP<T> implements Buffer<T> {
	private BlockingQueue<T> buffer;
	
	public BoundedBufferP(int size) {
		buffer= new ArrayBlockingQueue<>(size);
	}

	@Override
	public void put(T x) throws InterruptedException {
		buffer.put(x);

	}

	@Override
	public T get() throws InterruptedException {
		return buffer.take();
	}

}

