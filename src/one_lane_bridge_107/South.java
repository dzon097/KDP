package one_lane_bridge_107;

public class South extends Car implements Runnable {
	private Thread t;

	public South(int id, BridgeA bridge) {
		super(id, bridge);
		t = null;
	}

	@Override
	public void run() {
		starting();
		synchronized (bridge) {
			while (bridge.north != 0)
			try {
				bridge.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bridge.south++;
		}
		crossing();
		synchronized (bridge) {
			bridge.south--;
			if (bridge.south == 0)
				bridge.notifyAll(); // ako su svi prosli sa jedne strane budi sve da krenu sa druge
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
