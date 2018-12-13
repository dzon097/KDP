package sasinhronize;

public class Makepoints extends Thread {
	private Point p;
	private int n;
	
	public Makepoints(Point p, int n) {
		this.n=n; this.p=p;
	}
	
	public void run() {
		for(int i=1; i<n; i++) {
			synchronized(p) {
				while(p.full)
					try {
						p.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.x=i; p.y=i*i; p.full=true; p.notify();
			}
		}
	}
}
