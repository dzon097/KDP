package work;

import java.util.LinkedList;

public class SemaphoreFIFO {
	//formiram listu objekata na kojima ce biti blokirane niti,  vaka blokirana nit imace svoj objekat na kome ceka

	class WaitObject{ //Objekat na kome ce se nit blokirati ako je pozvala wait i uslov nije ispunjen
		static final int DONE= 0;
		static final int NOTDONE=1;
		
		int state;			//stanje objekta da li je neko pozvao signal(),  i da li ta nit mozeda nastavi
		
		public WaitObject() {
			state=NOTDONE;
		}
		
		public synchronized void await() {
			while(state != DONE) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public synchronized void signal() {
			state=DONE;
			notify();
		}
	}
	
	private int s;
	private LinkedList<WaitObject> queue;
	
	public SemaphoreFIFO(int val) {
		s=val;
		queue= new LinkedList<WaitObject>();
	}
	
	@Deprecated
	public synchronized void initS(int val) {
		s=val;
		queue= new LinkedList<WaitObject>();
	}
	
	private synchronized WaitObject Lock() {
		WaitObject result = null;
		if(queue.size()>0 || s<=0) {
			result= new WaitObject();
			queue.add(result);
		}else s--;
		return result;
	}
	
	public void waitS() {
		WaitObject lock = Lock();
		if(lock==null) {
			return;
		}
		lock.await();
	}
	
	public synchronized void signalS() {
		if(queue.size()>0) {
			WaitObject waitObj = queue.remove();
			waitObj.signal();
		}else s++;
	}
	
	
}
