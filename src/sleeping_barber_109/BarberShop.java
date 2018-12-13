package sleeping_barber_109;

public class BarberShop {
	private int numChairs;
	private int numPresent;
	private int[] chairs;
	private int nextCair;
	private int nextToShave;
	private boolean done;

	public BarberShop(int nc) {
		chairs =  new int[nc];
		numChairs=nc;
		numPresent=nextCair=nextToShave=0;
		done =false;
	}
	
	public synchronized int getNextCustomer() {
		while(numPresent==0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return chairs[nextToShave];
	}

	public synchronized void finishedCut() {
		done=true;
		notifyAll();
		while(done)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		nextToShave++;
		nextToShave%=numChairs;
		numPresent--;
		notifyAll();
	}

	public synchronized boolean getHaircut(int id) {
		if(numPresent == numChairs) return false;
		numPresent++;
		int myChair = nextCair++;
		chairs[myChair]=id;
		nextCair %= numChairs;
		while (myChair != nextToShave)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		notifyAll();
		while(!done)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		done=false;
		notifyAll();
		return true;
	}

}
