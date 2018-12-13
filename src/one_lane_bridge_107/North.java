package one_lane_bridge_107;

public class North extends Car implements Runnable {
	private Thread t;

	public North(int id, BridgeA bridge) {
		super(id, bridge);
		t = null;
	}

	@Override
	public void run() {
		starting();
		synchronized (bridge) {
			while (bridge.south != 0)
				try {
					bridge.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			bridge.north++;
		}
		crossing();

		synchronized (bridge) {
			bridge.north--;
			if (bridge.north == 0)
				bridge.notifyAll();
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
