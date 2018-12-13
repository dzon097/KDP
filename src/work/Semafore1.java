package work;

public class Semafore1 {
	private int s;
	
	public Semafore1(int val) {
		s=val;
	}
	
	public synchronized void initS(int i) {
		s=i;
	}
	
	public synchronized void waitS() {
		while(s<=0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		s--;
	}
	
	public synchronized void signlS() {
		s++;
		notifyAll();
	}

}
