package sasinhronize;

public class ProduserConsumer {
	public static final int N=10;

	public static void main(String[] args) {
		int n= N;
		Point p = new Point(0, 0, true);
		Makepoints maker = new Makepoints(p, n);
		Printpoints print = new Printpoints(p, n);
		print.start();
		maker.start();
	}
}
	
