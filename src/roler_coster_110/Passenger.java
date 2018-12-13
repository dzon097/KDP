package roler_coster_110;

import java.util.concurrent.Semaphore;

public class Passenger extends Thread {
	private int id;
	private Semaphore start, allAboard, stop, allOut, mutex;
	private RollerCoster coaster;
	
	public Passenger(int id, Semaphore start, Semaphore allAboard, Semaphore stop, Semaphore allOut, Semaphore mutex,
			RollerCoster coaster) {
		super();
		this.id = id;
		this.start = start;
		this.allAboard = allAboard;
		this.stop = stop;
		this.allOut = allOut;
		this.mutex = mutex;
		this.coaster = coaster;
	}
	
	public void run() {
		while(true) {
			starting();
			
			boardCar();
			ride();
			leaveCar();
		}
	}

	private void ride() {
		try {
			sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void starting() {
		try {
			sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void leaveCar() {
		stop.acquireUninterruptibly();
		mutex.acquireUninterruptibly();
		coaster.passengers--;
		System.out.println("leaveCar "+ id);
		
		if(coaster.passengers==0)
			allOut.release();
		mutex.release();
	}

	private void boardCar() {
		start.acquireUninterruptibly();
		
		mutex.acquireUninterruptibly();
		coaster.passengers++;
		System.out.println("boardCr "+id);
		if(coaster.passengers == RollerCoster.C)
			allAboard.release();
		mutex.release();
		
	}

}
