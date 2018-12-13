package sasinhronize;

public class Printpoints extends Thread {
	private Point p;
	int n;
	
	public Printpoints(Point p, int n) {
		this.n=n; this.p=p;
	}
	
	public void run() {
		for(int i=0; i<n; i++) {
			synchronized(p) {
				while(!p.full)
					try {
						p.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			String s = p.x+" "+p.y;
			System.out.println(s);
			p.full=false;  p.notifyAll();
			}
		}
	}
}
