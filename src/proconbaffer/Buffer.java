package proconbaffer;

public interface Buffer<T> {
	
	void put(T x) throws InterruptedException;
	
	T get() throws InterruptedException;

}
