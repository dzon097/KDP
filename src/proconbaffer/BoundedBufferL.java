package proconbaffer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferL<T> implements Buffer<T> {
	private Lock lock;
	private Condition notFull, notEmpty;
	
	private List<T> buffer;
	private int size;
	
	public BoundedBufferL(int s) {
		size=s;
		buffer= new LinkedList<T>();
		lock = new ReentrantLock();
		notFull=lock.newCondition();
		notEmpty=lock.newCondition();
	}

	@Override
	public void put(T x) throws InterruptedException {
		lock.lock();
		try {
			while(size==buffer.size()) notFull.await();
			buffer.add(x);
			notEmpty.signal();
			
		} catch (Exception e) {}
		finally {
			lock.unlock();
		}
	}

	@Override
	public T get() throws InterruptedException {
		lock.lock();
		try{
			while(buffer.size()==0) notEmpty.await();
			T x= buffer.remove(0);
			notFull.signal();
			return x;
		}
		finally {
			lock.unlock();
		}
	}

}
