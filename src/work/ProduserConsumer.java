package work;

public class ProduserConsumer {
	public static final int N=89;

	public static void main(String[] args) {
		int n= N;
		SemaphoreFIFO full = new SemaphoreFIFO(1), empty = new SemaphoreFIFO(0);
		Point p = new Point(0, 0);
		Makepoints maker = new Makepoints(p, n,full, empty);
		Printpoints print = new Printpoints(p, n, full,empty);
		print.start();
		maker.start();
		
		
	}
}
	
