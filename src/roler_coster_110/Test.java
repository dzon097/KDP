package roler_coster_110;

import java.util.concurrent.*;

public class Test {
	public static final int NUMP=10;
	
	public static void main(String[] args) {
		Semaphore start = new Semaphore(0),
				allAboard = new Semaphore(0),
				stop	=	new Semaphore(0),
				mutex = new Semaphore(1),
				allOut = new Semaphore(0);
		Passenger[] passengers = new Passenger[NUMP];
		RollerCoster coaster = new RollerCoster(0,start, allAboard, stop, allOut);
		coaster.start();
		for(int i =0; i< NUMP; i++) {
			passengers[i] = new Passenger(i, start, allAboard, stop, allOut, mutex, coaster);
			passengers[i].start();
		}
	}

}
