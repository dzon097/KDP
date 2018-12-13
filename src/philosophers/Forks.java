package philosophers;

public class Forks {
	public static final int N= DiningPhilosophersTest.N;
	private int forks_available[];
	
	public Forks() {
		forks_available = new int[N];
		for(int i=0; i<N; i++) {
			forks_available[i]=2;
		}
	}
	
	public synchronized void pickup(int i) {
		while(forks_available[i]!=2)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		forks_available[(i+1)%N]--;
		forks_available[(i+N-1)%N]--;
		notifyAll();
	}
	
	public synchronized void putdown(int i) {
		forks_available[(i+1)%N]++;
		forks_available[(i+N-1)%N]++;
		notifyAll();
	}
		

}
