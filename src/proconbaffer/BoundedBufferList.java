package proconbaffer;

import java.util.LinkedList;
import java.util.List;

public class BoundedBufferList<T> implements Buffer<T> {
	private List<T> buffer;
	private int size;
	
	public BoundedBufferList(int s) {
		buffer = new LinkedList<T>();		// ili ArrayList<T>();
		size=s;
	}

	@Override
	public void put(T x) throws InterruptedException {
		while(buffer.size() == size) wait();
		buffer.add(x);
		notifyAll();
	}

	@Override
	public T get() throws InterruptedException {
		while(buffer.isEmpty()) wait();
		T temp= buffer.remove(0);
		notifyAll();
		return temp;
	}

}
