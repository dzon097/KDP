package work;

public class Printpoints extends Thread {
	private Point p;
	int n;
	SemaphoreFIFO full,  empty;
	
	public Printpoints(Point p, int n,SemaphoreFIFO f, SemaphoreFIFO em) {
		this.n=n; this.p=p;
		full=f; empty=em;
	}
	
	public void run() {
		for(int i=0; i<n; i++) {
			full.waitS();
			String s = p.x+" "+p.y;
			System.out.println(s);
			empty.signalS();
		}
	}
}
