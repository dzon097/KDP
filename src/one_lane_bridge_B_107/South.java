package one_lane_bridge_B_107;

public class South extends Car implements Runnable {

	private Thread t;

	public South(int id, Bridge bridge) {
		super(id, bridge);
		t = null;
	}

	@Override
	public void run() {
		starting();
		
		synchronized(bridge) {
			bridge.south.wait++;
			
			while(!((bridge.north.cross ==0)&&( bridge.south.ahead<10))) //ceka ako neko sa druge strane prelazi i ako neko ceka sa druge strane a vec je proslo N automobila
				try {
					bridge.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			bridge.south.wait--;
			bridge.south.cross++;
			if(bridge.north.wait>0) bridge.north.ahead++;
		}
		
		crossing();
		
		synchronized(bridge) {
			bridge.south.cross--;
			if(bridge.south.cross==0) {		//ako sam poslednji koji prelazi, resetujem brojac oobavestim sve da je slobodan most
				bridge.north.ahead=0;
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
