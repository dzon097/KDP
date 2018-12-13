package proconbaffer;

public class BoundedBufferS<T> implements Buffer<T> {
	private T[] buffer;
	private int tail, head, count;
	
	@SuppressWarnings("unchecked")
	public BoundedBufferS(int size) {
		buffer =(T[]) new Object[size];
		tail=head=count=0;
	}
	
	@Override
	public synchronized void put(T x) throws InterruptedException {
		while(count==buffer.length) wait();
		buffer[tail]=x;
		tail=(tail+1)% buffer.length;
		count++;
		notifyAll();
	}
	
	@Override
	public T get() throws InterruptedException {
		while(count==0)  wait();
		T x= buffer[head];
		head=(head+1) % buffer.length;
		count--;
		notifyAll();
		return x;
	}
}
