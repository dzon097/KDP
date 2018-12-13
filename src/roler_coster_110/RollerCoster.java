package roler_coster_110;

import java.util.concurrent.Semaphore;

public class RollerCoster extends Thread {
	public static final int C = 5;
	public int passengers;
	private Semaphore start, allAboard, stop, allOut;

	public RollerCoster(int passengers, Semaphore start, Semaphore allAboard, Semaphore stop, Semaphore allOut) {
		super();
		this.passengers = passengers;
		this.start = start;
		this.allAboard = allAboard;
		this.stop = stop;
		this.allOut = allOut;
	}

	public void run() {
		while (true) {
			boardCar();
			ride();
			leaveCar();
		}
	}

	private void ride() {
		for (int i = 0; i < 50000; i++)
			;

	}

	private void leaveCar() {
		for (int i = 0; i < C; i++)
			stop.release();
		allOut.acquireUninterruptibly();
	}

	private void boardCar() {
		for (int i = 0; i < C; i++)
			start.release();
		allAboard.acquireUninterruptibly();

	}

}
