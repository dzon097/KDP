package work;

public class Makepoints extends Thread {
	private Point p;
	private int n;
	SemaphoreFIFO full,  empty;
	
	public Makepoints(Point p, int n, SemaphoreFIFO f, SemaphoreFIFO em) {
		this.n=n; this.p=p;
		full=f; empty=em;
	}
	
	public void run() {
		for(int i=1; i<n; i++) {
			empty.waitS();
			p.x=i; p.y=i*i;
			full.signalS();
		}
	}
}
