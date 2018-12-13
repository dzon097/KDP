package one_lane_bridge_B_107;

public class North extends Car implements Runnable {

	private Thread t;

	public North(int id, Bridge bridge) {
		super(id, bridge);
		t = null;
	}

	@Override
	public void run() {
		starting();
		
		synchronized(bridge) {
			bridge.north.wait++;
			
			while(!((bridge.south.cross ==0)&&( bridge.north.ahead<10))) //ceka ako neko sa druge strane prelazi i ako neko ceka sa druge strane a vec je proslo N automobila
				try {
					bridge.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			bridge.north.wait--;
			bridge.north.cross++;
			if(bridge.south.wait>0) bridge.south.ahead++;
		}
		
		crossing();
		
		synchronized(bridge) {
			bridge.north.cross--;
			if(bridge.north.cross==0) {		//ako sam poslednji koji prelazi, resetujem brojac oobavestim sve da je slobodan most
				bridge.south.ahead=0;
				bridge.notifyAll();
			}
		}

	}

	@Override
	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}

	}

}
